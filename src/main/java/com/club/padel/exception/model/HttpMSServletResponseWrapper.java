package com.club.padel.exception.model;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletResponseWrapper;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class HttpMSServletResponseWrapper extends ServletResponseWrapper implements HttpServletResponse {
    public HttpMSServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    private HttpServletResponse _getHttpServletResponse() {
        return (HttpServletResponse) super.getResponse();
    }

    public void addCookie(Cookie cookie) {
        this._getHttpServletResponse().addCookie(cookie);
    }

    public boolean containsHeader(String name) {
        return this._getHttpServletResponse().containsHeader(name);
    }

    public String encodeURL(String url) {
        return this._getHttpServletResponse().encodeURL(url);
    }

    public String encodeRedirectURL(String url) {
        return this._getHttpServletResponse().encodeRedirectURL(url);
    }

    /**
     * @deprecated
     */
    public String encodeUrl(String url) {
        return this._getHttpServletResponse().encodeUrl(url);
    }

    /**
     * @deprecated
     */
    public String encodeRedirectUrl(String url) {
        return this._getHttpServletResponse().encodeRedirectUrl(url);
    }

    public void sendError(int sc, String msg) throws IOException {
        this.setStatus(sc);
        //this._getHttpServletResponse().sendError(sc, msg);
    }

    public void sendError(int sc) throws IOException {
        this.setStatus(sc);
        //this._getHttpServletResponse().sendError(sc);
    }

    public void sendRedirect(String location) throws IOException {
        this._getHttpServletResponse().sendRedirect(location);
    }

    public void setDateHeader(String name, long date) {
        this._getHttpServletResponse().setDateHeader(name, date);
    }

    public void addDateHeader(String name, long date) {
        this._getHttpServletResponse().addDateHeader(name, date);
    }

    public void setHeader(String name, String value) {
        this._getHttpServletResponse().setHeader(name, value);
    }

    public void addHeader(String name, String value) {
        this._getHttpServletResponse().addHeader(name, value);
    }

    public void setIntHeader(String name, int value) {
        this._getHttpServletResponse().setIntHeader(name, value);
    }

    public void addIntHeader(String name, int value) {
        this._getHttpServletResponse().addIntHeader(name, value);
    }

    public void setStatus(int sc) {
        this._getHttpServletResponse().setStatus(sc);
    }

    /**
     * @deprecated
     */
    public void setStatus(int sc, String sm) {
        this._getHttpServletResponse().setStatus(sc, sm);
    }

    public int getStatus() {
        return this._getHttpServletResponse().getStatus();
    }

    public String getHeader(String name) {
        return this._getHttpServletResponse().getHeader(name);
    }

    public Collection<String> getHeaders(String name) {
        return this._getHttpServletResponse().getHeaders(name);
    }

    public Collection<String> getHeaderNames() {
        return this._getHttpServletResponse().getHeaderNames();
    }
}
