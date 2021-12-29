package logica;
import java.util.ArrayList;
import java.util.LinkedList;

import dominio.*;

public class SistemaStarkonImpl implements SistemaStarkon{
	private ListaCircularDobleEntrega entregas;
	private Lista<Documento> documentos;
	private Lista<Encomienda> encomiendas;
	private Lista<Valija> valijas;
	private LinkedList<Cliente> clientes;
	private ArrayList<Localizacion> localizaciones;
	
	public SistemaStarkonImpl() {
		entregas = new ListaCircularDobleEntrega();
		documentos = new ListaCircularDoble<Documento>();
		encomiendas = new ListaCircularDoble<Encomienda>();
		valijas = new ListaCircularDoble<Valija>();
		clientes = new LinkedList<Cliente>();
		localizaciones = new ArrayList<Localizacion>();
	}
	
	
	@Override
	public boolean agregarCliente(String rut, String nombre, String apellido, int saldo, String ciudad) {
		for (Cliente cliente:clientes) {
			if(cliente.getRut().equals(rut))return false;
		}
		Cliente c = new Cliente(rut,nombre,apellido,saldo,ciudad);
		return clientes.add(c);
	}

	@Override
	public boolean agregarLocalizacion(String nombre) {
		for (Localizacion localizacion:localizaciones) {
			if(localizacion.getNombre().equals(nombre))return false;
		}
		Localizacion l = new Localizacion(nombre);
		return localizaciones.add(l);
	}

	@Override
	public void agregarDocumento(int peso, String codigo, String rutRemitente, String rutDestinatario, String tipo, int grosor) {
		Documento d = new Documento(peso,codigo,rutRemitente,rutDestinatario,tipo,grosor);
		for(Cliente cliente:clientes) {
			if(cliente.getRut().equals(rutRemitente)) {
				cliente.getEntregasE().add(d);
				break;
			}
			if(cliente.getRut().equals(rutDestinatario)) {
				cliente.getEntregasR().add(d);
				break;
			}
		}
		entregas.add(d);
		documentos.add(d);
	}

	@Override
	public void agregarEncomienda(int peso, String codigo, String rutRemitente, String rutDestinatario, String tipo, int largo, int ancho, int profundidad) {
		Encomienda e = new Encomienda(peso,codigo,rutRemitente,rutDestinatario,tipo,largo,ancho,profundidad);
		for(Cliente cliente:clientes) {
			if(cliente.getRut().equals(rutRemitente)) {
				cliente.getEntregasE().add(e);
				break;
			}
			if(cliente.getRut().equals(rutDestinatario)) {
				cliente.getEntregasR().add(e);
				break;
			}
		}
		entregas.add(e);
		encomiendas.add(e);
	}

	@Override
	public void agregarValija(int peso, String codigo, String rutRemitente, String rutDestinatario, String tipo, String material) {
		Valija v = new Valija(peso,codigo,rutRemitente,rutDestinatario,tipo,material);
		for(Cliente cliente:clientes) {
			if(cliente.getRut().equals(rutRemitente)) {
				cliente.getEntregasE().add(v);
				break;
			}
			if(cliente.getRut().equals(rutDestinatario)) {
				cliente.getEntregasR().add(v);
				break;
			}
		}
		entregas.add(v);
		valijas.add(v);
	}

	/*@Override//HACER EN LA APP DIRECTO
	public void realizarEntrega(String rutRemitente, String tipoPaquete, String rutDestinatario) {
		//En la app hacer llamado sistema.agregarDocumento(...) y los parámetros serían los ingresados por pantalla.
		for (Cliente cliente:clientes) {
			if(cliente.getRut().equals(rutRemitente) && tipoPaquete.equals("D")) {
				Documento d = new Documento();
				cliente.disminuirSaldo(0);
			}
		}
	}*/
	
	@Override
	public void recargarSaldo(String rutCliente, int saldo) {
		for(Cliente cliente:clientes) {
			if(cliente.getRut().equals(rutCliente)) {
				//Cliente c = (Cliente)cliente;
				cliente.aumentarSaldo(saldo);//c
				return;
			}
		}
		throw new NullPointerException("No existe el cliente seleccionado");
		
	}

