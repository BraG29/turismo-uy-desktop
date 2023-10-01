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

import turismouydesktop.gui.panels.ListActivities;
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
	private ListActivities listActivities;
	
	private JScrollPane scrollPaneActivities;
	private JList listBundleActivities;
	
	private JLabel lblBundles;
	private JLabel lblBundleData;
	private JLabel lblActivities;
	private DtTouristicActivity activityData;
	private List<DtTouristicActivity> activities;
	private ShowActivityData activityPanel;
	private JLabel lblCategories;
	private JScrollPane scrollPaneCategories;
	private JList listCategories;
	private List<DtCategory> categories = new ArrayList<DtCategory>();
	

	public ConsultBundle() {
		setTitle("Consulta de Paquetes de actividades turisticas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1192, 517);

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
		lblActivities.setBounds(660, 15, 110, 15);
		contentPane.add(lblActivities);
		
		
		//lista paquetes
		touristicBundleList = new ListTouristicBundle();
		touristicBundleList.setBounds(12, 42, 200, 433);
		touristicBundleList.setListener(this);
		contentPane.add(touristicBundleList);
		
		
		//panel datos
		bundleData = new ShowBundleData();
		bundleData.setBounds(224, 42, 391, 312);
		contentPane.add(bundleData);
		scrollPaneActivities = new JScrollPane();
		scrollPaneActivities.setBounds(640, 41, 144, 434);
		contentPane.add(scrollPaneActivities);
		listBundleActivities = new JList();
		listBundleActivities.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				String selectedActivity = (String) listBundleActivities.getSelectedValue();
				
				
				Long id = activities
						.stream()
						.filter(bundle -> bundle.getName().equalsIgnoreCase(selectedActivity))
						.findFirst()
						.get()
						.getId();
				
				
				IController ctrl = ControllerFactory.getIController();
				activityPanel = new ShowActivityData(ctrl.getTouristicActivityData(id));
				contentPane.add(activityPanel);
				activityPanel.setBounds(800, 42, 391, 350);
				activityPanel.setVisible(true);
			}
		});
		scrollPaneActivities.setViewportView(listBundleActivities);
		
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
		
		activities = dtBundle.getActivities();
		
		
		 //null pointer exception aca.
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
	}

}
