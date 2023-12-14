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
package org.ballcat.easyexcel.application;

import org.ballcat.easyexcel.annotation.RequestExcel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Hccake
 */
@RestController
@RequestMapping("/import")
public class ExcelImportTestController {

	@PostMapping(value = "/simple")
	public List<DemoData> simple(@RequestExcel List<DemoData> list) {
		return list;
	}

	@PostMapping(value = "/ignore-empty-row-enabled")
	public List<DemoData> ignoreEmptyRow(@RequestExcel(ignoreEmptyRow = true) List<DemoData> list) {
		return list;
	}

	@PostMapping(value= "/ignore-empty-row-disabled")
	public List<DemoData> notIgnoreEmptyRow(@RequestExcel(ignoreEmptyRow = false) List<DemoData> list) {
		return list;
	}

}
