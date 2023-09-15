package turismouydesktop.gui.frames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PopUpWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public PopUpWindow(String titulo,String mensaje, Color color) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 340, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelTitulo = new JLabel(titulo);
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setForeground(color);
		labelTitulo.setFont(new Font("Dialog", Font.BOLD, 16));
		labelTitulo.setBounds(12, 12, 306, 34);
		contentPane.add(labelTitulo);
		
		JLabel labelMensaje = new JLabel(mensaje);
		labelMensaje.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelMensaje.setBounds(27, 59, 281, 90);
		contentPane.add(labelMensaje);
		
		JButton btnOK = new JButton("Ok");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnOK.setBounds(105, 163, 117, 25);
		contentPane.add(btnOK);
	}
}
