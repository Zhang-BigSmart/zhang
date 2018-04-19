package com.zhang.upms;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/7/26
 * @history
 */
public class CollectionDataTest {

    public static void main(String[] args) {
        Set<String> set = new LinkedHashSet<String>(
                new CollectionData<String>(new Government(), 15));
        set.addAll(CollectionData.list(new Government(), 15));
        System.out.println(set);
    }
}

class Government implements Generator<String>{

    String[] foundation = ("strange women lying in ponds "+
            "distributing swords is no basis for a system of " +
            "government").split(" ");
    private int index;
    public String next() {
        return foundation[index++];
    }
}