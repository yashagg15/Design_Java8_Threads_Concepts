public class PrintEvenOdd {

    private static int counter=1;
    public void printEvenNumber(int N) throws InterruptedException {

        synchronized (this){

            while(counter<N){

                if(counter%2==1){
                    this.wait();
                }
                System.out.println(counter);
                counter++;
                notify();


            }

        }

    }

    public void printOddNumber(int N) throws InterruptedException {

        synchronized (this){
            while(counter<N){
                if(counter%2==0){
                    this.wait();
                }
                System.out.println(counter);
                counter++;
                notify();

            }

        }

    }

    public static void main(String[] args) {

        PrintEvenOdd obj=new PrintEvenOdd();

        Thread t1=new Thread(()->{
            try {
                obj.printEvenNumber(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2=new Thread(()->{
            try {
                obj.printOddNumber(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();
    }

}

