package com.github.cnkeep.util;

import java.util.Collection;

/**
 * @description: 集合工具
 * @author: <a href="mailto:zhangleili@lizhi.fm">LeiLi.Zhang</a>
 * @date: 2020-03-13
 * @version: v1.1.8
 **/
public class CollectionUtils {
    /**
     * Null-safe check if the specified collection is empty.
     * <p>
     * Null returns true.
     *
     * @param coll  the collection to check, may be null
     * @return true if empty or null
     * @since Commons Collections 3.2
     */
    public static boolean isEmpty(Collection coll) {
        return (coll == null || coll.isEmpty());
    }
}
