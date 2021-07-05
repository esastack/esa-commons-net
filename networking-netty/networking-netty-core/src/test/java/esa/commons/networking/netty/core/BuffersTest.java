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

import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BuffersTest {

    @Test
    void testEmptyBuffer() {
        assertSame(Unpooled.EMPTY_BUFFER, ((BufferImpl) Buffers.EMPTY_BUFFER).underlying());
    }

    @Test
    void testCreateBufferByUnpooled() {
        final Buffer buffer = Buffers.buffer();
        assertTrue(((BufferImpl) buffer).underlying().hasArray());

        final Buffer buffer1 = Buffers.buffer(1);
        assertTrue(((BufferImpl) buffer1).underlying().hasArray());
        assertEquals(1, buffer1.capacity());

        final Buffer buffer2 = Buffers.buffer(1, 2);
        assertTrue(((BufferImpl) buffer2).underlying().hasArray());
        assertEquals(1, buffer2.capacity());
        assertEquals(2, ((BufferImpl) buffer2).underlying().maxCapacity());
    }

    @Test
    void testCreateBufferByWrappingBytes() {
        final byte[] bytes = new byte[4];

        final Buffer buffer = Buffers.buffer(bytes);
        assertTrue(((BufferImpl) buffer).underlying().hasArray());
        assertSame(bytes, ((BufferImpl) buffer).underlying().array());
        assertEquals(bytes.length, buffer.readableBytes());

        final Buffer buffer1 = Buffers.buffer(bytes, 1, 2);
        assertTrue(((BufferImpl) buffer1).underlying().hasArray());
        assertSame(bytes, ((BufferImpl) buffer1).underlying().array());
        assertEquals(2, buffer1.readableBytes());
    }

    @Test
    void testGetBytes() {
        final Buffer buffer = Buffers.buffer();
        buffer.writeBytes("Hello World!".getBytes());
        buffer.writeBytes("Hello World!".getBytes());

        assertEquals(24, Buffers.getBytes(buffer).length);

        assertArrayEquals("Hello World!".getBytes(), Buffers.getBytes(buffer, 0, 12, true));
        assertArrayEquals("Hello World!".getBytes(), Buffers.getBytes(buffer, 0, 12, false));
        assertArrayEquals("Hello World!".getBytes(), Buffers.getBytes(buffer, 12, 12));
    }

}
