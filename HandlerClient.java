import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

interface Handler{

    public void setHandler(Handler handler);

    public void processRequest(int num);
}

class  EvenNumberCheckHandler implements Handler{

    private Handler handler;

    @Override
    public void setHandler(Handler handler) {
        this.handler=handler;
    }

    @Override
    public void processRequest(int num) {
        if(num%2==0){
            System.out.println("Yes this is a even number");
        }else{
            handler.processRequest(num);
        }
    }
}

class  OddNumberCheckHandler implements Handler{

private Handler handler;

        @Override
        public void setHandler(Handler handler) {
            this.handler=handler;
        }

        @Override
        public void processRequest(int num) {
            if (num % 2 == 1) {
                System.out.println("Yes this is a odd number");
            }
        }

}

public class HandlerClient {

    public static void main(String[] args) {

        Handler handler1=new EvenNumberCheckHandler();
        Handler handler2=new OddNumberCheckHandler();
        handler1.setHandler(handler2);
        handler1.processRequest(2);
        handler1.processRequest(3);

        List<Integer> li=new ArrayList<>();
        Iterator it=li.iterator();

    }
}
