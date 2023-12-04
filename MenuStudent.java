import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuStudent {

    private final JPanel cardPanel;
    private final CardLayout cardLayout;

    Mapa mapa = new Mapa();
    GestorClass creador = new GestorClass(mapa);
    Horario horario = new Horario(creador);

    public MenuStudent() throws InterruptedException {
    	creador.crearSalon();
    	creador.crearClases();
    	
        JFrame frame = new JFrame("SISTEMA INSCRIPCIONES UDLAP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        
        JPanel menuPanel = new JPanel();
        
        JLabel facultadLabel = new JLabel("¿A qué facultad perteneces?");
        String[] facultades = {"Ingenierías", "Ciencias", "Humanidades", "Ciencias sociales", "Negocios", "Salud"};
        JComboBox<String> facultadesMenu = new JComboBox<>(facultades);
        facultadesMenu.setSelectedIndex(0);
        
        JButton agregarClaseButton = new JButton("Agregar clase");
        JButton cancelarClaseButton = new JButton("Cancelar clase");
        JButton mostrar = new JButton("Consulte los cursos ofertados");

        menuPanel.add(facultadLabel);
        menuPanel.add(facultadesMenu);
        menuPanel.add(agregarClaseButton);
        menuPanel.add(cancelarClaseButton);
        menuPanel.add(mostrar);

        cardPanel.add(menuPanel, "Menú");

        agregarClaseButton.addActionListener(e -> {
        	int facultadIndex = facultadesMenu.getSelectedIndex();
            String facultad = switch (facultadIndex) {
                case 0 -> "Ingenierias";
                case 1 -> "Ciencias";
                case 2 -> "Humanidades";
                case 3 -> "Ciencias sociales";
                case 4 -> "Negocios";
                case 5 -> "Salud";
                default -> null;
            };
            new BuscarClaseGUI(creador, horario, mapa, facultad);
            cardLayout.show(cardPanel, "Asignar salón");
        });

        cancelarClaseButton.addActionListener(e -> {
            new EliminarClaseGUI(creador, horario, mapa);
            cardLayout.show(cardPanel, "Asignar salón");
        });
        
        mostrar.addActionListener(e -> {
        	new MostrarClases(creador.mostrarClases());
        });
        
        frame.add(cardPanel);
        frame.setSize(1000, 70);
        frame.setVisible(true);
    }
}