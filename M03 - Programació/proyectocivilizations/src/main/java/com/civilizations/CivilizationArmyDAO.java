package com.civilizations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CivilizationArmyDAO implements Variables {

    CivilizationDAO civilizationDAO = new CivilizationDAO();

    public int setSwordsman(int id) {
        return createAttackUnit(id, FOOD_COST_SWORDSMAN, WOOD_COST_SWORDSMAN, IRON_COST_SWORDSMAN, "Swordsman", ARMOR_SWORDSMAN, BASE_DAMAGE_SWORDSMAN);
    }
    
    public int setSpearman(int id) {
        return createAttackUnit(id, FOOD_COST_SPEARMAN, WOOD_COST_SPEARMAN, IRON_COST_SPEARMAN, "Spearman", ARMOR_SWORDSMAN, BASE_DAMAGE_SPEARMAN);
    }
    
    public int setCrossbow(int id) {
        return createAttackUnit(id, FOOD_COST_CROSSBOW, WOOD_COST_CROSSBOW, IRON_COST_CROSSBOW, "Crossbow", ARMOR_SWORDSMAN, BASE_DAMAGE_CROSSBOW);
    }
    
    public int setCannon(int id) {
        return createAttackUnit(id, FOOD_COST_CANNON, WOOD_COST_CANNON, IRON_COST_CANNON, "Cannon", ARMOR_SWORDSMAN, BASE_DAMAGE_CANNON);
    }
    
    private int createAttackUnit(int id, int foodCost, int woodCost, int ironCost, String unitType, int armor, int baseDamage) {
        if (civilizationDAO.getWood(id) >= woodCost && civilizationDAO.getFood(id) >= foodCost && civilizationDAO.getIron(id) >= ironCost) {
            String sql = "INSERT INTO ATTACK_UNITS_STATS (CIVILIZATION_ID, UNIT_ID, TYPE_UNIT, ARMOR, BASE_DAMAGE) " +
                         "VALUES (?, ?, ?, ?, ?)";
            String getMaxUnitIdSql = "SELECT COALESCE(MAX(UNIT_ID), 0) + 1 AS new_unit_id FROM ATTACK_UNITS_STATS";
    
            try (Connection connection = AppData.getInstance().getConnection();
                 PreparedStatement getMaxUnitIdStmt = connection.prepareStatement(getMaxUnitIdSql);
                 PreparedStatement insertStmt = connection.prepareStatement(sql)) {
    
                // Obtener el nuevo UNIT_ID
                ResultSet resultSet = getMaxUnitIdStmt.executeQuery();
                int newUnitId = 1;
                if (resultSet.next()) {
                    newUnitId = resultSet.getInt("new_unit_id");
                }
    
                // Configurar los parámetros de la consulta de inserción
                insertStmt.setInt(1, id);
                insertStmt.setInt(2, newUnitId);
                insertStmt.setString(3, unitType);
                insertStmt.setInt(4, armor); // Suponiendo que ARMOR y BASE_DAMAGE son iguales a baseDamage
                insertStmt.setInt(5, baseDamage);
    
                // Ejecutar la inserción
                insertStmt.executeUpdate();
    
                // Restar los costos de los recursos
                civilizationDAO.updateResources(
                        civilizationDAO.getFood(id) - foodCost,
                        civilizationDAO.getWood(id) - woodCost,
                        civilizationDAO.getIron(id) - ironCost,
                        civilizationDAO.getMana(id),
                        id
                );
    
                return 1; // Indica que la actualización fue exitosa
            } catch (SQLException e) {
                e.printStackTrace();
                return 0; // Indica que hubo un error durante la actualización
            }
        }
    
        // Indicar que no hay suficientes recursos
        System.out.println("No hay suficientes recursos para construir la unidad " + unitType);
        return -1;
    }

    public int setArrowTower(int id) {
        return createDefenseUnit(id, FOOD_COST_ARROWTOWER, WOOD_COST_ARROWTOWER, IRON_COST_ARROWTOWER, "ArrowTower", ARMOR_ARROWTOWER, BASE_DAMAGE_ARROWTOWER);
    }
    
    public int setCatapult(int id) {
        return createDefenseUnit(id, FOOD_COST_CATAPULT, WOOD_COST_CATAPULT, IRON_COST_CATAPULT, "Catapult", ARMOR_CATAPULT, BASE_DAMAGE_CATAPULT);
    }
    
    public int setRocketLauncherTower(int id) {
        return createDefenseUnit(id, FOOD_COST_ROCKETLAUNCHERTOWER, WOOD_COST_ROCKETLAUNCHERTOWER, IRON_COST_ROCKETLAUNCHERTOWER, "RocketLauncherTower", ARMOR_ROCKETLAUNCHERTOWER, BASE_DAMAGE_ROCKETLAUNCHERTOWER);
    }
    
    
    private int createDefenseUnit(int id, int foodCost, int woodCost, int ironCost, String unitType, int armor, int baseDamage) {
        if (civilizationDAO.getWood(id) >= woodCost && civilizationDAO.getFood(id) >= foodCost && civilizationDAO.getIron(id) >= ironCost) {
            String sql = "INSERT INTO DEFENSE_UNITS_STATS (CIVILIZATION_ID, UNIT_ID, TYPE_UNIT, ARMOR, BASE_DAMAGE) " +
                         "VALUES (?, ?, ?, ?, ?)";
            String getMaxUnitIdSql = "SELECT COALESCE(MAX(UNIT_ID), 0) + 1 AS new_unit_id FROM DEFENSE_UNITS_STATS";
    
            try (Connection connection = AppData.getInstance().getConnection();
                 PreparedStatement getMaxUnitIdStmt = connection.prepareStatement(getMaxUnitIdSql);
                 PreparedStatement insertStmt = connection.prepareStatement(sql)) {
    
                // Obtener el nuevo UNIT_ID
                ResultSet resultSet = getMaxUnitIdStmt.executeQuery();
                int newUnitId = 1;
                if (resultSet.next()) {
                    newUnitId = resultSet.getInt("new_unit_id");
                }
    
                // Configurar los parámetros de la consulta de inserción
                insertStmt.setInt(1, id);
                insertStmt.setInt(2, newUnitId);
                insertStmt.setString(3, unitType);
                insertStmt.setInt(4, armor);
                insertStmt.setInt(5, baseDamage);
    
                // Ejecutar la inserción
                insertStmt.executeUpdate();
    
                // Restar los costos de los recursos
                civilizationDAO.updateResources(
                        civilizationDAO.getFood(id) - foodCost,
                        civilizationDAO.getWood(id) - woodCost,
                        civilizationDAO.getIron(id) - ironCost,
                        civilizationDAO.getMana(id),
                        id
                );
    
                return 1; // Indica que la actualización fue exitosa
            } catch (SQLException e) {
                e.printStackTrace();
                return 0; // Indica que hubo un error durante la actualización
            }
        }
    
        // Indicar que no hay suficientes recursos
        System.out.println("No hay suficientes recursos para construir la unidad " + unitType);
        return -1;
    }
    
    public int setMagician(int id) {
        return createMagicUnit(id, FOOD_COST_MAGICIAN, WOOD_COST_MAGICIAN, IRON_COST_MAGICIAN, MANA_COST_MAGICIAN, "Magician", 0, BASE_DAMAGE_MAGICIAN);
    }
    
    public int setPriest(int id) {
        // Check if the civilization has an available church
        if (civilizationDAO.getChurchCount(id) >= 1) {
            return createMagicUnit(id, FOOD_COST_PRIEST, WOOD_COST_PRIEST, IRON_COST_PRIEST, MANA_COST_PRIEST, "Priest", 0, 0);
        } else {
            System.out.println("No tienes suficientes iglesias para crear un Priest.");
            return -1;
        }
    }
    
    private int createMagicUnit(int id, int foodCost, int woodCost, int ironCost, int manaCost, String unitType, int armor, int baseDamage) {
        if (civilizationDAO.getWood(id) >= woodCost && civilizationDAO.getFood(id) >= foodCost
                && civilizationDAO.getIron(id) >= ironCost && civilizationDAO.getMana(id) >= manaCost) {
            String sql = "INSERT INTO SPECIAL_UNITS_STATS (CIVILIZATION_ID, UNIT_ID, TYPE_UNIT, ARMOR, BASE_DAMAGE) " +
                         "VALUES (?, ?, ?, ?, ?)";
            String getMaxUnitIdSql = "SELECT COALESCE(MAX(UNIT_ID), 0) + 1 AS new_unit_id FROM SPECIAL_UNITS_STATS";
    
            try (Connection connection = AppData.getInstance().getConnection();
                 PreparedStatement getMaxUnitIdStmt = connection.prepareStatement(getMaxUnitIdSql);
                 PreparedStatement insertStmt = connection.prepareStatement(sql)) {
    
                // Obtener el nuevo UNIT_ID
                ResultSet resultSet = getMaxUnitIdStmt.executeQuery();
                int newUnitId = 1;
                if (resultSet.next()) {
                    newUnitId = resultSet.getInt("new_unit_id");
                }
    
                // Configurar los parámetros de la consulta de inserción
                insertStmt.setInt(1, id);
                insertStmt.setInt(2, newUnitId);
                insertStmt.setString(3, unitType);
                insertStmt.setInt(4, armor);
                insertStmt.setInt(5, baseDamage);
    
                // Ejecutar la inserción
                insertStmt.executeUpdate();
    
                // Restar los costos de los recursos
                civilizationDAO.updateResources(
                        civilizationDAO.getFood(id) - foodCost,
                        civilizationDAO.getWood(id) - woodCost,
                        civilizationDAO.getIron(id) - ironCost,
                        civilizationDAO.getMana(id) - manaCost,
                        id
                );
    
                return 1; // Indica que la actualización fue exitosa
            } catch (SQLException e) {
                e.printStackTrace();
                return 0; // Indica que hubo un error durante la actualización
            }
        }
    
        // Indicar que no hay suficientes recursos
        System.out.println("No hay suficientes recursos para construir la unidad " + unitType);
        return -1;
    }
    


    public List<AttackUnit> getAttackUnits(int id) {
        List<AttackUnit> attackUnits = new ArrayList<>();

        String sql = "SELECT * FROM ATTACK_UNITS_STATS";

        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Extract data from result set
                int civilization_id = resultSet.getInt("CIVILIZATION_ID");
                int unit_id = resultSet.getInt("UNIT_ID");

                int armor = resultSet.getInt("ARMOR");
                int baseDamage = resultSet.getInt("BASE_DAMAGE");
                // Create AttackUnit instance
                AttackUnit attackUnit = new AttackUnit(civilization_id,unit_id,armor, baseDamage);
                // Add AttackUnit to the list
                attackUnits.add(attackUnit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attackUnits;
    }
    public List<DefenseUnit> getDefenseUnits(int id) {
        List<DefenseUnit> defenseUnits = new ArrayList<>();

        String sql = "SELECT * FROM DEFENSE_UNITS_STATS";

        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Extract data from result set
                int civilization_id = resultSet.getInt("CIVILIZATION_ID");
                int unit_id = resultSet.getInt("UNIT_ID");

                int armor = resultSet.getInt("ARMOR");
                int baseDamage = resultSet.getInt("BASE_DAMAGE");
                // Create AttackUnit instance
                DefenseUnit defenseUnit = new DefenseUnit(civilization_id,unit_id,armor, baseDamage);
                // Add AttackUnit to the list
                defenseUnits.add(defenseUnit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return defenseUnits;
    }
    public List<SpecialUnit> getSpecialUnits(int id) {
        List<SpecialUnit> specialUnits = new ArrayList<>();

        String sql = "SELECT * FROM SPECIAL_UNITS_STATS";

        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Extract data from result set
                int civilization_id = resultSet.getInt("CIVILIZATION_ID");
                int unit_id = resultSet.getInt("UNIT_ID");

                int armor = resultSet.getInt("ARMOR");
                int baseDamage = resultSet.getInt("BASE_DAMAGE");
                // Create AttackUnit instance
                SpecialUnit specialunit = new SpecialUnit(civilization_id,unit_id,armor, baseDamage);
                // Add AttackUnit to the list
                specialUnits.add(specialunit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return specialUnits;
    }

    //ELIMINAR
    public void deleteAttackUnit(int unitId) {
        String sql = "DELETE FROM ATTACK_UNITS_STATS WHERE UNIT_ID = ?";
    
        try (Connection conn = AppData.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, unitId);
    
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteDefenseUnit(int unitId) {
        String sql = "DELETE FROM DEFENSE_UNITS_STATS WHERE UNIT_ID = ?";
    
        try (Connection conn = AppData.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, unitId);
    
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteSpecialUnit(int unitId) {
        String sql = "DELETE FROM SPECIAL_UNITS_STATS WHERE UNIT_ID = ?";
    
        try (Connection conn = AppData.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, unitId);
    
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}


    

