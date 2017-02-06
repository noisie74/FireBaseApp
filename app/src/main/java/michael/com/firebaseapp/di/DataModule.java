package michael.com.firebaseapp.di;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import michael.com.firebaseapp.FireBaseApp;
import michael.com.firebaseapp.post.IPosts;
import michael.com.firebaseapp.post.PostRepository;

/**
 * Created by Mikhail on 2/5/17.
 */

@Module
public class DataModule {

    FireBaseApp mFirebaseApp;

    public DataModule(FireBaseApp fireBaseApp) {
        this.mFirebaseApp = fireBaseApp;
    }

    @Provides
    @Singleton
    FirebaseAuth getFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    @Singleton
    FirebaseUser getCurrentUser(FirebaseAuth firebaseAuth) {
        return firebaseAuth.getCurrentUser();
    }

    @Provides
    @Singleton
    IPosts providePosts() {
        DatabaseReference databaseReference = FirebaseDatabase
                .getInstance()
                .getReference();
        return new PostRepository(databaseReference);
    }


}