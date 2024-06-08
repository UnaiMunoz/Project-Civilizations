package com.civilizations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BattleLogDAO {
    private int battleCount = 0;
    private int logEntryNumber = 0;

    public void logAttack(int civilizationId, int damageDealt, MilitaryUnit unit) {
        try (Connection conn = AppData.getInstance().getConnection()) {
            int numBattle = battleExists(conn, civilizationId);
            if (numBattle == -1) {
                System.out.println("Battle entry does not exist in battle_stats table.");
                return; // Exit the method
            }

            // Proceed with the insertion using numBattle as the battle number
            String sql = "INSERT INTO BATTLE_LOG (civilization_id, num_battle, num_line, log_entry, battle_id) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, civilizationId);
                ps.setInt(2, numBattle);
                ps.setInt(3, logEntryNumber++);
                ps.setString(4, "Unit " + unit.getClass().getSimpleName() + " dealt " + damageDealt + " damage.");
                ps.setInt(5, battleCount); // Insert battleCount as the BattleID
                ps.executeUpdate();
                System.out.println("Inserted battle log for civilization " + civilizationId + " with battle number " + numBattle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getBattleCount() {
        return battleCount;
    }

    public void incrementBattleCount() {
        battleCount++;
    }

    private int battleExists(Connection conn, int civilizationId) throws SQLException {
        // Check if the battle entry exists in the battle_stats table
        String sql = "SELECT num_battle FROM battle_stats WHERE civilization_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, civilizationId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int numBattle = rs.getInt("num_battle");
                    System.out.println("Found battle entry for civilization " + civilizationId + " with num_battle: " + numBattle);
                    return numBattle; // Return the num_battle value if a matching entry is found
                } else {
                    return -1; // Return -1 if no matching entry is found
                }
            }
        }
    }
}
