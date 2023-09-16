package turismouydesktop.gui.frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import turismouydesktop.gui.panels.PanelConsultActivity;

public class ConsultActivity extends JFrame {

	private JPanel contentPane;
	private PanelConsultActivity panelConsultActivity;
	private JButton button;

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

		//setteamos los atributos del frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		//agregamos boton atrás
		button = new JButton("<------");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(0, 275, 87, 25);
		button.setBackground(new Color(178, 34, 34));
		contentPane.add(button);
		
		//cargamos PanelActivity
		panelConsultActivity = new PanelConsultActivity();
		panelConsultActivity.setBounds(0, 0, 422, 244);
		contentPane.add(panelConsultActivity);
		setContentPane(contentPane);

	}
	
	
}
