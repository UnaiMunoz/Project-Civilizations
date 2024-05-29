package com.civilizations;
import java.util.*;


public class PartidaDAO {

    public void guardarPartida(int civilizationId, String NAME, int woodAmount, int ironAmount, int foodAmount, int manaAmount, int magicTowerCounter, int churchCounter, int farmCounter, int smithyCounter, int carpentryCounter, int technologyDefenseLevel, int technologyAttackLevel, int battlesCounter) {
        String sql = "UPDATE civilization_stats " +
                     "SET NAME = '" + NAME + "', wood_amount = " + woodAmount + ", iron_amount = " + ironAmount + ", food_amount = " + foodAmount + ", mana_amount = " + manaAmount + ", magictower_counter = " + magicTowerCounter + ", church_counter = " + churchCounter + ", farm_counter = " + farmCounter + ", smithy_counter = " + smithyCounter + ", carpentry_counter = " + carpentryCounter + ", technology_defense_level = " + technologyDefenseLevel + ", technology_attack_level = " + technologyAttackLevel + ", battles_counter = " + battlesCounter +
                     " WHERE CIVILIZATION_ID = " + civilizationId;

        AppData db = AppData.getInstance();
        db.update(sql);
    }


     public List<Map<String, Object>> loadAllGames() {
        String query = "SELECT CIVILIZATION_ID, NAME FROM civilization_stats";
        AppData db = AppData.getInstance();
        return db.query(query);
    }

    public List<Map<String, Object>> loadState(int civilizationId) {
        String query = "SELECT * FROM civilization_stats WHERE CIVILIZATION_ID = " + civilizationId;
        AppData db = AppData.getInstance();
        return db.query(query);
    }

}