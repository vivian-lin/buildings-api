package com.msr.util;

import io.micrometer.core.instrument.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * File: ClasspathUtil
 *
 * @author Measurabl
 * @since 2019-06-11
 */
public class ClasspathUtil {
    public static String readFileToString(String path, Class aClass) throws IOException {

        try (InputStream stream = aClass.getClassLoader()
                .getResourceAsStream(path)) {
            if (stream == null) {
                throw new IOException("Stream is null");
            }
            return IOUtils.toString(stream, Charset.defaultCharset());
        }
    }
}

////////////////////////////////////////////////////////////
// Copyright 2018  Measurabl, Inc. All rights reserved.
////////////////////////////////////////////////////////////
    