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
import esa.commons.StringUtils;
import esa.commons.function.Function3;
import esa.commons.http.MimeType;

import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static esa.commons.http.MimeType.parseMimeType;
import static esa.commons.http.MimeType.parseMimeTypes;

public final class MediaTypeUtil {

    static final String Q_VALUE = "q";

    private static final Function3<String, String, Map<String, String>, MediaTypeImpl> GENERATOR
            = MediaTypeImpl::new;

    private static final ConcurrentHashMap<String, ParseResult> CACHE = new ConcurrentHashMap<>(16);

    /**
     * Parses media type string to instance of {@link MediaType}.
     *
     * @param mediaType media type string
     * @return parsed
     */
    public static MediaType parseMediaType(String mediaType) {
        Checks.checkNotEmptyArg(mediaType, "mediaType");
        ParseResult cached = CACHE.get(mediaType);
        if (cached == null) {
            try {
                cached = ParseResult.ok(parseMediaType0(mediaType));
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

    public static List<MediaType> parseMediaTypes(String mediaTypes) {
        List<MediaType> parsed = new LinkedList<>();
        parsed.addAll(parseMimeTypes(mediaTypes, s -> (MediaTypeImpl) MediaTypeUtil.parseMediaType(s)));
        return parsed;
    }

    public static void parseMediaTypes(String mediaTypes, List<MediaType> target) {
        if (target == null) {
            return;
        }

        List<MediaTypeImpl> mimes = new LinkedList<>();
        parseMimeTypes(mediaTypes, s -> (MediaTypeImpl) (MediaTypeUtil.parseMediaType(s)), mimes);

        target.addAll(mimes);
    }

    private static MediaType parseMediaType0(String mediaType) {
        return parseMimeType(mediaType, GENERATOR);
    }

    public static void sortBySpecificityAndQuality(List<MediaType> mediaTypes) {
        Checks.checkNotNull(mediaTypes, "'mediaTypes' must not be null");
        if (mediaTypes.size() > 1) {
            mediaTypes.sort(SPECIFICITY_COMPARATOR.thenComparing(QUALITY_VALUE_COMPARATOR));
        }
    }

    public static MediaType copyQualityValue(MediaType from, MediaType to) {
        String qValue = from.param(Q_VALUE);
        if (StringUtils.isEmpty(qValue)) {
            return to;
        }
        Map<String, String> params = new LinkedHashMap<>(to.params());
        params.put(Q_VALUE, qValue);
        return new MediaTypeImpl(to.type(), to.subtype(), params);
    }

    public static final Comparator<MediaType> QUALITY_VALUE_COMPARATOR = (mediaType1, mediaType2) -> {
        double quality1 = mediaType1.qValue();
        double quality2 = mediaType2.qValue();
        return Double.compare(quality2, quality1);
    };

    private static final MimeType.SpecificityComparator<MimeType> MIME_TYPE_SPECIFICITY_COMPARATOR
            = new MimeType.SpecificityComparator<>();

    public static final Comparator<MediaType> SPECIFICITY_COMPARATOR =
            (o1, o2) -> MIME_TYPE_SPECIFICITY_COMPARATOR.compare(convert(o1), convert(o2));

    private static MimeType convert(MediaType type) {
        if (type == null) {
            return null;
        }
        if (type instanceof MimeType) {
            return (MimeType) type;
        }

        return MimeType.of(type.type(), type.subtype(), type.params());
    }

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

    private MediaTypeUtil() {
    }
}

