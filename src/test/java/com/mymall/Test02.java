package com.mymall;

import com.mymall.commons.ServerResponse;

/**
 * @author Flash gems
 * @create 2020-06-06 13:59
 */
public class Test02 {
    public static void main(String[] args) {
        System.out.println(ServerResponse.createByError().getStatus());
    }
}
