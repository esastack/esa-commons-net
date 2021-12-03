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

import esa.commons.Checks;

/**
 * Default implementation of {@link HttpStatus}.
 */
final class HttpStatusImpl implements HttpStatus {

    private final int code;
    private final String reasonPhrase;
    private HttpStatusClass codeClass;

    HttpStatusImpl(int code, String reasonPhrase) {
        Checks.checkArg(code > 0, "code(" + code + ") <= 0");
        Checks.checkNotNull(reasonPhrase, "reasonPhrase");
        this.code = code;
        this.reasonPhrase = reasonPhrase;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String reasonPhrase() {
        return reasonPhrase;
    }

    @Override
    public HttpStatusClass codeClass() {
        HttpStatusClass c = this.codeClass;
        if (c == null) {
            this.codeClass = c = HttpStatusClass.valueOf(code);
        }
        return c;
    }
}
