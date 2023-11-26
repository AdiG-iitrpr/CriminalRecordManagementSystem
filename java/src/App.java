import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

class App{

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/crime_record_management_system";
    static final String USER = "postgres";
    static final String PASSWORD = "postgres";

    private static void display(){
        System.out.println("Choose your options: ");
                System.out.println("1. Enter the dashboard for information regarding criminals");
                System.out.println("2. Information regarding case reporting in various districts.");
                System.out.println("3. Admin Portal");
                System.out.println("4. Exit");
    }
    public static void main(String [] args){

        Scanner cin = new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            
            while(true){
                
                display();
                
                int option = cin.nextInt();

                if(option == 1){
                    CriminalData crimeData = new CriminalData(connection);
                    crimeData.runClass();

                }else if(option == 2){
                    // directed to caseData class
                    CasesData casesData = new CasesData(connection);
                    casesData.runClass();
                }else if(option == 3){
                    AdminPortal adminPortal = new AdminPortal(connection);
                    adminPortal.runClass();
                }else if(option == 4){
                    break;
                }else{
                    System.out.println("Wrong input");
                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        cin.close();
    }
}