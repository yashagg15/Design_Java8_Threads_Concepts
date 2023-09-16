import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyHashMap<K,V> {

    public class Node<K,V>{

        K key;
        V value;

        public Node(K key, V value){
            this.key=key;
            this.value=value;
        }



    }

    private int bucketSize;
    public List<LinkedList<Node<K,V>>> bucket=new ArrayList();

    public MyHashMap(){
        this.bucketSize=16;
        createBucket();
    }

    public void createBucket(){
        for(int i=0;i<bucketSize;i++){
            bucket.add(new LinkedList<>());
        }
    }

    public V put(K key, V value){

        int bucketNo=hashCodeIndex(key);
        int index=getNodeNumber(key,bucketNo);
        if(index!=-1){
            Node node=bucket.get(bucketNo).get(index);
            node.key=key;
            node.value=value;

        }else{
            Node node=new Node(key,value);
            bucket.get(bucketNo).add(node);
        }
        return value;

    }
    public V get(K key){

        int bucketNo=hashCodeIndex(key);
        int index=getNodeNumber(key,bucketNo);
        if(index!=-1){
            Node node=bucket.get(bucketNo).get(index);
            return (V) node.value;
        }
            return null;


    }

    public int getNodeNumber(K key,int index){
        LinkedList<Node<K,V>> linkedList=bucket.get(index);
        for(int i=0;i<linkedList.size();i++){
            if(linkedList.get(i).key==key){
                return i;
            }
        }
        return -1;

    }

    public int hashCodeIndex(K key){
        int hc=key.hashCode();
        int index=hc%16;
       return index;
    }

    public static void main(String[] args) {

        MyHashMap myHashMap=new MyHashMap<Integer,Integer>();
        myHashMap.put(23,34);
        myHashMap.put(34,54);
        myHashMap.put(2,90);

        System.out.println(myHashMap.get(34));
        System.out.println(myHashMap.get(2));


    }
}
