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
package io.esastack.commons.net.buffer;

import esa.commons.Checks;

public interface BufferAllocator {

    static BufferAllocator getDefault() {
        return BufferUtil.DEFAULT_ALLOC;
    }

    /**
     * Creates an {@link Buffer} which is always empty.
     *
     * @return buffer
     */
    Buffer empty();

    /**
     * Creates a new, empty buffer.
     *
     * @return buffer
     */
    Buffer buffer();

    /**
     * Creates a new, empty buffer using specified initial size.
     *
     * @param initialCapacity initial size
     * @return buffer
     */
    Buffer buffer(int initialCapacity);

    /**
     * Creates a new, buffer wrapping the given bytes.
     *
     * @param src src
     * @return buffer
     */
    default Buffer buffer(byte[] src) {
        Checks.checkNotNull(src, "src");
        if (src.length == 0) {
            return empty();
        }
        return buffer(src.length).writeBytes(src);
    }

    /**
     * Creates a new, buffer wrapping the given range of bytes.
     *
     * @param src src
     * @param off offset
     * @param len length
     * @return buffer
     */
    default Buffer buffer(byte[] src, int off, int len) {
        if (len == 0) {
            return empty();
        }
        Checks.checkNotNull(src, "src");
        return buffer(len).writeBytes(src, off, len);
    }

}
