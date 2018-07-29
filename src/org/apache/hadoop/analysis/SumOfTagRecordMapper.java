package org.apache.hadoop.analysis;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import au.com.bytecode.opencsv.CSVParser;

public class SumOfTagRecordMapper extends Mapper<Object, Text, Text,IntWritable> {
	
	@Override
	protected void map(Object key,Text value,Context context) throws IOException,InterruptedException{
		try{
			CSVParser parser = new CSVParser();
			String[] lines = parser.parseLine(value.toString());
			if(!lines[1].equals("tagindex") && !lines[1].equals("a")){
				context.write(new Text(lines[1]), new IntWritable(1));
			}
		}catch(ParseException e){
			e.printStackTrace();
		}
	}

}
