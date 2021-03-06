package io.github.biezhi.elves.request;

import io.github.biezhi.elves.spider.Spider;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Request 请求
 *
 * @author biezhi
 * @date 2018/1/11
 */
@Getter
public class Request<T> {

    private Spider spider;
    private String url;
    private String              method  = "GET";
    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> cookies = new HashMap<>();
    private String contentType = "text/html; charset=UTF-8";
    private String charset = "UTF-8";
    private Parser<T> parser;
    private Map<Object,Object> context = new HashMap<>();
    private int failTimes = 0;
    private Long lastReqTime;
    private String parentId;

    public Request(Spider spider, String url, Parser<T> parser) {
        this.spider = spider;
        this.url = url;
        this.parser = parser;
        this.header("User-Agent", spider.getConfig().userAgent());
    }

    public Request(Spider spider, String url, Parser<T> parser,String parentId) {
        this.spider = spider;
        this.url = url;
        this.parser = parser;
        this.header("User-Agent", spider.getConfig().userAgent());
        this.parentId = parentId;
    }

    public Request header(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public Request cookie(String key, String value) {
        this.cookies.put(key, value);
        return this;
    }

    public String header(String key) {
        return this.headers.get(key);
    }

    public String cookie(String key) {
        return this.cookies.get(key);
    }

    public void setParser(Parser<T> parser) {
        this.parser = parser;
    }

    public String contentType() {
        return contentType;
    }

    public Request contentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public String charset() {
        return charset;
    }

    public Request charset(String charset) {
        this.charset = charset;
        return this;
    }

    public Request method(String method) {
        this.method = method;
        return this;
    }

    public int increaseFailTime(){
        return this.failTimes++;
    }

    public String method() {
        return this.method;
    }

    public int getFailTimes() {
        return failTimes;
    }

    public void setFailTimes(int failTimes) {
        this.failTimes = failTimes;
    }

    public Map<Object, Object> getContext() {
        return context;
    }

    public void setContext(Map<Object, Object> context) {
        this.context = context;
    }

    public Long getLastReqTime() {
        return lastReqTime;
    }

    public void setLastReqTime(Long lastReqTime) {
        this.lastReqTime = lastReqTime;
    }
}
