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
 * HTTP status codes.
 */
public interface HttpStatus {

    HttpStatus CONTINUE = create(100, "Continue");
    HttpStatus SWITCHING_PROTOCOLS = create(101, "Switching Protocols");
    HttpStatus PROCESSING = create(102, "Processing");
    HttpStatus OK = create(200, "OK");
    HttpStatus CREATED = create(201, "Created");
    HttpStatus ACCEPTED = create(202, "Accepted");
    HttpStatus NON_AUTHORITATIVE_INFORMATION = create(203, "Non-Authoritative Information");
    HttpStatus NO_CONTENT = create(204, "No Content");
    HttpStatus RESET_CONTENT = create(205, "Reset Content");
    HttpStatus PARTIAL_CONTENT = create(206, "Partial Content");
    HttpStatus MULTI_STATUS = create(207, "Multi-Status");
    HttpStatus MULTIPLE_CHOICES = create(300, "Multiple Choices");
    HttpStatus MOVED_PERMANENTLY = create(301, "Moved Permanently");
    HttpStatus FOUND = create(302, "Found");
    HttpStatus SEE_OTHER = create(303, "See Other");
    HttpStatus NOT_MODIFIED = create(304, "Not Modified");
    HttpStatus USE_PROXY = create(305, "Use Proxy");
    HttpStatus TEMPORARY_REDIRECT = create(307, "Temporary Redirect");
    HttpStatus PERMANENT_REDIRECT = create(308, "Permanent Redirect");
    HttpStatus BAD_REQUEST = create(400, "Bad Request");
    HttpStatus UNAUTHORIZED = create(401, "Unauthorized");
    HttpStatus PAYMENT_REQUIRED = create(402, "Payment Required");
    HttpStatus FORBIDDEN = create(403, "Forbidden");
    HttpStatus NOT_FOUND = create(404, "Not Found");
    HttpStatus METHOD_NOT_ALLOWED = create(405, "Method Not Allowed");
    HttpStatus NOT_ACCEPTABLE = create(406, "Not Acceptable");
    HttpStatus PROXY_AUTHENTICATION_REQUIRED = create(407, "Proxy Authentication Required");
    HttpStatus REQUEST_TIMEOUT = create(408, "Request Timeout");
    HttpStatus CONFLICT = create(409, "Conflict");
    HttpStatus GONE = create(410, "Gone");
    HttpStatus LENGTH_REQUIRED = create(411, "Length Required");
    HttpStatus PRECONDITION_FAILED = create(412, "Precondition Failed");
    HttpStatus REQUEST_ENTITY_TOO_LARGE = create(413, "Request Entity Too Large");
    HttpStatus REQUEST_URI_TOO_LONG = create(414, "Request-URI Too Long");
    HttpStatus UNSUPPORTED_MEDIA_TYPE = create(415, "Unsupported Media Type");
    HttpStatus REQUESTED_RANGE_NOT_SATISFIABLE = create(416, "Requested Range Not Satisfiable");
    HttpStatus EXPECTATION_FAILED = create(417, "Expectation Failed");
    HttpStatus MISDIRECTED_REQUEST = create(421, "Misdirected Request");
    HttpStatus UNPROCESSABLE_ENTITY = create(422, "Unprocessable Entity");
    HttpStatus LOCKED = create(423, "Locked");
    HttpStatus FAILED_DEPENDENCY = create(424, "Failed Dependency");
    HttpStatus UNORDERED_COLLECTION = create(425, "Unordered Collection");
    HttpStatus UPGRADE_REQUIRED = create(426, "Upgrade Required");
    HttpStatus PRECONDITION_REQUIRED = create(428, "Precondition Required");
    HttpStatus TOO_MANY_REQUESTS = create(429, "Too Many Requests");
    HttpStatus REQUEST_HEADER_FIELDS_TOO_LARGE = create(431, "Request Header Fields Too Large");
    HttpStatus INTERNAL_SERVER_ERROR = create(500, "Internal Server Error");
    HttpStatus NOT_IMPLEMENTED = create(501, "Not Implemented");
    HttpStatus BAD_GATEWAY = create(502, "Bad Gateway");
    HttpStatus SERVICE_UNAVAILABLE = create(503, "Service Unavailable");
    HttpStatus GATEWAY_TIMEOUT = create(504, "Gateway Timeout");
    HttpStatus HTTP_VERSION_NOT_SUPPORTED = create(505, "HTTP Version Not Supported");
    HttpStatus VARIANT_ALSO_NEGOTIATES = create(506, "Variant Also Negotiates");
    HttpStatus INSUFFICIENT_STORAGE = create(507, "Insufficient Storage");
    HttpStatus NOT_EXTENDED = create(510, "Not Extended");
    HttpStatus NETWORK_AUTHENTICATION_REQUIRED = create(511, "Network Authentication Required");

