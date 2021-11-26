package db.dao;

import db.SQLConnection;
import db.entities.Service;

import java.util.List;
import java.util.Optional;

public class ServiceDao extends SQLConnection implements Dao<Service>{
    public ServiceDao() {
        super();
    }

    @Override
    public void save(Service service, String sql) {

    }

    @Override
    public Optional<Service> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Service> getAll() {
        return null;
    }

    @Override
    public void update(Service service) {

    }

    @Override
    public void delete(Service service) {

    }
}
