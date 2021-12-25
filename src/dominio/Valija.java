package dominio;

public class Valija extends Entrega{
	private String material;

	public Valija(int peso, String codigo, String rutRemitente, String rutDestinatario, String material) {
		super(peso, codigo, rutRemitente, rutDestinatario);
		this.material = material;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Override
	public double calcularPrecio() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String toStringValija() {
		return "Tipo de entrega: Valija, Material de la valija: "+material+", "+super.toStringEntrega()+"\n";
	}
}
