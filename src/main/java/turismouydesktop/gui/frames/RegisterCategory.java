package turismouydesktop.gui.frames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import turismouydesktop.gui.panels.InsertCategoryData;
import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtCategory;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterCategory extends JFrame {

	private JPanel contentPane;
	private InsertCategoryData categoryData;
	private PopUpWindow window;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public RegisterCategory() {
		setTitle("Alta Categoria");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 215);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		IController controller = ControllerFactory.getIController();
		
		categoryData = new InsertCategoryData();
		categoryData.setBounds(41, 12, 334, 73);
		contentPane.add(categoryData);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnCancelar.setBounds(41, 116, 117, 25);
		contentPane.add(btnCancelar);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String name = categoryData.getName();					
					DtCategory category = new DtCategory(null, name, null ,null);
					controller.registerCategory(category);
					
					window = new PopUpWindow("Éxito", "La categoria fue dada de alta con éxito.", Color.GREEN);
					window.setVisible(true);
					
				}catch(Exception e1) {
					
					window = new PopUpWindow("ERROR!",e1.getLocalizedMessage(),Color.RED);
					window.setVisible(true);
				
				}
				
			}
		});
		btnCrear.setBounds(258, 116, 117, 25);
		contentPane.add(btnCrear);
		
	}
}
