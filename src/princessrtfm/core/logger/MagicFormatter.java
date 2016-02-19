package princessrtfm.core.logger;


import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import princessrtfm.core.util.Datetime;
import princessrtfm.core.util.Reflection;


/**
 * Easily configurable logging formatter, with magic format codes to allow easy drop-in replacement
 * of useful data
 *
 * @since 1.0.0-alpha.1
 */
public class MagicFormatter extends Formatter {
	/**
	 * A collection of magic format codes that will, when surrounded by the formatter's delimiters,
	 * be transformed into some piece of useful data
	 *
	 * @since 1.0.0-alpha.1
	 * @see MagicFormatter#DEFAULT_DELIM
	 * @see MagicFormatter#DELIM
	 */
	public interface FormatCode {
		/**
		 * Magic format code to be replaced with the name of the level for this log record
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String LEVEL_NAME = "level";
		/**
		 * Magic format code to be replaced with the integer value of the level for this log record
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String LEVEL_NUM = "levelNum";
		/**
		 * Magic format code to be replaced with the name of the thread that tried to log this
		 * record
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String SOURCE_THREAD_NAME = "thread";
		/**
		 * Magic format code to be replaced with ID of the thread that tried to log this record
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String SOURCE_THREAD_ID = "threadID";
		/**
		 * Magic format code to be replaced with the day of the month
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String DAY_OF_MONTH = "day";
		/**
		 * Magic format code to be replaced with the day of the year
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String DAY_OF_YEAR = "yearDay";
		/**
		 * Magic format code to be replaced with the current hour, in twelve hour format
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String HOUR_12 = "hour12";
		/**
		 * Magic format code to be replaced with the current hour, in twenty four hour format
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String HOUR_24 = "hour24";
		/**
		 * Magic format code to be replaced with the timezone offset from UTC
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String TIMEZONE_OFFSET = "timezone";
		/**
		 * Magic format code to be replaced with name of this logger
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String LOGGER_NAME = "loggerName";
		/**
		 * Magic format code to be replaced with the current four digit year
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String YEAR = "year";
		/**
		 * Magic format code to be replaced with the current two digit year
		 *
		 * @deprecated Two digit years are vague. Use four digit years when possible.
		 * @since 1.0.0-alpha.1
		 */
		@Deprecated
		public static final String YEAR_SHORT = "yearShort";
		/**
		 * Magic format code to be replaced with the number of the current month
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String MONTH_NUMBER = "month";
		/**
		 * Magic format code to be replaced with the full name of the current month
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String MONTH_NAME = "monthName";
		/**
		 * Magic format code to be replaced with the short name of the current month
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String MONTH_NAME_SHORT = "shortMonthName";
		/**
		 * Magic format code to be replaced with the full name of the current day
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String DAY_NAME = "dayName";
		/**
		 * Magic format code to be replaced with the short name of the current day
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String DAY_NAME_SHORT = "shortDayName";
		/**
		 * Magic format code to be replaced with the current minute
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String MINUTE = "minute";
		/**
		 * Magic format code to be replaced with the current second
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String SECOND = "second";
		/**
		 * Magic format code to be replaced with the current date, in ISO 8601 format
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String ISO_DATE = "dateISO";
		/**
		 * Magic format code to be replaced with the current time, in ISO 8601 format
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String ISO_TIME = "timeISO";
		/**
		 * Magic format code to be replaced with the current date/time stamp, in ISO 8601 format
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String ISO_STAMP = "iso";
		/**
		 * Magic format code to be replaced with an am/pm indicator, in lowercase
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String AM_PM_LOWER = "ampm";
		/**
		 * Magic format code to be replaced with an am/pm indicator, in uppercase
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String AM_PM_UPPER = "AMPM";
		/**
		 * Magic format code to be replaced with the ordinal date, in ISO 8601 format
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String ISO_DATE_ORDINAL = "ordinalISO";
		/**
		 * Magic format code to be replaced with the message to log (if this is missing, the message
		 * will be appended, separated by a space)
		 *
		 * @since 1.0.0-alpha.1
		 */
		public static final String LOG_MESSAGE = "message";
	}
	/**
	 * The default delimiter, used to indicate magic
	 */
	public static final String DEFAULT_DELIM = "&";
	/**
	 * The default format string for the logging output
	 */
	public static final String DEFAULT_FORMAT = "[" + DEFAULT_DELIM + FormatCode.LOGGER_NAME + DEFAULT_DELIM + "/" + DEFAULT_DELIM + FormatCode.LEVEL_NAME + DEFAULT_DELIM + "] " + DEFAULT_DELIM + FormatCode.LOG_MESSAGE + DEFAULT_DELIM;
	/**
	 * Whether or not to enable magic formatting in the logged message by default
	 */
	public static final boolean DEFAULT_EXTRA_MAGIC = false;
	/**
	 * The regular expression used by this formatter instance to detect custom timestamps
	 */
	public final Pattern REGEX_TIMESTAMP;
	/**
	 * The delimiter used by this formatter instance
	 */
	public final String DELIM;
	/**
	 * The format string used by this formatter instance
	 */
	public final String FORMAT;
	/**
	 * Whether or not this formatter instance allows magic formatting in the logged message
	 */
	public final boolean allowMagicMessage;
	/**
	 * Instantiate a formatter with the default format, delimiter, and extra magic permission
	 */
	public MagicFormatter() {
		this(DEFAULT_FORMAT);
	}
	/**
	 * Instantiate a formatter with the given format and the default delimiter and extra magic
	 * permission
	 *
	 * @param fmt
	 *        - the format string for this formatter instance
	 */
	public MagicFormatter(String fmt) {
		this(fmt, DEFAULT_DELIM);
	}
	/**
	 * Instantiate a formatter with the given format and delimiter and the default extra magic
	 * permission
	 *
	 * @param fmt
	 *        - the format string for this formatter instance
	 * @param delim
	 *        - the delimiter for this formatter instance
	 */
	public MagicFormatter(String fmt, String delim) {
		this(fmt, delim, DEFAULT_EXTRA_MAGIC);
	}
	/**
	 * Instantiate a formatter with the given format, delimiter, and extra magic permission
	 *
	 * @param fmt
	 *        - the format string for this formatter instance
	 * @param delim
	 *        - the delimiter for this formatter instance
	 * @param extraMagic
	 *        - whether this formatter instance should allow magic formatting in logged messages
	 */
	public MagicFormatter(String fmt, String delim, boolean extraMagic) {
		super();
		FORMAT = fmt.trim();
		DELIM = delim.substring(0, 1);
		REGEX_TIMESTAMP = Pattern.compile("\\Q" + DELIM + "\\E(?:date(?:time)?(?:stamp)?|time(?:stamp)?)\\|(?<format>[^\\Q" + DELIM + "\\E]+)\\Q" + DELIM + "\\E");
		allowMagicMessage = extraMagic;
	}
	/**
	 * Replaces all of the format codes (see {@link FormatCode}) with the appropriate values
	 */
	// There's a magic format code for the short year, which is deprecated in Datetime.
	@SuppressWarnings("deprecation")
	@Override
	public String format(LogRecord rec) {
		final int threadID = rec.getThreadID();
		final String threadName = Reflection.getPrettyThreadName(threadID);
		String name = rec.getLoggerName() == null ? "Unknown" : rec.getLoggerName();
		String content = rec.getMessage() == null ? "" : rec.getMessage().trim();
		if (rec.getThrown() != null) {
			boolean isEmpty = content.isEmpty();
			content += isEmpty ? "" : " [";
			content += rec.getThrown().toString();
			content += isEmpty ? "" : "]";
		}
		Datetime now = new Datetime();
		String msg = FORMAT;
		if (allowMagicMessage) {
			if (msg.contains(DELIM + FormatCode.LOG_MESSAGE + DELIM)) {
				msg = msg.replaceAll("\\Q" + DELIM + FormatCode.LOG_MESSAGE + DELIM + "\\E", content);
			}
			else {
				msg = msg + " " + content;
			}
		}
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.LEVEL_NAME + DELIM + "\\E", rec.getLevel().getLocalizedName());
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.LEVEL_NUM + DELIM + "\\E", String.valueOf(rec.getLevel().intValue()));
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.SOURCE_THREAD_NAME + DELIM + "\\E", threadName);
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.SOURCE_THREAD_ID + DELIM + "\\E", String.valueOf(threadID));
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.YEAR + DELIM + "\\E", now.getLongYear());
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.YEAR_SHORT + DELIM + "\\E", now.getShortYear());
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.MONTH_NUMBER + DELIM + "\\E", now.getMonth());
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.MONTH_NAME + DELIM + "\\E", now.getLongMonth());
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.MONTH_NAME_SHORT + DELIM + "\\E", now.getShortMonth());
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.DAY_OF_MONTH + DELIM + "\\E", now.getDay());
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.DAY_OF_YEAR + DELIM + "\\E", now.getDayOfYear());
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.DAY_NAME + DELIM + "\\E", now.getDayName());
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.DAY_NAME_SHORT + DELIM + "\\E", now.getShortDayName());
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.HOUR_12 + DELIM + "\\E", now.getHour12());
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.HOUR_24 + DELIM + "\\E", now.getHour24());
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.MINUTE + DELIM + "\\E", now.getMinute());
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.SECOND + DELIM + "\\E", now.getSecond());
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.TIMEZONE_OFFSET + DELIM + "\\E", now.getTimezone());
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.ISO_DATE + DELIM + "\\E", now.dateISO());
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.ISO_TIME + DELIM + "\\E", now.timeISO());
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.ISO_STAMP + DELIM + "\\E", now.stampISO());
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.AM_PM_LOWER + DELIM + "\\E", now.isAM() ? "am" : "pm");
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.AM_PM_UPPER + DELIM + "\\E", now.isAM() ? "AM" : "PM");
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.ISO_DATE_ORDINAL + DELIM + "\\E", now.ordinalISO());
		msg = msg.replaceAll("\\Q" + DELIM + FormatCode.LOGGER_NAME + DELIM + "\\E", name);
		Matcher m = REGEX_TIMESTAMP.matcher(msg).reset();
		while (m.find()) {
			StringBuffer sb = new StringBuffer();
			String f = m.group("format");
			sb.append(msg.substring(0, m.start()));
			sb.append(now.format(f));
			sb.append(msg.substring(m.end()));
			msg = sb.toString();
			m.reset(msg);
		}
		if (!allowMagicMessage) {
			if (msg.contains(DELIM + FormatCode.LOG_MESSAGE + DELIM)) {
				msg = msg.replaceAll("\\Q" + DELIM + FormatCode.LOG_MESSAGE + DELIM + "\\E", content);
			}
			else {
				msg = msg + " " + content;
			}
		}
		return msg + "\n";
	}
}
