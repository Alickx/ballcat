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
package org.ballcat.easyexcel.head;

/**
 * Excel头生成器，用于自定义生成头部信息
 *
 * @author Hccake 2020/10/27
 *
 */
public interface HeadGenerator {

	/**
	 * <p>
	 * 自定义头部信息
	 * </p>
	 * 实现类根据数据的class信息，定制Excel头<br/>
	 * 具体方法使用参考：<a href="https://www.yuque.com/easyexcel/doc/write#b4b9de00">Excel 写入</a>
	 * @param clazz 当前sheet的数据类型
	 * @return List<List < String>> Head头信息
	 */
	HeadMeta head(Class<?> clazz);

}
