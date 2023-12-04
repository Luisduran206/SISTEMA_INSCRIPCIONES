import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BuscarClaseGUI extends JFrame implements ActionListener{

	GestorClass creador;
	Horario horario;
	Mapa mapa;
	JLabel claseLabel, ofertaLabel;
	JTextField nombreTextfield, seccionTextField;
	JButton inscribir, buscar;
	String facultad;
	List<Class> cursos;
	String curso;
	
	public BuscarClaseGUI(GestorClass creador, Horario horario, Mapa mapa, String facultad) {
		this.creador = creador;
		this.horario = horario;
		this.mapa = mapa;
		this.facultad = facultad;
		
		Container container = getContentPane();
        container.setLayout(new FlowLayout());

        claseLabel = new JLabel("¿Qué clase deseas buscar?");
        container.add(claseLabel);
        nombreTextfield = new JTextField(15);
        container.add(nombreTextfield);

        buscar = new JButton("Buscar");
        container.add(buscar);
        buscar.addActionListener(this);
        
        ofertaLabel = new JLabel("Ingresa el número de sección que desea inscribir:");
        container.add(ofertaLabel);
        seccionTextField = new JTextField(5);
        container.add(seccionTextField);

        inscribir = new JButton("Inscribir");
        container.add(inscribir);
        inscribir.addActionListener(this);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(2000, 150);
        setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == buscar){
			curso = nombreTextfield.getText();
			cursos = creador.explorarClases(facultad, curso);
	        if (cursos != null) {
	        	new MostrarClases(cursos);
	        } else {
	        	JOptionPane.showMessageDialog(null, "No se encontró dicho curso, intente de nuevo...");
	        }
		}
		
		if(event.getSource() == inscribir) {
			String sec = seccionTextField.getText();
			int seccion = Integer.parseInt(sec);
			
			if(curso != null && seccion > 0) {
				String confirmation = null;
				try {
					confirmation = creador.inscribir(cursos, seccion);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				horario.setText();
				JOptionPane.showMessageDialog(null, "CLASE INSCRITA: \n" + confirmation);
			}
		}
	}
}
