package com.websool.wallet.domain.response;

public class ApiResponse {
    public String status = "200";
    public String message = "Success!";
    public Object data;

    public ApiResponse(Object data){
        this.data = data;
    }
    public ApiResponse(String status,String message, Object data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(String status,String message){
        this.status = status;
        this.message = message;
    }
}
