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
import java.util.Map;

/**
 * Interface defines <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec3.html#sec3.7">HTTP/1.1 section 3.7</a>
 */
public interface MediaType {

    /**
     * Obtains the type of current {@link MediaType}.
     *
     * @return  type
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
     * @return  subType.
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
     * @return  value
     */
    String param(String name);

    /**
     * Obtains the unmodifiable parameter maps.
     *
     * @return  params
     */
    Map<String, String> params();

    /**
     * Obtains the charset parsed from {@link #params()}.
     *
     * @return  charset
     */
    Charset charset();

    /**
     * Whether the current {@link MediaType} includes {@code other} or not.
     *
     * @param other other
     * @return  true if the current media type includes other, otherwise false.
     */
    boolean includes(MediaType other);

    /**
     * Whether the current {@link MediaType} is compatible with {@code other} or not.
     *
     * @param other other
     * @return  true if the current media type is compatible with other, otherwise false.
     */
    boolean isCompatibleWith(MediaType other);

    /**
     * Obtains the qValue of the {@link MediaType}.
     *
     * @return  the value of parameter "q".
     */
    double qValue();

    /**
     * Obtains the string value of the {@link MediaType}.
     *
     * @return  the string value of the media type.
     */
    String value();

}

