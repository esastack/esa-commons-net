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
package io.esastack.commons.net.buffer;

import esa.commons.Checks;
import esa.commons.spi.SpiLoader;
import io.esastack.commons.net.internal.buffer.BufferProvider;

import java.util.List;

/**
 * Unity class of {@link Buffer}.
 */
public final class BufferUtil {

    /**
     * Obtains a {@link Buffer} which is always empty.
     *
     * @return  buffer
     */
    public static Buffer empty() {
        checkStatus();
        return PROVIDER.empty();
    }

    /**
     * Creates a new, empty buffer.
     *
     * @return buffer
     */
    public static Buffer buffer() {
        checkStatus();
        return PROVIDER.buffer();
    }

    /**
     * Creates a new, empty buffer using specified initial size.
     *
     * @param initialCapacity initial size
     *
     * @return buffer
     */
    public static Buffer buffer(int initialCapacity) {
        checkStatus();
        return PROVIDER.buffer(initialCapacity);
    }

    /**
     * Creates a new, buffer wrapping the given bytes.
     *
     * @param src src
     *
     * @return buffer
     */
    public static Buffer buffer(byte[] src) {
        checkStatus();
        return PROVIDER.buffer(src);
    }

    /**
     * Creates a new, buffer wrapping the given range of bytes.
     *
     * @param src src
     * @param off offset
     * @param len length
     *
     * @return buffer
     */
    public static Buffer buffer(byte[] src, int off, int len) {
        checkStatus();
        return PROVIDER.buffer(src, off, len);
    }

    /**
     * Wraps the given {@code buffer} to a {@link Buffer}.
     *
     * @param buffer    buffer
     * @return  buffer
     */
    public static Buffer wrap(Object buffer) {
        checkStatus();
        return PROVIDER.wrap(buffer).orElse(null);
    }

    /**
     * Obtains the underlying object of the given {@code buffer}.
     *
     * @return  if the underlying {@link Object} is present, then it will be returned, otherwise {@code null}
     * will be returned.
     */
    public static Object unwrap(Buffer buffer) {
        checkStatus();
        return PROVIDER.unwrap(buffer).orElse(null);
    }

    private static final BufferProvider PROVIDER;

    static {
        List<BufferProvider> providers = SpiLoader.cached(BufferProvider.class).getAll();
        if (providers.isEmpty()) {
            PROVIDER = null;
        } else {
            PROVIDER = providers.get(0);
        }
    }

    private static void checkStatus() {
        Checks.checkNotNull(PROVIDER, "provider");
    }

    private BufferUtil() {
    }

}
