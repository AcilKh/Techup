import java.sql.SQLException;

public class DataSource {
    private static JDBCConnectionPool data;

    private static final String idUser = "";

    private static final String Password="";

    private static final String URL="";

    static {
        try {
            data = new JDBCConnectionPool(URL, idUser,Password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
