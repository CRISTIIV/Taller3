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
		lecturaArchivos(sistema);
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
}
