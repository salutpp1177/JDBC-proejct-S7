package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.*;

@SuppressWarnings("serial")
public class CreateUserView extends JFrame {
	private JPanel panel;
	private UserControler userctrl;
	private Controller ctrl;

	public CreateUserView() {
		// super();
		this.setTitle("UserSignIn");
		this.setSize(400, 450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		this.add(panel);
		
		this.userctrl = new UserControler();
		this.ctrl = new Controller();

		panel.setLayout(null);

		// nom label
		JLabel nomLabel = new JLabel("Nom :");
		nomLabel.setBounds(60, 50, 140, 25);
		nomLabel.setForeground(Color.blue);
		panel.add(nomLabel);
		// input nom
		JTextField nomText = new JTextField(32);
		nomText.setBounds(200, 50, 165, 25);
		panel.add(nomText);

		// prenom label
		JLabel prenomLabel = new JLabel("Prenom :");
		prenomLabel.setBounds(60, 80, 140, 25);
		prenomLabel.setForeground(Color.blue);
		panel.add(prenomLabel);
		// input prenom
		JTextField prenomText = new JTextField(32);
		prenomText.setBounds(200, 80, 165, 25);
		panel.add(prenomText);
		
		// nomutilisateur label
		JLabel nduLabel = new JLabel("Nom de utilisateur :");
		nduLabel.setBounds(60, 110, 140, 25);
		nduLabel.setForeground(Color.blue);
		panel.add(nduLabel);
		// input nomutilisateur
		JTextField nduText = new JTextField(32);
		nduText.setBounds(200, 110, 165, 25);
		panel.add(nduText);
		

		// motdepasse label
		JLabel mdpLabel = new JLabel("Mot de passe :");
		mdpLabel.setBounds(60, 140, 140, 25);
		mdpLabel.setForeground(Color.blue);
		panel.add(mdpLabel);
		// input motdepasse
		JTextField mdpText = new JTextField(32);
		mdpText.setBounds(200, 140, 165, 25);
		panel.add(mdpText);
		
		// signin button
		JButton signinButton = new JButton("INSCRIRE");
		signinButton.setBounds(200, 170, 140, 25);
		panel.add(signinButton);
		signinButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String nom = nomText.getText();
				userctrl.getModelUser().setNom(nom);
				
				String pm = prenomText.getText();
				userctrl.getModelUser().setPrenom(pm);
				
				String ndu = nduText.getText();
				userctrl.getModelUser().setUsername(ndu);
				
				String mdp = mdpText.getText();
				userctrl.getModelUser().setMotdepasse(mdp);
				
				userctrl.signIn(nom, pm, ndu, mdp);
				dispose();
				ctrl.updateView(null,ndu);
			}
		});

		this.setVisible(true);
	}

}
