package org.apache.hadoop.analysis;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import au.com.bytecode.opencsv.CSVParser;

public class AveTempOfTagMapper extends Mapper<Object,Text,Text,DoubleWritable>{
	
	
	@Override
	protected void map(Object key,Text value,Context context) throws IOException,InterruptedException{
//		if(key.get()>0)
		try{
			CSVParser parser = new CSVParser();
			String[] lines = parser.parseLine(value.toString());		
//			double temp = Double.parseDouble(lines[3]);
//			int tem = Integer.parseInt(lines[3]);
			String tag = "tagindex";
			if(!lines[1].equals(tag) && !lines[1].equals("a")){
//				System.out.println(lines[1] + " " + lines[3]);
				context.write(new Text(lines[1]),new DoubleWritable(Double.parseDouble(lines[3])));
			}
			
		}catch(ParseException e){
			e.printStackTrace();
		}
	}
}
