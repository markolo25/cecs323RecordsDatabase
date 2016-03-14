
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author mark
 */
public class records {

    static String USER = "records";
    static String PASS = "records";
    static String DBNAME = "records";
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static String DB_URL = "jdbc:derby://localhost:1527/";
    private static Connection conn = null;
    private static Statement stmt = null;

    public static void main(String[] args) {
        createConnection();
        userInteraction();
    }

    private static void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            DB_URL = DB_URL + DBNAME + ";user=" + USER + ";password=" + PASS;
            //System.out.println(DB_URL);
            conn = DriverManager.getConnection(DB_URL);
        } catch (Exception except) {
            except.printStackTrace();
        }
    }

    private static void userInteraction() {
        System.out.println("1.List All Albums Titles\n"
                + "2.List all the data for an album specified by the user\n"
                + "3.Insert a new album\n"
                + "4.Insert a new studio and update all albums published by one studio to be published by the new studio\n"
                + "5.Remove an album specified by the user\n"
                + "6.Exit");
        int choice = 2;
        switch (choice) {
            case 1:
                query("Select * From Album", "Album TItle");
                break;
            case 2:
                query("Select * From Album", "Album TItle");
                break;
            case 3:
                query("Select * From Album", "Album TItle");
                break;
            case 4:
                query("Select * From Album", "Album TItle");
                break;
            case 5:
                query("Select * From Album", "Album TItle");
                break;
            case 6:
                exitCase();

        }

    }

    private static void query(String sql, String displayFormat) {
        try {
            stmt = conn.createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                //Retrieve by column name
                String id = rs.getString("au_id");
                String phone = rs.getString("phone");
                String first = rs.getString("au_fname");
                String last = rs.getString("au_lname");

                //Display values
                //System.out.printf(displayFormat,
                // dispNull(id), dispNull(first), dispNull(last), dispNull(phone));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private static void exitCase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
