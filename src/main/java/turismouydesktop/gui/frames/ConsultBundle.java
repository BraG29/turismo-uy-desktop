package turismouydesktop.gui.frames;

import java.awt.EventQueue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import turismouydesktop.gui.panels.ListActivities;
import turismouydesktop.gui.panels.ListActivityListener;
import turismouydesktop.gui.panels.ListTouristicBundle;
import turismouydesktop.gui.panels.ListTouristicBundleListener;
import turismouydesktop.gui.panels.ShowActivityData;
import turismouydesktop.gui.panels.ShowBundleData;
import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtCategory;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicActivity;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicBundle;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ConsultBundle extends JFrame implements ListTouristicBundleListener, ListActivityListener {

	private JPanel contentPane;
	private ListTouristicBundle touristicBundleList;
	private ShowBundleData bundleData;
//	private ListActivities listActivities;
	
	private JScrollPane scrollPaneActivities;
	private JList listBundleActivities;
	
	private JLabel lblBundles;
	private JLabel lblBundleData;
	private JLabel lblActivities;
	private DtTouristicActivity activityData;
	private List<DtTouristicActivity> activities;
	private ShowActivityData activityPanel = null;
	private JLabel lblCategories;
	private JScrollPane scrollPaneCategories;
	private JList listCategories;
	private List<DtCategory> categories = new ArrayList<DtCategory>();
	

	public ConsultBundle() {
		setTitle("Consulta de Paquetes de actividades turisticas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1492, 517);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//lbls
		lblBundles = new JLabel("Paquetes:");
		lblBundles.setBounds(49, 15, 73, 15);
		contentPane.add(lblBundles);
		
		lblBundleData = new JLabel("Datos del Paquete:");
		lblBundleData.setBounds(320, 15, 207, 15);
		contentPane.add(lblBundleData);
		
		lblActivities = new JLabel("Actividades:");
		lblActivities.setBounds(783, 15, 110, 15);
		contentPane.add(lblActivities);
		
		
		//lista paquetes
		touristicBundleList = new ListTouristicBundle();
		touristicBundleList.setBounds(12, 42, 200, 433);
		touristicBundleList.setListener(this);
		contentPane.add(touristicBundleList);
		
		
		//panel datos
		bundleData = new ShowBundleData();
		bundleData.setBounds(224, 42, 527, 312);
		contentPane.add(bundleData);
		listBundleActivities = new JList();
		listBundleActivities.setBounds(763, 42, 141, 431);
		contentPane.add(listBundleActivities);
		listBundleActivities.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
//				String selectedActivity = (String) listBundleActivities.getSelectedValue();
//				
//				
//				Long id = activities
//						.stream()
//						.filter(bundle -> bundle.getName().equalsIgnoreCase(selectedActivity))
//						.findFirst()
//						.get()
//						.getId();
//				
//				
//				IController ctrl = ControllerFactory.getIController();
//				activityPanel = new ShowActivityData(ctrl.getTouristicActivityData(id));
//				contentPane.add(activityPanel);

				//llamo al controlador para conseguir el DtTouristicActivity entero
				IController controller = ControllerFactory.getIController();
				
				//controlo si es la primera vez que se llama showActivityVentana.
				if(activityPanel == null) {
				//cargo la ventana que muestra la info de la actividad
				activityPanel = new ShowActivityData(controller.getTouristicActivityData(activities.get(listBundleActivities.getSelectedIndex()).getId()));
				activityPanel.setBounds(950, 42, 950, 450);//estaba en 200 el valor que esta en 350.
				//x, y ,width, height
				getContentPane().add(activityPanel);
				activityPanel.setVisible(true);
				}else {
					activityPanel.setValues(controller.getTouristicActivityData(activities.get(listBundleActivities.getSelectedIndex()).getId()));
				}
				
//				activityPanel.setBounds(950, 42, 950, 450);
//				activityPanel.setSize(500, 500);
//				activityPanel.setVisible(true);
			}
		});
		scrollPaneActivities = new JScrollPane();
		scrollPaneActivities.setBounds(763, 41, 144, 434);
		contentPane.add(scrollPaneActivities);
		
		lblCategories = new JLabel("Categorias:");
		lblCategories.setBounds(230, 366, 110, 15);
		contentPane.add(lblCategories);
		
		scrollPaneCategories = new JScrollPane();
		scrollPaneCategories.setBounds(376, 366, 207, 109);
		contentPane.add(scrollPaneCategories);
		
		listCategories = new JList();
		scrollPaneCategories.setViewportView(listCategories);
		
	}

	@Override
	public void onListTouristicBundle(Long id) {
		
	}


	@Override
	public void onListActivitySelected(DtTouristicActivity activity) {
		
		
	}


	@Override
	public void onListActivitySelectedDt(Long id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void reLoadListBundle(DtTouristicBundle dataBundle) {
		
		
			
	}

	@Override
	public void onListTouristicBundleDt(DtTouristicBundle dtBundle) {
	
		bundleData.setData(dtBundle);
		
		// listing categories, wiping out if no activities 
		if(!dtBundle.getActivities().isEmpty()) {
		activities = dtBundle.getActivities();
		
			DefaultListModel listModel = new DefaultListModel();
		
			for(int i = 0; i < activities.size(); i++) {
				this.categories = activities.get(i).getCategories(); // obtengo lista de categorias de las actividades.		
			}
		
			for (int i = 0; i < this.categories.size(); i++) { //recorro categorias y las añado al jList
			
				if(!listModel.contains(this.categories.get(i).getName())) { //evito repeticion de categorias.
					listModel.add(i, this.categories.get(i).getName());				
					listCategories.setModel(listModel);
				}
			}
		}
		else {
			DefaultListModel listModel = new DefaultListModel();
			listModel.clear();
			listCategories.setModel(listModel);

		}
		
		
		if(!dtBundle.getActivities().isEmpty()) {
	
			//obtengo nombre de las actividades del paquete..
			String [] bundleActivities = dtBundle
					.getActivities()
					.stream()
					.map(DtTouristicActivity::getName)
					.collect(Collectors.toList())
					.toArray(new String[0]);
			
			
			listBundleActivities.setModel(new AbstractListModel() {
				String[] values = bundleActivities;

				public int getSize() {
					return values.length;
				}

				public Object getElementAt(int index) {
					return values[index];
				}
				
			});				 
		}
		else {
			listBundleActivities.setModel(new AbstractListModel() {
				String[] values = {};

				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});
			activityPanel.setVisible(false);
			activityPanel = null;
		}
		activityPanel.setVisible(false);
		activityPanel = null;
	}
	
	

}
