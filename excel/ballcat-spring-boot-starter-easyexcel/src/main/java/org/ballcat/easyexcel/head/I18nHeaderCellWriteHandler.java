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

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.PropertyPlaceholderHelper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 对表头进行国际化处理
 *
 * @author hccake
 */
@RequiredArgsConstructor
public class I18nHeaderCellWriteHandler implements CellWriteHandler {

	/**
	 * 国际化消息源
	 */
	private final MessageSource messageSource;

	/**
	 * 国际化翻译
	 */
	private final PropertyPlaceholderHelper.PlaceholderResolver placeholderResolver;

	public I18nHeaderCellWriteHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
		this.placeholderResolver = placeholderName -> this.messageSource.getMessage(placeholderName, null,
				LocaleContextHolder.getLocale());
	}

	/**
	 * 占位符处理
	 */
	private final PropertyPlaceholderHelper propertyPlaceholderHelper = new PropertyPlaceholderHelper("{", "}");

	@Override
	public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row,
			Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {
		if (isHead != null && isHead) {
			List<String> originHeadNameList = head.getHeadNameList();
			if (CollectionUtils.isNotEmpty(originHeadNameList)) {
				// 国际化处理
				List<String> i18nHeadNames = originHeadNameList.stream()
					.map(headName -> propertyPlaceholderHelper.replacePlaceholders(headName, placeholderResolver))
					.collect(Collectors.toList());
				head.setHeadNameList(i18nHeadNames);
			}
		}
	}

}
