package com.jy.restDemo.restdemo.service;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TestClass {

    class Node {

        int number;

        String str;

        List<Node> list;

        
    }

    enum Operation {

        SL, BY

    }

    @Test
    public void demoTest() {

        int[][] arrays = new int[3][];

        arrays[0] = new int[] {5, 7, 6, 4};
        arrays[1] = new int[] {1, 3, 1, 8, 6};
        arrays[2] = new int[] {9, 2, 3, 5};

        // Create an empty list
        List<Integer> list = new ArrayList<Integer>();

        // Instantiating list using Collections.addAll()
        Collections.addAll(list, 1, 2, 3, 4);

        HashMap<String, Integer> map = new HashMap<>();

        String str = "APpL SL -50.0 -10";

        String[] strs = str.split(" ");

        String stock = strs[0].toUpperCase();

        if (Operation.SL.name().equals(strs[1])) {

            int a = 1;
        }

        int share = 0;
        double share1 = 0;
        try{
            share = Integer.parseInt(strs[2]);
        }catch (NumberFormatException e) {
            share1 = Double.parseDouble(strs[2]);
        }

        double profit = Double.parseDouble(strs[3]);

    }


}
