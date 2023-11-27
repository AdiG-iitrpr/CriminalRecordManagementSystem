import java.io.*;
import java.sql.*;

public class DatabaseInit {
    static void executeSql(File file, Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String sql = "";
            try (FileReader fileReaderObj = new FileReader(file)) {
                char cur;
                while ((cur = (char) fileReaderObj.read()) != (char) -1) {
                    sql = sql + cur;
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception e) {
            System.out.println(file.getName() + ": " + e.getMessage());
        }
    }

    static void init(File[] files, Connection connection, String filePath) {
        // File roleFile = null;
        // for (File curFile : files) {
        //     if(curFile.getName() == "roles.sql"){
        //         roleFile = curFile;
        //         continue;
        //     }

        //     if (curFile.isDirectory()) {
        //         init(curFile.listFiles(), connection);
        //     } else {
        //         executeSql(curFile, connection);
        //         System.out.println("File completed: " + curFile.getName());
        //     }
        // }

        // executeSql(roleFile, connection);
        File file = new File(filePath+"/queries/createTables.sql");
        executeSql(file, connection);
        file = new File(filePath+"/storedProcedures/insertProcedures.sql");
        executeSql(file, connection);
        file = new File(filePath+"/triggers/insertTriggers.sql");
        executeSql(file, connection);
        file = new File(filePath+"/queries/dataDistrict.sql");
        executeSql(file, connection);
        file = new File(filePath+"/queries/dataStation.sql");
        executeSql(file, connection);
        file = new File(filePath+"/queries/dataOfficer.sql");
        executeSql(file, connection);
        file = new File(filePath+"/queries/dataCases.sql");
        executeSql(file, connection);
        file = new File(filePath+"/queries/dataFIR.sql");
        executeSql(file, connection);
        file = new File(filePath+"/queries/dataSuspect.sql");
        executeSql(file, connection);
        file = new File(filePath+"/queries/dataCriminal.sql");
        executeSql(file, connection);
        file = new File(filePath+"/queries/dataCourtHearing.sql");
        executeSql(file, connection);
        file = new File(filePath+"/queries/dataJail.sql");
        executeSql(file, connection);
        file = new File(filePath+"/queries/dataJailLog.sql");
        executeSql(file, connection);
        file = new File(filePath+"/queries/dataUsers.sql");
        executeSql(file, connection);
    }

    public static void main(String[] args) throws IOException {
        String filePath = new File("").getAbsolutePath() + "/../../sql";
        File[] files = new File(filePath).listFiles();

        final String DB_URL_1 = "jdbc:postgresql://localhost/postgres";
        final String DB_URL_2 = "jdbc:postgresql://localhost/crime_record_management_system";
        final String USER = "postgres";
        final String PASSWORD = "1234";

        try {
            Connection connection_1 = DriverManager.getConnection(DB_URL_1, USER, PASSWORD);
            Statement statement = connection_1.createStatement();
            System.out.println("Successfully connected to the database: " + DB_URL_1);

            String sql = "DROP DATABASE IF EXISTS crime_record_management_system;";
            statement.executeUpdate(sql);
            sql = "CREATE DATABASE crime_record_management_system;";
            statement.executeUpdate(sql);
            connection_1.close();
            statement.close();

            Connection connection_2 = DriverManager.getConnection(DB_URL_2, USER, PASSWORD);
            System.out.println("Successfully connected to the database: " + DB_URL_2);

            init(files, connection_2, filePath);
            System.out.println("SQL files executed successfully!");

            connection_2.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
