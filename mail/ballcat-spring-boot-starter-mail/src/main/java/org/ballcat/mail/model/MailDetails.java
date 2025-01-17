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
package org.ballcat.mail.model;

import lombok.Data;

import java.io.File;

/**
 * @author Hccake 2020/2/27 17:06
 */
@Data
public class MailDetails {

	/**
	 * 发件人
	 */
	private String from;

	/**
	 * 收件人
	 */
	private String[] to;

	/**
	 * 邮件主题
	 */
	private String subject;

	/**
	 * 是否渲染html
	 */
	private Boolean showHtml;

	/**
	 * 邮件内容
	 */
	private String content;

	/**
	 * 抄送
	 */
	private String[] cc;

	/**
	 * 密送
	 */
	private String[] bcc;

	/**
	 * 附件
	 */
	private File[] files;

}
