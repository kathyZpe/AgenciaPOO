package windows;

import db.dao.AgencyDao;
import db.dao.PartDao;
import db.entities.Agency;
import db.entities.Part;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.*;
import java.util.List;

public class ProviderWindow extends JFrame implements WindowListener {
    private List<Agency> agencyList;
    private List<Part> partList;
    private List<Part> partsSelected;

    private AgencyDao agencyDao;
    private PartDao partDao;

    private JPanel mainPanel;

    private DefaultListModel<Agency> agencyModel;
    private DefaultListModel<Part> partsModel;
    private DefaultListModel<Part> usedPartsModel;

    private JList<Agency> agencyJList;
    private JList<Part> partJList;
    private JList<Part> partsSelectedJList;

    private JTextField searchTextField;

    private Agency selectedAgency;

    public ProviderWindow(){
        agencyModel = new DefaultListModel<>();
        partsModel = new DefaultListModel<>();
        usedPartsModel = new DefaultListModel<>();

        agencyList = new ArrayList<>();
        partList = new ArrayList<>();
        partsSelected = new ArrayList<>();

        agencyDao = new AgencyDao();
        partDao = new PartDao();

        mainPanel = new JPanel();

        initializeButtons();
        initializeList();
        initializeTextField();

        searchTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                JTextField textField = ((JTextField) e.getComponent());
                if(!textField.getText().isEmpty()){
                    if(Character.isLetter(e.getKeyChar())) {
                        agencyList = agencyDao.getLikeName(textField.getText());
                        agencyModel.clear();
                        for (int i = 0; i < agencyList.size(); i++) {
                            agencyModel.addElement(agencyList.get(i));
                        }
                    }
                }else{
                    agencyList = agencyDao.getAll();
                    agencyModel.clear();
                    for (int i = 0; i < agencyList.size(); i++) {
                        agencyModel.addElement(agencyList.get(i));
                    }
                }
            }
        });

        agencyJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedAgency = agencyJList.getSelectedValue();
                partList = partDao.getAllByAgency(selectedAgency);

                updatePartList();
            }
        });

        setUp();
    }

    private void setUp(){
        mainPanel.add(partJList);

        add(mainPanel);
        add(searchTextField, BorderLayout.NORTH);
        add(agencyJList, BorderLayout.WEST);

        setTitle("Proveedor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(600, 500);
        addWindowListener(this);
    }

    private void initializeButtons(){

    }

    private void initializeList(){
        agencyJList = new JList<>(agencyModel);
        partJList = new JList<>(partsModel);
        partsSelectedJList = new JList<>(usedPartsModel);

        agencyList = agencyDao.getAll();

        for (int i = 0; i < agencyList.size(); i++) {
            agencyModel.addElement(agencyList.get(i));
        }

        agencyJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        agencyJList.setVisibleRowCount(-1);

        partJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        partJList.setVisibleRowCount(-1);

        partsSelectedJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        partsSelectedJList.setVisibleRowCount(-1);
    }

    private void initializeTextField(){
        searchTextField = new JTextField(4);
    }

    private void updatePartList(){
        partsModel.clear();
        for (int i = 0; i < partList.size(); i++) {
            partsModel.addElement(partList.get(i));
        }
    }

    public void run(){
        setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        agencyDao.close();
        partDao.close();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
