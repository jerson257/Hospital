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

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uniandes.cupi2.cupiHospital.mundo.Paciente;
import uniandes.cupi2.cupiHospital.mundo.Paciente.Motivo;
import uniandes.cupi2.cupiHospital.mundo.Unidad;
import uniandes.cupi2.cupiHospital.mundo.Unidad.Tipo;

/**
 * Diálogo para ver la información de un paciente.
 */
public class DialogoInformacionPaciente extends JDialog implements ActionListener
{
	// -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para cerrar el diálogo.
     */
    private static final String CERRAR = "Cerrar";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Botón para cerrar el diálogo.
     */
    private JButton btnCerrar;

	/**
	 * Etiqueta con el nombre del paciente.
	 */
    private JLabel lblNombre;

	/**
	 * Etiqueta con el apellido del paciente.
	 */
    private JLabel lblApellido;

	/**
	 * Etiqueta con el número de identificación del paciente.
	 */
    private JLabel lblNumeroidentificacion;
    
	/**
	 * Etiqueta con la edad del paciente.
	 */
    private JLabel lblEdad;
    
	/**
	 * Etiqueta con el motivo de ingreso del paciente.
	 */
    private JLabel lblMotivoIngreso;

	/**
	 * Etiqueta con los días de hospitalización del paciente.
	 */
    private JLabel lblDiasHospitalizacion;

	/**
	 * Etiqueta con el diagnóstico del paciente
	 */
    private JLabel lblDiagnostico;

	/**
	 * Etiqueta con la unidad donde se ingresará el paciente.
	 */
    private JLabel lblUnidad;

	/**
	 * Campo de texto con el nombre del paciente.
	 */
    private JTextField txtNombre;

	/**
	 * Campo de texto con el apellido del paciente.
	 */
    private JTextField txtApellido;

	/**
	 * Campo de texto con el número de identificación del paciente.
	 */
    private JTextField txtNumeroIdentificacion;
    
	/**
	 * Campo de texto con la edad del paciente.
	 */
    private JTextField txtEdad;
    
	/**
	 *  Campo de texto con el motivo de ingreso del paciente.
	 */
    private JTextField txtMotivoIngreso;

	/**
	 * Campo de texto con los días de hospitalización del paciente.
	 */
    private JTextField txtDiasHospitalizacion;

	/**
	 * Campo de texto con el diágnostico del paciente.
	 */
    private JTextField txtDiagnostico;

	/**
	 *  Campo de texto con la unidad donde se encuentra el paciente.
	 */
    private JTextField txtUnidad;
    
 // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del diálogo
     * @param unidadActual 
     * @param paciente Paciente del que se va a mostrar la información. paciente!=null
     * @param unidadActual Unidad donde se encuentra el paciente. unidadActual!=null
     */
    public DialogoInformacionPaciente(Paciente paciente, Unidad unidadActual  )
    {
    	setSize(350, 300);
        JPanel panelAuxiliar= new JPanel();
        panelAuxiliar.setBorder( BorderFactory.createTitledBorder( "Datos del paciente" ) );
        panelAuxiliar.setLayout( new GridBagLayout( ) );

        // Crea las etiquetas y los campos de texto y los agrega al panel
        lblApellido = new JLabel( "Apellido:" );
        lblNombre = new JLabel( "Nombre:" );
        lblNumeroidentificacion = new JLabel( "Número ID:" );
        lblEdad = new JLabel( "Edad:" );
        lblMotivoIngreso = new JLabel( "Motivo ingreso:" );
        lblDiasHospitalizacion = new JLabel( "Dias hospitalización:" );
        lblDiagnostico = new JLabel( "Diagnóstico:" );
        lblUnidad = new JLabel( "Unidad:" );

        txtNombre = new JTextField( paciente.darNombre() );
        txtNombre.setEditable( false );
        txtApellido = new JTextField( paciente.darApellido() );
        txtApellido.setEditable( false );
        txtNumeroIdentificacion = new JTextField( ""+paciente.darNumeroIdentificacion() );
        txtNumeroIdentificacion.setEditable( false );
        txtEdad = new JTextField( ""+paciente.darEdad() );
        txtEdad.setEditable( false );
        txtMotivoIngreso = new JTextField( parsearMotivo(paciente.darMotivoIngreso()) );
        txtMotivoIngreso.setEditable( false );
        txtDiasHospitalizacion = new JTextField( ""+paciente.darDiasHospitalizacion() );
        txtDiasHospitalizacion.setEditable( false );
        txtDiagnostico = new JTextField( paciente.darDiagnostico() );
        txtDiagnostico.setEditable( false );
        txtUnidad = new JTextField( unidadActual.darNombre() + " - " + parsearTipo(unidadActual.darTipo()) );
        txtUnidad.setEditable( false );

        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets( 0, 0, 3, 3 );
        lblNumeroidentificacion.setPreferredSize( new Dimension( 70, 20 ) );
        panelAuxiliar.add( lblNumeroidentificacion, gbc );
        gbc.gridx = 1;
        txtNumeroIdentificacion.setPreferredSize( new Dimension( 70, 20 ) );
        panelAuxiliar.add( txtNumeroIdentificacion, gbc );

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelAuxiliar.add( lblNombre, gbc );
        gbc.gridx = 1;
        panelAuxiliar.add( txtNombre, gbc );

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelAuxiliar.add( lblApellido, gbc );
        gbc.gridx = 1;
        panelAuxiliar.add( txtApellido, gbc );

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelAuxiliar.add( lblEdad, gbc );
        gbc.gridx = 1;
        panelAuxiliar.add( txtEdad, gbc );

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelAuxiliar.add( lblMotivoIngreso, gbc );
        gbc.gridx = 1;
        panelAuxiliar.add( txtMotivoIngreso, gbc );

        gbc.gridx = 0;
        gbc.gridy = 5;
        panelAuxiliar.add( lblDiasHospitalizacion, gbc );
        gbc.gridx = 1;
        panelAuxiliar.add( txtDiasHospitalizacion, gbc );

        gbc.gridx = 0;
        gbc.gridy = 6;
        panelAuxiliar.add( lblDiagnostico, gbc );
        gbc.gridx = 1;
        panelAuxiliar.add( txtDiagnostico, gbc );
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        panelAuxiliar.add( lblUnidad, gbc );
        gbc.gridx = 1;
        panelAuxiliar.add( txtUnidad, gbc );

        // Crea los botones y los agrega al panel
        JPanel panelBotones = new JPanel( );
        btnCerrar = new JButton( CERRAR );
        btnCerrar.setActionCommand( CERRAR );
        btnCerrar.addActionListener( this );
        panelBotones.add( btnCerrar );

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        panelAuxiliar.add( panelBotones, gbc );
        
        add(panelAuxiliar);
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
	 * Cambia el tipo de la unidad de Tipo a String.
	 * @param pTipo tipo de la unidad.
	 * @return Tipo de la unidad como cadena de caracteres.
	 */
	private String parsearTipo (Tipo pTipo)
	{
		String rta = "Cuidados intensivos";
		if(pTipo==Tipo.CUIDADOS_INTERMEDIOS)
		{
			rta= "Cuidados intermedios";
		}
		else if(pTipo == Tipo.PEDIATRIA)
		{
			rta= "Pediatría";
		}
		else if(pTipo == Tipo.MATERNIDAD)
		{
			rta= "Maternidad";
		}
		else if (pTipo == Tipo.GERIATRIA)
		{
			rta= "Geriatría";
		}
		else if (pTipo == Tipo.OBSERVACION)
		{
			rta = "Observación";
		}
		return rta;
	}
    
	/**
	 * Cambia el motivo del paciente de Motivo a String.
	 * @param pMotivo Motivo de ingreso del paciente.
	 * @return Motivo del paciente como cadena de caracteres.
	 */
	private String parsearMotivo (Motivo pMotivo)
	{
		String rta= "Infección";
		if(pMotivo == Motivo.ACCIDENTE)
		{
			rta="Accidente";
		}
		else if (pMotivo == Motivo.PARTO)
		{
			rta= "Parto";
		}
		else if (pMotivo == Motivo.TRATAMIENTO)
		{
			rta = "Tratamiento";
		}
		else if (pMotivo == Motivo.CIRUGIA)
		{
			rta= "Cirugía";
		}
		return rta;
	}
	
	/**
	 * Manejo de los eventos de los botones.
	 * @param e Evento asociado al click en un botón. e != null.
	 */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );

        if( comando.equals( CERRAR ) )
        {
            dispose();
        }
    }
    
    
}
