package com.practise.problemsolving.searchingandsorting;

public class NumberOfBits {
 
    private void approach1(int n) {
        int count = 0;
        while(n!=0){
            count += (n%2) & 1;
            n>>=1;
        }
        System.out.println(count);
    }

    public static void main(String[] args) {
        NumberOfBits bits = new NumberOfBits();
        int n = 00000000000000000000000000001011;
        bits.approach1(n);
    }    
}