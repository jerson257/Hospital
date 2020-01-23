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
package uniandes.cupi2.cupiHospital.interfaz;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.cupiHospital.mundo.Hospital;
import uniandes.cupi2.cupiHospital.mundo.Paciente;
import uniandes.cupi2.cupiHospital.mundo.Paciente.Motivo;
import uniandes.cupi2.cupiHospital.mundo.Unidad;


/**
 * Ventana principal de la aplicación.
 */
public class InterfazCupiHospital extends JFrame
{
	//-----------------------------------------------------------------
	// Atributos
	//-----------------------------------------------------------------

	/**
	 * Clase principal del mundo.
	 */
	private Hospital hospital;

	//-----------------------------------------------------------------
	// Atributos de la interfaz
	//-----------------------------------------------------------------

	/**
	 * Panel con la imagen de la tienda de mascotas.
	 */
	private PanelImagen panelImagen;

	/**
	 * Panel con las extensiones.
	 */
	private PanelOpciones panelOpciones;

	/**
	 * Panel para mostrar la información de las unidades.
	 */
	private PanelUnidad panelUnidad;
	
	/**
	 * Panel para las opciones de administración del hospital.
	 */
	private PanelAdministracionPacientes panelAdministracionPacientes;


	//-----------------------------------------------------------------
	// Constructores
	//-----------------------------------------------------------------

	/**
     * Constructor de la interfaz <br>
     */
	public InterfazCupiHospital()
	{
		// Crea la clase principal
		hospital = new Hospital();
		setTitle("CupiHospital");

		// Construye la forma
		setLayout( new BorderLayout( ) );
		setSize( 660, 420 );
		setResizable(false);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		//Creación de los paneles aquí
		panelImagen = new PanelImagen();
		add( panelImagen, BorderLayout.NORTH );

		panelUnidad = new PanelUnidad(this);
		add( panelUnidad, BorderLayout.CENTER );
		
		panelOpciones = new PanelOpciones( this );
		add( panelOpciones, BorderLayout.SOUTH);
		
		panelAdministracionPacientes = new PanelAdministracionPacientes(this);
		add(panelAdministracionPacientes, BorderLayout.EAST);

	}

	//-----------------------------------------------------------------
	// Métodos
	//-----------------------------------------------------------------
	
	/**
	 * Intenta dar de alta un paciente.
	 * <b>post: </b> Se muestra un mensaje que informa si el paciente fue dado de alta o no.
	 */
	public void darDeAlta()
	{
		String strNumero = JOptionPane.showInputDialog( this, "Número de identificación del paciente:", "Dar de alta paciente", JOptionPane.QUESTION_MESSAGE );
		
		if( strNumero == null ){
			return;
		}
		
		if( strNumero.equals( "" ) )
		{
			JOptionPane.showMessageDialog( this, "El número de identificación no es válido.", "Error", JOptionPane.ERROR_MESSAGE );
		}
		else
		{
			try
			{
				int numeroIdentificacion = Integer.parseInt( strNumero );

				if( hospital.darDeAltaPaciente(numeroIdentificacion)==false )
				{
					JOptionPane.showMessageDialog( this, "No se encontró el paciente para ser dado de alta.", "Error", JOptionPane.ERROR_MESSAGE );
				}
				else
				{
					JOptionPane.showMessageDialog( this, "El paciente fue dado de alta.", "Dar de alta", JOptionPane.INFORMATION_MESSAGE );
					panelUnidad.mostrarInfoUnidad();
				}
			}
			catch( NumberFormatException e )
			{
				JOptionPane.showMessageDialog( this, "El número de identificación del paciente es inválido.", "Error", JOptionPane.ERROR_MESSAGE );
			}
		}
	}

