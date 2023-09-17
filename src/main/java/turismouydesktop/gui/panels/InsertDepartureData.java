package turismouydesktop.gui.panels;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class InsertDepartureData extends JPanel {
	private JTextField textFieldname;
	private JTextField textFieldPlace;
	private JTextField textFieldMaxTourists;
	private JDateChooser creationDateChooser;
	private JDateChooser dateChooserMeeting;
	private JSpinner scheduleMeeting;
	/**
	 * Create the panel.
	 */
	public InsertDepartureData() {
		setLayout(null);
		
		JLabel lblName = new JLabel("Nombre:");
		lblName.setBounds(33, 37, 70, 15);
		add(lblName);
		
		JLabel lblPlace = new JLabel("Lugar:");
		lblPlace.setBounds(33, 122, 70, 15);
		add(lblPlace);
		
		JLabel lblMaxTourists = new JLabel("Máximo de turistas:");
		lblMaxTourists.setBounds(33, 165, 153, 15);
		add(lblMaxTourists);
		
		JLabel lblCreationDate = new JLabel("Fecha de creación:");
		lblCreationDate.setBounds(33, 204, 139, 15);
		add(lblCreationDate);
		
		JLabel lblMeeting = new JLabel("Horario de reunión:");
		lblMeeting.setBounds(33, 78, 139, 15);
		add(lblMeeting);
		
		scheduleMeeting = new JSpinner();
		scheduleMeeting.setBounds(317, 76, 112, 20);
		
		SpinnerDateModel spinnerModel = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
        scheduleMeeting = new JSpinner(spinnerModel);
        scheduleMeeting.setBounds(310, 79, 119, 19);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(scheduleMeeting, "HH:mm");
        scheduleMeeting.setEditor(timeEditor);
        add(scheduleMeeting);
		
		
		dateChooserMeeting = new JDateChooser();
		dateChooserMeeting.setBounds(179, 78, 126, 19);
		add(dateChooserMeeting);
		
		
		textFieldname = new JTextField();
		add(textFieldname);
		textFieldname.setColumns(10);
		textFieldname.setForeground(Color.GRAY);
		textFieldname.setText("Ingrese un nombre.");
		textFieldname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textFieldname.getText().equals("Ingrese un nombre.")) {
					textFieldname.setText("");
					textFieldname.setForeground(Color.BLACK);
				}
			}
		});
		textFieldname.setBounds(121, 35, 308, 19);
		
		
		
		textFieldPlace = new JTextField();
		textFieldPlace.setBounds(121, 120, 308, 19);
		add(textFieldPlace);
		textFieldPlace.setColumns(10);
		textFieldPlace.setForeground(Color.gray);
		textFieldPlace.setText("Ingrese lugar de encuentro.");
		textFieldPlace.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textFieldPlace.getText().equals("Ingrese lugar de encuentro.")) {
					textFieldPlace.setText("");
					textFieldPlace.setForeground(Color.BLACK);
				}
			}
		});
			
		
		textFieldMaxTourists = new JTextField();
		textFieldMaxTourists.setBounds(179, 163, 250, 19);
		add(textFieldMaxTourists);
		textFieldMaxTourists.setColumns(10);
		textFieldMaxTourists.setForeground(Color.gray);
		textFieldMaxTourists.setText("Ingrese el máximo de turistas.");
		textFieldMaxTourists.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textFieldMaxTourists.getText().equals("Ingrese el máximo de turistas.")) {
					textFieldMaxTourists.setText("");
					textFieldMaxTourists.setForeground(Color.BLACK);
				}
			}
		});
		
		
		creationDateChooser = new JDateChooser();
		creationDateChooser.setBounds(179, 200, 250, 19);
		add(creationDateChooser);

		
	}
	
	
	public LocalDate getCreationDate() {
		
		Date convert = creationDateChooser.getDate();
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDate creationDate= convert.toInstant().atZone(zoneId).toLocalDate();
		
		return creationDate ;
	}
	
	public LocalDate getMeetingDate() {
		
		Date convert = dateChooserMeeting.getDate();
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDate meetingDate = convert.toInstant().atZone(zoneId).toLocalDate();
		
		return meetingDate;
	}
	
	public LocalTime getSpinnerSchedule() {
		LocalTime selectedSchedule = (LocalTime) scheduleMeeting.getValue();
		return selectedSchedule;
	}
	
	public Integer getMaxTourists() {
		String maxTouristsStr = textFieldMaxTourists.getText();
		Integer maxTourists = Integer.parseInt(maxTouristsStr);
		return maxTourists;
	}
	
	
	public String getName() {
		return textFieldname.getText();
	}
	
	
	public String getPlace() {
		return textFieldPlace.getText();
	}
	
	
}
