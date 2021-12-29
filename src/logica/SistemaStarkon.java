package logica;

public interface SistemaStarkon {
	boolean agregarCliente(String rut, String nombre, String apellido, int saldo, String ciudad);
	boolean agregarLocalizacion(String nombre);
	void agregarDocumento(int peso, String codigo, String rutRemitente, String rutDestinatario, String tipo, int grosor);
	void agregarEncomienda(int peso, String codigo, String rutRemitente, String rutDestinatario, String tipo, int largo, int ancho, int profundidad);
	void agregarValija(int peso, String codigo, String rutRemitente, String rutDestinatario, String tipo, String material);
	String obtenerClientes();
	String obtenerEntregas();
	void recargarSaldo(String rutCliente, int saldo);
	String iniciarSesion(String rut);
	String obtenerEntregasRecibosCliente(String rutCliente);
	String obtenerEntregasPorTipo();
	String obtenerEntregasClientes();
	String obtenerDatosOficinas();
	String obtenerGananciasOficinas();
}
