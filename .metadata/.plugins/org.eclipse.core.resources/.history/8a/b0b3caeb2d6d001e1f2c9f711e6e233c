package operaciones_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import computacionaj_vo.EmpleadoVO;
import dbconnection_ds.ConnectionDS;

public class EmpleadoDAO implements IOperacionesDB {
	
	private Connection conexion;
	private String sql;
	private PreparedStatement stmt;

	public void ingresarNuevo(Object a) {
		
		EmpleadoVO p = (EmpleadoVO)a;
		String nombre;
		String nombre2 = p.getNombre();
		String apellido = p.getApellido();
		String cargo = p.getCargo();
		try {
			conexion = ConnectionDS.ConectarDB();
			sql = "SELECT * FROM empleados WHERE codigo = ?";
			stmt = conexion.prepareStatement(sql);
			stmt.setInt(1, p.getCodigo());
			ResultSet datos = stmt.executeQuery();

			if (!datos.next()) {
				// El artículo no existe, insertarlo
				conexion.setAutoCommit(false);
				sql = "INSERT INTO empleados (nombre, apellido, cargo, codigo, dni) VALUES (?, ?, ?, ?, ?)";
				stmt = conexion.prepareStatement(sql);
				stmt.setString(1, p.getNombre());
				stmt.setString(2, p.getApellido());
				stmt.setString(3, p.getCargo());
				stmt.setInt(4, p.getCodigo());
				stmt.setInt(5, p.getDni());
				stmt.execute();
				stmt.close();
				System.out.println("\n" + "*** EMPLEADO AGREGADO CORRECTAMENTE ***" + "\n" + "CODIGO: " + p.getCodigo()
						+ " " + " NOMBRE: " + nombre2.toUpperCase() + " " + " APELLIDO: " + apellido.toUpperCase() + " " + " DNI: "
						+ p.getDni() + " " + " CARGO: " + cargo.toUpperCase());
				conexion.commit();
				conexion.close();
			} else {
				nombre = datos.getString("nombre");
				System.err.println("\n" + "EL EMPLEADO CON CODIGO " + "[" + p.getCodigo() + "]"
						+ " YA EXISTE CON EL NOMBRE " + "[" + nombre.toUpperCase() + "]"
						+ " .PUEDE SEGUIR LAS SIGUIENTES RECOMENDACIONES:" + "\n"
						+ "* VERIFICAR QUE HA INGRESADO EL CODIGO DEL CLIENTE CORRECTAMENTE, YA QUE CADA ARTICULO TIENE UN NUMERO UNICO Y NO SE PUEDE REPETIR");
			}
		} catch (SQLException ex) {
			System.err.println("Error al conectar o ejecutar la consulta: " + ex.getMessage());
			ex.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				stmt.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void eliminar(int codigo) {
		int valorCodigo = 0;
		boolean validador = false;
		String nombre = "";
		try {
			conexion = ConnectionDS.ConectarDB();
			sql = "SELECT * FROM empleados";
			stmt = conexion.prepareStatement(sql);
			ResultSet datos = stmt.executeQuery();

			while (datos.next()) {
				valorCodigo = datos.getInt("codigo");
				nombre = datos.getString("nombre");
				if (valorCodigo == codigo) {
					conexion.setAutoCommit(false);
					sql = "DELETE FROM empleados WHERE codigo = ?";
					stmt = conexion.prepareStatement(sql);
					stmt.setInt(1, codigo);
					stmt.execute();
					stmt.close();
					conexion.commit();
					conexion.close();
					validador = true;
					break;
				}
			}
			if (validador) {
				System.out.println("\n" + "*** EL EMPLEADO " + "[" + nombre.toUpperCase() + "]" + " CON CODIGO " + "["
						+ valorCodigo + "]" + " FUE ELIMINADO CORRECTAMENTE ***" + "\n");
			} else {
				System.err.println("\n" + "EL CODIGO DEL EMPLEADO NO EXISTE EN LA BASE DE DATOS...");
			}
		} catch (SQLException z) {
			System.err.println("\n" + "*** PRIMERO SE DEBE ELIMINAR LA VENTA ANTES DE ELIMINAR AL VENDEDOR O "
					+ "ARTICULO INVOLUCRADOS EN DICHA TRANSACCION *** ");
			try {
				conexion.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} finally {
			try {
				stmt.close();
				conexion.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
	}

	public void verListadoArticulosYUsuarios() {
		try {
			conexion = ConnectionDS.ConectarDB();
			sql = "SELECT * FROM empleados";
			stmt = conexion.prepareStatement(sql);
			ResultSet datos = stmt.executeQuery();

			if (datos.next()) {
				do {
					String nombre = datos.getString("nombre");
					String apellido = datos.getString("apellido");
					String cargo = datos.getString("cargo");
					int codigo = datos.getInt("codigo");
					int dni = datos.getInt("dni");

					System.out.println("\n" + "NOMBRE: " + nombre.toUpperCase() + "\n" + "APELLIDO: " + apellido.toUpperCase() + "\n" + "CARGO: "
							+ cargo.toUpperCase() + "\n" + "CODIGO: " + codigo + "\n" + "DNI: " + dni + "\n");
				} while (datos.next());
			} else {
				System.err.println("*** ACTUALMENTE NO POSEE NINGUN EMPLEADO CARGADO EN LA BASE DE DATOS ***");
			}
		} catch (SQLException z) {
			z.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexion.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public void actualizarArticulosYUsuarios(Object elemento, int codigo, int seleccion) {
		
		try {
			conexion = ConnectionDS.ConectarDB();
			sql = "SELECT * FROM empleados WHERE codigo = ?";
			stmt = conexion.prepareStatement(sql);
			stmt.setInt(1, codigo);
			ResultSet datos = stmt.executeQuery();

			if (datos.next()) {
				if (seleccion == 1) {
					conexion.setAutoCommit(false);
					sql = "UPDATE empleados SET nombre = ? WHERE codigo = ?";
					stmt = conexion.prepareStatement(sql);
					stmt.setString(1, (String) elemento);
					stmt.setInt(2, codigo);
					stmt.execute();
					stmt.close();
					conexion.commit();
					conexion.close();
					System.out.println("\n" + "NOMBRE ACTUALIZADO CORRECTAMENTE...");
				
			}else if (seleccion == 2) {
					conexion.setAutoCommit(false);
					sql = "UPDATE empleados SET apellido = ? WHERE codigo = ?";
					stmt = conexion.prepareStatement(sql);
					stmt.setString(1, (String) elemento);
					stmt.setInt(2, codigo);
					stmt.execute();
					stmt.close();
					conexion.commit();
					conexion.close();
					System.out.println("\n" + "APELLIDO ACTUALIZADO CORRECTAMENTE...");
				
			}else if (seleccion == 3) {
					conexion.setAutoCommit(false);
					sql = "UPDATE empleados SET cargo = ? WHERE codigo = ?";
					stmt = conexion.prepareStatement(sql);
					stmt.setString(1, (String) elemento);
					stmt.setInt(2, codigo);
					stmt.execute();
					stmt.close();
					conexion.commit();
					conexion.close();
					System.out.println("\n" + "CARGO ACTUALIZADO CORRECTAMENTE...");

			}else if (seleccion == 4) {
					conexion.setAutoCommit(false);
					sql = "UPDATE empleados SET dni = ? WHERE codigo = ?";
					stmt = conexion.prepareStatement(sql);
					stmt.setInt(1, (Integer) elemento);
					stmt.setInt(2, codigo);
					stmt.execute();
					stmt.close();
					conexion.commit();
					conexion.close();
					System.out.println("\n" + "DNI ACTUALIZADO CORRECTAMENTE...");
					}
				
			} else {
				System.err.println("\n" + "EL CODIGO DEL ARTICULO NO EXISTE EN LA BASE DE DATOS...");
			}

		} catch (SQLException z) {
			z.printStackTrace();
			try {
				conexion.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} finally {
			try {
				stmt.close();
				conexion.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public boolean verArticuloYUsuario(int codigo) {
		boolean validador = false;
		try {
			conexion = ConnectionDS.ConectarDB();
			sql = "SELECT * FROM empleados WHERE codigo = ?";
			stmt = conexion.prepareStatement(sql);
			stmt.setInt(1, codigo);
			ResultSet datos = stmt.executeQuery();
			if (datos.next()) {
				do {
					conexion.setAutoCommit(false);
					String nombre = datos.getString("nombre");
					String apellido = datos.getString("apellido");
					String cargo = datos.getString("cargo");
					int codigo1 = datos.getInt("codigo");
					int dni = datos.getInt("dni");
					System.out.println("\n" + "NOMBRE: " + nombre.toUpperCase() + "\n" + "APELLIDO: " + apellido.toUpperCase() + "\n" + "CARGO: "
							+ cargo.toUpperCase() + "\n" + "CODIGO: " + codigo1 + "\n" + "DNI: " + dni + "\n");
				} while (datos.next());
				validador = true;
				conexion.commit();
			} else {
				System.err.println("\n" + "EL CODIGO DEL EMPLEADO NO EXISTE EN LA BASE DE DATOS...");
			}
		} catch (SQLException z) {
			try {
				conexion.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			z.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexion.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return validador;
	}

	public int obtenerCodigo() {
		int codigo = 0;
		int valorCodigo = 0;
		try {
			conexion = ConnectionDS.ConectarDB();
			sql = "SELECT MAX(codigo) AS ultimo_codigo FROM empleados";
			stmt = conexion.prepareStatement(sql);
			ResultSet datos = stmt.executeQuery();

			if (datos.next()) {
				codigo = datos.getInt("ultimo_codigo");
				codigo++;
				valorCodigo = codigo;
			} else {
				valorCodigo = 1;
			}

		} catch (Exception e) {

		} finally {
			try {
				stmt.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return valorCodigo;
	}

	public void verVentas() {
		try {
			conexion = ConnectionDS.ConectarDB();
			sql = "SELECT * FROM ventas";
			stmt = conexion.prepareStatement(sql);
			ResultSet datos = stmt.executeQuery();

			if (datos.next()) {
				do {
					int codigoVenta = datos.getInt("id_venta");
					int codigoEmpleado = datos.getInt("codigo_empleado");
					String nombreEmpleado = datos.getString("nombre_empleado");
					int codigoProducto = datos.getInt("codigo_producto");
					String nombreProducto = datos.getString("nombre_producto");
					int cantidadProducto = datos.getInt("cantidad_producto");
					double totalVenta = datos.getDouble("total_venta");

					System.out.println("\n" + "CODIGO DE LA VENTA: " + codigoVenta + "\n" + "CODIGO VENDEDOR: " + codigoEmpleado + "\n" + "NOMBRE VENDEDOR: " 
							+ nombreEmpleado.toUpperCase() + "\n" + "CODIGO PRODUCTO: "
							+ codigoProducto + "\n" + "NOMBRE PRODUCTO: " + nombreProducto.toUpperCase() + "\n" + "CANTIDAD VENDIDA: " + cantidadProducto + "\n" 
							+ "TOTAL VENTA: " + "$" + totalVenta );
				} while (datos.next());
			} else {
				System.err.println("*** NO POSEE VENTAS ***");
			}
		} catch (SQLException z) {
			z.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexion.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void eliminarVenta(int codigo) {
		int valorCodigo = 0;
		boolean validador = false;
		int devolverProducto = 0;
		int codigoProducto = 0;
		int stock = 0;
		int devolverStock = 0;
		
		try {
			conexion = ConnectionDS.ConectarDB();
			conexion.setAutoCommit(false);
			sql = "SELECT * FROM ventas";
			stmt = conexion.prepareStatement(sql);
			ResultSet datos = stmt.executeQuery();
			sql = "SELECT * FROM articulos";
			stmt = conexion.prepareStatement(sql);
			ResultSet datos2 = stmt.executeQuery();

			while (datos.next() && datos2.next()) {
				valorCodigo = datos.getInt("id_venta");
				devolverProducto = datos.getInt("cantidad_producto");
				codigoProducto = datos.getInt("codigo_producto");
				stock = datos2.getInt("stock");
				devolverStock = stock + devolverProducto;
				
				if (valorCodigo == codigo) {	
					sql = "UPDATE articulos SET stock = ? WHERE codigo = ?";
					stmt = conexion.prepareStatement(sql);
					stmt.setInt(1, devolverStock);
					stmt.setInt(2, codigoProducto);
					stmt.execute();
					
					sql = "DELETE FROM ventas WHERE id_venta = ?";
					stmt = conexion.prepareStatement(sql);
					stmt.setInt(1, codigo);
					stmt.execute();
					stmt.close();
					validador = true;
					break;
				}
			}
			conexion.commit();
			if (validador) {
				System.out.println("\n" + "*** LA VENTA CON CODIGO " + "[" + valorCodigo + "]" 
			+ " FUE ELIMINADA CORRECTAMENTE Y LOS ARTICULOS FUERON DEVUELTOS AL STOCK ***" + "\n");
			} else {
				System.err.println("\n" + "EL CODIGO DE LA VENTA NO EXISTE EN LA BASE DE DATOS...");
			}
		} catch (SQLException z) {
			z.printStackTrace();
			try {
				conexion.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} finally {
			try {
				stmt.close();
				conexion.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void agregarStock(Object b) {
		// TODO Auto-generated method stub
		
	}

	public void vender(Object c, int codigo) {
		// TODO Auto-generated method stub
		
	}

	
	

}
