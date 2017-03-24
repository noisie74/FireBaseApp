package michael.com.firebaseapp.showpost;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import michael.com.firebaseapp.R;
import michael.com.firebaseapp.data.model.Post;

/**
 * Created by Mikhail on 2/7/17.
 */

public class ShowPostFragment extends Fragment {

    private LinearLayoutManager linearLayoutManager;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;
    private FirebaseRecyclerAdapter<Post, PostHolder> mAdapter;


    public static ShowPostFragment newInstance() {
        return new ShowPostFragment();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_recycler_view, container, false);
        ButterKnife.bind(this, rootView);
        linearLayoutManager = new LinearLayoutManager(this.getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);


        setHasOptionsMenu(true);
        setRetainInstance(true);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mAdapter = new FirebaseRecyclerAdapter<Post, PostHolder>(Post.class, R.layout.note_card,
                PostHolder.class, mDatabase.child("users").child(mFirebaseUser.getUid()).child("posts")) {
            @Override
            public void populateViewHolder(PostHolder postMessageViewHolder, Post postMessage, int position) {
                postMessageViewHolder.setName(postMessage.getTitle());
                postMessageViewHolder.setText(postMessage.getBody());

            }
        };
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

    public static class PostHolder extends RecyclerView.ViewHolder {
        private final TextView mPostField;
        private final TextView mBodyField;

        public PostHolder(View itemView) {
            super(itemView);
            mPostField = (TextView) itemView.findViewById(R.id.post_title);
            mBodyField = (TextView) itemView.findViewById(R.id.post_description);
        }

        public void setName(String name) {
            mPostField.setText(name);
        }

        public void setText(String text) {
            mBodyField.setText(text);
        }
    }
}
