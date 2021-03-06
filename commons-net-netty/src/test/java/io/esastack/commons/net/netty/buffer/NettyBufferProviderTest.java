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
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NettyBufferProviderTest {

    @Test
    void testWrap() {
        final NettyBufferProvider provider = new NettyBufferProvider();
        assertFalse(provider.wrap(null).isPresent());
        assertFalse(provider.wrap(new Object()).isPresent());

        final ByteBuf buf = Unpooled.buffer(200);
        final Optional<Buffer> buffer = provider.wrap(buf);
        assertTrue(buffer.isPresent());
        assertSame(buf, provider.unwrap(buffer.get()).orElse(null));

        assertSame(buffer.get(), provider.wrap(buffer.get()).orElse(null));
    }

    @Test
    void testUnwrap() {
        final NettyBufferProvider provider = new NettyBufferProvider();
        final Buffer buffer = Buffer.defaultAlloc().buffer("Hello World!".getBytes(StandardCharsets.UTF_8));
        final Optional<Object> unwrapped = provider.unwrap(buffer);

        assertTrue(unwrapped.isPresent());
        assertTrue(unwrapped.get() instanceof ByteBuf);

        assertFalse(provider.unwrap(null).isPresent());
    }

}

