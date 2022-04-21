package com.jiunntarn.detection_reminder.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author JiunnTarn
 */
public class LogUtil {
    public final static Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void log(String text) {
        logger.info(text);
    }
}
