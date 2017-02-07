package michael.com.firebaseapp.data.repository;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import dagger.internal.Preconditions;
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

    public PostRepository(DatabaseReference databaseReference) {
        this.mDatabaseReference = Preconditions.checkNotNull(databaseReference);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
    }

    @Override
    public Observable getPost(String title) {
        Query query = mDatabaseReference
                .child("users")
                .child(mFirebaseUser.getUid())
                .child("posts")
                .orderByChild("title");
        return observe(query);

    }


    @Override
    public Observable sendPost(Post post) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(final Subscriber<? super Boolean> subscriber) {
                mDatabaseReference.child("users")
                        .child(mFirebaseUser.getUid())
                        .child(post.getTitle())
                        .child(post.getBody())
                        .push()
//                        .setValue(new PhotoComment(Calendar.getInstance().getTime(), message))
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override public void onComplete(@NonNull Task<Void> task) {
//                                subscriber.onNext(task.isSuccessful());
//                                subscriber.onCompleted();
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override public void onFailure(@NonNull Exception e) {
//                                subscriber.onError(new FirebaseException(e.getMessage()));
//                            }
//                        });
            }
        });
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
