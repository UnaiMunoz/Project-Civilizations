package com.civilizations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TechnologyDAO {

    public void upgradeAttackTechnology(int civilizationId) throws SQLException {
        upgradeTechnology(civilizationId, 
                Variables.UPGRADE_BASE_ATTACK_TECHNOLOGY_FOOD_COST,
                Variables.UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST,
                Variables.UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST,
                "attack");
    }

    public void upgradeDefenseTechnology(int civilizationId) throws SQLException {
        upgradeTechnology(civilizationId, 
                Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_FOOD_COST,
                Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST,
                Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST,
                "defense");
    }

    private void upgradeTechnology(int civilizationId, int foodCost, int woodCost, int ironCost, String technologyType) throws SQLException {
        Connection connection = null;
        try {
            connection = getConnection();
            if (hasEnoughResources(connection, civilizationId, foodCost, woodCost, ironCost)) {
                CivilizationDAO civilizationDAO = new CivilizationDAO();
                civilizationDAO.updateResources(-foodCost, -woodCost, -ironCost, 0, civilizationId);

                String sql = "";
                if (technologyType.equals("attack")) {
                    sql = "UPDATE civilization_stats SET technology_attack_level = technology_attack_level + 1 WHERE civilization_id = ?";
                } else if (technologyType.equals("defense")) {
                    sql = "UPDATE civilization_stats SET technology_defense_level = technology_defense_level + 1 WHERE civilization_id = ?";
                }

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, civilizationId);
                    statement.executeUpdate();
                }
            } else {
                throw new SQLException("La civilización no tiene suficientes recursos para la actualización de tecnología.");
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // Log the exception or handle as appropriate
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean hasEnoughResources(Connection connection, int civilizationId, int foodCost, int woodCost, int ironCost) throws SQLException {
        CivilizationDAO civilizationDAO = new CivilizationDAO();
        int currentFood = civilizationDAO.getFood(civilizationId);
        int currentWood = civilizationDAO.getWood(civilizationId);
        int currentIron = civilizationDAO.getIron(civilizationId);

        return currentFood >= foodCost && currentWood >= woodCost && currentIron >= ironCost;
    }

    private Connection getConnection() throws SQLException {
        return AppData.getInstance().getConnection();
    }
}
