package com.civilizations;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CivilizationDAO {

    public static void addUser(String name) {
        String sql = "INSERT INTO civilization_stats (civilization_id, name, wood_amount, iron_amount, food_amount, mana_amount, magictower_counter, church_counter, farm_counter, smithy_counter, carpentry_counter, technology_defense_level, technology_attack_level, battles_counter) " +
                     "VALUES (civilization_seq.nextval, '" + name + "', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)";
        AppData db = AppData.getInstance();
        db.update(sql);
    }

    public int getWood(int id) {
        String sql = "SELECT wood_amount FROM civilization_stats WHERE civilization_id = 1";
        System.out.println("Query: " + sql); // Imprime la consulta SQL
        AppData db = AppData.getInstance();
        List<Map<String, Object>> results = db.query(sql);
        System.out.println(results);

    
        // Añadir el código para obtener el valor de "WOOD_AMOUNT"
        if (!results.isEmpty()) {
            BigDecimal woodAmount = (BigDecimal) results.get(0).get("WOOD_AMOUNT");
            int woodAmountInt = woodAmount.intValue(); // Convertir BigDecimal a int
            System.out.println("Wood amount: " + woodAmountInt);
            return woodAmountInt;
        } else {
            System.out.println("No se encontraron resultados para el civilization_id: " + id);
            return 0; // O cualquier otro valor predeterminado que desees devolver en caso de que no haya resultados
        }
    }

    public void updateWood(int id) {
        String sql = "UPDATE civilization_stats SET wood_amount = 100 WHERE civilization_id = "+ id +"";
        AppData db = AppData.getInstance();
        db.update(sql);
        System.out.println(sql);

    }



}
    
    

    

    


    