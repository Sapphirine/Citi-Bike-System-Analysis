package com.bigdata.recommend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;



public class Bikemaintain 
{
	static ArrayList<String> station = new ArrayList<String>();
	static List<Float> rideminute = new ArrayList<Float>();

	static int date=0;
	
	static int maxusetimes=5000;
	
	static float usetime=0;

	public static void main( String[] args ) throws IOException
	{
		BufferedReader br1 = new BufferedReader(new FileReader("dataset/2014_02_bikedata.csv"));
		BufferedReader br2 = new BufferedReader(new FileReader("dataset/2014_02_bikeid.txt"));
		
//		BufferedWriter bw = new BufferedWriter(new FileWriter("dataset/2014_02_station.txt"));

		String line;
		
		while((line = br2.readLine())!=null){
			String[] values =line.split(",",-1);

            station.add(values[0]);
            rideminute.add((float) 0);
       
		}
		
		
		while((line = br1.readLine())!=null){
			String[] values2  =line.split(",",-1);
			
			// Do SOME adjustment
			values2[1] = values2[1].replaceAll("\"", "");
			values2[2] = values2[2].replaceAll("\"", "");
						
			if(!values2[1].equals("starttime"))
			{
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 
				Date d1 = null;
				Date d2 = null;
			 
				try {
					d1 = format.parse(values2[1]);
					d2 = format.parse(values2[2]);
			 
					DateTime dt1 = new DateTime(d1);
					DateTime dt2 = new DateTime(d2);
			 
					int day=Days.daysBetween(dt1, dt2).getDays();
					int hours=Hours.hoursBetween(dt1, dt2).getHours() % 24;
					int minutes=Minutes.minutesBetween(dt1, dt2).getMinutes() % 60;
					float seconds=Seconds.secondsBetween(dt1, dt2).getSeconds() % 60;
					
                    usetime=day*60*24+hours*60+minutes+seconds/60;
                    
			 
				 } catch (Exception e) {
					e.printStackTrace();
				 }	
			}
		
		// start calculate	
			for(int i=1;i<station.size();i++)
			{  
				float minutes=rideminute.get(i);
			
                if(values2[11].equals(station.get(i)))
            		{
                	     minutes=minutes+usetime;
            	         rideminute.set(i, minutes);
            		}
         
			}
		}
		
		for(int i=1;i<station.size();i++)
		{

		   if(rideminute.get(i)<=0.5*maxusetimes)

		   System.out.println(station.get(i)+",  "+rideminute.get(i)+" condition: Good");   
		   
		   //output if total minutes is lower than its usage age, then its condition is good
		}
	    		
		br2.close();

	}
	
	private static String getCharAt(String strValue, int start, int end) {
		return strValue.substring(start, end);
 
	}
	
}
