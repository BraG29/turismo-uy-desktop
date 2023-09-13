package turismouydesktop.gui;



import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicBundle;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CreateBundle extends JFrame {

	private JPanel contentPane;
	private InsertBundleData insertBundleData;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateBundle frame = new CreateBundle();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 
	/**
	 * Create the frame.
	 */
	public CreateBundle() {
		
		IController controller = ControllerFactory.getIController();
		
		setTitle("Crear paquete turístico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		insertBundleData = new InsertBundleData();
		insertBundleData.setBounds(31, 12, 460, 367);
		contentPane.add(insertBundleData);
		//insertBundleData.setVisible(true);
		
		
		
		JButton btnCreate = new JButton("Crear");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String name = insertBundleData.getName();
				String desc = insertBundleData.getDescription();
				Double disc = insertBundleData.getDiscount();
				Integer days = insertBundleData.getValidityBundle();
				LocalDate creationDate = insertBundleData.getCreationDate();

				if (name == null || desc == null || disc == null || days == null || creationDate == null){
					//ventana error campo o campos vacios.
				}else {

					DtTouristicBundle touristicbundle = new DtTouristicBundle(null, name, desc, days, disc , creationDate, null);	
					controller.registerTouristicBundle(touristicbundle);
					
					//si no tira excepción llamamo a la ventana de confirmacion.
					
				}
				
				
			}
		});
		btnCreate.setBounds(292, 406, 117, 25);
		contentPane.add(btnCreate);
		
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(120, 406, 117, 25);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		contentPane.add(btnCancel);
	}
}
