package io.esastack.commons.net.buffer;

import io.esastack.commons.net.internal.buffer.BufferProvider;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

class BufferUtilTest {

    @Test
    void testCreate() {
        assertSame(BufferProviderImpl.EMPTY, BufferUtil.empty());
        assertSame(BufferProviderImpl.BUFFER, BufferUtil.buffer());
        assertSame(BufferProviderImpl.BUFFER_WITH_INIT_CAP, BufferUtil.buffer(1));
        assertSame(BufferProviderImpl.BUFFER_WITH_BYTES, BufferUtil.buffer(new byte[2]));
        assertSame(BufferProviderImpl.BUFFER_WITH_LENGTH_BASED_BYTES, BufferUtil.buffer(new byte[2], 0, 1));
        assertSame(BufferProviderImpl.WRAP, BufferUtil.wrap(new Object()));
        assertNull(BufferUtil.wrap(null));
        assertSame(BufferProviderImpl.UNWRAP, BufferUtil.unwrap(BufferProviderImpl.EMPTY));
        assertNull(BufferUtil.unwrap(null));
    }

    public static class BufferProviderImpl implements BufferProvider {

        private static final Buffer EMPTY = mock(Buffer.class);
        private static final Buffer BUFFER = mock(Buffer.class);
        private static final Buffer BUFFER_WITH_INIT_CAP = mock(Buffer.class);
        private static final Buffer BUFFER_WITH_BYTES = mock(Buffer.class);
        private static final Buffer BUFFER_WITH_LENGTH_BASED_BYTES = mock(Buffer.class);
        private static final Buffer WRAP = mock(Buffer.class);
        private static final Object UNWRAP = EMPTY;

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

        @Override
        public Buffer buffer(byte[] src) {
            return BUFFER_WITH_BYTES;
        }

        @Override
        public Buffer buffer(byte[] src, int off, int len) {
            return BUFFER_WITH_LENGTH_BASED_BYTES;
        }

        @Override
        public Optional<Buffer> wrap(Object buffer) {
            return buffer == null ? Optional.empty() : Optional.of(WRAP);
        }

        @Override
        public Optional<Object> unwrap(Buffer buffer) {
            return buffer == null ? Optional.empty() : Optional.of(UNWRAP);
        }
    }
}
