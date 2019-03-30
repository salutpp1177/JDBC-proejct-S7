package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Controller;
import controller.UserControler;

@SuppressWarnings("serial")
public class UserLoginView extends JFrame {

	private JPanel panel;
	private UserControler userctrl;
	private Controller ctrl;

	public UserLoginView() {
		// super();
		this.setTitle("UserLogin");
		this.setSize(400, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		this.add(panel);

		panel.setLayout(null);
		
		this.userctrl = new UserControler();
		this.ctrl = new Controller();


		// username title
		JLabel userLabel = new JLabel("Nom de Utilisateur:");
		userLabel.setBounds(60, 50, 140, 25);
		panel.add(userLabel);


		// input username
		JTextField userText = new JTextField();
		userText.setBounds(200, 50, 165, 25);
		panel.add(userText);

		// password title
		JLabel passwordLabel = new JLabel("Mot de Passe:");
		passwordLabel.setBounds(60, 80, 140, 25);
		panel.add(passwordLabel);

		// input password
		JPasswordField passwordText = new JPasswordField();
		passwordText.setBounds(200, 80, 165, 25);
		panel.add(passwordText);

		// input the information when login fails
		JLabel reminder = new JLabel("Veuillez revérifier votre compte et mot de passe!!");
		reminder.setBounds(50, 20, 350, 25);
		reminder.setForeground(Color.red);


		// login button

		JButton loginButton = new JButton("ME CONNECTER");
		loginButton.setBounds(180, 120, 200, 25);
		panel.add(loginButton);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					String n = userText.getText();
					userctrl.getModelUser().setUsername(userText.getText());
			
					char[] str = passwordText.getPassword();
					String pwd = String.valueOf(str);
					userctrl.getModelUser().setMotdepasse(pwd);				
				
					int testlogin = userctrl.login(n, pwd);

					if (testlogin == -1) {
						panel.add(reminder);
					} else {
						// go to next page
						dispose();
						ctrl.updateView(null, userText.getText());

					}

			}

		});
		

		// signin button
		JButton goCreate = new JButton("NOUVEL UTILISATEUR ?");
		goCreate.setBounds(180, 150, 200, 25);
		panel.add(goCreate);
		goCreate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.toCreateUser();
			}
		});

		// check timetable directly
		JButton goTimetable = new JButton("Check les horaires directement");
		goTimetable.setBounds(130, 180, 250, 25);
		panel.add(goTimetable);
		goTimetable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.updateView(null, null);
			}
		});

		this.setVisible(true);

	}

}
