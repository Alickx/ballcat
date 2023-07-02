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
package org.ballcat.common.core.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * WebUtils
 *
 * @author Hccake
 */
@Slf4j
@UtilityClass
public class WebUtils extends org.springframework.web.util.WebUtils {

	/**
	 * 获取 ServletRequestAttributes
	 * @return {ServletRequestAttributes}
	 */
	public ServletRequestAttributes getServletRequestAttributes() {
		return (ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
	}

	/**
	 * 获取 HttpServletRequest
	 * @return {HttpServletRequest}
	 */
	public HttpServletRequest getRequest() {
		return getServletRequestAttributes().getRequest();
	}

	/**
	 * 获取 HttpServletResponse
	 * @return {HttpServletResponse}
	 */
	public HttpServletResponse getResponse() {
		return getServletRequestAttributes().getResponse();
	}

}