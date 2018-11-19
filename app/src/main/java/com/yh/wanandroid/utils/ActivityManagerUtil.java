package com.yh.wanandroid.utils;

import android.app.Activity;

import java.util.Stack;

public class ActivityManagerUtil {

    private static ActivityManagerUtil activityManager;
    private Stack<Activity> activityStack = new Stack<>();

    private ActivityManagerUtil() {
    }

    public static ActivityManagerUtil getActivityManager() {
        if (activityManager == null) {
            synchronized (ActivityManagerUtil.class) {
                if (activityManager == null) {
                    activityManager = new ActivityManagerUtil();
                }
            }
        }
        return activityManager;
    }

    public void add(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    public void remove(Activity activity) {
        if (activity != null && activityStack != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    public void removeAll() {
        if (activityStack != null && !activityStack.empty()) {
            Stack<Activity> tempStack = new Stack<>();
            for (Activity activity : activityStack) {
                if (activity != null) {
                    tempStack.add(activity);
                    activity.finish();
                }
            }
            activityStack.removeAll(tempStack);
        }
    }
}
