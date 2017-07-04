package com.dk.netty.json.codec;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * Created with IntelliJ IDEA
 * HttpJsonResponse
 *
 * @author dk
 * @date 2017/7/4 18:08
 */
public class HttpJsonResponse {
    private FullHttpResponse response;
    private Object result;

    public HttpJsonResponse(FullHttpResponse response, Object result) {
        this.response = response;
        this.result = result;
    }

    public FullHttpResponse getResponse() {
        return response;
    }

    public void setResponse(FullHttpResponse response) {
        this.response = response;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "HttpJsonResponse{" +
                "response=" + response +
                ", result=" + result +
                '}';
    }
}
