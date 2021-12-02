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
import esa.commons.spi.SpiLoader;
import io.esastack.commons.net.internal.buffer.BufferProvider;

import java.util.List;

/**
 * Unity class of {@link Buffer}.
 */
public final class BufferUtil {

    static final BufferAllocator DEFAULT_ALLOC;

    /**
     * Wraps the given {@code buffer} to a {@link Buffer}.
     *
     * @param buffer buffer
     * @return buffer
     */
    public static Buffer wrap(Object buffer) {
        return PROVIDER.wrap(buffer).orElse(null);
    }

    /**
     * Obtains the underlying object of the given {@code buffer}.
     *
     * @return if the underlying {@link Object} is present, then it will be returned, otherwise {@code null}
     * will be returned.
     */
    public static Object unwrap(Buffer buffer) {
        return PROVIDER.unwrap(buffer).orElse(null);
    }

    private static final BufferProvider PROVIDER;

    static {
        List<BufferProvider> providers = SpiLoader.cached(BufferProvider.class).getAll();
        Checks.checkNotEmptyState(providers,
                "Could not find any implementation of '" + BufferProvider.class.getName() + "'");
        PROVIDER = providers.iterator().next();

        List<BufferAllocator> extensions = SpiLoader.cached(BufferAllocator.class).getAll();
        Checks.checkNotEmptyState(extensions,
                "Could not find any implementation of '" + BufferAllocator.class.getName() + "'");
        DEFAULT_ALLOC = extensions.iterator().next();
    }

    private BufferUtil() {
    }

}
