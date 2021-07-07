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

import io.esastack.commons.net.http.Cookie;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NettyCookieProviderTest {

    @Test
    void testCreate() {
        final NettyCookieProvider provider = new NettyCookieProvider();
        final Cookie cookie = provider.create("a", "b");
        assertNotNull(cookie);
        assertEquals("a", cookie.name());
        assertEquals("b", cookie.value());

        assertThrows(NullPointerException.class, () -> provider.create(null, "B"));
        assertThrows(IllegalArgumentException.class, () -> provider.create("", "B"));
        assertThrows(NullPointerException.class, () -> provider.create("a", null));
    }

    @Test
    void testWrap() {
        final NettyCookieProvider provider = new NettyCookieProvider();
        final Optional<Cookie> cookie0 = provider.wrap(null);
        assertFalse(cookie0.isPresent());

        final Optional<Cookie> cookie1 = provider.wrap(new Object());
        assertFalse(cookie1.isPresent());

        final Optional<Cookie> cookie2 = provider.wrap(new DefaultCookie("a", "b"));
        assertTrue(cookie2.isPresent());
        assertEquals("a", cookie2.get().name());
        assertEquals("b", cookie2.get().value());
    }

    @Test
    void testUnwrap() {
        final NettyCookieProvider provider = new NettyCookieProvider();
        final Cookie cookie = provider.create("a", "b");

        final Optional<Object> unwrapped = provider.unwrap(cookie);
        assertTrue(unwrapped.isPresent());
        assertTrue(unwrapped.get() instanceof io.netty.handler.codec.http.cookie.Cookie);

        assertFalse(provider.unwrap(null).isPresent());
    }
}

