package com.mxc.lru.cache;

/**
 * @Description
 * @Author chenkaideng
 * @Date 2019/8/6
 **/
public class LruCacheTest {

    public static void main(String[] args) {
        LruCache lruCache = new LruCache(3);
        lruCache.set(1, 1);
        lruCache.set(2, 2);
        lruCache.set(3, 3);
        System.out.println("lruCache.get(1):" + lruCache.get(1));
        lruCache.set(4, 4);
        System.out.println("lruCache.get(2):" + lruCache.get(2));
    }

}
