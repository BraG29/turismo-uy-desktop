package turismouydesktop.gui;

import java.awt.Font;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtProvider;

public class ListProvider extends JPanel {
	
	private ListProviderListener listener;
	/**
	 * Create the panel.
	 */
	public ListProvider() {
		setLayout(null);
		
		//llamo al controlador y le pido los DT de departamento y proveedor.
		IController controller = ControllerFactory.getIController();
		List<DtProvider> dtProviders = controller.getListProvider();
		
		//creo los Array de String.
		String[] providerStringArray = new String[dtProviders.size()];
		//String[] departmentStringArray = new String[dtDepartments.size()];
		
		//itero sobre los DT para darles valor a las array de String
		int i = 0;
		for(DtProvider prov : dtProviders) {
			providerStringArray[i] = prov.getNickname();
			i++;
		}

		
		JScrollPane scrollPaneProvider = new JScrollPane();
		scrollPaneProvider.setBounds(0, 0, 179, 98);
		add(scrollPaneProvider);
		
		JList listProvider = new JList();
		scrollPaneProvider.setViewportView(listProvider);
		listProvider.setFont(new Font("Dialog", Font.PLAIN, 12));
		listProvider.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listProvider.setModel(new AbstractListModel() {
			String[] values = providerStringArray;
			
			public int getSize() {
				return values.length;
			}
				
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		listProvider.addListSelectionListener(new ListSelectionListener() {	
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				String providerName = (String) listProvider.getSelectedValue();
				if (listener != null && providerName != null) {
					
					Long id = dtProviders
							.stream()
							.filter(provider -> provider.getNickname().equalsIgnoreCase(providerName))
							.findFirst()
							.get()
							.getId();
					DtProvider dtProvedor = dtProviders.get(listProvider.getSelectedIndex());
					listener.onProviderSelected(id);
					listener.onProviderSelectedDt(dtProvedor);
					
				}
			}
		});
	}
	
	
	public void setListener(ListProviderListener listener) {
		this.listener = listener;
	}
}
