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

import org.junit.Test;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class RabbitUtilsTest {

    private final ConnectionFactory connectionFactory = mock(ConnectionFactory.class);

    private final RabbitUtils rabbitUtils = new RabbitUtils(null);

    @Test
    public void checkAccessFailure() {
        when(this.connectionFactory.createConnection()).thenThrow(new UnsupportedOperationException("test-message"));
        assertThat(this.rabbitUtils.checkAccess(this.connectionFactory)).isEqualTo("failed with test-message");
    }

    @Test
    public void checkAccessOk() {
        assertThat(this.rabbitUtils.checkAccess(this.connectionFactory)).isEqualTo("ok");
    }

    @Test
    public void url() {
        when(this.connectionFactory.getHost()).thenReturn("test-host");
        when(this.connectionFactory.getVirtualHost()).thenReturn("test-virtual-host");
        assertThat(this.rabbitUtils.getUrl(this.connectionFactory)).isEqualTo("amqp://test-host/test-virtual-host");
    }

}
