package michael.com.firebaseapp;

import android.app.Application;

import timber.log.Timber;


/**
 * Created by Mikhail on 1/29/17.
 */

public class FireBaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

    }

}
