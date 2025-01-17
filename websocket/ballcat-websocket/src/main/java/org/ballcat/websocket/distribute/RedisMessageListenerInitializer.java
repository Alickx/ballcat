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
package org.ballcat.websocket.distribute;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import javax.annotation.PostConstruct;

/**
 * 初始化 redis 消息的监听器
 *
 * @author Hccake
 */
@RequiredArgsConstructor
public class RedisMessageListenerInitializer {

	private final RedisMessageListenerContainer redisMessageListenerContainer;

	private final RedisMessageDistributor redisWebsocketMessageListener;

	@PostConstruct
	public void addMessageListener() {
		redisMessageListenerContainer.addMessageListener(redisWebsocketMessageListener,
				new PatternTopic(RedisMessageDistributor.CHANNEL));
	}

}