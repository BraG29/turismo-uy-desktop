package turismouydesktop.gui.frames;

import java.awt.EventQueue;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import turismouydesktop.gui.panels.ListTouristicBundle;
import turismouydesktop.gui.panels.ListTouristicBundleListener;
import turismouydesktop.gui.panels.ShowBundleData;
import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicBundle;

import javax.swing.JLabel;

public class ConsultBundle extends JFrame implements ListTouristicBundleListener {

	private JPanel contentPane;
	private ListTouristicBundle touristicBundleList;
	private ShowBundleData bundleData;


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
		setTitle("Consulta de actividades turisticas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 738, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		touristicBundleList = new ListTouristicBundle();
		touristicBundleList.setBounds(12, 12, 200, 145);
		touristicBundleList.setListener(this);
		contentPane.add(touristicBundleList);

		bundleData = new ShowBundleData();
		bundleData.setBounds(265, 12, 391, 481);
		contentPane.add(bundleData);
			
	}

	@Override
	public void onListTouristicBundle(Long id) {
		IController ctrl = ControllerFactory.getIController();
		DtTouristicBundle touristicBundle = ctrl.getTouristicBundleData(id);
		bundleData.setData(touristicBundle);	
	}
	
}
