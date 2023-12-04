import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main {

	public Main() throws InterruptedException {
		String ID = JOptionPane.showInputDialog("Ingrese su ID a continuación...", "Bienvenido");
		String[] digits = ID.split("");
		if(digits.length == 5) {
			MenuAdmin trabajador = new MenuAdmin();
		} else {
			MenuStudent estudiante = new MenuStudent();
		}
	}
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
			try {
				new Main();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
    }
}
