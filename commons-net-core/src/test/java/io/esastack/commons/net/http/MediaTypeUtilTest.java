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
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MediaTypeUtilTest {

    @Test
    void testOf() {
        final MediaType type0 = MediaType.create("application");
        assertEquals("application", type0.type());
        assertEquals("*", type0.subtype());
        assertTrue(type0.params().isEmpty());

        final MediaType type1 = MediaType.create("application", "jsonp");
        assertEquals("application", type1.type());
        assertEquals("jsonp", type1.subtype());
        assertTrue(type1.params().isEmpty());

        final MediaType type2 = MediaType.builder("text", "plain").charset(StandardCharsets.UTF_8).build();
        assertEquals("text", type2.type());
        assertEquals("plain", type2.subtype());
        assertEquals(1, type2.params().size());
        assertEquals(StandardCharsets.UTF_8, type2.charset());

        final Map<String, String> params = new LinkedHashMap<>(1);
        params.put("a", "b");
        params.put("x", "y");
        final MediaType type3 = MediaType.builder("*", "*").addParams(params).build();
        assertEquals("*", type3.type());
        assertEquals("*", type3.subtype());
        assertEquals(2, type3.params().size());
        assertEquals("b", type3.param("a"));
        assertEquals("y", type3.param("x"));
    }

    @Test
    void testValueOf() {
        final MediaType type0 = MediaTypeUtil.parseMediaType("application/json;charset=utf-8");
        assertEquals(MediaType.APPLICATION_JSON_UTF8, type0);
        assertThrows(IllegalArgumentException.class, () -> MediaTypeUtil.parseMediaType("xx,"));

        final List<MediaType> types1 = new LinkedList<>(MediaTypeUtil
                .parseMediaTypes("application/json, text/plain;a=b;c=d"));
        assertEquals(MediaType.APPLICATION_JSON, types1.get(0));
        assertEquals("text", types1.get(1).type());
        assertEquals("plain", types1.get(1).subtype());
        assertEquals("b", types1.get(1).param("a"));
        assertEquals("d", types1.get(1).param("c"));
    }

    @Test
    void testValuesOf() {
        assertDoesNotThrow(() -> MediaTypeUtil.parseMediaTypes(null, null));
        final List<MediaType> target = new LinkedList<>();

        MediaTypeUtil.parseMediaTypes("", target);
        assertTrue(target.isEmpty());

        MediaTypeUtil.parseMediaTypes("application/json, text/plain;a=b;c=d", target);
        assertEquals(MediaType.APPLICATION_JSON, target.get(0));
        assertEquals("text", target.get(1).type());
        assertEquals("plain", target.get(1).subtype());
        assertEquals("b", target.get(1).param("a"));
        assertEquals("d", target.get(1).param("c"));
    }

    @Test
    void testParseMediaType() {
        assertThrows(IllegalArgumentException.class, () -> MediaTypeUtil.parseMediaType(""));

        final MediaType type0 = MediaTypeUtil.parseMediaType("application/octet-stream");
        assertEquals("application", type0.type());
        assertEquals("octet-stream", type0.subtype());
        assertTrue(type0.params().isEmpty());
    }

    @Test
    void testParseTypes() {
        assertTrue(MediaTypeUtil.parseMediaTypes("").isEmpty());

        final List<MediaType> types = new LinkedList<>(MediaTypeUtil.parseMediaTypes("*/*, text/plain"));
        assertEquals(2, types.size());
        assertEquals(MediaType.ALL, types.get(0));
        assertEquals(MediaType.TEXT_PLAIN, types.get(1));
    }

    @Test
    void testSortBySpecificityAndQuality() {
        assertThrows(NullPointerException.class, () -> MediaTypeUtil.sortBySpecificityAndQuality(null));
        final List<MediaType> types = new LinkedList<>();
        final MediaType mediaType0 = MediaType.ALL;
        types.add(mediaType0);
        MediaTypeUtil.sortBySpecificityAndQuality(types);
        assertEquals(1, types.size());

        final MediaType mediaType1 = MediaType.create("text");
        final MediaType mediaType2 = MediaType.TEXT_HTML;
        final MediaType mediaType3 = MediaType.builder("application", "json")
                .addParam(MediaTypeUtil.Q_VALUE, "0.5")
                .build();
        final MediaType mediaType4 = MediaType.builder("foo", "bar")
                .addParam(MediaTypeUtil.Q_VALUE, "0.7")
                .build();

        MediaTypeUtil.sortBySpecificityAndQuality(types);
        types.add(mediaType1);
        types.add(mediaType2);
        types.add(mediaType3);
        types.add(mediaType4);

        MediaTypeUtil.sortBySpecificityAndQuality(types);
        assertSame(mediaType2, types.get(0));
        assertSame(mediaType1, types.get(1));
        assertSame(mediaType4, types.get(2));
        assertSame(mediaType3, types.get(3));
        assertSame(mediaType0, types.get(4));
    }

    @Test
    void testCopyQualityValue() {
        final MediaType type0 = MediaTypeUtil.parseMediaType("text/plain");
        assertSame(type0, MediaTypeUtil.copyQualityValue(MediaType.ALL, type0));

        final MediaType type1 = MediaTypeUtil.parseMediaType("text/plain;a=b;c=d;m=n");
        final MediaType type2 = MediaTypeUtil.copyQualityValue(MediaTypeUtil.parseMediaType("*/*;a=xxx;q=11"), type1);
        assertNotSame(type1, type2);
        assertEquals("text", type2.type());
        assertEquals("plain", type2.subtype());
        assertEquals(4, type2.params().size());
        assertEquals("b", type2.param("a"));
        assertEquals("d", type2.param("c"));
        assertEquals("n", type2.param("m"));
        assertEquals(11L, type2.qValue());
    }

}

