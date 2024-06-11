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
    


    public ArrayList<MilitaryUnit> getAttackUnitsByCivilization(int civilizationId) {
        ArrayList<MilitaryUnit> attackUnits = new ArrayList<>();
    
        String sql = "SELECT * FROM ATTACK_UNITS_STATS WHERE CIVILIZATION_ID = ?";
    
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            // Set the parameter in the SQL query
            statement.setInt(1, civilizationId);
    
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // Extract data from result set
                    int civId = resultSet.getInt("CIVILIZATION_ID");
                    int uId = resultSet.getInt("UNIT_ID");
                    int armor = resultSet.getInt("ARMOR");
                    int baseDamage = resultSet.getInt("BASE_DAMAGE");
                    String unitType = resultSet.getString("TYPE_UNIT");
    
                    // Create unit instance based on unit type
                    MilitaryUnit unit = null;
                    switch (unitType.toUpperCase()) {
                        case "SWORDSMAN":
                            unit = new Swordsman(civId, uId, armor, baseDamage);
                            break;
                        case "SPEARMAN":
                            unit = new Spearman(civId, uId, armor, baseDamage);
                            break;
                        case "CROSSBOW":
                            unit = new Crossbow(civId, uId, armor, baseDamage);
                            break;
                        case "CANNON":
                            unit = new Cannon(civId, uId, armor, baseDamage);
                            break;
                        // Add cases for other unit types as needed
                        default:
                            // Handle unknown unit types
                            System.err.println("Unknown unit type: " + unitType);
                            break;
                    }
    
                    // Add unit to the list if it's not null
                    if (unit != null) {
                        attackUnits.add(unit);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return attackUnits;
    }
    
    
    
    public ArrayList<DefenseUnit> getDefenseUnits(int id) {
        ArrayList<DefenseUnit> defenseUnits = new ArrayList<>();

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
    public ArrayList<SpecialUnit> getSpecialUnits(int id) {
        ArrayList<SpecialUnit> specialUnits = new ArrayList<>();

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


    public List<Integer> getUnitIds(int civilizationId) {
        List<Integer> unitIds = new ArrayList<>();
        String sql = "SELECT UNIT_ID FROM ATTACK_UNITS_STATS WHERE CIVILIZATION_ID = ? " +
                     "UNION SELECT UNIT_ID FROM DEFENSE_UNITS_STATS WHERE CIVILIZATION_ID = ? " +
                     "UNION SELECT UNIT_ID FROM SPECIAL_UNITS_STATS WHERE CIVILIZATION_ID = ?";

        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, civilizationId);
            statement.setInt(2, civilizationId);
            statement.setInt(3, civilizationId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                unitIds.add(resultSet.getInt("UNIT_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return unitIds;
    }


    public static class UnitCounts {
        public int swordsmanCount;
        public int crossbowCount;
        public int cannonCount;
        public int spearmanCount;
        public int arrowTowerCount;
        public int catapultCount;
        public int rlTowerCount;
        public int magicianCount;
        public int priestCount;




    }
    //CONTAR:
    public UnitCounts CountArmy(int id) {
        String sql = "SELECT TYPE_UNIT, COUNT(*) AS unitCount FROM ATTACK_UNITS_STATS " +
                     "WHERE CIVILIZATION_ID = ? " +
                     "GROUP BY TYPE_UNIT " +
                     "UNION " +
                     "SELECT TYPE_UNIT, COUNT(*) AS unitCount FROM DEFENSE_UNITS_STATS " +
                     "WHERE CIVILIZATION_ID = ? " +
                     "GROUP BY TYPE_UNIT " +
                     "UNION " +
                     "SELECT TYPE_UNIT, COUNT(*) AS unitCount FROM SPECIAL_UNITS_STATS " +
                     "WHERE CIVILIZATION_ID = ? " +
                     "GROUP BY TYPE_UNIT";
        UnitCounts counts = new UnitCounts();
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setInt(2, id);
            statement.setInt(3, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String typeUnit = rs.getString("TYPE_UNIT");
                    int count = rs.getInt("unitCount");
                    switch (typeUnit) {
                        case "Swordsman":
                            counts.swordsmanCount = count;
                            break;
                        case "Crossbow":
                            counts.crossbowCount = count;
                            break;
                        case "Cannon":
                            counts.cannonCount = count;
                            break;
                        case "Spearman":
                            counts.spearmanCount = count;
                            break;
                        case "Arrow Tower":
                            counts.arrowTowerCount = count;
                            break;
                        case "Catapult":
                            counts.catapultCount = count;
                            break;
                        case "Rocket Launcher Tower":
                            counts.rlTowerCount = count;
                            break;
                        case "Magician":
                            counts.magicianCount = count;
                            break;
                        case "Priest":
                            counts.priestCount = count;
                            break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counts;
    }
    
    
    
    //OBTENER TROPAS:
    public ArrayList<MilitaryUnit> getArmy(int civilizationId) {
        ArrayList<MilitaryUnit> allUnits = new ArrayList<>();
    
        String sql = "SELECT CIVILIZATION_ID, UNIT_ID, TYPE_UNIT, ARMOR, BASE_DAMAGE FROM ATTACK_UNITS_STATS " +
                     "WHERE CIVILIZATION_ID = ? " +
                     "UNION " +
                     "SELECT CIVILIZATION_ID, UNIT_ID, TYPE_UNIT, ARMOR, BASE_DAMAGE FROM DEFENSE_UNITS_STATS " +
                     "WHERE CIVILIZATION_ID = ? " +
                     "UNION " +
                     "SELECT CIVILIZATION_ID, UNIT_ID, TYPE_UNIT, ARMOR, BASE_DAMAGE FROM SPECIAL_UNITS_STATS " +
                     "WHERE CIVILIZATION_ID = ?";
    
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
    
            statement.setInt(1, civilizationId);
            statement.setInt(2, civilizationId);
            statement.setInt(3, civilizationId);
    
            ResultSet resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                int civId = resultSet.getInt("CIVILIZATION_ID");
                int unitId = resultSet.getInt("UNIT_ID");
                String typeUnit = resultSet.getString("TYPE_UNIT");
                int armor = resultSet.getInt("ARMOR");
                int baseDamage = resultSet.getInt("BASE_DAMAGE");
    
                // Create appropriate unit instance based on the type
                MilitaryUnit unit;
                switch (typeUnit) {
                    case "Swordsman":
                    case "Spearman":
                    case "Crossbow":
                    case "Cannon":
                        unit = new AttackUnit(civId, unitId, armor, baseDamage);
                        break;
                    case "ArrowTower":
                    case "Catapult":
                    case "RocketLauncherTower":
                        unit = new DefenseUnit(civId, unitId, armor, baseDamage);
                        break;
                    default:
                        unit = new SpecialUnit(civId, unitId, armor, baseDamage);
                        break;
                }
    
                allUnits.add(unit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return allUnits;
    }
    private String getUnitNameByType(String type) {
    switch (type) {
        case "Swordsman":
            return "Swordsman";
        case "Spearman":
            return "Spearman";
        case "Crossbow":
            return "Crossbow";
        case "Cannon":
            return "Cannon";
        case "ArrowTower":
            return "Arrow Tower";
        case "Catapult":
            return "Catapult";
        case "RocketLauncherTower":
            return "Rocket Launcher Tower";
        case "Magician":
            return "Magician";
        case "Priest":
            return "Priest";
        default:
            return "Unknown";
    }
}

    
}


    

