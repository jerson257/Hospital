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
package uniandes.cupi2.cupiHospital.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import uniandes.cupi2.cupiHospital.mundo.Paciente;
import uniandes.cupi2.cupiHospital.mundo.Paciente.Motivo;
import uniandes.cupi2.cupiHospital.mundo.Unidad;
import uniandes.cupi2.cupiHospital.mundo.Unidad.Tipo;

/**
 * Clase usada para verificar que los métodos de la clase Unidad están correctamente implementados.
 */
public class UnidadTest
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Unidad de prueba.
	 */
	private Unidad unidad;

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------
	
	/**
	 * Escenario 1: Construye una nueva unidad.
	 * unidad - nombre: Unidad de prueba, tipo: Observación.
	 */
	@Before
	public void setupEscenario1( )
	{
		unidad = new Unidad("Unidad de prueba", Tipo.OBSERVACION);
	}
	
	/**
	 * Prueba 1: Crear la unidad.
	 */
	@Test
	public void testUnidad()
	{
		assertEquals("El nombre no corresponde", "Unidad de prueba", unidad.darNombre());
		assertEquals("El tipo no corresponde", Tipo.OBSERVACION, unidad.darTipo());
		assertNull("El encargado debería ser null", unidad.darEncargado());
	}	
	
	/**
     * Prueba 2: Agregar un paciente a la unidad.
     */
	@Test
	public void testAgregarPaciente()
	{
		unidad.agregarPaciente("Germán", "Romero", 273, 22, "Dolor general.", 1, Motivo.ACCIDENTE);
		assertEquals("La cantidad de pacientes en la unidad debería ser 1.", 1, unidad.darPacientes().size());
	}
	
	/**
     * Prueba 3: Agregar un paciente a la unidad.
     */
	@Test
	public void testDarDeAltaPaciente()
	{
		unidad.agregarPaciente("Germán", "Romero", 273, 22, "Dolor general.", 1, Motivo.ACCIDENTE);
		unidad.darDeAltaPaciente(273);
		assertEquals("La cantidad de pacientes en la unidad debería ser 0.", 0, unidad.darPacientes().size());
	}
	
	
	/**
     * Prueba 4: Verificar que la busqueda de un paciente sea correcta.
     */
	@Test
	public void testBuscarPaciente()
	{
		unidad.agregarPaciente("Germán", "Romero", 273, 22, "Dolor general.", 1, Motivo.ACCIDENTE);
		Paciente rta = unidad.buscarPaciente(273);
		assertNotNull("El paciente debería existir",rta);
		assertEquals("La información no coincide.", "Germán", rta.darNombre());
		
	}
	
	/**
	 * Prueba 5: Verificar la busqueda del paciente con mayor edad.
	 */
	@Test
	public void testDarPacienteMayorEdad()
	{
		unidad.agregarPaciente("Germán", "Romero", 273, 22, "Dolor general.", 1, Motivo.ACCIDENTE);
		unidad.agregarPaciente("Germán2", "Romero2", 275, 24, "Dolor general.", 1, Motivo.ACCIDENTE);
		unidad.agregarPaciente("Germán3", "Romero3", 276, 32, "Dolor general.", 1, Motivo.ACCIDENTE);
		unidad.agregarPaciente("Germán4", "Romero4", 272, 28, "Dolor general.", 1, Motivo.ACCIDENTE);
		unidad.agregarPaciente("Germán5", "Romero5", 278, 32, "Dolor general.", 1, Motivo.ACCIDENTE);
		unidad.agregarPaciente("Germán6", "Romero6", 279, 12, "Dolor general.", 1, Motivo.ACCIDENTE);
		assertNotNull("No debería ser nula la respuesta", unidad.darPacienteMayorEdad());
		assertEquals("No está encontrando correctamente el paciente de mayor edad.", 32, unidad.darPacienteMayorEdad().darEdad());
		assertTrue("El paciente de mayor edad no es el que debería", (unidad.darPacienteMayorEdad().darNombre().equals("Germán3")||unidad.darPacienteMayorEdad().darNombre().equals("Germán5")));
	}
	
	/**
	 * Prueba 6: Verificar la asignación de un encargado.
	 * 1. cuando no hay encargado.
	 * 2. cuando hay encargado.
	 */
	@Test
	public void testAsignarEncargado()
	{
		boolean pudo = unidad.asignarEncargado("Luis", 15);
		assertTrue("Debería ser verdadero la respuesta de asignarEncargado", pudo);
		assertNotNull("No debería ser nulo el encargado", unidad.darEncargado());
		
		boolean pudo2 = unidad.asignarEncargado("Juan", 12);
		assertFalse("Debería ser falso la respuesta de asignarEncargado", pudo2);
		assertNotNull("No debería ser nulo el encargado", unidad.darEncargado());
		assertEquals("El nombre no debería haber cambiado", "Luis", unidad.darEncargado().darNombre());
		assertEquals("Los años de experiencia no deberían haber cambiado", 15, unidad.darEncargado().darAniosExperiencia());
	}
	
	
}
