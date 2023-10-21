package turismouydesktop.gui.frames;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FileChooserImage extends JFrame {

	private JPanel contentPane;
	private JFileChooser fileChooser;
	private FileChooserImageListener listener;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileChooserImage frame = new FileChooserImage();
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
	public FileChooserImage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);
		
		fileChooser = new JFileChooser();
		fileChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println("Input: " + e.getActionCommand());
				//Boton aceptar: ApproveSelection
				if(e.getActionCommand().equals("ApproveSelection")) {
//					System.out.println("Path: " + fileChooser.getSelectedFile().getAbsolutePath());
					setVisible(false);
					listener.onImageSelected(fileChooser.getSelectedFile());
					
				}
				
				//Boton cancelar: CancelSelection
				if(e.getActionCommand().equals("CancelSelection")) {
					setVisible(false);
				}
			}
		});
		fileChooser.setBounds(0, 0, 482, 297);
		fileChooser.setAcceptAllFileFilterUsed(false);
		
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Archivos de imagen", "jpg", "jpeg", "png");
        fileChooser.setFileFilter(imageFilter);
		
		contentPane.add(fileChooser);
	}

	public void setListener(FileChooserImageListener listener) {
		this.listener = listener;
	}
	
	

}
