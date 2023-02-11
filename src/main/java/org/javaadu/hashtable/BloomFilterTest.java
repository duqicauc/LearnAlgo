package org.javaadu.hashtable;

import cn.hutool.bloomfilter.BitMapBloomFilter;
import cn.hutool.bloomfilter.BloomFilterUtil;

public class BloomFilterTest {

    public static void main(String[] args) {
        BitMapBloomFilter bitMapBloomFilter = BloomFilterUtil.createBitMap(1000);
        bitMapBloomFilter.add("a");
        bitMapBloomFilter.add("b");
        bitMapBloomFilter.add("c");

        System.out.println(bitMapBloomFilter.contains("a"));
        System.out.println(bitMapBloomFilter.contains("d"));
    }
}
