import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;

//=============================================================================================================================================
class Data_Transfer extends Thread implements ActionListener
{
  static JFrame frame;

  static JTextField t1= new JTextField(9);

  static JFileChooser  sourcefile= new JFileChooser("./");

  static JFileChooser  destfile= new JFileChooser("./");

  static JButton sbrowse = new JButton("Browse");

  static JButton bstart,bpause,bstop;

  static JLabel s1 = new JLabel("     Source File",JLabel.LEFT);

  static JMenuItem mbtnstart,mbtnpause,mbtnabort,mbtnhelp,mbtnabout,mbtnExit,mbtnSource,mbtnDest;

  static ImageIcon is,ip,ie,is2,ip2,ie2;

  public static int  ce=0;

  static int rate=15000,len;  
  
  static int key_len;
  
  static byte otpval[] = (Authentication.otpkey).getBytes();

  static String spath,dpath,key=Authentication.otpkey,orig_key="";

  static xcopy x=new xcopy();

  static int width=300,height=180;

  static InputStream in = null;
  
  static  OutputStream out = null;
  
  static AudioClip ac;
  
  static int size=0;

//=============================================================================================================================================

   public static void System_Start()throws Exception
   {
	 
	 key_len=0;  

	 for(int i=0;i<otpval.length;i++)
	 {
	   key_len+=otpval[i];	 
	 }
	 key_len=key_len/otpval.length;

	 frame=new JFrame("Bio-Security System");                         //create Frame with Title Xstream Copy
     Dimension d= Toolkit.getDefaultToolkit().getScreenSize();
     frame.setBounds((d.width- width)/2, (d.height- height)/2,295,165);   //disply at center of screen
   
	 x.setPriority(9);

	 frame.setResizable(false);

	 Image imgs = Toolkit.getDefaultToolkit().getImage(Login.Location+"/ts.gif");  //  Create start button
		is=new ImageIcon(imgs);                                           //  assign image icon
	    bstart = new JButton("Start",is);                                         //  from image folder

	 Image imgp = Toolkit.getDefaultToolkit().getImage(Login.Location+"/tp.gif");
		ip=new ImageIcon(imgp);                                            // same for pause button
	    bpause = new JButton("Pause",ip);


	 Image imge = Toolkit.getDefaultToolkit().getImage(Login.Location+"/error.gif");  // same for stop button
	    ie=new ImageIcon(imge);
	    bstop = new JButton("Stop",ie);

	 Image imgs2 = Toolkit.getDefaultToolkit().getImage(Login.Location+"/ts2.gif");   // set rollover image of start button
		is2=new ImageIcon(imgs2);

	 Image imgp2 = Toolkit.getDefaultToolkit().getImage(Login.Location+"/tp2.gif");   // set rollover image of pause button
		ip2=new ImageIcon(imgp2);

	 Image imge2 = Toolkit.getDefaultToolkit().getImage(Login.Location+"/error2.gif"); // set rollover image of stop button
		ie2=new ImageIcon(imge2);

   	    bstart.setRolloverIcon(is2);    //  set rollover(mouse move)
		bpause.setRolloverIcon(ip2);    //  image to repective button
		bstop.setRolloverIcon(ie2);

        bstart.setEnabled(true);
	    bpause.setEnabled(false);
	    bstop.setEnabled(false);


   	 Image imgx = Toolkit.getDefaultToolkit().getImage(Login.Location+"/frame_logo.gif");   //set frame logo to main frame
   		frame.setIconImage(imgx);

   		JMenu file=new JMenu("    File    ");                   // create File Tab
   			 file.setMnemonic('f');                         // assign shortcut key
			 mbtnSource = new JMenuItem("Open Source");     // create internal
			 mbtnDest = new JMenuItem("Open Destination");  // option of file
			 mbtnExit = new JMenuItem("Exit");              // menu Tab
		
		JMenu Actions=new JMenu("    Actions    ");
			 Actions.setMnemonic('a');                 // same for Action Tab
			 mbtnstart = new JMenuItem("Start");
			 mbtnpause = new JMenuItem("Pause");
			 mbtnabort = new JMenuItem("Abort");

		JMenu Help=new JMenu("    Help    ");
			 Help.setMnemonic('h');
			 mbtnhelp = new JMenuItem("Help");        //same for setting Tab
			 mbtnabout = new JMenuItem("About");

		file.add(mbtnSource);   //  add source destn
		file.add(mbtnDest);     //  exit MenuItem into
 		file.addSeparator();    //  file Menu
		file.add(mbtnExit);     //  add separator after source and destn

		Actions.add(mbtnstart);
			mbtnstart.setIcon(is);  // set ImageIcon
		Actions.add(mbtnpause);
			mbtnpause.setIcon(ip);
		Actions.add(mbtnabort);
			mbtnabort.setIcon(ie);

		Help.add(mbtnhelp);     // same for
		Help.addSeparator();    // help Tab
		Help.add(mbtnabout);

		JMenuBar mb=new JMenuBar();           //Create MenuBar

		mb.add(file);        // add file Action
		mb.add(Actions);     // Setting And Help
		mb.add(Help);

		frame.add(mb);  	//menuBar
		frame.add(s1);  	//source file label
		frame.add(t1);      //desn file lable
		frame.add(sbrowse); //source browse btn

		frame.add(bstart);  //add start pause stop and
		frame.add(bpause);  //progess Bar on frame
		frame.add(bstop);

		frame.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));  // set postion of frame on Window
	    frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter()
    	{
    	  public void windowClosing(WindowEvent e)
    	  {
    		exit();
  		  }
  		});

		mbtnSource.addActionListener(new Data_Transfer());
		mbtnDest.addActionListener(new Data_Transfer());
		mbtnExit.addActionListener(new Data_Transfer());

		mbtnstart.addActionListener(new Data_Transfer());
		mbtnpause.addActionListener(new Data_Transfer());
		mbtnabort.addActionListener(new Data_Transfer());

  		mbtnhelp.addActionListener(new Data_Transfer());
		mbtnabout.addActionListener(new Data_Transfer());

		bstart.addActionListener(new Data_Transfer());
			bstart.setMnemonic('s');
			bstart.setToolTipText("Start copy");

		bpause.addActionListener(new Data_Transfer());
			bpause.setMnemonic('p');
			bpause.setToolTipText("Pause copy");

		bstop.addActionListener(new Data_Transfer());
			bstop.setMnemonic('e');
			bstop.setToolTipText("Abort copy");

		sbrowse.addActionListener(new Data_Transfer());
			sbrowse.setToolTipText("Select Source");
  	}

