package com.civilizations;

    public class CivilizationDAO {
        public static Civilization addUser(String name) {
            String sql = "INSERT INTO civilization_stats (civilization_id, name, wood_amount, iron_amount, food_amount, mana_amount, magictower_counter, church_counter, farm_counter, smithy_counter, carpentry_counter, technology_defense_level, technology_attack_level, battles_counter) " +
                        "VALUES (civilization_seq.nextval, '" + name + "', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)";
            AppData db = AppData.getInstance();
            int generatedId = db.insertAndGetId(sql);
            
            if (generatedId != -1) {
                Civilization myCivilization = new Civilization(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                return myCivilization;
            } else {
                return null;
            }
        }

    public static void viewGame(){
        String sql = "SELECT * from civilization_stats";
        AppData db = AppData.getInstance();
        db.query(sql);
    }

    public static void addWood(int Wood){
        String sql = "UPDATE civilization_stats " +
            "SET wood_amount = wood_amount + " + Wood;         
            AppData db = AppData.getInstance();
            db.update(sql);
    }
    public static void addIron(int Iron){
        String sql = "UPDATE civilization_stats " +
            "SET iron_amount = iron_amount + " + Iron;         
            AppData db = AppData.getInstance();
            db.update(sql);
    }
    public static void addFood(int food){
        String sql = "UPDATE civilization_stats " +
            "SET food_amount = food_amount + " + food;         
            AppData db = AppData.getInstance();
            db.update(sql);
    }
    public static void addMana(int Mana){
        String sql = "UPDATE civilization_stats " +
            "SET mana_amount = mana_amount + " + Mana;         
            AppData db = AppData.getInstance();
            db.update(sql);
    }

}
