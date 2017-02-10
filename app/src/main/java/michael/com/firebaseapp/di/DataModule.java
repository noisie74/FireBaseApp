//package michael.com.firebaseapp.di;
//
//import android.support.annotation.NonNull;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import javax.inject.Singleton;
//
//import dagger.Module;
//import dagger.Provides;
//import michael.com.firebaseapp.FireBaseApp;
//import michael.com.firebaseapp.data.IUserAuth;
//import michael.com.firebaseapp.data.repository.IPosts;
//import michael.com.firebaseapp.data.repository.PostRepository;
//
///**
// * Created by Mikhail on 2/5/17.
// */
//
//@Module
//public class DataModule {
//
//    FireBaseApp mFirebaseApp;
//
//    public DataModule(FireBaseApp fireBaseApp) {
//        this.mFirebaseApp = fireBaseApp;
//    }
//
//    @Provides
//    FireBaseApp provideFireBaseApplication() {
//        return mFirebaseApp;
//    }
//
//    @Provides
//    @Singleton
//    FirebaseAuth getFirebaseAuth() {
//        return FirebaseAuth.getInstance();
//    }
//
//    @Provides
//    @Singleton
//    FirebaseUser getCurrentUser(FirebaseAuth firebaseAuth) {
//        return firebaseAuth.getCurrentUser();
//    }
//
//    @Provides
//    @Singleton
//    IPosts providePosts() {
//        DatabaseReference databaseReference = FirebaseDatabase
//                .getInstance()
//                .getReference();
//        return new PostRepository(databaseReference);
//    }
//
//
//    @Provides
//    @Singleton
//    IUserAuth provideCurrentAuthUser(FirebaseAuth firebaseAuth) {
//        FirebaseAuth.AuthStateListener mFireBaseAuth = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//
//
//            }
//        } return mFireBaseAuth;
//
//    }
//}
