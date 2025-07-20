package controller;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String CSV_FILE = "src/users.csv";
    private static final String HEADER = "username,password,email";
    
    public DatabaseManager() {
        initializeDatabase();
    }
    
    private void initializeDatabase() {
        File file = new File(CSV_FILE);
        if (!file.exists()) {
            try {
                file.createNewFile();
                
                // Tulis header
                try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE))) {
                    writer.println(HEADER);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean registerUser(String username, String password, String email) {
        // Cek apakah username sudah ada
        if (userExists(username)) {
            return false;
        }
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE, true))) {
            writer.println(username + "," + password + "," + email);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean loginUser(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            reader.readLine(); // Skip header
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    if (parts[0].equals(username) && parts[1].equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean userExists(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            reader.readLine(); // Skip header
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<String[]> getAllUsers() {
        List<String[]> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            reader.readLine(); // Skip header
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                users.add(parts);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}