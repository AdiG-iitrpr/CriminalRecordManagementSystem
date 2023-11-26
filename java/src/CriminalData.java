import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
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
    }

    private void addCriminalRecord(){

        // add criminal Information in Criminal Table using Connection
        // add case information in Cases Table

        // use attributes for taking input


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