	/**
	 * Busca un paciente por número de identificación.
	 * <b>post: </b> Se muestra un diálogo con la información del paciente, o un mensaje que informa que no se encontró el paciente.
	 */
	public void buscar() 
	{
		String numeroS = JOptionPane.showInputDialog( this, "Número de identificación del paciente:", "Búsqueda paciente", JOptionPane.QUESTION_MESSAGE );

		if( numeroS == null ){
			return;
		}
		
		if( numeroS.equals( "" ) )
		{
			JOptionPane.showMessageDialog( this, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE );
		}
		else
		{
			try
            {
                int numero = Integer.parseInt( numeroS );
                Paciente paciente = hospital.buscarPaciente( numero );
                if( paciente == null )
                {
                    JOptionPane.showMessageDialog( this, "Ningún paciente tiene el número de identificación especificado.", "Búsqueda paciente", JOptionPane.INFORMATION_MESSAGE );
                }
                else
                {
                    Unidad unidadActual = hospital.buscarUnidadPaciente(numero);
                    DialogoInformacionPaciente dialogo = new DialogoInformacionPaciente( paciente, unidadActual ) ;
                    dialogo.setLocationRelativeTo(this);
                    dialogo.setVisible( true );
                }
            }
            catch( NumberFormatException e )
            {
                JOptionPane.showMessageDialog( this, "Ingrese un número de identificación válido.", "Error", JOptionPane.ERROR_MESSAGE );
            }
		}
	}

	/**
	 * Busca una unidad dado su nombre.
	 * @param nombreUnidad Nombre de la unidad.
	 * @return La unidad cuyo nombre es dado por parámetro, null en caso de no encontrarla.
	 */
	public Unidad buscarUnidad(String nombreUnidad)
	{
		return hospital.buscarUnidad(nombreUnidad);
	}

	/**
	 * Devuelve el arreglo con las unidades del hospital.
	 * @return El arreglo de unidades.
	 */
	public Unidad[] obtenerUnidades() 
	{
		return hospital.darUnidades();
	}
	

	/**
	 * Interna un nuevo paciente al hospital con los parámetros dados.
	 * @param nombreUnidad Nombre de la unidad a la que se ingresa el paciente.
	 * @param nombre Nombre del paciente.
	 * @param apellido Apellido del paciente. 
	 * @param numeroIdentificacion ID del paciente.
	 * @param edad Edad del paciente.
	 * @param diagnostico Diagnóstico del paciente. 
	 * @param diasHospitalizacion Días de hospitalización del paciente.
	 * @param motivoIngreso Motivo de ingreso del paciente en el hospital.
	 * @return True si logra agregar, false en caso contrario.
	 */
	public boolean internarNuevoPaciente(String nombreUnidad, String nombre, String apellido, int numeroIdentificacion, int edad, String diagnostico, int diasHospitalizacion, Motivo motivoIngreso)
	{
		boolean agrego=hospital.internarNuevoPaciente(nombreUnidad, nombre, apellido, numeroIdentificacion, edad, diagnostico, diasHospitalizacion, motivoIngreso);
		if(agrego==true)
		{
			JOptionPane.showMessageDialog( this, "El paciente fue ingresado exitosamente.", "Ingreso exitoso", JOptionPane.INFORMATION_MESSAGE );
			panelUnidad.mostrarInfoUnidad();	
		}
		else
		{
			JOptionPane.showMessageDialog( this, "El paciente no pudo ser ingresado. Verifique los datos.", "Error", JOptionPane.ERROR_MESSAGE );
			
		}
		
		return agrego;
	}
	
	/**
	 * Cambia de unidad a un paciente dado su ID por parámetro.
	 * @param numeroIdentificacion ID del paciente.
	 * @param nuevaUnidad Unidad a la que será trasferido el paciente.
	 * @return True si pudo reubicar el paciente, false en caso contrario.
	 */
	public boolean reubicarPaciente( int numeroIdentificacion, String nuevaUnidad )
	{
		boolean rta= hospital.reubicarPaciente(numeroIdentificacion, nuevaUnidad);
		panelUnidad.mostrarInfoUnidad();
		return rta;
	}
	
