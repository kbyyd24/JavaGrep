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
            BlockingDeque<File> queue = new LinkedBlockingDeque<>();
        }
    }
}
