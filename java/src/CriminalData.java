import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CriminalData {
    Connection connection;
    public CriminalData(Connection connection){
        this.connection = connection;
    }

    private void display(){
        System.out.println("1. Add criminal Record ");
        System.out.println("2. Fetch Information of criminal");
        System.out.println("3. Go back to main dashboard");

    }

    private void fetchCriminalRecord(int criminal_id){
        // fetch information from table using connection 
        try{
            Statement stmt = connection.createStatement();
            System.out.println("Fetching records with condition...");
            String sql = "SELECT * FROM Registration" + " WHERE id >= " + Integer.toString(criminal_id);
            ResultSet rs = stmt.executeQuery(sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                //Display values
                System.out.print("ID: " + rs.getInt("criminal_id"));
                System.out.print(", First Name: " + rs.getInt("first_name"));
                System.out.print(", Last Name: " + rs.getString("last_name"));
                System.out.println(", Gender: " + rs.getString("gender"));
                System.out.println(", Address: " + rs.getString("criminal_address"));
                System.out.println(", District: " + rs.getString("district"));
            }
            rs.close();
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return;
    }

    private void addCriminalRecord(){

        // add criminal Information in Criminal Table using Connection
        // add case information in Cases Table

        // use attributes for taking input
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Criminal ID :");
        String criminal_id = sc.nextLine();
        System.out.println("Enter First name :");
        String first_name = sc.nextLine();
        System.out.println("Enter Last name :");
        String last_name = sc.nextLine();
        System.out.println("Enter gender (M/F) :");
        char gender = sc.next().charAt(0);
        System.out.println("Enter Criminal Address :");
        String address = sc.nextLine();
        System.out.println("Enter District :");
        String district = sc.nextLine();
        System.out.println("Enter Jail ID :");
        int jail_id = sc.nextInt();
        try{
            Statement stmt = connection.createStatement();
            System.out.println("Inserting records into the table...");  
            String values = "("+criminal_id+","+first_name+","+last_name+","+gender+","+address+","+district+")";
            String sql = "INSERT INTO Criminal (criminal_id, first_name, last_name, gender, criminal_address, district) VALUES " + values;
            stmt.executeUpdate(sql);
            // To be done - Add data to jail log
            values = "("+Integer.toString(jail_id)+","+criminal_id+")";
            sql = "INSERT INTO jailLog (jail_id, criminal_id) VALUES " + values;
            stmt.executeUpdate(sql);
            System.out.println("Inserted record into the table...");
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        sc.close();
        return;
        // officer-in-charge must exist
        // case must be filed already 
        // verdict in courtHearing must be 
    }


    public void runClass(){

        Scanner cin = new Scanner(System.in);
        while(true){

            display();
            int option = cin.nextInt();

            if(option == 1){
                addCriminalRecord();
            }else if(option == 2){
                int criminal_id = cin.nextInt();
                fetchCriminalRecord(criminal_id);
            }else if(option == 3){
                System.out.println("Going back to Main dashboard!!");
                break;
            }else{
                System.out.println("Wrong option selected");
            }
        }

        cin.close();
    }
}
