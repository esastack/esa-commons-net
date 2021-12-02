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
package io.esastack.commons.net.http;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * Builder interface for {@link MediaType}.
 */
public interface MediaTypeBuilder {

    /**
     * Sets {@link MediaType#subtype()}, default to {@code '*'}
     */
    MediaTypeBuilder subtype(String subtype);

    /**
     * Sets {@link MediaType#charset()}
     */
    default MediaTypeBuilder charset(Charset charset) {
        return charset(charset.name());
    }

    /**
     * Sets {@link MediaType#charset()}
     */
    MediaTypeBuilder charset(String charset);

    /**
     * Add parameter into {@link MediaType#params()} ()}
     */
    MediaTypeBuilder addParam(String key, String value);

    /**
     * Add parameters into {@link MediaType#params()}
     */
    MediaTypeBuilder addParams(Map<String, String> parameters);

    /**
     * Builds a new instance of {@link MediaType} now.
     */
    MediaType build();

}
