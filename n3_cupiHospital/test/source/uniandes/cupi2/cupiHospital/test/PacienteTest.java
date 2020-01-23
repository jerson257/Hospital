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

import org.junit.Before;
import org.junit.Test;

import uniandes.cupi2.cupiHospital.mundo.Paciente;
import uniandes.cupi2.cupiHospital.mundo.Paciente.Motivo;

/**
 * Clase usada para verificar que los métodos de la clase Paciente están correctamente implementados.
 */
public class PacienteTest
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Paciente de prueba.
	 */
	private Paciente paciente;

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Escenario 1: Construye un nuevo paciente con la siguiente información:
	 * paciente - nombre: Germán, apellido: Romero, número de identificación: 273, edad: 22, diagnóstico: Dolor general, dias de hospitalización: 1, motivo ingreso: accidente.
	 */
	@Before
	public void setupEscenario1( )
	{
		paciente = new Paciente("Germán", "Romero", 273, 22, "Dolor general.", 1, Motivo.ACCIDENTE);
	}

	/**
     * Prueba 1: Obtener el nombre del paciente.
     */
	@Test
	public void testDarNombre()
	{
		assertEquals("El nombre esperado es Germán.", "Germán", paciente.darNombre());
	}
	
	/**
     * Prueba 2: Obtener el apellido del paciente.
     */
	@Test
	public void testDarApellido()
	{
		assertEquals("El apellido esperado es Romero.", "Romero", paciente.darApellido());
	}
	
	/**
     * Prueba 3: Obtener el número de identificación del paciente.
     */
	@Test
	public void testDarNumeroIdentificacion()
	{
		assertEquals("El numero de identificación esperado es 273.", 273, paciente.darNumeroIdentificacion());
	}
	
	/**
     * Prueba 4: Obtener la edad del paciente.
     */
	@Test
	public void testDarEdad()
	{
		assertEquals("La edad esperada es 22.", 22, paciente.darEdad());
	}
	
	/**
     * Prueba 5: Obtener el diagnóstico del paciente.
     */
	@Test
	public void testDarDiagnostico()
	{
		assertEquals("El diagnóstico esperado es: Dolor general.", "Dolor general.", paciente.darDiagnostico());
	}
	
	/**
     * Prueba 6: Obtener los días de hospitalización del paciente.
     */
	@Test
	public void testDarDiasHospitalizacion()
	{
		assertEquals("Los días de hospitalización esperados son: 1.", 1, paciente.darDiasHospitalizacion());
	}
	
	/**
     * Prueba 7: Obtener el motivo de ingreso del paciente.
     */
	@Test
	public void testDarMotivoIngreso()
	{
		assertEquals("El motivo de ingreso esperado es: Accidente", Motivo.ACCIDENTE, paciente.darMotivoIngreso());
	}
}
