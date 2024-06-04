package com.civilizations;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    
}
