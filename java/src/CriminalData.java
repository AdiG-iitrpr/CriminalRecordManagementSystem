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

    private void fetchCriminalRecord(String cid){
        // fetch information from table using connection 
        try{
            Statement stmt = connection.createStatement();
            System.out.println("Fetching records with condition...");
            String sql = "SELECT * FROM criminal WHERE criminal_id = '" + cid + "'";
            ResultSet rs = stmt.executeQuery(sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                //Display values
                System.out.print("ID: " + rs.getString("criminal_id"));
                System.out.print(", First Name: " + rs.getString("first_name"));
                System.out.print(", Last Name: " + rs.getString("last_name"));
                System.out.print(", Gender: " + rs.getString("gender"));
                System.out.print(", Address: " + rs.getString("criminal_address"));
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
        String gender = sc.nextLine();
        System.out.println("Enter Criminal Address :");
        String address = sc.nextLine();
        System.out.println("Enter District :");
        String district = sc.nextLine();
        System.out.println("Enter Jail ID :");
        int jail_id = sc.nextInt();
        try{
            Statement stmt = connection.createStatement();
            System.out.println("Inserting records into the table...");  
            String values = "('"+criminal_id+"','"+first_name+"','"+last_name+"','"+gender+"','"+address+"','"+district+"')";
            String sql = "INSERT INTO Criminal (criminal_id, first_name, last_name, gender, criminal_address, district) VALUES " + values;
            stmt.executeUpdate(sql);
            // To be done - Add data to jail log
            values = "("+Integer.toString(jail_id)+",'"+criminal_id+"')";
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
                System.out.println("Enter criminal id : ");
                cin.nextLine();
                String criminal_id = cin.nextLine();
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
