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
package org.ballcat.redis.prefix.impl;

import org.ballcat.redis.prefix.IRedisPrefixConverter;

/**
 * redis key前缀默认转换器
 *
 * @author huyuanzhi
 */
public class DefaultRedisPrefixConverter implements IRedisPrefixConverter {

	private final String prefix;

	public DefaultRedisPrefixConverter(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public String getPrefix() {
		return prefix;
	}

	@Override
	public boolean match() {
		return true;
	}

}
