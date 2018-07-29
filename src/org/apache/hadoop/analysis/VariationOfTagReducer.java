package org.apache.hadoop.analysis;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

public class VariationOfTagReducer extends Reducer<Text,DoubleWritable,Text,DoubleWritable>{

	@Override
	protected void reduce(Text key,Iterable<DoubleWritable> values,Context context)throws IOException,InterruptedException{
		int count = 0;	//N
		double sum = 0.00d;	//总和
		List<String> list = new ArrayList<String>();
		for(DoubleWritable val:values){
			list.add(String.valueOf(val.get()));
			sum += val.get();
			count++;
		}
		double mean = sum/count;
		double var = 0.00d;
		for(int i=0;i<list.size();i++){
			double pow = Math.pow(Double.parseDouble(list.get(i)) - mean,2);
			var += pow;
		}
		double variation = var/(count-1);
		context.write(key,new DoubleWritable(variation));
	}
}
