package com.jianxilin.vhr_springboot.model;

import java.util.List;

public class ResponsePageBean {

    List<?> data;
    Integer total;

    public ResponsePageBean() {
    }

    public ResponsePageBean(List<?> data, Integer total) {
        this.data = data;
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
