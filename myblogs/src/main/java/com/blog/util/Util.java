package com.blog.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	/** Logger. */
	private static final int MONTH_DAY_NUM[] = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public static boolean isNullStr(String str) {
		if (Util.isNull(str))
			return true;
		else
			return ("nil".equalsIgnoreCase(str) || "null".equalsIgnoreCase(str));
	}

	public static boolean isNotNullStr(String str) {
		return !isNullStr(str);
	}

	public static boolean isNull(String str) {
		return (str == null || str.trim().length() == 0);
	}

	public static boolean isNotNull(String str) {
		return !isNull(str);
	}

	public static String getTimeStr(Date time) {
		if (time == null)
			return "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(time);
	}

	public static String getDayStr(Date day) {
		if (day == null)
			return "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(day);
	}

	public static String getDayStr() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return dateFormat.format(Calendar.getInstance().getTime());
	}

	public static String getHourStr(Date day) {
		if (day == null)
			return "";
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		return dateFormat.format(day);
	}

	// 当天时间
	public static String getHourStr() {
		DateFormat dateFormat = new SimpleDateFormat("HHmm");
		return dateFormat.format(Calendar.getInstance().getTime());
	}

	// 当天时间长字符
	public static String getLongDayHourStr() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return dateFormat.format(Calendar.getInstance().getTime());
	}

	// 当天时间字符
	public static String getDayHourStr() {
		DateFormat dateFormat = new SimpleDateFormat("HHmmss");
		return dateFormat.format(Calendar.getInstance().getTime());
	}

	// 当天时间字符
	public static String getLongHourStr() {
		DateFormat dateFormat = new SimpleDateFormat("HHmmssSSS");
		return dateFormat.format(Calendar.getInstance().getTime());
	}

	public static String getShortDayStr(Date day) {
		if (day == null)
			return "";
		DateFormat dateFormat = new SimpleDateFormat("MM-dd");
		return dateFormat.format(day);
	}

	public static String getTimeDiff(String time) {
		if (Util.isNull(time))
			return "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = dateFormat.parse(time);
			return getTimeDiff(d);
		} catch (ParseException e) {
			return "";
		}
	}

	// 获得指定时间和当前时间的天数差异,当天差异为1
	public static int getDayOff(String offset) {
		if (Util.isNull(offset))
			return 0;
		String format = "yyyy-MM-dd HH:mm:ss";
		if (!offset.contains(" "))
			format = "yyyy-MM-dd";
		DateFormat dateFormat = new SimpleDateFormat(format);
		try {
			Date offdate = dateFormat.parse(offset);
			Calendar calc = Calendar.getInstance();
			calc.setTime(offdate);
			long offmillis = calc.getTimeInMillis();
			long difmillis = System.currentTimeMillis() - offmillis;
			long day = difmillis / 1000 / 60 / 60 / 24;
			return (int) day + 1;
		} catch (ParseException pe) {
			return 0;
		}
	}

	public static Date parseDateStr(String dtStr) {
		Date rtDate = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			rtDate = dateFormat.parse(dtStr);
		} catch (ParseException pe) {
		}
		return rtDate;
	}

	// 获得指定时间在日历中的天数
	public static int getDayofCal(String day) {
		if (Util.isNull(day))
			return 0;
		String format = "yyyy-MM-dd";
		DateFormat dateFormat = new SimpleDateFormat(format);
		try {
			Date offdate = dateFormat.parse(day);
			Calendar calc = Calendar.getInstance();
			calc.setTime(offdate);
			long offmillis = calc.getTimeInMillis();
			long dayNum = offmillis / (1000 * 60 * 60 * 24);
			return (int) (dayNum + 1);
		} catch (ParseException pe) {
			return 0;
		}
	}

	// 获得指定时间在日历中的天数
	public static int getDayofCal(Date day) {
		if (day == null)
			return 0;
		Calendar calc = Calendar.getInstance();
		calc.setTime(day);
		long offmillis = calc.getTimeInMillis();
		long dayNum = offmillis / 1000 / 60 / 60 / 24;
		return (int) dayNum;
	}

	// 获得自1970年以来的天数所对应的时间
	public static Date getDateFromCal(int day) {
		Calendar calc = Calendar.getInstance();
		long offmillis = (day + 1) * 1000;
		// offmillis += 1;
		offmillis = offmillis * 60 * 60 * 24;
		calc.setTimeInMillis(offmillis);
		return calc.getTime();
	}

	// 获得指定时间在一周中的对应时间，周一返回1，周2返回2
	public static int getDayofWeek(Date day) {
		if (day == null)
			return 0;
		Calendar calc = Calendar.getInstance();
		calc.setTime(day);
		int dw = calc.get(Calendar.DAY_OF_WEEK);
		if (dw == 1)
			return 7;
		else
			return dw - 1;
	}

	// 获得日历中的天数对应的时间
	public static String getDayStrWithDayOfCal(int dayNum) {
		if (dayNum < 0)
			return "0000-00-00";
		Calendar calc = Calendar.getInstance();
		long offmillis = (long) (dayNum + 1) * 1000 * 60 * 60 * 24 - 1;
		calc.setTimeInMillis(offmillis);
		return Util.getDayStr(calc.getTime());
	}

	public static String convert2ShortDateStr(String dtStr) {
		Date rtDate = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			rtDate = dateFormat.parse(dtStr);
			dateFormat = new SimpleDateFormat("MM-dd");
			return dateFormat.format(rtDate);
		} catch (ParseException pe) {
		}
		return "";
	}

	public static String offsetDateStr(String dtStr, int offset) {
		Date rtDate = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			rtDate = dateFormat.parse(dtStr);

			Calendar calc = Calendar.getInstance();
			calc.setTime(rtDate);
			calc.add(Calendar.DAY_OF_YEAR, offset);
			dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.format(calc.getTime());
		} catch (ParseException pe) {
		}
		return "";
	}

	// 用replace代替tag
	public static String replaceAll(String str, String tag, String replace) {
		if (str == null || str.length() == 0)
			return "";
		if (tag == null || tag.length() == 0)
			return str;
		StringBuffer buf = new StringBuffer();
		int index = str.indexOf(tag);
		while (index > -1) {
			buf.append(str.substring(0, index)).append(replace);
			str = str.substring(index + tag.length());
			index = str.indexOf(tag);
			// return replaceAll(str,tag,replace);
		}
		if (Util.isNotNull(str))
			buf.append(str);
		return buf.toString();
	}

	// 用replace代替tag
	public static String replace(String str, String tag, String replace) {
		if (str == null || str.length() == 0)
			return "";
		if (tag == null || tag.length() == 0)
			return str;
		int index = str.indexOf(tag);
		if (index > -1)
			return str.substring(0, index) + replace + str.substring(index + tag.length());
		return str;
	}

	// 获得时间差显示值,以毫秒显示
	public static String getTimeDiff2(long timediff) {
		int timeSec = (int) (timediff / 1000);
		timediff = timediff - 1000 * timeSec;
		int timeMin = (int) (timeSec / 60);
		timeSec = timeSec - timeMin * 60;
		int timeHour = (int) (timeMin / 60);
		timeMin = timeMin - timeHour * 60;
		int timeDay = (int) (timeHour / 25);
		// String timeStr = Long.toString(timediff)+"ms";
		String timeStr = "秒";
		if (timeSec > 0)
			timeStr = timeSec + timeStr;
		else
			timeStr = "0秒";
		if (timeMin > 0)
			timeStr = timeMin + "分" + timeStr;
		if (timeHour > 0)
			timeStr = timeHour + "时" + timeStr;
		if (timeDay > 0)
			timeStr = timeDay + "天" + timeStr;
		return timeStr;
	}

	public static String getTimeDiff(Date time) {
		Calendar t = Calendar.getInstance();
		t.setTime(time);
		long t1 = t.getTimeInMillis();
		long t0 = System.currentTimeMillis();
		if (t0 <= t1)
			return "立刻";
		// 秒
		long tmdiff = (t0 - t1) / 1000 + 1;
		// 60分钟之内
		if (tmdiff < 60 * 60)
			return (int) (tmdiff / 60.0 + 0.5) + "分钟";
		else if (tmdiff < 8 * 60 * 60)
			return (int) (tmdiff / 60.0 / 60.0 + 0.5) + "小时";
		else {
			String d1 = Util.getDayStr(time);
			// 今天
			String d0 = Util.currDay();
			// 昨天
			String dn1 = Util.currDayOffset(-1);
			if (d1.endsWith(d0))
				return "今天";
			else if (d1.endsWith(dn1))
				return "昨天";
			else
				return d1;
		}
		/*
		 * Calendar c = Calendar.getInstance(); Calendar t =
		 * Calendar.getInstance(); t.setTime(time); DateFormat dayFormat = new
		 * SimpleDateFormat("yyyy-MM-dd"); // 不同一天 if
		 * (c.get(Calendar.DAY_OF_YEAR) > t.get(Calendar.DAY_OF_YEAR) + 3)
		 * return dayFormat.format(time); else if (c.get(Calendar.DAY_OF_YEAR)
		 * >= t.get(Calendar.DAY_OF_YEAR) + 1) return
		 * (c.get(Calendar.DAY_OF_YEAR) - t.get(Calendar.DAY_OF_YEAR)) + "天前";
		 * else { if (c.get(Calendar.HOUR_OF_DAY) ==
		 * t.get(Calendar.HOUR_OF_DAY)){ if(c.get(Calendar.MINUTE) <
		 * t.get(Calendar.MINUTE)) return "立刻"; else return
		 * (c.get(Calendar.MINUTE) - t.get(Calendar.MINUTE)) + "分钟前"; }else if
		 * (c.get(Calendar.HOUR_OF_DAY) == t.get(Calendar.HOUR_OF_DAY) + 1) { if
		 * (c.get(Calendar.MINUTE) >= t.get(Calendar.MINUTE)) { return
		 * (c.get(Calendar.HOUR_OF_DAY) - t.get(Calendar.HOUR_OF_DAY)) + "小时前";
		 * } else { // 下个一小时 return (60 + c.get(Calendar.MINUTE) -
		 * t.get(Calendar.MINUTE)) + "分钟前"; } } else return
		 * (c.get(Calendar.HOUR_OF_DAY) - t.get(Calendar.HOUR_OF_DAY)) + "小时前";
		 * }
		 */
	}

	// 产生认证码
	public static String genAuthCode(int len) {
		Random random = new Random();
		if (len < 0)
			return "";
		char c;
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < len; i++) {
			// 数字
			if (i == 0) {
				c = (char) (49 + random.nextInt(9));
			} else
				c = (char) (48 + random.nextInt(10));
			buf.append(c);
		}
		return buf.toString();
	}

	// 产生字母字符
	public static String genAlphabetCode(int len) {
		Random random = new Random();
		if (len < 0)
			return "";
		char c;
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < len; i++) {
			c = (char) (97 + random.nextInt(26));
			buf.append(c);
		}
		return buf.toString();
	}

	// 产生认证码
	public static String genDigiCodeStr(int len) {

		Random random = new Random();
		char c;
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < len; i++) {
			// 数字
			if (i == 0) {
				c = (char) (49 + random.nextInt(9));
			} else
				c = (char) (48 + random.nextInt(10));
			buf.append(c);
		}
		return buf.toString();

	}

	// 产生认证码
	public static int genDigiCode(int lowlen, int highlen) {
		Random random = new Random();
		char c;
		StringBuffer buf = new StringBuffer();
		int len = highlen;
		if (highlen <= lowlen + 1)
			len = highlen;
		else
			len = lowlen + random.nextInt(highlen - lowlen);
		for (int i = 0; i < len; i++) {
			// 数字
			if (i == 0) {
				c = (char) (49 + random.nextInt(9));
			} else
				c = (char) (48 + random.nextInt(10));
			buf.append(c);
		}
		return Integer.parseInt(buf.toString());
	}

	// 产生认证码
	public static String genSessCode(int len) {
		Random random = new Random();
		if (len < 0)
			return "";
		char c;
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < len; i++) {
			int type = random.nextInt(2);
			// 数字
			if (type == 0) {
				c = (char) (48 + random.nextInt(10));
			} else
				c = (char) (97 + random.nextInt(26));
			buf.append(c);
		}
		return buf.toString();
	}

	// 产生对应安全码对照
	public static String genSecID(int len) {
		Random random = new Random();
		if (len < 0)
			return "";
		StringBuffer buf = new StringBuffer();
		int matched = 0;
		while (matched < len) {
			int type = random.nextInt(128);
			if (type >= 48 && type <= 122) {
				if (type <= 57 || type >= 97 || (type >= 65 && type <= 90)) {
					buf.append((char) type);
					matched++;
				}
			}
		}
		return buf.toString();
	}

	// 当天
	public static String currDay() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	// 当年
	public static String currYear() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		return dateFormat.format(date);
	}

	// 此刻
	public static String currTime() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}

	// 获得当前时间的offset天数
	public static String currDayOffset(int day) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calc = Calendar.getInstance();
		calc.add(Calendar.DAY_OF_YEAR, day);
		return dateFormat.format(calc.getTime());
	}

	// 获得当前天n天前
	public static Date currDateOffset(int day) {
		Calendar calc = Calendar.getInstance();
		calc.add(Calendar.DAY_OF_YEAR, day);
		return calc.getTime();
	}

	public static String encodeURL(String str) {
		String urlEncode = "";
		try {
			urlEncode = URLEncoder.encode(str, "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return urlEncode;
	}

	public static String encodeURL(String str, String enc) {
		String urlEncode = "";
		try {
			urlEncode = URLEncoder.encode(str, enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return urlEncode;
	}

	// 比较字符串时间是否早于等于当前时间
	public static boolean earlyThanCurday(String dtStr) {
		if (Util.isNull(dtStr))
			return false;
		Date dtDate = parseDate(dtStr);
		Calendar dtCalc = Calendar.getInstance();
		dtCalc.setTime(dtDate);
		Calendar curCalc = Calendar.getInstance();
		curCalc.add(Calendar.DAY_OF_YEAR, -1);
		return !dtCalc.after(curCalc);
	}

	// 解析日期字符串到日期对象
	public static Date parseDate(String dtStr) {
		if (Util.isNull(dtStr))
			return null;
		Date rtDate = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			rtDate = dateFormat.parse(dtStr);
		} catch (ParseException pe) {
		}
		return rtDate;
	}

	// 根据开始和结束时间返回时间轴列表
	public static List<String> genDayList(String frmStr, String toStr) {
		List<String> days = new ArrayList<String>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (Util.isNull(frmStr) && Util.isNull(toStr)) {
			for (int i = -6; i <= 0; i++)
				days.add(Util.currDayOffset(i));
		} else if (Util.isNotNull(frmStr) && Util.isNotNull(toStr)) {
			int index = frmStr.indexOf("T");
			if (index > -1)
				frmStr = frmStr.substring(0, index);
			else {
				index = frmStr.indexOf(" ");
				if (index > -1)
					frmStr = frmStr.substring(0, index);
			}
			// 以frmStr为准
			Date curday = Util.parseDate(frmStr);
			Calendar calc = Calendar.getInstance();
			int startday = 3;
			for (int i = 3; i >= -3;) {
				calc.setTime(curday);
				calc.add(Calendar.DAY_OF_YEAR, startday--);
				if (!calc.after(Calendar.getInstance())) {
					days.add(dateFormat.format(calc.getTime()));
					i--;
				}
			}
			for (int i = 0; i <= 3; i++) {
				String d = days.get(i);
				days.set(i, days.get(days.size() - 1 - i));
				days.set(days.size() - 1 - i, d);
			}
			if (frmStr.equals(toStr))
				days.add(frmStr);
		} else {
			// 以frmStr为准
			if (Util.isNull(frmStr))
				frmStr = toStr;

			int index = frmStr.indexOf("T");
			if (index > -1)
				frmStr = frmStr.substring(0, index);
			else {
				index = frmStr.indexOf(" ");
				if (index > -1)
					frmStr = frmStr.substring(0, index);
			}
			Date curday = Util.parseDate(frmStr);
			Calendar calc = Calendar.getInstance();
			int startday = 3;
			for (int i = 3; i >= -3;) {
				calc.setTime(curday);
				calc.add(Calendar.DAY_OF_YEAR, startday--);
				if (!calc.after(Calendar.getInstance())) {
					days.add(dateFormat.format(calc.getTime()));
					i--;
				}
			}
			for (int i = 0; i <= 3; i++) {
				String d = days.get(i);
				days.set(i, days.get(days.size() - 1 - i));
				days.set(days.size() - 1 - i, d);
			}
		}
		return days;
	}

	// 获得当前前n天的时间字符串，比如当天是2014-6-1，前3天就是2014-5-28
	public static String curDayOffset(int day) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calc = Calendar.getInstance();
		calc.add(Calendar.DAY_OF_YEAR, day);
		return dateFormat.format(calc.getTime());
	}

	// 当前时间到当天11:59:59妙的时间微秒差距
	public static long curDayDiffInMill() {
		String curDayStr = curDayOffset(0) + " 23:59:59";
		Date curDay = Util.parseDateStr(curDayStr);
		Calendar calc = Calendar.getInstance();
		calc.setTime(curDay);
		long etime = calc.getTimeInMillis();
		long btime = System.currentTimeMillis();
		return etime - btime;
	}

	public static Date curDateOffset(int day) {
		Calendar calc = Calendar.getInstance();
		calc.add(Calendar.DAY_OF_YEAR, day);
		return calc.getTime();
	}

	public static String encodeGB2UTF8(String str) {
		return encode(str, "GBK", "UTF-8");
	}

	public static String trimYesNo(String str) {
		if (Util.isNull(str))
			return null;
		str = str.toUpperCase();
		if (str.startsWith("Y"))
			return "Y";
		else
			return "N";
	}

	public static String encode(String str, String srcEnc, String desEnc) {
		if (Util.isNull(str))
			return str;
		try {
			byte b[] = str.getBytes(srcEnc);
			return new String(b, desEnc);
		} catch (UnsupportedEncodingException uee) {
			// 不做转换
			return str;
		}
	}

	// 按照长度平分一个字符
	public static List<String> splitEqStr(String str, int len) {
		List<String> lstr = new ArrayList<String>();
		if (Util.isNull(str))
			return lstr;
		if (len <= 0) {
			lstr.add(str);
			return lstr;
		}
		int strLen = str.length();
		int count = strLen / len;
		for (int i = 0; i < count; i++) {
			lstr.add(str.substring(i * len, i * len + len));
		}
		if (count * len != strLen)
			lstr.add(str.substring(count * len));
		return lstr;
	}

	// 返回以日期为间隔的目录结构
	public static String getDaySepDir() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String dirs = dateFormat.format(Calendar.getInstance().getTime());
		return dirs;
	}

	// 返回以日期为间隔的目录结构
	public static String getDaySepDir(Date time) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String dirs = dateFormat.format(time);
		return dirs;
	}

	// 判断是否闰年
	private static boolean isLeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
			return true;
		else
			return false;
	}

	// 获得指定月的天数
	public static int getLastDayOfMonth(int year, int month) {
		int dayNum = MONTH_DAY_NUM[month - 1];
		// 判断闰月
		if (month == 2) {
			boolean leapYear = isLeapYear(year);
			if (leapYear)
				dayNum = 29;
		}
		return dayNum;
	}

	// orgEnc：原来url编码，desEnc:目标url编码
	public static String decodeUrl(String url, String orgEnc) {
		try {
			String u = URLDecoder.decode(url, orgEnc);
			// System.out.println(u.length());
			return u;
		} catch (UnsupportedEncodingException e) {
			return url;
		}
	}

	// orgEnc：原来url编码，desEnc:目标url编码
	public static String urlConvert(String url, String orgEnc, String desEnc) {
		try {
			String u = URLDecoder.decode(url, orgEnc);
			System.out.println(u.length());
			u = URLEncoder.encode(u, desEnc);
			return u.replaceAll("[+]", "%20");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	// 以逗号间隔的字符串转换成数组
	public static List<String> str2Array(String str, String tag) {
		List<String> arrs = new ArrayList<String>();
		if (Util.isNull(str)) {
			return arrs;
		} else {
			if (!str.contains(tag)) {
				arrs.add(str);
				return arrs;
			}
		}

		StringTokenizer st = new StringTokenizer(str, tag);
		while (st.hasMoreElements()) {
			arrs.add(st.nextElement().toString());
		}
		return arrs;
	}

	// 以逗号间隔的字符串转换成数组
	public static List<String> str2ArrayExt(String str, String tag) {
		List<String> arrs = new ArrayList<String>();
		if (Util.isNull(str)) {
			return arrs;
		} else {
			if (!str.contains(tag)) {
				arrs.add(str);
				return arrs;
			}
		}

		int pos = 1;
		while (pos > 0) {
			pos = str.indexOf(tag);
			if (pos > -1) {
				arrs.add(str.substring(0, pos));
				str = str.substring(pos + tag.length());
			} else
				arrs.add(str);
		}
		return arrs;
	}

	// 以逗号间隔的字符串转换成字符数组
	public static String[] str2StrArray(String str) {
		List<String> arrs = str2Array(str, ",");
		return arrs.toArray(new String[0]);
	}

	// 以逗号间隔的字符串转换成数组
	public static List<String> str2Array(String str) {
		return str2Array(str, ",");
	}

	// 获得date的日期值
	public static String getDateStr(Date date) {
		if (date == null)
			return "";
		else {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.format(date);
		}
	}

	// 获得date的长串显示
	public static String getDateTimeStr(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}

	// 挖取str中从btag开始到etag结束的内容
	public static String trim(String str, String btag, String etag) {
		if (Util.isNull(str))
			return null;
		if (Util.isNull(btag) || Util.isNull(etag))
			return str;
		int index1 = str.indexOf(btag);
		int index2 = str.indexOf(etag);
		if (index1 == -1 || index2 == -1)
			return str;
		else {
			str = str.substring(0, index1) + str.substring(index2 + etag.length());
			return trim(str, btag, etag);
		}
	}

	// 将字符值转换成数值
	public static short parseShortVl(String str, short defNum) {
		short retVl = defNum;
		if (Util.isNull(str))
			return defNum;
		try {
			retVl = Short.parseShort(str);
		} catch (NumberFormatException nfe) {
		}
		return retVl;
	}

	// 将字符值转换成数值
	public static int parseNumVl(String str, int defNum) {
		int retVl = defNum;
		if (Util.isNull(str))
			return defNum;
		try {
			retVl = Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
		}
		return retVl;
	}

	// 将字符值转换成数值
	public static double parseDoubleVl(String str, double defNum) {
		double retVl = defNum;
		if (Util.isNull(str))
			return defNum;
		try {
			// retVl = Double.parseDouble(Util.getRexStr(str, "[0-9\\.]{1,}"));
			retVl = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
		}
		return retVl;
	}

	// 将字符值转换成数值
	public static float parseFloatVl(String str, float defNum) {
		float retVl = defNum;
		if (Util.isNull(str))
			return defNum;
		try {
			// retVl = Double.parseDouble(Util.getRexStr(str, "[0-9\\.]{1,}"));
			retVl = Float.parseFloat(str);
		} catch (NumberFormatException nfe) {
		}
		return retVl;
	}

	// 将字符值转换成Long类型数值
	public static long parseLongVl(String str, long defNum) {
		long retVl = defNum;
		if (Util.isNull(str))
			return defNum;
		try {
			retVl = Long.parseLong(str);
		} catch (NumberFormatException nfe) {
		}
		return retVl;
	}

	// 获得最大的内容长度
	public static String trimMaxLen(String str, int maxlen) {
		if (maxlen < 0)
			return str;
		if (Util.isNotNull(str) && str.length() > maxlen)
			return str.substring(0, maxlen);
		else
			return str;
	}

	public static String trim(String str) {
		if (str == null)
			return null;
		if (str.length() == 0)
			return "";
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(str);
		str = m.replaceAll("");
		return str;
	}

	public static String trimLeftN(String str) {
		if (str == null)
			return null;
		if (str.length() == 0)
			return "";
		Pattern p = Pattern.compile("^[ 　\\s\t\r\n]{0,}");
		Matcher m = p.matcher(str);
		str = m.replaceAll("");
		return str;
	}

	public static String trimRightN(String str) {
		if (str == null)
			return null;
		if (str.length() == 0)
			return "";
		Pattern p = Pattern.compile("[ 　\\s\t\r\n]*$");
		Matcher m = p.matcher(str);
		str = m.replaceAll("");
		return str;
	}

	public static String trimN(String str) {
		str = trimLeftN(str);
		str = trimRightN(str);
		return str;
	}

	public static String mobileNumMark(String mobile) {
		if (mobile.length() > 8)
			return mobile.substring(0, 3) + "****" + mobile.substring(7);
		else
			return mobile;
	}

	// 获得字符显示，如果null返回“”
	public static String val(String str) {
		if (Util.isNull(str))
			return "";
		else
			return str;
	}

	public static int val(Integer vl) {
		if (vl == null)
			return 0;
		else
			return vl;
	}

	public static void main(String args[]) {

		System.out.println(Util.genSessCode(8));
		System.out.println(Util.genSessCode(8));
		System.out.println(Util.genDigiCodeStr(8));
		System.out.println(Util.genDigiCodeStr(8));
	}
}
