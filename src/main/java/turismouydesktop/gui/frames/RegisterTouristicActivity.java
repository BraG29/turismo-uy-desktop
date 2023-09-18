package turismouydesktop.gui.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import turismouydesktop.gui.panels.PanelActivity;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterTouristicActivity extends JFrame {

	private JPanel contentPane;
	private PanelActivity panelActivity;
	private JButton button;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterTouristicActivity frame = new RegisterTouristicActivity();
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
	public RegisterTouristicActivity() {
		setTitle("Alta de Actividad Turistica");
		
		//setteamos los atributos del frame
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 690, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
//		//agregamos boton atrás
//		button = new JButton("<------");
//		button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				dispose();
//			}
//		});
//		button.setBounds(0, 410, 87, 25);
//		button.setBackground(new Color(178, 34, 34));
//		contentPane.add(button);
		
		//cargamos PanelActivity
		panelActivity = new PanelActivity();
		panelActivity.setBounds(0, 0, 668, 378);
		contentPane.add(panelActivity);
		setContentPane(contentPane);
	}
}
