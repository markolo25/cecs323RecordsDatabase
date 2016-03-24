
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
    private int debug = 0;

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
        Scanner scan = null;
        Scanner scanToo = new Scanner(System.in);
        scan = new Scanner(System.in);
        System.out.println("RecordList ");
        System.out.println("1.List All Albums Titles\n"
                + "2.List all the data for an album specified by the user\n"
                + "3.Insert a new album\n"
                + "4.Insert a new studio and update all albums published by one studio to be published by the new studio\n"
                + "5.Remove an album specified by the user\n"
                + "6.Exit");
        int choice = scanToo.nextInt();

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
                insertStudio(scan);
                break;
            case 5:
                System.out.println("Which album would you like to delete");
                query("Select AlbumTitle From Albums", 1);
                removeAlbum(scan);
                break;
            case 6:
                scanToo.close();
                exitCase();

        }

    }

    private static void viewSingle(Scanner scan) {

        String title = scan.nextLine();
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
                System.out.println("------------------------------------------------------------------------");
            }
            if (type == 2) {
                System.out.println("Title\tGroup\t\tStudio\t\tdloDate_Recorded\tLength\tNumber of Songs");
                System.out.println("------------------------------------------------------------------------");

            }
            if (type == 3) {
                System.out.println("Which studio would you like to change");
            }

            while (rs.next()) {
                String title = null;

                if (type < 3) {
                    title = dispNull(rs.getString("ALBUMTITLE"));
                }

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

                if (type == 3) {
                    System.out.println(rs.getString("STUDIONAME"));
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
        String sName = null, gName = null, title = null, date = null, length = null, tracks = null;
        try {
            System.out.println("Name of album (Please avoid apostrophies)");
            title = scan.nextLine();
            System.out.println("How many tracks does it have");
            tracks = scan.nextLine();
            date = "3/24/16";
            sName = "Arkham Studio";
            gName = "ragae boys";
            length = "9:99";
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO albums VALUES(\'" + sName + "\',\'" + gName + "\', \'" + title + "\', \'" + date + "\', \'" + length + "\', \'" + tracks + "\')");

        } catch (Exception ex) {
            System.out.println("Primary Key match error, you already have an element with a matching primary key, or you have an \' on your input");
            ex.printStackTrace();
        }

    }

    private static void removeAlbum(Scanner scan) {
        try {
            System.out.println("Which one would you like to remove");
            stmt = conn.createStatement();
            String album = scan.nextLine();
            String sql = "DELETE FROM ALBUMS WHERE ALBUMTITLE = \'" + album + "\'";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

//INSERT INTO recordingstudio VALUES('Arkham Studio','13 eastshore,irvine','sam','949-123-4567');
    private static void insertStudio(Scanner scan) {
        String owner = null, phone = null, studioName = null, studioAddress = null;
        try {

            System.out.println("Studio Name?");
            studioName = scan.nextLine();
            System.out.println("Studio Address?");
            studioAddress = scan.nextLine();
            System.out.println("Studio Owner?");
            owner = scan.nextLine();
            System.out.println("Studio Phone?");
            phone = scan.nextLine();

            stmt = conn.createStatement();
            String sql = "INSERT INTO recordingstudio VALUES(\'" + studioName + "\',\'" + studioAddress + "\', \'" + owner + "\', \'" + phone + "\')";
            stmt.executeUpdate(sql);

            System.out.println("Which studio would you like to replace");
            System.out.println("_____________________________________________");
            query("Select Studioname From recordingstudio", 3);
            String studioToReplace = scan.nextLine();
            String updSQL = "UPDATE RECORDS.ALBUMS SET \"STUDIONAME\" = \'" + studioName + "\' WHERE STUDIONAME = \'" + studioToReplace+"\'";
            System.out.println(updSQL);
            stmt = conn.createStatement();
            stmt.executeUpdate(updSQL);

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}
