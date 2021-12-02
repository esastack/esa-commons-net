package io.esastack.commons.net.netty.buffer;

import io.esastack.commons.net.buffer.Buffer;
import io.esastack.commons.net.buffer.BufferAllocator;

/**
 * Implementation of {@link BufferAllocator} in netty.
 */
public interface NettyBufferAllocator extends BufferAllocator {

    static NettyBufferAllocator getDefault() {
        return NettyBufferAllocatorImpl.DEFAULT;
    }

    Buffer heapBuffer();

    Buffer heapBuffer(int initialCapacity);

    Buffer directBuffer();

    Buffer directBuffer(int initialCapacity);

}
