import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Mapa {

	private JLabel labelConImagen;
    private JPanel panelBotones;
	private JLabel[] edificios = new JLabel[6];
	JFrame ventana;
	 
	public Mapa() {
		 SwingUtilities.invokeLater(() -> {
			 ventana = new JFrame("MAPA DEL CAMPUS");
	         ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	         ventana.setSize(500, 500);
	            
	         FondoPanel fondoPanel = new FondoPanel();
	         ventana.setContentPane(fondoPanel);
		 
	         labelConImagen = new JLabel();
	         labelConImagen.setLayout(null);
	         ventana.add(labelConImagen);

	         panelBotones = new JPanel();
	         panelBotones.setOpaque(false);
	         labelConImagen.add(panelBotones);
	            
	         edificios[0] = agregarPunto("IA", 10, 10, Color.GRAY);
	         edificios[1] = agregarPunto("CN", 30, 10, Color.GRAY);
	         edificios[2] = agregarPunto("HU", 50, 10, Color.GRAY);
	         edificios[3] = agregarPunto("CS", 70, 10, Color.GRAY);
	         edificios[4] = agregarPunto("NE", 90, 10, Color.GRAY);
	         edificios[5] = agregarPunto("SA", 110, 10, Color.GRAY);
	         
	         ventana.setVisible(true);
		 }); 
	 }
	 
	 public void setColor(int index) throws InterruptedException {
		 for(int i = 0; i < edificios.length; i++) {
			 edificios[i].setBackground(Color.GRAY);
	     }
		 edificios[index].setBackground(Color.GREEN);
	}
	    
	    private JLabel agregarPunto(String nombre, int x, int y, Color color) {
	        JLabel punto = new JLabel(nombre);
	        punto.setBounds(x, y, 10, 10);
	        punto.setBackground(color);
	        punto.setOpaque(true);
	        //labelConImagen.add(punto);
	        ventana.add(punto);
	        return punto;
	    }
	    
	  public void indice(String facultad) throws InterruptedException {
		  int index = switch (facultad) {
              case "IA" -> 0;
              case "CN" -> 1;
              case "HU" -> 2;
              case "CS" -> 3;
              case "NE" -> 4;
              case "SA" -> 5;
              default -> throw new IllegalArgumentException("Unexpected value: " + facultad);
          };
		  setColor(index);
	  }
}

class FondoPanel extends JPanel {
    private BufferedImage imagenFondo;

    public FondoPanel() {
        try {
            imagenFondo = ImageIO.read(new File("/Users/luisduranflores/Downloads/UDLAP/SEGUNDO SEMESTRE/P. ORIENTADA A OBJETOS/PROGRAMAS/Y/src/imagenes/Mapa.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}