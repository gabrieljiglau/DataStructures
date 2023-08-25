package algoExpert;

import java.util.HashMap;

public class SmallestSubstringContaining {

    public static void main(String[] args) {

        String bigString = "abcd$ef$axb$c$";
        String substring = "$$abf";

        System.out.println(getSmallestSubstringContaining(bigString,substring));
    }


    public static String getSmallestSubstringContaining(String bigString, String substring) {
        HashMap<Character, Integer> charsInSubstring = getCharactersCount(substring);

        int left = 0;
        int right = 0;
        int uniqueCharactersStillNeeded = charsInSubstring.size();
        int minLength = Integer.MAX_VALUE;
        String smallestSubstring = "";

        while (right < bigString.length()) {
            char currentRight = bigString.charAt(right);

            if (charsInSubstring.containsKey(currentRight)) {
                charsInSubstring.put(currentRight, charsInSubstring.get(currentRight) - 1);
                if (charsInSubstring.get(currentRight) == 0) {
                    uniqueCharactersStillNeeded--;
                }
            }

            while (uniqueCharactersStillNeeded == 0) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    smallestSubstring = bigString.substring(left, right + 1);
                }

                char currentLeft = bigString.charAt(left);

                if (charsInSubstring.containsKey(currentLeft)) {
                    charsInSubstring.put(currentLeft, charsInSubstring.get(currentLeft) + 1);
                    if (charsInSubstring.get(currentLeft) > 0) {
                        uniqueCharactersStillNeeded++;
                    }
                }
                left++;
            }

            right++;
        }

        return smallestSubstring;
    }

    private static HashMap<Character,Integer> getCharactersCount(String string){

        HashMap<Character,Integer> map = new HashMap<>();

        int baseCount = 1;
        for(int i = 0; i < string.length(); i++){
            char current = string.charAt(i);
            if(!map.containsKey(current)){
                map.put(current,baseCount);
            } else {
                int oldValue = map.get(current);
                map.replace(current,oldValue,oldValue +1);
            }
        }

        return map;
    }
}
