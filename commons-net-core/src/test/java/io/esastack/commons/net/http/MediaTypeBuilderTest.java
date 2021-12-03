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

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MediaTypeBuilderTest {

    @Test
    void testBuild() {
        final MediaType mediaType = MediaType.builder("foo")
                .subtype("bar")
                .charset(StandardCharsets.UTF_8)
                .addParam("1", "1")
                .build();
        assertEquals("foo/bar;charset=UTF-8;1=1", mediaType.value());
    }

    @Test
    void testSubtypeDefaultToWildcard() {
        assertEquals("*", MediaType.builder("foo").build().subtype());
    }

}
