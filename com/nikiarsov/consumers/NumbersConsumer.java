package com.nikiarsov.consumers;
/*** Represent a number consumer
 * @author Nikolai Arsov
 * @version 1.0
 */

import com.nikiarsov.helpers.*;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class NumbersConsumer implements Runnable {

    private BlockingQueue<Integer> queue;
    private Queue<Integer> concurrentQueue;
    private final int poisonPill;
    private boolean isConcurent = false;

    /** 
     * @args BlockingQueue
     * @args int
     */
    public NumbersConsumer(BlockingQueue<Integer> queue, int poisonPill) {
        this.queue = queue;
        this.poisonPill = poisonPill;
    }

    /** 
     * @args Queue
     * @args int
     */
    public NumbersConsumer(Queue<Integer> concurrentQueue, int poisonPill) {
        this.concurrentQueue = concurrentQueue;
        this.poisonPill = poisonPill;
        this.isConcurent = true;
    }

    @Override
    public void run() {
        try {
            PrintContext print = new PrintContext(new ConsolePrint());
            if (this.isConcurent) {
                Integer number;
                while ((number = concurrentQueue.poll()) != null) {
                    TimeUnit.SECONDS.sleep(2);
                    // Integer number = queue.poll(1, TimeUnit.MILLISECONDS);
                    String queueName = Thread.currentThread().getName();
                    print.executePrint("blue", queueName + " concurrent consumed number is: " + number);
                    if (number.equals(poisonPill)) {
                        print.executePrint("yellow", queueName + " concurrent consumed number is: " + number);
                        return;
                    }
                }
            } else {
                while (true) {
                    TimeUnit.SECONDS.sleep(2);
                    Integer number = queue.take();
                    // Integer number = queue.poll(1, TimeUnit.MILLISECONDS);
                    String queueName = Thread.currentThread().getName();
                    print.executePrint("blue", queueName + " consumed number is: " + number);
                    if (number.equals(poisonPill)) {
                        print.executePrint("yellow", queueName + " exit with poison pill: " + number);
                        return;
                    }
                }
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
