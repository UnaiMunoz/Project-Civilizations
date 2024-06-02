package com.civilizations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuildingsDAO {
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
