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

import uniandes.cupi2.cupiHospital.mundo.Paciente.Motivo;
import uniandes.cupi2.cupiHospital.mundo.Unidad.Tipo;

/**
 * Clase que representa el hospital.
 */
public class Hospital {
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Cantidad de unidades del hospital.
	 */
	public final static int NUMERO_UNIDADES = 6;

	// -------------------------------------------------------------
	// Atributos
	// -------------------------------------------------------------

	/**
	 * Arreglo que contiente las unidades del hospital.
	 */
	private Unidad unidades[];
	// fijo.

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Construye el hospital con las siguientes unidades en el arreglo: unidades[0]
	 * - nombre: Unidad A, Tipo: Cuidados intensivos. unidades[1] - nombre: Unidad
	 * B, Tipo: Cuidados intermedios. unidades[2] - nombre: Unidad C, Tipo:
	 * Pediatría. unidades[3] - nombre: Unidad D, Tipo: Maternidad. unidades[4] -
	 * nombre: Unidad E, Tipo: Geriatría. unidades[5] - nombre: Unidad F, Tipo:
	 * Observación.
	 */
	public Hospital() {
		
		unidades = new Unidad[NUMERO_UNIDADES];
		unidades[0]= new Unidad("Unidad A",Tipo.CUIDADOS_INTENSIVOS);
		unidades[1]= new Unidad("Unidad B",Tipo.CUIDADOS_INTERMEDIOS);
		unidades[2]= new Unidad("Unidad C",Tipo.PEDIATRIA);
		unidades[3]= new Unidad("Unidad D",Tipo.MATERNIDAD);
		unidades[4]= new Unidad("unidad E",Tipo.GERIATRIA);
		unidades[5]= new Unidad("unidad F",Tipo.OBSERVACION);		
	}

	// -------------------------------------------------------------
	// Métodos
	// -------------------------------------------------------------

	/**
	 * Devuelve el arreglo con las unidades del hospital.
	 * 
	 * @return El arreglo de unidades.
	 */
	public Unidad[] darUnidades() {

		return unidades; 
	}

	/**
	 * Interna un nuevo paciente al hospital con los parámetros dados. No se permite
	 * internar un paciente si: - existe un paciente con el mismo número de
	 * identificación. - se desea internarlo en pediatría y tiene mas de 15 años
	 * (Unidad.EDAD_MAX_PEDIATRIA). - se desea internarlo en geriatría y tiene menos
	 * de 60 años (Unidad.EDAD_MIN_GERIATRIA).
	 * 
	 * @param pNombreUnidad         Nombre de la unidad a la que se ingresa el
	 *                              paciente.
	 * @param pNombrePaciente       Nombre del paciente. pNombrePaciente != null &&
	 *                              pNombrePaciente != "".
	 * @param pApellido             Apellido del paciente. pApellido != null
	 *                              &&pApellido != "".
	 * @param pNumeroIdentificacion ID del paciente. pNumeroidentificacion > 0.
	 * @param pEdad                 Edad del paciente. pEdad >= 0.
	 * @param pDiagnostico          Diagnóstico del paciente. pDiagnostico != null
	 *                              && pDiagnostico != "".
	 * @param pDiasHospitalizacion  Días de hospitalización del paciente.
	 *                              pDiasHospitalizacion >= 0.
	 * @param pMotivoIngreso        Motivo de ingreso del paciente en el hospital.
	 * @return true si logra ingresar el paciente, false en caso contrario.
	 */
	public boolean internarNuevoPaciente(String pNombreUnidad, String pNombrePaciente, String pApellido,
			int pNumeroIdentificacion, int pEdad, String pDiagnostico, int pDiasHospitalizacion,
			Motivo pMotivoIngreso) {
		if(buscarPaciente(pNumeroIdentificacion)!=null)
		{
			if(pNombreUnidad == "PEDIATRIA" && pEdad > Unidad.EDAD_MAX_PEDIATRIA)
			{
				return Boolean.FALSE;
			}
			if(pNombreUnidad == "GERIATRIA" && pEdad < Unidad.EDAD_MIN_GERIATRIA)
			{
				return Boolean.FALSE;
			}
		}
		else
		{
			Unidad unidad = buscarUnidad(pNombreUnidad);
			if(unidad != null)
			{
				unidad.agregarPaciente(pNombrePaciente, pApellido, pNumeroIdentificacion, pEdad, pDiagnostico, pDiasHospitalizacion, pMotivoIngreso);
				return Boolean.TRUE;
			}
		}	
		return Boolean.FALSE;
	}

