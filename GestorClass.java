import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GestorClass {

	Mapa mapa;
	List<Class> clases = new ArrayList<>();
	List<Class> inscritas = new ArrayList<>();
	GestorSalon salones = new GestorSalon();
	
	public GestorClass(Mapa mapa) {
		this.mapa = mapa;
	}
	
	public String insertClase(String facultad, String nombre, int seccion, int aforo,  String dias, double inicio, double fin) throws InterruptedException {
		String confirmacion = null;
		String locationSalon = salones.find(facultad, aforo, dias, inicio, fin);
		if(locationSalon != null) {
			Class clase = new Class(locationSalon, facultad, nombre, seccion, aforo, dias, inicio, fin);
			clases.add(clase);
			//String facu = extraerFacultad(clase.locationSalon);
			//mapa.indice(facu);
			confirmacion = "Su clase fue agregada: " + nombre + " en " + locationSalon;
		} else {
			confirmacion = "No se encontró un aula adecuada.";
		}
		return confirmacion;
	}
	
	public boolean insertSalon(String locationSalon, String facultad, int aforo) {
		boolean confirmation = salones.insertSalon(locationSalon, facultad, aforo);
		return confirmation;
	}
	
	public List<Class> explorarClases(String facultad, String curso) {
		List<Class> secciones = new ArrayList<Class>();
		for(int i = 0; i < clases.size(); i++) {
			if(clases.get(i).nombre.equals(curso)) {
				secciones.add(clases.get(i));
			}
		}
		return secciones;
	} 
	
	/*public String completa(String location) {
		String temp = extraerFacultad(location);
		String facu = switch (temp) {
        case "IA" -> "Ingenieras";
        case "CN" -> "Ciencia";
        case "HU" -> "Humanidades";
        case "CS" -> "Ciencias sociales";
        case "NE" -> "Negocios";
        case "SA" -> "Salud";
        default -> throw new IllegalArgumentException("Unexpected value");
		};
		return facu;
	}*/
	
	public String inscribir(List<Class> cursos, int seccion) throws InterruptedException {
		String confirmation = null;
		for(int i = 0; i < cursos.size(); i++) {
			if(cursos.get(i).seccion == seccion) {
				inscritas.add(cursos.get(i));
				confirmation = sb(cursos.get(i));
				String facu = extraerFacultad(cursos.get(i).locationSalon);
				mapa.indice(facu);
				break;
			}
		}
		return confirmation;
	}
	
	private static String extraerFacultad(String input) {
        int inicio = input.indexOf("Edificio") + "Edificio".length();
        int fin = input.indexOf(" - Aula");

        if (inicio >= 0 && fin >= 0 && inicio < fin) {
            return input.substring(inicio, fin).trim();
        } else {
            return "Formato no válido";
        }
    }
	
	public String eliminar(int index) {
		String confirmation = null;
		confirmation = sb(inscritas.get(index));
		inscritas.remove(index);
		return confirmation;
	}
	
	public String sb(Class curso) {
		String confirmation = curso.nombre + " en " + curso.locationSalon
				+ " lo días " + curso.dias + ", de: " + curso.inicio + " a: "
				+ curso.fin + ".";
		return confirmation;
	}
	
	public List<Class> mostrarInscritas(){
		return inscritas;
	}
	
	public List<Salon> mostrarRoom(){
		List<Salon> tabla1 = salones.mostrarSalon();
		return tabla1;
	}
	
	public List<Class> mostrarClases(){
		return clases;
	}
	
	public void crearSalon() {
		salones.insertSalon("Edificio SA - Aula 201", "Salud", 18);
		salones.insertSalon("Edificio SA - Aula 202", "Salud", 20);
		salones.insertSalon("Edificio SA - Aula 203", "Salud", 22);
		salones.insertSalon("Edificio SA - Aula 106", "Salud", 25);
		salones.insertSalon("Edificio SA - Aula 205", "Salud", 20);
		salones.insertSalon("Edificio SA - Aula 206", "Salud", 25);
		salones.insertSalon("Edificio IA - Aula 101", "Ingenierias", 40);
		salones.insertSalon("Edificio IA - Aula 209", "Ingenierias", 40);
		salones.insertSalon("Edificio IA- Aula 214", "Ingenierias", 36);
		salones.insertSalon("Edificio IA - Aula 104", "Ingenierias", 50);
		salones.insertSalon("Edificio IA - Aula 105", "Ingenierias", 80);
		salones.insertSalon("Edificio IA - Aula 206", "Ingenierias", 40);
		salones.insertSalon("Edificio CN - Aula 101", "Ciencias", 30);
		salones.insertSalon("Edificio CN - Aula 202", "Ciencias", 24);
		salones.insertSalon("Edificio CN - Aula 103", "Ciencias", 20);
		salones.insertSalon("Edificio CN - Aula 221", "Ciencias", 32);
		salones.insertSalon("Edificio CN - Aula 235", "Ciencias", 26);
		salones.insertSalon("Edificio CN - Aula 220", "Ciencias", 35);
		salones.insertSalon("Edificio HU - Aula 201", "Humanidades", 30);
		salones.insertSalon("Edificio HU - Aula 102", "Humanidades", 16);
		salones.insertSalon("Edificio HU - Aula 203", "Humanidades", 25);
		salones.insertSalon("Edificio HU - Aula 204", "Humanidades", 42);
		salones.insertSalon("Edificio HU - Aula 205", "Humanidades", 42);
		salones.insertSalon("Edificio HU - Aula 206", "Humanidades", 30);
		salones.insertSalon("Edificio CS - Aula 101", "Ciencias Sociales", 28);
		salones.insertSalon("Edificio CS- Aula 102", "Ciencias Sociales", 55);
		salones.insertSalon("Edificio CS - Aula 103", "Ciencias Sociales", 32);
		salones.insertSalon("Edificio CS - Aula 104", "Ciencias Sociales", 25);
		salones.insertSalon("Edificio CS - Aula 105", "Ciencias Sociales", 40);
		salones.insertSalon("Edificio CS - Aula 106", "Ciencias Sociales", 35);
		salones.insertSalon("Edificio NE - Aula 201", "Negocios", 20);
		salones.insertSalon("Edificio NE - Aula 202", "Negocios", 20);
		salones.insertSalon("Edificio NE - Aula 103", "Negocios", 20);
		salones.insertSalon("Edificio NE - Aula 104", "Negocios", 30);
		salones.insertSalon("Edificio NE - Aula 105", "Negocios", 15);
		salones.insertSalon("Edificio NE - Aula 106", "Negocios", 40);
	}
	
	public void crearClases() throws InterruptedException {
		insertClase("Ingenierias", "Estructuras", 1, 31, "Lunes Miercoles", 7.0, 8.30);
		insertClase("Ingenierias", "Estructuras", 3, 25, "Lunes Miercoles", 10.00, 11.30);
		insertClase("Ingenierias", "Estructuras", 2, 50, "Lunes Miercoles", 7.0, 8.30);
		insertClase("Ingenierias", "Programacion", 1, 40, "Lunes Miercoles", 10.00, 11.30);
	}
}

class Class {
	
	String locationSalon;
    String facultad;
    String nombre;
    int seccion;
    int aforo;
    String dias;
    double inicio;
    double fin;
    
    public Class(String locationSalon, String facultad, String nombre, int seccion, int aforo, String dias, double inicio, double fin) {
    	this.locationSalon = locationSalon;
    	this.facultad = facultad;
    	this.nombre = nombre;
    	this.seccion = seccion;
    	this.aforo = aforo;
    	this.dias = dias;
    	this.inicio = inicio;
    	this.fin = fin;
    }
}