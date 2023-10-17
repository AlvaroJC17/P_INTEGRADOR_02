package computacionaj_vo;
//Clase para los articulos
import operaciones_dao.ArticulosDAO;

public class ArticulosVO {

	//Atributos
	
	private String nombre;
	private double precio;
	private int codigo;
	private int stock;
	private String descripcion;
	ArticulosDAO op = new ArticulosDAO(); // se instancia la clase ArticulosDAO pera usar el metodo obtenercodigo
	
	
	//Constructores
	public ArticulosVO() {
		
	}
	
	
	public ArticulosVO(int codigo, int stock) {
		this.codigo = codigo;
		this.stock = stock;
	}
	
	public ArticulosVO(double precio, int codigo) {
		this.codigo = codigo;
		this.precio = precio;
	}
	
	
	public ArticulosVO(String nombre, double precio, int codigo,  int stock, String descripcion) {
		
		this.nombre = nombre;
		this.precio = precio;
		this.codigo = op.obtenerCodigo(); // genera un nuevo codigo para cada instancia del objeto, asi el codigo no es manipulado por el usuario
		this.stock = stock;
		this.descripcion = descripcion;
	}
		
	//setters y getters 
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public double getPrecio() {
		return this.precio;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getStock() {
		return this.stock;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}

	//metodo tostring
	@Override
	public String toString() {
		return "Articulos [nombre=" + nombre + ", precio=" + precio + ", codigo=" + codigo + ", stock=" + stock + ", descripcion=" + descripcion + "]";
	}
	
	
}
