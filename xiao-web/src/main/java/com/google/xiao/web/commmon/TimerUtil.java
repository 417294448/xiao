/*
 * Copyright 2015 edaijia.cn All right reserved. 
 *
 */
package com.google.xiao.web.commmon;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类Utils.java的实现描述：TODO 类实现描述
 * 
 * @author shuang.xiao 2015年9月23日 下午3:57:53
 */
public class TimerUtil {

    public static String getStrTime(Long cc_time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        re_StrTime = sdf.format(new Date(cc_time));
        return re_StrTime;
    }
    
    public static String getStrTime(Date cc_time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        re_StrTime = sdf.format(cc_time);
        return re_StrTime;
    }
}
