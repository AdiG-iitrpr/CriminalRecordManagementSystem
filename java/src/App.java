import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

class App{

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/crime_record_management_system";
    static final String USER = "postgres";
    static final String PASSWORD = "postgres";

    private void display(){
        System.out.println("1. Get Information related to criminal by providing criminal ID: (12-DigitNumber)\n");
        System.out.println("2. Add criminal Information in the format (FirstName,LastName,DOB,Gender,Address,StateOfOrigin,DateOfConviction)");
    }
    public static void main(String [] args){

        
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            
            while(true){

            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        


    }
}