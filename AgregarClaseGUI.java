import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class AgregarClaseGUI extends JFrame implements ActionListener{
    //Swing components and declarations
    JLabel plazasLabel, facultadLabel, seccionLabel, diasLabel, inicioLabel, finLabel, nombreLabel;
    JTextField plazasTextField, nombreTextfield, seccionTextfield;
    JButton asignar;
    Boolean proyectorBool;
    JComboBox<String> facultadesMenu;
    JComboBox<String> diasMenu;
    JComboBox<String> inicioMenu;
    JComboBox<String> finMenu;
    GestorClass creador;

    String[] facultades = {"Ingenierías", "Ciencias", "Humanidades", "Ciencias sociales", "Negocios", "Salud"};
    String[] dias = {"Lunes Miercoles", "Martes Jueves", "Viernes Sabado"};
    String[] horasInicio = {"7.00", "7.30", "8.00", "8.30", "9.00", "9.30", "10.00", "10.30", "11.00", "11.30",
			"12.00", "12.30", "13.00", "13.30", "14.00", "14.30", "15.00", "15.30", "16.00", "16.30",
			"17.00", "17.30", "18.00", "18.30", "19.00", "19.30", "20.00", "20.30", "21.00"};
    String[] horasFin = {"7.00", "7.30", "8.00", "8.30", "9.00", "9.30", "10.00", "10.30", "11.00", "11.30",
			"12.00", "12.30", "13.00", "13.30", "14.00", "14.30", "15.00", "15.30", "16.00", "16.30",
			"17.00", "17.30", "18.00", "18.30", "19.00", "19.30", "20.00", "20.30", "21.00"};

    public AgregarClaseGUI(GestorClass creador) {
    	//creador.crear();
    	this.creador = creador;
    	
        Container container = getContentPane();
        container.setLayout(new FlowLayout());

        nombreLabel = new JLabel("¿Cuál es el nombre de la clase?");
        container.add(nombreLabel);
        nombreTextfield = new JTextField(15);
        container.add(nombreTextfield);

        seccionLabel = new JLabel("¿Cuál es la sección de la clase?");
        container.add(seccionLabel);
        seccionTextfield = new JTextField(5);
        container.add(seccionTextfield);
        
        plazasLabel = new JLabel("¿Cuántos alumnos hay en la clase?");
        container.add(plazasLabel);
        plazasTextField = new JTextField(5);
        container.add(plazasTextField);

        facultadLabel = new JLabel("Escoge la facultad a la que pertenece la clase");
        container.add(facultadLabel);
        facultadesMenu = new JComboBox<>(facultades);
        container.add(facultadesMenu);
        
        diasLabel = new JLabel("Días:");
        container.add(diasLabel);
        diasMenu = new JComboBox<>(dias);
        container.add(diasMenu);

        // Create checkbox
        inicioLabel = new JLabel("Hora de inicio: ");
        container.add(inicioLabel);
        inicioMenu = new JComboBox<>(horasInicio);
        container.add(inicioMenu);
        
        finLabel = new JLabel("Hora de fin: ");
        container.add(finLabel);
        finMenu = new JComboBox<>(horasFin);
        container.add(finMenu);

        asignar = new JButton("Guardar datos y asignar aula");
        container.add(asignar);

        asignar.addActionListener(this);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(760, 170);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        // if the user closes the window go back to the main menu

        if (event.getSource() == asignar){
            //get school
            int facultadIndex = facultadesMenu.getSelectedIndex();
            String facultadString = switch (facultadIndex) {
                case 0 -> "Ingenierias";
                case 1 -> "Ciencias";
                case 2 -> "Humanidades";
                case 3 -> "Ciencias sociales";
                case 4 -> "Negocios";
                case 5 -> "Salud";
                default -> null;
            };

            // get class code
            String nombreClase = nombreTextfield.getText();

            int diasIndex = diasMenu.getSelectedIndex();
            String dias = switch (diasIndex) {
                case 0 -> "Lunes Miercoles";
                case 1 -> "Martes Jueves";
                case 2 -> "Viernes Sabado";
                default -> null;
            };
            
            String sec = seccionTextfield.getText();
            int seccion = Integer.parseInt(sec);
            
            String num = plazasTextField.getText(); 
            int aforo = Integer.parseInt(num);
            
            int inicio = inicioMenu.getSelectedIndex();
            double horaInicio = Double.parseDouble(horasInicio[inicio]);
            
            int fin = finMenu.getSelectedIndex();
            double horaFin = Double.parseDouble(horasFin[fin]);

            if(facultadString != null && nombreClase != null && aforo > 0 && horaInicio < horaFin) {
                String confirmacion = null;
				try {
					confirmacion = creador.insertClase(facultadString, nombreClase, seccion, aforo, dias, horaInicio, horaFin);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
                JOptionPane.showMessageDialog(this, confirmacion);
            } else {
            	JOptionPane.showMessageDialog(this, "Verifique una vez más los datos de su clase, \nocurrió un error...");
            }

            dispose();
        }
    }
}