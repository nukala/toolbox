package org.ravi.spring;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class SpringUtils {
	private static final Logger logger = LoggerFactory.getLogger(SpringUtils.class);

	public static <T> void deepPopulate(ApplicationContext context, Object container, T interceptor, Class<T> klass) {
		// if wiring doesnt work, try wiring manually-forcibly, if that too fails, give up
		if (interceptor == null) {
			if (context == null) {
				logger.error("{}: interceptor and context are both null, irrecovarable wiring problem",
						SpringUtils.class.getSimpleName());
				return;
			}
			context.getAutowireCapableBeanFactory().autowireBean(container);
			if (interceptor == null) {
				logger.error("inteceptor null, even after wiring-up manually, howcome ? ");
				List<String> axisBeans = Lists.newArrayList();
				for (String name : context.getBeanDefinitionNames()) {
					if (StringUtils.contains(name, "axis")) {
						axisBeans.add(name);
					}
				}
				throw new RuntimeException("All the availabe bean definitions [" + StringUtils.join(axisBeans, "; ") + "] no interceptor ?");
			}
		}
	}

	private List<String> namesAndTypes(ApplicationContext ctxt) {
		List<String> ret = Lists.newArrayList();
		for (String nm : ctxt.getBeanDefinitionNames()) {
			Object b = ctxt.getBean(nm);

			String typeName = b.getClass().getName();
			if (!StringUtils.contains(typeName, "CGLIB")) {
				ret.add(String.format("%s[%s]", nm, typeName));
			}
		}

		logger.warn("dumping all the bean names and classes=\n{}", Joiner.on("\n\t").join(namesAndTypes(ctxt)));
		return ret;
	}
}
