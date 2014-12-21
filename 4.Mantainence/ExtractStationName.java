package com.bigdata.recommend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// Extract Station Name Information from dataset and write into new file

public class ExtractStationName 
{
	static ArrayList<String> stationname = new ArrayList<String>();

	public static void main( String[] args ) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("dataset/2014_02_bikedata.csv"));
		//  File name is the dataset file(should be modified)
		BufferedWriter bw = new BufferedWriter(new FileWriter("dataset/2014_02_station.txt"));

		String line;
		int initial=0;
		int checknum=0;

		while((line = br.readLine())!=null){
			String[] values =line.split(",",-1);

			if(initial==0)
			{
				stationname.add(values[4]);
				initial=1;
			}

			else if(initial==1)
			{
				checknum=0;
				for(String check:stationname){
					
					if(check.equals(values[11]))
					{
						checknum=1;
					}
				}
				
				if(checknum==0)
				{					
					stationname.add(values[11]); 
				}
			}
		}

		for(String d:stationname) {
			bw.write(d+",25,10"+"\n"); 
			//  write station name+ initial bike number+initial dock number into the txt file
		};

		br.close();
		bw.close();

	}
}
