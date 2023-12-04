import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuAdmin {
    private final GestorSalon nodosSalon = new GestorSalon();
    private final JPanel cardPanel;
    private final CardLayout cardLayout;

    Mapa mapa = new Mapa();
    GestorClass creador = new GestorClass(mapa);
    GestorSalon gestor = new GestorSalon();

    public MenuAdmin() {
    	creador.crearSalon();
        JFrame frame = new JFrame("Bienvenido al sistema de inscripciones para administrativos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        JPanel menuPanel = new JPanel();
        JButton agregarClaseButton = new JButton("Agregar clase y asignar salón");
        JButton agregarSalonButton = new JButton("Agregar salón");
        JButton mostrar = new JButton("Consulte salones y clases actuales");

        menuPanel.add(agregarClaseButton);
        menuPanel.add(agregarSalonButton);
        menuPanel.add(mostrar);

        cardPanel.add(menuPanel, "Menú");

        agregarClaseButton.addActionListener(e -> {
            new AgregarClaseGUI(creador);
            cardLayout.show(cardPanel, "Asignar salón");
        });

        agregarSalonButton.addActionListener(e -> {
            new AgregarSalonGUI(creador);
            cardLayout.show(cardPanel, "Agregar salón");
        });

        mostrar.addActionListener(e -> {
        	new MostrarSalones(creador.mostrarRoom());
        	new MostrarClases(creador.mostrarClases());
        });

        frame.add(cardPanel);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}