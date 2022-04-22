package ficheros;

public class Persona implements Comparable<Persona>{

	private int ID_persona;
	private String nombre;
	private String apellidos;
	
	public Persona(int iD_persona, String nombre, String apellidos) {
		super();
		ID_persona = iD_persona;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public int getID_persona() {
		return ID_persona;
	}
	public void setID_persona(int iD_persona) {
		ID_persona = iD_persona;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@Override
	public int compareTo(Persona p) {
		// TODO Auto-generated method stub
		return this.nombre.compareTo(p.getNombre());
	}

	@Override
	public String toString() {
		return ID_persona + " " + nombre + " " + apellidos+"\n";
	}
	
	
}
