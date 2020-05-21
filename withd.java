import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class withd extends JFrame {
	static String amount,acc_no,cust_nm,tamt,full_nm;
	Loginfv2 L1 = new Loginfv2();

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					withd frame = new withd();
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
	public withd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 700, 550);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCheckBalance = new JLabel("WITHDRAW DETAILS");
		lblCheckBalance.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCheckBalance.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckBalance.setBounds(251, 48, 199, 48);
		contentPane.add(lblCheckBalance);
		
		JLabel lblTransansactionid = new JLabel("ACCOUNT HOLDER :");
		lblTransansactionid.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTransansactionid.setBounds(111, 134, 199, 27);
		contentPane.add(lblTransansactionid);
		
		JLabel lblTransactionName = new JLabel("ACCOUNT NO. :");
		lblTransactionName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTransactionName.setBounds(111, 184, 199, 27);
		contentPane.add(lblTransactionName);
		
		JLabel lblTransactionAmount = new JLabel("ENTER WITHDRAW AMOUNT :");
		lblTransactionAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTransactionAmount.setBounds(53, 337, 261, 27);
		contentPane.add(lblTransactionAmount);
		
		JTextField amt = new JTextField();
		amt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		amt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 tamt = amt.getText();	
			}
		});
		amt.setBorder(null);
		amt.setColumns(10);
		amt.setBounds(324, 338, 288, 27);
		contentPane.add(amt);
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				//transac_frame t1= new transac_frame();     	//Comment this line
				//t1.setVisible(true);							//Comment this line
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
			
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(23, 11, 89, 41);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 473, 684, 27);
		contentPane.add(panel);
		
		JButton btnNewButton_1 = new JButton("WITHDRAW\r\n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tamt = amt.getText();
				amount=tamt;
				L1.flag123 = 2;
				JOptionPane.showMessageDialog(null,"Withdraw Successful");
				dispose();
				transac_frame t2= new transac_frame();		//Comment this line
				t2.setVisible(true);							//Comment this line

				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(273, 410, 150, 41);
		contentPane.add(btnNewButton_1);
		
		JLabel lblCustomerName = new JLabel("CUSTOMER NAME :");
		lblCustomerName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCustomerName.setBounds(111, 240, 199, 27);
		contentPane.add(lblCustomerName);
		
		full_nm = L1.full_nm;
		acc_no = L1.acc_no;
		cust_nm = L1.cust_nm;
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(359, 134, 254, 27);
		contentPane.add(label);
		label.setText(full_nm);
		
		JLabel label_1 = new JLabel("");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setBounds(359, 184, 254, 27);
		contentPane.add(label_1);
		label_1.setText(acc_no);
		
		JLabel label_2 = new JLabel("");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_2.setBounds(359, 240, 254, 27);
		contentPane.add(label_2);
		label_2.setText(cust_nm);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(359, 167, 254, 12);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(359, 212, 254, 12);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(359, 269, 254, 12);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(324, 371, 288, 8);
		contentPane.add(separator_3);
	}
}