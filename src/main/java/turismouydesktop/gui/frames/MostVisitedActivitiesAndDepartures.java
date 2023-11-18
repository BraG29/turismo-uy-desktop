//package turismouydesktop.gui.frames;
//
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.UIManager;
//import javax.swing.border.BevelBorder;
//
//public class MostVisitedActivitiesAndDepartures extends JFrame {
//
//	private static final long serialVersionUID = 1L;
//	private JPanel contentPane;
//	private JTable table;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MostVisitedActivitiesAndDepartures frame = new MostVisitedActivitiesAndDepartures();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public MostVisitedActivitiesAndDepartures() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 610, 241);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//		setContentPane(contentPane);
//		
//		table = new JTable();
//		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		table.setFillsViewportHeight(true);
//		table.setBounds(15, 15, 580, 185);
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//				{null, null, null, null, ""},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//			},
//			new String[] {
//				"ID", "Tipo", "Nombre", "Provedor", "Visitas"
//			}
//		) {
//			Class[] columnTypes = new Class[] {
//				Long.class, Object.class, String.class, String.class, String.class
//			};
//			public Class getColumnClass(int columnIndex) {
//				return columnTypes[columnIndex];
//			}
//		});
//		table.getColumnModel().getColumn(0).setResizable(false);
//		table.getColumnModel().getColumn(0).setPreferredWidth(30);
//		table.getColumnModel().getColumn(1).setResizable(false);
//		table.getColumnModel().getColumn(1).setPreferredWidth(100);
//		table.getColumnModel().getColumn(2).setResizable(false);
//		table.getColumnModel().getColumn(2).setPreferredWidth(150);
//		table.getColumnModel().getColumn(3).setResizable(false);
//		table.getColumnModel().getColumn(3).setPreferredWidth(150);
//		table.getColumnModel().getColumn(4).setResizable(false);
//		table.getColumnModel().getColumn(4).setPreferredWidth(150);
//		contentPane.setLayout(null);
//		contentPane.add(table);
//	}
//
//}
