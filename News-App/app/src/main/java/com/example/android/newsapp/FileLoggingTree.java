package com.example.android.newsapp;

import android.util.Log;

import timber.log.Timber;

import static org.slf4j.LoggerFactory.getLogger;

public class FileLoggingTree extends Timber.DebugTree {

    static org.slf4j.Logger mLogger = (org.slf4j.Logger) getLogger(FileLoggingTree.class);

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        if (priority == Log.VERBOSE) {
            return;
        }

        String logMessage = tag + ": " + message;
        switch (priority) {
            case Log.DEBUG:
                mLogger.debug(logMessage);
                break;
            case Log.INFO:
                mLogger.info(logMessage);
                break;
            case Log.WARN:
                mLogger.warn(logMessage);
                break;
            case Log.ERROR:
                mLogger.error(logMessage);
                break;
        }
    }
}
