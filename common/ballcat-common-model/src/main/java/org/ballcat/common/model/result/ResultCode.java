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
package org.ballcat.common.model.result;

/**
 * @author Hccake 2020/3/20 14:45
 */
public interface ResultCode {

	/**
	 * 获取业务码
	 * @return 业务码
	 */
	Integer getCode();

	/**
	 * 获取信息
	 * @return 返回结构体中的信息
	 */
	String getMessage();

}
