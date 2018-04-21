package com.selfcreate.qingxie.util;

import java.util.List;

/**
 * @author evans 2018/3/10 17:08
 */

public class ListUtil {
    public static <T> T getSingleQueryResult(List<T> tList){
        if(tList==null||tList.size()!=1){
            return null;
        }
        return tList.get(0);
    }
}
