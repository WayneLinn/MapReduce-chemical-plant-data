package org.apache.hadoop.analysis;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

public class SumOfTagRecordReducer extends Reducer<Text, IntWritable,Text,IntWritable> {
	
	@Override
	protected void reduce(Text key,Iterable<IntWritable> values,Context context)throws IOException,InterruptedException{
		int count = 0;
		for(IntWritable val:values){
			count++;
		}
		context.write(key, new IntWritable(count));
	}

}