//=============================================================================================================================================

   public void actionPerformed(ActionEvent e)
	{
	  Object o=e.getSource();

	  if(o==sbrowse || o==mbtnSource)  //source browse btn or file menu source item
	  {
		sourcefile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);   //open file chooser dialog box

        play_sound("Browse File");

		if(sourcefile.showOpenDialog(null)== sourcefile.APPROVE_OPTION)
		{
	 	  spath= sourcefile.getSelectedFile().getPath();   // get file path
		  t1.setText(spath);  // disply path in text box
		}
	  }

	  if(o==bstart || o==mbtnstart)
	  {
	    bstart.setEnabled(false);
	    bpause.setEnabled(true);
	    bstop.setEnabled(true);

	  	startcopy();
      }

	  if(o==bpause || o==mbtnpause)
	  {
        bstart.setEnabled(true);
	    bpause.setEnabled(false);
	    bstop.setEnabled(true);

	  	pausecopy();
      }

	  if(o==bstop || o==mbtnabort)   //stop while copy data
		exit();

	  if(o==mbtnExit)     //normal stop
	  {
		 exit();
	  }

	  if(o==mbtnhelp)
	  {
	    try
	    {
		  Runtime run= Runtime.getRuntime();
		  run.exec("notepad"+Login.Location+"/help.txt");   // open help txt file
	    }
	    catch(Exception ee)
	    {
		  JOptionPane.showMessageDialog(frame, "Unable to open help file\n"+ ee, "Operation Failed", JOptionPane.ERROR_MESSAGE);
	    }
	  }

	  if(o==mbtnabout)
	  {
		 frame.setEnabled(false);
		 AboutFrame af=new AboutFrame();
	  }
	}

