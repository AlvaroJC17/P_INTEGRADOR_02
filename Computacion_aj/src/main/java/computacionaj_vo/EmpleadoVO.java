package computacionaj_vo;
// subclase empleado
import operaciones_dao.EmpleadoDAO;

public class EmpleadoVO extends PersonaVO {

	private String cargo;
	private int codigo;
	EmpleadoDAO op = new EmpleadoDAO(); // se instancia para usar el metodo obtenercodigo
	
	//constructores
	public EmpleadoVO() {
		
	}

	public EmpleadoVO(String nombre, String apellido, String cargo, int codigo, int dni) {
		super(nombre, apellido, dni );
		this.cargo = cargo;
		this.codigo = op.obtenerCodigo(); // genera un codigo automaticamente
	}
	
	
	// setters y getters
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	//metodo tostring
	@Override
	public String toString() {
		return super.toString() + "Empleado [cargo=" + cargo + "]";
	}
	
	
	
	
}
