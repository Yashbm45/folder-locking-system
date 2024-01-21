import javax.swing.*;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

//===================================================================================================================================================================

class Option extends JFrame implements ActionListener
{
	static JButton register=new JButton(new ImageIcon(Login.Location+"\\Register.jpg"));
	static JButton verify=new JButton(new ImageIcon(Login.Location+"\\verify.jpg"));
	int width=550,height=165;
	JPanel panel=new JPanel();
	static Option frame=null;
	static String mail="";
	public void actionPerformed(ActionEvent e){}

	Option()
	{
		setSize(width,height);
		setTitle("Select Platform");
		setResizable(false);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((d.width-width)/2,(d.height-height)/2);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.Location+"\\stop1.jpg"));
		register.setOpaque(true);
		register.setToolTipText("Click here for Register");
		verify.setToolTipText("Click here for Varify");

		panel.add(register,"East");


		panel.add(verify,"West");
		add(panel);
		setVisible(true);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(3);

	}

//===================================================================================================================================================================

	public static void method()
	{

		frame = new Option();

		register.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				try
				{
					System.out.println("In Register");
					
					mail=JOptionPane.showInputDialog(null,"Enter Email-Id for Key send ","Email-ID",JOptionPane.QUESTION_MESSAGE);   
				    
					if (mail.equals(""))
					{
						JOptionPane.showMessageDialog (frame, "Please! provide valid mail id.","Locking System - EmptyField", JOptionPane.PLAIN_MESSAGE);
					}
					else
					{
						Random t = new Random();
					    String OTP = t.nextInt(9999) +"";
					    if(OTP.length()==1)
					    	OTP="000"+OTP;
					    if(OTP.length()==2)
					    	OTP="00"+OTP;
					    if(OTP.length()==3)
					    	OTP="0"+OTP;
					    
					     DAO db=new DAO();
						   
						 Connection conn=db.getConnection();
						   
						 String insertQuery = "insert into registration values(?,?)";
						   
						 PreparedStatement preparedStatement;
							
						 preparedStatement = conn.prepareStatement(insertQuery);
						   
						 preparedStatement.setString(1,mail);
						   
						 preparedStatement.setString(2,OTP);
						   
						 preparedStatement.executeUpdate();
						   
						 System.out.println("Data  Successfully Inserted !!!");
						  		   
				  	  	 conn.close();					   
					    
					     EmailSender.mailsent(mail, OTP);
					     
						 JOptionPane.showMessageDialog(null,"Email Send DataBase Updated !!!");					  	  
					}
				}
				catch (SQLException e1) 
				{
					JOptionPane.showMessageDialog(frame,"Duplicate Email Id","Already Register User !!",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				catch(Exception ace)
				{
					System.out.println(ace);
					JOptionPane.showMessageDialog(frame,"Invalid Email Id","Wrong Email !!",JOptionPane.ERROR_MESSAGE);
					
					DAO db=new DAO();
					Connection conn=db.getConnection();
							
				    String deleteQuery = "delete from registration where email=?";

				    PreparedStatement preparedStatement;
					
					try 
					{
						preparedStatement = conn.prepareStatement(deleteQuery);
						
						preparedStatement.setString(1,mail);
						
						preparedStatement.executeUpdate();
						
						conn.close();
						
					} 
					catch (SQLException e1) 
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			    } 
			    Option.method();
		    }
		});


		verify.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				try
				{
					System.out.println("In Verify");
					
					Authentication ob=new Authentication();
					ob.verifier();
				}
				catch(Exception ace){}

			}
		});


		 frame.addWindowListener(new WindowAdapter()
		 {
		   public void windowClosing(WindowEvent e)
		   {
    	     try
   	         {
			  System.exit(0);
             }
	         catch(Exception e1){}

             System.exit(0);
		   }
		 });
	}
}

//==================================================================================================================================================================
