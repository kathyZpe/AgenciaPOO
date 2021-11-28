package db.dao;

import db.SQLConnection;
import db.entities.Agency;
import db.entities.Part;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartDao extends SQLConnection implements Dao<Part> {
    private Connection connection;
    public PartDao() {
        super();
        connection = getConnection();
    }

    @Override
    public void save(Part part) {
        String sql = "INSERT INTO parts(part_name, unities,price,agency_id) VALUES(?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, part.getName());
            statement.setInt(2, part.getUnities());
            statement.setDouble(3, part.getPrice());
            statement.setInt(4, part.getAgencyId());

            statement.execute();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            listener.onSQLException("Error trying to create part");
        }
    }

    @Override
    public Part get(int id) {
        return null;
    }

    @Override
    public List<Part> getAll() {
        return null;
    }

    public List<Part> getAllByAgency(Agency agency){
        String sql = "SELECT parts.id, parts.name, parts.unities, parts.price, parts.agency_id FROM parts INNER JOIN " +
            "parts.agency_id = agency.id WHERE agency_id = ?";
        List<Part> partList = new ArrayList<Part>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, agency.getId());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                //int id = resultSet.getInt()
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            listener.onSQLException("Error trying to get all parts by agency");
        }
        return null;
    }

    @Override
    public void update(Part part) {
        String sql = "UPDATE parts SEt unities = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, part.getUnities());
            statement.setInt(2, part.getId());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            listener.onSQLException("Error trying to update part");
        }
    }

    @Override
    public void delete(int id) {

    }
}
