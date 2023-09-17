public class Generator implements Runnable {

    static int counter=1;
    int rem;
    static Object lock=new Object();

    public Generator(int rem){
        this.rem=rem;
    }
    @Override
    public void run() {
        synchronized (lock) {
            while (counter < 9) {

                while (counter % 3 != rem) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println(Thread.currentThread().getName() + " " + counter);
                counter++;
                lock.notifyAll();


            }
        }

    }


    public static void main(String[] args) {

        Generator first=new Generator(1);
        Generator second=new Generator(2);
        Generator third=new Generator(0);
        Thread t1=new Thread(first,"Thread T1");
        Thread t2=new Thread(second,"Thread T2");
        Thread t3=new Thread(third,"Thread T3");
        t1.start();
        t2.start();
        t3.start();
    }



}
