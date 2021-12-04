import listeners.AddClientListener;
import windows.AddClientWindow;

public class App {
    public static void main(String[] args) {
        AddClientWindow window = new AddClientWindow();
        window.setAddClientListener(new AddClientListener() {
            @Override
            public void OnCancel() {
                System.out.println("Cerrada la ventana");
            }

            @Override
            public void OnAdd(String name, String surname, String model, String registration, String phone, String email) {
                System.out.println(name);
                System.out.println(surname);
                System.out.println(model);
                System.out.println(registration);
                System.out.println(phone);
                System.out.println(email);
            }
        });
        window.run();
    }
}
