/*
 * Copyright 2021 OPPO ESA Stack Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.esastack.commons.net.netty.http;

import esa.commons.Checks;
import io.esastack.commons.net.http.Cookie;
import io.netty.handler.codec.http.cookie.ClientCookieEncoder;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import io.netty.handler.codec.http.cookie.ServerCookieEncoder;

import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of {@link Cookie} that delegates the {@link io.netty.handler.codec.http.cookie.Cookie}.
 */
public class CookieImpl implements Cookie {

    protected final io.netty.handler.codec.http.cookie.Cookie cookie;

    public CookieImpl(String name, String value) {
        this.cookie = new DefaultCookie(name, value);
    }

    public CookieImpl(io.netty.handler.codec.http.cookie.Cookie cookie) {
        Checks.checkNotNull(cookie, "cookie");
        this.cookie = cookie;
    }

    @Override
    public String name() {
        return cookie.name();
    }

    @Override
    public String value() {
        return cookie.value();
    }

    @Override
    public void setValue(String value) {
        cookie.setValue(value);
    }

    @Override
    public boolean wrap() {
        return cookie.wrap();
    }

    @Override
    public void setWrap(boolean wrap) {
        cookie.setWrap(wrap);
    }

    @Override
    public String domain() {
        return cookie.domain();
    }

    @Override
    public void setDomain(String domain) {
        cookie.setDomain(domain);
    }

    @Override
    public String path() {
        return cookie.path();
    }

    @Override
    public void setPath(String path) {
        cookie.setPath(path);
    }

    @Override
    public long maxAge() {
        return cookie.maxAge();
    }

    @Override
    public void setMaxAge(long maxAge) {
        cookie.setMaxAge(maxAge);
    }

    @Override
    public boolean isSecure() {
        return cookie.isSecure();
    }

    @Override
    public void setSecure(boolean secure) {
        cookie.setSecure(secure);
    }

    @Override
    public boolean isHttpOnly() {
        return cookie.isHttpOnly();
    }

    @Override
    public void setHttpOnly(boolean httpOnly) {
        cookie.setHttpOnly(httpOnly);
    }

    @Override
    public String encode(boolean isServer) {
        if (isServer) {
            return ServerCookieEncoder.STRICT.encode(cookie);
        } else {
            return ClientCookieEncoder.STRICT.encode(cookie);
        }
    }

    @Override
    public int hashCode() {
        return cookie.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CookieImpl cookie1 = (CookieImpl) o;
        return Objects.equals(cookie, cookie1.cookie);
    }

    Optional<Object> unwrap() {
        return Optional.of(cookie);
    }

}
