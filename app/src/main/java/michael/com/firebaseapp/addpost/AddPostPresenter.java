package michael.com.firebaseapp.addpost;

import java.util.List;

import javax.inject.Inject;

import michael.com.firebaseapp.FireBaseApp;
import michael.com.firebaseapp.data.model.Post;
import michael.com.firebaseapp.data.model.Response;
import michael.com.firebaseapp.data.repository.PostRepository;

/**
 * Created by Mikhail on 2/2/17.
 */

public class AddPostPresenter implements AddPostContract.UserActionListener {

    PostRepository mRepository;
    FireBaseApp mFireBaseApp;

    private final AddPostContract.View mView;


    public AddPostPresenter(PostRepository postRepository, AddPostContract.View view) {
        this.mRepository = postRepository;
        mView = view;

//        mFireBaseApp.getApplicationComponent().inject(this);
    }

    @Override
    public void savePost(String title, String body) {

        Post post = new Post(title, body);
        if (post.isEmpty()) {
            mView.showEmptyPostError();
        } else {
            mRepository.sendPost(post);
            mView.hideFragment();
        }

    }

//    @Override
//    public void getPosts(Response response) {
//        mRepository.getPost();
//        List<Post> list = response.getPostList();
//        mView.showPostList(list);
//    }
}
