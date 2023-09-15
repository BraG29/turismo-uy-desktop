package turismouydesktop.gui.panels;

import javax.swing.JPanel;
import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import java.time.format.DateTimeFormatter;
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
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ShowUserData extends JPanel {
	
	private JTextArea textAreaNickname;
	private JScrollPane scrollPaneEmail;
	private JTextArea textAreaEmail;
	private JTextArea textAreaName;
	private JTextArea textAreaLastName;
	private JTextArea textAreaBirthDate;
	

	
	//Para Proveedor:
	
	private JLabel lblWebSite;
	private JScrollPane scrollPaneWebSite;
	private JTextArea textAreaWebSite;

	private JLabel lblDescription;
	private JScrollPane scrollPaneDescription;
	private JTextArea textAreaDescription;
	
	//Para Turist:
	JLabel lblNacionality;
	private JTextArea textAreaNacionality;
	
	/**
	 * Crea el panel para mostrar los datos de un usuario
	 */
	public ShowUserData() {
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
		scrollPaneEmail.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneEmail.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
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
		
		//Para Turista
		lblNacionality = new JLabel("Nacionalidad:");
		lblNacionality.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNacionality.setBounds(12, 147, 102, 15);
		add(lblNacionality);
		
		textAreaNacionality = new JTextArea();
		textAreaNacionality.setBounds(125, 147, 127, 15);
		textAreaNacionality.setEditable(false);
		add(textAreaNacionality);
		

		
		//Para Proveedor
		
		lblWebSite = new JLabel("Sitio Web:");
		lblWebSite.setFont(new Font("Dialog", Font.BOLD, 12));
		lblWebSite.setBounds(12, 147, 78, 15);
		add(lblWebSite);
		
		textAreaWebSite = new JTextArea();
		textAreaWebSite.setEditable(false);
		
		scrollPaneWebSite = new JScrollPane(textAreaWebSite);
		scrollPaneWebSite.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneWebSite.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		
		scrollPaneWebSite.setBounds(98, 147, 127, 15);
		add(scrollPaneWebSite);
		
		lblDescription = new JLabel("Descripción:");
		lblDescription.setBounds(12, 174, 87, 15);
		add(lblDescription);
		
		textAreaDescription = new JTextArea();
		textAreaDescription.setLineWrap(true);
		textAreaDescription.setWrapStyleWord(true);
		textAreaDescription.setEditable(false);
		
		scrollPaneDescription = new JScrollPane(textAreaDescription);
		scrollPaneDescription.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneDescription.setBounds(108, 174, 179, 95);
		add(scrollPaneDescription);
		
		setProviderDataVisibilityFalse();
		setTouristDataVisibilityFalse();
		
	}
	
	
	/**
	 * Carga de Datos
	 * @param userData de tipo DtProvider o DtTourist
	 * @throws Exception Si se envia un DtUser
	 */
	public void loadData(DtUser userData) throws Exception {
		//Carga de datos generales:
		
		textAreaNickname.setText(userData.getNickname());
		textAreaEmail.setText(userData.getEmail());
		textAreaName.setText(userData.getName());
		textAreaLastName.setText(userData.getLastName());
		
		//Formateo de la fecha de tipo: dd/MM/yyyy
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyy");
		
		//Saco la fecha ya formateada como se explico antes
		String birthDateStr = userData
				.getBirthDate()
				.format(format);
		
		textAreaBirthDate.setText(birthDateStr);
		

		//Se Muestran datos extra de Proveedor
		if(userData instanceof DtProvider) {

			setTouristDataVisibilityFalse();
			
			DtProvider providerData = (DtProvider) userData;
			
			
			
			//Carga de datos de Proveedor
			
			textAreaWebSite.setText(providerData.getUrl());
			textAreaDescription.setText(providerData.getDescription());
			
			lblWebSite.setVisible(true);
			lblDescription.setVisible(true);
			
			scrollPaneWebSite.setVisible(true);
			scrollPaneDescription.setVisible(true);
			
		}
		//Se muestran datos extras de Turista
		else if(userData instanceof DtTourist) {
			setProviderDataVisibilityFalse();
			
			DtTourist touristData = (DtTourist) userData;
			
			
			//Carga de datos Turista

			textAreaNacionality.setText(touristData.getNationality());
			
			lblNacionality.setVisible(true);
			textAreaNacionality.setVisible(true);

		}
		//En caso de que se reciba un DtUser
		else {
			throw new IllegalArgumentException(userData.getClass() + "Debe ser una instancia de DtProvider o DtTourist");
		}
	
	}
	
	private void setProviderDataVisibilityFalse() {
		lblDescription.setVisible(false);
		lblWebSite.setVisible(false);
		scrollPaneDescription.setVisible(false);
		scrollPaneWebSite.setVisible(false);
	}
	
	private void setTouristDataVisibilityFalse() {
		lblNacionality.setVisible(false);
		textAreaNacionality.setVisible(false);
	}
}
