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
package io.esastack.commons.net.netty;

import io.esastack.commons.net.buffer.Buffer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NettyBufferFactoryTest {

    @Test
    void testEmpty() {
        final NettyBufferFactory factory = new NettyBufferFactory();
        assertSame(Unpooled.EMPTY_BUFFER, factory.empty().unwrap().orElse(null));
    }

    @Test
    void testCreateEmptyBuffer() {
        final NettyBufferFactory factory = new NettyBufferFactory();
        final Buffer buffer = factory.buffer();

        final Optional<Object> unwrapped = buffer.unwrap();
        assertTrue(unwrapped.isPresent());
        assertTrue(((ByteBuf) unwrapped.get()).hasArray());

        final Buffer buffer1 = factory.buffer(1);
        final Optional<Object> unwrapped1 = buffer1.unwrap();
        assertTrue(unwrapped1.isPresent());
        assertTrue(((ByteBuf) unwrapped1.get()).hasArray());
        assertEquals(1, buffer1.capacity());

        final Buffer buffer2 = factory.buffer(1, 2);
        final Optional<Object> unwrapped2 = buffer2.unwrap();
        assertTrue(unwrapped2.isPresent());
        assertTrue(((ByteBuf) unwrapped2.get()).hasArray());
        assertEquals(1, buffer2.capacity());
        assertEquals(2, ((ByteBuf) unwrapped2.get()).maxCapacity());
    }

    @Test
    void testCreateBufferByBytes() {
        final NettyBufferFactory factory = new NettyBufferFactory();
        final byte[] bytes = new byte[4];

        final Buffer buffer = factory.buffer(bytes);
        final Optional<Object> unwrapped = buffer.unwrap();
        assertTrue(unwrapped.isPresent());
        assertTrue(((ByteBuf) unwrapped.get()).hasArray());
        assertSame(bytes, ((ByteBuf) unwrapped.get()).array());
        assertEquals(bytes.length, buffer.readableBytes());

        final Buffer buffer1 = factory.buffer(bytes, 1, 2);
        final Optional<Object> unwrapped1 = buffer1.unwrap();
        assertTrue(unwrapped1.isPresent());
        assertTrue(((ByteBuf) unwrapped1.get()).hasArray());
        assertSame(bytes, ((ByteBuf) unwrapped1.get()).array());
        assertEquals(2, buffer1.readableBytes());
    }

    @Test
    void testWrap() {
        final NettyBufferFactory factory = new NettyBufferFactory();
        assertFalse(factory.wrap(null).isPresent());
        assertFalse(factory.wrap(new Object()).isPresent());

        final ByteBuf buf = Unpooled.buffer(200);
        final Optional<Buffer> buffer = factory.wrap(buf);
        assertTrue(buffer.isPresent());
        assertSame(buf, buffer.get().unwrap().orElse(null));
    }

}

