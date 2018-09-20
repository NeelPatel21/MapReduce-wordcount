package mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MyMap extends Mapper<LongWritable, Text, Text, LongWritable> {
    @Override
    protected void map(LongWritable key, Text value, final Context context) throws IOException, InterruptedException {
        String[] ar = value.toString().trim().split(" ");
        for(String w : ar){
            context.write(new Text(w), new LongWritable(1));
        }
    }
}
