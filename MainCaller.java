package List;

import java.util.Scanner;

public class MainCaller {
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		boolean f=true;
		while(f)
		{
			System.out.println("1.bookTickets\n2.cancelTicket\n3.printPasseneger\n4.exit");
			int ch=sc.nextInt();
			switch(ch)
			{
			  case 1:
				  System.out.print("enter passenger Name:");
				  String passengerName=sc.next();
				  System.out.print("enter passenger perferberth:");
				  String passengerperferberth=sc.next();
				  Passenger p=new Passenger(passengerName,passengerperferberth);
				  TicketBooker.Bookticket(p);
				  break;
			  case 2:
				  System.out.println("enter passenger id:");
				  int passid=sc.nextInt();
				  TicketBooker.cancelTicket(passid);
				  break;
			  case 3:
				   TicketBooker.printPassenger();
				   break;
			  case 4:
				   f=false;
				   break;
			}
		}
	}

}
