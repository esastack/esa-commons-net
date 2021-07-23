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
package io.esastack.commons.net.internal.buffer;

import esa.commons.annotation.Internal;
import esa.commons.spi.SPI;
import io.esastack.commons.net.buffer.Buffer;

import java.util.Optional;

/**
 * A provider to create {@link io.esastack.commons.net.buffer.Buffer}.
 */
@SPI
@Internal
public interface BufferProvider {

    /**
     * Creates an {@link Buffer} which is always empty.
     *
     * @return  buffer
     */
    Buffer empty();

    /**
     * Creates an {@link Buffer} which is empty when initializing.
     *
     * @return  buffer
     */
    Buffer buffer();

    /**
     * Creates an {@link Buffer} with given {@code initialCapacity}.
     *
     * @param initialCapacity   initial capacity
     * @return  buffer
     */
    Buffer buffer(int initialCapacity);

    /**
     * Creates an {@link Buffer} by given {@code src}.
     *
     * @param src   src
     * @return  buffer
     */
    Buffer buffer(byte[] src);

    /**
     * Creates an {@link Buffer} by given {@code src} and {@code off}, {@code len}.
     *
     * @param src   src
     * @param off   off
     * @param len   len
     * @return  buffer
     */
    Buffer buffer(byte[] src, int off, int len);

    /**
     * Wraps the given {@code buffer} to a {@link Buffer}.
     *
     * @param buffer    buffer
     * @return  buffer
     */
    Optional<Buffer> wrap(Object buffer);

    /**
     * Try to unwrap the {@code buffer}.
     *
     * @param buffer    buffer
     * @return  object
     */
    Optional<Object> unwrap(Buffer buffer);

}

