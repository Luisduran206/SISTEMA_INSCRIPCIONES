import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EliminarClaseGUI extends JFrame implements ActionListener{
	
	GestorClass creador;
	Horario horario;
	Mapa mapa;
	JLabel borrarEtiqueta;
	JComboBox<String> clases;
	JButton eliminarButton;
	
	public EliminarClaseGUI(GestorClass creador, Horario horario, Mapa mapa) {
		this.creador = creador;
		this.horario = horario;
		this.mapa = mapa;
		
		Container container = getContentPane();
        container.setLayout(new FlowLayout());

        // Crear una etiqueta
        borrarEtiqueta = new JLabel("Selecciona la clase que deseas eliminar de tu horario:");
        clases = new JComboBox<>(clasesInscritas());
        eliminarButton = new JButton("Eliminar clase");
        eliminarButton.addActionListener(this);

        // Agregar la etiqueta y el JComboBox al contenedor
        container.add(borrarEtiqueta);
        container.add(clases);
        container.add(eliminarButton);

        // Configuración de la ventana
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(450, 150);
        setTitle("Eliminar clase");
        setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == eliminarButton){
			int facultadIndex = clases.getSelectedIndex();
			String confirmation = creador.eliminar(facultadIndex);
			horario.setText();
			JOptionPane.showMessageDialog(null, "CLASE ELIMINADA: \n" + confirmation);
		}
		
	}
	
	public String[] clasesInscritas() {
		List<Class> inscritas = creador.mostrarInscritas();
		String[] arrayIns = new String[inscritas.size()];
		for(int i = 0; i < inscritas.size(); i++) {
			arrayIns[i] = inscritas.get(i).nombre;
		}
		return arrayIns; 
	}
}
