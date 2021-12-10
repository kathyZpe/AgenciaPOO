import db.dao.ServiceDao;
import db.entities.Service;
import windows.ServiceWindow;

import java.sql.Date;

public class App {
    public static void main(String[] args) {
        ServiceDao serviceDao = new ServiceDao();

        long now = System.currentTimeMillis();
        Date dateNow = new Date(now);
        Date dateTomorrow = Date.valueOf("2021-12-10");
        Service service = new Service(0, dateNow, dateTomorrow, "Gamaliel", "Garcia", "Nissa", "0000XXX",
            "5951140476", "egamagz@hotmail.com"); // <- Este servicio debe tener id para borrarse


        ServiceWindow serviceWindow = new ServiceWindow(service);
        serviceWindow.setServiceWindowListener(new ServiceWindow.ServiceWindowListener() {
            @Override
            public void onDelete() {
                System.out.println("Eliminado");
            }
        });
        serviceWindow.run();
    }
}