	/**
	 * Muestra un mensaje con el paciente con mayor edad.
	 */
	public void mayorEdad() 
	{
		Paciente mayorEdad = hospital.darPacienteMayorEdad();
		if(mayorEdad==null)
		{
			JOptionPane.showMessageDialog(this, "No hay pacientes en el hospital.", "Paciente mayor edad", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			String rta = "El paciente con mayor edad es: " + mayorEdad.darNombre()+" "+ mayorEdad.darApellido() + " con identificación: " + mayorEdad.darNumeroIdentificacion();
			JOptionPane.showMessageDialog(this, rta, "Paciente mayor edad", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Muestra un mensaje con el total de paciente del hospital.
	 */
	public void totalPacientes() 
	{
		int total = hospital.darCantidadTotalPacientes();
		JOptionPane.showMessageDialog(this, "El total de pacientes en el hospital es: "+ total, "Total de pacientes", JOptionPane.INFORMATION_MESSAGE);
	
	}
	
	/**
	 * Asgigna un encargado a la unidad actual.
	 * @param pNombre nombre del encargado. pNombre != null && pNombre != "".
	 * @param pAniosExperiencia Años de experiencia del encargado. pAniosExperiencia > 0.
	 */
	public void asignarEncargado(String pNombre, int pAniosExperiencia) 
	{
		boolean pudo = hospital.asignarEncargado(pNombre, pAniosExperiencia, panelUnidad.obtenerUnidadSeleccionada());
		if(pudo==true)
		{
			JOptionPane.showMessageDialog(this, "Se asignó el encargado.", "Asignar encargado", JOptionPane.INFORMATION_MESSAGE);
			panelUnidad.mostrarInfoUnidad();
		}
		else
		{
			JOptionPane.showMessageDialog(this, "No se pudo asignar el encargado porque la unidad ya tiene encargado.", "Asignar encargado", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Muestra el diálogo de los pacientes ubicado en una unidad determinada.
	 */
	public void mostrarDialogoPacientesUnidad()
	{
		String nombreUnidad = panelUnidad.obtenerUnidadSeleccionada( );
        ArrayList<Paciente> pacientes = hospital.buscarUnidad(nombreUnidad).darPacientes();

        DialogoPacientesUnidad dialogo = new DialogoPacientesUnidad( this, pacientes );
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible( true );
	}

	/**
	 * Muestra el diálogo para internar un nuevo paciente al hospital.
	 */
	public void mostrarDialogoInternarPaciente() 
	{
		DialogoInternarPaciente dialogo = new DialogoInternarPaciente(this);
		dialogo.setLocationRelativeTo(this);
		dialogo.setVisible( true );
	}

	/**
	 * Muestra el diálogo para reubicar un paciente.
	 */
	public void mostrarDialogoReubicarPaciente() 
	{
		DialogoReubicarPaciente dialogo = new DialogoReubicarPaciente(this, hospital.darUnidades());
		dialogo.setLocationRelativeTo(this);
		dialogo.setVisible( true );
	}

	/**
	 * Muestar el diálogo para asignar un encargado.
	 */
	public void mostrarDialogoAsignarEncargado() 
	{
		DialogoAsignarEncargado dialogo = new DialogoAsignarEncargado(this);
		dialogo.setLocationRelativeTo(this);
		dialogo.setVisible(true);
		
	}

	//-----------------------------------------------------------------
	// Puntos de Extensión
	//-----------------------------------------------------------------

	/**
	 * Método para la extensión 1.
	 */
	public void reqFuncOpcion1( )
	{
		String resultado = hospital.metodo1();
		JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
	}

	/**
	 * Método para la extensión 2.
	 */
	public void reqFuncOpcion2( )
	{
		String resultado = hospital.metodo2();
		JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
	}

	//-----------------------------------------------------------------
	// Main
	//-----------------------------------------------------------------

	/**
     * Este método ejecuta la aplicación, creando una nueva interfaz.
     * @param args  Argumentos del llamado. No se requiere ninguno.
     */
	public static void main( String[] args )
	{
		InterfazCupiHospital interfaz = new InterfazCupiHospital();
		interfaz.setVisible( true );
	}

}
