package com.neuedu.douban;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Three {

	public static void myRun() throws IOException {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://master:9000");
		FileSystem fs = FileSystem.get(conf);

		Job job = Job.getInstance(conf, "my three");
		job.setJarByClass(Two.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		Path src = new Path("/douban/data2");
		Path dst = new Path("/douban/data3");

		fs.delete(dst, true);
		FileInputFormat.setInputPaths(job, src);
		FileOutputFormat.setOutputPath(job, dst);

		job.setMapperClass(MyMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setReducerClass(MyReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		job.setPartitionerClass(MyPartitioner.class);
		job.setNumReduceTasks(4);
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
			String[] a = value.toString().split("\\s+");
			String[] b = a[0].split(",");
			outKey.set(b[0]);
			outValue.set(b[1] + ":" + a[1]);
			context.write(outKey, outValue);
		}
	}

	public static class MyPartitioner extends Partitioner<Text, Text> {
		@Override
		public int getPartition(Text key, Text value, int numPartitions) {
			return (key.hashCode() & Integer.MAX_VALUE) % numPartitions;
		}
	}

	public static class MyReducer extends Reducer<Text, Text, Text, Text> {
//		private Text outKey = new Text();
		private Text outValue = new Text();

		@Override
		protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			StringBuffer s = new StringBuffer();
			for (Text t : values) {
				s.append(t.toString()).append(";");
			}
			String s2 = s.toString();
			s2 = s2.substring(0, s2.length() - 1);
			outValue.set(s2);
			context.write(key, outValue);
		}
	}
}
