import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool extends Thread {



    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(5);
        for(int i=0;i<7;i++){
//            executorService.execute(new Thread(new WaitNotifyMultiThreading()));
            if(i==5){
                ThreadPool th=new ThreadPool();
                th.setPriority(10);
                th.setName("Priority Thread");
                executorService.submit(th);
            }else {
                ThreadPool th=new ThreadPool();
                th.setPriority(1);
                th.setName("temp");
                executorService.submit(th);
            }
        }
        executorService.shutdown();
        while (!executorService.isTerminated()){}
        System.out.println("All threads execution finished");


    }

    @Override
    public void run() {
        try {
            Thread.sleep(4000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Thread name "+ Thread.currentThread().getName());
    }
}
