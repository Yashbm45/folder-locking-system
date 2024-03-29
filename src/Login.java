import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.net.*;
import javax.media.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.applet.*;

class MediaPlayer extends JPanel
{
	static Player mediaPlayer;

	public MediaPlayer(URL mediauUrl)
	{
	try
	{
		mediaPlayer=Manager.createRealizedPlayer(new MediaLocator(mediauUrl));

		Component video=mediaPlayer.getVisualComponent();

		Component control=mediaPlayer.getControlPanelComponent();

		add(video);

		mediaPlayer.start();
	}
	catch (Exception e) {System.out.println("e7-"+e);}
 }
}



public class Login extends JFrame implements ActionListener,Runnable
{
    static String path="D:\\finger.dat";
    
    static String Location="C:\\Users\\hp\\Desktop\\Folder_Loading_SQL_GUI\\image\\";

	private JPanel jpInfo = new JPanel();

	private JLabel Pinlbl;

	private JPasswordField PinVal;

	private JLabel l1,l3,l4,l5,l6,l7;

	private JProgressBar jprgbar;

	private JButton btnLogin,btnChange;

	Login(int i){}

	public void run(){}

	Login ()
	{
		super ("System Authention");
	try
		{
		File file = new File(path);           // create file object

		if(!(file.exists()))
		{
		  DataOutputStream dout = new DataOutputStream(new FileOutputStream(path));
		  dout.writeUTF("login");
		  dout.close();
		}

		  AudioClip ac = Applet.newAudioClip(new File(Location+"Please Enter Password.wav").toURL());
		ac.play();

		setSize (645, 360);
		setResizable(false);
		setLocationRelativeTo(null);
		jpInfo.setBounds (0, 0, 500, 115);
		jpInfo.setLayout (null);

		l1 = new JLabel ("*** Project Team ***");
		l1.setForeground (Color.red);
		l1.setBounds (375, 20, 252, 28);

		l3 = new JLabel ("Student Name 1");
		l3.setForeground (Color.black);
		l3.setBounds (415, 60, 190, 25);

		l4 = new JLabel ("Student Name 2");
		l4.setForeground (Color.black);
		l4.setBounds (415, 90, 190, 25);

		l5 = new JLabel ("Student Name 3");
		l5.setForeground (Color.black);
		l5.setBounds (415, 120, 190, 25);

		l6 = new JLabel ("Student Name 4");
		l6.setForeground (Color.black);
		l6.setBounds (415, 150, 190, 25);

		l1.setFont(new Font("Eras Demi ITC",1,23));
		l3.setFont(new Font("Times New Roman",1,20));
		l4.setFont(new Font("Times New Roman",1,20));
		l5.setFont(new Font("Times New Roman",1,20));
		l6.setFont(new Font("Times New Roman",1,20));

		jpInfo.add (l1);
		jpInfo.add (l3);
		jpInfo.add (l4);
		jpInfo.add (l5);
		jpInfo.add (l6);

		Pinlbl = new JLabel ("Enter Pin:");
		Pinlbl.setForeground (Color.black);
		Pinlbl.setBounds (360, 190, 90, 25);

		PinVal = new JPasswordField(10);
		PinVal.setHorizontalAlignment (JTextField.RIGHT);
		PinVal.setEchoChar('$');
		PinVal.setBounds (430, 190, 190, 25);

		btnLogin = new JButton ("System Login");
		btnLogin.setBounds (360, 230, 120, 35);
		btnLogin.addActionListener (this);

		btnChange = new JButton ("PIN Change");
		btnChange.setBounds (500, 230, 120, 35);
		btnChange.addActionListener (this);

		jprgbar = new JProgressBar();
		jprgbar.setOrientation(JProgressBar.HORIZONTAL);
		jprgbar.setBackground(new Color(22,23,25));
		jprgbar.setFont(new java.awt.Font("Algerian",Font.BOLD,21 ));
		jprgbar.setForeground(Color.black);
		jprgbar.setMaximumSize(new Dimension(32767, 20));
		jprgbar.setMinimumSize(new Dimension(10, 20));
		jprgbar.setBounds(350,280,280,45);
		jprgbar.setString("Folder Locking System");
		jprgbar.setStringPainted(true);
		jprgbar.enable(true);

		File file1 = new File(Location+"Video.mpg");

		URL mediaUrl = file1.toURL();

		MediaPlayer mediaPanel = new MediaPlayer( mediaUrl );

        mediaPanel.setBounds(1,0,335,325);

		jpInfo.add (Pinlbl);
		jpInfo.add (PinVal);

		jpInfo.add (btnLogin);
		jpInfo.add (btnChange);

		jpInfo.add (mediaPanel);

		jpInfo.add (jprgbar);

		jpInfo.setBackground(new Color(22,223,55));

		getContentPane().add (jpInfo);

		setVisible (true);

		addWindowListener (new WindowAdapter ()
		{
			public void windowClosing (WindowEvent we)
			{
				quitApp ();
			}
		}
		);
	}
		catch(Exception e){System.out.println("e1:-"+e);}
}
	private void quitApp ()
	{
		try
		{
		    	int reply = JOptionPane.showConfirmDialog (this,"Are you really want to exit\nFrom System?","System - Exit", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (reply == JOptionPane.YES_OPTION)
				{
				AudioClip ac = Applet.newAudioClip(new File("Images/Good_Bye.wav").toURL());
				ac.play();

				Login b=new Login(0);
				Thread t=new Thread(b);
				t.sleep(1000);
				System.exit (0);        //Close the Application.
				}
				else if (reply == JOptionPane.NO_OPTION)
				{
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			   }
		 }
   	    catch (Exception e) {System.out.println("e8-"+e);}
     }

	public void actionPerformed (ActionEvent ae)
	{
		Object obj = ae.getSource();

		String pass = PinVal.getText();

		if(obj == btnChange)
		{
		  changePassword();
		}
      else
      {
	 try
	  { int checkPassword = 0000;
		if(checkPassword == 0000)
		{

				setVisible(false);

				AudioClip ac = Applet.newAudioClip(new File("Images/Welcome.wav").toURL());
				ac.play();

 	  	        new Thread().sleep(1000);

                System.out.println("welcome into System");
                Option.method();    
  		  }
		else
		{

			setVisible(false);

			AudioClip ac = Applet.newAudioClip(new File("Images/Welcome.wav").toURL());
			ac.play();

	  	        new Thread().sleep(1000);

            System.out.println("welcome into System");
            Option.method(); 
				/*AudioClip ac = Applet.newAudioClip(new File("Images/Invalid Password.wav").toURL());
				ac.play();

			  JOptionPane.showMessageDialog(null,"Password does not matches with original password","Wrong User",JOptionPane.ERROR_MESSAGE);
	          PinVal.setText("");*/
		}
	  }
	   catch(Exception e1){System.out.println("e2-"+e1);}
    }
   }

  //===================================================================================================================================================================
	 /*  public boolean checkPassword()
	   {
		  String original = "0000";
		  try
		  {
		 	DataInputStream dout = new DataInputStream(new FileInputStream(path));
		 	original = dout.readUTF();
		  }
		  catch(Exception e){System.out.println("e3-"+e);}

		  if(PinVal.getText().equals(original))
		 	return false;
		  else
		    return true;
	   }*/
//===================================================================================================================================================================

   public void changePassword()
   {
	try
	{
	  DataInputStream din = new DataInputStream(new FileInputStream(path));        // Open Secure file
	  String pass = din.readUTF();           //Read Password
	  din.close();

	  final JFrame frame = new JFrame("Change PIN");

	  JPanel panel = new JPanel();

	  JLabel lblOldPass = new JLabel("Old PIN ");          // create label
	  JLabel lblNewPass = new JLabel("New PIN ");          // for password
	  JLabel lblConPass = new JLabel("Confirm PIN ");      // input and verification

	  final JPasswordField txtOldPass = new JPasswordField(15);  // create TextArea
	  final JPasswordField txtNewPass = new JPasswordField(15);  // for password entry
	  final JPasswordField txtConPass = new JPasswordField(15);  // and conform password

	  JButton btnOk = new JButton("Change");
	  JButton btnCancel = new JButton("Cancel");

	  panel.setBackground(new Color(212,223,255));

	  GridBagLayout gbl= new GridBagLayout();
	  GridBagConstraints gbc= new GridBagConstraints();

	  panel.setLayout(gbl);

	  gbc.weighty= 4;     // divide vertical space into 4 parts

	  gbc.gridx= 0;       // locate first part
	  gbl.setConstraints(lblOldPass, gbc);         // old password
	  panel.add(lblOldPass);                       // label

	  gbl.setConstraints(lblNewPass, gbc);  // New password
	  panel.add(lblNewPass);                // label

	  gbl.setConstraints(lblConPass, gbc);  // conform password
	  panel.add(lblConPass);                // label

	  gbl.setConstraints(btnOk, gbc);  // Change button
	  panel.add(btnOk);

	  gbc.gridx= 1;       //locate second part
	  gbl.setConstraints(txtOldPass, gbc);   // old password textArea
	  panel.add(txtOldPass);

	  gbl.setConstraints(txtNewPass, gbc);   // New password textArea
	  panel.add(txtNewPass);

      gbl.setConstraints(txtConPass, gbc);   // conform password textArea
	  panel.add(txtConPass);

	  gbl.setConstraints(btnCancel, gbc);  // cancel button
	  panel.add(btnCancel);

	  getContentPane().add(panel);

      frame.add(panel);

	  frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	  frame.setResizable(false);
	  frame.setLocation(400,300);
	  frame.setSize(310,200);
	  frame.setVisible(true);
	  this.setVisible(false);  // hide first window

	  btnOk.addActionListener(new ActionListener()
	  {
	   public void actionPerformed(ActionEvent event)
	   {
	    try
	    {
	 	  DataInputStream din = new DataInputStream(new FileInputStream(path));
		  String orginalPass = din.readUTF();
		  din.close();

		  if(txtOldPass.getText().equals(orginalPass))
		  {
		    if(txtNewPass.getText().equals(txtConPass.getText()))
		    {
		     try
		     {
		 	   DataOutputStream dout = new DataOutputStream(new FileOutputStream(path));
			   dout.writeUTF(txtNewPass.getText());
			   dout.close();

 		       AudioClip ac = Applet.newAudioClip(new File("image\\password_changed.wav").toURL());
  	           ac.play();

			   JOptionPane.showMessageDialog(null,"Password Changed!!!");
			   frame.setVisible(false);             // hide password window
			   new Login().setVisible(true); // disply first window
			 }
			 catch(Exception e){System.out.println("e4-"+e);}
		    }
		    else
			{
			  JOptionPane.showMessageDialog(null,"New password does not match with confirmation password");
			}
		  }g hjm,bhnhnhjkfg'
		  
		  else
			  JOptionPane.showMessageDialog(null,"Password does not match with the original password");
		 }
		   catch(Exception e){System.out.println("e5-"+e);}
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
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("e6-"+e);
		}

	}
	public static void main(String args[])
   {
			Login obj=new Login();
			obj.setVisible(true);
		}

//===================================================================================================================================================================
}