package turismouydesktop.gui.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

import com.toedter.calendar.JCalendar;

import turismouydesktop.gui.frames.PopUpWindow;
import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtCategory;
import uy.turismo.servidorcentral.logic.datatypes.DtDepartment;
import uy.turismo.servidorcentral.logic.datatypes.DtProvider;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicActivity;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelActivity extends JPanel implements ListDepartmentListener, ListProviderListener, ListCategoryListener {
	private JTextField txtNombre;
	private JTextField txtCity;

	// paneles con listener
	private ListDepartment jListDepartment;
	private ListProvider jListProvider;
	private ListCategory jListCategory;
//  private ListCategory jListCategory
	// atributos de la Actividad Turística
	private String TAName = null;
	private String TADescription = null;
	private Double TADuration = null;
	private Double TACostPerTourist = null;
	private String TACity = null;
	private LocalDate TAUploadDate = null;
	private DtProvider TAProvider = null;
	private DtDepartment TADepartment = null;
	private List<DtCategory> categories = new ArrayList<DtCategory>();
	/**
	 * Create the panel.
	 */
	public PanelActivity() {
		setLayout(null);
//----------------------------Inicialización de elementos Swing--------------------------------------------------------
		jListDepartment = new ListDepartment(180,100);
		jListDepartment.setBounds(199, 243, 212, 130);
		add(jListDepartment);
		jListDepartment.setListener(this);

		jListProvider = new ListProvider(180,100);
		jListProvider.setBounds(12, 243, 212, 130);
		add(jListProvider);
		jListProvider.setListener(this);
		
		//----------------------------------codigo agregado : LT
		jListCategory = new ListCategory(true);
		jListCategory.setBounds(437, 61, 189, 286);
		add(jListCategory);
		jListCategory.setListener(this);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 43, 60, 15);
		add(lblNombre);

		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setBounds(12, 70, 87, 15);
		add(lblDescripcion);

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
		spinnerDuration.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerDuration.setBounds(119, 125, 57, 20);
		add(spinnerDuration);

		JLabel lblHrs = new JLabel("Hrs.");
		lblHrs.setBounds(180, 129, 70, 15);
		add(lblHrs);

		JLabel lblCostPerTourist = new JLabel("Costo/Turista:");
		lblCostPerTourist.setBounds(12, 156, 100, 15);
		add(lblCostPerTourist);

		JSpinner spinnerCost = new JSpinner();
		spinnerCost.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(10)));
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

		JLabel lblSeleccioneProveedor = new JLabel("Seleccione Proveedor:");
		lblSeleccioneProveedor.setBounds(13, 216, 171, 15);
		add(lblSeleccioneProveedor);

		JLabel lblSeleccionDepartamento = new JLabel("Seleccioné Departamento:");
		lblSeleccionDepartamento.setBounds(189, 216, 195, 15);
		add(lblSeleccionDepartamento);
//----------------------------Inicialización de elementos Swing--------------------------------------------------------

		// agregamos botón adelamte
		JButton btnOk = new JButton("OK!");

		// cuando clickeo el botón:
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TAName = txtNombre.getText();
					TADescription = txtAreaDescription.getText();
					TADuration = (Double) spinnerDuration.getValue();
					TACostPerTourist = (Double) spinnerCost.getValue();
					TACity = txtCity.getText();
					TAUploadDate = LocalDate.now();
					
					DtTouristicActivity DTA = new DtTouristicActivity(
												null, 
												TAName, 
												TADescription, 
												TADuration,
												TACostPerTourist, 
												TACity, 
												TAUploadDate, 
												TAProvider, 
												TADepartment, 
												null,
												null,categories);
					checkEmptyValues(DTA);
					
					IController controller = ControllerFactory.getIController();
					controller.registeTouristicActivity(DTA);
				} catch (Exception e2) {
					// tirar ventana con exception
					System.out.println(e2);
					PopUpWindow ventanaError = new PopUpWindow("ERROR!",e2.toString(),Color.RED);
					ventanaError.setVisible(true);
				}

			}
		});
		btnOk.setBounds(285, 146, 96, 25);
		add(btnOk);
		btnOk.setBackground(new Color(60, 179, 113));
		
		//codigo agregado : LT
		JLabel lblSelectCategories = new JLabel("Seleccione categoria/s:");
		lblSelectCategories.setBounds(437, 41, 189, 15);
		add(lblSelectCategories);
	}

	@Override
	public void onListDepartmentSelected(Long id) {
	}

	@Override
	public void onProviderSelected(Long id) {
	}

	@Override
	public void onProviderSelectedDt(DtProvider provider) {
		this.TAProvider = provider;
	}

	@Override
	public void onListDepartmentSelectedDt(DtDepartment department) {
		this.TADepartment = department;
		}
	
	@Override
	public void onListCategorySelected(DtCategory categoriesSelected) { 
		if(!this.categories.contains(categoriesSelected)) {			
			this.categories.add(categoriesSelected);		
		}
	}
	
	/*
	 * checkea que los datos insertados por el usuario no estén vacios
	 */
	public void checkEmptyValues(DtTouristicActivity DTA) throws Exception {

		if (DTA.getName().isEmpty()) {
			throw new Exception("Nombre no puede estár vacio");
		}

		if (DTA.getDescription().isEmpty()) {
			throw new Exception("Descripción no puede estár vacio");
		}

		if (DTA.getDuration() == 0D) {
			throw new Exception("Duración no puede ser 0");
		}

		if (DTA.getCity().isEmpty()) {
			throw new Exception("Ciudad no puede estár vacio");
		}
		
		if (DTA.getDepartment() == null) {
			throw new Exception("Debe seleccionar un departamento");
		}
		
		if (DTA.getProvider() == null) {
			throw new Exception("Debe seleccionar un Proveedor");
		}
		if (DTA.getCategories() == null) {
			throw new Exception("Debe seleccionar una/s categoria/s");
		}
		
	}

}
