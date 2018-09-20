package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class InputGenerator {

    public static void main(String ... arg) throws IOException {
        System.out.println("enter number of distinct words...");
        Scanner sc = new Scanner(System.in);
        int nw = sc.nextInt();

        System.out.println("total number of words...");
        long size = sc.nextLong();

        System.out.println("filename :- ");
        String fileName = "";
        for(fileName = sc.nextLine(); fileName.trim().isEmpty(); fileName = sc.nextLine());
        File f = new File(fileName);
        f.createNewFile();
        f.setWritable(true);
        try(FileOutputStream fos = new FileOutputStream(fileName)){
            makeList(new PrintWriter(fos), generatRandomWords(nw), size);
        };
    }

    public static List<String> generatRandomWords(int count){
        Random r = new Random();
        List<String> l = new ArrayList<String>();
        for(int i=0; i<count; i++){
            StringBuilder sb = new StringBuilder();
            do{
                sb.append((char)(r.nextInt(26)+97));
            }while (r.nextBoolean()||r.nextBoolean());
            l.add(sb.toString());
        }
        return l;
    }

    public static void makeList(PrintWriter os, List<String> words, long size){
        Random r = new Random();
        for(long i = 0; i < size; i++){
            os.print(words.get(r.nextInt(words.size())));
            if(r.nextBoolean() && r.nextBoolean())
                os.println();
            else
                os.print(" ");
            if(i%100000 == 0)
                os.flush();
        }
    }
}
