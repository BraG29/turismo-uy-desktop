package turismouydesktop.gui.panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import uy.turismo.servidorcentral.logic.datatypes.DtDepartment;

import java.awt.Component;

import javax.swing.JLabel;

public class DepartmentDataManagment extends JPanel {

	private JTextArea textAreaName;

	private JScrollPane scrollPaneWebSite;
	private JTextArea textAreaWebSite;
	
	private JTextArea textAreaDescription;
	private JScrollPane scrollPaneDescription;

	/**
	 * Create the panel.
	 */
	public DepartmentDataManagment() {
		setLayout(null);
		
		JLabel lblName = new JLabel("Nombre:");
		lblName.setBounds(12, 12, 70, 15);
		add(lblName);
		
		JLabel lblWebSite = new JLabel("Sitio Web:");
		lblWebSite.setBounds(12, 39, 78, 15);
		add(lblWebSite);
		
		JLabel lblDescription = new JLabel("Descripción:");
		lblDescription.setBounds(12, 66, 87, 15);
		add(lblDescription);
		
		textAreaName = new JTextArea();
		textAreaName.setBounds(89, 12, 154, 20);
		add(textAreaName);
		
		textAreaWebSite = new JTextArea();
		textAreaWebSite.setLineWrap(true);
		textAreaWebSite.setWrapStyleWord(true);
		
		
		scrollPaneWebSite = new JScrollPane(textAreaWebSite);
		scrollPaneWebSite.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneWebSite.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPaneWebSite.setBounds(89, 38, 199, 20);
		add(scrollPaneWebSite);
		
		textAreaDescription = new JTextArea();
		textAreaDescription.setLineWrap(true);
		textAreaDescription.setWrapStyleWord(true);

		scrollPaneDescription = new JScrollPane(textAreaDescription);
		scrollPaneDescription.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneDescription.setBounds(109, 65, 179, 95);
		add(scrollPaneDescription);
		
		
	}
	
	public DtDepartment getData() {
		DtDepartment departmentData = new DtDepartment(
				null,
				textAreaName.getText(),
				textAreaDescription.getText(),
				textAreaWebSite.getText(),
				null
				);
		
		return departmentData;
	}

}
