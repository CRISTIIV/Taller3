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
	public void agregarDocumento(int peso, String codigo, String rutRemitente, String rutDestinatario, int grosor) {
		Documento d = new Documento(peso,codigo,rutRemitente,rutDestinatario,grosor);
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
	public void agregarEncomienda(int peso, String codigo, String rutRemitente, String rutDestinatario, int largo, int ancho, int profundidad) {
		Encomienda e = new Encomienda(peso,codigo,rutRemitente,rutDestinatario,largo,ancho,profundidad);
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
	public void agregarValija(int peso, String codigo, String rutRemitente, String rutDestinatario, String material) {
		Valija v = new Valija(peso,codigo,rutRemitente,rutDestinatario,material);
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
	
	/*
	@Override
	public void asociarEntregaRemitenteDestinatario(String codigo, String rutRemitente, String rutDestinatario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asociarLocalidadCliente(String rutCliente, String ciudad) {
		Cliente cliente = null;
		for(Cliente client:clientes) {
			if(client.getRut().equals(rutCliente))cliente = (Cliente)client;
		}
		for(Localizacion localizacion:localizaciones) {
			if(localizacion.getNombre().equals(ciudad)) {
				cliente.g
			}
		}
		
	}*/

	@Override
	public void realizarEntrega(String rutRemitente, String tipoPaquete, String rutDestinatario) {
		// TODO Auto-generated method stub
		
	}

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String obtenerEntregasClientes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String obtenerDatosOficinas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String obtenerGananciasOficinas() {
		// TODO Auto-generated method stub
		return null;
	}

}