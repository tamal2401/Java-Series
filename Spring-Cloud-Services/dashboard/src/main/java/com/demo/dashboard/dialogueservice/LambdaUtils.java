package com.demo.dashboard.dialogueservice;

import org.apache.commons.lang.StringUtils;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class LambdaUtils {

    private static Predicate<String> isBlankOrNull = str -> (StringUtils.isBlank(str) || StringUtils.isWhitespace(str));

    public static boolean checkBlankOrNull(String content){
        return isBlankOrNull.test(content);
    }
}
