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
package io.esastack.commons.net.netty;

import io.esastack.commons.net.http.Cookie;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NettyCookieFactoryTest {

    @Test
    void testCreate() {
        final NettyCookieFactory factory = new NettyCookieFactory();
        final Cookie cookie = factory.create("a", "b");
        assertNotNull(cookie);
        assertEquals("a", cookie.name());
        assertEquals("b", cookie.value());

        assertThrows(NullPointerException.class, () -> factory.create(null, "B"));
        assertThrows(IllegalArgumentException.class, () -> factory.create("", "B"));
        assertThrows(NullPointerException.class, () -> factory.create("a", null));
    }

    @Test
    void testWrap() {
        final NettyCookieFactory factory = new NettyCookieFactory();
        final Optional<Cookie> cookie0 = factory.wrap(null);
        assertFalse(cookie0.isPresent());

        final Optional<Cookie> cookie1 = factory.wrap(new Object());
        assertFalse(cookie1.isPresent());

        final Optional<Cookie> cookie2 = factory.wrap(new DefaultCookie("a", "b"));
        assertTrue(cookie2.isPresent());
        assertEquals("a", cookie2.get().name());
        assertEquals("b", cookie2.get().value());
    }
}

