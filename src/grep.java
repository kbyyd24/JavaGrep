import customer.Customer;
import producer.Producer;

import java.io.File;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by kbyyd on 2016/3/11.
 */
public class grep {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("parameter number error.");
            System.out.println("please input:");
            System.out.println("java grep <search string> <file/dir>");
            System.exit(-1);
        } else {
            final int CUS_THREADS = 10;
            String keyword = args[0];
            String directory = args[1];

            BlockingDeque<File> queue = new LinkedBlockingDeque<>();
            Producer producer = new Producer(queue, new File(directory));
            new Thread(producer).start();
            for (int i = 0; i < CUS_THREADS; i++) {
                new Thread(new Customer(queue, keyword)).start();
            }
        }
    }
}
