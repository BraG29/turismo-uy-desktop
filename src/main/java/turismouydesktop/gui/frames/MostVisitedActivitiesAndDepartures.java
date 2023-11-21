package turismouydesktop.gui.frames;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;

import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;

import uy.turismo.servidorcentral.logic.datatypes.DtTouristicActivity;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicDeparture;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MostVisitedActivitiesAndDepartures extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;

	public static class MergedData {
		private Long id;
		private String name;
		private String provider;
		private Integer visits;
		private String type; // "Activity" or "Departure"

		public MergedData(Long id, String name, String provider, Integer visits, String type) {
			this.id = id;
			this.name = name;
			this.provider = provider;
			this.visits = visits;
			this.type = type;
		}

		public Long getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getProvider() {
			return provider;
		}

		public Integer getVisits() {
			return visits;
		}

		public String getType() {
			return type;
		}
	}

	ArrayList<DtTouristicActivity> activities = null;
	ArrayList<DtTouristicDeparture> departures = null;

	public MostVisitedActivitiesAndDepartures() {
		RefreshList();
        getContentPane().setLayout(null);

        // Create a JTable with the model
        table = new JTable(model);

        // Set the preferred size of the JTable
        table.setPreferredScrollableViewportSize(new Dimension(800, 800));
        
    	// Increase the row height
        table.setRowHeight(23); // Adjust the height as needed

        // Create a scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 853, 247);

        // Add the scroll pane to the frame
        getContentPane().add(scrollPane);

		JButton btnReload = new JButton("Recargar");
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RefreshList();
			}
		});
		scrollPane.setRowHeaderView(btnReload);

		// Set frame properties
		setTitle("Actividades/Salidas mas Visitadas");
		setSize(855, 282);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		// Create and display the GUI
		SwingUtilities.invokeLater(() -> {
			new MostVisitedActivitiesAndDepartures().setVisible(true);
		});
	}

	public void RefreshList() {

		// Sample data for departures and activities
		IController controller = ControllerFactory.getIController();

		ArrayList<DtTouristicActivity> activities = controller.getListTouristicActivityWithVisits();
		ArrayList<DtTouristicDeparture> departures = new ArrayList<>();
		for (DtTouristicActivity act : activities) {
			departures.addAll(act.getDepartures());
		}

		// Merge data from both arrays into a list of MergedData
		List<MergedData> mergedList = new ArrayList<>();

		for (DtTouristicDeparture departure : departures) {
			mergedList.add(new MergedData(departure.getId(), departure.getTouristicActivity().getName(),
					departure.getTouristicActivity().getProvider().getName(), departure.getVisits(), "Salida"));
		}

		for (DtTouristicActivity activity : activities) {
			mergedList.add(new MergedData(activity.getId(), activity.getName(), activity.getProvider().getName(),
					activity.getVisits(), "Actividad"));
		}

		// Sort the list based on visits in descending order
		mergedList.sort((data1, data2) -> Integer.compare(data2.getVisits(), data1.getVisits()));
		

		// Create a table model
		if (model == null) {
			
			model = new DefaultTableModel();
			model.addColumn("ID");
			model.addColumn("Nombre");
			model.addColumn("Provedor");
			model.addColumn("Visitas");
			model.addColumn("Tipo");
		}
		else
		{
			model.setRowCount(0); // Clear the existing rows	
		}

		
		if (table != null) {
		// Update the existing frame with the new data
			model = (DefaultTableModel) table.getModel();
		}
		

		// Populate the table model with the top 10 most visited items
		int rowCount = Math.min(10, mergedList.size());
		for (int i = 0; i < rowCount; i++) {
			MergedData mergedData = mergedList.get(i);
			model.addRow(new Object[] { mergedData.getId(), mergedData.getName(), mergedData.getProvider(),
					mergedData.getVisits(), mergedData.getType() });
		}
	}
}
