package com.civilizations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    
    

}


    

