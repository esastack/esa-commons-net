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

/**
 * Code class of {@link HttpStatus#code()}.
 *
 * @see HttpStatus
 * @see HttpStatus#codeClass()
 */
public enum HttpStatusClass {

    INFORMATIONAL(1),
    SUCCESSFUL(2),
    REDIRECTION(3),
    CLIENT_ERROR(4),
    SERVER_ERROR(5),
    Unknown(-1);

    private final int value;

    HttpStatusClass(int value) {
        this.value = value;
    }

    /**
     * Resolves the {@link HttpStatusClass} by give {@code}.
     *
     * @return code class or {@code null} if missing
     */
    public static HttpStatusClass valueOf(int code) {
        int c = code / 100;
        for (HttpStatusClass v : HttpStatusClass.values()) {
            if (c == v.value) {
                return v;
            }
        }
        return HttpStatusClass.Unknown;
    }

}
