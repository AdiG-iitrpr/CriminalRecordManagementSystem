import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class CasesData {
    
    Connection connection;

    public CasesData(Connection connection){
        this.connection = connection;
    }

    private void display(){
        System.out.println("1. Case Information based case-type, time, station, age, gender");
        System.out.println("2. Dangerous criminals in district");
        System.out.println("3. Case Reporting in various districts");
        System.out.println("4. Ranking of Districts based on crime rate,Time of incident");
        System.out.println("5. Go back to Main dashboard");
    }

    private void caseInformation(){

    }

    private void dangerousCriminal(){

    }

    private void caseReporting(){

    }

    private void rankDistrict(){

    }

    public void runClass(){

        Scanner cin = new Scanner(System.in);

        while (true) {
            display();
            int option = cin.nextInt();

            if(option == 1){
                // case information 
                caseInformation();
            }else if(option == 2){
                dangerousCriminal();
            }else if(option == 3){
                caseReporting();
            }else if(option == 4){
                rankDistrict();
            }else if(option == 5){
                System.out.println("Going back to Main dashboard!!");
                break;
            }else {
                System.out.println("Wrong input");
            }
        }

        cin.close();
    }
}
