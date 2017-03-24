package michael.com.firebaseapp.data.repository;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import michael.com.firebaseapp.data.model.Post;
import rx.Observable;
import rx.Subscriber;
import rx.subscriptions.Subscriptions;

/**
 * Created by Mikhail on 2/5/17.
 */

public class PostRepository implements IPosts {

    DatabaseReference mDatabaseReference;
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;

    public PostRepository(FirebaseAuth firebaseAuth, FirebaseUser firebaseUser, DatabaseReference databaseReference) {
        this.mFirebaseAuth = firebaseAuth;
        this.mFirebaseUser = firebaseUser;
        this.mDatabaseReference = databaseReference;
    }

    @Override
    public Observable getPost() {
        Query query = mDatabaseReference
                .child("users")
                .child(mFirebaseUser.getUid())
                .child("posts")
                .orderByChild("title");
        return observe(query);
    }

    @Override
    public void sendPost(Post post) {
        mDatabaseReference.child("users")
                .child(mFirebaseUser.getUid())
                .child("posts").push()
                .setValue(post);
    }

    private Observable<DataSnapshot> observe(final Query ref) {
        return Observable.create(new Observable.OnSubscribe<DataSnapshot>() {
            @Override
            public void call(final Subscriber<? super DataSnapshot> subscriber) {
                final ValueEventListener listener = ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        subscriber.onNext(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        subscriber.onError(new FirebaseException(databaseError.getMessage()));
                    }
                });

                // When the subscription is cancelled, remove the listener
                subscriber.add(Subscriptions.create(() -> ref.removeEventListener(listener)));
            }
        });
    }

}
