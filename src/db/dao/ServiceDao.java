package db.dao;

import db.SQLConnection;
import db.entities.Service;
import db.statements.ServiceStatements;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ServiceDao extends SQLConnection implements Dao<Service> {
    private Connection connection;

    public ServiceDao() {
        super();
        connection = getConnection();
    }

    @Override
    public void save(Service service) {
        String sql = "INSERT INTO services (service, delivered, arrival, quit, user_name, surname, model, registration, phone, email) VALUES (?,?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, service.getService());
            statement.setBoolean(2, service.isDelivered());
            statement.setDate(3, service.getArrival());
            statement.setDate(4, service.getQuit());
            statement.setString(5, service.getName());
            statement.setString(6, service.getSurname());
            statement.setString(7, service.getModel());
            statement.setString(8, service.getRegistration());
            statement.setString(9, service.getPhone());
            statement.setString(10, service.getEmail());

            statement.execute();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            listener.onSQLException("Error trying to create service");
        }
    }

    @Override
    public Service get(int id) {
        Service newService = null;
        String sql = "SELECT * FROM services WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int temp_id = resultSet.getInt(ServiceStatements.COLUMN_ID);
                int service = resultSet.getInt(ServiceStatements.COLUMN_TYPE);
                boolean delivered = resultSet.getBoolean(ServiceStatements.COLUMN_DELIVERED);
                Date arrival = resultSet.getDate(ServiceStatements.COLUMN_ARRIVAL);
                Date quit = resultSet.getDate(ServiceStatements.COLUMN_QUIT);
                String name = resultSet.getString(ServiceStatements.COLUMN_NAME);
                String surname = resultSet.getString(ServiceStatements.COLUMN_SURNAME);
                String model = resultSet.getString(ServiceStatements.COLUMN_MODEL);
                String registration = resultSet.getString(ServiceStatements.COLUMN_REGISTRATION);
                String phone = resultSet.getString(ServiceStatements.COLUMN_PHONE);
                String email = resultSet.getString(ServiceStatements.COLUMN_EMAIL);

                newService = new Service(temp_id, service, delivered, arrival, quit, name, surname, model, registration,
                        phone, email);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            listener.onSQLException("Error tratando de obtener el servicio");
        }

        return newService;
    }

    @Override
    public List<Service> getAll() {
        return null;
    }

    @Override
    public void update(Service service) {
        String sql = "UPDATE services SET delivered = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, service.isDelivered());
            statement.setInt(2, service.getId());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            listener.onSQLException("Error tratando de actualizar el servicio");
        }

    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM services WHERE id = ?;";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            listener.onSQLException("Error tratando de borrar el servicio");
        }
    }
}
