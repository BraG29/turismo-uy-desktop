package turismouydesktop.gui.panels;

import javax.swing.JPanel;

import uy.turismo.servidorcentral.logic.datatypes.DtCategory;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicActivity;
import javax.swing.JTextPane;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class ShowActivityData extends JPanel {
	
	JLabel lblName = new JLabel();
	JLabel lblDesc = new JLabel();
	JLabel lblDuration = new JLabel();
	JLabel lblCost = new JLabel();
	JLabel lblDepartment = new JLabel();
	JLabel lblCity = new JLabel();	
	JLabel lblUpload = new JLabel();
	private JLabel lblCategories = new JLabel("Categorias:");
	private List<DtCategory> categories;
	/**
	 * Create the panel.
	 */
	public ShowActivityData(DtTouristicActivity DTA) {
		setLayout(null);
		
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
		
		
		lblName.setText(DTA.getName());
		lblName.setForeground(new Color(0, 206, 209));
		lblName.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblName.setBounds(12, 12, 354, 22);
		add(lblName);
		
		lblDesc.setText(DTA.getDescription());
		lblDesc.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDesc.setBounds(125, 46, 291, 15);
		add(lblDesc);
		
		lblDuration.setText(DTA.getDuration().toString());
		lblDuration.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDuration.setBounds(125, 73, 220, 16);
		add(lblDuration);
		
		lblCost.setText(DTA.getCostPerTourist().toString());
		lblCost.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCost.setBounds(125, 101, 220, 15);
		add(lblCost);
		
		lblDepartment.setText(DTA.getDepartment().getName());
		lblDepartment.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDepartment.setBounds(125, 129, 220, 15);
		add(lblDepartment);
		
		lblCity.setText(DTA.getCity());
		lblCity.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCity.setBounds(125, 156, 220, 15);
		add(lblCity);
		
		lblUpload.setText(DTA.getUploadDate().toString());
		lblUpload.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblUpload.setBounds(125, 183, 220, 15);
		add(lblUpload);
		
		/********************************************************/		
		lblCategories.setBounds(36, 210, 87, 15);
		add(lblCategories);
		
		JScrollPane scrollPaneCategories = new JScrollPane();
		scrollPaneCategories.setBounds(135, 210, 163, 137);
		add(scrollPaneCategories);

		JList listCategories = new JList();
		scrollPaneCategories.setViewportView(listCategories);		
		
		categories = DTA.getCategories();
		
		DefaultListModel listModel = new DefaultListModel();
		
		for (int i = 0; i < categories.size(); i++) {
			
			listModel.add(i, categories.get(i).getName());
		}
		listCategories.setModel(listModel);
		
		/********************************************************/	
	}
	
	public void setValues(DtTouristicActivity DTA) {
		lblName.setText(DTA.getName());
		lblName.setForeground(new Color(0, 206, 209));
		lblName.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblName.setBounds(12, 12, 354, 22);
		add(lblName);
		
		lblDesc.setText(DTA.getDescription());
		lblDesc.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDesc.setBounds(125, 46, 220, 15);
		add(lblDesc);
		
		lblDuration.setText(DTA.getDuration().toString());
		lblDuration.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDuration.setBounds(125, 73, 220, 16);
		add(lblDuration);
		
		lblCost.setText(DTA.getCostPerTourist().toString());
		lblCost.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCost.setBounds(125, 101, 220, 15);
		add(lblCost);
		
		lblDepartment.setText(DTA.getDepartment().getName());
		lblDepartment.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDepartment.setBounds(125, 129, 220, 15);
		add(lblDepartment);
		
		lblCity.setText(DTA.getCity());
		lblCity.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCity.setBounds(125, 156, 220, 15);
		add(lblCity);
		
		lblUpload.setText(DTA.getUploadDate().toString());
		lblUpload.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblUpload.setBounds(125, 183, 220, 15);
		add(lblUpload);
	}
}
