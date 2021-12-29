package logica;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		SistemaStarkon sistema = new SistemaStarkonImpl();
		lecturArchivos(sistema);
		menu(sistema);
		cierreSistema(sistema);
	}
	
	public static void cierreSistema(SistemaStarkon sistema) {
		try {
			escrituraEntregas(sistema);
		}catch(Exception e) {
			System.out.println("No se pudo sobreescribir el archivo de entregas");
		}
		try {
			escrituraClientes(sistema);
		}catch(Exception e) {
			System.out.println("No se pudo sobreescribir el archivo de clientes");
		}
	}
	
	public static void menu(SistemaStarkon sistema) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Bienvenido");
		while(true){
            String is = iniciarSesion(sistema);
            switch (is) {
                case"Admin":
                	while(true) {
                		System.out.println("Ingrese la password de admin: ");
                		String password = sc.nextLine();
                		if(password.equals("choripan123")) {
                			menuAdmin(sistema);
                			System.out.println("Desea salir('si' para salir)?");
                    		String salir = sc.next().toLowerCase();
                    		if(salir.equals("si"))break;
                		}else {
                			System.out.println("Password incorrecta, se cerrara la sesion.");
                			break;
                		}	
                	}
                    break;
                case"Registrar":
                	System.out.println("El rut ingresado no existe."
                			+ "Desea registrarse('si' para registrar)?");
                	String registro = sc.next().toLowerCase();
            		if(registro.equals("si"))registro(sistema);
                    
                    break;
                default:
                    while(true) {
                    	System.out.println("Vuelva a ingresar su rut para confirmar su identidad: ");
                    	String rut = sc.nextLine();
                    	String rutValidad = validarRut(rut);
                    	menuCliente(sistema, rutValidad);
                		System.out.println("Desea salir('si' para salir)?");
                		String salir = sc.next().toLowerCase();
                		if(salir.equals("si"))break;
                	}
                    break;
            }
            System.out.println("Desea cerrar el sistema? ('si, para salir')");
            String salida = sc.next().toLowerCase();
            if(salida.equals("si"))break;
        }
	}
	
	private static void menuAdmin(SistemaStarkon sistema) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido administrador");
        System.out.println("Que desea realizar:");
        System.out.println("A) Desplegar las entregas segun el tipo ");
        System.out.println("B) Desplegar la cantidad de entregas segun la locacion ");
        System.out.println("C) Desplegar las entregas por cada cliente ");
        System.out.println("D) Desplegar el registro de ganancias ");
        String opcion = sc.next().toLowerCase();
        switch (opcion) {
            case "a":
                System.out.println(sistema.obtenerEntregasPorTipo());
                break;
            case "b":
                System.out.println(sistema.obtenerDatosOficinas());
                break;
            case "c":
                System.out.println(sistema.obtenerEntregasClientes());
                break;
            case "d":
                System.out.println(sistema.obtenerGananciasOficinas());
                break;
            default:
                System.out.println("La opcion ingresada no es valida");
        }
    }
	
	private static void menuCliente(SistemaStarkon sistema, String rut) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido cliente: ");
        System.out.println("Que desea realizar: ");
        System.out.println("A) Realizar entrega ");
        System.out.println("B) Agregar saldo ");
        System.out.println("C) Desplegar entregas recibidas y realizadas ");
        String opcion = sc.next().toLowerCase();
        switch (opcion) {
            case "c":
                System.out.println(sistema.obtenerEntregasRecibosCliente(rut));
                break;
            case "b":
                System.out.println("Ingrese el saldo que desea agregar");
                try {
                    int saldo = sc.nextInt();
                    sistema.recargarSaldo(rut, saldo);
                } catch (Exception e) {
                    System.out.println("Ingrese un numero");
                }
                break;
            case "a":
            	int numero = (int)(Math.random()*(999999-100000+1)+100000);
            	String codigo = String.valueOf(numero);
            	System.out.println("Ingrese el tipo de paquete que desea enviar (Documento = D, Encomienda = E, Valija = V): ");
            	String tipo = sc.nextLine().toUpperCase();
            	System.out.println("Ingrese el rut del destinatario al que desea enviar: ");
            	String rutDes = sc.nextLine();
            	String rutDesFor = validarRut(rutDes);
            	switch(tipo) {
            	case "D":
            		System.out.println("Ingrese el peso en gramos del documento (menor o igual a 1500 gramos): ");
            		int pesoD = sc.nextInt();
            		System.out.println("Ingrese el grosor en milimetros del documento (menor o igual a 50 milimetros): ");
            		int grosor = sc.nextInt();
            		if (pesoD <= 1500 && grosor <= 50) {
            			try {
                			sistema.realizarEntregaDocumento(rut, codigo, rutDesFor, pesoD, grosor);
                		}catch(Exception e) {
                			System.out.println(e.getMessage());
                		}
            		}else {
            			System.out.println("Algun parametro (grosor o peso) supera los limites establecidos.");
            		}
            		break;
            		
            	case "E":
            		System.out.println("Ingrese el peso en gramos de la encomienda (menor o igual a 50000 gramos): ");
            		int pesoE = sc.nextInt();
            		System.out.println("Ingrese el largo en centimetros de la encomienda (menor o igual a 120 centimetros): ");
            		int largo = sc.nextInt();
            		System.out.println("Ingrese el ancho en centimetros de la encomienda (menor o igual a 80 centimetros): ");
            		int ancho = sc.nextInt();
            		System.out.println("Ingrese la profundidad en centimetros de la encomienda (menor o igual a 80 centimetros): ");
            		int deep = sc.nextInt();
            		if (pesoE <= 50000 && largo <= 120 && ancho <= 80 && deep <= 80) {
            			try {
                			sistema.realizarEntregaEncomienda(rut, codigo, rutDesFor, pesoE, largo, ancho, deep);
                		}catch(Exception e) {
                			System.out.println(e.getMessage());
                		}
            		}else {
            			System.out.println("Algun parametro (peso, largo, ancho o profundidad) supera los limites establecidos.");
            		}
            		break;
            	case "V":
            		System.out.println("Ingrese el peso en gramos de la valija (menor o igual a 2000 gramos): ");
            		int pesoV = sc.nextInt();
            		System.out.println("Ingrese el material de la valija (Plastico, Cuero, Tela): ");
            		String material = sc.nextLine();
            		if (pesoV <= 2000) {
            			try {
                			sistema.realizarEntregaValija(rut, codigo, rutDesFor, pesoV, material);
                		}catch(Exception e) {
                			System.out.println(e.getMessage());
                		}
            		}else {
            			System.out.println("Algun parametro (peso) supera los limites establecidos.");
            		}
            		break;
            	}
                break;
            default:
                System.out.println("La opcion ingresada no es valida");
        }
	}

	
	private static void escrituraClientes(SistemaStarkon sistema) throws IOException{
		BufferedWriter escritura = new BufferedWriter(new FileWriter("Cliente.txt"));
		escritura.write(sistema.obtenerClientes());
		escritura.close();
	}

	
	private static void escrituraEntregas(SistemaStarkon sistema) throws IOException{
		BufferedWriter escritura = new BufferedWriter(new FileWriter("Entregas.txt"));
		escritura.write(sistema.obtenerEntregas());
		escritura.close();
	}
	
	private static void registro(SistemaStarkon sistema) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese su rut: ");
		String rut = sc.nextLine();
		String rutFor = validarRut(rut);
		System.out.println("Ingrese su nombre: ");
		String nombre = sc.nextLine();
		System.out.println("Ingrese su apellido: ");
		String apellido = sc.nextLine();
		System.out.println("Digite el saldo con el que va a iniciar: ");
		int saldo = Integer.parseInt(sc.nextLine());
		System.out.println("Ingrese su localidad: ");
		String ciudad = sc.nextLine();
		if(sistema.agregarCliente(rutFor, nombre, apellido, saldo, ciudad))System.out.println("Resgitro exitoso.");
	}
	
	private static String iniciarSesion(SistemaStarkon sistema) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese su rut: ");
		String rut = sc.nextLine();
		String rutFormat = validarRut(rut);
		String inicioSesion = sistema.iniciarSesion(rutFormat);
		return inicioSesion;
	}
	
	private static void lecturArchivos(SistemaStarkon sistema) {
		try {
			lecturaCiudades(sistema);
		}catch(Exception e) {
			System.out.println("No se pudo leer el archivo localizacion.txt");
		}
		try {
			lecturaEntregas(sistema);
		}catch(Exception e) {
			System.out.println("No se pudo leer el archivo Entregas.txt");
		}
		try {
			lecturaClientes(sistema);
		}catch(Exception e) {
			System.out.println("No se pudo leer el archivo Cliente.txt");
			System.out.println(e.getMessage());
		}
	}
	
	private static String validarRut(String rut) {
		int contador = 0;
		rut = rut.replace(".", "").replace("-", "");
		String rutFormateado = "-" + rut.substring(rut.length() - 1);
		for (int i = rut.length() - 2; i >= 0; i--) {
			rutFormateado = rut.substring(i, i + 1) + rutFormateado;
			contador++;
			if (contador == 3 && i != 0) {
				rutFormateado = "." + rutFormateado;
				contador = 0;
			}
		}
		return rutFormateado;
	}
	
	private static void lecturaClientes(SistemaStarkon sistema)throws FileNotFoundException{
		Scanner arch = new Scanner(new File("Cliente.txt"));
		while(arch.hasNextLine()) {
			String linea = arch.nextLine();
			String[] parte = linea.split(",");
			
			String rut = parte[0];
			String nombre = parte[1];
			String apellido = parte[2];
			int saldo = Integer.parseInt(parte[3]);
			String ciudad = parte[4];
			sistema.agregarCliente(rut, nombre, apellido, saldo, ciudad);
		}
	}
	
	private static void lecturaCiudades(SistemaStarkon sistema)throws FileNotFoundException{
		Scanner arch = new Scanner(new File("localizacion.txt"));
		while(arch.hasNextLine()) {
			String nombre = arch.nextLine();
			sistema.agregarLocalizacion(nombre);
		}
	}
	
	private static void lecturaEntregas(SistemaStarkon sistema)throws FileNotFoundException{
		Scanner arch = new Scanner(new File("Entregas.txt"));
		while(arch.hasNextLine()) {
			String linea = arch.nextLine();
			String[] parte = linea.split(",");
			
			String codigo = parte[0];
			String tipo = parte[1];
			String rutRemitente = parte[2];
			String rutDestinatario = parte[3];
			
			if(tipo.equals("D")) {
				int pesoG = Integer.parseInt(parte[4]);
				int grosorMm = Integer.parseInt(parte[5]);
				sistema.agregarDocumento(pesoG, codigo, rutRemitente, rutDestinatario, tipo, grosorMm);
			}
			if(tipo.equals("E")) {
				int pesoE = Integer.parseInt(parte[4]);
				int largoCm = Integer.parseInt(parte[5]);
				int anchoCm = Integer.parseInt(parte[6]);
				int deepCm = Integer.parseInt(parte[7]);
				sistema.agregarEncomienda(pesoE, codigo, rutRemitente, rutDestinatario, tipo, largoCm, anchoCm, deepCm);
			}
			if(tipo.equals("V")) {
				String material = parte[4];
				int pesoV = Integer.parseInt(parte[5]);
				sistema.agregarValija(pesoV, codigo, rutRemitente, rutDestinatario, tipo, material);
			}
			//LO VERDE ES EN VEZ DE ESTO:
			/*
			switch(tipo) {
			case "D":
				int pesoD = Integer.parseInt(parte[4]);
				int grosorMm = Integer.parseInt(parte[5]);
				sistema.agregarDocumento(pesoD, codigo, rutRemitente, rutDestinatario, tipo, grosorMm);
				
				break;
			case "E":
				int pesoE = Integer.parseInt(parte[4]);
				int largoCm = Integer.parseInt(parte[5]);
				int anchoCm = Integer.parseInt(parte[6]);
				int deepCm = Integer.parseInt(parte[7]);
				sistema.agregarEncomienda(pesoE, codigo, rutRemitente, rutDestinatario, tipo, largoCm, anchoCm, deepCm);
				break;
			case "V":
				String material = parte[4];
				int pesoV = Integer.parseInt(parte[5]);
				sistema.agregarValija(pesoV, codigo, rutRemitente, rutDestinatario, tipo, material);
				break;
			}*/
		}
	}
}
