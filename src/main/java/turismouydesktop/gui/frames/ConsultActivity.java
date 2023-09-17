package turismouydesktop.gui.frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import turismouydesktop.gui.panels.ListDepartment;
import turismouydesktop.gui.panels.ListDepartmentListener;
import turismouydesktop.gui.panels.ListTouristicBundle;
import turismouydesktop.gui.panels.ListTouristicBundleListener;
import turismouydesktop.gui.panels.PanelConsultActivity;
import turismouydesktop.gui.panels.ShowActivityData;
import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtDepartment;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicActivity;

public class ConsultActivity extends JFrame implements ListDepartmentListener, ListTouristicBundleListener{

	private JPanel contentPane;
	
	private ListTouristicBundle jListBundle;
	private ListDepartment jListDepartment;
	
	private ShowActivityData showActivityVentana = null;
	private List<DtTouristicActivity> actividades = null;
	
	private JList listActivities = new JList();
	
	//atributos visuales
	private JScrollPane scrollPaneActivities = new JScrollPane();
	private final JLabel lblActividadAElegir = new JLabel("Actividad a elegir:");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultActivity frame = new ConsultActivity();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsultActivity() {
		setTitle("Consultar Actividad Turística");
		getContentPane().setLayout(null);
		//setteamos los atributos del frame
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 525, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		jListDepartment = new ListDepartment(140,200);
		jListDepartment.setBounds(0, 42, 179, 200);
		getContentPane().add(jListDepartment);
		jListDepartment.setListener(this);
		
		
		
		
		JLabel lblSeleccioneDepartamento = new JLabel("Departamento a elegir:");
		lblSeleccioneDepartamento.setBounds(0, 26, 200, 15);
		getContentPane().add(lblSeleccioneDepartamento);

		scrollPaneActivities.setBounds(230, 42, 140, 200);
		getContentPane().add(scrollPaneActivities);
		scrollPaneActivities.setViewportView(listActivities);
		scrollPaneActivities.setVisible(false);
		
		lblActividadAElegir.setBounds(230, 26, 140, 15);
		getContentPane().add(lblActividadAElegir);
		lblActividadAElegir.setVisible(false);
		
		listActivities.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				//llamo al controlador para conseguir el DtTouristicActivity entero
				IController controller = ControllerFactory.getIController();
				
				if(showActivityVentana == null) {
					reDimensionWindow(0,170);
//					jListBundle = new ListTouristicBundle(controller.getTouristicActivityData(actividades.get(listActivities.getSelectedIndex()).getId()));
//					jListBundle.setBounds(0, 42, 179, 200);
//					getContentPane().add(jListBundle);
//					jListBundle.setVisible(true);
//					
					showActivityVentana = new ShowActivityData(controller.getTouristicActivityData(actividades.get(listActivities.getSelectedIndex()).getId()));
					showActivityVentana.setBounds(10, 240, 600, 200);
					//x, y ,width, height
					getContentPane().add(showActivityVentana);
					showActivityVentana.setVisible(true);
				}else {
					showActivityVentana.setValues(controller.getTouristicActivityData(actividades.get(listActivities.getSelectedIndex()).getId()));
				}
			}
		});
	}
	
	
	@Override
	public void onListDepartmentSelected(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onListDepartmentSelectedDt(DtDepartment department) {
		
		actividades = department.getActivities();
		
		//creo los Array de String.
		String[] activitiesStringArray = new String[actividades.size()];
							
		//itero sobre el DT para darles valor a las array de String
		int i = 0;
		for(DtTouristicActivity acti : actividades) {
				activitiesStringArray[i] = acti.getName();
				i++;
		}
		loadList(activitiesStringArray);
		
		scrollPaneActivities.setVisible(true);
		listActivities.setVisible(true);
		lblActividadAElegir.setVisible(true);
		
		if(showActivityVentana != null) {
			showActivityVentana.setVisible(false);
			showActivityVentana = null;
			reDimensionWindow(0,-170);
		}
	}
	
	
	private void loadList(String[] loadData) {
		
		listActivities.setModel(new AbstractListModel() {
			String[] values = loadData;

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listActivities.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listActivities.setFont(new Font("Dialog", Font.PLAIN, 12));
	}


	@Override
	public void onListTouristicBundle(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	private void reDimensionWindow(int width, int height) {
		
		Rectangle contentDimensions = this.getBounds();
		//x,y,widht,height
		contentDimensions.height += height;
		//contentDimensions.width += 500;
		
		this.setBounds(contentDimensions);
	}
}
