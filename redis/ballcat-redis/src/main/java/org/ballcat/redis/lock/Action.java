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
package org.ballcat.redis.lock;

import org.ballcat.redis.config.CachePropertiesHolder;
import org.ballcat.redis.lock.function.ThrowingExecutor;

import java.util.concurrent.TimeUnit;

/**
 * @author huyuanzhi 锁住的方法
 * @param <T> 返回类型
 */
@FunctionalInterface
public interface Action<T> {

	/**
	 * 执行方法,采用配置文件中的默认过期时间
	 * @param lockKey 待锁定的 key
	 * @param supplier 执行方法
	 * @return 状态处理器
	 */
	default StateHandler<T> action(String lockKey, ThrowingExecutor<T> supplier) {
		return action(lockKey, CachePropertiesHolder.defaultLockTimeout(), TimeUnit.SECONDS, supplier);
	}

	/**
	 * 执行方法
	 * @param lockKey 待锁定的 key
	 * @param timeout 锁定过期时间,默认单位秒
	 * @param supplier 执行方法
	 * @return 状态处理器
	 */
	default StateHandler<T> action(String lockKey, long timeout, ThrowingExecutor<T> supplier) {
		return action(lockKey, timeout, TimeUnit.SECONDS, supplier);
	}

	/**
	 * 执行方法
	 * @param lockKey 待锁定的 key
	 * @param timeout 锁定过期时间
	 * @param timeUnit 锁定过期时间单位
	 * @param supplier 执行方法
	 * @return 状态处理器
	 */
	StateHandler<T> action(String lockKey, long timeout, TimeUnit timeUnit, ThrowingExecutor<T> supplier);

}
