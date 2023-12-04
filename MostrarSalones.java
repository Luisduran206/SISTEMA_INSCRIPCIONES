import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class MostrarSalones extends JFrame {
	
	public MostrarSalones(List<Salon> salones) {
        DefaultTableModel tableModel = new DefaultTableModel();

        tableModel.addColumn("Ubicación");
        tableModel.addColumn("Facultad");
        tableModel.addColumn("Aforo");
        tableModel.addColumn("Horario");

        for (Salon salon : salones) {
            Object[] rowData = {salon.locationSalon, salon.facultad, salon.aforo, "Ver horario"};
            tableModel.addRow(rowData);
        }

        JTable table = new JTable(tableModel);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (col == 3) { 
                    Salon salon = salones.get(row);
                    mostrarHorario(salon);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        setTitle("Tabla de Salones");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); 
        setVisible(true);
    }
	
	private void mostrarHorario(Salon salon) {
        JOptionPane.showMessageDialog(this, builder(salon.horario));
    }
	
	private String builder(Map<String, List<Double>> horario) {
		String sb = null;
		for(int i = 0; i < horario.size(); i++) {
			sb = "Lunes y Miércoles " + horario.get("Lunes Miercoles").toString()
			+ "\nMartes y Jueves " + horario.get("Martes Jueves").toString()
			+ "\nViernes y Sábado " + horario.get("Viernes Sabado").toString();  
		}
		
		return sb;
	}
}