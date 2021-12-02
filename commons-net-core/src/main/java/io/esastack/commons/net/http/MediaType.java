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
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Interface defines <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec3.html#sec3.7">HTTP/1.1 section 3.7</a>
 */
public interface MediaType {

    /**
     * Media type for all
     */
    MediaType ALL = create("*", "*");

    /**
     * String media type for all
     */
    String ALL_VALUE = "*/*";

    /**
     * Media type for {@code application/x-www-form-urlencoded}.
     */
    MediaType APPLICATION_FORM_URLENCODED = create("application", "x-www-form-urlencoded");

    /**
     * String media type for {@code application/x-www-form-urlencoded}.
     */
    String APPLICATION_FORM_URLENCODED_VALUE = "application/x-www-form-urlencoded";

    /**
     * Media type for {@code application/json}.
     */
    MediaType APPLICATION_JSON = create("application", "json");

    /**
     * String media type for {@code application/json}.
     */
    String APPLICATION_JSON_VALUE = "application/json";

    /**
     * Media type for {@code application/json;charset=utf-8}.
     */
    MediaType APPLICATION_JSON_UTF8 = builder("application")
            .subtype("json")
            .charset(StandardCharsets.UTF_8)
            .build();

    /**
     * String media type for {@code application/json;charset=utf-8}.
     */
    String APPLICATION_JSON_UTF8_VALUE
            = "application/json;charset=UTF-8";

    /**
     * Media type for {@code application/octet-stream}.
     */
    MediaType APPLICATION_OCTET_STREAM = create("application", "octet-stream");

    /**
     * String media type for {@code application/octet-stream}.
     */
    String APPLICATION_OCTET_STREAM_VALUE = "application/octet-stream";

    /**
     * Media type for {@code application/xml}.
     */
    MediaType APPLICATION_XML = create("application", "xml");

    /**
     * String media type for {@code application/xml}.
     */
    String APPLICATION_XML_VALUE = "application/xml";

    /**
     * Media type for {@code multipart/form-data}.
     */
    MediaType MULTIPART_FORM_DATA = create("multipart", "form-data");

    /**
     * String media type for {@code multipart/form-data}.
     */
    String MULTIPART_FORM_DATA_VALUE = "multipart/form-data";

    /**
     * Media type for {@code text/html}.
     */
    MediaType TEXT_HTML = create("text", "html");

    /**
     * String media type for {@code text/html}.
     */
    String TEXT_HTML_VALUE = "text/html";

    /**
     * Media type for {@code text/plain}.
     */
    MediaType TEXT_PLAIN = create("text", "plain");

    /**
     * String media type for {@code text/plain}.
     */
    String TEXT_PLAIN_VALUE = "text/plain";
    
    /**
     * Creates a new instance by given {@code type} and use '*' as the subtype.
     */
    static MediaType create(String type) {
        return new MediaTypeImpl(type);
    }

    /**
     * Creates a new instance by given {@code type} and {@code subtype}.
     */
    static MediaType create(String type, String subtype) {
        return new MediaTypeImpl(type, subtype);
    }

    /**
     * Creates a {@link MediaTypeBuilder} by given {@code type}.
     */
    static MediaTypeBuilder builder(String type) {
        return new MediaTypeBuilderImpl(type);
    }

    /**
     * Creates a {@link MediaTypeBuilder} by given {@code type} and {@code subtype}.
     */
    static MediaTypeBuilder builder(String type, String subtype) {
        return new MediaTypeBuilderImpl(type).subtype(subtype);
    }

    /**
     * Obtains the type of current {@link MediaType}.
     *
     * @return type
     */
    String type();

    /**
     * Whether the {@link #type()} is wildcard or not.
     *
     * @return true if the result is wildcard, otherwise not.
     */
    boolean isWildcardType();

    /**
     * Obtains the subType of current {@link MediaType}.
     *
     * @return subType.
     */
    String subtype();

    /**
     * Whether the {@link #subtype()} is wildcard or not.
     *
     * @return true if the result is wildcard, otherwise not.
     */
    boolean isWildcardSubtype();

    /**
     * Obtains the parameter value of given {@code name}.
     *
     * @param name name
     * @return value
     */
    String param(String name);

    /**
     * Obtains the unmodifiable parameter maps.
     *
     * @return params
     */
    Map<String, String> params();

    /**
     * Obtains the charset parsed from {@link #params()}.
     *
     * @return charset
     */
    Charset charset();

    /**
     * Whether the current {@link MediaType} includes {@code other} or not.
     *
     * @param other other
     * @return true if the current media type includes other, otherwise false.
     */
    boolean includes(MediaType other);

    /**
     * Whether the current {@link MediaType} is compatible with {@code other} or not.
     *
     * @param other other
     * @return true if the current media type is compatible with other, otherwise false.
     */
    boolean isCompatibleWith(MediaType other);

    /**
     * Obtains the qValue of the {@link MediaType}.
     *
     * @return the value of parameter "q".
     */
    double qValue();

    /**
     * Obtains the string value of the {@link MediaType}.
     *
     * @return the string value of the media type.
     */
    String value();

}

