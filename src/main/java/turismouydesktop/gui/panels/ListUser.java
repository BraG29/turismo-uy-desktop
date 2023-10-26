package turismouydesktop.gui.panels;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicDeparture;
import uy.turismo.servidorcentral.logic.datatypes.DtUser;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ListUser extends JPanel {
	
	ListUserListener listener;

	JScrollPane scrollPaneList;
	JList listUsers;
	
	/**
	 * Crea panel de ListUser
	 */
	public ListUser() {
		setLayout(null);
		
		listUsers = new JList<String>();
		
		scrollPaneList = new JScrollPane(listUsers);
		scrollPaneList.setBounds(0, 0, 259, 282);
		add(scrollPaneList);
		
		IController controller = ControllerFactory.getIController();
		
		List<DtUser> usersData;
		try {
			usersData = controller.getListUser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] usersKey = usersData
		        .stream()
		        .map(user -> user.getNickname() + "<" + user.getEmail() + ">")
		        .toArray(String[]::new);
		
		loadList(usersKey);
		
		//Evento lanzado cuano se seleccione un usuario en la lista
		listUsers.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Integer selectedUser = listUsers.getSelectedIndex();
				if(listener != null && selectedUser != null) {
					Long id = usersData
							.get(selectedUser)
							.getId();					
					listener.onSelectUser(id);
				}
			}
		});
	}
	
	private void loadList(String[] loadData) {
		listUsers.setModel(new AbstractListModel() {
			String[] values = loadData;

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}

	public void setListener(ListUserListener listener) {
		this.listener = listener;
	}
	
}
