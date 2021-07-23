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
import esa.commons.http.MimeType;

import java.nio.charset.Charset;
import java.util.Map;

class MediaTypeImpl extends MimeType implements MediaType {

    MediaTypeImpl(String type) {
        super(type);
    }

    MediaTypeImpl(String type, String subtype) {
        super(type, subtype);
    }

    MediaTypeImpl(String type, String subtype, Charset charset) {
        super(type, subtype, charset);
    }

    MediaTypeImpl(String type, String subtype, Map<String, String> parameters) {
        super(type, subtype, parameters);
    }

    @Override
    public String param(String name) {
        return getParameter(name);
    }

    @Override
    public Map<String, String> params() {
        return parameters();
    }

    @Override
    public boolean includes(MediaType other) {
        if (other == null) {
            return false;
        }
        if (other instanceof MimeType) {
            return super.includes((MimeType) other);
        }
        return super.includes(new MediaTypeImpl(other.type(), other.subtype()));
    }

    @Override
    public boolean isCompatibleWith(MediaType other) {
        if (other == null) {
            return false;
        }
        if (other instanceof MimeType) {
            return super.isCompatibleWith((MimeType) other);
        }
        return super.isCompatibleWith(new MediaTypeImpl(other.type(), other.subtype()));
    }

    @Override
    public double qValue() {
        String qualityFactor = getParameter(MediaTypeUtil.Q_VALUE);
        return (qualityFactor != null ? Double.parseDouble(StringUtils.unquote(qualityFactor)) : 1D);
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        return value().hashCode();
    }

}

