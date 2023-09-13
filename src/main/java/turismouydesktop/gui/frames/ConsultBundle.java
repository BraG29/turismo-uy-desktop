package turismouydesktop.gui.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import turismouydesktop.gui.panels.ListTouristicBundle;
import turismouydesktop.gui.panels.ListTouristicBundleListener;
import javax.swing.JLabel;

public class ConsultBundle extends JFrame implements ListTouristicBundleListener {

	private JPanel contentPane;
	private ListTouristicBundle touristicBundleList;
	private JLabel lblPrueba;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultBundle frame = new ConsultBundle();
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
	public ConsultBundle() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		touristicBundleList = new ListTouristicBundle();
		touristicBundleList.setBounds(12, 12, 226, 246);
		touristicBundleList.setListener(this);
		contentPane.add(touristicBundleList);
		
		lblPrueba = new JLabel();
		lblPrueba.setBounds(274, 70, 122, 42);
		contentPane.add(lblPrueba);
	}

	@Override
	public void onListTouristicBundle(Long id) {
		lblPrueba.setText("Id del Bundle: " + id);
	}
}
