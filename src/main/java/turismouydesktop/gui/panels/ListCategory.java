package turismouydesktop.gui.panels;

import java.util.ArrayList;
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
import uy.turismo.servidorcentral.logic.datatypes.DtCategory;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicBundle;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListCategory extends JPanel {
	private ListCategoryListener listener;
	private JList jListCategory;
	private Object lastSelected;
	
	
	public ListCategory(boolean selectionMode) {
		IController controller = ControllerFactory.getIController();
		lastSelected = null;
		setLayout(null);
		
		
		
		jListCategory = new JList<String>();
		
		if(selectionMode == false) {
			jListCategory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		}else {
			jListCategory.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);			
		}
		
		jListCategory.setBounds(52, 48, 123, 142);
		
		JScrollPane listScrollPane = new JScrollPane(jListCategory);
		listScrollPane.setBounds(23, 12, 200, 202);
		add(listScrollPane);
		
		List<DtCategory> listCategories = controller.getListCategory();
		
		String[] categoriesName = listCategories.stream()
                .map(DtCategory::getName)
                .collect(Collectors.toList())
                .toArray(new String[0]);
		
		
		jListCategory.setModel(new AbstractListModel() {
			String[] values = categoriesName;

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		
		
		jListCategory.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				Object selectedValue = jListCategory.getSelectedValue();
				if (listener != null && selectedValue  != null) {
					if(!selectedValue.equals(lastSelected)) {
						
						String categoryName = (String) jListCategory.getSelectedValue();
						
						lastSelected = selectedValue;
						
						DtCategory categoryDt= listCategories
								.stream()
								.filter(category-> category.getName().equalsIgnoreCase(categoryName))
								.findFirst()
								.get();
						
						listener.onListCategorySelected(categoryDt);	
					}


				}
			}
		});	
					
	
	}

		
	
	public void setListener(ListCategoryListener listener) {
		this.listener = listener;
	}
}

