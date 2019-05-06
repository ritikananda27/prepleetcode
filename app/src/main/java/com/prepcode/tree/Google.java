package com.prepcode.tree;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.*;

public class Google {

    public List<String> summaryRanges(int[] nums) {

        int left = 0;

        List<String> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {

            if (i == nums.length - 1) {
                res.add(createString(nums, left, i));
            } else {
                int num = nums[i];
                int next = num + 1;
                if (nums[i + 1] != next) {
                    String sub = createString(nums, left, i);
                    res.add(sub);
                    left = i + 1;
                }

            }
        }


        return res;
    }

    private String createString(int[] nums, int start, int end) {
        StringBuilder sb = new StringBuilder();
        sb.append(nums[start]);
        if (end > start) {
            sb.append("->");
            sb.append(nums[end]);
        }
        return sb.toString();
    }

    public List<Integer> findDuplicates(int[] nums) {

        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (set.contains(num)) {
                list.add(num);
            } else {
                set.add(num);
            }
        }

        return list;
    }

    /*Get All combinations from point A to point B */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public int findLadders(String beginWord, String endWord, List<String> wordList) {

        if (wordList.isEmpty()) return 0;
        if (!wordList.contains(endWord)) return 0;

        Map<String, List<String>> map = preProcessWords(wordList);

        Set<String> visited = new HashSet<>();
        List<String> tempList = new ArrayList<>();

        tempList.add(beginWord);
        visited.add(beginWord);

        List<List<String>> finalRes = new ArrayList<>();
        getAllLists(beginWord, endWord, map, visited, finalRes, tempList);

        return finalRes.size();
    }

    private void getAllLists(String start, String end, Map<String, List<String>> map, Set<String> set, List<List<String>> finalList, List<String> temp) {

        if (end.equals(start)) {
            if (finalList.isEmpty()) {
                finalList.add(new ArrayList<>(temp));
            } else {
                int minSize = finalList.get(0).size();
                if (temp.size() < minSize) {
                    finalList.clear();
                    finalList.add(new ArrayList<>(temp));
                } else if (temp.size() == minSize) {
                    finalList.add(new ArrayList<>(temp));
                }
            }
            return;
        }


        List<String> children = findOneDistanceApartChildren(start, map);
        for (String s : children) {
            if (set.add(s)) {
                temp.add(s);
                getAllLists(s, end, map, set, finalList, temp);
                temp.remove(s);
                set.remove(s);
            } else {
                continue;
            }

        }
        set.remove(start);
    }

    private List<String> findOneDistanceApartChildren(String word, Map<String, List<String>> map) {

        List<String> res = new ArrayList<>();
        int len = word.length();

        for (int i = 0; i < len; i++) {
            String nw = word.substring(0, i) + "*" + word.substring(i + 1);
            if (map.containsKey(nw)) {
                List<String> resList = map.get(nw);
                res.addAll(resList);
            }
        }

        return res;

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Map<String, List<String>> preProcessWords(List<String> words) {
        int len = words.get(0).length();
        Map<String, List<String>> map = new HashMap<>();
        for (String word : words) {
            for (int i = 0; i < len; i++) {
                String newWord = word.substring(0, i) + "*" + word.substring(i + 1);
                List l = map.getOrDefault(newWord, new ArrayList<>());
                l.add(word);
                map.put(newWord, l);
            }
        }

        return map;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<List<String>> findLaddersMinimum(String beginWord, String endWord, List<String> wordList) {

        if (wordList.size() == 0 || !wordList.contains(endWord)) return Collections.emptyList();

        Map<String, List<String>> map = preProcessWords(wordList);

        List<String> temp = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        List<List<String>> finalRes = new ArrayList<>();
        temp.add(beginWord);
        visited.add(beginWord);

        findLadderEfficientHelper(beginWord, endWord, map, visited, temp, finalRes);

        return finalRes;

    }

    private void findLadderEfficientHelper(String begin, String end, Map<String, List<String>> map, Set<String> visited, List<String> temp, List<List<String>> finalRes) {
        if (end.equals(begin)) {
            if (finalRes.isEmpty()) {
                finalRes.add(new ArrayList<>(temp));
            } else {
                int minSize = finalRes.get(0).size();
                if (temp.size() < minSize) {
                    finalRes.clear();
                    finalRes.add(new ArrayList<>(temp));
                } else if (temp.size() == minSize) {
                    finalRes.add(new ArrayList<>(temp));
                }
            }
            return;
        }

        List<String> children = findOneDistanceApartChildren(begin, map, visited);
        visited.addAll(children);

        for (String s : children) {
            temp.add(s);
            findLadderEfficientHelper(s, end, map, visited, temp, finalRes);
            temp.remove(s);
            visited.remove(s);
        }

        visited.remove(begin);

    }

    private List<String> findOneDistanceApartChildren(String word, Map<String, List<String>> map, Set<String> set) {

        List<String> res = new ArrayList<>();
        int len = word.length();

        for (int i = 0; i < len; i++) {
            String nw = word.substring(0, i) + "*" + word.substring(i + 1);
            if (map.containsKey(nw)) {
                List<String> resList = map.get(nw);
                for (String s : resList) {
                    if (!set.contains(s) && !word.equals(s)) {
                        res.add(s);
                    }
                }

            }
        }

        return res;

    }

    class BFSNode {
        String value;
        List<BFSNode> children;

        BFSNode(String val) {
            this.value = val;
            children = new ArrayList<>();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<List<String>> findLaddersMinimumBFSDFS(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> dictMap = preProcessWords(wordList);

        Queue<BFSNode> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        BFSNode startNode = new BFSNode(beginWord);
        queue.add(startNode);
        visited.add(beginWord);

        drawGraph(queue, visited, dictMap, endWord);

        List<List<String>> finalRes = new ArrayList<>();
        List<String> temp = new ArrayList<>();

        ladderDFS(startNode, finalRes, temp, endWord);

        return finalRes;
    }

    private void drawGraph(Queue<BFSNode> queue, Set<String> visited, Map<String, List<String>> map, String end) {

        while (!queue.isEmpty()) {
            BFSNode node = queue.poll();
            if (node != null) {
                List<String> children = findOneDistanceApartChildren(node.value, map, visited);
                for (int i = 0; i < children.size(); i++) {
                    String ch = children.get(i);
                    if (!ch.equals(end)) {
                        visited.add(ch);
                    }
                    BFSNode chNode = new BFSNode(ch);
                    node.children.add(chNode);
                    queue.add(chNode);
                }
            }
        }
    }

    private void ladderDFS(BFSNode start, List<List<String>> finalRes, List<String> temp, String end) {

        if (end.equals(start.value)) {
            temp.add(start.value);
            finalRes.add(new ArrayList<>(temp));
            return;
        }

        temp.add(start.value);
        for (BFSNode node : start.children) {
            ladderDFS(node, finalRes, temp, end);
            temp.remove(node.value);
        }

    }
}
