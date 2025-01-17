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
package org.ballcat.autoconfigure.pay.virtual;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import live.lingting.virtual.currency.etherscan.endpoints.EtherscanEndpoints;

/**
 * @author lingting 2021/1/22 17:47
 */
@Data
@ConfigurationProperties(prefix = "ballcat.pay.ethereum")
public class EtherscanProperties {

	/**
	 * 是否开启
	 */
	private Boolean enabled = true;

	private EtherscanEndpoints endpoints;

	/**
	 * 使用 <a href="https://infura.io/dashboard">infura 平台</a>
	 */
	private Infura infura;

	@Data
	public static class Infura {

		/**
		 * 在 <a href="https://infura.io/dashboard"/> 注册后, 创建的app的 projectId
		 */
		private String projectId;

		/**
		 * projectSecret
		 */
		private String projectSecret;

	}

}
