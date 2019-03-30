package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import controller.Controller;

public class SalleReservationView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controller ctrl;
	private String oldvalue = null;

	// label of the window
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void SalleReservation(HashMap<String, List<String>> map,
			HashMap<String, List<String>> map2, Date date, String username) {
		ctrl = new Controller();
		this.setTitle("SalleReservation");
		// size of the window
		this.setSize(1100, 450);
		this.setVisible(true);

		Container contentPane = this.getContentPane();

		JPanel myPane = new JPanel();

		JButton jb1 = new JButton("Previous Day     ");
		JButton jb2 = new JButton("Next day    ");

		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.PreviousDay(date, username);
				dispose();
			}
		});

		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.NextDay(date, username);
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

		// myPane.setLayout(new FlowLayout());
		JPanel pane1 = new JPanel();
		pane1.setLayout(new BorderLayout());

		// -----------------------table---------------------
		Vector vData = new Vector();
		Vector vData2 = new Vector();
		Vector vName = new Vector();
		vName.add("Reservation(Confirmez par Entre)");
		vName.add("8:00-10:00");
		vName.add("10:00-12:00 ");
		vName.add("14:00-16:00");
		vName.add("16:00-18:00");

		// -------------------------hashmap-----------------------------
		for (HashMap.Entry<String, List<String>> entry : map.entrySet()) {
			String key = entry.getKey();
			List<String> values = entry.getValue();
			Vector vRow = new Vector();
			vRow.add(key);

			for (String e : values) {

				vRow.add(e);
			}
			vData.add(vRow.clone());
		}

		DefaultTableModel model = new DefaultTableModel(vData, vName) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 2 || column == 3 || column == 4 || column == 1) {

					return true;
				} else {
					return false;
				}
			}
		};

		JTable table = new JTable();
		JTable table2 = new JTable();

		table.setModel(model);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (table.getValueAt(table.getSelectedRow(),
						table.getSelectedColumn()) != null) {
					oldvalue = table.getValueAt(table.getSelectedRow(),
							table.getSelectedColumn()).toString();
				}
			}
		});
		model.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent event) {
				if (event.getType() == TableModelEvent.UPDATE) {
					String newvalue = model.getValueAt(event.getLastRow(),
							event.getColumn()).toString();
					if ((!newvalue.equals(oldvalue))
							&& (!oldvalue.equals("Occupe"))) {

						String salle = model.getValueAt(event.getLastRow(), 0)
								.toString();

						Time temp = null;
						String status = "Occupe";
						switch (event.getColumn()) {
						case 1:
							temp = strToTime("8:00:00");
							break;
						case 2:
							temp = strToTime("10:00:00");
							break;
						case 3:
							temp = strToTime("14:00:00");
							break;
						case 4:
							temp = strToTime("16:00:00");
							break;
						}

						ctrl.Reserver(salle, temp, date, status, username);
					}
				}
			}
		});

		for (HashMap.Entry<String, List<String>> entry : map2.entrySet()) {
			String key = entry.getKey();
			List<String> values = entry.getValue();
			Vector vRow = new Vector();
			vRow.add(key);

			for (String e : values) {

				vRow.add(e);
			}
			vData2.add(vRow.clone());
		}
		// vName.get(0) = "Annuler";
		vName.remove(0);
		vName.add(0, "Annuler");
		DefaultTableModel model2 = new DefaultTableModel(vData2, vName);
		table2.setModel(model2);

		// ---------------------------------------------------------------//
		table2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (table2.getValueAt(table2.getSelectedRow(),
						table2.getSelectedColumn()) != null) {
					oldvalue = table2.getValueAt(table2.getSelectedRow(),
							table2.getSelectedColumn()).toString();
				}
			}
		});
		model2.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent event) {
				if (event.getType() == TableModelEvent.UPDATE) {
					String newvalue = model2.getValueAt(event.getLastRow(),
							event.getColumn()).toString();
					if ((oldvalue != null)
							&& (newvalue.equals("") && (oldvalue
									.equals(username)))) {

						String salle = model2.getValueAt(event.getLastRow(), 0)
								.toString();

						Time temp = null;
						String status = "Libre";
						switch (event.getColumn()) {
						case 1:
							temp = strToTime("8:00:00");
							break;
						case 2:
							temp = strToTime("10:00:00");
							break;
						case 3:
							temp = strToTime("14:00:00");
							break;
						case 4:
							temp = strToTime("16:00:00");
							break;
						}

						ctrl.Annuler(salle, temp, date, status, username);
					}
				}
			}
		});
		// ---------------------------------------------------------//
		table.setPreferredScrollableViewportSize(new Dimension(550, 100));

		table2.setPreferredScrollableViewportSize(new Dimension(550, 100));

		JScrollPane scrollPane = new JScrollPane(table);
		JScrollPane scrollPane2 = new JScrollPane(table2);

		pane1.add(scrollPane, BorderLayout.NORTH);
		pane1.add(scrollPane2, BorderLayout.CENTER);

		JButton save = new JButton("Save");
		save.setBounds(130, 180, 250, 25);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.updateView(date, username);
				dispose();
			}
		});
		pane1.add(save, BorderLayout.SOUTH);
		contentPane.add(myPane, BorderLayout.NORTH);
		contentPane.add(pane1, BorderLayout.CENTER);

		// ------------------------Menu-------------------------
		JMenuBar barMenu = new JMenuBar();

		JMenu menuUtilisateur = new JMenu("Utilisateur");

		JMenuItem compte = new JMenuItem("Compte");
		JMenuItem logout = new JMenuItem("LogOut");

		barMenu.add(menuUtilisateur);

		menuUtilisateur.addSeparator();
		menuUtilisateur.add(compte);
		menuUtilisateur.add(logout);
		compte.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.toIdentityView(username);
				dispose();
			}
		});
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.toLoginUser();
				dispose();
			}
		});

		this.setJMenuBar(barMenu);
		table.setRowSelectionAllowed(false);
		table2.setRowSelectionAllowed(false);
		setLocationRelativeTo(null);//
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
