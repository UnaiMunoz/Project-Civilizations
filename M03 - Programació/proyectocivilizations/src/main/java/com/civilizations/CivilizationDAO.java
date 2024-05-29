package com.civilizations;

public class CivilizationDAO {

    public void addUser(String name) {
        // Obtain the AppData instance
        AppData db = AppData.getInstance();

        // Check if the AppData instance is not null
        if (db != null) {
            // Construct the SQL statement
            String sql = "INSERT INTO CIVILIZATION_STATS (NAME, WOOD_AMOUNT, IRON_AMOUNT, FOOD_AMOUNT, MANA_AMOUNT, MAGICTOWER_COUNTER, CHURCH_COUNTER, FARM_COUNTER, SMITHY_COUNTER, CARPENTRY_COUNTER, TECHNOLOGY_DEFENSE_LEVEL, TECHNOLOGY_ATTACK_LEVEL, BATTLES_COUNTER) " +
                    "VALUES ('" + name + "', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)";

            // Call the update method of the AppData instance to execute the SQL statement
            db.update(sql);
        } else {
            // Print an error message if the AppData instance is null
            System.out.println("Failed to obtain AppData instance.");
        }
    }
}
