package String;

public class Permutation{

    public static void main(String... s){

        permutation("" , "xyz");
    }

    public static void permutation(String prefix, String str){
        if(str.isEmpty()){
            System.out.println(prefix);
            return;
        }

        for(int i = 0 ; i < str.length(); i++){
            String newpref = prefix + str.charAt(i);
            String result = str.substring(0, i) + str.substring(i + 1);
            permutation(newpref, result);
        }
    }

}