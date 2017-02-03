
package PortScanner;

public class macfinder
{
    
    public static void main(String[] args)
    {
		Runtime runtime = Runtime.getRuntime();
		try {
   		 Process p1 = runtime.exec("cmd /c start E:\\macFinder.bat");

		} catch(Exception ex) {

		}

    }

}