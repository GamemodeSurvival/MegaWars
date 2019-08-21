package net.keylon.me.utils;

import java.util.ArrayList;
import java.util.Random;

public class RandomNumberGenerator {
    
    private Random rand;
    private Integer numbercount;
    private Integer bound;
    public RandomNumberGenerator(Integer numbercount, Integer bound) {
        rand = new Random();
        this.numbercount = numbercount;
        this.bound = bound;
    }
     
    public ArrayList<Integer> getNumbers() {
        ArrayList<Integer> listOfNumbers = new ArrayList<Integer>();
        if (numbercount >= bound) {
            return null;
        }
        while (listOfNumbers.size() <= numbercount) {
            Integer randomNumber = rand.nextInt(bound);
            if (listOfNumbers.contains(randomNumber)) {
                continue;
            } else {
                listOfNumbers.add(randomNumber);
            }
        }
        return listOfNumbers;
    }
    

}