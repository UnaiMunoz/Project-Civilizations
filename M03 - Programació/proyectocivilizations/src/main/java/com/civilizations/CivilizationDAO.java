package com.civilizations;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class CivilizationDAO implements Variables {

    // Método para agregar un nuevo usuario y devolver el objeto Civilization con los valores de la base de datos
    public static Civilization addUser(String name) {
        String insertSql = "INSERT INTO civilization_stats (civilization_id, name, wood_amount, iron_amount, food_amount, mana_amount, magictower_counter, church_counter, farm_counter, smithy_counter, carpentry_counter, technology_defense_level, technology_attack_level, battles_counter) " +
                           "VALUES (civilization_seq.nextval, ?, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)";
        String selectSql = "SELECT civilization_id, name, wood_amount, iron_amount, food_amount, mana_amount, magictower_counter, church_counter, farm_counter, smithy_counter, carpentry_counter, technology_defense_level, technology_attack_level, battles_counter FROM civilization_stats WHERE civilization_id = ?";

        try (Connection conn = AppData.getInstance().getConnection();
             PreparedStatement insertStmt = conn.prepareStatement(insertSql, new String[]{"civilization_id"})) {
             
            insertStmt.setString(1, name);
            int affectedRows = insertStmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = insertStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);

                        try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
                            selectStmt.setInt(1, generatedId);
                            try (ResultSet rs = selectStmt.executeQuery()) {
                                if (rs.next()) {
                                    return new Civilization(
                                        generatedId,
                                        rs.getString("name"),
                                        rs.getInt("technology_defense_level"),
                                        rs.getInt("technology_attack_level"),
                                        rs.getInt("wood_amount"),
                                        rs.getInt("iron_amount"),
                                        rs.getInt("food_amount"),
                                        rs.getInt("mana_amount"),
                                        rs.getInt("magictower_counter"),
                                        rs.getInt("church_counter"),
                                        rs.getInt("farm_counter"),
                                        rs.getInt("smithy_counter"),
                                        rs.getInt("carpentry_counter"),
                                        rs.getInt("battles_counter")
                                    );
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getCivilizationIdByName(String name) {
        String sql = "SELECT civilization_id FROM civilization_stats WHERE name = ?";
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("civilization_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Indica que no se encontró
    }

    public static void viewGame() {
        String sql = "SELECT * FROM civilization_stats";
        AppData db = AppData.getInstance();
        List<Map<String, Object>> results = db.query(sql);

        for (Map<String, Object> row : results) {
            System.out.println(row);
        }
    }

    public static Civilization CurrentGame(int id) {
        String sql = "SELECT * FROM civilization_stats WHERE civilization_id = " + id;
        AppData db = AppData.getInstance();
        List<Map<String, Object>> results = db.query(sql);

        if (!results.isEmpty()) {
            Map<String, Object> row = results.get(0);
            return new Civilization(
                id,
                (String) row.get("name"),
                (int) row.get("technology_defense_level"),
                (int) row.get("technology_attack_level"),
                (int) row.get("wood_amount"),
                (int) row.get("iron_amount"),
                (int) row.get("food_amount"),
                (int) row.get("mana_amount"),
                (int) row.get("magictower_counter"),
                (int) row.get("church_counter"),
                (int) row.get("farm_counter"),
                (int) row.get("smithy_counter"),
                (int) row.get("carpentry_counter"),
                (int) row.get("battles_counter")
            );
        } else {
            System.out.println("No se encontró la civilización con ID: " + id);
        }
        return null;
    }

    public void updateResources(int food, int wood, int iron, int mana, int civilizationId) {
        // Asegúrate de que los valores no sean negativos antes de actualizar la base de datos
        if (food < 0) food = 0;
        if (wood < 0) wood = 0;
        if (iron < 0) iron = 0;
        if (mana < 0) mana = 0;
    
        String sql = "UPDATE civilization_stats SET food_amount = ?, wood_amount = ?, iron_amount = ?, mana_amount = ? WHERE civilization_id = ?";
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, food);
            statement.setInt(2, wood);
            statement.setInt(3, iron);
            statement.setInt(4, mana);
            statement.setInt(5, civilizationId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateAmounts(int civilizationId) {
        BuildingsDAO buildingsDAO = new BuildingsDAO();
        int foodAmount = getFood(civilizationId);
        int woodAmount = getWood(civilizationId);
        int ironAmount = getIron(civilizationId);
        int manaAmount = getMana(civilizationId);
        int magictowerCounter = buildingsDAO.getMagictowerCounter(civilizationId);

        // SI MAGIC TOWER MAYOR A 1,COMIENZA A GENERAR
        if (magictowerCounter >= 1) {
            manaAmount += CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER;
        }

        updateResources(foodAmount + CIVILIZATION_FOOD_GENERATED, 
                        woodAmount + CIVILIZATION_WOOD_GENERATED, 
                        ironAmount + CIVILIZATION_IRON_GENERATED, 
                        manaAmount, 
                        civilizationId);
    }

    public int getFood(int id) {
        String sql = "SELECT food_amount FROM civilization_stats WHERE civilization_id = ?";
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("food_amount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getWood(int id) {
        String sql = "SELECT wood_amount FROM civilization_stats WHERE civilization_id = ?";
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("wood_amount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getIron(int id) {
        String sql = "SELECT iron_amount FROM civilization_stats WHERE civilization_id = ?";
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("iron_amount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getMana(int id) {
        String sql = "SELECT mana_amount FROM civilization_stats WHERE civilization_id = ?";
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("mana_amount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getChurchCount(int civilizationId) {
        String sql = "SELECT church_counter FROM civilization_stats WHERE civilization_id = ?";
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, civilizationId);
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
    

}