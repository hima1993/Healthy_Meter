package middleware;
import java.io.*;
import java.net.*; 
import java.util.*;

public class Middleware {
	
	Socket socket;
	PrintWriter sOut = null;
	BufferedReader sIn = null;
	
	String HOST = "localhost";
	int PORT = 3993;
	
	Map<String, String[]> ServiceRepo;
	
	public Middleware(String service){
		
		this.InitializeService(service, HOST, PORT);
		
		socket = new Socket();
	    try {
			socket.connect(new InetSocketAddress(HOST,PORT));
			
			sOut = new PrintWriter( socket.getOutputStream(), true);
			sIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	private void InitializeService(String Service,String Host,int Port){
		
		ServiceRepo = new HashMap<String, String[]>();
		ServiceRepo.put(Service, new String[]{Host,Integer.toString(Port)});
		
		
	}
	
	private int[] RequestService(String Service,String Parameter) {
		
		if(Service.equals("getFibbonacciService")){
			   sOut.println(Parameter);
			  
			  	int count = 0;
	           
	           int[] Finalresponse = {};
	            String Readresponse;
	            try {
					while ((Readresponse= sIn.readLine()) != null){
						String[] tokens = Readresponse.trim().split(" ");

						Finalresponse = new int[tokens.length];
						int i = 0;
                            					for (String token : tokens){
    							Finalresponse[i++] = Integer.parseInt(token); 
						}
						
						
					
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            
	            return Finalresponse;
			
		}
		
		return null;
		
	
	            
		
		
	}
	
	
private String RequestServices(String Service,String... Parameter) {
		
		if(Service.equals("getBMIService")){
				
			String message = "bmi&";
            for(int i=0;i<Parameter.length;i++){
            	
            	 if(i>0){
            		 message = message+"&";
                 }   
            	 message = message+Parameter[i];
                
                	
               
            }
			
			
			
			   sOut.println(message);
			  
			   int count = 0;
	           
	           String Finalresponse = "";
	            String Readresponse;
	            try {
					while ((Readresponse= sIn.readLine()) != null){
						

						Finalresponse = Readresponse;
						
						
						
					
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            
	            return Finalresponse;
			
		}if(Service.equals("getBodyTempService")){
			
			
			String message1 = "temp&";
            for(int i=0;i<Parameter.length;i++){
            	
            	 if(i>0){
            		 message1 = message1+"&";
                 }   
            	 message1 = message1 + Parameter[i];
                
                	
               
            }
			
			
			
			   sOut.println(message1);
			  
			   int count = 0;
	           
	           String Finalresponse1 = "";
	            String Readresponse;
	            try {
					while ((Readresponse= sIn.readLine()) != null){
						

						Finalresponse1 = Readresponse;
						
						
						
					
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            
	            return Finalresponse1;
			
			
			
			
		}if(Service.equals("getBMRService")){
			
			
			String message2 = "bmr&";
            for(int i=0;i<Parameter.length;i++){
            	
            	 if(i>0){
            		 message2 = message2+"&";
                 }   
            	 message2 = message2 + Parameter[i];
                
                	
               
            }
			
			
			
			   sOut.println(message2);
			  
			   int count = 0;
	           
	           String Finalresponse2 = "";
	            String Readresponse;
	            try {
					while ((Readresponse= sIn.readLine()) != null){
						

						Finalresponse2 = Readresponse;
						
						
						
					
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            
	            return Finalresponse2;
			
			
			
			
		}
		
		return null;
		
	
	            
		
		
	}

	
	
  public int[] getFibbonacciService(int num1) throws Exception {
		
		int[] response;
        try{
            response = RequestService("getFibbonacciService", Integer.toString(num1));
        }catch(Exception e){
            throw new Exception("Serive Error");
        }
        return response;
		
		
	}
  
  public String getBMI(int age,String gender,Double height,Double weight) throws Exception {
		
		String response;
      try{
          response = RequestServices("getBMIService",Integer.toString(age),gender,Double.toString(height),Double.toString(weight));
      }catch(Exception e){
          throw new Exception("Serive Error");
      }
      return response;
		
		
	}
  
  public String getBodyTemp(String scale,Double temp) throws Exception {
		
		String response;
    try{
        response = RequestServices("getBodyTempService",scale,Double.toString(temp));
    }catch(Exception e){
        throw new Exception("Serive Error");
    }
    return response;
		
		
	}
  
  public String getBMR(int age,String gender,Double height,Double weight) throws Exception {
		
		String response;
  try{
      response = RequestServices("getBMRService",Integer.toString(age),gender,Double.toString(height),Double.toString(weight));
  }catch(Exception e){
      throw new Exception("Serive Error");
  }
  return response;
		
		
	}
	
	

}
