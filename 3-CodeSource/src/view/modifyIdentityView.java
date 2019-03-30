package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.*;

public class modifyIdentityView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserControler userctrl;
	private Controller ctrl;

	public modifyIdentityView(String username) {
		userctrl = new UserControler();
		ctrl = new Controller();

		this.setTitle("ModifyIdentity");
		this.setSize(400, 450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container container = this.getContentPane();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

		JPanel panel1 = new JPanel();
		BoxLayout layout1 = new BoxLayout(panel1, BoxLayout.Y_AXIS);

		JPanel panel2 = new JPanel();
		BoxLayout layout2 = new BoxLayout(panel2, BoxLayout.Y_AXIS);

		// ------------------------menu-----------------------------
		JMenuBar barMenu = new JMenuBar();

		JMenu menuUtilisateur = new JMenu("Utilisateur");
		;
		JMenuItem logout = new JMenuItem("LogOut");

		barMenu.add(menuUtilisateur);
		menuUtilisateur.addSeparator();
		menuUtilisateur.add(logout);
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.toLoginUser();
				dispose();
			}
		});
		this.setJMenuBar(barMenu);

		panel1.setLayout(layout1);

		JLabel title1 = new JLabel("Modifier nom ou prenom");
		title1.setForeground(Color.blue);
		panel1.add(title1);

		JLabel nomLabel = new JLabel("Nom :");

		panel1.add(nomLabel);
		JTextField nomText = new JTextField();

		panel1.add(nomText);

		JLabel pmLabel = new JLabel("Prenom:");

		panel1.add(pmLabel);
		JTextField pmText = new JTextField();

		panel1.add(pmText);

		JButton button1 = new JButton("MODIFIER");

		panel1.add(button1);

		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String n = nomText.getText();

				String m = pmText.getText();

				userctrl.modify(username, n, m);

				dispose();
				ctrl.updateView(null, username);
			}

		});

		// --------------------------modifier motdepasse
		// ---------------------------
		panel2.setLayout(layout2);
		JLabel title2 = new JLabel("Modifier mot de passe");
		title2.setForeground(Color.blue);
		panel2.add(title2);
		JLabel mdpLabel = new JLabel("Nouveau Mot de Passe :");
		panel2.add(mdpLabel);
		JTextField mdpText = new JTextField();
		panel2.add(mdpText);

		JButton button2 = new JButton("MODIFIER");

		panel2.add(button2);

		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String p = mdpText.getText();

				userctrl.modifyPasswd(username, p);

				dispose();

				ctrl.updateView(null, username);
			}

		});

		container.add(panel1);
		container.add(panel2);
		this.setVisible(true);
	}
}