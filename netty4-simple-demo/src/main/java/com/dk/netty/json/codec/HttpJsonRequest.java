package com.dk.netty.json.codec;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * Created with IntelliJ IDEA
 * HttpJsonRequest
 *
 * @author dk
 * @date 2017/7/4 18:07
 */
public class HttpJsonRequest {
    private FullHttpRequest request;
    private Object body;


    public HttpJsonRequest(FullHttpRequest request, Object body) {
        this.request = request;
        this.body = body;
    }

    public FullHttpRequest getRequest() {
        return request;
    }

    public void setRequest(FullHttpRequest request) {
        this.request = request;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "HttpJsonRequest{" +
                "request=" + request +
                ", body=" + body +
                '}';
    }
}
