package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import java.util.*;

/**
 * Created by G5501 on 2017/10/14.
 */
class Leet208x {

class TrieNode {
    HashMap<Character, TrieNode> map;
    boolean isWord;
    TrieNode() {
        isWord = false;
        map = new HashMap<>();
    }
}

class Trie {
    /** Initialize your data structure here. */
    public TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode ptr = root;
        for (char c : word.toCharArray()) {
            if (!ptr.map.containsKey(c)) {
                ptr.map.put(c, new TrieNode());
            }
            ptr = ptr.map.get(c);
        }
        ptr.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode ptr = root;
        for (char c : word.toCharArray()) {
            if (!ptr.map.containsKey(c)) {
                return false;
            }
            ptr = ptr.map.get(c);
        }
        return ptr.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode ptr = root;
        for (char c : prefix.toCharArray()) {
            if (!ptr.map.containsKey(c)) {
                return false;
            }
            ptr = ptr.map.get(c);
        }
        return true;
    }
    }
}



public class Leet208x_t {
    public static void main(String[] args) {
        Leet208x leet = new Leet208x();
        /*
        tr.insert("abc");
        tr.insert("abcd");
        tr.insert("def");
        tr.insert("defghijk");
        assert tr.search("abc");
        assert tr.search("abcd");
        assert !tr.search("abcde");
        assert tr.search("def");
        assert !tr.search("defij");
        assert !tr.search("defijk");
        assert tr.search("defghijk");
        assert tr.startsWith("defghi");
        assert !tr.startsWith("defghia");*/
    }
}
