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

import io.esastack.commons.net.buffer.Buffer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UnpooledNettyBufferAllocatorTest {

    @Test
    void testSpiLoaded() {
        assertEquals(UnpooledNettyBufferAllocator.class, Buffer.defaultAlloc().getClass());
    }

    @Test
    void testWrapByteArray() {
        final UnpooledNettyBufferAllocator alloc = new UnpooledNettyBufferAllocator();
        final byte[] toTest = new byte[] {1, 2};
        assertSame(toTest,
                ((ByteBuf) ((BufferImpl) alloc.buffer(toTest)).unwrap().get()).array());
        assertSame(toTest,
                ((ByteBuf) ((BufferImpl) alloc.buffer(toTest, 0, 1)).unwrap().get()).array());
    }

}
