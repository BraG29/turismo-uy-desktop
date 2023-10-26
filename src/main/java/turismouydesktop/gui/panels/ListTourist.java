package turismouydesktop.gui.panels;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtTourist;

public class ListTourist extends JPanel {

	private JList listTourists;
	private JScrollPane scrollPaneList;
	private ListTouristListener listener;
	
	private List<DtTourist> touristsData = null;
	
	/**
	 * Create the panel.
	 */
	public ListTourist() {
		setLayout(null);
		
		listTourists = new JList<String>();
		
		scrollPaneList = new JScrollPane(listTourists);
		scrollPaneList.setBounds(0, 0, 259, 282);
		
		add(scrollPaneList);
		
		IController controller = ControllerFactory.getIController();
		
		try {
			touristsData = controller.getListTourist();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		listTourists.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String selectedUser = (String) listTourists.getSelectedValue();
				if(listener != null && selectedUser != null) {
					Long id = touristsData
							.stream()
							.filter(user -> selectedUser.contains(user.getNickname()))
							.findFirst()
							.get()
							.getId();
					listener.onListTouristSelected(id);
				}
			}
		});
		
		
		String[] dataString = touristsData
		        .stream()
		        .map(user -> user.getNickname() + "<" + user.getEmail() + ">")
		        .toArray(String[]::new);
		
		loadList(dataString);
		
	}
	
	private void loadList(String[] loadData) {
		listTourists.setModel(new AbstractListModel() {
			String[] values = loadData;

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}
	
	public void setListener(ListTouristListener listener) {
		this.listener = listener;
	}

}