//=============================================================================================================================================

	static void startcopy()
	{
		if(spath==null )
		{
		  JOptionPane.showMessageDialog(frame,"Please Enter Both Sourcce And Destination Fields","Error", JOptionPane.ERROR_MESSAGE);
		  bstart.setEnabled(true);
		  bpause.setEnabled(false);
		  bstop.setEnabled(false);
		 return;
		}
		try
	    {
		  if(x.getState()==Thread.State.NEW)  // first time copy start (new thread created using xcopy class obj)
		  {
			play_sound("copy_started");
			x.start();               		// xcopy run method call
		  }
		  else                              // copy resume after pause
		  {
			play_sound("copy_started");
			x.resume();
		  }
		}
		catch(NumberFormatException e)
		{
		   JOptionPane.showMessageDialog(frame,"Please Enter the key in range (0-127)","Error", JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e)
		{
		   System.out.println(e);
		}
	 }
	
//=============================================================================================================================================

  	static void play_sound(String s)
  	{
	  try
	  {
		ac=Applet.newAudioClip(new File(Login.Location+"\\"+s+".wav").toURL());
		ac.play();
	  }
	  catch(Exception e){}
	}

//=============================================================================================================================================

	static void pausecopy()
	{
	    try
		{
		  if(x.getState()==Thread.State.NEW)  //user press pause before starting copy
		    return;
		  else
	   	  {
			play_sound("copy_pause");
			x.suspend();               //suspend current copy threads.
		  }
		}
		catch(Exception e)
		{
		  System.out.println(e+" bpause");
		}
	}

//=============================================================================================================================================

  	static void exit()
  	{
	  pausecopy();
	  if(!(x.getState()==Thread.State.NEW))   //copy process running
	  {
		play_sound("do_you_want_exit");
		int scd=JOptionPane.showConfirmDialog(frame, "Do You Really Want To Stop Transfer ?", "Question", JOptionPane.YES_NO_OPTION);

		if(scd==JOptionPane.YES_OPTION)  // stop process
		{
		  x.stop();
		  System.exit(0);
		}
		if(scd==JOptionPane.NO_OPTION)  // resume copy process
		{
		  play_sound("copy_started");
		  startcopy();
		}
	  }
	   else
	  {
		x.stop();             // copy alredy completed
		System.exit(0);
	  }
	}
}
//=============================================================================================================================================

