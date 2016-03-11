package customer;

import producer.Producer;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

/**
 * Created by kbyyd on 2016/3/11.
 * Customer class is used to find keyword in the files
 * @author gaoyx
 * @version 1.0
 */
public class Customer implements Runnable {

    private BlockingQueue<File> queue;
    private String keyword;

    /**
     * Constructor
     * @param queue the BlockingQueue object save the aim files
     * @param keyword the keyword want to found
     */
    public Customer(BlockingQueue<File> queue, String keyword) {
        this.queue = queue;
        this.keyword = keyword;
    }

    /**
     * search the keyword in files and output the lines
     * @param file the aim file
     * @throws IOException
     */
    public void search(File file) throws IOException {
        try (Scanner in = new Scanner(file)) {
            int line = 0;
            while (in.hasNext()) {
                line++;
                String lineText = in.nextLine();
                if (lineText.contains(keyword)) {
                    System.out.println(file.getPath() + " : " + line + " : " + lineText);
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            boolean finish = false;
            while (!finish) {
                File file = queue.take();
                if (file == Producer.flag) {
                    queue.put(file);
                    finish = true;
                } else search(file);
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
