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


    public ArrayList<MilitaryUnit> getEnemyArmy(int civilizationId) {
        ArrayList<MilitaryUnit> enemyArmy = new ArrayList<>();
    
        String sql = "SELECT * FROM ENEMY_THREAD";
    
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
    
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // Extract data from result set
                    int civId = resultSet.getInt("CIVILIZATION_ID");
                    int uId = resultSet.getInt("UNIT_ID");
                    int armor = resultSet.getInt("ARMOR");
                    int baseDamage = resultSet.getInt("BASE_DAMAGE");
                    String unitType = resultSet.getString("TYPE_UNIT");
    
                    // Create unit instance based on unit type
                    MilitaryUnit unit = null;
                    switch (unitType.toUpperCase()) {
                        case "SWORDSMAN":
                            unit = new Swordsman(uId, civilizationId, armor, baseDamage, 0);
                            break;
                        case "SPEARMAN":
                            unit = new Spearman(uId, civilizationId, armor, baseDamage, 0);
                            break;
                        case "CROSSBOW":
                            unit = new Crossbow(uId, civilizationId, armor, baseDamage, 0);
                            break;
                        case "CANNON":
                            unit = new Cannon(uId, civilizationId, armor, baseDamage, 0);
                            break;
                        // Add cases for other unit types as needed
                        default:
                            // Handle unknown unit types
                            System.err.println("Unknown unit type: " + unitType);
                            break;
                    }
    
                    // Add unit to the list if it's not null
                    if (unit != null) {
                        enemyArmy.add(unit);
                    }
                }
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

    public void deleteEnemyArmy() {
        String sql = "DELETE FROM ENEMY_THREAD";
    
        try (Connection conn = AppData.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public List<Integer> getEnemyUnitIds(int civilizationId) {
        List<Integer> unitIds = new ArrayList<>();
        String sql = "SELECT UNIT_ID FROM ENEMY_THREAD WHERE CIVILIZATION_ID = ?"; 

        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, civilizationId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                unitIds.add(resultSet.getInt("UNIT_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return unitIds;
    }
}
