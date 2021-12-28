package dominio;

public class Encomienda extends Entrega{
	private int largo;
	private int ancho;
	private int deep;
	public Encomienda(int peso, String codigo, String rutRemitente, String rutDestinatario, int largo, int ancho,
			int deep) {
		super(peso, codigo, rutRemitente, rutDestinatario);
		this.largo = largo;
		this.ancho = ancho;
		this.deep = deep;
	}
	public int getLargo() {
		return largo;
	}
	public void setLargo(int largo) {
		this.largo = largo;
	}
	public int getAncho() {
		return ancho;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	public int getDeep() {
		return deep;
	}
	public void setDeep(int deep) {
		this.deep = deep;
	}
	@Override
	public double calcularPrecio() {
		int volumen = this.getAncho()*this.getDeep()*this.getLargo();
		return (this.getPeso()*volumen*50);
	}
	
	public String toStringEncomienda() {
		return "Tipo de entrega: Encomienda, Ancho de la encomienda: "+ancho+"m, Largo de la encomienda: "+largo+"m, Profundidad de la encomienda: "+deep+"m, "+super.toStringEntrega()+"\n";
	}
}