
/*** Present producer-consumer example
 * @author Nikolai Arsov
 * @version 1.0
 */
import com.nikiarsov.consumers.NumbersConsumer;
import com.nikiarsov.producers.NumbersProducer;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerExample {

    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        int BOUND = 10;
        int N_PRODUCERS = 4;
        int N_CONSUMERS = Runtime.getRuntime().availableProcessors();
        int poisonPill = Integer.MAX_VALUE;

        BlockingQueue<Integer> linkedQueue = new LinkedBlockingQueue<>(BOUND);
        Queue<Integer> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();

        String arg = null;
        if (args.length > 0) {
            arg = args[0];
            System.out.println("arg: " + args[0]);
        }

        if (arg == null || arg.equals("-l") ) {
            for (int i = 1; i < N_PRODUCERS; i++) {
                new Thread(new NumbersProducer(linkedQueue, poisonPill)).start();
            }
            for (int j = 0; j < N_CONSUMERS; j++) {
                new Thread(new NumbersConsumer(linkedQueue, poisonPill)).start();
            }
        } else if(arg.equals("-c")) {
            for (int i = 1; i < N_PRODUCERS; i++) {
                new Thread(new NumbersProducer(concurrentLinkedQueue, poisonPill)).start();
            }

            for (int j = 0; j < N_CONSUMERS; j++) {
                new Thread(new NumbersConsumer(concurrentLinkedQueue, poisonPill)).start();
            }
        }
    }

}
