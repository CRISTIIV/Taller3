package logica;

public interface SistemaStarkon {
	boolean agregarCliente(String rut, String nombre, String apellido, int saldo, String ciudad);
	boolean agregarLocalizacion(String nombre);
	boolean agregarDocumento(int peso, String codigo, String rutRemitente, String rutDestinatario, int grosor);
	boolean agregarEncomienda(int peso, String codigo, String rutRemitente, String rutDestinatario, int largo, int ancho, int profundidad);
	boolean agregarValija(int peso, String codigo, String rutRemitente, String rutDestinatario, String material);
	void asociarEntregaRemitenteDestinatario(String codigo, String rutRemitente, String rutDestinatario);
	void asociarLocalidadCliente(String rutCliente, String ciudad);
	void realizarEntrega(String rutRemitente, String tipoPaquete, String rutDestinatario);
	void recargarSaldo(String rutCliente, int saldo);
	String iniciarSesion(String rut);
	String obtenerEntregasRecibosCliente(String rutCliente);
	String obtenerEntregasPorTipo();
	String obtenerEntregasClientes();
	String obtenerDatosOficinas();
	String obtenerGananciasOficinas();
}
