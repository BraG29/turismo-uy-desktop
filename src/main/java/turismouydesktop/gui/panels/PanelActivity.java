package turismouydesktop.gui.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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

import turismouydesktop.gui.frames.FileChooserImage;
import turismouydesktop.gui.frames.FileChooserImageListener;
import turismouydesktop.gui.frames.PopUpWindow;
import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtCategory;
import uy.turismo.servidorcentral.logic.datatypes.DtDepartment;
import uy.turismo.servidorcentral.logic.datatypes.DtProvider;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicActivity;
import uy.turismos.servidorcentral.logic.enums.ActivityState;

import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollBar;

public class PanelActivity extends JPanel implements ListDepartmentListener, ListProviderListener, ListCategoryListener, FileChooserImageListener {
	private JTextField txtNombre;
	private JTextField txtCity;

	// paneles con listener
	private ListDepartment jListDepartment;
	private ListProvider jListProvider;
	private ListCategory jListCategory;
	//private ListCategory jListCategory
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
	
	private JLabel lblImage;
	private FileChooserImage fileChooserImage;
	private BufferedImage selectedImage;
	
	private PopUpWindow window;
	
	/**
	 * Create the panel.
	 */
	public PanelActivity() {
		setLayout(null);
		
		//----------------------------Inicialización de elementos Swing--------------------------------------------------------
		// agregamos botón adelamte
		JButton btnOk = new JButton("OK!");
		btnOk.setBounds(12, 368, 96, 25);
		add(btnOk);
		
		JButton btnAddImage = new JButton("Agregar Imagen");	
		btnAddImage.setBounds(200, 368, 150, 25);
		add(btnAddImage);
		
		btnOk.setBackground(new Color(60, 179, 113));
		jListDepartment = new ListDepartment(180,100);
		jListDepartment.setBounds(200, 263, 212, 130);
		add(jListDepartment);
		jListDepartment.setListener(this);

		jListProvider = new ListProvider(180,100);
		jListProvider.setBounds(12, 263, 212, 130);
		add(jListProvider);
		jListProvider.setListener(this);
		
		//----------------------------------codigo agregado : LT
		jListCategory = new ListCategory(true);
		jListCategory.setBounds(414, 216, 189, 286);
		add(jListCategory);
		jListCategory.setListener(this);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 43, 60, 15);
		add(lblNombre);

		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setBounds(12, 151, 87, 15);
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
		lblDuration.setBounds(12, 70, 70, 15);
		add(lblDuration);

		JSpinner spinnerDuration = new JSpinner();
		spinnerDuration.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerDuration.setBounds(119, 68, 57, 20);
		add(spinnerDuration);

		JLabel lblHrs = new JLabel("Hrs.");
		lblHrs.setBounds(180, 70, 70, 15);
		add(lblHrs);

		JLabel lblCostPerTourist = new JLabel("Costo/Turista:");
		lblCostPerTourist.setBounds(12, 94, 100, 15);
		add(lblCostPerTourist);

		JSpinner spinnerCost = new JSpinner();
		spinnerCost.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(10)));
		spinnerCost.setBounds(119, 92, 57, 20);
		add(spinnerCost);

		JLabel lblCost = new JLabel("$");
		lblCost.setBounds(180, 94, 23, 15);
		add(lblCost);

		JTextArea txtAreaDescription = new JTextArea();
		txtAreaDescription.setLineWrap(true);
		txtAreaDescription.setBounds(117, 151, 171, 73);
		add(txtAreaDescription);

		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(12, 121, 70, 15);
		add(lblCiudad);

		txtCity = new JTextField();
		txtCity.setBounds(119, 119, 171, 19);
		add(txtCity);
		txtCity.setColumns(10);

		JLabel lblSeleccioneProveedor = new JLabel("Seleccione Proveedor:");
		lblSeleccioneProveedor.setBounds(12, 236, 171, 15);
		add(lblSeleccioneProveedor);

		JLabel lblSeleccionDepartamento = new JLabel("Seleccioné Departamento:");
		lblSeleccionDepartamento.setBounds(200, 236, 195, 15);
		add(lblSeleccionDepartamento);
		
		//codigo agregado : LT
		JLabel lblSelectCategories = new JLabel("Seleccione categoria/s:");
		lblSelectCategories.setBounds(437, 189, 189, 15);
		add(lblSelectCategories);
		
		lblImage = new JLabel("");
		lblImage.setBounds(437, 12, 150, 150);
		add(lblImage);
				
		fileChooserImage = new FileChooserImage();
		fileChooserImage.setListener(this);
				
		// cuando clickeo el botón:
		btnOk.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e2) {
			try {
					TAName = txtNombre.getText();
					TADescription = txtAreaDescription.getText();
					TADuration = (Double) spinnerDuration.getValue();
					TACostPerTourist = (Double) spinnerCost.getValue();
					TACity = txtCity.getText();
					TAUploadDate = LocalDate.now();
					ActivityState TAstate = ActivityState.ADDED;
					//null con las imagenes por ahora
					DtTouristicActivity DTA = new DtTouristicActivity(
													null, 
													TAName, 
													TADescription, 
													TADuration,
													TACostPerTourist, 
													TACity,
													selectedImage,
													TAstate,
													TAUploadDate, 
													TAProvider, 
													TADepartment, 
													null,
													null,
													categories);
						checkEmptyValues(DTA);
							
						IController controller = ControllerFactory.getIController();
						controller.registeTouristicActivity(DTA);
						
						window = new PopUpWindow("Éxito", "La Actividad fue dada de alta con éxito.", Color.GREEN);
						window.setVisible(true);
						
					} catch (Exception e1) {
						// tirar ventana con exception
						System.out.println(e1);
						window = new PopUpWindow("ERROR!",e1.getLocalizedMessage(),Color.RED);
						window.setVisible(true);
					}
		
				}
			});
			btnAddImage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					fileChooserImage.setVisible(true);
				}
			});
//----------------------------Inicialización de elementos Swing--------------------------------------------------------
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
	
	
	public BufferedImage scalateImage(BufferedImage baseImage) {
		double scaleX = (double) lblImage.getWidth() / baseImage.getWidth();
		double scaleY = (double) lblImage.getHeight() / baseImage.getHeight();
		AffineTransform at = AffineTransform.getScaleInstance(scaleX, scaleY);
		
		// Crear una operación de transformación
		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		
		// Crear una nueva imagen escalada
		BufferedImage scaletedImage = new BufferedImage(200, 200, baseImage.getType());
		
		// Aplicar la operación de transformación para escalar la imagen
		Graphics2D g2d = scaletedImage.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.drawImage(baseImage, op, 0, 0);
		g2d.dispose();
		return scaletedImage;
	}
	
	public void loadImage(BufferedImage imageForLoad){
		ImageIcon image = null;
		
		if(imageForLoad != null) {
			BufferedImage scaletedImage = scalateImage(imageForLoad);
			image = new ImageIcon(scaletedImage);
		}else {
//			lblImage.setIcon(image);
			lblImage.setText("No Image");
			lblImage.setForeground(Color.RED);
		}
		lblImage.setIcon(image);		
	}
	
	@Override
	public void onImageSelected(File image) {
//		System.out.println("Path: " + image.getAbsolutePath()
//				+ "\nNombre: " + image.getName());
		try {
			selectedImage = ImageIO.read(image);
		
			
		} catch (Exception e) {
			loadImage(selectedImage);
			System.out.println("Error: " + e.getMessage());
		}
		loadImage(selectedImage);
		
		
	}
}
