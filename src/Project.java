import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

//===================================================================================================================================================================

class Project implements ActionListener
{
	static JButton encrypt=new JButton(new ImageIcon(Login.Location+"/lock.jpg"));
	static JButton decrypt=new JButton(new ImageIcon(Login.Location+"/unlock.jpg"));
	static JFrame fm=new JFrame();
	
	int width=750,height=305;
	JPanel panel=new JPanel();
	static Project frame=null;
	public void actionPerformed(ActionEvent e){}

	Project()
	{
		fm.setSize(width,height);
		fm.setTitle("Secure +");
		fm.setResizable(false);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		fm.setLocation((d.width-width)/2,(d.height-height)/2);
		fm.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.Location+"/stop1.jpg"));
		encrypt.setOpaque(true);
		encrypt.setToolTipText("Click here for encryption");
		decrypt.setToolTipText("Click here for decryption");

		panel.add(encrypt,"East");

		panel.add(decrypt,"West");
		fm.add(panel);
		fm.setVisible(true);
		fm.setAlwaysOnTop(true);
		fm.setDefaultCloseOperation(3);
	}
//===================================================================================================================================================================

	public void method()
	{
		fm.setVisible(true);
		
		encrypt.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				xcopy.ce =1;
				fm.setVisible(false);
				try
				{
					AudioClip ac = Applet.newAudioClip(new File(Login.Location+"/Encryption.wav").toURL());
					ac.play();
				xcopy obj1=new xcopy();
			    obj1.System_Start();
				}
				catch(Exception ace){
					System.out.println(""+ace);
				}
			}
		});


		decrypt.addActionListener(new ActionListener()
		{
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e)
			{
				fm.setVisible(false);
				try
				{
					xcopy.ce =2;
					AudioClip ac = Applet.newAudioClip(new File(Login.Location+"/Decryption.wav").toURL());
					ac.play();

				xcopy obj1=new xcopy();
			    obj1.System_Start();
				}
				catch(Exception ace){
					System.out.println(""+ace);
				}
			}
		});
	}
}

//==================================================================================================================================================================
