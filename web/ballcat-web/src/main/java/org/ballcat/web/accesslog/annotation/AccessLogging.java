package org.ballcat.web.accesslog.annotation;

import java.lang.annotation.*;

/**
 * @author Alickx
 * @date 2023/11/23 17:48
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLogging {

	/**
	 * 是否记录 body
	 *
	 * @return boolean
	 */
	boolean recordBody() default true;

}
