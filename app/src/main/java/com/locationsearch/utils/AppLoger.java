/**
 * @author Wild Coder
 */
package com.locationsearch.utils;

import android.util.Log;


import com.locationsearch.BuildConfig;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Wild Coder
 */
public class AppLoger {
    public static boolean DEBUG_MODE = BuildConfig.LOG_DEBUG_MODE;
    public static boolean DEBUG_WITH_STACKTRACE_MODE = BuildConfig.LOG_DEBUG_WITH_STACKTRACE_MODE;

    /**
     * @param cls     Class<T>
     * @param message String
     * @author Android Lead
     */
    public static <T> void logInfo(Class<T> cls, String message) {
        if (DEBUG_MODE || DEBUG_WITH_STACKTRACE_MODE) {
            String tag = cls.getName();
            Log.i(tag, "-----");
            Log.i(tag, LogType.INFO + ": " + message);
            if (DEBUG_WITH_STACKTRACE_MODE) {
                Log.i(tag, getStackTrace());
            }
        }
    }

    /**
     * @param cls     Class<T>
     * @param message String
     * @author Android Lead
     */
    public static <T> void logWarning(Class<T> cls, String message) {
        if (DEBUG_MODE || DEBUG_WITH_STACKTRACE_MODE) {
            String tag = cls.getName();
            Log.w(tag, "-----");
            Log.w(tag, LogType.WARNING + ": " + message);

            if (DEBUG_WITH_STACKTRACE_MODE) {
                Log.w(tag, getStackTrace());
            }
        }
    }

    /**
     * @param cls     Class<T>
     * @param message String
     * @author Android Lead
     */
    public static <T> void logError(Class<T> cls, String message) {
        if (DEBUG_MODE || DEBUG_WITH_STACKTRACE_MODE) {
            String tag = cls.getName();
            Log.e(tag, "-----");
            Log.e(tag, LogType.ERROR + ": " + message);

            if (DEBUG_WITH_STACKTRACE_MODE) {
                Log.e(tag, getStackTrace());
            }
        }
    }

    /**
     * @param cls     Class<T>
     * @param message String
     * @author Android Lead
     */
    public static <T> void logError(Class<T> cls, String message, Throwable e) {
        if (DEBUG_MODE || DEBUG_WITH_STACKTRACE_MODE) {
            String tag = cls.getName();
            Log.e(tag, "-----");
            Log.e(tag, LogType.ERROR + ": " + message, e);

            if (DEBUG_WITH_STACKTRACE_MODE) {
                Log.e(tag, getStackTrace());
            }
        }
    }

    /**
     * @param tag String
     * @param msg String/JSON/ArrayList
     * @author Android Lead
     */
    public static void e(String tag, Object msg) {
        if (DEBUG_MODE || DEBUG_WITH_STACKTRACE_MODE)
            Log.e(tag, "" + msg);
    }

    /**
     * @param tag String
     * @param msg String/JSON/ArrayList
     * @author Android Lead
     */
    public static void i(String tag, Object msg) {
        if (DEBUG_MODE || DEBUG_WITH_STACKTRACE_MODE)
            Log.i(tag, "" + msg);
    }

    /**
     * @author Android Lead
     */
    private static String getStackTrace() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        new Throwable().printStackTrace(pw);
        return sw.toString();
    }

    private enum LogType {
        INFO, WARNING, ERROR
    }

}
