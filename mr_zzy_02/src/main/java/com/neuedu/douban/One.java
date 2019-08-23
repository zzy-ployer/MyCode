package com.neuedu.douban;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class One {
	public static void myRun() throws IOException {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://master:9000");
		FileSystem fs = FileSystem.get(conf);

		Job job = Job.getInstance(conf, "my one");
		job.setJarByClass(One.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		Path src = new Path("/douban/data");
		Path dst = new Path("/douban/data1");

		fs.delete(dst, true);
		FileInputFormat.setInputPaths(job, src);
		FileOutputFormat.setOutputPath(job, dst);

		job.setMapperClass(MyMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setReducerClass(MyReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		try {
			job.waitForCompletion(true);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static class MyMapper extends Mapper<Object, Text, Text, Text> {
		private Text outKey = new Text();
		private Text outValue = new Text();

		@Override
		protected void map(Object key, Text value, Mapper<Object, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			String[] arr = value.toString().split(",");
			outKey.set(arr[1]);
			outValue.set(arr[2] + ":" + arr[4]);
			context.write(outKey, outValue);
		}
	}

	public static class MyReducer extends Reducer<Text, Text, Text, Text> {
//		private Text outKey = new Text();
		private Text outValue = new Text();

		@Override
		protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			String temp = "";
			for (Text value : values) {
				temp += value.toString() + ";";
				outValue = new Text(temp.substring(0, temp.length() - 1));
			}
			context.write(key, outValue);
		}
	}

}
