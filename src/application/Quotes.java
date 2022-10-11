// AUTHOR: LINYUN LIU
// DATE: MARCH 15th, 2021

package application;

import java.util.Random;

public class Quotes {
	String Q1 = "Don't worry about what anybody else is going to do.\n"
			+ " The best way to predict the future is to invent it.\n"
			+ "-- Alan Kay";
	
	String Q2 = "No problem should ever have to be solved twice.\n"
			+ "-- Eric S. Raymond";
	
	String Q3 = "It is said that the real winner is the one who\n"
			+ "lives in today but able to see tomorrow.\n"
			+ "-- Juan Meng";
	
	String Q4 = "Fools ignore complexity. Pragmatists suffer it.\n"
			+ "Some can avoid it, Geniuses remove it.\n"
			+ "-- Alan J. Perlis";
	
	String Q5 = "A little learning is a dangerous thing.\n"
			+ "-- Alexander Pope";
	
	String Q6 = "Computer science education cannot make anybody\n" 
			+ "an expert programmer any more than studying brushes\n"
			+ "and pigment can make somebody an expert painter.\n"
			+ "-- Eric Raymond";
	
	String Q7 = "Everybody makes their own fun. If you don't make\n"
			+ "it yourself, it ain't fun -- it's entertainment.\n"
			+ "-- David Mamet";
	
	String Q8 = "The only constant in the world of hi-tech is change.\n"
			+ "-- Mark Ward";
	
	String Q9 = "A journey of a thousand miles must begin with"
			+ "a single step.\n"
			+ "-- Lao­Tzu";
	
	String Q10 = "To the optimist, the glass is half full.\n"
			+ "To the pessimist, the glass is half empty.\n"
			+ "To the engineer, the glass is twice as big as \n"
			+ "it needs to be.";
	
	Random ran = new Random();
	String str;
	String[] list = {Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q7, Q8, Q9, Q10};
	public String getQuotes(){
		return list[ran.nextInt(9)];
	}
	

}
