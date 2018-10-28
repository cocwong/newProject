package com.example.cocwong.test.http;

public class ResponseEntity<T> {
    private String resCode;
    private T resData;
    private String resMessage;
    private Object exData;

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public T getResData() {
        return resData;
    }

    public void setResData(T resData) {
        this.resData = resData;
    }

    public String getResMessage() {
        return resMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
    }

    public Object getExData() {
        return exData;
    }

    public void setExData(Object exData) {
        this.exData = exData;
    }
}
