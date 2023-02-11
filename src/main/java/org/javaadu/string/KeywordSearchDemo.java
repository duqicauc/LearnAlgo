package org.javaadu.string;

import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;

import java.util.Collection;

public class KeywordSearchDemo {

    public static void main(String[] args) {
        Trie trie = Trie.builder()
                .addKeyword("hers")
                .addKeyword("his")
                .addKeyword("she")
                .addKeyword("he")
                .build();
        Collection<Emit> emits = trie.parseText("ushers");

        System.out.println(emits);
    }
}
