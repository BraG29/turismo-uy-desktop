package turismouydesktop.gui.panels;

import java.time.format.DateTimeFormatter;


import javax.swing.JPanel;
import javax.swing.JLabel;

import javax.swing.JTextField;


import uy.turismo.servidorcentral.logic.datatypes.DtTouristicBundle;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class ShowBundleData extends JPanel {
	private JTextField textFieldName;
	private JTextField textFieldDiscount;
	private JTextField textFieldValidityPeriod;
	private JTextField textFieldDate;
	private JTextArea textArea;

	
	public ShowBundleData() {
		setLayout(null);
		
		JLabel lblName = new JLabel("Nombre:");
		lblName.setBounds(12, 33, 70, 15);
		add(lblName);
		
		JLabel lblDescription = new JLabel("Descripción:");
		lblDescription.setBounds(12, 70, 87, 15);
		add(lblDescription);
		
		JLabel lblDiscount = new JLabel("Descuento:");
		lblDiscount.setBounds(12, 189, 95, 15);
		add(lblDiscount);
		
		JLabel lblPerodoDeValidez = new JLabel("Período de validez:");
		lblPerodoDeValidez.setBounds(12, 283, 146, 15);
		add(lblPerodoDeValidez);
		
		
		textFieldName = new JTextField();
		textFieldName.setBounds(85, 31, 274, 19);
		add(textFieldName);
		textFieldName.setColumns(10);
		textFieldName.setEditable(false);
		
		
		//descripción
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(106, 69, 253, 94);
		add(scrollPane);
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);//no corto palabras al saltar linea
		textArea.setLineWrap(true); //salto de linea
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
		textFieldDiscount = new JTextField();
		textFieldDiscount.setBounds(106, 187, 253, 19);
		add(textFieldDiscount);
		textFieldDiscount.setColumns(10);
		textFieldDiscount.setEditable(false);
		
		
		textFieldValidityPeriod = new JTextField();
		textFieldValidityPeriod.setBounds(157, 281, 202, 19);
		add(textFieldValidityPeriod);
		textFieldValidityPeriod.setColumns(10);
		textFieldValidityPeriod.setEditable(false);
		
		
		JLabel lbluploadDate = new JLabel("Fecha de creación:");
		lbluploadDate.setBounds(12, 239, 133, 15);
		add(lbluploadDate);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(157, 237, 202, 19);
		add(textFieldDate);
		textFieldDate.setColumns(10);
		textFieldDate.setEditable(false);
		

	}
	
	public void setData(DtTouristicBundle bundle) {
		//agregar parametros que con esto coloco los datos.
		textFieldName.setText(bundle.getName());
		
		textArea.setText(bundle.getDescription());
		
		String discountStr = String.valueOf(bundle.getDiscount());
		textFieldDiscount.setText(discountStr + " %");
		
		String validityPeriodStr = String.valueOf(bundle.getValidityPeriod());
		textFieldValidityPeriod.setText(validityPeriodStr);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String uploadDateStr = bundle.getUploadDate().format(formatter);
		textFieldDate.setText(uploadDateStr);
		
	}
}
