package turismouydesktop.gui.panels;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import uy.turismo.servidorcentral.logic.datatypes.DtProvider;
import uy.turismo.servidorcentral.logic.datatypes.DtTourist;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicActivity;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicDeparture;
import uy.turismo.servidorcentral.logic.datatypes.DtUser;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JDateChooser;

import turismouydesktop.gui.frames.FileChooserImage;
import turismouydesktop.gui.frames.FileChooserImageListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.SwingConstants;


public class UserDataManagment extends JPanel implements FileChooserImageListener {
	
	public 	enum ForWhat{
		REGISTER,
		UPDATE
	}

	private FileChooserImage fileChooserImage;
	
	private DtUser userData;
	
	private JTextArea textAreaNickname;
	private JScrollPane scrollPaneEmail;
	private JTextArea textAreaEmail;
	private JTextArea textAreaName;
	private JTextArea textAreaLastName;
	private JTextArea textAreaBirthDate;
	private JTextArea textAreaPassword;

	private JDateChooser dateChooserBirthDate;
	
	private JLabel lblImage;
	private JLabel lblPassword;	
	private BufferedImage selectedImage;

	
	JButton btnDisplayFileChooser;

	// Para Proveedor:

	private JLabel lblWebSite;
	private JScrollPane scrollPaneWebSite;
	private JTextArea textAreaWebSite;

	private JLabel lblDescription;
	private JScrollPane scrollPaneDescription;
	private JTextArea textAreaDescription;

	// Para Turist:
	JLabel lblNacionality;
	private JTextArea textAreaNacionality;

	/**
	 * Crea el panel para mostrar los datos de un usuario
	 */
	public UserDataManagment() {
		setLayout(null);

		JLabel lblName = new JLabel("Nombre:");
		lblName.setFont(new Font("Dialog", Font.BOLD, 12));
		lblName.setBounds(12, 66, 70, 15);
		add(lblName);

		JLabel lblLastName = new JLabel("Apellido: ");
		lblLastName.setFont(new Font("Dialog", Font.BOLD, 12));
		lblLastName.setBounds(12, 93, 70, 15);
		add(lblLastName);

		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNickname.setBounds(12, 12, 87, 15);
		add(lblNickname);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 12));
		lblEmail.setBounds(12, 39, 57, 15);
		add(lblEmail);

		JLabel lblBirthDate = new JLabel("Fecha Nacimiento:");
		lblBirthDate.setBounds(12, 120, 138, 15);
		add(lblBirthDate);

		textAreaNickname = new JTextArea();
		textAreaNickname.setBounds(98, 12, 127, 15);
		textAreaNickname.setEditable(false);
		add(textAreaNickname);

		textAreaEmail = new JTextArea();
		textAreaEmail.setEditable(false);

		scrollPaneEmail = new JScrollPane(textAreaEmail);
		scrollPaneEmail.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneEmail.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPaneEmail.setBounds(98, 39, 189, 15);

		add(scrollPaneEmail);

		textAreaName = new JTextArea();
		textAreaName.setBounds(98, 66, 127, 15);
		textAreaNickname.setEditable(false);
		add(textAreaName);

		textAreaLastName = new JTextArea();
		textAreaLastName.setBounds(98, 93, 127, 15);
		textAreaNickname.setEditable(false);
		add(textAreaLastName);

		textAreaBirthDate = new JTextArea();
		textAreaBirthDate.setBounds(160, 120, 127, 15);
		textAreaBirthDate.setEditable(false);
		add(textAreaBirthDate);

		dateChooserBirthDate = new JDateChooser();
		dateChooserBirthDate.setEnabled(false);

		// Para Turista
		lblNacionality = new JLabel("Nacionalidad:");
		lblNacionality.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNacionality.setBounds(12, 174, 102, 15);
		add(lblNacionality);

		textAreaNacionality = new JTextArea();
		textAreaNacionality.setBounds(125, 174, 127, 15);
		textAreaNacionality.setEditable(false);
		add(textAreaNacionality);

		// Para Proveedor

		lblWebSite = new JLabel("Sitio Web:");
		lblWebSite.setFont(new Font("Dialog", Font.BOLD, 12));
		lblWebSite.setBounds(12, 174, 78, 15);
		add(lblWebSite);

		textAreaWebSite = new JTextArea();
		textAreaWebSite.setEditable(false);

		scrollPaneWebSite = new JScrollPane(textAreaWebSite);
		scrollPaneWebSite.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneWebSite.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

		scrollPaneWebSite.setBounds(98, 174, 127, 15);
		add(scrollPaneWebSite);

		lblDescription = new JLabel("Descripción:");
		lblDescription.setBounds(12, 201, 87, 15);
		add(lblDescription);

		textAreaDescription = new JTextArea();
		textAreaDescription.setLineWrap(true);
		textAreaDescription.setWrapStyleWord(true);
		textAreaDescription.setEditable(false);

		scrollPaneDescription = new JScrollPane(textAreaDescription);
		scrollPaneDescription.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneDescription.setBounds(108, 201, 179, 95);
		add(scrollPaneDescription);
		
		lblImage = new JLabel("");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setBounds(305, 12, 200, 200);
		add(lblImage);
		
		fileChooserImage = new FileChooserImage();
		fileChooserImage.setListener(this);
		
		lblPassword = new JLabel("Contraseña:");
		lblPassword.setBounds(12, 147, 102, 15);
		add(lblPassword);
		lblPassword.setVisible(false);
		
		textAreaPassword = new JTextArea();
		textAreaPassword.setEditable(false);
		textAreaPassword.setBounds(101, 147, 186, 15);
		add(textAreaPassword);
		
		btnDisplayFileChooser = new JButton("");
		btnDisplayFileChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooserImage.setVisible(true);
			}
		});
		btnDisplayFileChooser.setBounds(315, 224, 190, 25);
		btnDisplayFileChooser.setEnabled(false);
