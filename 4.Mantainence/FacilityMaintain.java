package com.bigdata.recommend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class FacilityMaintain 
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
		
//		BufferedWriter bw = new BufferedWriter(new FileWriter("dataset/2014_02_station.txt"));

		String line;
		
		while((line = br2.readLine())!=null){
			String[] values =line.split(",",-1);

            station.add(values[0]);
            borrow.add(0);
            back.add(0);          
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
			
//			    if(date>=1 && date<=5)
//			    {
                if(values2[8].equals(station.get(i)))
            		{
                	     
            	         back.set(i, dock1+1);
            		}
            
                if(values2[4].equals(station.get(i)))
            		{
                	     
            	         borrow.set(i, bike1+1);
            		}
//			    }
			}
		}
		
		for(int i=1;i<station.size();i++)
		{
		   int usage=borrow.get(i)+back.get(i);
		   if(usage>=0.8*maxusetimes)
		   System.out.println(station.get(i)+",  "+usage+":  "+"Require maintaince");
		   //  If usage is larger than 0.8, than it requires maintaince
		   
//		   else if(usage>0.5*maxusetimes && usage <=0.9*maxusetimes)
//		   System.out.println(station.get(i)+","+usage+": "+"In OK condition");   
//		   else
//		   System.out.println(station.get(i)+","+usage+": "+"Require maintaince");   
		}
	    		
		br2.close();

	}
	
	private static String getCharAt(String strValue, int start, int end) {
		return strValue.substring(start, end);
 
	}
	
}
