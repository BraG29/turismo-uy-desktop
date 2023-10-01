package turismouydesktop.gui.panels;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InsertCategoryData extends JPanel {
	
	private JTextField textFieldname;
	
	/**
	 * Create the panel.
	 */
	public InsertCategoryData() {
		setLayout(null);
		
		JLabel lblname = new JLabel("Nombre:");
		lblname.setBounds(30, 28, 60, 15);
		add(lblname);
		textFieldname = new JTextField();
		textFieldname.setBounds(108, 26, 206, 19);
		add(textFieldname);
		textFieldname.setColumns(10);
		textFieldname.setForeground(Color.GRAY);
		textFieldname.setText("Ingrese un nombre.");
		textFieldname.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(textFieldname.getText().equals("Ingrese un nombre.")) {
					textFieldname.setText("");
					textFieldname.setForeground(Color.BLACK);
				}
			}
		});
	}
	
	public String getName() {
		return textFieldname.getText();
	}

}