    /**
     * Returns the constant of this type with the given {@code code}.
     *
     * @param code status code
     * @return constant of  given {@code code} or {@code null} if missing
     */
    static HttpStatus valueOf(int code) {
        switch (code) {
            case 100:
                return CONTINUE;
            case 101:
                return SWITCHING_PROTOCOLS;
            case 102:
                return PROCESSING;
            case 200:
                return OK;
            case 201:
                return CREATED;
            case 202:
                return ACCEPTED;
            case 203:
                return NON_AUTHORITATIVE_INFORMATION;
            case 204:
                return NO_CONTENT;
            case 205:
                return RESET_CONTENT;
            case 206:
                return PARTIAL_CONTENT;
            case 207:
                return MULTI_STATUS;
            case 300:
                return MULTIPLE_CHOICES;
            case 301:
                return MOVED_PERMANENTLY;
            case 302:
                return FOUND;
            case 303:
                return SEE_OTHER;
            case 304:
                return NOT_MODIFIED;
            case 305:
                return USE_PROXY;
            case 307:
                return TEMPORARY_REDIRECT;
            case 308:
                return PERMANENT_REDIRECT;
            case 400:
                return BAD_REQUEST;
            case 401:
                return UNAUTHORIZED;
            case 402:
                return PAYMENT_REQUIRED;
            case 403:
                return FORBIDDEN;
            case 404:
                return NOT_FOUND;
            case 405:
                return METHOD_NOT_ALLOWED;
            case 406:
                return NOT_ACCEPTABLE;
            case 407:
                return PROXY_AUTHENTICATION_REQUIRED;
            case 408:
                return REQUEST_TIMEOUT;
            case 409:
                return CONFLICT;
            case 410:
                return GONE;
            case 411:
                return LENGTH_REQUIRED;
            case 412:
                return PRECONDITION_FAILED;
            case 413:
                return REQUEST_ENTITY_TOO_LARGE;
            case 414:
                return REQUEST_URI_TOO_LONG;
            case 415:
                return UNSUPPORTED_MEDIA_TYPE;
            case 416:
                return REQUESTED_RANGE_NOT_SATISFIABLE;
            case 417:
                return EXPECTATION_FAILED;
            case 421:
                return MISDIRECTED_REQUEST;
            case 422:
                return UNPROCESSABLE_ENTITY;
            case 423:
                return LOCKED;
            case 424:
                return FAILED_DEPENDENCY;
            case 425:
                return UNORDERED_COLLECTION;
            case 426:
                return UPGRADE_REQUIRED;
            case 428:
                return PRECONDITION_REQUIRED;
            case 429:
                return TOO_MANY_REQUESTS;
            case 431:
                return REQUEST_HEADER_FIELDS_TOO_LARGE;
            case 500:
                return INTERNAL_SERVER_ERROR;
            case 501:
                return NOT_IMPLEMENTED;
            case 502:
                return BAD_GATEWAY;
            case 503:
                return SERVICE_UNAVAILABLE;
            case 504:
                return GATEWAY_TIMEOUT;
            case 505:
                return HTTP_VERSION_NOT_SUPPORTED;
            case 506:
                return VARIANT_ALSO_NEGOTIATES;
            case 507:
                return INSUFFICIENT_STORAGE;
            case 510:
                return NOT_EXTENDED;
            case 511:
                return NETWORK_AUTHENTICATION_REQUIRED;
            default:
                return null;
        }
    }

    /**
     * Creates a new instance of {@link HttpStatus} by given {@code code} and {@code reasonPhrase}.
     */
    static HttpStatus create(int code, String reasonPhrase) {
        return new HttpStatusImpl(code, reasonPhrase);
    }

    /**
     * status code
     */
    int code();

    /**
     * Reason phrase of this status.
     */
    String reasonPhrase();

    /**
     * Code class of this status.
     *
     * @see HttpStatusClass
     */
    HttpStatusClass codeClass();

}
