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
package io.esastack.commons.net;

import esa.commons.annotation.Internal;
import esa.commons.spi.SPI;
import io.esastack.commons.net.http.Cookie;

import java.util.Optional;

/**
 * A factory to create {@link io.esastack.commons.net.http.Cookie}.
 */
@SPI
@Internal
public interface CookieFactory {

    /**
     * Creates a {@link Cookie} by given {@code name} and {@code value}.
     *
     * @param name  name
     * @param value value
     * @return  cookie
     */
    Cookie create(String name, String value);

    /**
     * Wraps the given {@code cookie} to a {@link Cookie}.
     *
     * @param cookie    cookie
     * @return  wrapped cookie
     */
    Optional<Cookie> wrap(Object cookie);

}

