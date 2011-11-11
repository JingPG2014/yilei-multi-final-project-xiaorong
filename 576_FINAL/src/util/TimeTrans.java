package util;

import config.Configure;

public class TimeTrans {
	public static String intToTime(int i) {
		int min = i / (60 * Configure.FRAME_RATE);
		int second = i % (60 * Configure.FRAME_RATE) / Configure.FRAME_RATE;
		int remain = i % (60 * Configure.FRAME_RATE) % Configure.FRAME_RATE;

		return "" + min + " : " + second + " : " + remain;
	}
}
