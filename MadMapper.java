import java.util.Scanner;

public class MadMapper 
{
	public static void main(String[] args)
	{
		// instantiate the backend
	   IBackend backend = new Backend();
	   
	   // instantiate the scanner for user input
       Scanner userInputScanner = new Scanner(System.in);
       
      //instantiate frontend and pass refernces of scanner and backend to it
       IFrontend frontend = new Frontend(backend, userInputScanner);
       
       //start the input loop of the frontend
       frontend.runCommandLoop();
       
	}

}
