package com.zhang.upms;

import java.util.ArrayList;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/7/26
 * @history
 */
public class CollectionData<T> extends ArrayList<T> {

    public CollectionData(Generator<T> gen, int quantity){
        for(int i = 0; i < quantity ; i++){
            add(gen.next());
        }
    }

    public static <T> CollectionData<T>
    list(Generator<T> gen, int quantity){
        return new CollectionData<T>(gen, quantity);
    }






}
