package operaciones_dao;


public interface IOperacionesDB {


	public void ingresarNuevo(Object a); // ingresar empleado o articulo
	
	public void eliminar(int codigo); // elimina empleado o articulo
	
	public void agregarStock(Object b); // suma stock a los articulos
	
	public void vender(Object c, int codigo); // simula una venta y renta la cantidad de stock
	
	public void verListadoArticulosYUsuarios(); // muestra la lista de empleados y articulos
	
	public void actualizarArticulosYUsuarios(Object elemento, int codigo, int seleccion); // actualiza los datos de los empleados o articulos
	
	public boolean verArticuloYUsuario(int codigo); // muestra todo los datos del usuario o articulo del cual ingresemos el codigo
	
	public void verVentas(); // muestra todas las ventas realizadas
	
	public void eliminarVenta(int codigo); // elimina la venta que seleccionemos y devuelve al stock los articulos
	
	public int obtenerCodigo(); // crea codigos automaticamente a los empleados y articulos
	
	
	
	

	

}

