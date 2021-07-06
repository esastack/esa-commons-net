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
import io.esastack.commons.net.CookieFactory;

import java.util.List;

public final class Cookies {

    private static final CookieFactory FACTORY;

    static {
        List<CookieFactory> factories = SpiLoader.cached(CookieFactory.class).getAll();
        if (factories.isEmpty()) {
            FACTORY = null;
        } else {
            FACTORY = factories.get(0);
        }
    }

    /**
     * Creates a {@link Cookie} by given {@code name} and {@code value}.
     *
     * @param name  name
     * @param value value
     * @return  cookie
     */
    public static Cookie cookie(String name, String value) {
        checkStatus();
        return FACTORY.create(name, value);
    }

    /**
     * Wraps the given {@code cookie} to a {@link Cookie}.
     *
     * @param cookie    cookie
     * @return  cookie
     */
    public static Cookie wrap(Object cookie) {
        checkStatus();
        return FACTORY.wrap(cookie).orElse(null);
    }

    private static void checkStatus() {
        Checks.checkArg(FACTORY != null, "factory is null");
    }

    private Cookies() {
    }

}

