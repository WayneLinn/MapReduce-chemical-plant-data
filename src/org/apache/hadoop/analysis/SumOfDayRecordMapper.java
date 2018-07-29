package org.apache.hadoop.analysis;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

import au.com.bytecode.opencsv.CSVParser;

public class SumOfDayRecordMapper extends Mapper<Object, Text,Text,IntWritable> {
	
	@Override
	protected void map(Object key,Text value,Context context) throws IOException,InterruptedException{
			try{
			CSVParser parser = new CSVParser();
			String[] lines = parser.parseLine(value.toString());
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
			Date date = formatter.parse(lines[0]);	//取第四行的时间
			formatter.applyPattern("yyyy-MM-dd");	//转换成新的格式
			
			String dateString = formatter.format(date);	//用新的格式储存时间
			context.write(new Text(dateString), new IntWritable(1));
			
		}catch(ParseException e){
			e.printStackTrace();
		}
	}
}
