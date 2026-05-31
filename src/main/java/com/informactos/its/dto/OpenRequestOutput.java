package com.informactos.its.dto;

public class OpenRequestOutput {

    private String protocol;
    private String status;

    public OpenRequestOutput(){
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}