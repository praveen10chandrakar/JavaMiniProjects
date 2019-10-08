package com.DSApplications.AutoComplete;

import java.util.ArrayList;
import java.util.List;

class TrieNode {

    public final Integer ALPHABETS_COUNT = 26;
    TrieNode[] children = new TrieNode[26];
    Boolean isEndOfWord;

    public TrieNode() {
        for (int i = 0; i < ALPHABETS_COUNT; i++) {
            children[i] = null;
        }
        isEndOfWord = false;
    }

}

class Trie {

    public TrieNode root;
    private List<String> dictionary = new ArrayList<>();

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode pCrawl = root;
        int length = word.length();

        for (int level = 0; level < length; level++) {
            int index = word.charAt(level) - 'a';

            if (pCrawl.children[index] == null) {
                pCrawl.children[index] = new TrieNode();
            }
            pCrawl = pCrawl.children[index];
        }

        pCrawl.isEndOfWord = true;
    }

    public Boolean search(String word) {
        TrieNode pCrawl = root;
        int length = word.length();
        for (int level = 0; level < length; level++) {
            int index = word.charAt(level) - 'a';
            if (pCrawl.children[index] == null) {
                return false;
            }
            pCrawl = pCrawl.children[index];
        }
        return (pCrawl != null && pCrawl.isEndOfWord);
    }

    public void printDictionary(TrieNode root, StringBuffer buffer) {
        if (root.isEndOfWord) {
            dictionary.add(buffer.toString());
            System.out.println(buffer);
        }

        for (int i = 0; i < root.children.length; i++) {
            if (root.children[i] != null) {
                buffer.append((char) (i + 'a'));
                printDictionary(root.children[i], buffer);
                buffer.deleteCharAt(buffer.length() - 1);
            }
        }
    }


    public void printAutoCompletes(String word){
        TrieNode pCrawl = root;
        int length = word.length();
        for (int level = 0; level < length; level++) {
            int index = word.charAt(level) - 'a';
            if (pCrawl.children[index] == null) {
                return;
            }
            pCrawl = pCrawl.children[index];
        }

        if (pCrawl.isEndOfWord != true || !isLastNode(pCrawl)){
            printDictionary(pCrawl, new StringBuffer(word));
        }
    }

    Boolean isLastNode(TrieNode root){
        for (int i = 0; i < root.children.length; i++){
            if(root.children[i] != null){
                return false;
            }
        }
        return true;
    }
}
