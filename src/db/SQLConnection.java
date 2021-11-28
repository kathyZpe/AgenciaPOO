package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

import db.statements.AgencyStatements;
import db.statements.PartStatements;
import db.statements.ServiceStatements;
import listeners.SQLListener;

public class SQLConnection {

    private final String URL = "jdbc:mysql://localhost:3306/agenciakama";
    private final String USER = "root";
    private final String PASSWORD = "root";

    private Connection connection;
    protected SQLListener listener;

    public SQLConnection() {
        testDriver();
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            statement.execute(ServiceStatements.CREATE_TABLE);
            statement.execute(AgencyStatements.CREATE_TABLE);
            statement.execute(PartStatements.CREATE_TABLE);

            statement.close();
        } catch(SQLSyntaxErrorException e) {
            e.printStackTrace();
            listener.onSQLException(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            listener.onSQLException(e.getMessage());
        }
    }

    public void setSQLListener(SQLListener listener){
        this.listener = listener;
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            listener.onSQLException("Error tratando de cerrar la conexion con la base de datos");
        }
    }

    private void testDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            listener.onSQLException(e.getMessage());
        }

    }
}
