import java.util.Arrays;

public class Trie {

    public  class TrieNode{
        TrieNode children[];
        boolean isEndOfWord;
        public TrieNode(){
            children=new TrieNode[26];
            Arrays.fill(children,null);
            isEndOfWord=false;
        }

    }

    TrieNode start=new TrieNode();
    TrieNode search=start;

    public void insertKey(String key){

        int len=key.length();
        start=search;

        for(int i=0;i<len;i++){
            int index=key.charAt(i)-'a';
            if(start.children[index]==null){
                start.children[index]=new TrieNode();

            }
            start=start.children[index];
        }
        start.isEndOfWord=true;

    }

    public boolean isPresent(String key){

        start=search;
        int len=key.length();

        for (int i=0;i<key.length();i++){
            int index=key.charAt(i)-'a';
            if(start.children[index]==null)
                return false;
            start=start.children[index];
        }
        if(start.isEndOfWord)
            return true;

        return false;
    }

    public static void main(String[] args) {

        String keys[] = {"the", "a", "there", "answer", "any",
                "by", "bye", "their","anyways"};

        Trie trie=new Trie();

        for(int i=0;i<keys.length;i++){
            trie.insertKey(keys[i]);
        }

        System.out.println("The "+trie.isPresent("the"));
        System.out.println("anywas "+trie.isPresent("anywas"));
        System.out.println("any "+trie.isPresent("any"));


    }
}

