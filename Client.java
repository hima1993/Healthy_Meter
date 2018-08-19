
import middleware.Middleware;
import java.util.Scanner;

public class Client {
	
	
	
	public static void main(String[] args){
		
		boolean flag = true;
		
		while(flag) {
		System.out.println(".......................WELCOME TO HEALTHY METER...........................");
		System.out.println("Choose one Option in below by typing the command that mentioned infront");
		System.out.println("  1) Check your BMI(Body Mass Index)  : Type bmi ");
		System.out.println("  2) Check your BMR(Basal Metabolic Rate)  : Type bmr ");
		System.out.println("  3) Check your Body Temperature status  : Type temp ");
		
		
		Scanner reader = new Scanner(System.in);
		String n = reader.next();
		
		if(n.equals("bmi")){
			
			System.out.println("For Get your BMI value insert your age,gender,height(in meters) & weight(in kilograms)");
			System.out.println("Your Age :");
			int age = reader.nextInt();
			System.out.println("Your Gender :(m - for male & f - for female)");
			String gender = reader.next();
			System.out.println("Your Height :(in meters)");
			Double height = reader.nextDouble();
			System.out.println("Your Weight :(in kilograms)");
			Double weight = reader.nextDouble();
			
			String output = "";
			Middleware mware = new Middleware("getBMIService");
			
			
			 try{
		           output = mware.getBMI(age,gender,height,weight);
		           
		           System.out.println(output);
		           
//		           for(int j=0;j<output.length;j++) {
//		        	   
//		        	   System.out.println(output[j]);
//		           }
		        }catch(Exception e){
		            System.out.println(e.getMessage());
		        }
			
		}else if(n.equals("bmr")){
			
			System.out.println("For Get your BMR value insert your age,gender,height(in meters) & weight(in kilograms)");
			System.out.println("Your Age :");
			int age1 = reader.nextInt();
			System.out.println("Your Gender :(m - for male & f - for female)");
			String gender1 = reader.next();
			System.out.println("Your Height :(in meters)");
			Double height1 = reader.nextDouble();
			System.out.println("Your Weight :(in kilograms)");
			Double weight1 = reader.nextDouble();
			
			
			String output1 = "";
			Middleware mware = new Middleware("getBMRService");
			
			
			 try{
		           output1 = mware.getBMR(age1,gender1,height1,weight1);
		           
		           System.out.println(output1);
		           
//		           for(int j=0;j<output.length;j++) {
//		        	   
//		        	   System.out.println(output[j]);
//		           }
		        }catch(Exception e){
		            System.out.println(e.getMessage());
		        }
			
			
		}else if(n.equals("temp")){
			
			System.out.println("For Get your Body temperature status , insert your temperature measurement scale & temperature value");
			
			System.out.println("Your Temperature Scale :(c - for Celsius & f - for Farenheit)");
			String scale = reader.next();
			System.out.println("Your Temperature :");
			Double temp = reader.nextDouble();
			
			String output2 = "";
			Middleware mware = new Middleware("getBodyTempService");
			
			
			 try{
		           output2 = mware.getBodyTemp(scale,temp);
		           
		           System.out.println(output2);
		           
//		           for(int j=0;j<output.length;j++) {
//		        	   
//		        	   System.out.println(output[j]);
//		           }
		        }catch(Exception e){
		            System.out.println(e.getMessage());
		        }
			
			
			
		}
		
		System.out.println("You want to continue? y/n");
		String cnt = reader.next();
		
		if(cnt.equals("n")) {
			
			flag = false;
			
		}
		
		
	    	
		}	
		
		
	       
	    }
	    
	

}

