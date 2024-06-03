package com.civilizations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuildingsDAO implements Variables {

        CivilizationDAO civilizationDAO = new CivilizationDAO();

        public int setMagictowerCounter(int id) {
            System.out.println("setMagictowerCounter ejecutado para civilizationId: " + id);

            // Costos
            int woodCost = WOOD_COST_MAGICTOWER;
            int foodCost = FOOD_COST_MAGICTOWER;
            int ironCost = IRON_COST_MAGICTOWER;
        
            // Verificar si hay suficientes recursos
            if (civilizationDAO.getWood(id) >= woodCost && civilizationDAO.getFood(id) >= foodCost && civilizationDAO.getIron(id) >= ironCost) {
                System.out.println("Recursos suficientes para construir la torre mágica");
                String sql = "UPDATE civilization_stats SET magictower_counter = magictower_counter + 1, wood_amount = wood_amount - ?, food_amount = food_amount - ?, iron_amount = iron_amount - ? WHERE civilization_id = ?";
                try (Connection connection = AppData.getInstance().getConnection();
                     PreparedStatement statement = connection.prepareStatement(sql)) {
                    // Configurar los parámetros de la consulta
                    statement.setInt(1, woodCost);
                    statement.setInt(2, foodCost);
                    statement.setInt(3, ironCost);
                    statement.setInt(4, id);
        
                    // Ejecutar la actualización
                    statement.executeUpdate();
        
                    // Restar los costos de los recursos
                    civilizationDAO.updateResources(
                            civilizationDAO.getFood(id),
                            civilizationDAO.getWood(id),
                            civilizationDAO.getIron(id),
                            civilizationDAO.getMana(id),
                            id
                    );
        
                    return 1; // Indica que la actualización fue exitosa
                } catch (SQLException e) {
                    e.printStackTrace();
                    return 0; // Indica que hubo un error durante la actualización
                }
            }
        
            // Indicar que no hay suficientes recursos
            System.out.println("No hay suficientes recursos para construir la torre mágica");
            return -1;

        }
        
        public int setChurchCounter(int id) {
            System.out.println("setChurchCounter ejecutado para civilizationId: " + id);
        
            // Costos
            int woodCost = WOOD_COST_CHURCH;
            int foodCost = FOOD_COST_CHURCH;
            int ironCost = IRON_COST_CHURCH;
            int manaCost = MANA_COST_CHURCH;
        
            // Verificar si hay suficientes recursos
            if (civilizationDAO.getWood(id) >= woodCost && civilizationDAO.getFood(id) >= foodCost &&
                civilizationDAO.getIron(id) >= ironCost && civilizationDAO.getMana(id) >= manaCost) {
                System.out.println("Recursos suficientes para construir la iglesia");
                String sql = "UPDATE civilization_stats SET church_counter = church_counter + 1, wood_amount = wood_amount - ?, food_amount = food_amount - ?, iron_amount = iron_amount - ?, mana_amount = mana_amount - ? WHERE civilization_id = ?";
                try (Connection connection = AppData.getInstance().getConnection();
                     PreparedStatement statement = connection.prepareStatement(sql)) {
                    // Configurar los parámetros de la consulta
                    statement.setInt(1, woodCost);
                    statement.setInt(2, foodCost);
                    statement.setInt(3, ironCost);
                    statement.setInt(4, manaCost);
                    statement.setInt(5, id);
        
                    // Ejecutar la actualización
                    statement.executeUpdate();
        
                    // Restar los costos de los recursos
                    civilizationDAO.updateResources(
                            civilizationDAO.getFood(id),
                            civilizationDAO.getWood(id),
                            civilizationDAO.getIron(id),
                            civilizationDAO.getMana(id),
                            id
                    );
        
                    return 1; // Indica que la actualización fue exitosa
                } catch (SQLException e) {
                    e.printStackTrace();
                    return 0; // Indica que hubo un error durante la actualización
                }
            }
        
            // Indicar que no hay suficientes recursos
            System.out.println("No hay suficientes recursos para construir la iglesia");
            return -1;
        }
        
        
        public int setFarmCounter(int id) {
            System.out.println("setFarmCounter ejecutado para civilizationId: " + id);
        
            // Costos
            int woodCost = WOOD_COST_FARM;
            int foodCost = FOOD_COST_FARM;
            int ironCost = IRON_COST_FARM;
        
            // Verificar si hay suficientes recursos
            if (civilizationDAO.getWood(id) >= woodCost && civilizationDAO.getFood(id) >= foodCost && civilizationDAO.getIron(id) >= ironCost) {
                System.out.println("Recursos suficientes para construir la granja");
                String sql = "UPDATE civilization_stats SET farm_counter = farm_counter + 1, wood_amount = wood_amount - ?, food_amount = food_amount - ?, iron_amount = iron_amount - ? WHERE civilization_id = ?";
                try (Connection connection = AppData.getInstance().getConnection();
                     PreparedStatement statement = connection.prepareStatement(sql)) {
                    // Configurar los parámetros de la consulta
                    statement.setInt(1, woodCost);
                    statement.setInt(2, foodCost);
                    statement.setInt(3, ironCost);
                    statement.setInt(4, id);
        
                    // Ejecutar la actualización
                    statement.executeUpdate();
        
                    // Restar los costos de los recursos
                    civilizationDAO.updateResources(
                            civilizationDAO.getFood(id),
                            civilizationDAO.getWood(id),
                            civilizationDAO.getIron(id),
                            civilizationDAO.getMana(id), // Mantener la cantidad de mana actual
                            id
                    );
        
                    return 1; // Indica que la actualización fue exitosa
                } catch (SQLException e) {
                    e.printStackTrace();
                    return 0; // Indica que hubo un error durante la actualización
                }
            }
        
            // Indicar que no hay suficientes recursos
            System.out.println("No hay suficientes recursos para construir la granja");
            return -1;
        }
        
        public int setSmithyCounter(int id) {
            System.out.println("setSmithyCounter ejecutado para civilizationId: " + id);
        
            // Costos
            int woodCost = WOOD_COST_SMITHY;
            int foodCost = FOOD_COST_SMITHY;
            int ironCost = IRON_COST_SMITHY;
        
            // Verificar si hay suficientes recursos
            if (civilizationDAO.getWood(id) >= woodCost && civilizationDAO.getFood(id) >= foodCost && civilizationDAO.getIron(id) >= ironCost) {
                System.out.println("Recursos suficientes para construir la herrería");
                String sql = "UPDATE civilization_stats SET smithy_counter = smithy_counter + 1, wood_amount = wood_amount - ?, food_amount = food_amount - ?, iron_amount = iron_amount - ? WHERE civilization_id = ?";
                try (Connection connection = AppData.getInstance().getConnection();
                     PreparedStatement statement = connection.prepareStatement(sql)) {
                    // Configurar los parámetros de la consulta
                    statement.setInt(1, woodCost);
                    statement.setInt(2, foodCost);
                    statement.setInt(3, ironCost);
                    statement.setInt(4, id);
        
                    // Ejecutar la actualización
                    statement.executeUpdate();
        
                    // Restar los costos de los recursos
                    civilizationDAO.updateResources(
                            civilizationDAO.getFood(id),
                            civilizationDAO.getWood(id),
                            civilizationDAO.getIron(id),
                            civilizationDAO.getMana(id), // Mantener la cantidad de mana actual
                            id
                    );
        
                    return 1; // Indica que la actualización fue exitosa
                } catch (SQLException e) {
                    e.printStackTrace();
                    return 0; // Indica que hubo un error durante la actualización
                }
            }
        
            // Indicar que no hay suficientes recursos
            System.out.println("No hay suficientes recursos para construir la herrería");
            return -1;
        }
        
        public int setCarpentryCounter(int id) {
            System.out.println("setCarpentryCounter ejecutado para civilizationId: " + id);
        
            // Costos
            int woodCost = WOOD_COST_CARPENTRY;
            int foodCost = FOOD_COST_CARPENTRY;
            int ironCost = IRON_COST_CARPENTRY;
        
            // Verificar si hay suficientes recursos
            if (civilizationDAO.getWood(id) >= woodCost && civilizationDAO.getFood(id) >= foodCost && civilizationDAO.getIron(id) >= ironCost) {
                System.out.println("Recursos suficientes para construir la carpintería");
                String sql = "UPDATE civilization_stats SET carpentry_counter = carpentry_counter + 1, wood_amount = wood_amount - ?, food_amount = food_amount - ?, iron_amount = iron_amount - ? WHERE civilization_id = ?";
                try (Connection connection = AppData.getInstance().getConnection();
                     PreparedStatement statement = connection.prepareStatement(sql)) {
                    // Configurar los parámetros de la consulta
                    statement.setInt(1, woodCost);
                    statement.setInt(2, foodCost);
                    statement.setInt(3, ironCost);
                    statement.setInt(4, id);
        
                    // Ejecutar la actualización
                    statement.executeUpdate();
        
                    // Restar los costos de los recursos
                    civilizationDAO.updateResources(
                            civilizationDAO.getFood(id),
                            civilizationDAO.getWood(id),
                            civilizationDAO.getIron(id),
                            civilizationDAO.getMana(id), // Mantener la cantidad de mana actual
                            id
                    );
        
                    return 1; // Indica que la actualización fue exitosa
                } catch (SQLException e) {
                    e.printStackTrace();
                    return 0; // Indica que hubo un error durante la actualización
                }
            }
        
            // Indicar que no hay suficientes recursos
            System.out.println("No hay suficientes recursos para construir la carpintería");
            return -1;
        }
        

        public int getMagictowerCounter(int id){
            String sql = "SELECT magictower_counter FROM civilization_stats WHERE civilization_id = ?";
            try (Connection connection = AppData.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("magictower_counter");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }
        public int getChurchCounter(int id){
            String sql = "SELECT church_counter FROM civilization_stats WHERE civilization_id = ?";
            try (Connection connection = AppData.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
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
        public int getFarmCounter(int id){
            String sql = "SELECT farm_counter FROM civilization_stats WHERE civilization_id = ?";
            try (Connection connection = AppData.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("farm_counter");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }
        public int getSmithyCounter(int id){
            String sql = "SELECT smithy_counter FROM civilization_stats WHERE civilization_id = ?";
            try (Connection connection = AppData.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("smithy_counter");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }
        public int getCarpentryCounter(int id){
            String sql = "SELECT carpentry_counter FROM civilization_stats WHERE civilization_id = ?";
            try (Connection connection = AppData.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("carpentry_counter");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }
}
