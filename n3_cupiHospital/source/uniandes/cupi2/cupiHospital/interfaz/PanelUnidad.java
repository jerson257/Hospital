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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiHospital.mundo.Unidad;
import uniandes.cupi2.cupiHospital.mundo.Unidad.Tipo;

/**
 * Panel para mostrar la información de las unidades.
 */
public class PanelUnidad extends JPanel implements ActionListener {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Comando para mostrar una unidad.
	 */
	private static final String MOSTRAR_UNIDAD = "MOSTRAR_UNIDAD";

	/**
	 * Comando para mostrar los pacientes de una unidad.
	 */
	private static final String MOSTRAR_PACIENTES = "MOSTRAR_PACIENTES";

	/**
	 * Comando para asignar un encargado.
	 */
	private final static String ASIGNAR_ENCARGADO = "ASIGNAR_ENCARGADO";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Ventana principal de la aplicación
	 */
	private InterfazCupiHospital principal;

	// -----------------------------------------------------------------
	// Atributos de interfaz
	// -----------------------------------------------------------------

	/**
	 * Etiqueta con la imagen de una unidad.
	 */
	private JLabel lblImagen;

	/**
	 * Etiqueta con la cantidad de pacientes de una unidad
	 */
	private JLabel lblCantidad;

	/**
	 * Etiqueta con el encargado de la unidad
	 */
	private JLabel lblEncargado;

	/**
	 * Combo con las unidades del hospital.
	 */
	private JComboBox comboUnidades;

	/**
	 * Botón para mostrar los pacientes de una unidad.
	 */
	private JButton btnVerPacientes;

	/**
	 * Botón para asignar un encagrado.
	 */
	private JButton btnAsignarEncargado;

	/**
	 * Campo de texto para mostrar la cantidad de pacientes de una unidad.
	 */
	private JTextField txtCantidad;

	/**
	 * Campo de texto para mostrar el encargado de una unidad.
	 */
	private JTextField txtEncargado;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Constructor del panel.
	 * 
	 * @param ventanaPrincipal Ventana principal. ventanaPrincipal != null.
	 */
	public PanelUnidad(InterfazCupiHospital ventanaPrincipal) {
		super();
		principal = ventanaPrincipal;
		TitledBorder titulo;
		titulo = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
				"Información unidad");
		titulo.setTitleJustification(TitledBorder.LEFT);
		setBorder(titulo);

		setLayout(new BorderLayout());
		ImageIcon icono = new ImageIcon("data/Unidad A.png");

		lblImagen = new JLabel("");
		lblImagen.setIcon(icono);

		add(lblImagen, BorderLayout.WEST);

		JPanel panelCentro = new JPanel();
		panelCentro.setLayout(new GridLayout(4, 1, 10, 10));
		add(panelCentro, BorderLayout.CENTER);

		comboUnidades = new JComboBox();
		lblCantidad = new JLabel();
		btnVerPacientes = new JButton("Ver pacientes de la unidad");
		btnVerPacientes.setActionCommand(MOSTRAR_PACIENTES);
		btnVerPacientes.addActionListener(this);
		txtCantidad = new JTextField();
		txtCantidad.setEditable(false);

		Unidad[] unidadesAct = principal.obtenerUnidades();
		for (int i = 0; i < unidadesAct.length; i++) {
			comboUnidades.addItem(unidadesAct[i].darNombre() + " - " + parsearTipo(unidadesAct[i].darTipo()));
		}
		comboUnidades.setActionCommand(MOSTRAR_UNIDAD);
		comboUnidades.addActionListener(this);

		lblCantidad.setText("Cantidad de pacientes:");
		lblEncargado = new JLabel("Encargado:");
		txtEncargado = new JTextField();
		txtEncargado.setEditable(false);

		btnVerPacientes = new JButton("Ver pacientes");
		btnVerPacientes.setActionCommand(MOSTRAR_PACIENTES);
		btnVerPacientes.addActionListener(this);

		btnAsignarEncargado = new JButton("Asignar encargado");
		btnAsignarEncargado.setActionCommand(ASIGNAR_ENCARGADO);
		btnAsignarEncargado.addActionListener(this);

		panelCentro.add(comboUnidades);

		JPanel panelCantidad = new JPanel();
		panelCantidad.setLayout(new GridLayout(1, 2));
		panelCantidad.add(lblCantidad);
		panelCantidad.add(txtCantidad);

		panelCentro.add(panelCantidad);

		JPanel panelEncargado = new JPanel();
		panelEncargado.setLayout(new GridLayout(1, 2));
		panelEncargado.add(lblEncargado);
		panelEncargado.add(txtEncargado);

		panelCentro.add(panelEncargado);

		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(1, 2));
		panelBotones.add(btnVerPacientes);
		panelBotones.add(btnAsignarEncargado);
		panelCentro.add(panelBotones);

		mostrarInfoUnidad();
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Cambia el tipo de la unidad de Tipo a String.
	 * 
	 * @param pTipo tipo de la unidad.
	 * @return Tipo de la unidad como cadena de caracteres.
	 */
	private String parsearTipo(Tipo pTipo) {
		String rta = "Cuidados intensivos";
		if (pTipo == Tipo.CUIDADOS_INTERMEDIOS) {
			rta = "Cuidados intermedios";
		} else if (pTipo == Tipo.PEDIATRIA) {
			rta = "Pediatría";
		} else if (pTipo == Tipo.MATERNIDAD) {
			rta = "Maternidad";
		} else if (pTipo == Tipo.GERIATRIA) {
			rta = "Geriatría";
		} else if (pTipo == Tipo.OBSERVACION) {
			rta = "Observación";
		}
		return rta;
	}

	/**
	 * Método que actualiza la información del panel.
	 */
	public void mostrarInfoUnidad() {
		String nombreUnidad = obtenerUnidadSeleccionada();

		Unidad unidad = principal.buscarUnidad(nombreUnidad);

		txtCantidad.setText("" + unidad.darPacientes().size());

		String url = "data/" + unidad.darNombre() + ".png";
		ImageIcon icono = new ImageIcon(url);

		// La agrega a la etiqueta
		lblImagen.setIcon(icono);

		if (unidad.darEncargado() != null) {
			txtEncargado
					.setText(unidad.darEncargado().darNombre() + " - " + unidad.darEncargado().darAniosExperiencia());
		} else {
			txtEncargado.setText("No tiene");
		}

	}

	/**
	 * Devuelve el nombre de la unidad selecciona por el usuario.
	 * 
	 * @return Nombre de la unidad selecciona por el usuario.
	 */
	public String obtenerUnidadSeleccionada() {
		String unidad = (String) comboUnidades.getSelectedItem();
		return unidad.substring(0, unidad.indexOf(" - "));
	}

	/**
	 * Manejo de los eventos de los botones.
	 * 
	 * @param e Evento asociado al click en un botón. e != null.
	 */
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (MOSTRAR_PACIENTES.equals(comando)) {
			principal.mostrarDialogoPacientesUnidad();
		} else if (MOSTRAR_UNIDAD.equals(comando)) {
			mostrarInfoUnidad();
		} else if (ASIGNAR_ENCARGADO.equals(comando)) {
			principal.mostrarDialogoAsignarEncargado();
		}
	}

}
