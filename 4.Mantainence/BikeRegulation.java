package com.bigdata.recommend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// This code is for bike number regulation

public class BikeRegulation 
{
	static ArrayList<String> station = new ArrayList<String>();
	static List<Integer> borrow = new ArrayList<Integer>();
	static List<Integer> back = new ArrayList<Integer>();
		
	static int date=0;
	
	static int maxusetimes=5000;

	public static void main( String[] args ) throws IOException
	{
		BufferedReader br1 = new BufferedReader(new FileReader("dataset/2014_02_bikedata.csv"));
		BufferedReader br2 = new BufferedReader(new FileReader("dataset/2014_02_station.txt"));

		String line;
		
		while((line = br2.readLine())!=null){
			String[] values =line.split(",",-1);

            station.add(values[0]);
            borrow.add(Integer.parseInt(values[1]));
            back.add(Integer.parseInt(values[2]));          
		}
		
		
		while((line = br1.readLine())!=null){
			String[] values2  =line.split(",",-1);
			
			// Do SOME adjustment
			values2[1] = values2[1].replaceAll("\"", "");
			values2[1] = values2[1].replaceAll("-", "");
			
			if(!values2[1].equals("starttime"))
			{
			if(values2[1].substring(6, 7).equals("0"))
				
		       	{values2[1] = getCharAt(values2[1], 7,8);}
			 
			else
				
			    {values2[1] = getCharAt(values2[1], 6,8);}
			
			date=Integer.parseInt(values2[1]);			
			}
		
		// start calculate	
			for(int i=1;i<station.size();i++)
			{
				int bike1=borrow.get(i);
				int dock1=back.get(i);
			
			    if(date==1)   //choosing the date
			    {
                if(values2[8].equals(station.get(i)))
            		{
                	     bike1=bike1+1;
                	     dock1=dock1-1;
            	         borrow.set(i, bike1);
            	         back.set(i, dock1);
            		}
            
                if(values2[4].equals(station.get(i)))
            		{
                	     
                	bike1=bike1-1;
                	dock1=dock1+1;
                	borrow.set(i, bike1);
                	back.set(i, dock1);
            		}
			    }
			}
		}
		
		for(int i=1;i<station.size();i++)
		{
			System.out.println(station.get(i)+",  "+borrow.get(i)+",  "+back.get(i));  
		}
	    		
		br2.close();

	}
	
	private static String getCharAt(String strValue, int start, int end) {
		return strValue.substring(start, end);
 
	}
	
}
