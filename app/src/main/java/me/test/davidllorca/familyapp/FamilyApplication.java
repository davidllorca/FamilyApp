package me.test.davidllorca.familyapp;

import android.app.Application;
import android.content.Context;

/**
 * Custom implementation {@link Application} class.
 * <p>
 * Created by David Llorca <davidllorcabaron@gmail.com> on 11/11/17.
 */
public class FamilyApplication extends Application {

    /**
     * Accessor to application {@link Context}.
     */
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    /**
     * Public getter to application {@link Context}.
     */
    public static Context getContext() {
        return mContext;
    }
}
