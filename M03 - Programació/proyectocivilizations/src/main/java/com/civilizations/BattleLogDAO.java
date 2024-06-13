package com.civilizations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BattleLogDAO {
    private final AppData appData;

    public BattleLogDAO() {
        this.appData = AppData.getInstance();
    }

    public void saveLogEntry(int civilizationId, int numBattle, int numLine, String logEntry) {
        String sql = "INSERT INTO battle_log (civilization_id, num_battle, num_line, log_entry) VALUES (?, ?, ?, ?)";
        try {
            Connection connection = appData.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, civilizationId);
            pstmt.setInt(2, numBattle);
            pstmt.setInt(3, numLine);
            pstmt.setString(4, logEntry);
            pstmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Error saving log entry: " + e.getMessage());
        }
    }

    public void deleteLogbyID(int civilizationId) {
        String sql = "DELETE FROM battle_log WHERE civilization_id = ?";
        try {
            Connection connection = appData.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, civilizationId);
            pstmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Unable to delete log from ID " + civilizationId);
        }
    }
}
