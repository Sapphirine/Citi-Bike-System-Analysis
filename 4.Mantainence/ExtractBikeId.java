package com.bigdata.recommend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// Extract Bike Id Information from dataset and write into new file

public class ExtractBikeId 
{
	static ArrayList<String> bikeid = new ArrayList<String>();

	public static void main( String[] args ) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("dataset/2014_02_bikedata.csv"));
		//  File name is the dataset file(should be modified)
		BufferedWriter bw = new BufferedWriter(new FileWriter("dataset/2014_02_bikeid.txt"));

		String line;
		int initial=0;
		int checknum=0;

		while((line = br.readLine())!=null){
			String[] values =line.split(",",-1);

			if(initial==0)
			{
				bikeid.add(values[11]);
				initial=1;
			}

			else if(initial==1)
			{
				checknum=0;
				for(String check:bikeid){
					
					if(check.equals(values[11]))
					{
						checknum=1;
					}
				}
				
				if(checknum==0)
				{					
					bikeid.add(values[11]); 
				}
			}
		}

		for(String d:bikeid) {
			bw.write(d+"\n");
		};

		br.close();
		bw.close();

	}
}
