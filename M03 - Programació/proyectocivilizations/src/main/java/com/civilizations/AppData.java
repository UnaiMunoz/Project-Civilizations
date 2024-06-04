package com.civilizations;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class AppData {
    private static AppData instance;
    private Connection conn;

    private AppData() {
        // Conectar al crear la primera instancia
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
        String url = "jdbc:oracle:thin:@localhost:1521/xe";
        String user = "system";
        String password = "david"; 
        try {
            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false); // Disable autocommit to allow manual transaction control
            System.out.println("Database connection established.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                connect();
            }
        } catch (SQLException e) {
            System.out.println("Error checking database connection: " + e.getMessage());
        }
        return conn;
    }

    public void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }

    public void update(String sql) {
        try (Statement stmt = getConnection().createStatement()) {
            stmt.executeUpdate(sql);
            conn.commit(); // Confirm changes
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                getConnection().rollback(); // Rollback changes in case of error
            } catch (SQLException ex) {
                System.out.println("Error during rollback.");
                ex.printStackTrace();
            }
        }
    }

    public void saveGame(String sql, List<Object> values) {
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            // Set values in prepared statement
            for (int i = 0; i < values.size(); i++) {
                pstmt.setObject(i + 1, values.get(i));
            }
            pstmt.executeUpdate();
            conn.commit(); // Confirm changes
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                getConnection().rollback(); // Rollback changes in case of error
            } catch (SQLException ex) {
                System.out.println("Error during rollback.");
                ex.printStackTrace();
            }
        }
    }

    public int insertAndGetId(String sql) {
        int generatedId = -1;
        try (Statement stmt = getConnection().createStatement()) {
            stmt.executeUpdate(sql);
            conn.commit();  // Confirm changes if auto-commit is disabled

            // Query the last inserted row ID
            try (ResultSet rs = stmt.executeQuery("SELECT civilization_seq.currval FROM dual")) {
                if (rs.next()) {
                    generatedId = rs.getInt(1); // Retrieve the last inserted ID
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                getConnection().rollback(); // Rollback changes in case of error
            } catch (SQLException ex) {
                System.out.println("Error during rollback.");
                ex.printStackTrace();
            }
        }
        return generatedId;
    }

    public List<Map<String, Object>> query(String sql) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        try (Statement stmt = getConnection().createStatement();
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

    public void updateWithBlob(String query, byte[] blobData) {
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setBytes(1, blobData);
            pstmt.executeUpdate();
            conn.commit(); // Confirm changes
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                getConnection().rollback(); // Rollback changes in case of error
            } catch (SQLException ex) {
                System.out.println("Error during rollback.");
                ex.printStackTrace();
            }
        }
    }
}