public class xcopy extends Data_Transfer
{
   public void run()
   {
	 int count =1;
	 
	 try
	 {
	   spath=t1.getText();   // store source file path in spath and

	   if(ce ==1)
		   dpath=t1.getText()+"_encrypt";   // destination file path in dpath
	   
	   else
		   dpath=t1.getText().replaceAll("_encrypt", "");
	   
	    if(!(ce==1 || ce==2))
	 	  play_sound("copy_started");    // no security , play sound
	    
	    if(count>0)
	    {
	    	copy(spath,dpath);

	    	StringTokenizer st;
	    	
	    	String bpath="";
		
			st = new StringTokenizer(dpath, "\\");
			
			while(st.hasMoreTokens())
			{
				bpath = st.nextToken();
			}
			
			frame.setTitle("Folder Encrypted");
			
			play_sound("copy_completed");
	
	 try
	{
	  in.close();
      out.close();
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	
	 try
	 {
		 x.sleep(1600);
		 deleteDirectory(new File(spath));
	 }
	  catch(Exception e){     }
	  System.exit(0);
	 }
     else
	 {
		JOptionPane.showMessageDialog(frame,  "You are not authorise to decode.","Error", JOptionPane.ERROR_MESSAGE);	
		System.exit(0);
	 }

   }  catch(Exception e){     }
   }

 //=============================================================================================================  
 
   public static boolean deleteDirectory(File dir)
   {
     if (dir.isDirectory())
     {
        File[] children = dir.listFiles();
  
        for (int i = 0; i < children.length; i++)
        {
                boolean success = deleteDirectory(children[i]);
                if (!success)
                {
                     return false;
                }
                }
             } // either file or an empty directory
             System.out.println("removing file or directory : " + dir.getName());
             return dir.delete();
             }
             /* * Incorrect way to delete a directory in Java */
             public static void deleteDirectory(String file)
             {
                  File directory = new File(file);
                  File[] children = directory.listFiles();
                  for (File child : children)
                  {
                       System.out.println(child.getAbsolutePath());
                   } // let's delete this directory // it will not work because directory has sub-directory // which has files inside it. // In order to delete a directory, // you need to first delete its files or contents.
                   boolean result = directory.delete();
                   if (result)
                   {
                       System.out.printf("Directory '%s' is successfully deleted", directory.getAbsolutePath());
                   }
                   else
                   {
                      System.out.printf("Failed to delete directory '%s' %n", directory.getAbsolutePath());
                      }
                      }
  
 //=============================================================================================================  
   public void copy(String s,String d)throws Exception
   {
 	 File sf=new File(s);
 	 
 	 int generate = 75288857,skip_bytes;
 	 
 	 String Stored_key=null;
 	 
 	 long read_length;
 
	 if(!sf.isDirectory())
	 {
	   File source=new File(s);
	   File dest=new File(d);
	   
	   byte[] buf = new byte[rate];

	   try
	   {
	   	 in = new  FileInputStream(source);
		 out = new FileOutputStream(dest,true);
	
		 if(ce==2)   // for decode
		 {
		   DataInputStream din=new DataInputStream(new FileInputStream(source));
           read_length = din.readLong();
		   generate = din.readInt();
		   Stored_key = din.readUTF();
		   din.close();

           byte[] SECRETE_KEY_DE = Stored_key.getBytes();

     	   for(int k = 0;k < Stored_key.length();k++)
            SECRETE_KEY_DE[k] = new Integer(SECRETE_KEY_DE[k]^generate%256).byteValue();

           orig_key = new String(SECRETE_KEY_DE);

           while(true)
		   {
			if(!key.equals(orig_key))
			{
			  play_sound("key_error");
			  
			  JOptionPane.showMessageDialog(frame, "Key is Invalid.", "Error",JOptionPane.ERROR_MESSAGE);
			  
			  out.close();	 			  
			  
			  deleteDirectory(dest);

			  x.sleep(1600);

			  System.exit(0);
		    }
			else
			{
			 if(!dest.exists())
		     {
			   try
			   {
				  dest.createNewFile();
			   }
			   catch(Exception e)
			   {
			     JOptionPane.showMessageDialog(frame,"Invalid destination","Error", JOptionPane.ERROR_MESSAGE);
			   }
			 }	
			  skip_bytes=orig_key.length()+ 8 + 4 + 2;
	  	      in.skip(skip_bytes);  // Skip UTF data
			  break;
		    }
		   }
		  }
		  if(ce==1)       //ce==1 for encode  ce=2 for decode
		  {
			DataOutputStream dos=new DataOutputStream(new FileOutputStream(dest));

            long pass_len=key.length();

            byte[] SECRETE_KEY_EN = key.getBytes();

            for(int k = 0;k < pass_len;k++)
	         SECRETE_KEY_EN[k] = new Integer(SECRETE_KEY_EN[k]^generate%256).byteValue();

	         dos.writeLong(pass_len);
	         dos.writeInt(generate);
	         dos.writeUTF(new String(SECRETE_KEY_EN));
	         dos.close();

			 //key_len=(byte)key.length();
		  }
		   while (((len = in.read(buf)) > 0))
		  {
			 frame.setTitle("Work In Progress");   // change Frame title with % value
			
			if(ce==1)                           // for encode
			 for(int i=0;i<buf.length;i++)
				buf[i]+=key_len;

			if(ce==2)                          // for Decode
			 for(int i=0;i<buf.length;i++)
				buf[i]-=key_len;

			 out.write(buf, 0, len);    // write buffer data
		   }
		   in.close();
		 }

		 catch(IOException e)   // destination memory full
		 {
			 System.out.println(e);
	     }
		  catch(Exception e){ System.out.println(e);}
	   }
		else       //means sf is not a file
		{
			String l[]=sf.list();
			File df=new File(d);
			df.mkdir();
			try
			{
			  for(int k=0;k<l.length;k++)
			 	copy(s+"\\"+l[k],d+"\\"+l[k]);
			  
			  in.close();
			}
			catch(Exception e)
			{
			  JOptionPane.showMessageDialog(null,e,"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
   }
 }
//=============================================================================================================================================

