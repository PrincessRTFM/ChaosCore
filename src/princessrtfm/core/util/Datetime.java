package princessrtfm.core.util;


import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Convenience class for formatting date/time objects
 *
 * @since 1.0.0-alpha.1
 */
public class Datetime {
	/**
	 * Format code for era (BC/AD)
	 *
	 * @since 1.0.0-alpha.1
	 */
	public static final String FMT_ERA = "GG";
	/**
	 * Format code for four digit year
	 *
	 * @since 1.0.0-alpha.1
	 */
	public static final String FMT_YEAR_LONG = "yyyy";
	/**
	 * Format code for two digit year
	 *
	 * @since 1.0.0-alpha.1
	 */
	public static final String FMT_YEAR_SHORT = "yy";
	/**
	 * Format code for two digit number of the month (January = 01, February = 02, ...)
	 *
	 * @since 1.0.0-alpha.1
	 */
	public static final String FMT_MONTH = "MM";
	/**
	 * Format code for short month name (Jan, Feb, ...)
	 *
	 * @since 1.0.0-alpha.1
	 */
	public static final String FMT_MONTH_SHORT = "MMM";
	/**
	 * Format code for long month name (January, February)
	 *
	 * @since 1.0.0-alpha.1
	 */
	public static final String FMT_MONTH_LONG = "MMMM";
	/**
	 * Format code for the week of the year
	 *
	 * @since 1.0.0-alpha.1
	 */
	public static final String FMT_WEEK_YEAR = "ww";
	/**
	 * Format code for the week of the month
	 *
	 * @since 1.0.0-alpha.1
	 */
	public static final String FMT_WEEK_MONTH = "W";
	/**
	 * Format code for the day of the year
	 *
	 * @since 1.0.0-alpha.1
	 */
	public static final String FMT_DAY_YEAR = "DDD";
	/**
	 * Format code for the day of the month
	 *
	 * @since 1.0.0-alpha.1
	 */
	public static final String FMT_DAY_MONTH = "dd";
	/**
	 * Format code for the short name of the day (Sun, Mon, ...)
	 *
	 * @since 1.0.0-alpha.1
	 */
	public static final String FMT_DAY_SHORT = "EEE";
	/**
	 * Format code for the long name of the day (Sunday, Monday, ...)
	 *
	 * @since 1.0.0-alpha.1
	 */
	public static final String FMT_DAY_LONG = "EEEE";
	/**
	 * Format code for AM/PM
	 *
	 * @since 1.0.0-alpha.1
	 */
	public static final String FMT_AMPM = "a";
	/**
	 * Format code for hour (00-24)
	 *
	 * @since 1.0.0-alpha.1
	 */
	public static final String FMT_HOUR_24 = "HH";
	/**
	 * Format code for hour (1-12)
	 *
	 * @since 1.0.0-alpha.1
	 */
	public static final String FMT_HOUR_12 = "hh";
	/**
	 * Format code for minute
	 *
	 * @since 1.0.0-alpha.1
	 */
	public static final String FMT_MINUTE = "mm";
	/**
	 * Format code for second
	 *
	 * @since 1.0.0-alpha.1
	 */
	public static final String FMT_SECOND = "ss";
	/**
	 * Format code for long timezone (if the timezone has a name, it will return the full name; if
	 * it doesn't have a name, it returns an offset)
	 *
	 * @since 1.0.0-alpha.1
	 */
	public static final String FMT_TIMEZONE_GENERAL_LONG = "zzzz";
	/**
	 * Format code for short timezone (if the timezone has a name, it will return the abbreviation;
	 * if it doesn't have a name, it returns an offset)
	 *
	 * @since 1.0.0-alpha.1
	 */
	public static final String FMT_TIMEZONE_GENERAL_SHORT = "zzz";
	/**
	 * Format code for RFC 822 timezone (offset only, no colon)
	 *
	 * @since 1.0.0-alpha.1
	 */
	public static final String FMT_TIMEZONE_RFC = "Z";
	/**
	 * Format code for short ISO 8601 timezone (offset, no minutes)
	 *
	 * @since 1.0.0-alpha.1
	 */
	public static final String FMT_TIMEZONE_ISO_SHORT = "X";
	/**
	 * Format code for long ISO 8601 timezone (offset with minutes)
	 *
	 * @since 1.0.0-alpha.1
	 */
	public static final String FMT_TIMEZONE_ISO_LONG = "XX";
	/**
	 * Format code for long ISO8601 timezone with colon separating hours and minutes
	 *
	 * @since 1.0.0-alpha.1
	 */
	public static final String FMT_TIMEZONE_ISO_COLON = "XXX";
	protected Date date;
	/**
	 * @since 1.0.0-alpha.1
	 */
	public Datetime() {
		date = new Date();
	}
	/**
	 * @since 1.0.0-alpha.1
	 */
	@SuppressWarnings("javadoc")
	public Datetime(long time) {
		date = new Date(time);
	}
	/**
	 * @param init
	 *        the {@link Date} object to clone
	 * @since 1.0.0-alpha.1
	 */
	public Datetime(Date init) {
		date = (Date) init.clone();
	}
	/**
	 * @return the wrapped {@link Date} object
	 * @since 1.0.0-alpha.1
	 */
	public Date getDateObject() {
		return date;
	}
	/**
	 * @since 1.0.0-alpha.1
	 */
	@SuppressWarnings("javadoc")
	public void setDateObject(Date newDate) {
		date = newDate;
	}
	/**
	 * @return the two digit year (format code "<tt>yy</tt>", returns output like "<tt>08</tt>")
	 * @since 1.0.0-alpha.1
	 * @deprecated Two digit years are vague, use four digit years instead.
	 * @see #getLongYear()
	 */
	@Deprecated
	public String getShortYear() {
		return new SimpleDateFormat(FMT_YEAR_SHORT).format(date);
	}
	/**
	 * @return the four digit year (format code "<tt>yyyy</tt>", returns output like "<tt>2008</tt>
	 *         ")
	 * @since 1.0.0-alpha.1
	 */
	public String getLongYear() {
		return new SimpleDateFormat(FMT_YEAR_LONG).format(date);
	}
	/**
	 * @return the month of the year (format code "<tt>MM</tt>", returns output like "<tt>04</tt>")
	 * @since 1.0.0-alpha.1
	 */
	public String getMonth() {
		return new SimpleDateFormat(FMT_MONTH).format(date);
	}
	/**
	 * @return the short name of the month (format code "<tt>MMM</tt>", returns output like "
	 *         <tt>Sept</tt>")
	 * @since 1.0.0-alpha.1
	 */
	public String getShortMonth() {
		return new SimpleDateFormat(FMT_MONTH_SHORT).format(date);
	}
	/**
	 * @return the full name of the month (format code "<tt>MMMM</tt>", returns output like "
	 *         <tt>October</tt>")
	 * @since 1.0.0-alpha.1
	 */
	public String getLongMonth() {
		return new SimpleDateFormat(FMT_MONTH_LONG).format(date);
	}
	/**
	 * @return day of the month (format code "<tt>dd</tt>", returns output like "<tt>17</tt>")
	 * @since 1.0.0-alpha.1
	 */
	public String getDay() {
		return new SimpleDateFormat(FMT_DAY_MONTH).format(date);
	}
	/**
	 * @return day of the year (format code "<tt>DDD</tt>", returns output like "<tt>241</tt>")
	 * @since 1.0.0-alpha.1
	 */
	public String getDayOfYear() {
		return new SimpleDateFormat(FMT_DAY_YEAR).format(date);
	}
	/**
	 * @return full name of the day (format code "<tt>EEEE</tt>", returns output like "
	 *         <tt>Friday</tt>")
	 * @since 1.0.0-alpha.1
	 */
	public String getDayName() {
		return new SimpleDateFormat(FMT_DAY_LONG).format(date);
	}
	/**
	 * @return short name of the day (format code "<tt>EEE</tt>", returns output like "<tt>Wed</tt>
	 *         ")
	 * @since 1.0.0-alpha.1
	 */
	public String getShortDayName() {
		return new SimpleDateFormat(FMT_DAY_SHORT).format(date);
	}
	/**
	 * @return hour of the day in twelve hour format (format code "<tt>hh</tt>", returns output like
	 *         "<tt>04</tt>")
	 * @since 1.0.0-alpha.1
	 */
	public String getHour12() {
		return new SimpleDateFormat(FMT_HOUR_12).format(date);
	}
	/**
	 * @return hour of the day in twenty four hour format (format code "<tt>HH</tt>", returns output
	 *         like "<tt>16</tt>")
	 * @since 1.0.0-alpha.1
	 */
	public String getHour24() {
		return new SimpleDateFormat(FMT_HOUR_24).format(date);
	}
	/**
	 * @return minute of the hour (format code "<tt>mm</tt>", returns output like "<tt>07</tt>")
	 * @since 1.0.0-alpha.1
	 */
	public String getMinute() {
		return new SimpleDateFormat(FMT_MINUTE).format(date);
	}
	/**
	 * @return second of the minute (format code "<tt>ss</tt>", returns output like "<tt>01</tt>")
	 * @since 1.0.0-alpha.1
	 */
	public String getSecond() {
		return new SimpleDateFormat(FMT_SECOND).format(date);
	}
	/**
	 * @return ISO 8601 long timezone (format code "<tt>XX</tt>", returns output like "
	 *         <tt>-0300</tt>")
	 *
	 * @since 1.0.0-alpha.1
	 */
	public String getTimezone() {
		return new SimpleDateFormat(FMT_TIMEZONE_ISO_LONG).format(date);
	}
	/**
	 * @return am/pm, lowercase
	 *
	 * @since 1.0.0-alpha.1
	 */
	public String ampm() {
		return new SimpleDateFormat(FMT_AMPM).format(date).toLowerCase();
	}
	/**
	 * @return AM/PM, uppercase
	 *
	 * @since 1.0.0-alpha.1
	 */
	public String AMPM() {
		return new SimpleDateFormat(FMT_AMPM).format(date).toUpperCase();
	}
	/**
	 * @return am/pm, lowercase
	 *
	 * @since 1.0.0-alpha.1
	 */
	public String am() {
		return ampm();
	}
	/**
	 * @return am/pm, lowercase
	 *
	 * @since 1.0.0-alpha.1
	 */
	public String pm() {
		return ampm();
	}
	/**
	 * @return AM/PM, uppercase
	 *
	 * @since 1.0.0-alpha.1
	 */
	public String AM() {
		return AMPM();
	}
	/**
	 * @return AM/PM, uppercase
	 *
	 * @since 1.0.0-alpha.1
	 */
	public String PM() {
		return AMPM();
	}
	/**
	 * @return <code>true</code> if AM, <code>false</code> if PM
	 * @see #isPM()
	 *
	 * @since 1.0.0-alpha.1
	 */
	public boolean isAM() {
		return new SimpleDateFormat(FMT_AMPM).format(date).toLowerCase().startsWith("a");
	}
	/**
	 * @return <code>true</code> if PM, <code>false</code> if AM
	 * @see #isAM()
	 *
	 * @since 1.0.0-alpha.1
	 */
	public boolean isPM() {
		return !isAM();
	}
	/**
	 * Allow custom formatting
	 *
	 * @param fmt
	 *        - the {@link SimpleDateFormat} format string (see also Datetime.FMT_*)
	 * @return the formatted date/time
	 *
	 * @since 1.0.0-alpha.1
	 */
	public String format(String fmt) {
		return new SimpleDateFormat(fmt).format(date);
	}
	/**
	 * @return ISO 8601 date (format code "<tt>yyyy-MM-dd</tt>", returns output like "
	 *         <tt>2001-05-14</tt>")
	 *
	 * @since 1.0.0-alpha.1
	 */
	public String dateISO() {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	/**
	 * @return ISO 8601 time (format code "<tt>HH:mm:ssXX</tt>", returns output like "
	 *         <tt>16:43:27-0300</tt>")
	 *
	 * @since 1.0.0-alpha.1
	 */
	public String timeISO() {
		return new SimpleDateFormat("HH:mm:ssXX").format(date);
	}
	/**
	 * @return ISO 8601 date/time (format code "<tt>yyyy-MM-dd'T'HH:mm:ssXX</tt>", returns output
	 *         like "<tt>2001-05-14T16:43:27-0300</tt>")
	 *
	 * @since 1.0.0-alpha.1
	 */
	public String stampISO() {
		return dateISO() + "T" + timeISO();
	}
	/**
	 * @return ISO 8601 ordinal date (format code "<tt>yyyy-DDD</tt>", returns output like "
	 *         <tt>2010-137</tt>")
	 *
	 * @since 1.0.0-alpha.1
	 */
	public String ordinalISO() {
		return new SimpleDateFormat("yyyy-DDD").format(date);
	}
	/**
	 * Get the current time
	 *
	 * @return a Datetime object representing the current time
	 */
	public static final Datetime now() {
		return new Datetime();
	}
	/**
	 * Returns <code>this.stampISO()</code>
	 */
	@Override
	public String toString() {
		return this.stampISO();
	}
}
