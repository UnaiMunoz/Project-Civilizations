package com.civilizations;

public class CivilizationDAO {
    public static void addUser(String name) {
        String sql = "INSERT INTO civilization_stats (civilization_id, name, wood_amount, iron_amount, food_amount, mana_amount, magictower_counter, church_counter, farm_counter, smithy_counter, carpentry_counter, technology_defense_level, technology_attack_level, battles_counter) " +
                     "VALUES (civilization_seq.nextval, '" + name + "', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)";
        AppData db = AppData.getInstance();
        db.update(sql);
    }
}
    