package windows;

import db.entities.Service;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import listeners.AddClientListener;

public class principal extends JFrame {
    private final int TEXT_FIELD_COLUMNS = 2;

    private AddClientListener listener;

    private JPanel contenidoPanel;
    private JPanel subPanel1;
    private JPanel subPanel2;

    private JButton nextButton;
    private JButton insertButton;
    private JButton watchButton;

    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JLabel modelLabel;
    private JLabel registrationLabel;
    private JLabel serviceLabel;

    private Service service;
    private  Choice servicios;

    public principal(Service service) {

        this.service = service;
        contenidoPanel = new JPanel(new GridLayout(1, 2));
        subPanel1 = new JPanel();
        subPanel1.setBorder(BorderFactory.createTitledBorder("Cliente"));
        subPanel1.setLayout(new GridLayout(14,2));
        subPanel2 = new JPanel();
        subPanel2.setBorder(BorderFactory.createTitledBorder("Servicios"));
        subPanel2.setLayout(new GridLayout(8,2));

        initializeText();
        initializeLabel();
        initializeButton();
        initializedesplega();

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    
                

            }

        });
        
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    
                
            }

        });

        watchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    
                

            }

        });

        setUp();
    }

    public void setAddClientListener(AddClientListener listener){
        this.listener = listener;
    }

    private void setUp() {

        subPanel1.add(nameLabel);
        subPanel1.add(surnameLabel);
        subPanel1.add(emailLabel);
        subPanel1.add(phoneLabel);
        subPanel1.add(modelLabel);
        subPanel1.add(registrationLabel);
        subPanel1.add(insertButton);

        subPanel2.add(serviceLabel);
        subPanel2.add(servicios); 

        subPanel2.add(nextButton);
        subPanel2.add(watchButton);
        

        contenidoPanel.add(subPanel1);
        contenidoPanel.add(subPanel2);
        add(contenidoPanel);

        setTitle("Inicio");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initializeButton() {
        nextButton = new JButton("Añadir Servico");
        watchButton = new JButton("Ver Servico");
        insertButton= new JButton("Añadir Cliente");
    }

    private void initializeText() {

    }

    private void initializeLabel() {
        nameLabel = new JLabel("Nombre: "+service.getName());
        surnameLabel = new JLabel("Apellido: "+ service.getSurname());
        emailLabel = new JLabel("Correo: "+ service.getEmail());
        phoneLabel = new JLabel("Telefono: "+service.getPhone());
        modelLabel = new JLabel("Modelo: "+ service.getModel());
        registrationLabel = new JLabel("Matricula: "+service.getRegistration());
        serviceLabel = new JLabel(" Lista de Servicios: ");

    }
    private void initializedesplega(){
        servicios = new Choice();
        servicios.add(""); 
    }
   
    public void run(){
        setVisible(true);
    }
    
}
