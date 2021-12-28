package dominio;

public class Documento extends Entrega{
	private int grosor;
	
	public Documento(int peso, String codigo, String rutRemitente, String rutDestinatario, int grosor) {
		super(peso, codigo, rutRemitente, rutDestinatario);
		this.grosor = grosor;
	}

	public int getGrosor() {
		return grosor;
	}

	public void setGrosor(int grosor) {
		this.grosor = grosor;
	}

	@Override
	public double calcularPrecio() {
		return (this.getPeso()*grosor*100);
	}
	
	public String toStringDocumento() {
		return "Tipo de entrega: Documento, Grosor del documento: "+grosor+"cm, "+super.toStringEntrega()+"\n";
	}
}
