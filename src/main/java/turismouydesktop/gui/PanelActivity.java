package turismouydesktop.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;
import com.toedter.calendar.JCalendar;
public class PanelActivity extends JPanel {
	private JTextField txtNombre;
	private JTextField txtCity;

	/**
	 * Create the panel.
	 */
	public PanelActivity() {
		setLayout(null);
		
	
		
		JList listProvider = new JList();
		listProvider.setFont(new Font("Dialog", Font.PLAIN, 12));
		listProvider.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listProvider.setBounds(312, 53, 195, 98);
		listProvider.setModel(new AbstractListModel() {
			String[] values = new String[] {"Uruguay Turismo", "Centro Rocha", "Universidad ORT"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		add(listProvider);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 43, 60, 15);
		add(lblNombre);
		
		JLabel lblDescripcin = new JLabel("Descripción:");
		lblDescripcin.setBounds(12, 70, 87, 15);
		add(lblDescripcin);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(119, 41, 171, 19);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblAltaDeActividad = new JLabel("Alta de Actividad Turistica");
		lblAltaDeActividad.setForeground(new Color(0, 191, 255));
		lblAltaDeActividad.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblAltaDeActividad.setBackground(new Color(238, 238, 238));
		lblAltaDeActividad.setBounds(12, 12, 231, 19);
		add(lblAltaDeActividad);
		
		JLabel lblDuration = new JLabel("Duración:");
		lblDuration.setBounds(12, 127, 70, 15);
		add(lblDuration);
		
		JSpinner spinnerDuration = new JSpinner();
		spinnerDuration.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), null, Float.valueOf(1)));
		spinnerDuration.setBounds(119, 125, 57, 20);
		add(spinnerDuration);
		
		JLabel lblHrs = new JLabel("Hrs.");
		lblHrs.setBounds(180, 129, 70, 15);
		add(lblHrs);
		
		JLabel lblCostPerTourist = new JLabel("Costo/Turista:");
		lblCostPerTourist.setBounds(12, 156, 100, 15);
		add(lblCostPerTourist);
		
		JSpinner spinnerCost = new JSpinner();
		spinnerCost.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(10)));
		spinnerCost.setBounds(119, 154, 57, 20);
		add(spinnerCost);
		
		JLabel lblCost = new JLabel("$");
		lblCost.setBounds(180, 156, 23, 15);
		add(lblCost);
		
		JTextArea txtAreaDescription = new JTextArea();
		txtAreaDescription.setBounds(118, 70, 171, 45);
		add(txtAreaDescription);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(12, 183, 70, 15);
		add(lblCiudad);
		
		txtCity = new JTextField();
		txtCity.setBounds(119, 181, 171, 19);
		add(txtCity);
		txtCity.setColumns(10);
		
		JLabel lblFechaDeAlta = new JLabel("Fecha de Alta:");
		lblFechaDeAlta.setBounds(12, 206, 116, 15);
		add(lblFechaDeAlta);
		
		JLabel lblSeleccioneProveedor = new JLabel("Seleccione Proveedor:");
		lblSeleccioneProveedor.setBounds(312, 33, 171, 15);
		add(lblSeleccioneProveedor);
		
		JLabel lblSeleccionDepartamento = new JLabel("Seleccioné Departamento:");
		lblSeleccionDepartamento.setBounds(312, 173, 195, 15);
		add(lblSeleccionDepartamento);
		
		JList listDepartment = new JList();
		listDepartment.setModel(new AbstractListModel() {
			String[] values = new String[] {"Maldonado", "Montevideo", "Salto", "MELO CARAJO", "Rio Negro"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listDepartment.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listDepartment.setFont(new Font("Dialog", Font.PLAIN, 12));
		listDepartment.setBounds(312, 192, 195, 92);
		add(listDepartment);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(55, 226, 237, 159);
		add(calendar);
	}
}
