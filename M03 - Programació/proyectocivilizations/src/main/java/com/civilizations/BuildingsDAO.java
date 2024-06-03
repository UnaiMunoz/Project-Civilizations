package com.civilizations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuildingsDAO implements Variables {

        CivilizationDAO civilizationDAO = new CivilizationDAO();

        public int setMagictowerCounter(int magictower, int woodAmount, int foodAmount, int ironAmount, int id) {
            // Asegurar que magictower no sea negativo
            if (magictower < 0) magictower = 0;
        
            // Costos
            int woodCost = WOOD_COST_MAGICTOWER;
            int foodCost = FOOD_COST_MAGICTOWER;
            int ironCost = IRON_COST_MAGICTOWER;
        
            // Obtener los recursos actuales de la base de datos
            woodAmount = civilizationDAO.getWood(id);
            foodAmount = civilizationDAO.getFood(id);
            ironAmount = civilizationDAO.getIron(id);
        
            // Verificar si hay suficientes recursos
            if (woodAmount >= woodCost && foodAmount >= foodCost && ironAmount >= ironCost) {
                String sql = "UPDATE civilization_stats SET magictower_counter = ?, wood_amount = wood_amount - ?, food_amount = food_amount - ?, iron_amount = iron_amount - ? WHERE civilization_id = ?";
                try (Connection connection = AppData.getInstance().getConnection();
                     PreparedStatement statement = connection.prepareStatement(sql)) {
                    // Configurar los parámetros de la consulta
                    statement.setInt(1, magictower);
                    statement.setInt(2, woodCost);
                    statement.setInt(3, foodCost);
                    statement.setInt(4, ironCost);
                    statement.setInt(5, id);
        
                    // Ejecutar la actualización
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        
            return 1;
        }

        public int setChurchCounter(int church, int manaAmount, int id) {

            return 1;
            
        }
        

        public int getMagictowerCounter(int id){
        String sql = "SELECT magictower_counter FROM civilization_stats WHERE civilization_id = ?";
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("magictower_counter");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int getChurchCounter(int id){
        String sql = "SELECT church_counter FROM civilization_stats WHERE civilization_id = ?";
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("church_counter");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int getFarmCounter(int id){
        String sql = "SELECT farm_counter FROM civilization_stats WHERE civilization_id = ?";
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("farm_counter");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int getSmithyCounter(int id){
        String sql = "SELECT smithy_counter FROM civilization_stats WHERE civilization_id = ?";
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("smithy_counter");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int getCarpentryCounter(int id){
        String sql = "SELECT carpentry_counter FROM civilization_stats WHERE civilization_id = ?";
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("carpentry_counter");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
