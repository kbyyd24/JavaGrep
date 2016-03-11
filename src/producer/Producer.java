package producer;

import java.io.File;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * Created by kbyyd on 2016/3/11.
 */
public class Producer implements Runnable {
    public static File flag = new File("");

    private BlockingQueue<File> queue;
    private File rootDir;

    public Producer (BlockingQueue<File> queue, File rootDir) {
        this.queue = queue;
        this.rootDir = rootDir;
    }

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
