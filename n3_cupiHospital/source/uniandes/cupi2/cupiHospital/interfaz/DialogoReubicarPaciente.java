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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uniandes.cupi2.cupiHospital.mundo.Unidad;
import uniandes.cupi2.cupiHospital.mundo.Unidad.Tipo;

/**
 * Diálogo para reubicar un paciente.
 */
public class DialogoReubicarPaciente extends JDialog implements ActionListener
{

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Comando para reubicar un paciente.
	 */
	public static final String ACEPTAR_REUBICAR = "ACEPTAR_REUBICAR";

	/**
	 * Comando para cerrar el diálogo.
	 */
	public static final String CANCELAR_REUBICAR = "CANCELAR_REUBICAR";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
	/**
	 * Ventana principal de la aplicación.
	 */
	private InterfazCupiHospital principal;

	// -----------------------------------------------------------------
	// Atributos de Interfaz
	// -----------------------------------------------------------------

	/**
	 * Etiqueta para mostrar el número de identificación del paciente.
	 */
	private JLabel etiquetaNumeroIdentificacionPaciente;

	/**
	 * Etiqueta para mostrar la unidad a la que será reubicado el paciente.
	 */
	private JLabel etiquetaUnidad;

	/**
	 * Cuadro de texto para ingresar el número de identificación del paciente.
	 */
	private JTextField txtNumeroIdentificacionPaciente;
	
	/**
	 * Combo donde se muestran las unidades.
	 */
	private JComboBox comboUnidades;

	/**
	 * Botón para aceptar el cambio de unidad de un paciente.
	 */
	private JButton btnAceptar;

	/**
	 * Botón para cancelar el cambio de unidad y cerrar el diálogo.
	 */
	private JButton btnCancelar;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Constructor del diálogo.
	 * @param ventanaPrincipal Ventana principal. ventanaPrincipal != null.
	 * @param unidades Unidades del hospital.
	 */
	public DialogoReubicarPaciente(InterfazCupiHospital ventanaPrincipal, Unidad[] unidades) 
	{
		setSize(500, 200);
		setTitle( "Reubicar paciente");
		principal=ventanaPrincipal;
		JPanel panelAuxiliar = new JPanel();
		panelAuxiliar.setLayout( new GridBagLayout( ) );
		etiquetaUnidad = new JLabel( "Nueva unidad:" );
		etiquetaNumeroIdentificacionPaciente = new JLabel( "Número de identificación del paciente:" );
		txtNumeroIdentificacionPaciente = new JTextField( );
		comboUnidades = new JComboBox( );
		btnAceptar = new JButton( "Aceptar" );
		btnAceptar.setActionCommand( ACEPTAR_REUBICAR );
		btnAceptar.addActionListener( this );
		btnCancelar = new JButton( "Cancelar" );
		btnCancelar.setActionCommand( CANCELAR_REUBICAR );
		btnCancelar.addActionListener( this );
		JPanel panelBotones = new JPanel( );
		for( int i = 0; i < unidades.length; i++ )
		{
			comboUnidades.addItem( unidades[ i ].darNombre( )+ " - " + parsearTipo(unidades[ i ].darTipo( )) );
		}

		panelBotones.add( btnAceptar );
		panelBotones.add( btnCancelar );
		GridBagConstraints gbc = new GridBagConstraints( );
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets( 5, 5, 5, 5 );
		panelAuxiliar.add( etiquetaNumeroIdentificacionPaciente, gbc );
		gbc.gridx = 1;
		panelAuxiliar.add( txtNumeroIdentificacionPaciente, gbc );
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelAuxiliar.add( etiquetaUnidad, gbc );
		gbc.gridx = 1;
		panelAuxiliar.add( comboUnidades, gbc );
		gbc.gridx = 0;
		gbc.gridy = 2;
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
	 * Manejo de los eventos de los botones.
	 * @param e Evento asociado al click en un botón. e != null.
	 */
	public void actionPerformed( ActionEvent e )
	{
		String comando = e.getActionCommand( );
		if( comando.equals( ACEPTAR_REUBICAR ) )
		{
			String numeroStr = txtNumeroIdentificacionPaciente.getText();
			if(numeroStr==null || numeroStr.equals(""))
			{
				JOptionPane.showMessageDialog( this, "El número de identificación no es válido.", "Error", JOptionPane.ERROR_MESSAGE );
			}
			else
			{
				try
				{
					int numeroIdentificacion = Integer.parseInt(numeroStr);
					String unidad = ( String )comboUnidades.getSelectedItem( );
					String nuevaUnidad = unidad.substring( 0, unidad.indexOf( " - " ));
					boolean reubico = principal.reubicarPaciente(numeroIdentificacion, nuevaUnidad);
					if(reubico==true)
					{
						dispose();
						JOptionPane.showMessageDialog( principal, "El paciente fue reubicado.", "Paciente reubicado", JOptionPane.INFORMATION_MESSAGE );
					}
					else
					{
						JOptionPane.showMessageDialog( this, "El paciente no pudo ser reubicado.", "Error", JOptionPane.ERROR_MESSAGE );
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog( this, "El número de identificación no debe tener letras.", "Error", JOptionPane.ERROR_MESSAGE );
				}
			}

		}
		else if( comando.equals( CANCELAR_REUBICAR ) )
		{
			dispose();
		}
	}

}
