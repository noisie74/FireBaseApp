package michael.com.firebaseapp.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import michael.com.firebaseapp.R;
import michael.com.firebaseapp.addpost.PostActivity;
import michael.com.firebaseapp.data.IUserAuth;
import michael.com.firebaseapp.toppost.TopPostActivity;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;
import timber.log.Timber;


public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        IUserAuth {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;
    private ArrayAdapter<String> adapter;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    //    @BindView(R.id.listView) ListView mListView;
//    @BindView(R.id.postTitle) EditText mEditTextTitle;
//    @BindView(R.id.postBody) EditText mEditTextBody;
//    @BindView(R.id.addButton) Button mButton;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
//    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.image_main_view) ImageView mainImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main2);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        mainImage.setImageResource(R.drawable.firebase);

        initFireBase(); // Initialize FireBase

        if (mFirebaseUser == null) {
            // Not logged in, launch the Log In activity
            loadLogInView();
        } else {
            mUserId = mFirebaseUser.getUid();
//            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
//            mListView.setAdapter(adapter);
//
//            setButtonClickListener();
//            populateListViewWithData();
//            deletePostTitle();
//            deletePostBody();
            Intent intent = new Intent(MainActivity.this, PostActivity.class);
            startActivity(intent);
        }


    }

    @Override
    public Observable<FirebaseUser> observeAuthState(FirebaseAuth firebaseAuth) {
        return Observable.create(new Observable.OnSubscribe<FirebaseUser>() {
            @Override
            public void call(Subscriber<? super FirebaseUser> subscriber) {
                FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(firebaseAuth.getCurrentUser());
                            Timber.d("Current user: "+ firebaseAuth.getCurrentUser().toString());
                        }
                    }
                };

                firebaseAuth.addAuthStateListener(authStateListener);

                subscriber.add(Subscriptions.create(new Action0() {
                    @Override
                    public void call () {
                        firebaseAuth.removeAuthStateListener(authStateListener);

                    }

                }));
            }
        });
    }


    //    private void populateListViewWithData() {
//        mDatabase.child("users").child(mUserId).child("posts").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                adapter.add((String) dataSnapshot.child("title").getValue());
//                adapter.add((String) dataSnapshot.child("body").getValue());
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

//    private void deletePostTitle() {
//        mListView.setOnItemLongClickListener((parent, view, position, id) -> {
//            mDatabase.child("users").child(mUserId).child("posts")
//                    .orderByChild("title")
//                    .equalTo((String) mListView.getItemAtPosition(position))
//                    .addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            if (dataSnapshot.hasChildren()) {
//                                DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
//                                firstChild.getRef().removeValue();
//                                adapter.remove((String) mListView.getItemAtPosition(position));
//                                adapter.notifyDataSetChanged();
//                                deletePostBody();
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });
//
//            return false;
//        });
//    }

//    private void deletePostBody() {
//        mListView.setOnItemLongClickListener((parent, view, position, id) -> {
//            mDatabase.child("users").child(mUserId).child("posts")
//                    .orderByChild("body")
//                    .equalTo((String) mListView.getItemAtPosition(position))
//                    .addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            if (dataSnapshot.hasChildren()) {
//                                DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
//                                firstChild.getRef().removeValue();
//                                adapter.remove((String) mListView.getItemAtPosition(position));
//                                adapter.notifyDataSetChanged();
//                            } else {
//                                adapter.remove((String) mListView.getItemAtPosition(position));
//                                adapter.notifyDataSetChanged();
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });
//
//            return false;
//        });
//    }


//    private void setButtonClickListener() {
//        mButton.setOnClickListener(v -> {
//
//            Post post = new Post(mEditTextTitle.getText().toString(), mEditTextBody.getText().toString());
//            mDatabase.child("users").child(mUserId).child("posts").push().setValue(post);
//            mEditTextTitle.setText("");
//            mEditTextBody.setText("");
//        });
//    }

    private void initFireBase() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

//    public FirebaseAuth getCurrentUser(FirebaseAuth firebaseAuth) {
//        return FirebaseAuth.getInstance();
//    }

    private void loadLogInView() {
        Intent intent = new Intent(this, LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_top_posts) {
            Intent intent = new Intent(MainActivity.this, TopPostActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_my_posts) {
            Intent intent = new Intent(MainActivity.this, PostActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_search) {

        } else if (id == R.id.nav_log_out) {
            mFirebaseAuth.signOut();
            loadLogInView();
        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



}


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
////        if (id == R.id.action_logout) {
//            mFirebaseAuth.signOut();
//            loadLogInView();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


