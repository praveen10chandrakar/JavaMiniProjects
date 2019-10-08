package com.DSApplications.AutoComplete;


public class AutoComplete {

    public static void main(String[] args) {
        Trie root = new Trie();

        String keys[] = {"the", "a", "there", "answer", "any",
                "by", "bye", "their", "hello", "dog", "hell", "cat", "a", "hel", "helps", "help", "helping"};


        // Construct trie
        int i;
        for (i = 0; i < keys.length; i++)
            root.insert(keys[i]);

        StringBuffer buffer = new StringBuffer();

        root.printAutoCompletes(new String("he"));

    }
}
