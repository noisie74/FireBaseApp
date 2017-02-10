package michael.com.firebaseapp;

import android.app.Application;

//import michael.com.firebaseapp.di.ApplicationComponent;
//import michael.com.firebaseapp.di.DaggerApplicationComponent;
//import michael.com.firebaseapp.di.DataModule;
import timber.log.Timber;


/**
 * Created by Mikhail on 1/29/17.
 */

public class FireBaseApp extends Application {

//    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

//        inject();
    }

//    private void inject() {
//        component = DaggerApplicationComponent
//                .builder()
//                .dataModule(new DataModule(this))
//                .build();
//    }
//
//    public ApplicationComponent getApplicationComponent(){
//        return component;
//    }
}
