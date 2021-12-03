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

import static org.junit.jupiter.api.Assertions.assertEquals;

class HttpStatusClassTest {

    @Test
    void testValueOf() {
        assertEquals(HttpStatusClass.INFORMATIONAL, HttpStatusClass.valueOf(100));
        assertEquals(HttpStatusClass.INFORMATIONAL, HttpStatusClass.valueOf(101));
        assertEquals(HttpStatusClass.INFORMATIONAL, HttpStatusClass.valueOf(199));
        assertEquals(HttpStatusClass.SUCCESSFUL, HttpStatusClass.valueOf(200));
        assertEquals(HttpStatusClass.SUCCESSFUL, HttpStatusClass.valueOf(201));
        assertEquals(HttpStatusClass.SUCCESSFUL, HttpStatusClass.valueOf(299));
        assertEquals(HttpStatusClass.REDIRECTION, HttpStatusClass.valueOf(300));
        assertEquals(HttpStatusClass.REDIRECTION, HttpStatusClass.valueOf(301));
        assertEquals(HttpStatusClass.REDIRECTION, HttpStatusClass.valueOf(399));
        assertEquals(HttpStatusClass.CLIENT_ERROR, HttpStatusClass.valueOf(400));
        assertEquals(HttpStatusClass.CLIENT_ERROR, HttpStatusClass.valueOf(401));
        assertEquals(HttpStatusClass.CLIENT_ERROR, HttpStatusClass.valueOf(499));
        assertEquals(HttpStatusClass.SERVER_ERROR, HttpStatusClass.valueOf(500));
        assertEquals(HttpStatusClass.SERVER_ERROR, HttpStatusClass.valueOf(501));
        assertEquals(HttpStatusClass.SERVER_ERROR, HttpStatusClass.valueOf(599));
        assertEquals(HttpStatusClass.Unknown, HttpStatusClass.valueOf(0));
        assertEquals(HttpStatusClass.Unknown, HttpStatusClass.valueOf(-1));
        assertEquals(HttpStatusClass.Unknown, HttpStatusClass.valueOf(-100));
        assertEquals(HttpStatusClass.Unknown, HttpStatusClass.valueOf(600));
        assertEquals(HttpStatusClass.Unknown, HttpStatusClass.valueOf(1000));
    }

}
