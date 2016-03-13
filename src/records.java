
/**
 *
 * @author mark
 */
public class records {
    static String USER  = "records";
    static String PASS = "records";
    static String DBNAME = "records";
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static String DB_URL = "jdbc:derby://localhost:1527/";

    
public static void main(String[] args) {
        DB_URL = DB_URL + DBNAME + ";user="+ USER + ";password=" + PASS;

        
    
    }
    
    
}
