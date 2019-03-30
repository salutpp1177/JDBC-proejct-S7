package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.Controller;

public class HoraireView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controller ctrl;
	
	// label of the window
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void HoraireSalle(HashMap<String, List<String>> map, Date date, String username)  {
		
		ctrl = new Controller();
		this.setTitle("SalleHoraire");
		// size of the window
		this.setSize(600, 650);
		this.setVisible(true);

	    Container contentPane = this.getContentPane();

		JPanel myPane = new JPanel();

		JButton jb1 = new JButton("Previous Day     ");
		JButton jb2 = new JButton("Next day    ");
		
		jb1 .addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.PreviousDay(date,username);
				dispose();
			}
		});
		
		jb2 .addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.NextDay(date,username);
				dispose();
			}
		});
		
		JLabel jl1 = new JLabel("Date");
		JLabel jl2 = new JLabel("");
		jl2.setText("" + date);
		JLabel jl3 = new JLabel("User");
		JLabel jl4 = new JLabel("");
		jl4.setText("" + username);
		myPane.add(jl1);
		myPane.add(jl2);
		myPane.add(jb1);
		myPane.add(jb2);
		myPane.add(jl3);
		myPane.add(jl4);
		// -----------------------------pane1----------------
		JPanel pane1 = new JPanel();
		pane1.setLayout(new BorderLayout());
		
		// -----------------------table---------------------
		Vector vData = new Vector();
		Vector vName = new Vector();
		vName.add("Salle\\Temp");
		vName.add("8:00-10:00");
		vName.add("10:00-12:00 ");
		vName.add("14:00-16:00");
		vName.add("16:00-18:00");
		
		//-------------------------hashmap-----------------------------
		for(HashMap.Entry<String, List<String>> entry : map.entrySet()) {
		    String key = entry.getKey();
		    List<String> values = entry.getValue();
		    Vector vRow = new Vector();
		    vRow.add(key);

		    for(String e : values){
		    	
		    	vRow.add(e);
		    }
		    vData.add(vRow.clone());
		}
	
		DefaultTableModel model = new DefaultTableModel(vData, vName){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row,int column){
			       return false;
			}
		};
		
		
		JTable table = new JTable();
		
		table.setModel(model);
		/*
		table.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e){

				oldvalue = table.getValueAt(table.getSelectedRow(),table.getSelectedColumn()).toString();

			}
		});*/
		//---------------------------------------------------------//
		table.setPreferredScrollableViewportSize(new Dimension(550, 100));
	
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		pane1.add(scrollPane, BorderLayout.NORTH);
		
		
		contentPane.add(pane1, BorderLayout.CENTER);
		contentPane.add(myPane, BorderLayout.NORTH);

		// ------------------------Menu-------------------------
		JMenuBar barMenu = new JMenuBar();

		JMenu menuUtilisateur = new JMenu("Utilisateur");

		JMenuItem enregistrerU = new JMenuItem("SignIn");
		JMenuItem login = new JMenuItem("LogIn");

		
		barMenu.add(menuUtilisateur);

		menuUtilisateur.add(enregistrerU);
		menuUtilisateur.add(login);
		menuUtilisateur.addSeparator();
		
		//For sign in
		enregistrerU .addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.toCreateUser();
				
			}
		});
		login .addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.toLoginUser();
				
			}
		});
		this.setJMenuBar(barMenu);
		table.setRowSelectionAllowed(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	public static java.sql.Time strToTime(String strDate) {  
	     String str = strDate;  
	     SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");  
	     java.util.Date d = null;  
	     try {  
	         d = format.parse(str);  
	     } catch (Exception e) {  
	         e.printStackTrace();  
	     }  
	     new java.sql.Time(d.getTime());  
	     return Time.valueOf(str);  
	 }  
}
