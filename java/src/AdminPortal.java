import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class AdminPortal {
    Connection connection;
    public AdminPortal(Connection connection){
        this.connection = connection;
    }

    private void display(){
        System.out.println("1. Add Jail Information");
        System.out.println("2. Add Station Information");
        System.out.println("3. Update officer-in-charge in district Table");
        System.out.println("4. Go back to Main dashboard");
    }

    public void runClass(){

        Scanner cin = new Scanner(System.in);
        while(true){

            display();
            int option = cin.nextInt();

            if(option == 1){
                //
            }else if(option == 2){
                //
            }else if(option == 3){
                //
            }else if(option == 4){
                System.out.println("Going back to Main Menu!!");
                break;
            }else{
                System.out.println("Wrong option selected");
            }
        }
        
        cin.close();
    }
}
