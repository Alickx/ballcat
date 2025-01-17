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
package org.ballcat.tesseract;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

/**
 * @author lingting
 */
class TesseractImageTest {

	static final TesseractImage image;

	static {
		TesseractImage image1;
		try {
			image1 = new TesseractImage("C:\\Users\\lingting\\Pictures\\rgb.jpg");
		}
		catch (IOException e) {
			image1 = null;
		}
		image = image1;
	}

	@Test
	@SneakyThrows(IOException.class)
	void rgb() {
		final String write = image.rgb().write();
		System.out.println(write);
		Assertions.assertNotNull(write);
	}

	@Test
	@SneakyThrows(IOException.class)
	void crop() {
		final Rectangle rectangle = new Rectangle();
		rectangle.setLocation(238, 268);
		rectangle.setSize(100, 100);

		final String write = image.crop(rectangle).write();
		System.out.println(write);
		Assertions.assertNotNull(write);
	}

}