	/**
	 * Devuelve un paciente dado su ID.
	 * 
	 * @param pNumeroIdentificacion ID del paciente. pNumeroIdentificacion > 0.
	 * @return El paciente que tiene el ID dado por parámetro, null en caso de no
	 *         encontrarlo.
	 */
	public Paciente buscarPaciente(int pNumeroIdentificacion) {
		Paciente paciente = null;
		if(pNumeroIdentificacion > 0)
		{
			for(Unidad unidades : unidades)
			{
				paciente = unidades.buscarPaciente(pNumeroIdentificacion);
				if(paciente!=null)
				{
					return paciente;
				}
			}			
		}
		return null;
	}

	/**
	 * Determina si el paciente, dado su ID por parámetro, es dado de alta.
	 * 
	 * @param pNumeroIdentificacion ID del paciente.
	 * @return True si el paciente es dado de alta, false en caso de no encontrarlo.
	 */
	public boolean darDeAltaPaciente(int pNumeroIdentificacion) {
		// TODO Parte3 PuntoF: Complete el método según la documentación dada.
	}

	/**
	 * Cambia de unidad a un paciente dado su ID por parámetro, si se cumplen las
	 * siguiente condiciones. - Si la unidad destino es de tipo PEDIATRIA y el
	 * paciente no supera la edad máxima. - Si la unidad destino es de tipo
	 * GERIATRIA y el paciente no está por debajo de la edad mínima. - Si la unidad
	 * de destino no es la misma en la que se encuentra actualmente el paciente.
	 * post:<br>
	 * Se retira de la unidad original solo si es posible reasignarlo en la unidad
	 * destino.
	 * 
	 * @param pNumeroIdentificacion ID del paciente.
	 * @param pNuevaUnidad          Unidad a la que será trasferido el paciente.
	 * @return True si pudo reubicar el paciente, false en caso contrario.
	 */
	public boolean reubicarPaciente(int pNumeroIdentificacion, String pNuevaUnidad) {
		// TODO Parte3 PuntoG: Complete el método según la documentación dada.
	}

	/**
	 * Busca la unidad a la que pertenece un paciente.
	 * 
	 * @param pNumeroIdentificacion ID del paciente.
	 * @return La unidad a la que pertenece el paciente, null en caso de no
	 *         encontrar el paciente.
	 */
	public Unidad buscarUnidadPaciente(int pNumeroIdentificacion) {
		// TODO Parte3 PuntoH: Complete el método según la documentación dada.
	}

	/**
	 * Busca una unidad dado su nombre.
	 * 
	 * @param pNombreUnidad Nombre de la unidad.
	 * @return La unidad cuyo nombre es dado por parámetro, null en caso de no
	 *         encontrarla.
	 */
	public Unidad buscarUnidad(String pNombreUnidad) {
		
		for(Unidad unidad : unidades)
		{
			if(unidad.darNombre() == pNombreUnidad)	
			{
				return unidad;
			}			
		}
		return null;
	}

	/**
	 * Calcula la cantidad total de pacientes en el hospital.
	 * 
	 * @return Cantidad total de pacientes en el hospital.
	 */
	public int darCantidadTotalPacientes() {
		// TODO Parte3 PuntoJ: Complete el método según la documentación dada.
	}

	/**
	 * Retorna el paciente de mayor edad en el hospital. Si hay dos o más pacientes
	 * con la edad máxima, retorna cualquiera de los dos.
	 * 
	 * @return Paciente de mayor edad, null si no hay pacientes en el hospital.
	 */
	public Paciente darPacienteMayorEdad() {
		// TODO Parte3 PuntoK: Complete el método según la documentación dada.
	}

	/**
	 * Asigna el encargado con los valores dados por parámetro a la unidad dada por
	 * parámetro, si la unidad ya tenía encargado no lo asigna.
	 * 
	 * @param pNombre           Nombre del encargado. pNombre != null && pNombre !=
	 *                          "".
	 * @param pAniosExperiencia Años de experiencia del encargado. pAniosExperiencia
	 *                          > 0.
	 * @param pUnidad           Nombre de la unidad para asignar enccargado. pUnidad
	 *                          != null && pUnidad != "".
	 * @return True si el encargado se pudo asignar, false si la unidad ya tenía
	 *         encargado o si no existe una unidad con el nombre dado.
	 */
	public boolean asignarEncargado(String pNombre, int pAniosExperiencia, String pUnidad) {
		// TODO Parte3 PuntoL: Complete el método según la documentación dada.
	}

	// -----------------------------------------------------------------
	// Puntos de Extensión
	// -----------------------------------------------------------------

	/**
	 * Método para la extensión 1.
	 * 
	 * @return Respuesta1.
	 */
	public String metodo1() {
		return "Respuesta 1.";
	}

	/**
	 * Método para la extensión 2.
	 * 
	 * @return Respuesta2.
	 */
	public String metodo2() {
		return "Respuesta 2.";
	}

}