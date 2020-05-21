
// Java implementation of Bank Server side 
// Concurrent Server execution to enable multiple clients at a time 
  
import java.io.*; 
import java.text.*; 
import java.util.*; 
import java.net.*; 
import java.sql.*;
public class JavaMySQLv2 
{ 
    public static void main(String[] args) throws IOException  
    { 
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		try{
			String userName_db = "root";  //When you install MySQL that username.
			String password_db = "Pranav@2000";  //When you install MySQL password provided at that time.
			String url_db = "jdbc:mysql://localhost:3306/pbl";  //here mysql is name of jdbc driver...PBL is name of database already created.
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url_db, userName_db, password_db);
			stmt = conn.createStatement();
			System.out.println("\nDataBase Connection Established...Server Connected to Database\n");
        // server is listening on port 5056 
        ServerSocket ss = new ServerSocket(5056); 
		System.out.println("\nBank Server started...Waiting for Customers...\n");
        // running infinite loop for getting 
        // client request 
        while (true)  
        { 
            Socket s = null; 
              
            try 
            { 
                // socket object to receive incoming client requests 
                s = ss.accept(); 
                System.out.println("A new Customer is connected : " + s); 
                  
                // obtaining input and out streams 
                DataInputStream dis = new DataInputStream(s.getInputStream()); 
                DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
                  
                System.out.println("Assigning new thread for this Customer\n"); 
  
                // create a new thread object 
                Thread t = new ClientHandler(s, dis, dos, conn, stmt, rs, pstmt, rs1, pstmt1); 
  
                // Invoking the start() method 
                t.start(); 
                  
            } 
            catch (Exception e){ 
                s.close(); 
                e.printStackTrace(); 
            } 
        }
		}
			catch(Exception e)
			{
				System.out.println("Cannot connect to data base:"+e);
			}
			finally{
			if(conn != null)
			{
				try{
					conn.close();
					System.out.println("DataBAse connection closed!!");
					
				}
				catch(Exception e){}
			}
		}	
    }
} 
  
// ClientHandler class 
class ClientHandler extends Thread   
{ 
    final DataInputStream dis; 
    final DataOutputStream dos; 
    final Socket s;
	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement pstmt;
	ResultSet rs1;
	PreparedStatement pstmt1;
	final int key=17;
  
    // Constructor 
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos,Connection conn,Statement stmt,ResultSet rs,PreparedStatement pstmt, ResultSet rs1, PreparedStatement pstmt1)  
    { 
        this.s = s; 
        this.dis = dis; 
        this.dos = dos; 
		this.conn = conn;
		this.stmt = stmt;
		this.rs = rs;
		this.pstmt = pstmt;
		this.rs1 = rs1;
		this.pstmt1 = pstmt1;
    } 
  
    @Override
    public void run()  
    { 
        String username; 
		String password;
        String toreturn;  
         
            try { 
                // receive username and password from client 
                username = dis.readUTF(); 
                password = dis.readUTF();
				 
				//encrypting password
				password = encrypt(password,key);
			
				 String query = "update bank_db set bal=? where cust_nm=?";  //Query to update deposit and withdraw amount
				pstmt = conn.prepareStatement(query);
				
				String query1 = "select bal from bank_db where cust_nm=?";	//Query to fetch updated deposit and withdraw amount
				pstmt1 = conn.prepareStatement(query1);
				
				String cust_nm,pass,login,full_nm,acc_no,bal;
				int flag = 0; 				
				stmt.execute("select * from bank_db");						//firing the query
				rs = stmt.getResultSet();
				while(rs.next())
				{
					cust_nm = rs.getString("cust_nm");
					pass = rs.getString("pass");
					full_nm = rs.getString("full_nm");
					acc_no = rs.getString("acc_no");
					bal = rs.getString("bal");
					if(cust_nm.equals(username))
					{
						flag=1;
						if(pass.equals(password))						//checking for correct credentials
						{
							login="YES";
							dos.writeUTF(login);
							dos.writeUTF(cust_nm);
							dos.writeUTF(full_nm);
							dos.writeUTF(acc_no);
							dos.writeUTF(bal);
							
							String flag123,amount;
							flag123 = dis.readUTF();
							amount = dis.readUTF();
							System.out.println("Amount received!!...."+flag123);	//to check
							
							int amount1 = Integer.parseInt(amount);
							int bal1 = Integer.parseInt(bal);
							if(flag123.equals("1"))
							{
								amount1 = bal1 + amount1;
								pstmt.setInt(1,amount1);
								pstmt.setString(2,cust_nm);
								pstmt.executeUpdate();
								System.out.println("Query fired!!");			//to check

								pstmt1.setString(1,cust_nm);
								rs1 = pstmt1.executeQuery();					//fetch updated balance
								while(rs1.next())
								{
									bal = rs1.getString("bal");
								}
								
								dos.writeUTF(bal);
							}
							else if (flag123.equals("2"))
							{
								amount1 = bal1 - amount1;
								pstmt.setInt(1,amount1);
								pstmt.setString(2,cust_nm);

								pstmt.executeUpdate();
								System.out.println("Query fired!!");			//to check
								pstmt1.setString(1,cust_nm);
								rs1 = pstmt1.executeQuery();					//fetch updated balance
								while(rs1.next())
								{
									bal = rs1.getString("bal");
								}
								dos.writeUTF(bal);
							}
						}
						else
						{
							login = "NO";
							dos.writeUTF(login);					//password is incorrect
						}
					}
				}
				if(flag==0)
				{
					login = "NO1";
					dos.writeUTF(login);							//username and password are incorrect
				}
 
            } catch (IOException e) { 
                e.printStackTrace(); 
            }
			catch(SQLException ex)
			{
				System.out.println("SQLException : "+ex.getMessage());
				System.out.println("SQLState : "+ex.getSQLState());
				System.out.println("Vendor error : "+ex.getErrorCode());
			}  
        try
        { 
            // closing resources 
            this.dis.close(); 
            this.dos.close(); 
              
        }catch(IOException e){ 
            e.printStackTrace(); 
        }		
    }
	
	public String encrypt(String input, int key) {										//Ecryption code..Encrypted password stored in DataBAse
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabet = alphabet.substring(key)+alphabet.substring(0,key);
        
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            if(idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }

}