package turismouydesktop.gui.panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import uy.turismo.servidorcentral.logic.datatypes.DtTouristicDeparture;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ShowDepartureData extends JPanel {

	private JTextArea textAreaName;
	private JTextArea textAreaMaxToursit;
	private JTextArea textAreaUploadDate;
	private JTextArea textAreaDartureDateTime;
	private JTextArea textAreaPlace;
	private JTextArea textAreaActivity;

	private JScrollPane scrollPaneName;
	private JScrollPane scrollPaneActivity;
	private JScrollPane scrollPaneDartureDateTime;
	private JScrollPane scrollPanePlace;
	private JLabel lblImage;
	
	private BufferedImage selectedImage;

	/**
	 * Create the panel.
	 */
	public ShowDepartureData() {
		setLayout(null);
		
		JLabel lblName = new JLabel("Nombre:");
		lblName.setBounds(12, 12, 70, 15);
		add(lblName);
		
		JLabel lblMaxTourist = new JLabel("Maximo de Turistas:");
		lblMaxTourist.setBounds(12, 39, 142, 15);
		add(lblMaxTourist);
		
		JLabel lblUploadDate = new JLabel("Fecha de Alta:");
		lblUploadDate.setBounds(12, 66, 113, 15);
		add(lblUploadDate);
		
		JLabel lblDepartureDateTime = new JLabel("Fecha de Salida:");
		lblDepartureDateTime.setBounds(12, 93, 125, 15);
		add(lblDepartureDateTime);
		
		JLabel lblPlace = new JLabel("Lugar:");
		lblPlace.setBounds(12, 120, 51, 15);
		add(lblPlace);
		
		JLabel lblActivity = new JLabel("Actividad:");
		lblActivity.setBounds(12, 147, 70, 15);
		add(lblActivity);

		textAreaName = new JTextArea();
		textAreaName.setEditable(false);
		
		scrollPaneName = new JScrollPane(textAreaName);
		scrollPaneName.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneName.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPaneName.setBounds(79, 12, 200, 20);
		add(scrollPaneName);
		
		textAreaMaxToursit = new JTextArea();
		textAreaMaxToursit.setEditable(false);
		textAreaMaxToursit.setBounds(160, 39, 39, 20);
		add(textAreaMaxToursit);
		
		textAreaUploadDate = new JTextArea();
		textAreaUploadDate.setEditable(false);
		textAreaUploadDate.setBounds(125, 66, 96, 20);
		add(textAreaUploadDate);	
		
		textAreaDartureDateTime = new JTextArea();
		textAreaDartureDateTime.setEditable(false);
		
		scrollPaneDartureDateTime = new JScrollPane(textAreaDartureDateTime);
		scrollPaneDartureDateTime.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneDartureDateTime.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPaneDartureDateTime.setBounds(143, 93, 96, 20);
		add(scrollPaneDartureDateTime);
		
		textAreaPlace = new JTextArea();
		textAreaPlace.setEditable(false);
		
		scrollPanePlace = new JScrollPane(textAreaPlace);
		scrollPanePlace.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPanePlace.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPanePlace.setBounds(73, 120, 142, 20);
		add(scrollPanePlace);	
		
		
		textAreaActivity = new JTextArea();		
		textAreaActivity.setEditable(false);
		
		scrollPaneActivity = new JScrollPane(textAreaActivity);
		scrollPaneActivity.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneActivity.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPaneActivity.setBounds(89, 147, 124, 20);
		add(scrollPaneActivity);
		
		lblImage = new JLabel("");
		lblImage.setBounds(245, 40, 125, 125);
		add(lblImage);
	}
	
	//Carga de datos
	public void loadData(DtTouristicDeparture departureData) {
		selectedImage = null;
		
		//Fromatos de Fecha
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyy");
		DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyy - HH:mm");

		textAreaName.setText("");
		textAreaMaxToursit.setText("");
		textAreaUploadDate.setText("");
		textAreaDartureDateTime.setText("");
		textAreaPlace.setText("");
		textAreaActivity.setText("");
		loadImage(departureData.getImage());
		
		textAreaName.setText(departureData.getName());
		textAreaMaxToursit.setText(departureData.getMaxTourist().toString());
		textAreaUploadDate.setText(departureData.getUploadDate()
				.format(dateFormat));
		textAreaDartureDateTime.setText(departureData.getDepartureDateTime()
				.format(dateTimeFormat));
		textAreaPlace.setText(departureData.getPlace());
		textAreaActivity.setText(departureData.getTouristicActivity().getName());

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
}
