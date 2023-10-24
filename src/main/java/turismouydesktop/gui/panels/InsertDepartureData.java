package turismouydesktop.gui.panels;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.text.MaskFormatter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import turismouydesktop.gui.frames.FileChooserImage;
import turismouydesktop.gui.frames.FileChooserImageListener;

public class InsertDepartureData extends JPanel implements FileChooserImageListener {
	private JTextField textFieldname;
	private JTextField textFieldPlace;
	private JTextField textFieldMaxTourists;
	private JDateChooser creationDateChooser;
	private JDateChooser dateChooserMeeting;
	private JSpinner scheduleMeeting;
	private JFormattedTextField textFieldTime;
	
	private JLabel lblImage;
	private FileChooserImage fileChooserImage;
	private BufferedImage selectedImage;
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
		
	
		textFieldTime = new JFormattedTextField();
		textFieldTime.setForeground(Color.gray);
		textFieldTime.setText("Use HH:mm");
		textFieldTime.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(textFieldTime.getText().equals("Use HH:mm")) {
					textFieldTime.setText("");
					textFieldTime.setForeground(Color.BLACK);
				}
			}
		});
		textFieldTime.setBounds(317, 78, 114, 19);
		add(textFieldTime);
		textFieldTime.setColumns(10);
		
		lblImage = new JLabel("");
		lblImage.setBounds(450, 35, 150, 150);
		add(lblImage);
		
		fileChooserImage = new FileChooserImage();
		fileChooserImage.setListener(this);
		
		JButton btnDisplayFileChooser = new JButton("Agregar Imagen");
		btnDisplayFileChooser.setBounds(450, 200, 150, 25);
		add(btnDisplayFileChooser);
		btnDisplayFileChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooserImage.setVisible(true);
			}
		});
		
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
	
	public String getTime() {
		String timeText = textFieldTime.getText();
	
		return timeText;
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
	
	public BufferedImage getSelectedImage() {
		return selectedImage;
	}
	
	public BufferedImage scalateImage(BufferedImage baseImage) {
		double scaleX = (double) lblImage.getWidth() / baseImage.getWidth();
		double scaleY = (double) lblImage.getHeight() / baseImage.getHeight();
		AffineTransform at = AffineTransform.getScaleInstance(scaleX, scaleY);
		
		// Crear una operación de transformación
		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		
		// Crear una nueva imagen escalada
		BufferedImage scaletedImage = new BufferedImage(200, 200, baseImage.getType());
		
		// Aplicar la operación de transformación para escalar la imagen
		Graphics2D g2d = scaletedImage.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.drawImage(baseImage, op, 0, 0);
		g2d.dispose();
		return scaletedImage;
	}
	
	public void loadImage(BufferedImage imageForLoad){
		ImageIcon image = null;
		
		if(imageForLoad != null) {
			BufferedImage scaletedImage = scalateImage(imageForLoad);
			image = new ImageIcon(scaletedImage);
		}else {
//			lblImage.setIcon(image);
			lblImage.setText("No Image");
			lblImage.setForeground(Color.RED);
		}
		lblImage.setIcon(image);		
	}
	
	@Override
	public void onImageSelected(File image) {
//		System.out.println("Path: " + image.getAbsolutePath()
//				+ "\nNombre: " + image.getName());
		try {
			selectedImage = ImageIO.read(image);
		
			
		} catch (Exception e) {
			loadImage(selectedImage);
			System.out.println("Error: " + e.getMessage());
		}
		loadImage(selectedImage);
		
		
	}
}
