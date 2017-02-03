import java.net.*;
import java.util.*;
import java.io.*;
import java.awt.*;

import javax.swing.*;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

 public class File_handle

{
   public static void main(String args[]) throws UnknownHostException, IOException
   {
     String first_three_field="172.25.13";
     int port[]= { 22, 23, 135, 139, 443, 445};
     int startPortRange=134;
     int stopPortRange=140;
     int start_ip=100;
     int end_ip=224;
     int timeout=1000;
     int flag[][] = new int[101][450];
     for(int i=0;i<100;i++)
     {
    	 for(int j=0;j<450;j++)
    		 flag[i][j]=0;
     }
     Scanner input=new Scanner (System.in);
     
     while(true){
     for (int i=start_ip;i<end_ip;i++)
     {
		String host=first_three_field + "." +Integer.toString(i);
		try
		{
			
			
		   if (InetAddress.getByName(host).isReachable(timeout))
		   {
		      System.out.println(host + " is reachable");
		      File file= new File(host+".txt");
				if (!file.exists()) 
				{
					file.createNewFile();
				}
		   		
		      for(int j=0; j <=5; j++)
		      {
		    	  PrintWriter p=new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
		    	  try
                  {
		    		  Socket ServerSok = new Socket(host,port[j]);
		    		  System.out.println("Port in use: " + port[j] );
		    		 
		    		  if(flag[i][port[j]]==0)
		    		  {
		    			  DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		    		      Date dateobj = new Date();
		    			  System.out.println("Start Time " + df.format(dateobj) );
		    			  p.println("Port in use: " + port[j] );
		    			  p.println("Start Time " + df.format(dateobj) );
		    			  flag[i][port[j]]=1;
		    		  }
		    		  ServerSok.close(); 
                  }
		    	  catch (Exception e)
                  {
		    		  if(flag[i][port[j]]==1)
		    		  {
		    			  DateFormat dfe = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		    		      Date dateobj = new Date();
		    		      System.out.println("port no: " + port[j] );
		    			  System.out.println("End Time " + dfe.format(dateobj) );
		    			  p.println("Port closed: " + port[j] );
		    			  p.println("End Time " + dfe.format(dateobj) );
		    			  flag[i][port[j]]=0;
		    		  }
                  }
		    	  p.close();
		      }
		   }
		   else
		   {
			   System.out.println(host + " is not reachable");
			   File file= new File(host+".txt");
				if (file.exists()) 
				{
					PrintWriter p=new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
				
				   for(int j=0;j<=5;j++)
				   {
			  		  if(flag[i][port[j]]==1)
			  		  {
			  			  DateFormat dfe= new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			  		      Date dateobj = new Date();
			  		      System.out.println("port no: " + port[j] );
			  			  System.out.println("End Time " + dfe.format(dateobj) );
			  			 p.println("Port closed: " + port[j] );
		    			  p.println("End Time " + dfe.format(dateobj) );
			  			  flag[i][port[j]]=0;
			  		  }
				   }
				}
		   } 
     }
     catch(Exception e)
     {
    	 System.out.println("Error");
     }
   }	 
   }
   }
}