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
package org.ballcat.autoconfigure.web.accesslog;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ballcat.web.accesslog.*;
import org.ballcat.web.accesslog.annotation.AccessLogRuleFinder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;

/**
 * @author Hccake 2019/10/15 18:20
 */
@Slf4j
@ConditionalOnWebApplication
@RequiredArgsConstructor
@EnableConfigurationProperties(AccessLogProperties.class)
@ConditionalOnProperty(prefix = AccessLogProperties.PREFIX, name = "enabled", havingValue = "true")
public class AccessLogAutoConfiguration {

	private final AccessLogProperties accessLogProperties;

	private final RequestMappingHandlerMapping requestMappingHandlerMapping;

	@Bean
	@ConditionalOnMissingBean(AccessLogFilter.class)
	public AccessLogFilter defaultAccessLogFilter() {
		// 合并 annotationRules 和 propertiesRules, 注解高于配置
		List<AccessLogRule> annotationRules = AccessLogRuleFinder.findRulesFormAnnotation(requestMappingHandlerMapping);
		List<AccessLogRule> propertiesRules = accessLogProperties.getAccessLogRules();
		List<AccessLogRule> accessLogRules = AccessLogRuleFinder.mergeRules(annotationRules, propertiesRules);

		AccessLogRecordOptions defaultRecordOptions = accessLogProperties.getDefaultAccessLogRecordOptions();

		AbstractAccessLogFilter accessLogFilter = new DefaultAccessLogFilter(defaultRecordOptions, accessLogRules);
		accessLogFilter.setMaxBodyLength(accessLogProperties.getMaxBodyLength());
		accessLogFilter.setOrder(accessLogProperties.getFilterOrder());
		return accessLogFilter;
	}

	/**
	 * 注册 AccessLogFilter 过滤器
	 * @param accessLogFilter 访问日志过滤器
	 * @return FilterRegistrationBean<AccessLogFilter>
	 */
	@Bean
	@ConditionalOnBean(AccessLogFilter.class)
	public FilterRegistrationBean<AccessLogFilter> accessLogFilterCloseRegistrationBean(
			AccessLogFilter accessLogFilter) {
		FilterRegistrationBean<AccessLogFilter> registrationBean = new FilterRegistrationBean<>(accessLogFilter);
		registrationBean.setEnabled(false);
		return registrationBean;
	}

}
