package michael.com.firebaseapp.showpost;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import michael.com.firebaseapp.R;
import michael.com.firebaseapp.addpost.adapter.AddPostAdapter;
import michael.com.firebaseapp.data.model.Post;
import michael.com.firebaseapp.data.repository.PostRepository;

/**
 * Created by Mikhail on 2/7/17.
 */

public class ShowPostFragment extends Fragment implements ShowPostContract.View {

    private LinearLayoutManager linearLayoutManager;
    @BindView(R.id.swipeContainer) SwipeRefreshLayout swipeContainer;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    ShowPostContract listener;
    PostRepository mPostRepository;
    List<Post> mPostList;

    public static ShowPostFragment newInstance() {
        return new ShowPostFragment();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


//        listener = new ShowPostPresenter(mPostRepository,this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_recycler_view, container, false);
        ButterKnife.bind(this, rootView);
        linearLayoutManager = new LinearLayoutManager(this.getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);

        setHasOptionsMenu(true);
        setRetainInstance(true);

//        listener.showPostsList();

        DatabaseReference mDatabaseReference;
        FirebaseAuth mFirebaseAuth;
        FirebaseUser mFirebaseUser;

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();


        mPostRepository = new PostRepository(mFirebaseAuth, mFirebaseUser, mDatabaseReference);

        mPostList = new ArrayList<>();

        showPostList(mPostList);

        return rootView;
    }


    @Override
    public void showEmptyPostListError() {

    }

    @Override
    public void hideFragment() {

    }

//    @Override
    public void showPostList(List<Post> list) {

//        if (mPostRepository != null){
//
            mPostRepository.getPost();
//        }


        AddPostAdapter adapter = new AddPostAdapter(list, new AddPostAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_LONG).show();
            }
        });

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
