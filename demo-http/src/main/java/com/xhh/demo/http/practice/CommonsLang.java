package com.xhh.demo.http.practice;

import org.apache.commons.lang.Validate;

/**
 * Created by tiger on 2014/3/27.
 */
public class CommonsLang {

    public static void main(String[] args) {
        Validate.notEmpty("", "parameter is null");
    }
}
