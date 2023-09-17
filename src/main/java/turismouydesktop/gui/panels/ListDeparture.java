package turismouydesktop.gui.panels;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicActivity;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicBundle;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicDeparture;

public class ListDeparture extends JPanel {
	
	private ListDepartureListener listener;
	private JList jListDeparture;
	/**
	 * Create the panel.
	 */
	public ListDeparture() {
		setLayout(null);
	
		jListDeparture = new JList<String>();
		jListDeparture.setToolTipText("");
		jListDeparture.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		JScrollPane listScrollPane = new JScrollPane(jListDeparture);
		listScrollPane.setBounds(0, 0, 202, 276);
		add(listScrollPane);
		
		
		jListDeparture.setModel(new AbstractListModel() {
			String[] values = {};

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
	}
	
	
	public void reLoadList(DtTouristicActivity DTA) {	
		
		List<DtTouristicDeparture> listDepartures = DTA.getDepartures();
				
		String[] departureName = listDepartures.stream()
	                .map(DtTouristicDeparture::getName)
	                .collect(Collectors.toList())
	                .toArray(new String[0]);
			
		jListDeparture.setModel(new AbstractListModel() {
			String[] values = departureName;
	
			public int getSize() {
				return values.length;
			}
	
			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}
	
	
	
	public void setListener(ListDepartureListener listener) {
		this.listener = listener;
	}

}


