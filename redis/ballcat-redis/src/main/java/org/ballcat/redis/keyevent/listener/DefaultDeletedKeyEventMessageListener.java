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
package org.ballcat.redis.keyevent.listener;

import org.ballcat.redis.keyevent.template.KeyDeletedEventMessageTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * default key deleted event handler
 *
 * @author lishangbu 2023/1/12
 */
@Slf4j
public class DefaultDeletedKeyEventMessageListener extends AbstractDeletedKeyEventMessageListener {

	protected List<KeyDeletedEventMessageTemplate> keyDeletedEventMessageTemplates;

	/**
	 * Creates new {@link MessageListener} for specific messages.
	 * @param listenerContainer must not be {@literal null}.
	 */
	public DefaultDeletedKeyEventMessageListener(RedisMessageListenerContainer listenerContainer) {
		super(listenerContainer);
	}

	public DefaultDeletedKeyEventMessageListener(RedisMessageListenerContainer listenerContainer,
			ObjectProvider<List<KeyDeletedEventMessageTemplate>> objectProvider) {
		super(listenerContainer);
		objectProvider.ifAvailable(templates -> this.keyDeletedEventMessageTemplates = new ArrayList<>(templates));
	}

	@Override
	public void onMessage(Message message, byte[] pattern) {
		if (CollectionUtils.isEmpty(keyDeletedEventMessageTemplates)) {
			return;
		}
		super.onMessage(message, pattern);
		String setKey = message.toString();
		// 监听key信息新增/修改事件
		for (KeyDeletedEventMessageTemplate keyDeletedEventMessageTemplate : keyDeletedEventMessageTemplates) {
			if (keyDeletedEventMessageTemplate.support(setKey)) {
				if (log.isTraceEnabled()) {
					log.trace("use template [{}] handle key deleted event,the deleted key is [{}]",
							keyDeletedEventMessageTemplate.getClass().getName(), setKey);
				}
				keyDeletedEventMessageTemplate.handleMessage(setKey);
			}
		}

	}

}
