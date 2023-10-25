package turismouydesktop.gui.frames;

import java.awt.EventQueue;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicActivity;
import uy.turismos.servidorcentral.logic.enums.ActivityState;
import javax.swing.event.ListSelectionListener;

import turismouydesktop.gui.panels.ShowActivityData;

import javax.swing.event.ListSelectionEvent;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AcceptOrRejectActivities extends JFrame {

	private JFrame frmAceptarrechazarActividad;
	private JPanel contentPane;
	private JList listActivities;
	private JLabel lblStatus;
	private JComboBox comboBoxStatus;
	private JButton btnApply;
	private PopUpWindow window;

	private ShowActivityData activityPanel = null;

	List<DtTouristicActivity> activities = null;
	DtTouristicActivity selectedActivity = null;

	private void LoadActivities() {
		IController controller = ControllerFactory.getIController();

		ActivityState state = null;

		activities = controller.getListActivityStated(state.ADDED);
		
		// obtengo nombre de las actividades del paquete..
		String[] activitiesString = activities.stream().map(DtTouristicActivity::getName).collect(Collectors.toList())
				.toArray(new String[0]);

		listActivities.setModel(new AbstractListModel() {
			String[] values = activitiesString;

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}

	private void LoadActivityData() {
		// llamo al controlador para conseguir el DtTouristicActivity entero
		IController controller = ControllerFactory.getIController();

		// controlo si es la primera vez que se llama showActivityVentana.
		if (activityPanel == null) {

			String selectedActivityS = (String) listActivities.getSelectedValue();

			selectedActivity = activities.stream()
					.filter(activities -> activities.getName().equalsIgnoreCase(selectedActivityS)).findAny().get();

			activityPanel = new ShowActivityData(controller.getTouristicActivityData(selectedActivity.getId()));

			activityPanel.setBounds(180, 20, 550, 250);

			contentPane.add(activityPanel);
			activityPanel.setVisible(true);
			contentPane.add(btnApply);
			// x, y ,width, height
			contentPane.revalidate();
			contentPane.repaint();
			this.LoadActivityData();

		} else {

			String selectedActivityS = (String) listActivities.getSelectedValue();

			selectedActivity = activities.stream()
					.filter(activities -> activities.getName().equalsIgnoreCase(selectedActivityS)).findAny().get();

			Long id = selectedActivity.getId();

			activityPanel.setValues(controller.getTouristicActivityData(id));

			lblStatus.setText(selectedActivity.getState().toString());

		}
	}

	private void LoadStateComboBox() {
		String[] states = { "Aceptar", "Rechazar" };
		comboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel(states));
	}

	private void ApplyStatus() {

		try {
			IController controller = ControllerFactory.getIController();

			Long id = selectedActivity.getId();
			ActivityState state = null;
			
			if (comboBoxStatus.getSelectedIndex() == 0) { // accepted
				controller.changeActivityState(id, state.ACCEPTED);
				window = new PopUpWindow("Exito!", "Cambio de estado a 'Aceptado' con Exito", Color.green);
				window.setVisible(true);
			} else if (comboBoxStatus.getSelectedIndex() == 1) {
				controller.changeActivityState(id, state.REJECTED);
				window = new PopUpWindow("Exito!", "Cambio de estado a 'Rechazado' con Exito", Color.green);
				window.setVisible(true);
			} else {
				controller.changeActivityState(id, state.ADDED);
				window = new PopUpWindow("ERROR!","Como rompes un combo box de esta manera? sos alto hacker o saca la loteria..., intente nuevamente",Color.BLACK);
				window.setVisible(true);
			}

		} catch (Exception e1) {
			window = new PopUpWindow("ERROR!", e1.getLocalizedMessage(), Color.RED);
			window.setVisible(true);
		}
		LoadActivities();
	}

	/**
	 * Create the application.
	 */
	public AcceptOrRejectActivities() {
		setTitle("Agregar/Rechazar Actividad");
		setSize(new Dimension(600, 400));

		frmAceptarrechazarActividad = new JFrame();
		frmAceptarrechazarActividad.setTitle("Aceptar/Rechazar Actividad");
		frmAceptarrechazarActividad.setBounds(100, 100, 553, 373);
		frmAceptarrechazarActividad.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		contentPane = new JPanel();
		getContentPane().add(contentPane, BorderLayout.CENTER);

		contentPane.setSize(new Dimension(600, 400));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		JLabel lblActivitiesTitle = new JLabel("Actividades:");
		lblActivitiesTitle.setBounds(12, 12, 95, 15);
		contentPane.add(lblActivitiesTitle);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 37, 120, 200);
		contentPane.add(scrollPane);

		listActivities = new JList();
		scrollPane.setViewportView(listActivities);
		listActivities.setDragEnabled(true);
		listActivities.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		comboBoxStatus = new JComboBox();
		LoadStateComboBox();
		comboBoxStatus.setBounds(130, 303, 102, 24);
		contentPane.add(comboBoxStatus);

		JLabel lblStatusTitle = new JLabel("Estado Actual:");
		lblStatusTitle.setBounds(12, 265, 120, 15);
		contentPane.add(lblStatusTitle);

		JLabel lblCambiarEstado = new JLabel("Cambiar Estado");
		lblCambiarEstado.setBounds(12, 308, 120, 15);
		contentPane.add(lblCambiarEstado);

		lblStatus = new JLabel("Desconocido");
		lblStatus.setBounds(130, 265, 102, 15);
		contentPane.add(lblStatus);

		btnApply = new JButton("Applicar");
		btnApply.setBounds(414, 303, 117, 25);
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApplyStatus();
			}
		});

		contentPane.setVisible(true);

		LoadActivities();

		listActivities.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				LoadActivityData();
			}
		});
	}

}
