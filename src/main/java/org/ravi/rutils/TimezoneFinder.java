package org.ravi.rutils;

import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

public class TimezoneFinder {
	boolean isShowable(TimeZone tz, String to) {
		boolean ret = false;

		if (StringUtils.equals(to, "*")) {
			return true;
		}
		else if (StringUtils.containsIgnoreCase(tz.toString(), to)) {
			ret = true;
		} else if (StringUtils.containsIgnoreCase(tz.getDisplayName(), to)) {
			ret = true;
		} else if (StringUtils.containsIgnoreCase(tz.getID(), to)) {
			ret = true;
		}

		return ret;
	}
	void show(String to) {
		for (String tzId : TimeZone.getAvailableIDs()) {
			TimeZone tz = TimeZone.getTimeZone(tzId);
			if (isShowable(tz, to)) {
				System.out.printf("id=%s, offset=%d, display=[%s] str=[%s]%n", tz.getID(), tz.getRawOffset(), tz.getDisplayName(), tz);
			}
		}
	}

	public static void main(String ...args) {
		String to = args.length >= 1 ? args[0] : "beijing";

		TimezoneFinder tzf = new TimezoneFinder();
		tzf.show(to);
	}
}
