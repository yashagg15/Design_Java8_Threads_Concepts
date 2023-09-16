import java.util.Stack;

public class UrlShortener {

    //shortening url with base62 version

    String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    Integer baseConversion=62;

    public String shortenUrl(Integer id){
        Stack<Integer> stack=new Stack<>();

        while(id!=0){
            int modulo=id%baseConversion;
            stack.push(modulo);
            id=id/baseConversion;
        }

        String shortUrl="";
        while(!stack.isEmpty()){
            int pop=stack.pop();
            shortUrl+=str.charAt(pop);
        }
        return shortUrl;

    }

    public  Integer shortToLongUrl(String shortUrl){

        Integer res=0;
        for(int j=0;j<shortUrl.length();j++){
            int index=str.indexOf(shortUrl.charAt(j));
            res=res*62+index;

        }
        return res;
    }
    public static void main(String[] args) {

        Integer id=12345;
        UrlShortener urlShortener=new UrlShortener();
        String shorturl=urlShortener.shortenUrl(id);

        System.out.println(shorturl);

        Integer returnId=urlShortener.shortToLongUrl(shorturl);
        System.out.println(returnId);


    }
}
