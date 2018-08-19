
import java.io.*;
import java.net.*; 
import java.util.*;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;




public class Service {


	public synchronized static String getFiboSeq(int num1) {


		int t1 = 0, t2 = 1, k = 0;

		String seq ="";

		for (int i = 1; i <= num1; ++i) {
			seq = seq+" "+Integer.toString(t1);
			int sum = t1 + t2;
			t1 = t2;
			t2 = sum;
			k++;
		}
		return seq;
	

	
				
	}
	
	public synchronized static String[] getParameters(String msg){
		
		String[] tokens = msg.trim().split("&");
	
		
		return tokens;
		
	}


	public synchronized static String getBodyTemp(String scale,Double temp) {
		
		if(scale.equals("c")) {
			
			if(temp < 36.5){
				
				return "Hypothemia";
				
			}else if(temp >= 36.5 && temp <= 37.5) {
				
				return "Normal Temperature";
				
			}else if(temp > 37.5 && temp <= 40) {
				
				return "Fever or Hyperthemia";
			}else if(temp > 40) {
				
				return "Hyperpyrexia";
			}
			
			
		}else if(scale.equals("f")){
			
			if(temp < 95){
				
				return "Hypothemia";
				
			}else if(temp >= 95  && temp <= 99.5) {
				
				return "Normal Temperature";
				
			}else if(temp > 99.5 && temp <= 104) {
				
				return "Fever or Hyperthemia";
			}else if(temp > 104) {
				
				return "Hyperpyrexia";
			}
			
		}else {
			
			return "please choose correct scale fahrenheit or celsius";
		}
		
		return null;
		
	}
	
	
	public synchronized static String getBMR(int age,String gender,Double height,Double weight) {
		
		Double BMR;
		
		if(gender.equals("m")){
			
			BMR = 66.47+(13.75 * weight)+(5.0*height*100)-(6.75*age);
			
			return "Your BMR is "+BMR+".";
			
		}else if(gender.equals("f")){
			
			BMR = 665.09+(9.56 * weight)+(1.84*height*100)-(4.67*age);
			
			return "Your BMR is "+BMR+".";
			
			
		}else{
			return "please set the correct gender (male or female)";
		}
		
	}
	
	public synchronized static String getBMI(int age,String gender,Double height,Double weight) {
		
		Double BMI;
		
		BMI = (weight/(height * height)); 


		if(age > 20) {
			
			if(BMI < 18.5) {
				
				return "Your BMI is "+Double.toString(BMI)+" & you are having Underweight";
			}else if(BMI >= 18.5 && BMI <= 24.9) {
				
				return "Your BMI is "+Double.toString(BMI)+" & you are having Normal weight";
			}else if(BMI >=25  && BMI < 29.9) {
				
				return "Your BMI is "+Double.toString(BMI)+" & you are having Overweight";
			}else if(BMI >=30) {
				
				return "Your BMI is "+Double.toString(BMI)+" & you are having Obese";
			}
			
			
		}else if((age <= 20 && age >= 2) && gender.equals("m")){
			
			if(BMI < 19) {
				
				return "Your BMI is "+Double.toString(BMI)+" (less than 5th percentile) & you are having Underweight";
			}else if(BMI >= 19 && BMI < 27) {
				
				return "Your BMI is "+Double.toString(BMI)+" (5th - 85th percentile) &  you are having Normal weight";
			}else if(BMI >=27  && BMI < 30.2) {
				
				return "Your BMI is "+Double.toString(BMI)+" (85th - 95th percentile) & you are having Overweight";
			}else if(BMI >=30.2) {
				
				return "Your BMI is "+Double.toString(BMI)+" (greater than 95th percentile) & you are having Obese";
			}
			
			
		}else if((age <= 20 && age >= 2) && gender.equals("f")) {
			
			if(BMI < 18) {
				
				return "Your BMI is "+Double.toString(BMI)+" (less than 5th percentile) & you are having Underweight";
			}else if(BMI >= 18 && BMI < 26.2) {
				
				return "Your BMI is "+Double.toString(BMI)+" (5th - 85th percentile) &  you are having Normal weight";
			}else if(BMI >=26.2  && BMI < 32.2) {
				
				return "Your BMI is "+Double.toString(BMI)+" (85th - 95th percentile) & you are having Overweight";
			}else if(BMI >=32.2) {
				
				return "Your BMI is "+Double.toString(BMI)+" (greater than 95th percentile) & you are having Obese";
			}
			
			
		}else{
			
			return "Can't calculate BMI";
		}
	

	return null;
				
	}

@SuppressWarnings("deprecation")
public static void main(String args[]){
		
	 final ExecutorService clientProcessingPool = Executors.newFixedThreadPool(10);
	    Runnable serverTask = new Runnable() {

	        @Override
	        public void run() {
	        	
	        	System.out.println("Start");
	    		ServerSocket echoServer = null;
	    		
	    		Socket clientSocket = null;
	            try {
	            	
	    			echoServer = new ServerSocket(3993);
	                while (true) {
	                	clientSocket = echoServer.accept();
	                    clientProcessingPool.submit(new ClientTask(clientSocket));
	                }
	            } catch (Exception e) {
	                System.out.println(e);
	                System.out.print("You're opening too many servers in the same location, fool!\n");
	            }
	        }
	    };
	    Thread serverThread = new Thread(serverTask);
	    serverThread.start();
	
	


}

public static class ClientTask implements Runnable {

    private final Socket skt;

    private ClientTask(Socket skt) {
        this.skt = skt;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void run() {
        //for sending messages
        System.out.println("======================");
        System.out.println("Server has connected!");
        
        
        String line;
		DataInputStream is;
		PrintStream os;
     
        try {
        	
			is = new DataInputStream(skt.getInputStream());
			os = new PrintStream(skt.getOutputStream());
        
       
        
        	String respo;
        	line = is.readLine();
			System.out.println(line);
			if(line != null){

			   System.out.println("Recieved");
			}
		    	 
			String[] para = getParameters(line);
			
			if(para[0].equals("bmi")) {
				
				respo = getBMI(Integer.parseInt(para[1]),para[2],Double.parseDouble(para[3]),Double.parseDouble(para[4]));
				System.out.println(respo);
				os.println(respo);
				os.flush();
				os.close();
				skt.close();
				
			}else if(para[0].equals("bmr")) {
				
				respo = getBMR(Integer.parseInt(para[1]),para[2],Double.parseDouble(para[3]),Double.parseDouble(para[4]));
				System.out.println(respo);
				os.println(respo);
				os.flush();
				os.close();
				skt.close();
				
			}else if(para[0].equals("temp")) {
				
				respo = getBodyTemp(para[1],Double.parseDouble(para[2]));
				System.out.println(respo);
				os.println(respo);
				os.flush();
				os.close();
				skt.close();
				
				
			}
			
			
           
        }catch (Exception e) {
            System.out.println("Server had error receiving message.");
            System.out.println("Error: " + e);
        }

    }
}


}
