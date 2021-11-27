import db.dao.ServiceDao;
import db.entities.Service;
import listeners.SQLListener;

import java.sql.Date;

public class App {
    public static void main(String[] args) {

        // Ejemplo de funcionamiento de clase DAO;
        long now = System.currentTimeMillis();
        Date dateNow = new Date(now);
        Date dateTomorrow = Date.valueOf("2020-12-26");
        // Creacion de nuevo de servicio
        Service service = new Service(0, dateNow, dateTomorrow, "Gamaliel", "Garcia", "Nissa", "0000XXX",
                "5951140476", "egamagz@hotmail.com");

        ServiceDao serviceDao = new ServiceDao();
        serviceDao.setSQLListener(new SQLListener() {
            @Override
            public void onSQLException(String msg) {
                // Listener para cuando haya alguna exception con la base de datos
                // devuelve lo que es un mensaje relacionado con el error
                System.out.println(msg);
            }
        });
        serviceDao.save(service);
        Service getService = serviceDao.get(1);
        System.out.println(getService.isDelivered());

        getService.setDelivered(true);
        serviceDao.update(getService);

        getService=serviceDao.get(1);
        System.out.println(getService.isDelivered());

        serviceDao.delete(1);

        getService = serviceDao.get(1);
        if(getService == null){
            System.out.println("Servicio borrado");
        }

        serviceDao.close(); // Se cierra conexion con base de datos
    }
}
