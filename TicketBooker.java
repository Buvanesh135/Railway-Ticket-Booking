package List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TicketBooker {
	static int lowerberth=1;
	static int upperberth=1;
	static int middleberth=1;
	static int RAC=1;
	static int WI=1;
	static List<Integer> lowerberthcount=new ArrayList<>(Arrays.asList(1));
	static List<Integer> middleberthcount=new ArrayList<>(Arrays.asList(1));
	static List<Integer> upperberthcount=new ArrayList<>(Arrays.asList(1));
	static List<Integer> RACcount=new ArrayList<>(Arrays.asList(1));
	static List<Integer> WIcount=new ArrayList<>(Arrays.asList(1));
	static Queue<Integer> RACorder=new LinkedList<>();
	static Queue<Integer> WIorder=new LinkedList<>();
	static Map<Integer,Passenger> PassengerDetails=new HashMap<>();
	
	public static void Bookticket(Passenger p)
	{
		if(p.perferberth.equals("L")&&lowerberth>=1||p.perferberth.equals("M")&&upperberth>=1||p.perferberth.equals("U")&&upperberth>=1)
		{
			if(p.perferberth.equals("L")&&lowerberth>=1)
			{
				p.allotedberth="L";
				p.seatno=lowerberthcount.get(0);
				lowerberthcount.remove(0);
				lowerberth--;
				PassengerDetails.put(p.Passengerid, p);
				System.out.println("Tickets booked successfully... lowerberthgiven");
			}
			else if(p.perferberth.equals("M"))
			{
				p.allotedberth="L";
				p.seatno=middleberthcount.get(0);
				middleberthcount.remove(0);
				middleberth--;
				PassengerDetails.put(p.Passengerid, p);
				System.out.println("Tickets booked successfully... middleberthgiven");
			}
			else if(p.perferberth.equals("U"))
			{
				p.allotedberth="L";
				p.seatno=upperberthcount.get(0);
				upperberthcount.remove(0);
				upperberth--;
				PassengerDetails.put(p.Passengerid, p);
				System.out.println("Tickets booked successfully... upperberthgiven");
			}

		}
		else if(upperberth>=1)
		{
			p.allotedberth="U";
			p.seatno=upperberthcount.get(0);
			upperberthcount.remove(0);
			PassengerDetails.put(p.Passengerid, p);
			upperberth--;
			System.out.println("Tickets booked successfully... upperberthgiven");
			
		}
		else if(middleberth>=1)
		{
			p.allotedberth="M";
			p.seatno=middleberthcount.get(0);
			middleberthcount.remove(0);
			PassengerDetails.put(p.Passengerid, p);
			middleberth--;
			System.out.println("Tickets booked successfully... middleberthgiven");
			
		}
		else if(RAC>=1)
		{
			p.allotedberth="RAC";
			p.seatno=RACcount.get(0);
			RACorder.add(p.Passengerid);
			RACcount.remove(0);
			RAC--;
			PassengerDetails.put(p.Passengerid,p);
			System.out.println("Tickets booked successfully... RAC given..");
		}
		else if(WI>=1)
		{
			p.allotedberth="WI";
			p.seatno=WIcount.get(0);
			WIorder.add(p.Passengerid);
			WIcount.remove(0);
			WI--;
			PassengerDetails.put(p.Passengerid,p);
			System.out.println("Added to WaitingList......");
		}
		else
		{
			System.out.println("ALL seated are fulled....");
			return;
		}
		
	}
	public static void cancelTicket(int passengerId)
	{
		if(!PassengerDetails.containsKey(passengerId))
		{
			System.out.println("please enter valid passenger id");
			return;
		}
		else
		{
			Passenger cancelpassenger=PassengerDetails.remove(passengerId);
		    String allotedberth=cancelpassenger.allotedberth;
		    int seatedno=cancelpassenger.seatno;
		    if(allotedberth.equals("L"))
		    {
		    	lowerberth++;
		    	lowerberthcount.add(seatedno);
		    	if(RACorder.size()==0)
		    	{
		    		System.out.println("Passenger Removed successfully....");
		    		return;
		    	}
		    	Passenger RACtobeAdded=PassengerDetails.get(RACorder.poll());
		    	RACtobeAdded.allotedberth="L";
		    	int seatno=RACtobeAdded.seatno;
		    	RACtobeAdded.seatno=seatedno;
		    	Bookticket(RACtobeAdded);
		    	RACorder.remove(0);
		    	RAC++;
		    	RACcount.add(seatedno);
		    	if(WIorder.size()>0)
		    	{
		    	Passenger Waitingtobeadded=PassengerDetails.get(WIorder.poll());
		    	Waitingtobeadded.allotedberth="RAC";
		    	int seatofwaiter=Waitingtobeadded.seatno;
		    	Waitingtobeadded.seatno=seatno;
		    	RAC--;
		    	RACcount.remove(seatno);
		    	WIcount.add(seatofwaiter);
		    	WIorder.remove(0);
		    	WI++;
		    	}
		    	
		    }
		    else if(allotedberth.equals("M"))
		    {
		    	middleberth++;
		    	middleberthcount.add(seatedno);
		    	if(RACorder.size()==0)
		    	{
		    		System.out.println("Passenger Removed successfully....");
		    		return;
		    	}
		    	Passenger RACtobeAdded=PassengerDetails.get(RACorder.poll());
		    	RACtobeAdded.allotedberth="M";
		    	int seatno=RACtobeAdded.seatno;
		    	RACtobeAdded.seatno=seatedno;
		    	Bookticket(RACtobeAdded);
		    	RACorder.remove(0);
		    	RAC++;
		    	RACcount.add(seatedno);
		    	Passenger Waitingtobeadded=PassengerDetails.get(WIorder.poll());
		    	Waitingtobeadded.allotedberth="RAC";
		    	int seatofwaiter=Waitingtobeadded.seatno;
		    	Waitingtobeadded.seatno=seatno;
		    	RAC--;
		    	RACcount.remove(seatno);
		    	WIcount.add(seatofwaiter);
		    	WIorder.remove(0);
		    	WI++;
		    	
		    }
		    else if(allotedberth.equals("U"))
		    {
		    	upperberth++;
		    	upperberthcount.add(seatedno);
		    	if(RACorder.size()==0)
		    	{
		    		System.out.println("Passenger Removed successfully....");
		    		return;
		    	}
		    	Passenger RACtobeAdded=PassengerDetails.get(RACorder.poll());
		    	RACtobeAdded.allotedberth="L";
		    	int seatno=RACtobeAdded.seatno;
		    	RACtobeAdded.seatno=seatedno;
		    	Bookticket(RACtobeAdded);
		    	RACorder.remove(0);
		    	RAC++;
		    	RACcount.add(seatedno);
		    	Passenger Waitingtobeadded=PassengerDetails.get(WIorder.poll());
		    	Waitingtobeadded.allotedberth="RAC";
		    	int seatofwaiter=Waitingtobeadded.seatno;
		    	Waitingtobeadded.seatno=seatno;
		    	RAC--;
		    	RACcount.remove(seatno);
		    	WIcount.add(seatofwaiter);
		    	WIorder.remove(0);
		    	WI++;
		    	
		    }
		    
		}
	}
	public static void printPassenger()
	{
		if(PassengerDetails.size()==0)
		{
			System.out.println("No Passengers Avaible...");
			return;
		}
		for(Passenger P:PassengerDetails.values())
		{
			System.out.println("passenger name:"+P.name);
			System.out.println("Passenger id:"+P.Passengerid);
			System.out.println("Passenger allotedBerth:"+P.allotedberth);
			System.out.println("Passenger seatno:"+P.seatno);
		} 
	}

}
