package dominio;
import logica.*;

public class Cliente {
	private String rut;
	private String nombre;
	private String apellido;
	private int saldo;
	private String ciudad;
	private ListaCircularDobleEntrega entregasR;
	private ListaCircularDobleEntrega entregasE;
	
	public Cliente(String rut, String nombre, String apellido, int saldo, String ciudad) {
		this.rut = rut;
		this.nombre = nombre;
		this.apellido = apellido;
		this.saldo = saldo;
		this.ciudad = ciudad;
		entregasR = new ListaCircularDobleEntrega();
		entregasE = new ListaCircularDobleEntrega(); //AGREGAR AL DIAGRAMA
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

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

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public ListaCircularDobleEntrega getEntregasR() {
		return entregasR;
	}

	public void setEntregas(ListaCircularDobleEntrega entregasR) {
		this.entregasR = entregasR;
	}
	
	public ListaCircularDobleEntrega getEntregasE() {
		return entregasE;
	}

	public void setEntregasE(ListaCircularDobleEntrega entregasE) {
		this.entregasE = entregasE;
	}

	public void aumentarSaldo(int cant) {
		this.saldo += cant;
	}
	
	public void disminuirSaldo(int cant) {
		this.saldo -= cant;
	}
	
	public String toStringCliente() {
		String salida = "";
		
		salida += "Nombre: "+nombre+", Apellido: "+apellido+", Rut: "+rut+", Ciudad: "+ciudad+", Saldo: "+saldo+"\nEncomiendas hechas por el cliente: \n";
		for(int i=0; i<entregasE.size(); i++) {
			salida +=entregasE.get(i).getCodigo() +"\n";
		}
		salida += "Encomiendas recibidas por el cliente: \n";
		for(int i=0; i<entregasR.size(); i++) {
			salida +=entregasR.get(i).getCodigo() +"\n";
		}
		
		return salida;
	}

}
