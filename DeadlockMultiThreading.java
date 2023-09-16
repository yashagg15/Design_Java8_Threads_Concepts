public class DeadlockMultiThreading {

    public static void main(String[] args) {

        //lets create two objects
        DeadlockMultiThreading obj1=new DeadlockMultiThreading();
        DeadlockMultiThreading obj2=new DeadlockMultiThreading();

        //creating two different threads with lock on obj1 and obj2
        Thread t1=new Thread(()->{

            synchronized (obj1) {
                System.out.println("In obj1 lock for thread 1");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (obj2) {
                    System.out.println("In obj2 lock for thread 1");
                }
            }


        });
        Thread t2=new Thread(
                ()->{

                    synchronized (obj2) {
                        System.out.println("In obj2 lock for thread 2");

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        synchronized (obj1) {
                            System.out.println("In obj1 lock for thread 2");
                        }
                    }


                }
        );

        //here thread t1 lock obj1 first and sleeps. thread t2 locks obj2 and sleeps.
        //Now thread1 wait for obj2 to release but it was locked by thread 2.
        //Similarly thread2 wait for obj1 but it was locked by thread1.
        //both will remain dependent on ech other
        t1.start();
        t2.start();

    }
}
