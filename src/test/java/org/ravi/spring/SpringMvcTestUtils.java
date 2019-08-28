package org.ravi.spring;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;

import com.google.common.collect.Lists;

public class SpringMvcTestUtils {

	public static MockMvc buildMockMvc(WebApplicationContext wac) {
		assertNotNull("webapplicationcontext", wac);

		return MockMvcBuilders.webAppContextSetup(wac).build();
	}

	public static MockMvc standaloneMockMvc(Object controller, HttpMessageConverter<?> converterToPrepend) {
		assertNotNull("controller object", controller);
		assertTrue("is a controller", controller.getClass().getAnnotation(Controller.class) != null);

		// when using standalone setup, system overrides converters ...
		// mockMvc = MockMvcBuilders.standaloneSetup(toTest).build();

		StandaloneMockMvcBuilder smmb = MockMvcBuilders.standaloneSetup(controller);
		if (converterToPrepend != null) {
			@SuppressWarnings("unchecked")
			HttpMessageConverter<?> ary[] = (HttpMessageConverter<?>[]) Lists.newArrayList(converterToPrepend).toArray();

			smmb.setMessageConverters(ary);
		}

		return smmb.build();
	}
}
