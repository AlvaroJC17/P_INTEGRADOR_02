package operaciones_dao;
// clase para crear metodos del mebu
import java.util.Scanner;

public class Menu {
	//Atributos
	private boolean salirCase = false;
	private boolean salirSubMenu = false;
	private boolean salirMenuPrincipal = false;

	//Constructores
	public Menu() {
		
	}
	
	public Menu(String nombre) {
		
	}
	
	public Menu(boolean salirCase, boolean salirSubMenu, boolean salirMenuPrincipal) {
		this.salirCase = salirCase;
		this.salirSubMenu = salirSubMenu;
		this.salirMenuPrincipal = salirMenuPrincipal;
	}
	
	//setters y getters
	public boolean isSalirCase() {
		return salirCase;
	}

	public void setSalirCase(boolean salirCase) {
		this.salirCase = salirCase;
	}

	public boolean isSalirSubMenu() {
		return salirSubMenu;
	}

	public void setSalirSubMenu(boolean salirSubMenu) {
		this.salirSubMenu = salirSubMenu;
	}

	public boolean isSalirMenuPrincipal() {
		return salirMenuPrincipal;
	}

	public void setSalirMenuPrincipal(boolean salirMenuPrincipal) {
		this.salirMenuPrincipal = salirMenuPrincipal;
	}

	// Metodo para volver al menu precionando enter
	public static boolean volverMenu() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		boolean salir = false;
		System.out.println("\nPRESIONE ENTER PARA VOLVER AL MENU....");
		String entrada = sc.nextLine();

		if (entrada != null) {
			salir = true;
		}
		return salir;
	}
	
	//Metodo para salir y entrar a los submenu, al principio de case y al menu principal
	public static Menu volverSubmenu(String primeraOpcion) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		boolean salirCase = false;
		boolean salirSubMenu = false;
		boolean salirMenuPrincipal = false;
		int opcion = 0;
		try {
			System.out.println("\n" + primeraOpcion);
			System.out.println("2. VOLVER AL MENU");
			opcion = sc.nextInt();

			if (opcion == 1) {
				salirSubMenu = false;
				salirCase = true;
			} else if (opcion == 2) {
				salirSubMenu = false;
				salirMenuPrincipal = true;
			} else {
				throw new Exception(); 
			}
		} catch (Exception e) {
			salirMenuPrincipal = true;
			salirSubMenu = true;
			System.err.println("SOLO SE PERMITEN NUMEROS ENTRE 1 Y 2");
		}
		Menu resultados = new Menu(salirCase, salirSubMenu, salirMenuPrincipal);
		sc.nextLine(); 
		return resultados;
	}
	
	
	
}
