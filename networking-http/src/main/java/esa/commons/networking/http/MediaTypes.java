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
package esa.commons.networking.http;

import esa.commons.Checks;
import esa.commons.StringUtils;
import esa.commons.function.Function3;
import esa.commons.http.MimeType;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static esa.commons.http.MimeType.parseMimeType;
import static esa.commons.http.MimeType.parseMimeTypes;

public class MediaTypes {

    /**
     * Media type for all
     */
    public static final MediaType ALL = of("*", "*");

    /**
     * String media type for all
     */
    public static final String ALL_VALUE = "*/*";

    /**
     * Media type for {@code application/x-www-form-urlencoded}.
     */
    public static final MediaType APPLICATION_FORM_URLENCODED
            = of("application", "x-www-form-urlencoded");

    /**
     * String media type for {@code application/x-www-form-urlencoded}.
     */
    public static final String APPLICATION_FORM_URLENCODED_VALUE
            = "application/x-www-form-urlencoded";

    /**
     * Media type for {@code application/json}.
     */
    public static final MediaType APPLICATION_JSON
            = of("application", "json");

    /**
     * String media type for {@code application/json}.
     */
    public static final String APPLICATION_JSON_VALUE
            = "application/json";

    /**
     * Media type for {@code application/json;charset=utf-8}.
     */
    public static final MediaType APPLICATION_JSON_UTF8
            = of("application", "json", StandardCharsets.UTF_8);

    /**
     * String media type for {@code application/json;charset=utf-8}.
     */
    public static final String APPLICATION_JSON_UTF8_VALUE
            = "application/json;charset=UTF-8";

    /**
     * Media type for {@code application/octet-stream}.
     */
    public static final MediaType APPLICATION_OCTET_STREAM
            = of("application", "octet-stream");

    /**
     * String media type for {@code application/octet-stream}.
     */
    public static final String APPLICATION_OCTET_STREAM_VALUE
            = "application/octet-stream";

    /**
     * Media type for {@code application/xml}.
     */
    public static final MediaType APPLICATION_XML
            = of("application", "xml");

    /**
     * String media type for {@code application/xml}.
     */
    public static final String APPLICATION_XML_VALUE
            = "application/xml";

    /**
     * Media type for {@code multipart/form-data}.
     */
    public static final MediaType MULTIPART_FORM_DATA
            = of("multipart", "form-data");

    /**
     * String media type for {@code multipart/form-data}.
     */
    public static final String MULTIPART_FORM_DATA_VALUE
            = "multipart/form-data";

    /**
     * Media type for {@code text/html}.
     */
    public static final MediaType TEXT_HTML
            = of("text", "html");

    /**
     * String media type for {@code text/html}.
     */
    public static final String TEXT_HTML_VALUE
            = "text/html";

    /**
     * Media type for {@code text/plain}.
     */
    public static final MediaType TEXT_PLAIN
            = of("text", "plain");

    /**
     * String media type for {@code text/plain}.
     */
    public static final String TEXT_PLAIN_VALUE
            = "text/plain";

    static final String Q_VALUE = "q";

    private static final Function3<String, String, Map<String, String>, MediaTypeImpl> GENERATOR
            = MediaTypeImpl::new;

    private static final ConcurrentHashMap<String, ParseResult> CACHE = new ConcurrentHashMap<>(16);

    /**
     * Parses media type string to instance of {@link MediaType}.
     *
     * @param mediaType media type string
     *
     * @return parsed
     */
    public static MediaType valueOf(String mediaType) {
        Checks.checkNotEmptyArg(mediaType, "mediaType");
        ParseResult cached = CACHE.get(mediaType);
        if (cached == null) {
            try {
                cached = ParseResult.ok(parseMediaType(mediaType));
            } catch (Exception e) {
                cached = ParseResult.error(e);
            }
            // same media type string should present a same result.
            CACHE.put(mediaType, cached);
        }

        if (cached.t != null) {
            if (cached.t instanceof IllegalArgumentException) {
                throw (IllegalArgumentException) cached.t;
            } else {
                throw new RuntimeException(cached.t);
            }
        } else {
            return cached.r;
        }
    }

