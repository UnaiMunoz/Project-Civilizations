package com.civilizations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public int viewIncomingThreat(int id) {
        String sql = "SELECT TYPE_UNIT, COUNT(*) AS count FROM ENEMY_THREAD WHERE CIVILIZATION_ID = ? GROUP BY TYPE_UNIT";
        int swordsmanCount = 0;
        int crossbowCount = 0;
        int cannonCount = 0;
        int spearmanCount = 0;    
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String typeUnit = rs.getString("TYPE_UNIT");
                    int count = rs.getInt("count");
                    switch (typeUnit) {
                        case "Swordsman":
                            swordsmanCount = count;
                            break;
                        case "Crossbow":
                            crossbowCount = count;
                            break;
                        case "Cannon":
                            cannonCount = count;
                            break;
                        case "Spearman":
                            spearmanCount = count;
                            break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
