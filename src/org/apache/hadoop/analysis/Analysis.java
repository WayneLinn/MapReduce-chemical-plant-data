package org.apache.hadoop.analysis;



import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class Analysis {
	public static void main(String[] args) throws Exception{
		

		
		Configuration conf = new Configuration();	//程序运行时的参数
		String[] otherArgs = new GenericOptionsParser(conf,args).getRemainingArgs();
		if(otherArgs.length != 2){
			System.out.println("Usage:incorporation<in><out>");
			System.exit(2);
		}
		
		Job job = new Job(conf, "Analysis");	//设置环境参数
		job.setJarByClass(Analysis.class);	//设置整个程序的类名
		job.setMapperClass(VariationOfTagMapper.class);	//添加MyMapper类
		job.setReducerClass(VariationOfTagReducer.class);	//添加MyReducer类
		job.setOutputKeyClass(Text.class);	//设置输出键类型
		job.setOutputValueClass(DoubleWritable.class);	//设置输出值类型
	
		
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		
		System.exit(job.waitForCompletion(true)?0:1);
		
	}
}
