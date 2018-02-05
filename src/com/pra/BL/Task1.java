package com.pra.BL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Logger;

import com.pra.parser.CSVParser;

public class Task1 extends RecursiveAction {
	private static final int SEQUENTIAL_THRESHOLD = 50000;
	 private  List<String> data;
	 int fileNo=0;

	  public Task1(List<String> data, int i) {
	    this.data = data;
	    this.fileNo=i;
	  }

	public static void main(String[] args) throws IOException {
	//	FileInputStream fileInputStream = new FileInputStream(
       ////         new File("sample4.txt"));
//FileChannel fileChannel = fileInputStream.getChannel();
		
	//	ForkJoinPool commonPool = ForkJoinPool.commonPool();
		Properties prop = new Properties();
		InputStream input = null;
		////input = new FileInputStream("config.properties");
	//	prop.load(input);
		int j=0;
		int n=50000;
		String filename=prop.getProperty("/home/pradyumnm/Downloads/Multithreading_Task1_Books.csv");
		List<String> datalist = new ArrayList<String>();
		CSVParser parser = new CSVParser();
		int numberOfProcessors = Runtime.getRuntime().availableProcessors();
		datalist = CSVParser.getCSVData("/home/pradyumnm/Downloads/Multithreading_Task1_Books.csv");
		System.out.println(System.currentTimeMillis()+"  rt "+datalist.size());
/*	for (int i=0;i<datalist.size();i=i+n) {
			BufferedWriter fw = new BufferedWriter(new FileWriter("/home/pradyumnm/Downloads/tsk/"+"tsk"+ Integer.toString(i)+".csv"));
			
			for(j=i;j<i+n&&j<datalist.size();j++) {
				fw.write(datalist.get(j));
			}
	}	*/

		 ForkJoinPool pool = new ForkJoinPool();
		 // Task1 task = new Task1(datalist,0);
		 //  pool.invoke(task);	
		
		
	System.out.println(System.currentTimeMillis()+"    "+datalist.size());
	}

	@Override
	protected void compute() {
	    if (data.size() <= SEQUENTIAL_THRESHOLD) { // base case
	         computeDirectly();
	      } else { // recursive case
	        // Calcuate new range
	        int mid = data.size() - SEQUENTIAL_THRESHOLD;
	        Task1 firstSubtask =
	            new Task1(data.subList(0, mid),data.size()/SEQUENTIAL_THRESHOLD);
	        Task1 secondSubtask =
	            new Task1(data.subList(mid, data.size()),data.size()/SEQUENTIAL_THRESHOLD+1);

	        
	        invokeAll(firstSubtask, secondSubtask);
	      }
		
	}

	private void computeDirectly() {
		BufferedWriter fw;
		try {
			fw = new BufferedWriter(new FileWriter("/home/pradyumnm/Downloads/tsk/"+"tsk"+ Integer.toString(fileNo)+".csv"));
			for (int i=0;i<data.size();i++)
			{
				fw.write(data.get(i));
				fw.newLine();
            }
			fw.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
