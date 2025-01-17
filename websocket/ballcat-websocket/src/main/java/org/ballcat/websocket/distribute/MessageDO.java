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

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Hccake 2021/1/12
 *
 */
@Data
@Accessors(chain = true)
public class MessageDO {

	/**
	 * 是否广播
	 */
	private Boolean needBroadcast;

	/**
	 * 对于拥有相同 sessionKey 的客户端，仅对其中的一个进行发送
	 */
	private Boolean onlyOneClientInSameKey;

	/**
	 * 需要发送的 sessionKeys 集合，当广播时，不需要
	 */
	private List<Object> sessionKeys;

	/**
	 * 需要发送的消息文本
	 */
	private String messageText;

}