	@Override
	public String obtenerClientes() {
		String salida = "";
		for (int i = 0; i < clientes.size(); i++) {
			Cliente c = clientes.get(i);
			salida += c.getRut()+","+c.getNombre()+","+c.getApellido()+","+c.getSaldo()+","+c.getCiudad();
		}
		return salida;
	}


	@Override
	public String obtenerEntregas() {
		String salida = "";
		for (int i = 0; i<entregas.size(); i++) {
			Entrega e = entregas.get(i);
			salida += e.getCodigo()+","+e.getTipo()+","+e.getRutRemitente()+","+e.getRutDestinatario()+",";
			if(e instanceof Documento) {
				Documento d = (Documento)e;
				salida += d.getPeso()+","+d.getGrosor();
			}
			if(e instanceof Encomienda) {
				Encomienda d = (Encomienda)e;
				salida += d.getPeso()+","+d.getLargo()+","+d.getAncho()+","+d.getDeep();
			}
			if(e instanceof Valija) {
				Valija d = (Valija)e;
				salida += d.getMaterial()+","+d.getPeso();
			}
		}
		return salida;
	}


	@Override
	public String iniciarSesion(String rut) {
		Cliente client = null;
		for(Cliente cliente:clientes) {
			if(cliente.getRut().equals(rut)) {
				client = cliente;
			}
		}
		if(client==null) {
			return "Registrar";
		}
		if(!rut.equals("Admin")) {
			return "Cliente-"+client.getNombre()+" "+client.getApellido();
		}else {
			return "Admin";
		}
	}

	@Override
	public String obtenerEntregasRecibosCliente(String rutCliente) {
		Cliente client = null;
		for(Cliente cliente:clientes) {
			if(cliente.getRut().equals(rutCliente)) {
				client = cliente;
			}
		}
		return client.enviadasRecibidas();
	}

	@Override
	public String obtenerEntregasPorTipo() {
		String salida = "";
		String documentos = "Entregas del tipo documento: \n";
		String encomiendas = "\nEntregas del tipo encomienda: \n";
		String valijas = "\nEntregas del tipo valija: \n";
		for (int i = 0; i < entregas.size(); i++) {
			Entrega entrega = entregas.get(i);
			if (entrega.getTipo().equals("D")) {
				documentos += entrega.toStringEntrega();
			}
			if (entrega.getTipo().equals("E")) {
				encomiendas += entrega.toStringEntrega();
			}
			if (entrega.getTipo().equals("V")) {
				valijas += entrega.toStringEntrega();
			}
		}
		salida += documentos+encomiendas+valijas;
		return salida;
	}

	@Override
	public String obtenerEntregasClientes() {
		String salida = "";
		for (int i = 0; i<clientes.size();i++) {
			Cliente c = clientes.get(i);
			salida += c.toStringCliente();
		}
		return salida;
	}

	@Override
	public String obtenerDatosOficinas() {
		String salida = "Entregas por localizacion: \n";
		for (int i = 0; i<localizaciones.size(); i++) {
			int cantR = 0;
			int cantE = 0;
			Localizacion l = localizaciones.get(i);
			salida += "En "+l.getNombre()+" se realizaron: \n";
			for (int j = 0; j<clientes.size(); i++) {
				Cliente c = clientes.get(i);
				if(c.getCiudad().equals(l.getNombre())) {
					cantE += c.getEntregasE().size();
					cantR += c.getEntregasR().size();
				}
			}
			salida += cantE+" envios y "+cantR+" recepciones de entregas.\n";
		}
		return salida;
	}

	@Override
	public String obtenerGananciasOficinas() {
		int total = 0;
		String salida = "Ganancias por localizacion: ";
		for (int i = 0; i<localizaciones.size(); i++) {
			int cont = 0;
			Localizacion l = localizaciones.get(i);
			salida += "\nEn "+l.getNombre()+" se recaudaron: ";
			for (int j = 0; j<clientes.size(); i++) {
				Cliente c = clientes.get(i);
				if(c.getCiudad().equals(l.getNombre())) {
					for(int m = 0; m<c.getEntregasR().size(); m++) {
						Entrega e = c.getEntregasR().get(m);
						cont += e.calcularPrecio();
					}
				}
			}
			salida += "$"+cont+".\n";
			total += cont;
		}
		salida += "Y a nivel nacional se recaudaron: $"+total;
		return salida;
	}

}
