package db.dao;

import db.SQLConnection;
import db.entities.Agency;
import db.statements.AgencyStatements;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgencyDao extends SQLConnection implements Dao<Agency> {
    private Connection connection;

    public AgencyDao() {
        super();
        connection = getConnection();
    }

    @Override
    public void save(Agency agency) {
        String sql = "INSERT INTO agencies (agency_name) VALUES (?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, agency.getName());

            statement.execute();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            listener.onSQLException("Error trying to create agency");
        }
    }

    @Override
    public Agency get(int id) {
        Agency newAgency = null;
        String sql = "SELECT * FROM agencies WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int temp_id = resultSet.getInt(AgencyStatements.COLUMN_ID);
                String name = resultSet.getString(AgencyStatements.COLUMN_NAME);

                newAgency = new Agency(temp_id, name);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            listener.onSQLException("Error trying to get agency");
        }
        return newAgency;
    }

    public List<Agency> getLikeName(String name){
        String sql = "SELECT * FROM agencies WHERE UPPER(agency_name) LIKE ?;";
        List<Agency> agencyList = new ArrayList<Agency>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name.toUpperCase() + "%");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int temp_id = resultSet.getInt(AgencyStatements.COLUMN_ID);
                String temp_name = resultSet.getString(AgencyStatements.COLUMN_NAME);
                Agency agency = new Agency(temp_id, temp_name);
                agencyList.add(agency);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            listener.onSQLException("Error trying to get agency by name");
        }
        return agencyList;
    }

    @Override
    public List<Agency> getAll() {
        String sql = "SELECT * FROM agencies;";
        List<Agency> agencyList = new ArrayList<Agency>();
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int temp_id = resultSet.getInt(AgencyStatements.COLUMN_ID);
                String temp_name = resultSet.getString(AgencyStatements.COLUMN_NAME);
                Agency agency = new Agency(temp_id, temp_name);
                agencyList.add(agency);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            listener.onSQLException("Error trying to get agency by name");
        }
        return agencyList;
    }

    @Override
    public void update(Agency agency) {
    }

    @Override
    public void delete(int id) {

    }
}