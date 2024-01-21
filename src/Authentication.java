import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Authentication extends JFrame
{
	static JFrame frame = new JFrame("Voter Verification");
	
	static String otpkey="";
   
	public void verifier()
	   {
		try
		{
		  JPanel panel = new JPanel();

		  JLabel lblMail = new JLabel("Email Id:- ");          // create label
		 
		  JLabel lblPIN = new JLabel("Account OTP:- ");          // for password

		  final JTextField txtEmail = new JTextField(15);  // create TextArea
		  
		  txtEmail.setText("amolpatil37@yahoo.com");
		  
		  final JPasswordField txtPin = new JPasswordField(15);  // for password entry
		  txtPin.setText("0415");
		  
		  JButton btnOk = new JButton("Verify");
		  
		  JButton btnCancel = new JButton("Cancel");

		  GridBagLayout gbl= new GridBagLayout();

		  GridBagConstraints gbc= new GridBagConstraints();

		  panel.setLayout(gbl);

		  gbc.weighty= 3;     // divide vertical space into 4 parts

		  gbc.gridx= 0;       // locate first part
		  gbl.setConstraints(lblMail, gbc);         // old password
		  panel.add(lblMail);                       // label

		  gbl.setConstraints(lblPIN, gbc);  // New password
		  panel.add(lblPIN);                // label

		  gbl.setConstraints(btnOk, gbc);  // Change button
		  panel.add(btnOk);

		  gbc.gridx= 1;       //locate second part
		  gbl.setConstraints(txtEmail, gbc);   // old password textArea
		  panel.add(txtEmail);

		  gbl.setConstraints(txtPin, gbc);   // New password textArea
		  panel.add(txtPin);

		  gbl.setConstraints(btnCancel, gbc);  // cancel button
		  panel.add(btnCancel);

		  getContentPane().add(panel);

	      frame.add(panel);

		  frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		  frame.setResizable(false);
		  frame.setSize(310,200);

		  Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		  frame.setLocation((d.width-310)/2,(d.height-200)/2);

		  frame.setVisible(true);
		  this.setVisible(false);  // hide first window

		  btnOk.addActionListener(new ActionListener()
		  {
		   public void actionPerformed(ActionEvent event)
		   {
			   if (txtEmail.getText().equals(""))
				{
					JOptionPane.showMessageDialog (frame, "Please! Provide Voter number.","Voting System - EmptyField", JOptionPane.PLAIN_MESSAGE);
				}
			   else if (txtPin.getText().equals("")) 
				{
					JOptionPane.showMessageDialog (frame, "Please! Provide Name of Voter.","Voting System - EmptyField", JOptionPane.PLAIN_MESSAGE);
				}
				else 
				{
				  String Mail = txtEmail.getText().trim();
				  
				  String otp = txtPin.getText().trim();
					
				  try
				  {					
					DAO db=new DAO();

					Connection conn=db.getConnection();
							
				    String selectQuery = "select * from  registration";
				    	    
				    PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);

				    ResultSet result = preparedStatement.executeQuery();
		
				    boolean flag=false;
				    
				    while (result.next())
			        {
				      if(result.getString(1).equals(Mail) && otp.equals(result.getString(2)))
				      {
				          flag=true;
				          otpkey=otp;
			          }
				     } 
				     if(flag==true)
				     {				    	 
						  frame.setVisible(false);
		
						  System.out.println("\nSuccessful");

						 // xcopy obj1=new xcopy();
						  //obj1.System_Start();
						  Project ob=new Project();
						  ob.method();
				     }
				     else
				     {
					   JOptionPane.showMessageDialog(frame,
							  "No Account Found Wrong Mail or OTP !!",
									  "No Record",0);	       	 
				     }
				     try 
				     {
						conn.close();
					} 
				     catch (SQLException e) 
				     {
						// TODO Auto-generated catch block
						e.printStackTrace();
					 }
					}
					catch(Exception e){}
			     }
			 }
          });
		  btnCancel.addActionListener(new ActionListener()
		  {
			 public void actionPerformed(ActionEvent event)
			 {
				frame.setVisible(false);              //hide password window
 			    new Login().setVisible(true); // disply first window
			 }
		   });
	  }
	   catch(Exception e){}
   }
}
