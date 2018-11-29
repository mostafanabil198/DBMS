package eg.edu.alexu.csd.oop.db.cs51.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.factory.IOuterFactory;

public class DBMSGui extends JFrame {

	private JPanel contentPane;
	private JTextField query;
	private Database dbms;
	private IOuterFactory outerFactory;

	public void initialize(Database dbms, IOuterFactory outerFactory) {
		this.dbms = dbms;
		this.outerFactory = outerFactory;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBMSGui frame = new DBMSGui();
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
	public DBMSGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 977, 752);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JTextArea results = new JTextArea();
		results.setBounds(187, 161, 749, 515);
		contentPane.add(results);
		query = new JTextField();
		query.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						Object result = outerFactory.getOutput(query.getText(), dbms);
						query.setText("");
						results.setText(results.getText() + "\n" + result);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						results.setText(results.getText() + "\n" + "Wrong Query! Syntax error");
					}
				}
			}
		});
		query.setBounds(187, 44, 749, 47);
		contentPane.add(query);
		query.setColumns(10);
		
		JLabel lblEnterQuery = new JLabel("Enter Query:");
		lblEnterQuery.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		lblEnterQuery.setBounds(53, 51, 119, 30);
		contentPane.add(lblEnterQuery);
		

		
		JLabel lblResults = new JLabel("Results:");
		lblResults.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		lblResults.setBounds(53, 425, 119, 30);
		contentPane.add(lblResults);
	}
}
