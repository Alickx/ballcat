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
package org.ballcat.web.exception.notice;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 异常通知配置
 *
 * @author Hccake
 * @since 2.0.0
 */
@Data
public class ExceptionNoticeConfig {

	/**
	 * 是否同时忽略 配置的忽略异常类的子类, 默认忽略子类
	 */
	private Boolean ignoreChild = true;

	/**
	 * 忽略指定异常
	 */
	private Set<Class<? extends Throwable>> ignoreExceptions = new HashSet<>();

	/**
	 * 通知间隔时间 单位秒 默认 5分钟
	 */
	private long time = TimeUnit.MINUTES.toSeconds(5);

	/**
	 * 消息阈值 即便间隔时间没有到达设定的时间， 但是异常发生的数量达到阈值 则立即发送消息
	 */
	private int max = 5;

	/**
	 * 堆栈转string 的长度
	 */
	private int length = 3000;

}
