package turismouydesktop.gui.panels;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicBundle;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ListTouristicBundle extends JPanel {


	private ListTouristicBundleListener listener;
	private JList listBundlesJList;
	
	/**
	 * Create the panel.
	 */
	public ListTouristicBundle() {
		IController controller = ControllerFactory.getIController();
		
		
		setLayout(null);
	
		listBundlesJList = new JList<String>();
		
		JScrollPane listScrollPane = new JScrollPane(listBundlesJList);
		listScrollPane.setBounds(0, -1, 202, 276);
		add(listScrollPane);
		
		List<DtTouristicBundle> listBundles = controller.getListTouristicBundle();
		
		String[] bundlesName = listBundles.stream()
                .map(DtTouristicBundle::getName)
                .collect(Collectors.toList())
                .toArray(new String[0]);
		
		listBundlesJList.setModel(new AbstractListModel() {
			String[] values = bundlesName;

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		listBundlesJList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				String bundleName = (String) listBundlesJList.getSelectedValue();
				if (listener != null && bundleName != null) {
				
					Long id = listBundles
							.stream()
							.filter(bundle -> bundle.getName().equalsIgnoreCase(bundleName))
							.findFirst()
							.get()
							.getId();
					listener.onListTouristicBundle(id);
				}
			}
		});
		
	}
	
	public void setListener(ListTouristicBundleListener listener) {
		this.listener = listener;
	}

}
