package io.esastack.commons.net.buffer;

import io.esastack.commons.net.internal.buffer.BufferProvider;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BufferUtilTest {

    @Test
    void testDefaultAlloc() {
        assertSame(BufferAllocatorImpl.EMPTY, Buffer.defaultAlloc().empty());
        assertSame(BufferAllocatorImpl.BUFFER, Buffer.defaultAlloc().buffer());
        assertSame(BufferAllocatorImpl.BUFFER_WITH_INIT_CAP, Buffer.defaultAlloc().buffer(1));

        assertThrows(NullPointerException.class, () -> Buffer.defaultAlloc().buffer(null));
        assertSame(BufferAllocatorImpl.EMPTY, Buffer.defaultAlloc().buffer(new byte[0]));
        final byte[] bytes = new byte[2];
        assertSame(BufferAllocatorImpl.BUFFER_WITH_INIT_CAP_AND_WRITE_BYTES, Buffer.defaultAlloc().buffer(bytes));

        assertSame(BufferAllocatorImpl.EMPTY, Buffer.defaultAlloc().buffer(null, 0, 0));
        assertThrows(NullPointerException.class,
                () -> Buffer.defaultAlloc().buffer(null, 0, 1));
        assertSame(BufferAllocatorImpl.BUFFER_WITH_INIT_CAP_AND_WRITE_BYTES_WITH_IDX,
                Buffer.defaultAlloc().buffer(bytes, 0, 1));
    }

    @Test
    void testBufferUtil() {
        assertSame(BufferProviderImpl.WRAP, BufferUtil.wrap(new Object()));
        assertNull(BufferUtil.wrap(null));
        assertSame(BufferProviderImpl.UNWRAP, BufferUtil.unwrap((Buffer) BufferProviderImpl.UNWRAP));
        assertNull(BufferUtil.unwrap(null));
    }

    public static class BufferProviderImpl implements BufferProvider {

        private static final Buffer WRAP = mock(Buffer.class);
        private static final Object UNWRAP = mock(Buffer.class);

        @Override
        public Optional<Buffer> wrap(Object buffer) {
            return buffer == null ? Optional.empty() : Optional.of(WRAP);
        }

        @Override
        public Optional<Object> unwrap(Buffer buffer) {
            return buffer == null ? Optional.empty() : Optional.of(UNWRAP);
        }
    }

    public static class BufferAllocatorImpl implements BufferAllocator {

        private static final Buffer EMPTY = mock(Buffer.class);
        private static final Buffer BUFFER = mock(Buffer.class);
        private static final Buffer BUFFER_WITH_INIT_CAP = mock(Buffer.class);
        private static final Buffer BUFFER_WITH_INIT_CAP_AND_WRITE_BYTES = mock(Buffer.class);
        private static final Buffer BUFFER_WITH_INIT_CAP_AND_WRITE_BYTES_WITH_IDX = mock(Buffer.class);

        static {
            when(BUFFER_WITH_INIT_CAP.writeBytes(any()))
                    .thenReturn(BUFFER_WITH_INIT_CAP_AND_WRITE_BYTES);
            when(BUFFER_WITH_INIT_CAP.writeBytes(any(), anyInt(), anyInt()))
                    .thenReturn(BUFFER_WITH_INIT_CAP_AND_WRITE_BYTES_WITH_IDX);
        }

        @Override
        public Buffer empty() {
            return EMPTY;
        }

        @Override
        public Buffer buffer() {
            return BUFFER;
        }

        @Override
        public Buffer buffer(int initialCapacity) {
            return BUFFER_WITH_INIT_CAP;
        }
    }
}
