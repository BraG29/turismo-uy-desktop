package turismouydesktop.gui.frames;

import java.awt.EventQueue;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import turismouydesktop.gui.panels.ListActivities;
import turismouydesktop.gui.panels.ListActivityListener;
import turismouydesktop.gui.panels.ListTouristicBundle;
import turismouydesktop.gui.panels.ListTouristicBundleListener;
import turismouydesktop.gui.panels.ShowBundleData;
import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicActivity;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicBundle;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;

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
	private List<DtTouristicActivity> activities;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultBundle frame = new ConsultBundle();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ConsultBundle() {
		setTitle("Consulta de Paquetes de actividades turisticas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 842, 401);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//lbls
		lblBundles = new JLabel("Paquetes:");
		lblBundles.setBounds(49, 15, 73, 15);
		contentPane.add(lblBundles);
		
		lblBundleData = new JLabel("Datos del Paquete:");
		lblBundleData.setBounds(344, 15, 207, 15);
		contentPane.add(lblBundleData);
		
		lblActivities = new JLabel("Actividades");
		lblActivities.setBounds(682, 15, 110, 15);
		contentPane.add(lblActivities);
		
		
		
		//lista paquetes
		touristicBundleList = new ListTouristicBundle();
		touristicBundleList.setBounds(12, 42, 200, 312);
		touristicBundleList.setListener(this);
		contentPane.add(touristicBundleList);

		//panel datos
		bundleData = new ShowBundleData();
		bundleData.setBounds(240, 42, 391, 312);
		contentPane.add(bundleData);
		
		
		//scroll panel y lista actividades del paquete..
		scrollPaneActivities = new JScrollPane();
		scrollPaneActivities.setBounds(664, 41, 144, 313);
		contentPane.add(scrollPaneActivities);
		listBundleActivities = new JList();
		scrollPaneActivities.setViewportView(listBundleActivities);
		
		
		
			
		
		
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


	
}
