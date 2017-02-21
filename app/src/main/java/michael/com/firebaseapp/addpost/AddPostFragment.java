package michael.com.firebaseapp.addpost;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import michael.com.firebaseapp.R;
import michael.com.firebaseapp.addpost.adapter.AddPostAdapter;
import michael.com.firebaseapp.data.model.Post;
import michael.com.firebaseapp.data.repository.PostRepository;
import michael.com.firebaseapp.showpost.ShowPostFragment;

/**
 * Created by Mikhail on 2/2/17.
 */

public class AddPostFragment extends Fragment implements AddPostContract.View {

    PostRepository mPostRepository;

    @BindView(R.id.add_note_title) EditText mTitleEditText;
    @BindView(R.id.add_note_description) EditText mDescriptionEditText;
    @BindView(R.id.button_post) Button mPostButton;
    private AddPostContract.UserActionListener mActionListener;


    public static AddPostFragment newInstance() {
        return new AddPostFragment();
    }

    public AddPostFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DatabaseReference mDatabaseReference;
        FirebaseAuth mFirebaseAuth;
        FirebaseUser mFirebaseUser;

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();


        mPostRepository = new PostRepository(mFirebaseAuth, mFirebaseUser, mDatabaseReference);
        mActionListener = new AddPostPresenter(mPostRepository, this);

        savePostWhenButtonSaveClicked();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_post_fragment, container, false);
        ButterKnife.bind(this, rootView);

        setHasOptionsMenu(true);
        setRetainInstance(true);

//        savePostWhenButtonSaveClicked();

        return rootView;
    }

    private void savePostWhenButtonSaveClicked() {

        mPostButton.setOnClickListener(v -> {

            mActionListener.savePost(mTitleEditText.getText().toString(), mDescriptionEditText.getText().toString());
//            hideFragment();
        });

    }

//    @Override
//    public void showPostList(List<Post> list) {
//        AddPostAdapter mAdapter = new AddPostAdapter(list, new AddPostAdapter.OnItemClickListener() {
//
//            ShowPostFragment fragment = new ShowPostFragment();
//
//
//
//            @Override
//            public void onItemClick(View itemView, int position) {
//
//                Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_LONG).show();
//
////                ShowPostFragment fragment = new ShowPostFragment();
////
////                getActivity().onAttachFragment(fragment);
//
//
//            }
//        });
//    }

    @Override
    public void showEmptyPostError() {
        Snackbar.make(mTitleEditText, R.string.title_empty_error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void hideFragment() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
