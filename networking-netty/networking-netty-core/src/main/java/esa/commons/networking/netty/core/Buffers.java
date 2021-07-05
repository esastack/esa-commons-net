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
package esa.commons.networking.netty.core;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.util.internal.PlatformDependent;

import java.util.Optional;

import static io.netty.util.internal.MathUtil.isOutOfBounds;

/**
 * Unity class of {@link Buffer}.
 */
public final class Buffers {

    /**
     * @see BufferImpl#EMPTY_BUFFER
     */
    public static Buffer EMPTY_BUFFER = BufferImpl.EMPTY_BUFFER;

    /**
     * Creates a new, empty buffer.
     *
     * @return buffer
     */
    public static Buffer buffer() {
        return new BufferImpl();
    }

    /**
     * Creates a new, empty buffer using specified initial size.
     *
     * @param initialCapacity initial size
     *
     * @return buffer
     */
    public static Buffer buffer(int initialCapacity) {
        return new BufferImpl(initialCapacity);
    }

    /**
     * Creates a new, empty buffer using specified initial size and max capacity.
     *
     * @param initialCapacity initial size
     *
     * @return buffer
     */
    public static Buffer buffer(int initialCapacity, int maxCapacity) {
        return new BufferImpl(initialCapacity, maxCapacity);
    }

    /**
     * Creates a new, buffer wrapping the given bytes.
     *
     * @param src src
     *
     * @return buffer
     */
    public static Buffer buffer(byte[] src) {
        return new BufferImpl(Unpooled.wrappedBuffer(src));
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
        return new BufferImpl(Unpooled.wrappedBuffer(src, off, len));
    }

    /**
     * Obtains the underlying {@link ByteBuf} of the given {@code buffer}.
     *
     * @param buffer    buffer
     * @return  if the underlying {@link ByteBuf} is present, then it will be returned, otherwise a empty will
     * be returned.
     */
    public static Optional<ByteBuf> underlyingByteBuf(Buffer buffer) {
        if (buffer instanceof BufferImpl) {
            return Optional.of(((BufferImpl) buffer).underlying());
        }
        return Optional.empty();
    }

    /**
     * Obtains a {@link byte[]} from the underlying {@code buffer}.
     *
     * @param buffer    buffer
     * @return  byte[]
     */
    public static byte[] getBytes(Buffer buffer) {
        return getBytes(buffer,  buffer.readerIndex(), buffer.readableBytes());
    }

    /**
     * Obtains a {@link byte[]} from the underlying {@code buffer}.
     *
     * @param buffer    buffer
     * @param start     startIndex
     * @param length    length
     * @return  byte[]
     */
    public static byte[] getBytes(Buffer buffer, int start, int length) {
        return getBytes(buffer, start, length, true);
    }

    /**
     * Obtains a {@link byte[]} from the underlying {@code buffer}.
     *
     * @param buffer    buffer
     * @param start     startIndex
     * @param length    length
     * @param copy      whether to share the underlying storage or not, if true, just copy the memory, otherwise try
     *                  to share.
     * @return  byte[]
     */
    public static byte[] getBytes(Buffer buffer, int start, int length, boolean copy) {
        Optional<ByteBuf> underlying;
        if ((underlying = underlyingByteBuf(buffer)).isPresent()) {
            return ByteBufUtil.getBytes(underlying.get(), start, length, copy);
        }

        int capacity = buffer.capacity();
        if (isOutOfBounds(start, length, capacity)) {
            throw new IndexOutOfBoundsException("expected: " + "0 <= start(" + start + ") <= start + length(" + length
                    + ") <= " + "buf.capacity(" + capacity + ')');
        }

        byte[] bytes = PlatformDependent.allocateUninitializedArray(length);
        buffer.getBytes(start, bytes);
        return bytes;
    }

    private Buffers() {
    }

}
