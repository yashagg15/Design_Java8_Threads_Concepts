import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaitNotifyMultiThreading implements Runnable {

    private static int  val=0;
    private int age;
    long a=0;
    long b;

    public void printNumber() throws InterruptedException {


        synchronized (this) {

            long curr=System.currentTimeMillis();
            b=curr-a;
            System.out.println("Diff is " +b);
            a=curr;

            if (this.age == 0) {
                Thread.sleep(5000);
                this.age = this.age + 1;
                this.wait();

            }
            else if(this.age==2){
                this.age+=4;
            }else {
                notify();
                this.age += 1;
            }

        }
    }



    @Override
    public void run() {
        System.out.println("Running Thread name is " + Thread.currentThread().getName());
    }


    public static void main(String[] args) throws InterruptedException {

        WaitNotifyMultiThreading one=new WaitNotifyMultiThreading();

        Thread t1=new Thread(()->{
            try {
                one.printNumber();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2=new Thread(()->{
            try {
                one.printNumber();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();

        Thread.sleep(8000);


        System.out.println(one.age);


        //Executor Thread Pool



    }
}
