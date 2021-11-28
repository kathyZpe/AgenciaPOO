import db.dao.AgencyDao;
import db.dao.ServiceDao;
import db.entities.Agency;
import db.entities.Service;
import listeners.SQLListener;

import java.sql.Date;
import java.util.List;

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
        Service getService = serviceDao.get(1); // Obtener servicio por ID
        System.out.println("Entregado:" + getService.isDelivered());

        getService.setDelivered(true); // Establece como entrago el servicio
        serviceDao.update(getService);

        getService=serviceDao.get(1);
        System.out.println("Entregado:" + getService.isDelivered());

        serviceDao.delete(1);

        getService = serviceDao.get(1);
        if(getService == null){
            System.out.println("Servicio borrado");
        }

        serviceDao.close(); // Se cierra conexion con base de datos

        // Ejemplo de funcionamiento de AgencyDao
        AgencyDao agencyDao = new AgencyDao();

        Agency newAgency1 = new Agency("Nissan"); // Creacion de nuevas agencias
        Agency newAgency2 = new Agency("Miaussan");
        Agency newAgency3 = new Agency("Chevrolet");

        agencyDao.save(newAgency1);
        agencyDao.save(newAgency2);
        agencyDao.save(newAgency3);

        List<Agency> agencyList = agencyDao.getAll(); // Obtener todas las agencias
        for (int i = 0; i < agencyList.size(); i++) {
            System.out.println("No. "+ i + " Nombre:" + agencyList.get(i).getName() + " Id:"+agencyList.get(i).getId());
        }

        agencyList = agencyDao.getLikeName("ssan"); // Obtener todas las agencias que contienen "ssan"
        for (int i = 0; i < agencyList.size(); i++) {
            System.out.println("No. "+ i + " Nombre:" + agencyList.get(i).getName() + " Id:"+agencyList.get(i).getId());
        }
    }
}
