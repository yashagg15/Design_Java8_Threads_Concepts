import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class LRUCache {

    static  Deque<Integer> queue = new LinkedList<>();

    static HashSet<Integer> hset=new HashSet<>();

    int capacity=3;

    public void addInQueue(int element){

        if(!hset.contains(element)){

            if(queue.size()==capacity){
                int remove=queue.removeLast();
                hset.remove(remove);
            }
            queue.addFirst(element);
            hset.add(element);



        }else{
            queue.remove(element);
            queue.addFirst(element);

        }

    }

    public static void main(String[] args) {

        LRUCache cache=new LRUCache();
        cache.addInQueue(1);
        cache.addInQueue(2);
        cache.addInQueue(3);
        cache.addInQueue(1);
        cache.addInQueue(4);
        cache.addInQueue(5);

        System.out.println(queue);
    }
}
