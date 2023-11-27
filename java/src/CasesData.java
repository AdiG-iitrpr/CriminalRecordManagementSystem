import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CasesData {
    
    Connection connection;

    public CasesData(Connection connection){
        this.connection = connection;
    }

    private void display(){
        System.out.println("1. Case Information based case-type, date, station, age, gender");
        System.out.println("2. Dangerous criminals in district");
        System.out.println("3. Ranking of Districts based on crime rate,Time of incident");
        System.out.println("4. Go back to Main dashboard");
    }

    private void caseInformation(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Press 1 for cases based fir-type, 2 for time, 3 for station, 4 for age, 5 for gender");
        int choice = sc.nextInt();
        sc.nextLine();
        if (choice == 1)
        {
            try
            {
                Statement stmt = connection.createStatement();
                System.out.println("Showing number of cases of particular FIR Types...");
                String sql = "SELECT incident_type, count (*) as num_cases FROM FIR GROUP BY incident_type ";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {
                    //Display values
                    System.out.print("FIR Type: " + rs.getString("incident_type"));
                    System.out.println(", Number of Cases: " + rs.getString("num_cases"));
                }
                rs.close();
            }
            catch(SQLException e)
            {
                System.err.println(e.getMessage());
            }
        }
        else if (choice == 2)
        {
            try
            {
                Statement stmt = connection.createStatement();
                System.out.println("Showing number of cases of particular FIR Time...");
                String sql = "SELECT incident_time, count (*) as num_cases FROM FIR GROUP BY incident_time ";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {
                    //Display values
                    System.out.print("FIR Time: " + rs.getString("incident_time"));
                    System.out.println(", Number of Cases: " + rs.getString("num_cases"));
                }
                rs.close();
            }
            catch(SQLException e)
            {
                System.err.println(e.getMessage());
            }            
        }
        else if (choice == 3)
        {
            try
            {
                Statement stmt = connection.createStatement();
                System.out.println("Showing number of cases of particular station...");
                String sql = "SELECT station_id, count (*) as num_cases FROM FIR GROUP BY station_id ";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {
                    //Display values
                    System.out.print("FIR Station: " + rs.getString("station_id"));
                    System.out.println(", Number of Cases: " + rs.getString("num_cases"));
                }
                rs.close();
            }
            catch(SQLException e)
            {
                System.err.println(e.getMessage());
            }              
        }
        else if (choice == 4)
        {
            try
            {
                Statement stmt = connection.createStatement();
                System.out.println("Showing number of cases where suspect is of a particular age...");
                String sql = "SELECT age, count (*) as num_cases FROM suspect,FIR where suspect.fir_id = FIR.fir_id GROUP BY age ORDER BY age";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {
                    //Display values
                    System.out.print("Age: " + rs.getString("age"));
                    System.out.println(", Number of Cases: " + rs.getString("num_cases"));
                }
                rs.close();
            }
            catch(SQLException e)
            {
                System.err.println(e.getMessage());
            }              
        }
        else if (choice == 5)
        {
            try
            {
                Statement stmt = connection.createStatement();
                System.out.println("Showing number of cases where suspect is of a particular gender...");
                String sql = "SELECT gender, count (*) as num_cases FROM suspect,FIR where suspect.fir_id = FIR.fir_id GROUP BY gender";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {
                    //Display values
                    System.out.print("gender: " + rs.getString("gender"));
                    System.out.println(", Number of Cases: " + rs.getString("num_cases"));
                }
                rs.close();
            }
            catch(SQLException e)
            {
                System.err.println(e.getMessage());
            }   
        }
        sc.close();

    }

    private void dangerousCriminal(){
        // Most dangerous criminals of a district
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the district you want dangerous criminals from: ");
            String District = sc.nextLine();
            Statement stmt = connection.createStatement();
            System.out.println("Showing dangerous criminals of district in descending order...");
            // String sql = "SELECT * FROM criminal WHERE criminal_id = '" + cid + "'";
            String sql = "SELECT COURT.suspect_id AS criminal_id, num_convictions FROM (SELECT suspect_id, COUNT(*) as num_convictions FROM courtHearing WHERE verdict = 'Guilty' GROUP BY suspect_id) AS COURT,(SELECT suspect_id FROM Criminal WHERE district = '" + District + "') AS D_Criminal WHERE COURT.suspect_id = D_Criminal.suspect_id ORDER BY num_convictions desc";
            ResultSet rs = stmt.executeQuery(sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                //Display values
                System.out.print("Criminal ID: " + rs.getString("criminal_id"));
                System.out.println(", Number of Convictions: " + rs.getString("num_convictions"));
            }
            rs.close();
            sc.close();
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return;

    }

    private void rankDistrict(){
        // Rank districts in order of danger (Number of FIRs)
        try{
            Statement stmt = connection.createStatement();
            System.out.println("Showing dangerous districts in descending order...");
            // String sql = "SELECT * FROM criminal WHERE criminal_id = '" + cid + "'";
            String sql = "SELECT district, count(fir_id) as num_fir FROM (FIR NATURAL JOIN Station) GROUP BY district ORDER BY num_fir DESC";

            ResultSet rs = stmt.executeQuery(sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                //Display values
                System.out.print("District: " + rs.getString("district"));
                System.out.println(", FIR Number: " + rs.getString("num_fir"));
            }
            rs.close();
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return;

    }

    public void runClass(){

        Scanner cin = new Scanner(System.in);

        while (true) {
            display();
            int option = cin.nextInt();
            cin.nextLine();
            if(option == 1){
                // case information 
                caseInformation();
            }else if(option == 2){
                dangerousCriminal();
            }else if(option == 3){
                rankDistrict();
            }else if(option == 4){
                System.out.println("Going back to Main dashboard!!");
                break;
            }else {
                System.out.println("Wrong input");
            }
        }

        cin.close();
    }
}
