import java.util.*;
import java.util.concurrent.TimeUnit;
import java.net.*;
import java.io.*;

public class Driver{

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
	Scanner cases=new Scanner(System.in);
	System.out.println("Enter Case number ");
		String desiredCase="";
		desiredCase=cases.nextLine();
		if(desiredCase.equals("1"))
		{
			
				//a.sendpacket(ii, message, ip);
				InetAddress ip = InetAddress.getLocalHost();
				ArrayList<Node1> n=new ArrayList<Node1>();
				for(int i=0;i<=40;i++) {
					Node1 a=new Node1();
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
		      System.out.println("Enter source  id\n");
		      source=read1.readLine();
		      System.out.println("Enter destination id\n");
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
	if(desiredCase.equals("2"))
	{
		String one;
		String two;
		String th;
		int count=0;
		Random rand=new Random();
		boolean flood=false;
		Node2[] array = new Node2[40];
		for(int i=0;i<40;i++)
			array[i]=new Node2(i);
		
		for(int i=0;i<40;i++)
		array[i].addNeighbour(array);
		for(int i=0;i<40;i++)
			{
			array[i].populatePorts(array);			
			array[i].listenFlooding();
			}
		for(int i=0;i<40;i++)
			array[i].printNeighbours();
		
		Scanner in=new Scanner(System.in);
		String input="";
		
		while(true)
		{
		System.out.println("Case 2");
		System.out.println("Please select the option ");
		System.out.println("Enter 0 to exit");
		System.out.println("Low or high message rate?");
		input=in.nextLine();
		if(input.equals("0"))
			break;
		if(input.equalsIgnoreCase("LOW"))
			array[0].setLow();
		else
			array[0].setHigh();
		
		System.out.println("Flooding or DSR?");
		input=in.nextLine();
		if(input.equals("0"))
			break;
		if(input.equalsIgnoreCase("FLOODING"))
			flood=true;
		else if(input.equalsIgnoreCase("DSR"))
			flood=false;
			
	if(count>7)
	{
	for(int i=0+count;i<6+count;i++)
		{
		int j=rand.nextInt(38)+1;		//any of the 40 nodes
		array[j].mobility();
		array[j].addNeighbour(array);
		}
	
	for(int i=0;i<40;i++)
		array[i].populatePorts(array);
	for(int i=0;i<40;i++)
	array[i].printNeighbours();
	}
	if(count>7)//for mobility
		count=0;
	

		//for(int i=0;i<40;i++)
	//	array[i].printNeighbours();	
		
		
	/*	System.out.println("Enter sender like so #number: ");
		two=in.nextLine();
		if(input.equals("0"))
			break;
		two='#'+two;
		System.out.println("Enter reciever like so #number: ");
		one=in.nextLine();
		if(input.equals("0"))
			break;
		one='#'+one;	
		*/
			
		
		System.out.println("Enter message : ");
		th=in.nextLine();
		if(input.equals("0"))
			break;
		
		if(array[0].low)
		{
			for(int i=0;i<8;i++)
			{
				int j=rand.nextInt(38)+1;		//any of the 40 nodes
				int k=rand.nextInt(38)+1;
				if(k==j)
					k=rand.nextInt(38)+1;
				if(!flood)
				{
					array[0].Dsr(th, array[j].getNode2number(), array[k].getNode2number(), array);
					TimeUnit.SECONDS.sleep(5);
				}	
				if(flood)
				array[0].sendMessage(array, array[j].getNode2number(), array[k].getNode2number(), th);		
			}
		}
		if(!array[0].low)
		{
			for(int i=0;i<25;i++)
			{
				int j=rand.nextInt(38)+1;		//any of the 40 nodes
				int k=rand.nextInt(38)+1;
				if(k==j)
					k=rand.nextInt(38)+1;
				if(!flood)
				{array[0].Dsr(th, array[j].getNode2number(), array[k].getNode2number(), array);	
				TimeUnit.SECONDS.sleep(5);
				}if(flood)
				array[0].sendMessage(array, array[j].getNode2number(), array[k].getNode2number(), th);		
			}
		}

		if(array[0].low)
			count+=7;
		else
			count+=25;
		
		TimeUnit.SECONDS.sleep(2);
		count++;
		}
	}
	if(desiredCase.equals("3"))
	{
		String one;
		String two;
		String th;
		int count=0;
		Random rand=new Random();
		boolean flood=false;
		Node[] array = new Node[40];
		for(int i=0;i<40;i++)
			array[i]=new Node(i);
		
		for(int i=0;i<40;i++)
		array[i].addNeighbour(array);
		for(int i=0;i<40;i++)
			{
			array[i].populatePorts(array);			
			array[i].listenFlooding();
			}
		for(int i=0;i<40;i++)
			array[i].printNeighbours();
		
		Scanner in=new Scanner(System.in);
		String input="";
		
		while(true)
		{
		System.out.println("Case 3");
		System.out.println("Please select the option ");
		System.out.println("Enter 0 to exit");
		System.out.println("Low or high message rate?");
		input=in.nextLine();
		if(input.equals("0"))
			break;
		if(input.equalsIgnoreCase("LOW"))
			array[0].setLow();
		else
			array[0].setHigh();
		
		System.out.println("Flooding or DSR?");
		input=in.nextLine();
		if(input.equals("0"))
			break;
		if(input.equalsIgnoreCase("FLOODING"))
			flood=true;
		else if(input.equalsIgnoreCase("DSR"))
			flood=false;
			
	if(count>7)
	{
	for(int i=0+count;i<6+count;i++)
		{
		int j=rand.nextInt(38)+1;		//any of the 40 nodes
		array[j].mobility();
		array[j].addNeighbour(array);
		}
	
	for(int i=0;i<40;i++)
		array[i].populatePorts(array);
	for(int i=0;i<40;i++)
	array[i].printNeighbours();
	}
	if(count>7)//for mobility
		count=0;
	

		//for(int i=0;i<40;i++)
	//	array[i].printNeighbours();	
		
		
	/*	System.out.println("Enter sender like so #number: ");
		two=in.nextLine();
		if(input.equals("0"))
			break;
		two='#'+two;
		System.out.println("Enter reciever like so #number: ");
		one=in.nextLine();
		if(input.equals("0"))
			break;
		one='#'+one;	
		*/
			
		
		System.out.println("Enter message : ");
		th=in.nextLine();
		if(input.equals("0"))
			break;
		
		if(array[0].low)
		{
			for(int i=0;i<13;i++)
			{
				int j=rand.nextInt(38)+1;		//any of the 40 nodes
				int k=rand.nextInt(38)+1;
				if(k==j)
					k=rand.nextInt(38)+1;
				if(!flood)
				{
					array[0].Dsr(th, array[j].getNodenumber(), array[k].getNodenumber(), array);
					TimeUnit.SECONDS.sleep(3);
				}	
				if(flood)
				array[0].sendMessage(array, array[j].getNodenumber(), array[k].getNodenumber(), th);		
			}
			System.out.println("Count : "+array[0].count);
			System.out.println("Count2 : "+array[0].count2);
		}
		if(!array[0].low)
		{
			for(int i=0;i<35;i++)
			{
				int j=rand.nextInt(38)+1;		//any of the 40 nodes
				int k=rand.nextInt(38)+1;
				if(k==j)
					k=rand.nextInt(38)+1;
				if(!flood)
				{array[0].Dsr(th, array[j].getNodenumber(), array[k].getNodenumber(), array);	
				TimeUnit.SECONDS.sleep(3);
				}if(flood)
				array[0].sendMessage(array, array[j].getNodenumber(), array[k].getNodenumber(), th);		
			}
			System.out.println("Count : "+array[0].count);
			System.out.println("Count2 : "+array[0].count2);
		}

		if(array[0].low)
			count+=7;
		else
			count+=25;
		
		TimeUnit.SECONDS.sleep(2);
		count++;
		}
	}//if three ends
		}
	
}

