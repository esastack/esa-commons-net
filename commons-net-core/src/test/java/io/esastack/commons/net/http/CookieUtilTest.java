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
        assertSame(CookieProviderImpl.CREATED, CookieUtil.cookie("foo", "bar"));
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
