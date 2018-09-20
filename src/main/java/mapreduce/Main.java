package mapreduce;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Main extends Configured implements Tool {
    public static void main(String ... arg) throws Exception{
        int exitCode = ToolRunner.run(new Main(), arg);
        System.exit(exitCode);
    }

    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(getConf());

        job.setMapperClass(MyMap.class);
        job.setReducerClass(MyReduce.class);
        job.setCombinerClass(MyReduce.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        job.setJobName("My Word Count");
        job.setJarByClass(Main.class);
        job.setNumReduceTasks(3);

        Path input = new Path(args[0]);
        Path output = new Path(args[1]);

        FileInputFormat.addInputPath(job,input);
        FileOutputFormat.setOutputPath(job,output);

        return job.waitForCompletion(true)? 0: 1;
    }
}