    public static List<MediaTypeImpl> valuesOf(String mediaTypes) {
        return parseMimeTypes(mediaTypes, s -> (MediaTypeImpl) MediaTypes.valueOf(s));
    }

    public static void valuesOf(String mediaTypes, List<MediaType> target) {
        if (target == null) {
            return;
        }

        List<MediaTypeImpl> mimes = new LinkedList<>();
        parseMimeTypes(mediaTypes, s -> (MediaTypeImpl) (MediaTypes.valueOf(s)), mimes);

        target.addAll(mimes);
    }

    public static MediaType parseMediaType(String mediaType) {
        return parseMimeType(mediaType, GENERATOR);
    }

    /**
     * Parses media type string to a list of {@link MediaType}s. The given value of media type string should be
     * separated by ',', eg: 'application/json,text/plain'.
     *
     * @param mediaTypes mediaTypes
     *
     * @return parsed
     */
    public static List<MediaTypeImpl> parseMediaTypes(String mediaTypes) {
        return parseMimeTypes(mediaTypes, GENERATOR);
    }

    public static void sortBySpecificityAndQuality(List<MediaType> mediaTypes) {
        Checks.checkNotNull(mediaTypes, "'mediaTypes' must not be null");
        if (mediaTypes.size() > 1) {
            mediaTypes.sort(SPECIFICITY_COMPARATOR.thenComparing(QUALITY_VALUE_COMPARATOR));
        }
    }

    public static MediaType of(String type) {
        return new MediaTypeImpl(type);
    }

    public static MediaType of(String type, String subtype) {
        return new MediaTypeImpl(type, subtype);
    }

    public static MediaType of(String type, String subtype, Charset charset) {
        return new MediaTypeImpl(type, subtype, charset);
    }

    public static MediaType of(String type, String subtype, Map<String, String> parameters) {
        return new MediaTypeImpl(type, subtype, parameters);
    }

    public static MediaType of(MimeType other, Charset charset) {
        return new MediaTypeImpl(other, charset);
    }

    public static MediaType of(MimeType other, Map<String, String> parameters) {
        return new MediaTypeImpl(other, parameters);
    }

    public static MediaType copyQualityValue(MediaType from, MediaType to) {
        String qValue = from.param(Q_VALUE);
        if (StringUtils.isEmpty(qValue)) {
            return to;
        }
        Map<String, String> params = new LinkedHashMap<>(to.params());
        params.put(Q_VALUE, qValue);
        return MediaTypes.of(to.type(), to.subtype(), params);
    }

    public static final Comparator<MediaType> QUALITY_VALUE_COMPARATOR = (mediaType1, mediaType2) -> {
        double quality1 = mediaType1.qValue();
        double quality2 = mediaType2.qValue();
        return Double.compare(quality2, quality1);
    };

    private static MimeType convert(MediaType type) {
        if (type == null) {
            return null;
        }
        if (type instanceof MimeType) {
            return (MimeType) type;
        }

        return MimeType.of(type.type(), type.subtype(), type.params());
    }

    private static final MimeType.SpecificityComparator<MimeType> MIME_TYPE_SPECIFICITY_COMPARATOR
            = new MimeType.SpecificityComparator<>();

    public static final Comparator<MediaType> SPECIFICITY_COMPARATOR =
            (o1, o2) -> MIME_TYPE_SPECIFICITY_COMPARATOR.compare(convert(o1), convert(o2));

    private static class ParseResult {
        final MediaType r;
        final Throwable t;

        private ParseResult(MediaType r, Throwable t) {
            this.r = r;
            this.t = t;
        }

        static ParseResult ok(MediaType r) {
            return new ParseResult(r, null);
        }

        static ParseResult error(Throwable t) {
            return new ParseResult(null, t);
        }
    }

}

