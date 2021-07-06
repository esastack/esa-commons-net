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
import io.esastack.commons.net.BufferFactory;

import java.util.List;

/**
 * Unity class of {@link Buffer}.
 */
public final class Buffers {

    private static final BufferFactory FACTORY;

    static {
        List<BufferFactory> factories = SpiLoader.cached(BufferFactory.class).getAll();
        if (factories.isEmpty()) {
            FACTORY = null;
        } else {
            FACTORY = factories.get(0);
        }
    }

    /**
     * Obtains a {@link Buffer} which is always empty.
     *
     * @return  buffer
     */
    public static Buffer empty() {
        checkStatus();
        return FACTORY.empty();
    }

    /**
     * Creates a new, empty buffer.
     *
     * @return buffer
     */
    public static Buffer buffer() {
        checkStatus();
        return FACTORY.buffer();
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
        return FACTORY.buffer(initialCapacity);
    }

    /**
     * Creates a new, empty buffer using specified initial size and max capacity.
     *
     * @param initialCapacity initial size
     *
     * @return buffer
     */
    public static Buffer buffer(int initialCapacity, int maxCapacity) {
        checkStatus();
        return FACTORY.buffer(initialCapacity, maxCapacity);
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
        return FACTORY.buffer(src);
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
        return FACTORY.buffer(src, off, len);
    }

    /**
     * Wraps the given {@code buffer} to a {@link Buffer}.
     *
     * @param buffer    buffer
     * @return  buffer
     */
    public static Buffer wrap(Object buffer) {
        checkStatus();
        return FACTORY.wrap(buffer).orElse(null);
    }

    private static void checkStatus() {
        Checks.checkArg(FACTORY != null, "factory is null");
    }

    private Buffers() {
    }

}
