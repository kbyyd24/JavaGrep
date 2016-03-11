package producer;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * Created by kbyyd on 2016/3/11.
 * Producer class is used to list files and add them into the blocking queue,
 * it realized Runnable interface.
 * @author gaoyx
 * @version 1.0
 */
public class Producer implements Runnable {
    /*a flag in blocking queue*/
    public static File flag = new File("");

    private BlockingQueue<File> queue;
    private File rootDir;

    /**
     * Constructor
     * @param queue a BlockingQueue object to mark files
     * @param rootDir the aim dir of the command
     */
    public Producer (BlockingQueue<File> queue, File rootDir) {
        this.queue = queue;
        this.rootDir = rootDir;
    }

    /**
     * add files into the blocking queue.
     * @param dir the dir of the files path
     * @throws InterruptedException
     */
    public void addToQueue (File dir) throws InterruptedException {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) addToQueue(file);
            else queue.put(file);
        }
    }

    @Override
    public void run() {
        try {
            addToQueue(rootDir);
            queue.put(flag);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
