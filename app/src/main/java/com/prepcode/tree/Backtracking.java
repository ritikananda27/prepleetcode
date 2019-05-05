package com.prepcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Backtracking {

    public int coinChange(int[] coins, int amount) {

        Arrays.sort(coins);
        List<List<Integer>> test = new ArrayList<>();

        for (int j = coins.length - 1; j >= 0; j--) {
            List<Integer> arr = coinChangeImpl(coins, amount, j);
            if (arr != null) {
                test.add(arr);
            }
        }
        return test.size();
    }


    private List<Integer> coinChangeImpl(int[] coins, int amount, int position) {

        int currentCoin = coins[position];

        if (currentCoin == amount) {
            ArrayList<Integer> list = new ArrayList();
            list.add(currentCoin);
            return list;
        }

        List<Integer> leftList = null;
        List<Integer> rightList = null;

        if (amount >= currentCoin) {
            leftList = coinChangeImpl(coins, amount - currentCoin, position);
            if (leftList != null) {
                leftList.add(currentCoin);
            }
        }

        if (position > 0) {
            rightList = coinChangeImpl(coins, amount, position - 1);
        }

        if (leftList == null && rightList == null) {
            return null;
        }

        if (leftList == null) {
            return rightList;
        }
        if (rightList == null) {
            return leftList;
        }

        if (leftList.size() > rightList.size()) {
            return rightList;
        } else {
            return leftList;
        }

    }

}
