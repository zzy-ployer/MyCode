package com.neuedu.douban;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Two {

	public static void myRun() throws IOException {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://master:9000");
		FileSystem fs = FileSystem.get(conf);

		Job job = Job.getInstance(conf, "my two");
		job.setJarByClass(Two.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		Path src = new Path("/douban/data1");
		Path dst = new Path("/douban/data2");

		fs.delete(dst, true);
		FileInputFormat.setInputPaths(job, src);
		FileOutputFormat.setOutputPath(job, dst);

		job.setMapperClass(MyMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setReducerClass(MyReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		try {
			job.waitForCompletion(true);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static class MyMapper extends Mapper<Object, Text, Text, IntWritable> {
		private Text outKey = new Text();
		private IntWritable outValue = new IntWritable(1);// 输出value,计数

		@Override
		protected void map(Object key, Text value, Mapper<Object, Text, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {
			String[] line = value.toString().split("\\s+")[1].split(";");
			for (int i = 0; i < line.length; i++) {
				for (int j = 0; j < line.length; j++) {
					outKey.set(line[i].split(":")[0] + "," + line[j].split(":")[0]);
					context.write(outKey, outValue);
				}
			}
		}
	}

	public static class MyReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
//		private Text outKey = new Text();
		private IntWritable outValue = new IntWritable(1);

		@Override
		protected void reduce(Text key, Iterable<IntWritable> values,
				Reducer<Text, IntWritable, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable intWritable : values) {
				sum += intWritable.get();
			}

			outValue.set(sum);
			context.write(key, outValue);
		}
	}
}
