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

import org.ballcat.web.accesslog.annotation.AccessLoggingRule;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/test")
@RestController
public class TestController {

	@GetMapping()
	public String testMethod() {
		return "Hello, World!";
	}

	@GetMapping("/ignored")
	public String ignoreLog() {
		return "Hello, World!";
	}

	@AccessLoggingRule(includeQueryString = true, includeResponseBody = true)
	@PostMapping("/annotation")
	public String annotation(@RequestBody String prefix) {
		return prefix + " Hello, World!";
	}

	@PostMapping("/record-all")
	public String recordAll(@RequestBody String prefix) {
		return prefix + " Hello, World!";
	}

}