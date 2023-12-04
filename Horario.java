import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Horario extends JFrame {

    Container container;
    JTextArea horarioTextArea;
    GestorClass creador;

    public Horario(GestorClass creador) {
        this.creador = creador;

        container = getContentPane();
        container.setLayout(new BorderLayout());

        horarioTextArea = new JTextArea();
        horarioTextArea.setEditable(false); // Para evitar que el usuario pueda editar el contenido
        horarioTextArea.setLineWrap(true); // Activar el ajuste de línea automático
        JScrollPane scrollPane = new JScrollPane(horarioTextArea); // Agregar un JScrollPane para manejar el desplazamiento

        container.add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setTitle("TU HORARIO");
        setVisible(true);
    }

    public void setText() {
        List<Class> inscritas = creador.mostrarInscritas();
        String horario = sb(inscritas);
        horarioTextArea.setText(horario);
    }

    public String sb(List<Class> inscritas) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < inscritas.size(); i++) {
            sb.append(inscritas.get(i).nombre).append(" - Sec. ").append(inscritas.get(i).seccion)
                    .append(" en ").append(inscritas.get(i).locationSalon)
                    .append("\nLos días: ").append(inscritas.get(i).dias)
                    .append(". De: ").append(inscritas.get(i).inicio)
                    .append(" a: ").append(inscritas.get(i).fin)
                    .append("\n\n");
        }
        return sb.toString();
    }
}

