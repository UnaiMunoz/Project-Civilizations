package com.civilizations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class EnemyDAO {
    public void insertEnemyTroop(int civilizationId, int unitId, String typeUnit, int armor, int baseDamage, int experience) {
        AppData db = AppData.getInstance();
        String sql = "INSERT INTO ENEMY_THREAD (CIVILIZATION_ID, UNIT_ID, TYPE_UNIT, ARMOR, BASE_DAMAGE, EXPERIENCE) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = db.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, civilizationId);
            pstmt.setInt(2, unitId);
            pstmt.setString(3, typeUnit);
            pstmt.setInt(4, armor);
            pstmt.setInt(5, baseDamage);
            pstmt.setInt(6, experience);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static class UnitCounts {
        public int swordsmanCount;
        public int crossbowCount;
        public int cannonCount;
        public int spearmanCount;
    }


    public UnitCounts viewIncomingThreat(int id) {
        String sql = "SELECT TYPE_UNIT, COUNT(*) AS count FROM ENEMY_THREAD WHERE CIVILIZATION_ID = ? GROUP BY TYPE_UNIT";
        UnitCounts counts = new UnitCounts();
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String typeUnit = rs.getString("TYPE_UNIT");
                    int count = rs.getInt("count");
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
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counts;
    }


    public List<AttackUnit> getEnemyArmy(int id) {
        List<AttackUnit> enemyArmy = new ArrayList<>();

        String sql = "SELECT * FROM ENEMY_THREAD";

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
                AttackUnit enemyTroop = new AttackUnit(civilization_id,unit_id,armor, baseDamage);
                // Add AttackUnit to the list
                enemyArmy.add(enemyTroop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enemyArmy;
    }
    //DELETES
    public void deleteEnemyTroop(int unitId) {
        String sql = "DELETE FROM ENEMY_THREAD WHERE UNIT_ID = ?";
    
        try (Connection conn = AppData.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, unitId);
    
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
