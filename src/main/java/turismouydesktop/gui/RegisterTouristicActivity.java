package turismouydesktop.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
		//setteamos los atributos del frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		//agregamos boton atrás
		button = new JButton("<------");
		button.setBackground(new Color(178, 34, 34));
		button.setBounds(0, 410, 87, 25);
		contentPane.add(button);
		
		//cargamos PanelActivity
		panelActivity = new PanelActivity();
		panelActivity.setBounds(0, 0, 668, 378);
		contentPane.add(panelActivity);
		setContentPane(contentPane);
		
		//agregamos botón adelamte
		JButton btnNewButton = new JButton("OK!");
		btnNewButton.setBounds(589, 410, 96, 25);
		contentPane.add(btnNewButton);
		btnNewButton.setBackground(new Color(60, 179, 113));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
	}
}
