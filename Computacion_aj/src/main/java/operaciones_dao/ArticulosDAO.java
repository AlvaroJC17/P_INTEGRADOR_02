package operaciones_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import computacionaj_vo.ArticulosVO;
import dbconnection_ds.ConnectionDS;

public class ArticulosDAO implements IOperacionesDB {

	// Atributos
	private Connection conexion;
	private String sql;
	private PreparedStatement stmt;


	// metodos

	public void ingresarNuevo(Object a) {
		ArticulosVO p = (ArticulosVO)a;
		String nombre;
		String nombre2 = p.getNombre();
		try {
			conexion = ConnectionDS.ConectarDB();
			sql = "SELECT * FROM articulos WHERE codigo = ?";
			stmt = conexion.prepareStatement(sql);
			stmt.setInt(1, p.getCodigo());
			ResultSet datos = stmt.executeQuery();

			if (!datos.next()) {
				// El artículo no existe, insertarlo
				conexion.setAutoCommit(false);
				sql = "INSERT INTO articulos (nombre, descripcion, codigo, precio, stock) VALUES (?, ?, ?, ?, ?)";
				stmt = conexion.prepareStatement(sql);
				stmt.setString(1, p.getNombre());
				stmt.setString(2, p.getDescripcion());
				stmt.setInt(3, p.getCodigo());
				stmt.setDouble(4, p.getPrecio());
				stmt.setInt(5, p.getStock());
				stmt.execute();
				stmt.close();
				System.out.println("\n" + "*** ARTICULO AGREGADO CORRECTAMENTE ***" + "\n" + "CODIGO: " + p.getCodigo()
						+ " " + " NOMBRE: " + nombre2.toUpperCase() + " " + " PRECIO: " + p.getPrecio() + " " + " STOCK: "
						+ p.getStock());
				conexion.commit();
				conexion.close();
			} else {
				nombre = datos.getString("nombre");
				System.err.println("\n" + "EL ARTICULO CON CODIGO " + "[" + p.getCodigo() + "]"
						+ " YA EXISTE CON EL NOMBRE " + "[" + nombre.toUpperCase() + "]"
						+ " .PUEDE SEGUIR LAS SIGUIENTES RECOMENDACIONES:" + "\n"
						+ "* VERIFICAR QUE HA INGRESADO EL CODIGO DEL ARTICULO CORRECTAMENTE, YA QUE CADA ARTICULO TIENE UN NUMERO UNICO Y NO SE PUEDE REPETIR"
						+ "\n"
						+ "* SI EL CODIGO DEL ARTICULO YA ESTA REGISTRADO, PUEDE AGREGAR MAS UNIDADES AL STOCK DESDE EL MENU PRINCIPAL.");
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
			sql = "SELECT * FROM articulos";
			stmt = conexion.prepareStatement(sql);
			ResultSet datos = stmt.executeQuery();

			while (datos.next()) {
				valorCodigo = datos.getInt("codigo");
				nombre = datos.getString("nombre");
				if (valorCodigo == codigo) {
					conexion.setAutoCommit(false);
					sql = "DELETE FROM articulos WHERE codigo = ?";
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
				System.out.println("\n" + "*** EL ARTICULO " + "[" + nombre.toUpperCase() + "]" + " CON CODIGO " + "["
						+ valorCodigo + "]" + " FUE ELIMINADO CORRECTAMENTE ***" + "\n");
			} else {
				System.err.println("\n" + "EL CODIGO DEL ARTICULO NO EXISTE EN LA BASE DE DATOS...");
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

	public void agregarStock(Object b) {
		ArticulosVO p = (ArticulosVO) b;
		int stockArt = 0;
		int aumentoStock;
		String nombre;

		try {
			conexion = ConnectionDS.ConectarDB();
			sql = "SELECT * FROM articulos WHERE codigo = ?";
			stmt = conexion.prepareStatement(sql);
			stmt.setInt(1, p.getCodigo());
			ResultSet datos = stmt.executeQuery();

			if (datos.next()) {
				conexion.setAutoCommit(false);
				stockArt = datos.getInt("stock");
				nombre = datos.getString("nombre");
				aumentoStock = p.getStock() + stockArt;
				sql = "UPDATE articulos SET stock = ? WHERE codigo = ?";
				stmt = conexion.prepareStatement(sql);
				stmt.setInt(1, aumentoStock);
				stmt.setInt(2, p.getCodigo());
				stmt.execute();
				stmt.close();
				conexion.commit();
				conexion.close();
				System.out.println("\n" + "*** SE AGREGARON " + "[" + p.getStock() + "]" + " UNIDADES DE " + "["
						+ nombre.toUpperCase() + "]" + " AL STOCK CORRECTAMENTE ***");
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
				conexion.close();
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void vender(Object c, int codigo) {
		ArticulosVO p = (ArticulosVO) c;
		int stockArt = 0;
		int disminuirStock;
		String nombreProducto;
		double precio = 0;
		double totalVenta = 0;
		int cantidad = p.getStock();
		int codigoProducto = p.getCodigo();
		String nombreEmpleado;
		int codigoEmpleado;

		try {
			conexion = ConnectionDS.ConectarDB();
			sql = "SELECT * FROM articulos WHERE codigo = ?";
			stmt = conexion.prepareStatement(sql);
			stmt.setInt(1, codigoProducto);
			ResultSet datos = stmt.executeQuery();
			sql = "SELECT * FROM empleados WHERE codigo = ?";
			stmt = conexion.prepareStatement(sql);
			stmt.setInt(1, codigo);
			ResultSet datos2 = stmt.executeQuery();

			if (datos.next() && datos2.next()) {
				conexion.setAutoCommit(false);
				stockArt = datos.getInt("stock");
				nombreProducto = datos.getString("nombre");
				precio = datos.getDouble("precio");
				nombreEmpleado = datos2.getString("nombre");
				codigoEmpleado = datos2.getInt("codigo");
				disminuirStock = stockArt - cantidad;
				totalVenta = cantidad * precio;

				if (disminuirStock < 0) {
					System.err.println("\n" + "*** ACTUALMENTE POSEE " + "[" + stockArt + "]" + " UNIDADES DE " + "["
							+ nombreProducto.toUpperCase() + "]"
							+ " EN STOCK. NO SE PUEDE VENDER UNA CANTIDAD MAYOR AL STOCK DISPONIBLE ***");
				} else {
					sql = "UPDATE articulos SET stock = ? WHERE codigo = ?";
					stmt = conexion.prepareStatement(sql);
					stmt.setInt(1, disminuirStock);
					stmt.setInt(2, p.getCodigo());
					stmt.execute();					
					
				    sql = "INSERT INTO ventas (codigo_empleado, nombre_empleado, codigo_producto, nombre_producto, cantidad_producto, total_venta) VALUES (?, ?, ?, ?, ?, ?)";
					stmt = conexion.prepareStatement(sql);
					stmt.setInt(1, codigoEmpleado);
					stmt.setString(2, nombreEmpleado);
					stmt.setInt(3, codigoProducto);
					stmt.setString(4, nombreProducto);
					stmt.setInt(5, cantidad);
					stmt.setDouble(6, totalVenta);
					stmt.execute();
					conexion.commit();
					System.out.println("\n" + "*** SE VENDIERON EXITOSAMENTE " + "[" + p.getStock() + "]" + " UNIDADES DE "
							+ "[" + nombreProducto.toUpperCase() + "]" + " POR UN TOTAL DE $" + totalVenta + " *** " + "\n" 
							+ "*** ACTUALMENTE QUEDAN " + "[" + disminuirStock + "]" + " DISPONIBLES ***");
				}

			} else {
				System.err.println("\n" + "EL CODIGO DEL ARTICULO NO EXISTE EN LA BASE DE DATOS...");
			}
		} catch (SQLException z) {
			z.printStackTrace();
			System.out.println(z.getMessage());
			System.out.println(z.getLocalizedMessage());
			try {
				conexion.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				conexion.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void verListadoArticulosYUsuarios() {
		try {
			conexion = ConnectionDS.ConectarDB();
			sql = "SELECT * FROM articulos";
			stmt = conexion.prepareStatement(sql);
			ResultSet datos = stmt.executeQuery();

			if (datos.next()) {
				do {
					String nombre = datos.getString("nombre");
					double precio = datos.getDouble("precio");
					int codigo = datos.getInt("codigo");
					int stock = datos.getInt("stock");
					String descripcion = datos.getString("descripcion");

					System.out.println("\n" + "NOMBRE: " + nombre.toUpperCase() + "\n" + "PRECIO: " + precio + "\n" + "CODIGO: "
							+ codigo + "\n" + "STOCK: " + stock + "\n" + "DESCRIPCION: " + descripcion.toUpperCase() + "\n");
				} while (datos.next());
			} else {
				System.err.println("*** ACTUALMENTE NO POSEE NINGUN ARTICULO EN STOCK ***");
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
			sql = "SELECT * FROM articulos WHERE codigo = ?";
			stmt = conexion.prepareStatement(sql);
			stmt.setInt(1, codigo);
			ResultSet datos = stmt.executeQuery();

			if (datos.next()) {
				if (seleccion == 1) {
					conexion.setAutoCommit(false);
					sql = "UPDATE articulos SET nombre = ? WHERE codigo = ?";
					stmt = conexion.prepareStatement(sql);
					stmt.setString(1, (String) elemento);
					stmt.setInt(2, codigo);
					stmt.execute();
					stmt.close();
					conexion.commit();
					conexion.close();
					System.out.println("\n" + "NOMBRE ACTUALIZADO CORRECTAMENTE...");

				} else if (seleccion == 2) {
					conexion.setAutoCommit(false);
					sql = "UPDATE articulos SET precio = ? WHERE codigo = ?";
					stmt = conexion.prepareStatement(sql);
					stmt.setDouble(1, (Double) elemento);
					stmt.setInt(2, codigo);
					stmt.execute();
					stmt.close();
					conexion.commit();
					conexion.close();
					System.out.println("\n" + "PRECIO ACTUALIZADO CORRECTAMENTE...");

				} else if (seleccion == 3) {
					conexion.setAutoCommit(false);
					sql = "UPDATE articulos SET descripcion = ? WHERE codigo = ?";
					stmt = conexion.prepareStatement(sql);
					stmt.setString(1, (String) elemento);
					stmt.setInt(2, codigo);
					stmt.execute();
					stmt.close();
					conexion.commit();
					conexion.close();
					System.out.println("\n" + "DESCRIPCION ACTUALIZADA CORRECTAMENTE...");
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
			sql = "SELECT * FROM articulos WHERE codigo = ?";
			stmt = conexion.prepareStatement(sql);
			stmt.setInt(1, codigo);
			ResultSet datos = stmt.executeQuery();
			if (datos.next()) {
				do {
					conexion.setAutoCommit(false);
					String nombre = datos.getString("nombre");
					double precio = datos.getDouble("precio");
					int stock = datos.getInt("stock");
					String descripcion = datos.getString("descripcion");
					System.out.println("\n" + "NOMBRE: " + nombre.toUpperCase() + "\n" + "PRECIO: " + precio + "\n" + "CODIGO: "
							+ codigo + "\n" + "STOCK: " + stock + "\n" + "DESCRIPCION: " + descripcion.toUpperCase() + "\n");
				} while (datos.next());
				validador = true;
				conexion.commit();
			} else {
				System.err.println("\n" + "EL CODIGO DEL ARTICULO NO EXISTE EN LA BASE DE DATOS...");
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
			sql = "SELECT MAX(codigo) AS ultimo_codigo FROM articulos";
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
		// TODO Auto-generated method stub
		
	}

	public void eliminarVenta(int codigo) {
		// TODO Auto-generated method stub
		
	}

}
