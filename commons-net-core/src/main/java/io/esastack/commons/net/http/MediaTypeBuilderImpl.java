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

import esa.commons.StringUtils;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

class MediaTypeBuilderImpl implements MediaTypeBuilder {
    private static final String CHARSET_KEY = "charset";

    private final String type;
    private String subtype;
    private Map<String, String> params;

    MediaTypeBuilderImpl(String type) {
        this.type = type;
    }

    @Override
    public MediaTypeBuilder subtype(String subtype) {
        this.subtype = subtype;
        return this;
    }

    @Override
    public MediaTypeBuilder charset(String charset) {
        params().put(CHARSET_KEY, charset);
        return this;
    }

    @Override
    public MediaTypeBuilder addParam(String key, String value) {
        params().put(key, value);
        return this;
    }

    @Override
    public MediaTypeBuilder addParams(Map<String, String> params) {
        params().putAll(params);
        return this;
    }

    @Override
    public MediaType build() {
        return new MediaTypeImpl(type,
                StringUtils.nonEmptyOrElse(subtype, MediaTypeImpl.WILDCARD_TYPE),
                params == null ? Collections.emptyMap() : params);
    }

    private Map<String, String> params() {
        if (this.params == null) {
            this.params = new LinkedHashMap<>();
        }
        return params;
    }
}
