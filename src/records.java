
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
        while (true) {
            userInteraction();
        }
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
        Scanner scan = new Scanner(System.in);
        System.out.println("RecordList ");
        System.out.println("1.List All Albums Titles\n"
                + "2.List all the data for an album specified by the user\n"
                + "3.Insert a new album\n"
                + "4.Insert a new studio and update all albums published by one studio to be published by the new studio\n"
                + "5.Remove an album specified by the user\n"
                + "6.Exit");
        int choice = scan.nextInt();

        switch (choice) {
            case 1:
                query("Select AlbumTitle From Albums", 1);
                break;
            case 2:
                System.out.println("Which album would you like to know about");
                viewSingle(scan);
                break;
            case 3:
                System.out.println("Tell me more about this album");
                insertAlbum(scan);
                break;
            case 4:

                break;
            case 5:

                break;
            case 6:
                scan.close();
                exitCase();

        }

    }

    private static void viewSingle(Scanner scan) {

        String title = scan.nextLine();
        title = scan.next();
        query("select * from albums WHERE albumtitle = \'" + title + "\'", 2);
    }

    private static void query(String sql, int type) {
        System.out.println(sql);
        try {
            stmt = conn.createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery(sql);
            if (type == 1) {
                System.out.println("Title");
                System.out.println("----------------");
            }
            if (type == 2) {
                System.out.println("Title\tGroup\t\tStudio\t\tdloDate_Recorded\tLength\tNumber of Songs");
                System.out.println("---------------------------------------------------------");

            }

            while (rs.next()) {
                String title = dispNull(rs.getString("ALBUMTITLE"));
                if (type == 2) {
                    String gName = dispNull(rs.getString("GROUPNAME"));
                    String sName = dispNull(rs.getString("STUDIONAME"));
                    String dateRecorded = dispNull(rs.getString("DATERECORDED"));
                    String length = dispNull(rs.getString("LENGTH"));
                    String numberOfSongs = dispNull(rs.getString("NUMBEROFSONGS"));

                    System.out.println(title + "\t" + gName + "\t" + sName + "\t" + dateRecorded + "\t" + length + "\t" + numberOfSongs);
                }
                if (type == 1) {
                    System.out.println(title);
                }

            }
            System.out.println("");
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private static void exitCase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Takes the input string and outputs "N/A" if the string is empty or null.
     *
     * @param input The string to be mapped.
     * @return Either the input string or "N/A" as appropriate.
     */
    public static String dispNull(String input) {
        //because of short circuiting, if it's null, it never checks the length.
        if (input == null || input.length() == 0) {
            return "N/A";
        } else {
            return input;
        }
    }

    //Reference: INSERT INTO albums VALUES('Party Studio','jammie', 'Sunday', '12/12/1961', '3:03', '2');
    private static void insertAlbum(Scanner scan) {
        try {
            
            

            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO albums VALUES('Party Studio','jammie', 'Saturday', '12/12/1961', '3:03', '2')");
        } catch (SQLException ex) {
            Logger.getLogger(records.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
