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
		double precio = 0;
		if (this.material.equals("Cuero")) {
			precio = (200*this.getPeso()*150);
		}
		if (this.material.equals("Plastico")) {
			precio = (150*this.getPeso()*150);
		}
		if (this.material.equals("Tela")) {
			precio = (100*this.getPeso()*150);
		}
		return precio;
	}
	
	public String toStringValija() {
		return "Tipo de entrega: Valija, Material de la valija: "+material+", "+super.toStringEntrega()+"\n";
	}
}
