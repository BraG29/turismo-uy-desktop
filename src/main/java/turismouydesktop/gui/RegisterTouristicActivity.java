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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		panelActivity = new PanelActivity();
		panelActivity.setBounds(0, 0, 530, 378);
		contentPane.add(panelActivity);
		setContentPane(contentPane);
		
		JPanel panelDecor = new JPanel();
		panelDecor.setBackground(new Color(0, 206, 209));
		panelDecor.setBounds(546, -26, 96, 466);
		contentPane.add(panelDecor);
		panelDecor.setLayout(null);
		
		JButton btnNewButton = new JButton("OK!");
		btnNewButton.setBackground(new Color(60, 179, 113));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(0, 437, 96, 25);
		panelDecor.add(btnNewButton);
		
		button = new JButton("<------");
		button.setBackground(new Color(178, 34, 34));
		button.setBounds(0, 410, 87, 25);
		contentPane.add(button);
	}
}