//		add(btnSeleccionarImagen);
		
		
		setProviderDataVisibilityFalse();
		setTouristDataVisibilityFalse();

	}
	

	/**
	 * Carga de Datos
	 * 
	 * @param userData de tipo DtProvider o DtTourist
	 * @throws Exception Si se envia un DtUser
	 */
	public void loadData(DtUser userData) throws Exception {
		this.userData = userData;
		selectedImage = null;

		// Carga de datos generales:
		
		textAreaNickname.setText(userData.getNickname());
		textAreaEmail.setText(userData.getEmail());
		textAreaName.setText(userData.getName());
		textAreaLastName.setText(userData.getLastName());
		loadImage(userData.getImage());
		
		
		if (dateChooserBirthDate.isEnabled()) {
			Date birthDate = Date
					.from(userData.getBirthDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
			dateChooserBirthDate.setDate(birthDate);
		}
		if (textAreaBirthDate.isEnabled()) {
			// Formateo de la fecha de tipo: dd/MM/yyyy
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyy");

			// Saco la fecha ya formateada como se explico antes
			String birthDateStr = userData.getBirthDate().format(format);
			textAreaBirthDate.setText(birthDateStr);
			
		}

		// Se Muestran datos extra de Proveedor
		if (userData instanceof DtProvider) {

			setTouristDataVisibilityFalse();

			DtProvider providerData = (DtProvider) userData;

			// Carga de datos de Proveedor

			textAreaWebSite.setText(providerData.getUrl());
			textAreaDescription.setText(providerData.getDescription());
			
			setProviderDataVisibilityTrue();
		}
		// Se muestran datos extras de Turista
		else if (userData instanceof DtTourist) {
			setProviderDataVisibilityFalse();

			DtTourist touristData = (DtTourist) userData;

			// Carga de datos Turista

			textAreaNacionality.setText(touristData.getNationality());
			
			setTouristDataVisibilityTrue();
		}
		// En caso de que se reciba un DtUser
		else {
			throw new IllegalArgumentException(
					userData.getClass() + "Debe ser una instancia de DtProvider o DtTourist");
		}

	}
	
	public BufferedImage scalateImage(BufferedImage baseImage) {
		double scaleX = (double) 200 / baseImage.getWidth();
		double scaleY = (double) 200 / baseImage.getHeight();
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
	
	public void setTouristDataVisibilityTrue() {
		lblNacionality.setVisible(true);
		textAreaNacionality.setVisible(true);
		
	}
	
	public void setProviderDataVisibilityTrue() {
		lblWebSite.setVisible(true);
		lblDescription.setVisible(true);

		scrollPaneWebSite.setVisible(true);
		scrollPaneDescription.setVisible(true);
		
	}

	public void setProviderDataVisibilityFalse() {
		lblDescription.setVisible(false);
		lblWebSite.setVisible(false);
		scrollPaneDescription.setVisible(false);
		scrollPaneWebSite.setVisible(false);
		
	}

	public void setTouristDataVisibilityFalse() {
		lblNacionality.setVisible(false);
		textAreaNacionality.setVisible(false);
		
	}

	public void enableToEditForUpdate() {

		btnDisplayFileChooser.setText("Cambiar Imagen");
		
		// General
		textAreaName.setEditable(true);
		textAreaLastName.setEditable(true);

		Rectangle birthDateBounds = textAreaBirthDate.getBounds();
		textAreaBirthDate.setEnabled(false);
		remove(textAreaBirthDate);
		
		dateChooserBirthDate.setBounds(birthDateBounds);
		dateChooserBirthDate.setEnabled(true);
		add(dateChooserBirthDate);
		
		btnDisplayFileChooser.setEnabled(true);
		add(btnDisplayFileChooser);
		
		// Para Proveedor:

		textAreaWebSite.setEditable(true);
		textAreaDescription.setEditable(true);

		// Para Turista:

		textAreaNacionality.setEditable(true);

	}
	
	public void enableToEditForRegister() {
		
		textAreaNickname.setEditable(true);
		textAreaEmail.setEditable(true);
		textAreaPassword.setEditable(true);
		lblPassword.setVisible(true);
		
		enableToEditForUpdate();
		btnDisplayFileChooser.setText("Seleccionar Imagen");
	}
	

	public DtUser getModifiedData() {
		DtUser userDataOutput = null;

		
		if(!areChanges()) {
			return userDataOutput;
		}

		if (userData instanceof DtProvider) {
			userDataOutput = getProviderData(ForWhat.UPDATE);
		} else if (userData instanceof DtTourist) {
			userDataOutput = getTouristData(ForWhat.UPDATE);
		}
		
		return userDataOutput;
	}
	
	public DtTourist getTouristData(ForWhat forWhat){
			
		Long id = 0L;
		String password = "";

		switch(forWhat){
			case REGISTER:
				id = null;
				password = textAreaPassword.getText();
				break;
			case UPDATE:
				id = userData.getId();
				password = userData.getPassword();
		}
		LocalDate birthDate = null;
		if(dateChooserBirthDate.getDate() != null) {
			birthDate = dateChooserBirthDate.getDate()
					.toInstant()
					.atZone(ZoneId.systemDefault())
					.toLocalDate();
		}
		

		
		DtTourist touristDataOutput = new DtTourist(
				id,
				textAreaName.getText(),
				textAreaNickname.getText(),
				textAreaEmail.getText(),
				textAreaLastName.getText(),
				birthDate,
				selectedImage,
				textAreaNacionality.getText(),
				null,
				password,
				null,
				null,
				null
				);
		
		return touristDataOutput;	
	}
	
	public DtProvider getProviderData(ForWhat forWhat) {
		Long id = 0L;
		String password = "";
		
		switch(forWhat){
			case REGISTER:
				id = null;
				password = textAreaPassword.getText();
				break;
			case UPDATE:
				id = userData.getId();
				password = userData.getPassword();
		}
		
		
		LocalDate birthDate = null;
		if(dateChooserBirthDate.getDate() != null) {
			birthDate = dateChooserBirthDate.getDate()
					.toInstant()
					.atZone(ZoneId.systemDefault())
					.toLocalDate();
		}
		
		DtProvider providerDataOutput = new DtProvider(
				id,
				textAreaName.getText(),
				textAreaNickname.getText(),
				textAreaEmail.getText(),
				textAreaLastName.getText(),
				birthDate,
				selectedImage,
				textAreaWebSite.getText(),
				textAreaDescription.getText(),
				null,
				password
				);
		
		return providerDataOutput;
		
	}
	
	
	public Boolean areChanges() {
		LocalDate birthDate = null;
		if(dateChooserBirthDate.getDate() != null) {
			birthDate = dateChooserBirthDate.getDate()
					.toInstant()
					.atZone(ZoneId.systemDefault())
					.toLocalDate();
		}
		
		if(
			userData.getName().equalsIgnoreCase(textAreaName.getText()) &&
			userData.getLastName().equalsIgnoreCase(textAreaLastName.getText()) &&
			userData.getBirthDate().isEqual(birthDate) && 
			isSame(userData.getImage())) {
			
			if(userData instanceof DtProvider) {
				DtProvider providerData = (DtProvider) userData;
				if(
					providerData.getDescription().equalsIgnoreCase(textAreaDescription.getText()) &&
					providerData.getUrl().equalsIgnoreCase(textAreaWebSite.getText())) {
						
						return false;
				}
			}else if(userData instanceof DtTourist) {
				DtTourist touristData = (DtTourist) userData;
				
				if(
					touristData.getNationality().equalsIgnoreCase(textAreaNacionality.getText())) {
					
					return false;
				}
			}	
		}
		
		return true;
			
	}
	
	/**
	 * Compara dos imagenes de tipo BufferedImage y Icon, si son la misma devuelve true
	 * @param bufferedImage
	 * @param imageIcon
	 * @return
	 */
	public Boolean isSame(BufferedImage userImage) {
		if(selectedImage != null  && userImage != null) {
			
			if (userImage.getWidth() != selectedImage.getWidth() || userImage.getHeight() != userImage.getHeight()) {
				return false;
			}
			
			DataBufferByte dfByteUser = (DataBufferByte) userImage.getRaster().getDataBuffer();
			DataBufferByte dfByteNew = (DataBufferByte) selectedImage.getRaster().getDataBuffer();
			
			// Convierte las imágenes en matrices de bytes
			byte[] pixelsUser = dfByteUser.getData();
			byte[] pixelsNew = dfByteNew.getData();
			
			// Compara los píxeles de ambas imágenes
			for (int i = 0; i < pixelsUser.length; i++) {
				if (pixelsUser[i] != pixelsNew[i]) {
					return false;
				}
			}
			
			return true;
		}
		
		if(selectedImage == null) {
			return true;
		}
		
		return false;
	
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
