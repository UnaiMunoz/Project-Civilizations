package com.civilizations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TechnologyDAO {

    public void upgradeAttackTechnology(int civilizationId) throws SQLException {
        upgradeTechnology(civilizationId, "TECHNOLOGY_ATTACK_LEVEL");
    }

    public void upgradeDefenseTechnology(int civilizationId) throws SQLException {
        int foodCost = Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_FOOD_COST;
        int woodCost = Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST;
        int ironCost = Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST;
        upgradeTechnology(civilizationId, "TECHNOLOGY_DEFENSE_LEVEL", foodCost, woodCost, ironCost);
    }

    private void upgradeTechnology(int civilizationId, String techType) throws SQLException {
        // Método de mejora de tecnología sin verificación de recursos
        String upgradeSQL = "UPDATE civilization_stats SET " + techType + " = " + techType + " + 1 WHERE civilization_id = ?";
        
        try (Connection conn = AppData.getInstance().getConnection();
             PreparedStatement upgradeStmt = conn.prepareStatement(upgradeSQL)) {
             
            // Perform upgrade
            upgradeStmt.setInt(1, civilizationId);
            upgradeStmt.executeUpdate();
            conn.commit(); // Confirm changes
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void upgradeTechnology(int civilizationId, String techType, int foodCost, int woodCost, int ironCost) throws SQLException {
        String checkResourcesSQL = "SELECT food_amount, wood_amount, iron_amount FROM civilization_stats WHERE civilization_id = ?";
        String updateResourcesSQL = "UPDATE civilization_stats SET food_amount = food_amount - ?, wood_amount = wood_amount - ?, iron_amount = iron_amount - ? WHERE civilization_id = ?";
        String upgradeSQL = "UPDATE civilization_stats SET " + techType + " = " + techType + " + 1 WHERE civilization_id = ?";

        try (Connection conn = AppData.getInstance().getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkResourcesSQL);
             PreparedStatement updateResourcesStmt = conn.prepareStatement(updateResourcesSQL);
             PreparedStatement upgradeStmt = conn.prepareStatement(upgradeSQL)) {

            // Check resources
            checkStmt.setInt(1, civilizationId);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    int food = rs.getInt("food_amount");
                    int wood_amount = rs.getInt("wood_amount");
                    int iron_amount = rs.getInt("iron_amount");

                    if (food < foodCost || wood_amount < woodCost || iron_amount < ironCost) {
                        throw new SQLException("La civilización no tiene suficientes recursos para la actualización de tecnología.");
                    }
                } else {
                    throw new SQLException("Civilization not found.");
                }
            }

            // Deduct resources
            updateResourcesStmt.setInt(1, foodCost);
            updateResourcesStmt.setInt(2, woodCost);
            updateResourcesStmt.setInt(3, ironCost);
            updateResourcesStmt.setInt(4, civilizationId);
            updateResourcesStmt.executeUpdate();

            // Perform upgrade
            upgradeStmt.setInt(1, civilizationId);
            upgradeStmt.executeUpdate();

            conn.commit(); // Confirm changes
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
