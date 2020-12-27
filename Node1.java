

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

//package flood;




class Node1 implements Runnable{
	
	static int id=4999;
	int seq=0;
	ArrayList<Integer> neihbour = new ArrayList<Integer>();
	int nodeid=0;
	
    DatagramSocket sockfd; 
    byte[] receive = new byte[65535]; 
    boolean dsr=false;
    boolean path=false;
    DatagramPacket DpReceive = null;
    byte buf[] = null;
    InetAddress ip;
    
    Node1(){
    id++;
    nodeid=id;
    try {
		sockfd=new DatagramSocket(nodeid);
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (SocketException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();}
   }
    
    public void printneighbour() {
		 System.out.println("Neighbour of Node # : "+nodeid);
		 for(int i=0;i<neihbour.size();i++) {
			 
			 System.out.println(neihbour.get(i));
		 }
		 System.out.println();
	 }
	
    void sendpacket(String message,int portno) {
    
  		  if(message.contentEquals("NewNeighbour") && portno!=this.nodeid) {
  			  if(neihbour.contains(portno)) {
  				  
  			  }
  			  else {
  			  neihbour.add(portno);
  			  buf = message.getBytes();
  			  DatagramPacket DpSend =  new DatagramPacket(buf, buf.length,ip,portno); 
  			  try {
  				sockfd.send(DpSend);
  			} catch (IOException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
  			  }
  		  }
  		  else {
  		Random obj=new Random();
  		  int drop=obj.nextInt(100);
  		  if(drop==1 || drop<=20) {
  			System.out.println("\nPacket Lost  at:  "+this.nodeid);  
  		  }
  		  else {
  		  buf = message.getBytes();
  		  DatagramPacket DpSend =  new DatagramPacket(buf, buf.length,ip,portno); 
  		  try {
  			sockfd.send(DpSend);
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		  }
  		  }
  		  
  	
    }
    void floodmessage(String message,int destination,int source) {
    	if(source==nodeid && source!=destination) {
    		String str1 = Integer.toString(source); 
    	    String str2 = Integer.toString(destination);
    	    Random random = new Random();
    	    seq=random.nextInt(3000);
    	    String str4=Integer.toString(seq);
    	   String str3;
    	   String str5;
    	   String division="#";
    	   String temp="";
    	   random = new Random();
   	    seq=random.nextInt(3000);
    	    if(message.contains("Route Request")) {
    	    	dsr=true;
    	    	path=false;
    	    	str3 = Integer.toString(1);
    	    }
    	    else {
    	    	dsr=false;
    	    	path=false;
    	    	str3 = Integer.toString(0);
    	    }
    	    str5 = Integer.toString(0);
    	    	
    	    	temp+=division;
    	    	temp+=str3;
    	    	temp+=division;
    	    	temp+=str1;
    	    	temp+=division;
    	    	temp+=str2;
    	    	temp+=division;
    	    	temp+=str4;
    	    	temp+=division;
    	    	temp+=str5;
    	    	temp+=division;
    	    	temp+=message;
    	    //System.out.println(temp);
    	    	buf = temp.getBytes();
    	    	
    	    	for(int i=0;i<neihbour.size();i++) {
    			
    			sendpacket(temp,neihbour.get(i));
    		    }
    	   }
    	else {
    		System.out.println("Your source no is not correct\n");
    	}
    	
    }
    void receivemessage() {
    	while(true){
    		DpReceive = new DatagramPacket(receive, receive.length);
    		try {
				sockfd.receive(DpReceive);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		 int ra=DpReceive.getPort();
    		String build=data(receive).toString();
    		//System.out.println(build); 
    		if(build.contains("NewNeighbour")) {
    				if(neihbour.contains(ra)) {}
    				else {
    				neihbour.add(ra);
    				}
    			   }
    		 else {
    		String checkdsr="";
    		int dest,source,sequence;

    		if((returndsr(build).contains("1"))) {
    			//System.out.println("entered");
    			if(build.contains("Route Request")){
    				
    			//	System.out.println("check seq no    "+ returnmessageseqno(build));
    				if(seq!=returnmessageseqno(build)) {
    					seq=returnmessageseqno(build);
    					if(returnmessagedestination(build)==nodeid) {
    						dsr=true;
    						path=true;
    						System.out.println("Destination Received DSR message :  "+build);
    						String reply="Route Reply";
    						String modifiedmessage="#";
    						modifiedmessage+="1";
    						modifiedmessage+="#";
    						System.out.println(returnmessagedestination(build));
    						modifiedmessage+=Integer.toString(returnmessagedestination(build));
    						modifiedmessage+="#";
    						modifiedmessage+=Integer.toString(returnmessagesource(build));	
    						modifiedmessage+="#";
    						modifiedmessage+=Integer.toString(returnmessageseqno(build));	
    						modifiedmessage+="#";
    						modifiedmessage+=Integer.toString(1);
    						modifiedmessage+="#";
    						modifiedmessage+=reply;
    						modifiedmessage+=returnpath(build);
    						String finalstr="";
    						finalstr+=messageotherthanpath(modifiedmessage);
    						finalstr+="#";
    						finalstr+=returnlastportofmessage(modifiedmessage);
    						
    						finalstr+=removelastport(modifiedmessage);
    						buf = finalstr.getBytes();
    						sendpacket(finalstr,Integer.parseInt(returnlastportofmessage(modifiedmessage)));
    		    		    
    						
    					}
    					else {
    						build+="#";
    						build+=Integer.toString(nodeid);
    						buf = build.getBytes();
    		    	    	
    		    	    	for(int i=0;i<neihbour.size();i++) {
    		    			
    		    			sendpacket(build,neihbour.get(i));
    		    		    }
    					}
    					
    				}
    				
    			}
    			else if(returnmessagechunk(build).equals("Route Reply") || returnmessagepath(build)==1){
    				int d=returnmessagedestination(build);
    					//System.out.println("catches   "+nodeid+"         :    " +d);
    					if(d==this.nodeid) {
    						String name ;
    						System.out.println("Received Route reply   " + nodeid);
    				        /*System.out.println("If you don't want to send reply just enter exit else write message\n");
    						BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    				        try {
								name = reader.readLine();
								if(!name.equals("exit")){
									dsr=true;
		    						path=true;
		    						System.out.println("Received message :  "+build);
		    						String modifiedmessage="#";
		    						modifiedmessage+="1";
		    						modifiedmessage+="#";
				    				modifiedmessage+=Integer.toString(returnmessagedestination(build));
		    						modifiedmessage+="#";
		    						modifiedmessage+=Integer.toString(returnmessagesource(build));	
		    						modifiedmessage+="#";
		    						modifiedmessage+=Integer.toString(returnmessageseqno(build));	
		    						modifiedmessage+="#";
		    						modifiedmessage+=Integer.toString(1);
		    						modifiedmessage+="#";
		    						modifiedmessage+=name;
		    						modifiedmessage+=returnpath(build);
		    						String finalstr="";
		    						finalstr+=messageotherthanpath(modifiedmessage);
		    						finalstr+="#";
		    						finalstr+=returnlastportofmessage(modifiedmessage);
		    						
		    						finalstr+=removelastport(modifiedmessage);
		    						buf = finalstr.getBytes();
		    						sendpacket(finalstr,Integer.parseInt(returnlastportofmessage(modifiedmessage)));
		    		    		    
								}
								else {
									dsr=false;
									floodmessage(returnmessagechunk(build),returnmessagesource(build),returnmessagedestination(build));
								}
								
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}*/
    					
    					}
    					else{
    					
    						
    						boolean desna=false;
    						for(int i=0;i<neihbour.size();i++) {
    							if(d==neihbour.get(i)) {
    								desna=true;
    						
            						String finalstr="";
            						finalstr+=messageotherthanpath(build);
            						finalstr+="#";
            						finalstr+=returnlastportofmessage(build);
            						
            						finalstr+=removelastport(build);
            						buf = finalstr.getBytes();
            						sendpacket(finalstr,d);
            		    		    break;  
    								
    							}
    							
    						}
    						if(desna==false) {
    							String finalstr="";
        						finalstr+=messageotherthanpath(build);
        						finalstr+="#";
        						finalstr+=returnlastportofmessage(build);
        						
        						finalstr+=removelastport(build);
        						buf = finalstr.getBytes();
        						
        						sendpacket(finalstr,Integer.parseInt(returnlastportofmessage(build)));
        		    		    }
    					}
}
    			
}
    		else {
    			   if(returnmessagedestination(build)==nodeid && seq!=returnmessageseqno(build)) {
    				   //if(build.contains("Founded")) {
    					   seq=returnmessageseqno(build);
    				   //System.out.println(idd+"  :  "+ build);}
    				   //else {
    					 // seq=se;
    					   System.out.println("Packet Founded  at : " +nodeid+"  :  "+ build);
    				   //flood("Founded",destination,source);
    				   //}
    			   }
    			   else if(seq!=returnmessageseqno(build)) {
    				   seq=returnmessageseqno(build);
    				   build+="#"+Integer.toString(nodeid);
    				   for(int ii=0;ii<neihbour.size();ii++) {
    				    	InetAddress ip;
    							if(returnmessagesource(build)!=neihbour.get(ii) && neihbour.get(ii)!=ra ) {
    							sendpacket(build,neihbour.get(ii));}
    						
    				   
    			   }
    			   
    		   
    		   }
    			
    			
    		}
    		
    		 receive = new byte[65535];	
    	}
    		}
    	
    }
    public static StringBuilder data(byte[] a) 
    { 
        if (a == null) 
            return null; 
        StringBuilder ret = new StringBuilder(); 
        int i = 0; 
        while (a[i] != 0) 
        { 
            ret.append((char) a[i]); 
            i++; 
        } 
        return ret; 
    } 
    
    String returndsr(String str) {
    	//System.out.println(str);
    	String value="";
    		value+=str.charAt(1);
    		
    	
    	//System.out.println(value);
		return value;
    	
    }
    String returnmessagechunk(String str) {
    	String value="";
    	int i=0,total=0;
    	for(;i<str.length();i++) {
    		if(str.charAt(i)=='#') {
    			total++;
    		}
    		if(total==6) {
    			i++;
    			break;
    		}
    	}
    	for(;i<str.length();i++) {
    		if(str.charAt(i)!='#') {
    			value+=str.charAt(i);
    		}
    		else {
    			break;
    		}
    	}
		return value;
    	
    }
    int returnmessagesource(String str) {
    	String value="";
    	int i=0,total=0;
    	for(;i<str.length();i++) {
    		if(str.charAt(i)=='#') {
    			total++;
    		}
    		if(total==2) {
    			i++;
    			break;
    		}
    	}
    	for(;i<str.length();i++) {
    		if(str.charAt(i)!='#') {
    			value+=str.charAt(i);
    		}
    		else {
    			break;
    		}
    	}
		return Integer.parseInt(value);
    	
    }
    int returnmessagedestination(String str) {
    	String value="";
    	int i=0,total=0;
    	for(;i<str.length();i++) {
    		if(str.charAt(i)=='#') {
    			total++;
    		}
    		if(total==3) {
    			i++;
    			break;
    		}
    	}
    	for(;i<str.length();i++) {
    		if(str.charAt(i)!='#') {
    			value+=str.charAt(i);
    		}
    		else {
    			break;
    		}
    	}
    	//System.out.println(Integer.parseInt(value));
		return Integer.parseInt(value);
    	
    }
    int returnmessagepath(String str) {
    	String value="";
    	int i=0,total=0;
    	for(;i<str.length();i++) {
    		if(str.charAt(i)=='#') {
    			total++;
    		}
    		if(total==5) {
    			i++;
    			break;
    		}
    	}
    	for(;i<str.length();i++) {
    		if(str.charAt(i)!='#') {
    			value+=str.charAt(i);
    		}
    		else {
    			break;
    		}
    	}
		return Integer.parseInt(value);
    	
    }
    int returnmessageseqno(String str) {
    	String value="";
    	int i=0,total=0;
    	for(;i<str.length();i++) {
    		if(str.charAt(i)=='#') {
    			total++;
    		}
    		if(total==4) {
    			i++;
    			break;
    		}
    	}
    	for(;i<str.length();i++) {
    		if(str.charAt(i)!='#') {
    			value+=str.charAt(i);
    		}
    		else {
    			break;
    		}
    	}
		return Integer.parseInt(value);
    	
    }
    String returnpath(String str) {
    	String value="";
   
    	int i=0,total=0;
    	for(;i<str.length();i++) {
    		if(str.charAt(i)=='#') {
    			total++;
    		}
    		if(total==7) {
    			//i++;
    			break;
    		}
    	}
    	if(str.charAt(i)!='#') {
    	value+="#";	
    	}
    	
    	for(;i<str.length();i++) {
    	
    			value+=str.charAt(i);
    		
    	}
		
    	
    	
    	return value;
    }
    String returnlastportofmessage(String str) {
     	String value="";
        int i=0,total=0,total1=0;
    	for(;i<str.length();i++) {
    		if(str.charAt(i)=='#') {
    			total++;
    		}
    	}
    	for(int k=0;k<str.length();k++) {
    		if(str.charAt(k)=='#') {
    			total1++;
    		}
    		else if(total==total1) {
    			value+=str.charAt(k);
    		}
    	}
    	return value;
    	
    }
    String messageotherthanpath(String str) {
    	String value="";
        int i=0,total=0,total1=0;
    	for(;i<str.length();i++) {
    		if(str.charAt(i)=='#') {
    			total++;
    		}
    		if(total<7) {
    		value+=str.charAt(i);
    		}
    		else {
    			break;
    		}
    	}
    	return value;
    }
  String removelastport(String str){
	  String value="";
	  String value1="";
	 
	  value=returnpath(str);
	  //System.out.println("converted   " +returnpath(str));
	  int total=0,total1=0;
	  for(int i=0;i<value.length();i++) {
		if(value.charAt(i)=='#') {
			total++;
		}  
	 }
	  for(int i=0;i<value.length();i++) {
			if(value.charAt(i)=='#') {
				total1++;
			}
			if(total1<total) {
				value1+=value.charAt(i);
			}
		  }
	  return value1;
  }
    
    @Override
	public void run() {
    	receivemessage();
		// TODO Auto-generated method stub
		
	}

/*	public static void main(String args[])throws Exception {
		//a.sendpacket(ii, message, ip);
		InetAddress ip = InetAddress.getLocalHost();
		ArrayList<node> n=new ArrayList<node>();
		for(int i=0;i<=40;i++) {
			node a=new s();
			n.add(a);
		}
		for(int i=0;i<=25;i++) {
			Thread t1 = new Thread(n.get(i));
			t1.start();
		}
		n.get(0).sendpacket("NewNeighbour", 5010);
		n.get(0).sendpacket("NewNeighbour", 5011);
		n.get(0).sendpacket("NewNeighbour", 5005);
		n.get(0).sendpacket("NewNeighbour", 5003);
		
		n.get(1).sendpacket("NewNeighbour", 5003);
		n.get(1).sendpacket("NewNeighbour", 5002);
		n.get(1).sendpacket("NewNeighbour", 5004);
		
		n.get(2).sendpacket("NewNeighbour", 5009);
		n.get(2).sendpacket("NewNeighbour", 5001);
		
		n.get(3).sendpacket("NewNeighbour", 5005);
		n.get(3).sendpacket("NewNeighbour", 5000);
		n.get(3).sendpacket("NewNeighbour", 5004);
		n.get(3).sendpacket("NewNeighbour", 5001);
		
		n.get(4).sendpacket("NewNeighbour", 5008);
		n.get(4).sendpacket("NewNeighbour", 5009);
		n.get(4).sendpacket("NewNeighbour", 5001);
		n.get(4).sendpacket("NewNeighbour", 5003);
		
		n.get(5).sendpacket("NewNeighbour", 5010);
		n.get(5).sendpacket("NewNeighbour", 5000);
		n.get(5).sendpacket("NewNeighbour", 5003);
		n.get(5).sendpacket("NewNeighbour", 5006);
		
		n.get(6).sendpacket("NewNeighbour", 5007);
		
		n.get(12).sendpacket("NewNeighbour", 5010);
		n.get(12).sendpacket("NewNeighbour", 5011);
		n.get(12).sendpacket("NewNeighbour", 5000);
		
		n.get(13).sendpacket("NewNeighbour", 5003);
		n.get(13).sendpacket("NewNeighbour", 5001);
		n.get(13).sendpacket("NewNeighbour", 5014);
		n.get(13).sendpacket("NewNeighbour", 5017);
		
		n.get(14).sendpacket("NewNeighbour", 5017);
		n.get(14).sendpacket("NewNeighbour", 5015);
		n.get(14).sendpacket("NewNeighbour", 5018);
		
		n.get(15).sendpacket("NewNeighbour", 5009);
		n.get(15).sendpacket("NewNeighbour", 5016);
		
		n.get(16).sendpacket("NewNeighbour", 5019);
		n.get(16).sendpacket("NewNeighbour", 5001);
		
		n.get(17).sendpacket("NewNeighbour", 5020);
		
		n.get(18).sendpacket("NewNeighbour", 5020);
		n.get(18).sendpacket("NewNeighbour", 5019);
		
		n.get(19).sendpacket("NewNeighbour", 5022);
		
		n.get(20).sendpacket("NewNeighbour", 5024);
		
		n.get(21).sendpacket("NewNeighbour", 5024);
		n.get(21).sendpacket("NewNeighbour", 5022);
		
		n.get(22).sendpacket("NewNeighbour", 5024);
		
		n.get(23).sendpacket("NewNeighbour", 5024);
		n.get(23).sendpacket("NewNeighbour", 5012);
		
		n.get(25).sendpacket("NewNeighbour", 5008);
		
		
		
		
		

		n.get(0).printneighbour();
		n.get(1).printneighbour();
		n.get(2).printneighbour();
		n.get(3).printneighbour();
		n.get(4).printneighbour();
		n.get(5).printneighbour();
		n.get(6).printneighbour();
		n.get(7).printneighbour();
		n.get(8).printneighbour();
		n.get(9).printneighbour();
		n.get(10).printneighbour();
		

		n.get(0).printneighbour();
		n.get(1).printneighbour();
		n.get(2).printneighbour();
		n.get(3).printneighbour();
		n.get(4).printneighbour();
		n.get(5).printneighbour();
		n.get(6).printneighbour();
		n.get(7).printneighbour();
		n.get(8).printneighbour();
		n.get(9).printneighbour();
		n.get(10).printneighbour();
		
		
		//n.get(0).flood("hello every one",5000, 5009);
		//n.get(1).floodmessage("Send some thing", 5007, 5001);
		while(true) {
		//BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
      String source,dest,trans;
      
       BufferedReader read1 = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Enter destination id\n");
      source=read1.readLine();
      System.out.println("Enter source id\n");
      dest=read1.readLine();
      System.out.println("Enter Message and for DSR Enter Route Request transmission\n");
      int s=Integer.parseInt(source);
      int d=Integer.parseInt(dest);
      if(s>4999 && s-5000>-1 && d>4999) {
      String	name = read1.readLine();
		n.get(s-5000).floodmessage(name, d, s);
		TimeUnit.SECONDS.sleep(5);
      }
	
      

      
		
		}
	
            
	}
		     */
	
	
	}

