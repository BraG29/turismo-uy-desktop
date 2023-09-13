package turismouydesktop.gui;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class InsertBundleData extends JPanel {

	private JTextField textFieldname;
	private JTextArea textAreaDescription;
	private JTextField textFieldDiscount;
	private JTextField textFieldValidity;
	private JTextField textFieldDays;

	public InsertBundleData() {
		setLayout(null);
		
		JLabel lblname = new JLabel("Nombre:");
		lblname.setBounds(12, 12, 87, 15);
		add(lblname);
		
		JLabel lbldescription = new JLabel("Descripción:");
		lbldescription.setBounds(12, 51, 87, 15);
		add(lbldescription);
		
		JLabel lblDiscount = new JLabel("Descuento %:");
		lblDiscount.setBounds(12, 165, 121, 15);
		add(lblDiscount);
		
		textFieldname = new JTextField();
		textFieldname.setBounds(117, 10, 319, 19);
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
		
			
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(117, 50, 319, 94);
		add(scrollPane);
		textAreaDescription = new JTextArea();
		textAreaDescription.setWrapStyleWord(true);//no corto palabras al saltar linea
		textAreaDescription.setLineWrap(true); //salto de linea
		scrollPane.setViewportView(textAreaDescription);
		textAreaDescription.setForeground(Color.GRAY);
		textAreaDescription.setText("Ingrese una descripción.");
		textAreaDescription.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textAreaDescription.getText().equals("Ingrese una descripción.")) {
					textAreaDescription.setText("");
					textAreaDescription.setForeground(Color.BLACK);
				}
			}
		});
		
		
		
		textFieldDiscount = new JTextField();
		textFieldDiscount.setBounds(117, 163, 319, 19);
		add(textFieldDiscount);
		textFieldDiscount.setColumns(10);
		textFieldDiscount.setForeground(Color.GRAY);
		textFieldDiscount.setText("Ingrese el descuento del paquete.");
		textFieldDiscount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textFieldDiscount.getText().equals("Ingrese el descuento del paquete.")) {
					textFieldDiscount.setText("");
					textFieldDiscount.setForeground(Color.BLACK);
				}
			}
		});
		
		JLabel lblValidity = new JLabel("Validez:");
		lblValidity.setBounds(12, 301, 84, 15);
		add(lblValidity);
		
		
		textFieldValidity = new JTextField();
		textFieldValidity.setBounds(117, 296, 108, 19);
		add(textFieldValidity);
		textFieldValidity.setColumns(10);
		textFieldValidity.setEditable(false);
		
		
		JLabel lblValidityPeriod = new JLabel("Fecha finalización:");
		lblValidityPeriod.setBounds(12, 255, 158, 15);
		add(lblValidityPeriod);
		
		JDateChooser validityPeriod = new JDateChooser();
		validityPeriod.setBounds(144, 255, 292, 19);
		add(validityPeriod);
				
		textFieldDays = new JTextField();
		textFieldDays.setBounds(224, 296, 212, 19);
		add(textFieldDays);
		textFieldDays.setColumns(10);
		textFieldDays.setEditable(false);

		JLabel lbluploadDate = new JLabel("Fecha alta:");
		lbluploadDate.setBounds(12, 206, 87, 15);
		add(lbluploadDate);
		
		JDateChooser uploadDate = new JDateChooser();
		uploadDate.setBounds(117, 206, 319, 19);
		add(uploadDate);
		
		
		JButton btnValidity = new JButton("Calcular validez");
		btnValidity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Date finishDate = validityPeriod.getDate();
				Date creationDate = uploadDate.getDate();
				
				Long validityDays = dateDifference(creationDate, finishDate);
				
				Long value = Math.abs(validityDays);
				if (value <= 0){
					
					//error de fecha invalida.
				}	
				else {
					if (value <= 1) {
						textFieldValidity.setText(value+"");
						textFieldDays.setText("Dia");
					}else {
						textFieldValidity.setText(value+"");
						textFieldDays.setText("Días");
					}	
				}
						
			}
		});
		btnValidity.setHorizontalAlignment(SwingConstants.RIGHT);
		btnValidity.setBounds(165, 327, 150, 25);
		add(btnValidity);
		
		
				
	}

	//dias de validez
	public Long dateDifference(Date uploadDate, Date finishDate) {
		
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDate creationDate= uploadDate.toInstant().atZone(zoneId).toLocalDate();
		LocalDate finalizationDate = finishDate.toInstant().atZone(zoneId).toLocalDate();
		Long daysDifference = Math.abs(finalizationDate.toEpochDay() - creationDate.toEpochDay());
		
		return daysDifference;
	}
	
	//obtener los dias de validez de el paquete.
	public Integer getValidityBundle() {
		//traer lo del textfield
		String validityDaysStr = textFieldValidity.getText();
		Integer validityDays = Integer.parseInt(validityDaysStr);
	
		return validityDays;
	}
	

	public LocalDate getCreationDate() {
		LocalDate today = LocalDate.now();
		return today;
	}
	
	public String getName() {
		return textFieldname.getText();
	}
	
	public String getDescription() {
		return textAreaDescription.getText();
	}
	
	public Double getDiscount(){
		String discountStr = textFieldDiscount.getText();
		Double discount = Double.parseDouble(discountStr);
		return discount;
	}
}
