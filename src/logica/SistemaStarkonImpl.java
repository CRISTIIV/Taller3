package logica;

public class SistemaStarkonImpl implements SistemaStarkon{

	@Override
	public boolean agregarCliente(String rut, String nombre, String apellido, int saldo, String ciudad) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean agregarLocalizacion(String nombre) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean agregarDocumento(int peso, String codigo, String rutRemitente, String rutDestinatario, int grosor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean agregarEncomienda(int peso, String codigo, String rutRemitente, String rutDestinatario, int largo,
			int ancho, int profundidad) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean agregarValija(int peso, String codigo, String rutRemitente, String rutDestinatario,
			String material) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void asociarEntregaRemitenteDestinatario(String codigo, String rutRemitente, String rutDestinatario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asociarLocalidadCliente(String rutCliente, String ciudad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void realizarEntrega(String rutRemitente, String tipoPaquete, String rutDestinatario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recargarSaldo(String rutCliente, int saldo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String iniciarSesion(String rut) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String obtenerEntregasRecibosCliente(String rutCliente) {
		// TODO Auto-generated method stub
		return null;
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
