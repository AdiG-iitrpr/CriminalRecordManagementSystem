import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AdminPortal {
    public void runClass(String user, String pass){
        // if(user!="postgres"||pass!="1234")return;
        System.out.println("Create a new user");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name : ");
        String name = sc.nextLine();
        System.out.print("Enter password : ");
        String password = sc.nextLine();
        System.out.print("Enter user role : ");
        String role = sc.nextLine();
        // CREATE USER
        // ASSIGN ROLE
        try{
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/crime_record_management_system", user, pass);
            String sql = "CREATE USER "+name+" WITH PASSWORD '"+password+"'";
            // String sql = "CREATE USER " + name+"@'localhost' IDENTIFIED BY "+password;
            System.out.println(sql);
            try(Statement statement = connection.createStatement()){
                statement.executeUpdate(sql);
            }
            sql = "ALTER USER "+name+" SET ROLE "+role;
            // sql = "GRANT "+role+" TO "+name;
            System.out.println(sql);
            try(Statement statement = connection.createStatement()){
                statement.executeUpdate(sql);
            }
            sql = "INSERT INTO Users VALUES ('"+name+"', '"+role+"')";
            System.out.println(sql);
            try(Statement statement = connection.createStatement()){
                statement.executeUpdate(sql);
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }


        
    }    
}
