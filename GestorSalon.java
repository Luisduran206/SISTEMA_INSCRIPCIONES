import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class GestorSalon {

	List<Salon> rooms = new ArrayList<>();
	double[] horas = {7.00, 7.30, 8.00, 8.30, 9.00, 9.30, 10.00, 10.30, 11.00, 11.30,
					12.00, 12.30, 13.00, 13.30, 14.00, 14.30, 15.00, 15.30, 16.00, 16.30,
					17.00, 17.30, 18.00, 18.30, 19.00, 19.30, 20.00, 20.30, 21.00};
	
	public GestorSalon(){}
	
	public boolean insertSalon(String locationSalon, String facultad, int aforo) {
		boolean confirmation = true;
		for(int i = 0; i < rooms.size(); i++) {
			if(rooms.get(i).locationSalon.equals(locationSalon)) {
				confirmation = false;
				break;
			} 
		}
		if(confirmation) {
			Map<String, List<Double>> horario = new HashMap<>();
			List<Double> listaHoras = new ArrayList<>();
	        for (double hora : horas) {
	            listaHoras.add(hora);
	        }
			horario.put("Lunes Miercoles", listaHoras);
			horario.put("Martes Jueves", listaHoras);
			horario.put("Viernes Sabado", listaHoras);
			Salon salon = new Salon(locationSalon, facultad, aforo, horario);
			rooms.add(salon);
		}
		return confirmation;
	}
	
	public String find(String facultad, int aforo, String dias, double inicio, double fin) {
	    String confirmation = null;
	    
	    // Busca el mejor aforo según la capacidad deseada
	    int mejorAforo = mejor(aforo);

	    // Obtiene el orden de los edificios según algún criterio (supongo que es lo que hace el método 'orden')
	    String[] escuelas = orden(facultad);

	    // Itera sobre cada edificio
	    for (String edificio : escuelas) {
	        // Itera sobre cada salón en el edificio actual
	        for (Salon salon : rooms) {
	            // Comprueba si el salón pertenece a la facultad actual
	            if (edificio.equals(salon.facultad)) {
	                // Obtiene las horas disponibles para el día especificado
	                List<Double> horas = salon.horario.get(dias);

	                // Verifica si las horas requeridas están disponibles y si el aforo es el mejor
	                if (horas.contains(inicio) && horas.contains(fin) && mejorAforo == salon.aforo) {
	                    confirmation = salon.locationSalon;
	                    update(salon, dias, inicio, fin);
	                    break; // Rompe el bucle ya que hemos encontrado un salón adecuado
	                }
	            }
	        }
	    }

	    return confirmation;
	}

	public void update(Salon salon, String dias, Double inicio, Double fin) {
	    List<Double> horarioDelDia = salon.horario.get(dias);

	    if (horarioDelDia != null) {
	        // Usar Java Stream API para filtrar y actualizar la lista
	        horarioDelDia = horarioDelDia.stream()
	                .filter(hora -> !(hora >= inicio && hora < fin))
	                .collect(Collectors.toList());

	        // Actualizar la lista en el mapa
	        salon.horario.put(dias, horarioDelDia);
	    }
	}
	
	public String[] orden(String facultad) {
		Grafo grafo = new Grafo();
		String[] orden = grafo.ordenar(facultad);
		
		for (int i = 0; i < orden.length / 2; i++) {
            String temp = orden[i];
            orden[i] = orden[orden.length - 1 - i];
            orden[orden.length - 1 - i] = temp;
        }
		return orden;
	}
	
	
	public int mejor(int aforo) {
		 int mejor = 0;
		 for (int i = 0; i < rooms.size(); i++) {
			 int salon = rooms.get(i).aforo;
			 if (salon >= aforo) {
				 if (mejor == 0 || salon < mejor) {
		                mejor = salon;
		            }
		        }
		    }
		  return mejor;
	}
	
	public List<Salon> mostrarSalon(){
		return rooms;
	}
}

class Salon{
	
	String locationSalon;
	String facultad;
	int aforo;
	Map<String, List<Double>> horario;
	
	public Salon(String locationSalon, String facultad, int aforo, Map<String, List<Double>> horario){
		this.locationSalon = locationSalon;  //Edificio IA - Aula 203
		this.facultad = facultad;  //Ingenierías
		this.aforo = aforo;
		this.horario = horario;
	}
}