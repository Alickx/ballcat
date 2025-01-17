/*
 * Copyright 2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ballcat.autoconfigure.idempotent;

import org.ballcat.idempotent.key.store.IdempotentKeyStore;
import org.ballcat.idempotent.key.store.InMemoryIdempotentKeyStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * 测试幂等装配
 *
 * @author lishangbu
 * @since 2022/10/18
 */
@SpringBootTest(classes = IdempotentAutoConfiguration.class)
class IdempotentTest {

	@Autowired
	private IdempotentKeyStore idempotentKeyStore;

	@Test
	void test() {
		Assertions.assertInstanceOf(InMemoryIdempotentKeyStore.class, idempotentKeyStore);
		idempotentKeyStore.saveIfAbsent("test", 1, TimeUnit.SECONDS);
	}

}
