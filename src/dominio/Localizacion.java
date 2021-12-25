package dominio;

public class Localizacion {
	private String nombre;

	public Localizacion(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String toStringLocalizacion() {
		return "Nombre: "+nombre;
	}
}
