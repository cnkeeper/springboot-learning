package com.github.cnkeep.common.util.collection;

import java.util.*;

public class CollectionUtils {

    public static <T, V> List<V> extract(Iterable<T> iterable, Getter<T, V> get) {
        List<V> list = new ArrayList<>();
        for (T t : iterable) {
            list.add(get.get(t));
        }
        return list;
    }

    public static <T, V> Set<V> extractSet(Iterable<T> iterable, Getter<T, V> get) {
        Set<V> vSet = new HashSet<>();
        for (T t : iterable) {
            vSet.add(get.get(t));
        }
        return vSet;
    }

    public static <T, K> Map<K, List<T>> group2Map(Iterable<T> iterable, Getter<T, K> keyGetter) {
        Map<K, List<T>> map = new HashMap<>();
        for (T t : iterable) {
            K k = keyGetter.get(t);
            List<T> list = map.get(k);
            if (list == null) list = new ArrayList<>();
            list.add(t);
            map.put(k, list);
        }
        return map;
    }

    public static <T, K, V> Map<K, V> buildMap(Iterable<T> iterable, Getter<T, K> keyGetter, Getter<T, V> valGetter) {
        Map<K, V> map = new HashMap<>();
        for (T t : iterable) {
            K k = keyGetter.get(t);
            V v = valGetter.get(t);
            if (k == null || v == null) {
                continue;
            }
            map.put(k, v);
        }
        return map;
    }
    public static <T, K> Map<K, T> buildMap(Iterable<T> iterable, Getter<T, K> keyGetter) {
        return buildMap(iterable, keyGetter, new Getter<T, T>() {
            @Override
            public T get(T t) {
                return t;
            }
        });
    }

    public static <T, V> List<V> transform(Iterable<T> ts, IFunc<T, V> trans) {
        List<V> list = new ArrayList<>();
        for (T t : ts) {
            list.add(trans.apply(t));
        }
        return list;
    }

    @SafeVarargs
    public static <T> Set<T> newHashSet(Set<T>... sets) {
        Set<T> totalSet = new HashSet<>();
        for (Set<T> set : sets)
            if (!org.springframework.util.CollectionUtils.isEmpty(set)) totalSet.addAll(set);
        return totalSet;
    }

    /**
     * 从中随机挑选n个。
     */
    public static <T> List<T> pickRandom(List<T> list, int count) {
        List<T> resList = new ArrayList<>();
        if (org.springframework.util.CollectionUtils.isEmpty(list)) return resList;
        //快速取1项
        if (count == 1) {
            resList.add(pickRandomOne(list));
            return resList;
        }

        //打乱取前n项
        Collections.shuffle(list);
        if (count >= list.size()) return list;
        return list.subList(0, count);
    }

    public static <T> T pickRandomOne(List<T> list) {
        return pickRandomOne(list, System.currentTimeMillis());
    }

    public static <T> T pickRandomOne(List<T> list, long seed) {
        if (org.springframework.util.CollectionUtils.isEmpty(list)) return null;
        if (list.size() == 1) return list.get(0);

        //随机
        Random pRandom = new Random(seed);
        int index = pRandom.nextInt(list.size());
        return list.get(index);
    }

    public static <T> T filterFirst(List<T> list, IApply<T> f) {
        for (T t : list) {
            if(f.apply(t)){
                return t;
            }
        }
        return null;
    }
}
