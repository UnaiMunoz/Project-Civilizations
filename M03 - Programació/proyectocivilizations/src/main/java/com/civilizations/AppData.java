package com.civilizations;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppData {
    private static AppData instance;
    private Connection conn;

    private AppData() {
        // Conecta al crear la primera instancia
        connect();
    }

    // Singleton
    public static AppData getInstance() {
        if (instance == null) {
            instance = new AppData();
        }
        return instance;
    }

    private void connect() {
        // URL de conexión para Oracle
        String url = "jdbc:oracle:thin:@//localhost:1521/xe"; // Cambia esto según tu configuración de Oracle
        String user = "tu_usuario";
        String password = "tu_contraseña";
        try {
            // Cargar el controlador JDBC de Oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // Establecer la conexión
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false); // Desactiva el autocommit para control manual de transacciones
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void close() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Métodos para ejecutar actualizaciones, inserciones y consultas...

    public void update(String sql) {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            conn.commit(); // Confirma los cambios
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                conn.rollback(); // Revierte los cambios en caso de error
            } catch (SQLException ex) {
                System.out.println("Error al hacer rollback.");
                ex.printStackTrace();
            }
        }
    }

    public int insertAndGetId(String sql) {
        int generatedId = -1;
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            conn.commit();  // Asegura hacer commit de la transacción si el autocommit está desactivado
    
            try (ResultSet rs = stmt.executeQuery("SELECT last_insert_id()")) {
                if (rs.next()) {
                    generatedId = rs.getInt(1); // Recupera el último ID insertado
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                conn.rollback(); // Revierte la transacción en caso de error
            } catch (SQLException ex) {
                System.out.println("Error al hacer rollback.");
                ex.printStackTrace();
            }
        }
        return generatedId;
    }
    
    public List<Map<String, Object>> query(String sql) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnLabel(i), rs.getObject(i));
                }
                resultList.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultList;
    }
}
