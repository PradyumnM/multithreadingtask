package com.pra.BL;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Logger;

public class CustomRecursiveAction extends RecursiveAction {
	 
    private List<String> workload = new ArrayList<String>();
    private static final int THRESHOLD = 4;
 
    private static Logger logger = 
      Logger.getAnonymousLogger();
 
    public CustomRecursiveAction(List<String> workload) {
        this.workload = workload;
    }
 
    @Override
    protected void compute() {
        if (workload.size() > THRESHOLD) {
            ForkJoinTask.invokeAll(createSubtasks());
        } else {
           processing(workload);
        }
    }
 
    private List<CustomRecursiveAction> createSubtasks() {
        List<CustomRecursiveAction> subtasks = new ArrayList<CustomRecursiveAction>();
 
        String partOne = workload.substring(0, workload.length() / 2);
        String partTwo = workload.substring(workload.length() / 2, workload.length());
 
        subtasks.add(new CustomRecursiveAction(partOne));
        subtasks.add(new CustomRecursiveAction(partTwo));
 
        return subtasks;
    }
 
    private void processing(List<String> work) {
        String result = work.toUpperCase();
        fw.write(line1);
        BufferedWriter fw = new BufferedWriter(new FileWriter("newfile"+Integer.toString(i)));
        logger.info("This result - (" + result + ") - was processed by "
          + Thread.currentThread().getName());
    }
}
