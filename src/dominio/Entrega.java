package dominio;

public abstract class Entrega {
	private int peso;
	private String codigo;
	private String rutRemitente;
	private String rutDestinatario;
	private String tipo;

	public Entrega(int peso, String codigo, String rutRemitente, String rutDestinatario, String tipo) {
		this.peso = peso;
		this.codigo = codigo;
		this.rutRemitente = rutRemitente;
		this.rutDestinatario = rutDestinatario;
		this.tipo = tipo;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getRutRemitente() {
		return rutRemitente;
	}

	public void setRutRemitente(String rutRemitente) {
		this.rutRemitente = rutRemitente;
	}

	public String getRutDestinatario() {
		return rutDestinatario;
	}

	public void setRutDestinatario(String rutDestinatario) {
		this.rutDestinatario = rutDestinatario;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public abstract double calcularPrecio();
	
	public String toStringEntrega() {
		return "Codigo: "+codigo+", Peso: "+peso+"kg, Rut del remitente: "+rutRemitente+", Rut del destinatario: "+rutDestinatario;
	}
}
