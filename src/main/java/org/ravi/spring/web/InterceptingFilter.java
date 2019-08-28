package org.ravi.spring.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

public class InterceptingFilter extends AbstractRequestLoggingFilter {
	private static final Logger logger = LoggerFactory.getLogger(InterceptingFilter.class);
	// magic string from Spring internals !
	private static final String PAYLOAD_STRING = "payload=";

	enum MessageType {
		BEFORE, AFTER
	};

	@Override
	protected void beforeRequest(HttpServletRequest request, String message) {
	}

	@Override
	protected void afterRequest(HttpServletRequest request, String message) {
		show(request, MessageType.AFTER, message);
	}

	private void show(HttpServletRequest request, MessageType msgType, String message) {
		if (StringUtils.contains(message, PAYLOAD_STRING)) {
			logger.info("{}: message \n[{}]", msgType, message);
		} else if (isIncludePayload()) {
			// try {
			// String msg = new StringBuilder(4096).append("2: ").append(StringUtils.join(IOUtils.readLines(request.getReader()))).toString();
			//
			// // dumper.dumpRequestMessage(msgType, msg);
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
		}
	}
}
