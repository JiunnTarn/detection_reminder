package com.jiunntarn.detection_reminder.pojo;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.Collections;
import java.util.List;

public interface MyCookieJar extends CookieJar {
    void clearCookieJar();
}
