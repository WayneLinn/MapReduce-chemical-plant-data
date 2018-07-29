package org.apache.hadoop.analysis;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

public class AveTempOfTagReducer extends Reducer<Text,DoubleWritable,Text,DoubleWritable>{
	
	@Override
	protected void reduce(Text key,Iterable<DoubleWritable> values,Context context)throws IOException,InterruptedException{
		double sum = 0.00d;
		int count = 0;
		for(DoubleWritable val:values){
			sum += val.get();
			count++;
		}
//		while(iterator.hasNext()){
//			sum += iterator.next().get();
//		}
		double ave = (double) sum/count;
		System.out.println("success");
		context.write(key, new DoubleWritable(ave));
	}
}
