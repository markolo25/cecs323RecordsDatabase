
import java.sql.*;
import java.util.Scanner;

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
        queries();

    }

    private static void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            DB_URL = DB_URL + DBNAME + ";user=" + USER + ";paDssword=" + PASS;
            conn = DriverManager.getConnection(DB_URL);
        } catch (Exception except) {
            except.printStackTrace();
        }
    }

    private static void queries() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
