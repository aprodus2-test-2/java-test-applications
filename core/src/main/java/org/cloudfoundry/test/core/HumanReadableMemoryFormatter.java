/*
 * Copyright 2014-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cloudfoundry.test.core;

final class HumanReadableMemoryFormatter {

    private static final double BYTE = 1;

    private static final double KIBI = 1024 * BYTE;

    private static final double MIBI = 1024 * KIBI;

    private static final double GIBI = 1048 * MIBI;

    static String toIbi(long size) {
        if (size > GIBI) {
            return String.format("%.1f GiB", (size / GIBI));
        } else if (size > MIBI) {
            return String.format("%.1f MiB", (size / MIBI));
        } else if (size > KIBI) {
            return String.format("%.1f KiB", (size / KIBI));
        } else {
            return String.format("%d B", size);
        }
    }

}
