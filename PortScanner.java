package PortScanner;

//import PortScanner.second;

import java.io.IOException;
import java.net.*;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class first_gui {

	private JFrame frame1;
	private JTextField start_ip;
	private JTextField stop_ip;
	private JTextField port_no_start;
	private JTextField port_no_end;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					first_gui window = new first_gui();
					window.frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the application.
	 */
	public first_gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame1.
	 */
	private void initialize() {
		frame1 = new JFrame();
		frame1.setTitle("PORT   SCANNER");
		frame1.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 17));
		frame1.setBounds(100, 100, 450, 300);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(null);
	
		
		JButton scanBtn = new JButton("Scan");
		scanBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				frame1.dispose();
				
				
				
				int timeout=1000;
				String s="";
				for(int i=start_ip.getText().length()-1;i>=0;i--)
				{
					if(start_ip.getText().charAt(i)!='.')
						s=start_ip.getText().charAt(i)+s;
					else
						break;
				}
			
				int start_IP = Integer.parseInt(s);
				s="";
				for(int i=stop_ip.getText().length()-1;i>=0;i--)
				{
					if(stop_ip.getText().charAt(i)!='.')
						s=stop_ip.getText().charAt(i)+s;
					else
						break;
				}
				int stop_IP = Integer.parseInt(s);
				
				int startPortRange= Integer.parseInt(port_no_start.getText());
				int stopPortRange= Integer.parseInt(port_no_end.getText());
				String first_three_fields="";
				
				String arr[] =new String[stop_IP];
				
				int count=0;
				for(int i=0;i<start_ip.getText().length();i++)
				{
					if(start_ip.getText().charAt(i)=='.')
						count++;
					
					if(count>=3)
						break;
					
					first_three_fields=first_three_fields+start_ip.getText().charAt(i);
					
				}
				
				for (int i=start_IP;i<stop_IP;i++)
			     {
					String host=first_three_fields + "." +Integer.toString(i);
					arr[i]=host;
					try
					{
					   if (InetAddress.getByName(host).isReachable(timeout))
					   {
					      System.out.println(host + " is reachable");
					      arr[i]=arr[i]+"      Reachable          ";
					   		
					      for(int j=startPortRange; j <=stopPortRange; j++)
					      {
					    	  try
			                  {
					    		  Socket ServerSok = new Socket(host,j);
					    		  System.out.println("Port in use: " + j );
					    		  arr[i]=arr[i]+ Integer.toString(j) +", ";
					    		  ServerSok.close();
			                  }
					    	  catch (Exception e)
			                  {
					    		  System.out.println("Port not in use: " + j );
			                  }
					      }
					   }
					   else
					   {
						   System.out.println(host + " is not reachable");
						   arr[i]=arr[i]+"   Not Reachable   ";
					   }
					}
				    catch(Exception e)
				    {
					   System.out.println(host + " is not reachable");
				    }
			     }
				String message="";
				for(int i=start_IP ; i<stop_IP;i++)
				{
					message=message+arr[i]+'\n';
				}
				 JTextArea jta = new JTextArea(message);
		            JScrollPane jsp = new JScrollPane(jta){
		                @Override
		                public Dimension getPreferredSize() {
		                    return new Dimension(480, 320);
		                }
		            };
		            JOptionPane.showMessageDialog(
		                null, jsp, "Result", JOptionPane.PLAIN_MESSAGE);
			//	JOptionPane.showMessageDialog(null,message);
				
			}
		});
		scanBtn.setFont(new Font("Tahoma", Font.BOLD, 17));
		scanBtn.setBounds(152, 199, 115, 29);
		frame1.getContentPane().add(scanBtn);
		
		start_ip = new JTextField();
		start_ip.setFont(new Font("Tahoma", Font.BOLD, 16));
		start_ip.setBounds(152, 46, 189, 26);
		frame1.getContentPane().add(start_ip);
		start_ip.setColumns(10);
		
		JLabel start_ip_label = new JLabel("Start IP");
		start_ip_label.setFont(new Font("Tahoma", Font.BOLD, 16));
		start_ip_label.setBounds(31, 49, 122, 20);
		frame1.getContentPane().add(start_ip_label);
		
		stop_ip = new JTextField();
		stop_ip.setFont(new Font("Tahoma", Font.BOLD, 16));
		stop_ip.setBounds(152, 88, 189, 26);
		frame1.getContentPane().add(stop_ip);
		stop_ip.setColumns(10);
		
		JLabel stop_ip_label = new JLabel("Stop IP");
		stop_ip_label.setFont(new Font("Tahoma", Font.BOLD, 16));
		stop_ip_label.setBounds(31, 100, 112, 20);
		frame1.getContentPane().add(stop_ip_label);
		
		port_no_start = new JTextField();
		port_no_start.setFont(new Font("Tahoma", Font.BOLD, 16));
		port_no_start.setBounds(152, 139, 68, 26);
		frame1.getContentPane().add(port_no_start);
		port_no_start.setColumns(10);
		
		JLabel port_number_label = new JLabel("Port Number");
		port_number_label.setFont(new Font("Tahoma", Font.BOLD, 16));
		port_number_label.setBounds(31, 142, 112, 20);
		frame1.getContentPane().add(port_number_label);
		
		JLabel lblTo = new JLabel("to");
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTo.setBounds(235, 142, 69, 20);
		frame1.getContentPane().add(lblTo);
		
		port_no_end = new JTextField();
		port_no_end.setFont(new Font("Tahoma", Font.BOLD, 16));
		port_no_end.setColumns(10);
		port_no_end.setBounds(273, 139, 68, 26);
		frame1.getContentPane().add(port_no_end);
	}
}