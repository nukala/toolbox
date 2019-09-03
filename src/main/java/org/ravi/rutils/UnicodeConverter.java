package org.ravi.rutils;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.List;

public class UnicodeConverter {
	// so=3585053
	static CharsetEncoder asciiEncoder = Charset.forName("US-ASCII").newEncoder(); // or "ISO-8859-1" for ISO Latin 1

	public static boolean isPureAscii(String v) {
		return asciiEncoder.canEncode(v);
	}

	public static boolean isPureAscii(char ch) {
		return asciiEncoder.canEncode(ch);
	}

	public String convert(String rawString) {
		String toClean = rawString;
		if (StringUtils.contains(toClean, "=")) {
			List<String> parts = Splitter.on("=").splitToList(toClean);
			String key = null;
			StringBuilder kvSb = new StringBuilder(512);
			for (String part : parts) {
				if (key == null) {
					key = StringUtils.trimToEmpty(part);
					if (!StringUtils.endsWith(key, ".name")) {
						key += ".name";
					}

					kvSb.append(key).append("=");
					continue;
				}

				kvSb.append(part);
			}
			toClean = kvSb.toString();
		}

		StringBuilder sb = new StringBuilder(StringUtils.length(toClean) + 100);

		for (char ch : toClean.toCharArray()) {
			if (isPureAscii(ch)) {
				sb.append(ch);
			} else {
				int ich = ch;
				sb.append(String.format("\\u%04x", ich));
			}
		}

		String ret = StringUtils.trimToEmpty(sb.toString());
		return ret.replaceAll("\\s*=\\s*", "=");
	}

	public String toRaw(String unicodeString) {
		return unescapeJava(unicodeString);
	}

	public String unescapeJava(String unicodeString) {
		return StringEscapeUtils.unescapeJava(unicodeString);
	}
}
