import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class ProducerConsumerProblem {
    public void st() throws InterruptedException {
        Object msg = new Object();
        Thread t1 = new Thread(() -> {
            try {
                long whenwated;
                synchronized (this) {
                    whenwated = System.currentTimeMillis();
                    wait();  // Waiting while holding the lock on msg
                }
                long diff = System.currentTimeMillis() - whenwated;
                System.out.println(diff);
                System.out.println("Executing when notified");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("This is thread 1");
        });

        Thread t2 = new Thread(() -> {
            synchronized (this) {
                try {
                    Thread.sleep(15000);  // Simulate work before notifying
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                notify();  // Notify thread 1 while holding the lock on msg
            }
            System.out.println("This is thread 2");
        });

        t1.start();
        t2.start();
    }

    // producer consumer problem
    public Queue<Integer> queue = new PriorityQueue<>(5);
    Integer count = 5;
    Semaphore fullSlots = new Semaphore(0);
    Semaphore emptySlots = new Semaphore(count);
    Semaphore mutex = new Semaphore(1);

    // Demonstrating with wait and notify
    public void produce(int item) throws InterruptedException {
        synchronized (this){
            while (queue.size()==count){
                wait();
            }
            queue.add(item);
            notify();
            System.out.println("Item has been added"+ item + "Thread name "+ Thread.currentThread().getName());
        }
    }

    public void poll() throws InterruptedException {;
        Integer poll = null;
        synchronized (this) {
            while (queue.isEmpty())
                wait();
            poll = queue.poll();
            System.out.println("Item has been polled" + poll + "Thread name " + Thread.currentThread().getName());
            notify();
        }

    }

    // Demonstrating with Semaphore
    int ct = 0;
    public void produceS(int item) throws InterruptedException {
        emptySlots.acquire();
        mutex.acquire();
        int num = ct++;
        queue.add(num);
        System.out.println("Element added "+ num);
        mutex.release();
        fullSlots.release();

    }
    public void consumeS() throws InterruptedException {
        fullSlots.acquire();
        mutex.acquire();
        int num =  queue.poll();
        System.out.println("Element removed "+ num);
        mutex.release();
        emptySlots.release();
    }


    public static void main(String ... args) throws InterruptedException {
        ProducerConsumerProblem producerConsumerProblem = new ProducerConsumerProblem();
//        main.st();
        Thread thread1 = new Thread(
                ()-> {
                    int[] xyz = {1,2,3,4,5};
                    for(int el:xyz){
                        try {
                            producerConsumerProblem.produceS(el);
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );

        Thread thread2 = new Thread(
                ()-> {
                    int[] xyz = {1,2,3,4,5};
                    for(int el:xyz){
                        try {
                            producerConsumerProblem.consumeS();
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        thread1.start();
        thread2.start();
    }

}