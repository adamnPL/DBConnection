import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class App {

	private JFrame frame;
	String connectionString = "jdbc:mysql://localhost:3306/test";
	String user = "test";
	String password = "test";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnConnect = new JButton("Get connection");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = DBConnection.getConnection(connectionString, user, password);
				if (connection == null) {
					JOptionPane.showMessageDialog(null, "B³¹d po³¹czenia z baz¹ danych", "B³¹d sterownika",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					JOptionPane.showMessageDialog(null, "Uda³o siê poprawnie nawi¹zaæ po³¹czenie z baz¹ danych",
							"Po³¹czenie aktywne", JOptionPane.INFORMATION_MESSAGE);
				}
				try {
					connection.close();
					JOptionPane.showMessageDialog(null, "Uda³o siê poprawnie zamkn¹æ po³¹czenie z baz¹ danych",
							"Po³¹czenie zakoñczone", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "B³¹d zamkniêcia po³¹czenia z baz¹ danych",
							"B³¹d zamykania po³¹czenia", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnConnect.setBounds(10, 11, 422, 49);
		frame.getContentPane().add(btnConnect);
	}
}
