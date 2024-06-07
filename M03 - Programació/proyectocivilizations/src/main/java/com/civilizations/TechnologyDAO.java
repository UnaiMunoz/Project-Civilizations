package com.civilizations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TechnologyDAO implements Variables {

    private static final int INITIAL_FOOD_COST = 100;
    private static final int INITIAL_WOOD_COST = 200;
    private static final int INITIAL_IRON_COST = 2000;

    private CivilizationDAO civilizationDAO = new CivilizationDAO();

    // Método para mejorar la tecnología de defensa
    public int upgradeDefenseTech(int civilizationId) {
        int currentLevel = getCurrentDefenseLevel(civilizationId);
        int foodCost = (int) (INITIAL_FOOD_COST * Math.pow(1.1, currentLevel));
        int woodCost = (int) (INITIAL_WOOD_COST * Math.pow(1.15, currentLevel));
        int ironCost = (int) (INITIAL_IRON_COST * Math.pow(1.2, currentLevel));

        if (civilizationDAO.getWood(civilizationId) >= woodCost && civilizationDAO.getFood(civilizationId) >= foodCost && civilizationDAO.getIron(civilizationId) >= ironCost) {
            String sql = "UPDATE TECHNOLOGIES SET DEFENSE_LEVEL = DEFENSE_LEVEL + 1 WHERE CIVILIZATION_ID = ?";

            try (Connection connection = AppData.getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                // Actualizar el nivel de defensa
                statement.setInt(1, civilizationId);
                statement.executeUpdate();

                // Restar los costos de los recursos
                civilizationDAO.updateResources(
                        civilizationDAO.getFood(civilizationId) - foodCost,
                        civilizationDAO.getWood(civilizationId) - woodCost,
                        civilizationDAO.getIron(civilizationId) - ironCost,
                        civilizationDAO.getMana(civilizationId),
                        civilizationId
                );

                return 1; // Indica que la actualización fue exitosa
            } catch (SQLException e) {
                e.printStackTrace();
                return 0; // Indica que hubo un error durante la actualización
            }
        }

        // Indicar que no hay suficientes recursos
        System.out.println("No hay suficientes recursos para mejorar la tecnología de defensa");
        return -1;
    }

    // Método para mejorar la tecnología de ataque
    public int upgradeAttackTech(int civilizationId) {
        int currentLevel = getCurrentAttackLevel(civilizationId);
        int foodCost = (int) (INITIAL_FOOD_COST * Math.pow(1.1, currentLevel));
        int woodCost = (int) (INITIAL_WOOD_COST * Math.pow(1.15, currentLevel));
        int ironCost = (int) (INITIAL_IRON_COST * Math.pow(1.2, currentLevel));

        if (civilizationDAO.getWood(civilizationId) >= woodCost && civilizationDAO.getFood(civilizationId) >= foodCost && civilizationDAO.getIron(civilizationId) >= ironCost) {
            String sql = "UPDATE TECHNOLOGIES SET ATTACK_LEVEL = ATTACK_LEVEL + 1 WHERE CIVILIZATION_ID = ?";

            try (Connection connection = AppData.getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                // Actualizar el nivel de ataque
                statement.setInt(1, civilizationId);
                statement.executeUpdate();

                // Restar los costos de los recursos
                civilizationDAO.updateResources(
                        civilizationDAO.getFood(civilizationId) - foodCost,
                        civilizationDAO.getWood(civilizationId) - woodCost,
                        civilizationDAO.getIron(civilizationId) - ironCost,
                        civilizationDAO.getMana(civilizationId),
                        civilizationId
                );

                return 1; // Indica que la actualización fue exitosa
            } catch (SQLException e) {
                e.printStackTrace();
                return 0; // Indica que hubo un error durante la actualización
            }
        }

        // Indicar que no hay suficientes recursos
        System.out.println("No hay suficientes recursos para mejorar la tecnología de ataque");
        return -1;
    }

    // Método para obtener el nivel actual de la tecnología de defensa
    private int getCurrentDefenseLevel(int civilizationId) {
        String sql = "SELECT DEFENSE_LEVEL FROM TECHNOLOGIES WHERE CIVILIZATION_ID = ?";
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, civilizationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("DEFENSE_LEVEL");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Nivel predeterminado si no se encuentra el registro
    }

    // Método para obtener el nivel actual de la tecnología de ataque
    private int getCurrentAttackLevel(int civilizationId) {
        String sql = "SELECT ATTACK_LEVEL FROM TECHNOLOGIES WHERE CIVILIZATION_ID = ?";
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, civilizationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("ATTACK_LEVEL");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Nivel predeterminado si no se encuentra el registro
    }
}
