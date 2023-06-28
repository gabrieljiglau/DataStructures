package algoExpert;

import java.util.ArrayList;
import java.util.List;

public class KnuthMorrisPratt {
    public static void main(String[] args) {

        String s = "abcbabaabcab";
        String substring = "abcab";
        System.out.println(KnuthMorrisPrattAlgorithm(s,substring));
    }

    public static boolean KnuthMorrisPrattAlgorithm(String s,String substring){
        List<Integer> pattern = getLongestPrefixThatIsAlsoASuffix(substring);

        return doesMatch(s,substring,pattern);
    }

    private static boolean doesMatch(String s,String substring,List<Integer> pattern){
        int i = 0;
        int j = 0;

        while(i + substring.length() -j <= s.length()){
            if(s.charAt(i) == substring.charAt(j)){
                if(j == substring.length() -1){
                    return true;
                }
                i++;
                j++;
            } else if(s.charAt(i) != s.charAt(j)){
                j = pattern.get(j -1) +1;
            } else {
                i++;
            }
        }

        return false;
    }

    private static List<Integer> getLongestPrefixThatIsAlsoASuffix(String s){

        List<Integer> prefixList = new ArrayList<>();
        for(int i = 0; i < s.length(); i++){
            prefixList.add(i,-1);
        }

        int i = 1;
        int j = 0;

        while(i < s.length()){
            if(s.charAt(i) == s.charAt(j)){
                i++;
                j++;
            } else if(j > 0){
                j = prefixList.get(j -1) +1;
            } else {
                i++;
            }
        }

        return prefixList;
    }
}
