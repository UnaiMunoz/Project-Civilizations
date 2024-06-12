package com.civilizations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BattleStatsDAO {
    private final AppData appData;
    private final CivilizationDAO civilizationDAO;
    public BattleStatsDAO() {
        this.appData = AppData.getInstance();
        this.civilizationDAO = new CivilizationDAO();
    }

    public void saveBattleStats(int civilizationId, int numBattle, int woodAcquired, int ironAcquired) {
        boolean civilizationExists = civilizationDAO.doesCivilizationExist(civilizationId);
    
        if (civilizationExists) {
            String sql = "INSERT INTO battle_stats (civilization_id, num_battle, wood_acquired, iron_acquired) VALUES (?, ?, ?, ?)";
            try {
                Connection connection = appData.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, civilizationId);
                pstmt.setInt(2, numBattle);
                pstmt.setInt(3, woodAcquired);
                pstmt.setInt(4, ironAcquired);
                pstmt.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                System.out.println("Error saving battle stats: " + e.getMessage());
            }
        } else {
            System.out.println("Civilization does not exist.");
        }
    }
    public void updateBattleResources(int civilizationId, int numBattle, int woodAcquired, int ironAcquired) {
        boolean civilizationExists = civilizationDAO.doesCivilizationExist(civilizationId);
    
        if (civilizationExists) {
            String sql = "UPDATE battle_stats SET wood_acquired = ?, iron_acquired = ? WHERE civilization_id = ? AND num_battle = ?";
            try {
                Connection connection = appData.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, woodAcquired);
                pstmt.setInt(2, ironAcquired);
                pstmt.setInt(3, civilizationId);
                pstmt.setInt(4, numBattle);
                pstmt.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                System.out.println("Error updating battle stats: " + e.getMessage());
            }
        } else {
            System.out.println("Civilization does not exist.");
        }
    }
}    
