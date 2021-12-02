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
package io.esastack.commons.net.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NettyBufferAllocatorImplTest {

    @Test
    void testAlloc() {
        assertThrows(NullPointerException.class, () -> new NettyBufferAllocatorImpl(null));
        final ByteBufAllocator mock = mock(ByteBufAllocator.class);
        final NettyBufferAllocatorImpl alloc = new NettyBufferAllocatorImpl(mock);
        assertSame(mock, alloc.alloc());
        assertSame(BufferImpl.EMPTY_BUFFER, alloc.empty());

        final ByteBuf buf1 = Unpooled.buffer();
        final ByteBuf buf2 = Unpooled.buffer();
        when(mock.buffer()).thenReturn(buf1);
        when(mock.buffer(anyInt())).thenReturn(buf2);

        assertSame(buf1, ((BufferImpl) alloc.buffer()).unwrap().get());
        assertSame(buf2, ((BufferImpl) alloc.buffer(2)).unwrap().get());

        final ByteBuf buf3 = Unpooled.buffer();
        final ByteBuf buf4 = Unpooled.buffer();
        when(mock.heapBuffer()).thenReturn(buf3);
        when(mock.heapBuffer(anyInt())).thenReturn(buf4);
        assertSame(buf3, ((BufferImpl) alloc.heapBuffer()).unwrap().get());
        assertSame(buf4, ((BufferImpl) alloc.heapBuffer(2)).unwrap().get());

        final ByteBuf buf5 = Unpooled.buffer();
        final ByteBuf buf6 = Unpooled.buffer();
        when(mock.directBuffer()).thenReturn(buf5);
        when(mock.directBuffer(anyInt())).thenReturn(buf6);
        assertSame(buf5, ((BufferImpl) alloc.directBuffer()).unwrap().get());
        assertSame(buf6, ((BufferImpl) alloc.directBuffer(2)).unwrap().get());
    }

    @Test
    void testGetDefault() {
        assertSame(ByteBufAllocator.DEFAULT, ((NettyBufferAllocatorImpl) NettyBufferAllocator.getDefault()).alloc());
    }

}
