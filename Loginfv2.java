import java.io.*; 
import java.net.*; 
import java.util.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Loginfv2 extends JFrame {

	private JPanel contentPane;
	private JTextField user;
	private JPasswordField pass;
	static String cust_nm,full_nm,acc_no,bal;
	static int flag123=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loginfv2 frame = new Loginfv2();
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
	public Loginfv2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setForeground(SystemColor.controlLtHighlight);
		panel.setBounds(0, 0, 716, 419);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(242, 0, 474, 419);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(26, 86, 130, 45);
		panel_1.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPassword.setBounds(26, 180, 130, 45);
		panel_1.add(lblPassword);
		
		user = new JTextField();
		user.setBounds(166, 102, 263, 20);
		panel_1.add(user);
		user.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBounds(166, 196, 263, 20);
		panel_1.add(pass);
		
		JButton LoginButton = new JButton("LOGIN");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String un = user.getText();
				String pwd= pass.getText();
				try{
				// getting localhost ip 
				InetAddress ip = InetAddress.getByName("localhost"); 
				// establish the connection with server port 5056 
				Socket s = new Socket(ip, 5056); 
				// obtaining input and out streams 
				DataInputStream dis = new DataInputStream(s.getInputStream()); 
				DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
			
				dos.writeUTF(un);
				dos.writeUTF(pwd);
				String rcvd = dis.readUTF();
				
				if(rcvd.equals("YES"))
				{
					cust_nm = dis.readUTF();
					full_nm = dis.readUTF();
					acc_no = dis.readUTF();
					bal = dis.readUTF();
					JOptionPane.showMessageDialog(null,"Login Successful !!");
					dispose();
					transac_frame t1 = new transac_frame();
					t1.setVisible(true);
					
					if(flag123==1)
					{
						depositt d1 = new depositt();
						String d_amt = d1.amount;
						System.out.println("...........3........."+d1.amount);			//to check
						dos.writeUTF("1");
						dos.writeUTF(d_amt);
					}
					else if(flag123==2)
					{
						withd w1 = new withd();
						String w_amt = w1.amount;
						System.out.println("...........3........."+w1.amount);			//to check
						dos.writeUTF("2");
						dos.writeUTF(w_amt);
					}
					else
					{
						dos.writeUTF("0");								//for check balance
						dos.writeUTF("0");
					}
					bal = dis.readUTF();
					System.out.println("bal updated !!"+bal);			//to check
					
				}
				else if(rcvd.equals("NO"))
				{
					JOptionPane.showMessageDialog(null,"Invalid Password !!");
				}
				else{
					JOptionPane.showMessageDialog(null,"Sorry, Customer not found !!");
				}
			}
			catch(Exception e1){
			e1.printStackTrace(); 
			}
			
			}
		});
		LoginButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LoginButton.setBounds(157, 279, 114, 45);
		panel_1.add(LoginButton);
		

		
		JLabel lblNewLabel_1 = new JLabel("Welcome To");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1.setBounds(10, 160, 222, 40);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("VishwaBANK");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_2.setBounds(10, 198, 222, 40);
		panel.add(lblNewLabel_2);
	}
}
