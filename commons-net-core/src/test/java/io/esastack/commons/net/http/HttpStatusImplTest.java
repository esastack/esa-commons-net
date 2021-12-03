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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HttpStatusImplTest {

    @Test
    void testCreate() {
        final HttpStatus status = HttpStatus.create(1000, "foo");
        assertEquals(1000, status.code());
        assertEquals("foo", status.reasonPhrase());
    }

    @Test
    void testCreateByConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new HttpStatusImpl(0, ""));
        assertThrows(IllegalArgumentException.class, () -> new HttpStatusImpl(-1, ""));
        assertThrows(NullPointerException.class, () -> new HttpStatusImpl(1, null));
        assertDoesNotThrow(() -> new HttpStatusImpl(1, ""));
    }

    @Test
    void testCodeClassCache() {
        final HttpStatusImpl status = new HttpStatusImpl(1000, "foo");
        final HttpStatusClass codeClass = status.codeClass();
        assertEquals(HttpStatusClass.Unknown, codeClass);
        assertSame(codeClass, status.codeClass());
    }

    @Test
    void testValueOf() {
        assertSame(HttpStatus.CONTINUE, HttpStatus.valueOf(100));
        assertSame(HttpStatus.SWITCHING_PROTOCOLS, HttpStatus.valueOf(101));
        assertSame(HttpStatus.PROCESSING, HttpStatus.valueOf(102));

        assertSame(HttpStatus.OK, HttpStatus.valueOf(200));
        assertSame(HttpStatus.CREATED, HttpStatus.valueOf(201));
        assertSame(HttpStatus.ACCEPTED, HttpStatus.valueOf(202));
        assertSame(HttpStatus.NON_AUTHORITATIVE_INFORMATION, HttpStatus.valueOf(203));
        assertSame(HttpStatus.NO_CONTENT, HttpStatus.valueOf(204));
        assertSame(HttpStatus.RESET_CONTENT, HttpStatus.valueOf(205));
        assertSame(HttpStatus.PARTIAL_CONTENT, HttpStatus.valueOf(206));
        assertSame(HttpStatus.MULTI_STATUS, HttpStatus.valueOf(207));


        assertSame(HttpStatus.MULTIPLE_CHOICES, HttpStatus.valueOf(300));
        assertSame(HttpStatus.MOVED_PERMANENTLY, HttpStatus.valueOf(301));
        assertSame(HttpStatus.FOUND, HttpStatus.valueOf(302));
        assertSame(HttpStatus.SEE_OTHER, HttpStatus.valueOf(303));
        assertSame(HttpStatus.NOT_MODIFIED, HttpStatus.valueOf(304));
        assertSame(HttpStatus.USE_PROXY, HttpStatus.valueOf(305));
        assertSame(HttpStatus.TEMPORARY_REDIRECT, HttpStatus.valueOf(307));
        assertSame(HttpStatus.PERMANENT_REDIRECT, HttpStatus.valueOf(308));

        assertSame(HttpStatus.BAD_REQUEST, HttpStatus.valueOf(400));
        assertSame(HttpStatus.UNAUTHORIZED, HttpStatus.valueOf(401));
        assertSame(HttpStatus.PAYMENT_REQUIRED, HttpStatus.valueOf(402));
        assertSame(HttpStatus.FORBIDDEN, HttpStatus.valueOf(403));
        assertSame(HttpStatus.NOT_FOUND, HttpStatus.valueOf(404));
        assertSame(HttpStatus.METHOD_NOT_ALLOWED, HttpStatus.valueOf(405));
        assertSame(HttpStatus.NOT_ACCEPTABLE, HttpStatus.valueOf(406));
        assertSame(HttpStatus.PROXY_AUTHENTICATION_REQUIRED, HttpStatus.valueOf(407));
        assertSame(HttpStatus.REQUEST_TIMEOUT, HttpStatus.valueOf(408));
        assertSame(HttpStatus.CONFLICT, HttpStatus.valueOf(409));
        assertSame(HttpStatus.GONE, HttpStatus.valueOf(410));
        assertSame(HttpStatus.LENGTH_REQUIRED, HttpStatus.valueOf(411));
        assertSame(HttpStatus.PRECONDITION_FAILED, HttpStatus.valueOf(412));
        assertSame(HttpStatus.REQUEST_ENTITY_TOO_LARGE, HttpStatus.valueOf(413));
        assertSame(HttpStatus.REQUEST_URI_TOO_LONG, HttpStatus.valueOf(414));
        assertSame(HttpStatus.UNSUPPORTED_MEDIA_TYPE, HttpStatus.valueOf(415));
        assertSame(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, HttpStatus.valueOf(416));
        assertSame(HttpStatus.EXPECTATION_FAILED, HttpStatus.valueOf(417));
        assertSame(HttpStatus.MISDIRECTED_REQUEST, HttpStatus.valueOf(421));
        assertSame(HttpStatus.UNPROCESSABLE_ENTITY, HttpStatus.valueOf(422));
        assertSame(HttpStatus.LOCKED, HttpStatus.valueOf(423));
        assertSame(HttpStatus.FAILED_DEPENDENCY, HttpStatus.valueOf(424));
        assertSame(HttpStatus.UNORDERED_COLLECTION, HttpStatus.valueOf(425));
        assertSame(HttpStatus.UPGRADE_REQUIRED, HttpStatus.valueOf(426));
        assertSame(HttpStatus.PRECONDITION_REQUIRED, HttpStatus.valueOf(428));
        assertSame(HttpStatus.TOO_MANY_REQUESTS, HttpStatus.valueOf(429));
        assertSame(HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE, HttpStatus.valueOf(431));

        assertSame(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.valueOf(500));
        assertSame(HttpStatus.NOT_IMPLEMENTED, HttpStatus.valueOf(501));
        assertSame(HttpStatus.BAD_GATEWAY, HttpStatus.valueOf(502));
        assertSame(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.valueOf(503));
        assertSame(HttpStatus.GATEWAY_TIMEOUT, HttpStatus.valueOf(504));
        assertSame(HttpStatus.HTTP_VERSION_NOT_SUPPORTED, HttpStatus.valueOf(505));
        assertSame(HttpStatus.VARIANT_ALSO_NEGOTIATES, HttpStatus.valueOf(506));
        assertSame(HttpStatus.INSUFFICIENT_STORAGE, HttpStatus.valueOf(507));
        assertSame(HttpStatus.NOT_EXTENDED, HttpStatus.valueOf(510));
        assertSame(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED, HttpStatus.valueOf(511));

        assertNull(HttpStatus.valueOf(512));
        assertNull(HttpStatus.valueOf(1000));
        assertNull(HttpStatus.valueOf(0));
        assertNull(HttpStatus.valueOf(-1));

    }

}
