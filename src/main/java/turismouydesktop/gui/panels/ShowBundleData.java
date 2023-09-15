package turismouydesktop.gui.panels;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import uy.turismo.servidorcentral.logic.datatypes.DtTouristicActivity;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicBundle;
import uy.turismo.servidorcentral.logic.entities.TouristicActivity;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ShowBundleData extends JPanel {
	private JTextField textFieldName;
	private JTextField textFieldDiscount;
	private JTextField textFieldValidityPeriod;
	private JTextField textFieldDate;
	private JTextArea textArea;
	private JList listActivities;

	
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
		
		JLabel lblActivities = new JLabel("Actividades:");
		lblActivities.setBounds(12, 343, 95, 19);
		add(lblActivities);
		
		
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
		
		JScrollPane scrollPaneActivities = new JScrollPane();
		scrollPaneActivities.setBounds(125, 343, 234, 125);
		add(scrollPaneActivities);
		
		listActivities = new JList();
		listActivities.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				//obtener id actividad para llamar menu martin
				
				DtTouristicActivity activity = (DtTouristicActivity) listActivities.getSelectedValue();				
				Long id = activity.getId();
				
				//magia
			}
		});
		scrollPaneActivities.setViewportView(listActivities);
		
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
		
		List<DtTouristicActivity> activities = bundle.getActivities();
		//mandar nombres de la actividad a la lista.
		
		String[] activitiesName = activities.stream()
                .map(DtTouristicActivity::getName)
                .collect(Collectors.toList())
                .toArray(new String[0]);
			
		listActivities.setModel(new AbstractListModel() {
			String[] values = activitiesName;

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});	
		
	}
}
