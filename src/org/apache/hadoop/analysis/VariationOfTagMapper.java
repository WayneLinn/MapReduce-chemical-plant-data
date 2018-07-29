package org.apache.hadoop.analysis;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

import au.com.bytecode.opencsv.CSVParser;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class VariationOfTagMapper extends Mapper<Object,Text,Text,DoubleWritable>{
	
	@Override
	protected void map(Object key,Text value,Context context)throws IOException,InterruptedException{
		try{
			CSVParser parser = new CSVParser();
			String[] lines = parser.parseLine(value.toString());	
			String tag = "tagindex";
			if(!lines[1].equals(tag) && !lines[1].equals("a")){
				context.write(new Text(lines[1]),new DoubleWritable(Double.parseDouble(lines[3])));
			}
		}catch(ParseException e){
			e.printStackTrace();
		}
	}
}
