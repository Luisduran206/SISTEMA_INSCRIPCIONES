import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MostrarClases extends JFrame {
	//String locationSalon, String facultad, String nombre, int aforo, String dias, double inicio, double fin
	
	public MostrarClases(List<Class> clases) {
		
        DefaultTableModel tableModel = new DefaultTableModel();

        tableModel.addColumn("Ubicación");
        tableModel.addColumn("Facultad");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Sección");
        tableModel.addColumn("Aforo");
        tableModel.addColumn("Días");
        tableModel.addColumn("De:");
        tableModel.addColumn("Hasta:");

        for (Class clase : clases) {
            Object[] rowData = {clase.locationSalon, clase.facultad, clase.nombre, clase.seccion, clase.aforo, clase.dias, clase.inicio, clase.fin};
            tableModel.addRow(rowData);
        }

        JTable table = new JTable(tableModel);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        setTitle("Todos tus cursos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); 
        setVisible(true);
    }
}