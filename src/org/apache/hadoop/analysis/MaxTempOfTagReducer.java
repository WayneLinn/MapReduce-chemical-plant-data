package org.apache.hadoop.analysis;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxTempOfTagReducer extends Reducer<Text,DoubleWritable,Text,DoubleWritable>{
		
	@Override
	protected void reduce(Text key,Iterable<DoubleWritable> values,Context context)throws IOException,InterruptedException{
		double max = 0.00d;
		for(DoubleWritable val:values){
			if(val.get()>max){
				max = val.get();
			}
		}
		context.write(key,new DoubleWritable(max));
	}
}
