package com.laity.store.util;

import java.io.Serializable;

public class JsonResult<T> implements Serializable {
    /**
     * 状态码
     */
    private Integer state;
    /**
     * 描述信息
     */
    private String message;
    private T data;

}
