package uniandes.cupi2.cupiHospital.mundo;

public class Encargado {
	
	private String nombre ="";
	private int aniosExperiencia = 0;
	public Encargado(String nombre, int experiencia)
	{
		this.nombre = nombre;
		aniosExperiencia = experiencia;
	}

	public String darNombre() {		
		return nombre;
	}

	public int darAniosExperiencia() {		
		
		return aniosExperiencia;
	}

}
