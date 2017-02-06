package michael.com.firebaseapp.di;

import javax.inject.Singleton;

import dagger.Component;
import michael.com.firebaseapp.activity.MainActivity;
import michael.com.firebaseapp.addpost.AddPostActivity;

/**
 * Created by Mikhail on 2/5/17.
 */

@Singleton
@Component(modules = DataModule.class)
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);

    void inject(AddPostActivity addPostActivity);
}
