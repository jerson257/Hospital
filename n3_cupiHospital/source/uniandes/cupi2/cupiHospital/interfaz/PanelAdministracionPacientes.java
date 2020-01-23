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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel para administrar los pacientes.
 */
public class PanelAdministracionPacientes extends JPanel implements ActionListener
{
	 //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Comando para ingresar un paciente.
     */
    private static final String INTERNAR = "INTERNAR";

    /**
     * Comando para dar de alta a un paciente.
     */
    private static final String DAR_DE_ALTA = "DAR_DE_ALTA";

    /**
     * Comando para buscar un paciente.
     */
    private static final String BUSCAR = "BUSCAR";

    /**
     * Comando para reubicar un paciente.
     */
    private static final String REUBICAR = "REUBICAR";
    
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazCupiHospital principal;

    //-----------------------------------------------------------------
    // Atributos de interfaz
    //-----------------------------------------------------------------
    
    /**
     * Botón para ingresar ingresar un paciente.
     */
    private JButton btnInternar;

    /**
     * Botón para dar de alta a un paciente.
     */
    private JButton btnDarDeAlta;

    /**
     * Botón para buscar un paciente.
     */
    private JButton btnBuscar;

    /**
     * Botón para reubicar un paciente.
     */
    private JButton btnReubicar;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

	/**
	 * Constructor del panel.
	 * @param ventanaPrincipal Ventana principal. ventanaPrincipal != null.
	 */
    public PanelAdministracionPacientes( InterfazCupiHospital ventanaPrincipal )
    {
        principal = ventanaPrincipal;
        setBorder( new TitledBorder( "Administración" ) );
        
        
        setLayout(new GridLayout(4, 1, 0, 10) );
        
        //Botón para ingresar un paciente.
        btnInternar = new JButton( "Internar" );
        btnInternar.setActionCommand( INTERNAR );
        btnInternar.addActionListener( this );
        add( btnInternar);
        
        // Botón para dar de alta un paciente.
        btnDarDeAlta = new JButton( "Dar de alta" );
        btnDarDeAlta.setActionCommand( DAR_DE_ALTA );
        btnDarDeAlta.addActionListener( this );
        add( btnDarDeAlta );
        
        // Botón para buscar un paciente.
        btnBuscar = new JButton( "Buscar" );
        btnBuscar.setActionCommand( BUSCAR );
        btnBuscar.addActionListener( this );
        add( btnBuscar );

        // Botón para reubicar un paciente.
        btnReubicar = new JButton( "Reubicar" );
        btnReubicar.setActionCommand( REUBICAR );
        btnReubicar.addActionListener( this );
        add( btnReubicar );
  
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

	/**
     * Manejo de los eventos de los botones.
     * @param e Evento asociado al click en un botón. e != null.
     */
    public void actionPerformed( ActionEvent e )
    {
    	String comando = e.getActionCommand();
    	
        if( INTERNAR.equals( comando ) )
        {
            principal.mostrarDialogoInternarPaciente( );

        }
        else if( DAR_DE_ALTA.equals( comando) ) 
        {
            principal.darDeAlta( );

        }
        else if( BUSCAR.equals( comando ) )
        {
            principal.buscar( );

        }
        else if( REUBICAR.equals( comando ) )
        {
            principal.mostrarDialogoReubicarPaciente( );
        }
    }
}
