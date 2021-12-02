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
package io.esastack.commons.net.http;

import io.esastack.commons.net.internal.http.CookieProvider;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

class CookieUtilTest {

    @Test
    void testCreate() {
        assertSame(CookieProviderImpl.CREATED, Cookie.cookie("foo", "bar"));
        assertSame(CookieProviderImpl.WRAP, CookieUtil.wrap(new Object()));
        assertNull(CookieUtil.wrap(null));
        assertSame(CookieProviderImpl.UNWRAP, CookieUtil.unwrap(CookieProviderImpl.CREATED));
        assertNull(CookieUtil.unwrap(null));
    }

    public static class CookieProviderImpl implements CookieProvider {

        private static final Cookie CREATED = mock(Cookie.class);
        private static final Cookie WRAP = mock(Cookie.class);
        private static final Object UNWRAP = mock(Cookie.class);

        @Override
        public Cookie create(String name, String value) {
            return CREATED;
        }

        @Override
        public Optional<Cookie> wrap(Object cookie) {
            return cookie == null ? Optional.empty() : Optional.of(WRAP);
        }

        @Override
        public Optional<Object> unwrap(Cookie cookie) {
            return cookie == null ? Optional.empty() : Optional.of(UNWRAP);
        }
    }

}
