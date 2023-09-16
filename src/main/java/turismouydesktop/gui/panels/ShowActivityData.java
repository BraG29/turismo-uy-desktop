package turismouydesktop.gui.panels;

import javax.swing.JPanel;

import uy.turismo.servidorcentral.logic.datatypes.DtTouristicActivity;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class ShowActivityData extends JPanel {

	/**
	 * Create the panel.
	 */
	public ShowActivityData(DtTouristicActivity DTA) {
		setLayout(null);
		
		JLabel lblName = new JLabel(DTA.getName());
		lblName.setForeground(new Color(0, 206, 209));
		lblName.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblName.setBounds(12, 12, 354, 22);
		add(lblName);
		
		JLabel lbl1 = new JLabel("Descripción:");
		lbl1.setBounds(36, 46, 93, 15);
		add(lbl1);
		
		JLabel lbl2 = new JLabel("Duración:");
		lbl2.setBounds(53, 74, 76, 15);
		add(lbl2);
		
		JLabel lbl3 = new JLabel("Costo/Turista:");
		lbl3.setBounds(22, 101, 107, 15);
		add(lbl3);
		
		JLabel lbl4 = new JLabel("Departamento:");
		lbl4.setBounds(12, 129, 111, 15);
		add(lbl4);
		
		JLabel lbl5 = new JLabel("Ciudad:");
		lbl5.setBounds(66, 156, 62, 15);
		add(lbl5);
		
		JLabel lbl6 = new JLabel("Fecha de Alta:");
		lbl6.setBounds(18, 183, 111, 15);
		add(lbl6);
		
		JLabel lblDesc = new JLabel(DTA.getDescription());
		lblDesc.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDesc.setBounds(125, 46, 220, 15);
		add(lblDesc);
		
		JLabel lblDuration = new JLabel(DTA.getDuration().toString());
		lblDuration.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDuration.setBounds(125, 73, 220, 16);
		add(lblDuration);
		
		JLabel lblCost = new JLabel(DTA.getCostPerTourist().toString());
		lblCost.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCost.setBounds(125, 101, 220, 15);
		add(lblCost);
		
		JLabel lblDepartment = new JLabel(DTA.getDepartment().getName());
		lblDepartment.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDepartment.setBounds(125, 129, 220, 15);
		add(lblDepartment);
		
		JLabel lblCity = new JLabel(DTA.getCity());
		lblCity.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCity.setBounds(125, 156, 220, 15);
		add(lblCity);
		
		JLabel lblUpload = new JLabel(DTA.getUploadDate().toString());
		lblUpload.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblUpload.setBounds(125, 183, 220, 15);
		add(lblUpload);
		
	}
}
