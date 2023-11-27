import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

class App{

    static String userName,pwd;

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/crime_record_management_system";
    static String USER = "postgres";
    static String PASSWORD = "1234";

    private static void display(){
        System.out.println("Choose your options: ");
        System.out.println("1. Enter the dashboard for information regarding criminals");
        System.out.println("2. Information regarding case reporting in various districts.");
        System.out.println("3. Exit");
    }

    private static void checkAuthorization(){
        System.out.println("Enter userName and password :");
        Scanner cin = new Scanner(System.in);// use both as postgres for now
        USER = cin.nextLine();
        // System.out.println("Username : "+USER);
        PASSWORD = cin.nextLine();
        // System.out.println("Password : "+PASSWORD);
        // cin.close();

    }
    public static void main(String [] args){

        Scanner cin = new Scanner(System.in);

        checkAuthorization();

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            
            while(true){
                
                display();
                int option = cin.nextInt();
                cin.nextLine();
                if(option == 1){
                    CriminalData crimeData = new CriminalData(connection);
                    crimeData.runClass();

                }else if(option == 2){
                    // directed to caseData class
                    CasesData casesData = new CasesData(connection);
                    casesData.runClass();
                }else if(option == 3){
                    break;
                }else{
                    System.out.println("Wrong input");
                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // cin.close();
    }
}