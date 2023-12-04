import javax.swing.*;
import java.awt.*;

public class AgregarSalonGUI extends JFrame {

	private final JTextField noDeSalonTextField;
    private final JTextField aforoTextField;
    GestorClass creador;
    
    public AgregarSalonGUI(GestorClass creador) {
    	this.creador = creador;
    	
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel codigoLabel = new JLabel("No. de salón:");
        noDeSalonTextField = new JTextField(20);

        JLabel facultadLabel = new JLabel("Facultad:");
        String[] facultades = {"Ingenierías", "Ciencias", "Humanidades", "Ciencias sociales", "Negocios", "Salud"};
        JComboBox<String> facultadesMenu = new JComboBox<>(facultades);
        facultadesMenu.setSelectedIndex(0);

        JLabel aforoLabel = new JLabel("Aforo:");
        aforoTextField = new JTextField(20);

        JButton agregarButton = new JButton("Agregar Salón");

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(codigoLabel);
        panel.add(noDeSalonTextField);
        panel.add(facultadLabel);
        panel.add(facultadesMenu);
        panel.add(aforoLabel);
        panel.add(aforoTextField);
        panel.add(agregarButton);

        agregarButton.addActionListener(e -> {

            String selectedFaculty = (String) facultadesMenu.getSelectedItem();
            int noDeSalon = Integer.parseInt(noDeSalonTextField.getText());
            assert selectedFaculty != null;
            String locacion = getString(selectedFaculty, noDeSalon);
            int aforo = Integer.parseInt(aforoTextField.getText());
 
            
            if(creador.insertSalon(locacion, selectedFaculty, aforo)) {
            	JOptionPane.showMessageDialog(this, "Salón agregado exitosamente...");
            } else {
            	JOptionPane.showMessageDialog(this, "Ocurrió un error, el salón ya existe...");
            }

            dispose();
        });

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private static String getString(String selectedFaculty, int noDeSalon) {
        assert selectedFaculty != null;
        
        String facultyAbbreviation;

        switch (selectedFaculty) {
            case "Ingenierías" -> facultyAbbreviation = "IA";
            case "Ciencias" -> facultyAbbreviation = "CN";
            case "Humanidades" -> facultyAbbreviation = "HU";
            case "Ciencias sociales" -> facultyAbbreviation = "CS";
            case "Negocios" -> facultyAbbreviation = "NE";
            case "Salud" -> facultyAbbreviation = "SA";
            default -> throw new IllegalStateException("Unexpected value: " + selectedFaculty);
        }

        return "Edificio " + facultyAbbreviation + " - Aula " + noDeSalon;
    }
}
