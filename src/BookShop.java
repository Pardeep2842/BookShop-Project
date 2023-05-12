import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BookShop {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTable table_1;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookShop window = new BookShop();
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
	public BookShop() {
		initialize();
		Connect();
		table_load();
	}
	
	
	Connection con;
	PreparedStatement Pst;
	ResultSet rs;

	
	public void Connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/bookshop", "root","");
        }
        catch (ClassNotFoundException ex)
        {
          ex.printStackTrace();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
 
    }
	
	
	  public void table_load()
	    {
	     try
	     {
	    Pst = con.prepareStatement("select * from book");
	    rs = Pst.executeQuery();
	    table_1.setModel(DbUtils.resultSetToTableModel(rs));
	}
	     catch (SQLException e)
	     {
	     e.printStackTrace();
	  }
	    }


	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 719, 449);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BOOK SHOP");
		lblNewLabel.setBounds(248, 11, 190, 37);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 59, 327, 225);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(20, 25, 89, 23);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(20, 59, 89, 23);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price\r\n");
		lblNewLabel_1_1_1.setBackground(new Color(240, 240, 240));
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(20, 93, 89, 22);
		panel.add(lblNewLabel_1_1_1);
		
		txtbname = new JTextField();
		txtbname.setText("");
		txtbname.setBounds(119, 28, 166, 20);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setText("");
		txtedition.setColumns(10);
		txtedition.setBounds(119, 62, 166, 20);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setText("");
		txtprice.setColumns(10);
		txtprice.setBounds(119, 96, 166, 20);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String bname,edition,price;
				
				bname=txtbname.getText();
				edition=txtedition.getText();
				price=txtprice.getText();
				
				try {
					Pst = con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
					Pst.setString(1, bname);
					Pst.setString(2, edition);
					Pst.setString(3, price);
					Pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
					table_load();
					          
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
					   }
					 
					catch (SQLException e1) {
					e1.printStackTrace();
					}

				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(20, 155, 71, 31);
		panel.add(btnNewButton);
		
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnExit.setBounds(114, 155, 68, 31);
		panel.add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				txtbname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtbname.requestFocus();
				
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnClear.setBounds(214, 155, 68, 31);
		panel.add(btnClear);
		
		table = new JTable();
		table.setBounds(421, 164, 161, -89);
		frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("table");
		scrollPane.setBounds(361, 66, 319, 218);
		frame.getContentPane().add(scrollPane);
		
		JScrollPane table_2 = new JScrollPane();
		scrollPane.setViewportView(table_2);
		
		table_1 = new JTable();
		table_2.setViewportView(table_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(20, 303, 317, 84);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(15, 24, 89, 23);
		panel_1.add(scrollPane_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Book ID");
		scrollPane_1.setViewportView(lblNewLabel_1_1_2);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		
		
				
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
					try {
					          
					            String id = txtbid.getText();
					 
					                Pst = con.prepareStatement("select name,edition,price from book where id = ?");
					                Pst.setString(1, id);
					                ResultSet rs = Pst.executeQuery();
					 
					            if(rs.next()==true)
					            {
					              
					                String name = rs.getString(1);
					                String edition = rs.getString(2);
					                String price = rs.getString(3);
					                
					                txtbname.setText(name);
					                txtedition.setText(edition);
					                txtprice.setText(price);
					                
					                
					            }  
					            else
					            {
					             txtbname.setText("");
					             txtedition.setText("");
					                txtprice.setText("");
					                
					            }
					            
					 
					 
					        }
					catch (SQLException ex) {
					          
					        }
					}

		});
		txtbid.setText("");
		txtbid.setColumns(10);
		txtbid.setBounds(114, 27, 166, 20);
		panel_1.add(txtbid);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
	      String bname,edition,price,bid;
				
				bname=txtbname.getText();
				edition=txtedition.getText();
				price=txtprice.getText();
				bid = txtbid.getText();
				
				try {
					Pst = con.prepareStatement("update book set name= ?,edition= ?,price= ? where id =?");
					Pst.setString(1, bname);
					Pst.setString(2, edition);
					Pst.setString(3, price);
					Pst.setString(4, bid);

					Pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated!!!!!");
					table_load();
					          
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
					   }
					 
					catch (SQLException e1) {
					e1.printStackTrace();
					}

				
				
				
			}

		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdate.setBounds(422, 318, 84, 31);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnExit_1_1 = new JButton("Delete");
		btnExit_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				   String bid;
				   bid  = txtbid.getText();
				   try {
				   Pst = con.prepareStatement("delete from book where id =?");
				               Pst.setString(1, bid);
				               Pst.executeUpdate();
				               JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
				               table_load();
				             
				               txtbname.setText("");
				               txtedition.setText("");
				               txtprice.setText("");
				               txtbname.requestFocus();
				   }
				    
				               catch (SQLException e1) {
				   e1.printStackTrace();
				   }

				
				
			}
		});
		btnExit_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnExit_1_1.setBounds(553, 318, 91, 31);
		frame.getContentPane().add(btnExit_1_1);
	}
}
