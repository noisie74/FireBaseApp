package michael.com.firebaseapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import michael.com.firebaseapp.R;
import michael.com.firebaseapp.model.Post;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;
    @BindView(R.id.toolbar)
    public Toolbar mToolbar;
    @BindView(R.id.listView)
    public ListView mListView;
    @BindView(R.id.postTitle)
    public EditText mEditTextTitle;
    @BindView(R.id.postBody)
    public EditText mEditTextBody;
    @BindView(R.id.addButton)
    public Button mButton;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        initFireBase(); // Initialize FireBase

        if (mFirebaseUser == null) {
            // Not logged in, launch the Log In activity
            loadLogInView();
        } else {
            mUserId = mFirebaseUser.getUid();
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
            mListView.setAdapter(adapter);

            setButtonClickListener();
            populateListViewWithData();
            deletePostTitle();
//            deletePostBody();
        }

    }

    private void populateListViewWithData() {
        mDatabase.child("users").child(mUserId).child("posts").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                adapter.add((String) dataSnapshot.child("title").getValue());
                adapter.add((String) dataSnapshot.child("body").getValue());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void deletePostTitle() {
        mListView.setOnItemLongClickListener((parent, view, position, id) -> {
            mDatabase.child("users").child(mUserId).child("posts")
                    .orderByChild("title")
                    .equalTo((String) mListView.getItemAtPosition(position))
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChildren()) {
                                DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
                                firstChild.getRef().removeValue();
                                adapter.remove((String) mListView.getItemAtPosition(position));
                                adapter.notifyDataSetChanged();
                                deletePostBody();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

            return false;
        });
    }

    private void deletePostBody() {
        mListView.setOnItemLongClickListener((parent, view, position, id) -> {
            mDatabase.child("users").child(mUserId).child("posts")
                    .orderByChild("body")
                    .equalTo((String) mListView.getItemAtPosition(position))
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChildren()) {
                                DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
                                firstChild.getRef().removeValue();
                                adapter.remove((String) mListView.getItemAtPosition(position));
                                adapter.notifyDataSetChanged();
                            } else {
                                adapter.remove((String) mListView.getItemAtPosition(position));
                                adapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

            return false;
        });
    }


    private void setButtonClickListener() {
        mButton.setOnClickListener(v -> {

            Post post = new Post(mEditTextTitle.getText().toString(), mEditTextBody.getText().toString());
            mDatabase.child("users").child(mUserId).child("posts").push().setValue(post);
            mEditTextTitle.setText("");
            mEditTextBody.setText("");
        });
    }

    private void initFireBase() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            mFirebaseAuth.signOut();
            loadLogInView();
        }

        return super.onOptionsItemSelected(item);
    }

}
