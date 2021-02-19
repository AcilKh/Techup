import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class JDBCConnectionPool {
    private List<Connection>availableConnections =
            new ArrayList<Connection>();
    private List<Connection>usedConnections = new ArrayList<Connection>();
    private final int MAX_CONNECTIONS = 5;

    private String url;
    private String id;
    private String password;

    public JDBCConnectionPool(String Url, String user, String Password)
            throws SQLException {
        this.url = Url;
        this.id = user;
        this.password = Password;

        for (int count = 0; count <MAX_CONNECTIONS; count++) {
            availableConnections.add(this.createConnection());
        }

    }

    /** Private function,
     used by the Pool to create new connection internally **/

    private Connection createConnection() throws SQLException {
        return DriverManager
                .getConnection(this.url, this.id, this.password);
    }


    /** Public function, used by us to get connection from Pool **/
    public Connection getConnection() {
        if (availableConnections.size() == 0) {
            System.out.println("All connections are Used !!");
            return null;
        } else {
            Connection con =
                    availableConnections.remove(
                            availableConnections.size() - 1);
            usedConnections.add(con);
            return con;
        }
    }

    /** Public function, to return connection back to the Pool **/
    public boolean releaseConnection(Connection con) {
        if (null != con) {
            usedConnections.remove(con);
            availableConnections.add(con);
            return true;
        }
        return false;
    }

    public int getFreeConnectionCount() {
        return availableConnections.size();
    }
}

