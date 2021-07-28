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

import esa.commons.annotation.Internal;
import esa.commons.spi.Feature;
import io.esastack.commons.net.http.Cookie;
import io.esastack.commons.net.internal.http.CookieProvider;

import java.util.Optional;

@Internal
@Feature(order = -1000)
public class NettyCookieProvider implements CookieProvider {

    @Override
    public Cookie create(String name, String value) {
        return new CookieImpl(name, value);
    }

    @Override
    public Optional<Cookie> wrap(Object cookie) {
        if (cookie instanceof io.netty.handler.codec.http.cookie.Cookie) {
            return Optional.of(new CookieImpl((io.netty.handler.codec.http.cookie.Cookie) cookie));
        } else if (cookie instanceof Cookie) {
            return Optional.of((Cookie) cookie);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Object> unwrap(Cookie cookie) {
        if (cookie instanceof CookieImpl) {
            return ((CookieImpl) cookie).unwrap();
        } else {
            return Optional.empty();
        }
    }
}

