package io.esastack.commons.net.http;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MediaTypeBuilderTest {

    @Test
    void testBuild() {
        final MediaType mediaType = MediaType.builder("foo")
                .subtype("bar")
                .charset(StandardCharsets.UTF_8)
                .addParam("1", "1")
                .build();
        assertEquals("foo/bar;charset=UTF-8;1=1", mediaType.value());
    }

    @Test
    void testSubtypeDefaultToWildcard() {
        assertEquals("*", MediaType.builder("foo").build().subtype());
    }

}
