import java.util.Arrays;

public class Grafo {

	public Grafo() {}
	
	public static String[] dijkstra(int[][] grafo, String edificioInicial, String[] nombresEdificios) {
        int nodo = grafo.length;
        int nodoInicial = obtenerIndiceEdificio(edificioInicial, nombresEdificios);

        if (nodoInicial == -1) {
            System.out.println("Edificio inicial no encontrado.");
            return new String[0]; 
        }

        int[] distancia = new int[nodo];
        boolean[] visitado = new boolean[nodo];

        for (int i = 0; i < nodo; i++) {
            distancia[i] = Integer.MAX_VALUE;
            visitado[i] = false;
        }

        distancia[nodoInicial] = 0;

        for (int i = 0; i < nodo - 1; i++) {
            int minDistance = Integer.MAX_VALUE;
            int minIndex = -1;

            for (int j = 0; j < nodo; j++) {
                if (!visitado[j] && distancia[j] < minDistance) {
                    minDistance = distancia[j];
                    minIndex = j;
                }
            }

            visitado[minIndex] = true;

            for (int j = 0; j < nodo; j++) {
                if (!visitado[j] && grafo[minIndex][j] != 0 && distancia[minIndex] != Integer.MAX_VALUE
                        && distancia[minIndex] + grafo[minIndex][j] < distancia[j]) {
                    distancia[j] = distancia[minIndex] + grafo[minIndex][j];
                }
            }
        }

        return mostrarN(distancia, nombresEdificios);
    }

    private static int obtenerIndiceEdificio(String edificio, String[] nombresEdificios) {
        for (int i = 0; i < nombresEdificios.length; i++) {
            if (edificio.equalsIgnoreCase(nombresEdificios[i])) {
                return i;
            }
        }
        return -1; // Devolver -1 si el edificio no se encuentra
    }

    private static String[] mostrarN(int distancia[], String[] nombresEdificios) {
        int n = distancia.length;

        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a, b) -> Integer.compare(distancia[a], distancia[b]));

        String[] edificiosOrdenados = new String[n];

        for (int i = 0; i < n; i++) {
            edificiosOrdenados[i] = nombresEdificios[indices[i]];
        }

        return edificiosOrdenados;
    }
    
    public String[] ordenar(String facultad) {
    	
    	String[] nombresEdificios = {
                "Ingenierias",
                "Ciencias",
                "Salud",
                "Humanidades",
                "Ciencias sociales",
                "Negocios"
            };
    	int[][] grafo = {
                {0, 50, 395, 190, 0, 0},
                {50, 0, 360, 180, 160, 0},
                {395, 360, 0, 0, 240, 242},
                {190, 180, 0, 0, 275, 300},
                {0, 160, 240, 275, 0, 50},
                {0, 0, 242, 300, 50, 0}
            };
    	String[] a = dijkstra(grafo, facultad, nombresEdificios);
    	return a;
    }
}
