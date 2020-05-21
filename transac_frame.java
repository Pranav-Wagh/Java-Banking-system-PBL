import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JSeparator;

public class transac_frame extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					transac_frame frame = new transac_frame();
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
	public transac_frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 643, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel Mainpanel = new JPanel();
		Mainpanel.setForeground(SystemColor.text);
		Mainpanel.setBackground(SystemColor.text);
		Mainpanel.setBounds(0, 0, 627, 397);
		contentPane.add(Mainpanel);
		Mainpanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME!!"); 
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(180, 21, 217, 50);
		Mainpanel.add(lblNewLabel);
		
		withd w = new withd();
		depositt d1 = new depositt();
		System.out.println("...2...."+w.amount);					//to check
		
		JButton btnNewButton = new JButton("DEPOSIT\r\n");
		
		Loginfv2 obj1 = new Loginfv2();
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				d1.setVisible(true);
				obj1.flag123 = 1;
				System.out.println("............2............"+d1.amount);		//to check
			}
		});
		btnNewButton.setBounds(33, 320, 155, 43);
		Mainpanel.add(btnNewButton);
		
		JButton btnWithdraw = new JButton("WITHDRAW");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				w.setVisible(true);
				obj1.flag123 = 2;	
			}
		});
		btnWithdraw.setBounds(225, 320, 155, 43);
		Mainpanel.add(btnWithdraw);
		
		JButton btnCheckBalance = new JButton("CHECK BALANCE");
		btnCheckBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String balance;
			balance = obj1.bal;
			JOptionPane.showMessageDialog(null,"Your current balance is:"+balance);
			}
		});
		btnCheckBalance.setBounds(408, 320, 155, 43);
		Mainpanel.add(btnCheckBalance);
		
		JButton btnNewButton_1 = new JButton("SIGN OUT");  
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
	
				obj1.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(478, 11, 110, 43);
		Mainpanel.add(btnNewButton_1);
		
		
		//-------------------Added Acc holder,acc no and customer name--------
		
		JLabel lblNewLabel_1 = new JLabel("ACCOUNT HOLDER :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(69, 95, 155, 25);
		Mainpanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ACCOUNT NO. :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(69, 168, 155, 25);
		Mainpanel.add(lblNewLabel_2);
		
		JLabel lblCustomerName = new JLabel("CUSTOMER NAME :");
		lblCustomerName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCustomerName.setBounds(69, 246, 155, 25);
		Mainpanel.add(lblCustomerName);
		
		//----------------jlabels added with no text for accessing db with variables full_nm,ac_no,cust_nm-----------------
		
		String full_nm,cust_nm,acc_no;
		full_nm = obj1.full_nm;
		acc_no = obj1.acc_no;
		cust_nm = obj1.cust_nm;
		
		JLabel Jlabel1 = new JLabel("");
		Jlabel1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Jlabel1.setBounds(301, 95, 229, 25);
		Mainpanel.add(Jlabel1);
	
		Jlabel1.setText(full_nm);
		
		JLabel Jlabel2 = new JLabel("");
		Jlabel2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Jlabel2.setBounds(301, 168, 229, 25);
		Mainpanel.add(Jlabel2);
		Jlabel2.setText(acc_no);
		
		JLabel Jlabel3 = new JLabel("");
		Jlabel3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Jlabel3.setBounds(301, 246, 229, 25);
		Mainpanel.add(Jlabel3);
		Jlabel3.setText(cust_nm);
		
		//----------------the lines below the empty labels------------
		
		JSeparator separator = new JSeparator();
		separator.setBounds(301, 124, 229, 9);
		Mainpanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(301, 198, 229, 9);
		Mainpanel.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(301, 275, 229, 9);
		Mainpanel.add(separator_2);
		
		
	}
}
