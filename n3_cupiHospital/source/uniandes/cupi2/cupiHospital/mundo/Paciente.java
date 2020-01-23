/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: cupiHospital
 * Autor: Equipo Cupi2 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.cupiHospital.mundo;

/**
 * Clase que representa un paciente del hospital.
 */
public class Paciente 
{
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Enumeración para el motivo de ingreso.
	 */
	public enum Motivo
	{
		INFECCION,
		ACCIDENTE,
		PARTO,
		TRATAMIENTO,
		CIRUGIA
	}

	//-------------------------------------------------------------
	// Atributos
	//-------------------------------------------------------------

	/**
	 * Nombre del paciente.
	 */
	private String nombre;

	/**
	 * Apellido del paciente.
	 */
	private String apellido;

	/**
	 * Número de identificación del paciente.
	 */
	private int numeroIdentificacion;

	/**
	 * Edad del paciente.
	 */
	private int edad;

	/**
	 * Diagnóstico del paciente.
	 */
	private String diagnostico;

	/**
	 * Días de hospitalización del paciente.
	 */
	private int diasHospitalizacion;

	/**
	 * Motivo de ingreso del paciente.
	 */
	private Motivo motivoIngreso;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Construye un nuevo paciente con los parámetros dados.
	 * @param pNombre Nombre del paciente. pNombre != null && !pNombre.equals("").
	 * @param pApellido Apellido del paciente. pApellido != null && !pApellido.equals("").
	 * @param pNumeroIdentificacion ID del paciente. pNumeroIdentificacion > 0.
	 * @param pEdad Edad del paciente. pEdad >= 0.
	 * @param pDiagnostico Diagnóstico del paciente. pDiagnostico != null && !pDiagnostico.equals("").
	 * @param pDiasHospitalizacion Dias de hospitalización del paciente. pDiasHospitalizacion > 0.
	 * @param pMotivoIngreso Motivo de ingreso del paciente. 
	 */
	public Paciente(String pNombre, String pApellido, int pNumeroIdentificacion, int pEdad, String pDiagnostico, int pDiasHospitalizacion, Motivo pMotivoIngreso) 
	{
		nombre = pNombre;
		apellido = pApellido;
		numeroIdentificacion = pNumeroIdentificacion;
		edad = pEdad;
		diagnostico = pDiagnostico;
		diasHospitalizacion = pDiasHospitalizacion;
		motivoIngreso = pMotivoIngreso;
	}

	//-------------------------------------------------------------
	// Métodos
	//-------------------------------------------------------------

	/**
	 * Devuelve el nombre del paciente.
	 * @return Nombre del paciente.
	 */
	public String darNombre() 
	{
		return nombre;
	}

	/**
	 * Devuelve el apellido del paciente.
	 * @return Apellido del paciente.
	 */
	public String darApellido() 
	{
		return apellido;
	}

	/**
	 * Devuelve el ID del paciente.
	 * @return ID del paciente.
	 */
	public int darNumeroIdentificacion() 
	{
		return numeroIdentificacion;
	}

	/**
	 * Devuelve la edad del paciente.
	 * @return Edad del paciente.
	 */
	public int darEdad() 
	{
		return edad;
	}

	/**
	 * Devuelve el diagnóstico del paciente.
	 * @return Diagnóstico del paciente.
	 */
	public String darDiagnostico()
	{
		return diagnostico;
	}

	/**
	 * Devuelve los días de hospitalización del paciente.
	 * @return Días de hospitalización del paciente.
	 */
	public int darDiasHospitalizacion()
	{
		return diasHospitalizacion;
	}

	/**
	 * Devuelve el motivo de ingreso del paciente.
	 * @return Motivo de ingreso del paciente.
	 */
	public Motivo darMotivoIngreso()
	{
		return motivoIngreso;
	}


}
