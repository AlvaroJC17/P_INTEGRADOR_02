package computacionaj_vo;

//Superclase abstracta
abstract public class PersonaVO {

	//Atributos
	private String nombre;
	private String apellido;
	private int dni;
	
	//Constructores
	public PersonaVO() {
		
	}
	
	public PersonaVO(String nombre, String apellido, int dni) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	}

	//setters y getters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	
	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	//Metodo tostring
	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + ", codigo="  + "]";
	}
	
	
	
	
}
