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

import esa.commons.Checks;
import esa.commons.spi.SpiLoader;
import io.esastack.commons.net.internal.http.CookieProvider;

import java.util.List;

public final class CookieUtil {

    /**
     * Wraps the given {@code cookie} to a {@link Cookie}.
     *
     * @param cookie cookie
     * @return cookie
     */
    public static Cookie wrap(Object cookie) {
        return PROVIDER.wrap(cookie).orElse(null);
    }

    /**
     * Unwraps the given {@code cookie}.
     *
     * @param cookie cookie
     * @return object, which may be null.
     */
    public static Object unwrap(Cookie cookie) {
        return PROVIDER.unwrap(cookie).orElse(null);
    }

    /**
     * Creates a {@link Cookie} by given {@code name} and {@code value}.
     *
     * @param name  name
     * @param value value
     * @return cookie
     */
    static Cookie cookie(String name, String value) {
        return PROVIDER.create(name, value);
    }

    private static final CookieProvider PROVIDER;

    static {
        List<CookieProvider> providers = SpiLoader.cached(CookieProvider.class).getAll();
        Checks.checkNotEmptyState(providers,
                "Could not find any implementation of '" + CookieProvider.class.getName() + "'");
        PROVIDER = providers.iterator().next();
    }

    private CookieUtil() {
    }

}

