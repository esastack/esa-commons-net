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

import esa.commons.http.MimeType;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MediaTypeImplTest {

    @Test
    void testIncludes() {
        assertTrue(MediaTypes.ALL.includes(MediaTypes.TEXT_HTML));
        assertFalse(MediaTypes.TEXT_HTML.includes(MediaTypes.ALL));

        assertTrue(MediaTypes.of("text").includes(MediaTypes.TEXT_HTML));
        assertFalse(MediaTypes.TEXT_HTML.includes(MediaTypes.of("text")));
    }

    @Test
    void testIsCompatibleWith() {
        assertTrue(MediaTypes.ALL.isCompatibleWith(MediaTypes.TEXT_HTML));
        assertTrue(MediaTypes.TEXT_HTML.isCompatibleWith(MediaTypes.ALL));

        assertFalse(MediaTypes.TEXT_HTML.isCompatibleWith(MediaTypes.TEXT_PLAIN));
        assertFalse(MediaTypes.TEXT_PLAIN.isCompatibleWith(MediaTypes.TEXT_HTML));
    }

    @Test
    void testQValue() {
        final MediaType type0 = MediaTypes.ALL;
        assertEquals(1L, type0.qValue());

        final MediaType type1 = MediaTypes.of("text", "plain", Collections.singletonMap("q", "10"));
        assertEquals(10L, type1.qValue());
    }

    @Test
    void testParams() {
        final Map<String, String> params = new HashMap<>();
        params.put("m", "n");
        params.put("x", "y");

        final MediaTypeImpl type = new MediaTypeImpl("a", "b", params);
        assertEquals(2, type.params().size());
        assertEquals("n", type.params().get("m"));
        assertEquals("y", type.params().get("x"));
        assertEquals("n", type.param("m"));
        assertEquals("y", type.param("x"));

        assertEquals(2, type.parameters().size());
        assertEquals("n", type.parameters().get("m"));
        assertEquals("y", type.parameters().get("x"));
        assertEquals("n", type.getParameter("m"));
        assertEquals("y", type.getParameter("x"));
    }

    @Test
    void testType() {
        final MediaTypeImpl type0 = new MediaTypeImpl("a");
        assertEquals("a", type0.type());
        assertFalse(type0.isWildcardType());

        final MediaTypeImpl type1 = (MediaTypeImpl) MediaTypes.ALL;
        assertEquals("*", type1.type());
        assertTrue(type1.isWildcardType());
    }

    @Test
    void testSubType() {
        final MediaTypeImpl type0 = new MediaTypeImpl("a", "b");
        assertEquals("b", type0.subtype());
        assertFalse(type0.isWildcardSubtype());

        final MediaTypeImpl type1 = new MediaTypeImpl("a");
        assertEquals("*", type1.subtype());
        assertTrue(type1.isWildcardSubtype());
    }

    @Test
    void testValue() {
        final MediaTypeImpl type = new MediaTypeImpl("a", "b");
        assertEquals(type.toString(), type.value());
    }

    @Test
    void testHashCode() {
        final MediaTypeImpl type0 = new MediaTypeImpl("a", "b");
        final MimeType type1 = new MediaTypeImpl("a", "b");

        assertEquals(type1.hashCode(), type0.hashCode());
    }

    @Test
    void testEquals() {
        final MediaTypeImpl type0 = new MediaTypeImpl("a", "b");
        final MimeType type1 = new MediaTypeImpl("a", "b");
        assertEquals(type0, type1);
    }

}

