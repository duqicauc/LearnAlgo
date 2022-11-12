package org.javaadu.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TongWeiWord {

    public static void main(String[] args) {
        System.out.println(isAnagram("a", "ab"));
    }

    public static boolean isAnagram(String s, String t) {
        Map<Character,Integer> sMap = new HashMap<>();
        Map<Character,Integer> tMap = new HashMap<>();

        for(char c : s.toCharArray()) {
            Integer temp = sMap.get(c);
            if(temp == null) {
                temp = 0;
            }
            temp++;
            sMap.put(c, temp);
        }

        for(char c : t.toCharArray()) {
            Integer temp = tMap.get(c);
            if(temp == null) {
                temp = 0;
            }
            temp++;
            tMap.put(c, temp);
        }

        for(Map.Entry<Character,Integer> entry: sMap.entrySet()) {
            Integer temp = tMap.get(entry.getKey());
            if(!Objects.equals(entry.getValue(), temp)) {
                return false;
            }
        }
        for(Map.Entry<Character,Integer> entry: tMap.entrySet()) {
            Integer temp = sMap.get(entry.getKey());
            if(!Objects.equals(entry.getValue(), temp)) {
                return false;
            }
        }
        return true;
    }
}
