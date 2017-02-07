package michael.com.firebaseapp.addpost;

import javax.inject.Inject;

import michael.com.firebaseapp.data.model.Post;
import michael.com.firebaseapp.data.repository.PostRepository;

/**
 * Created by Mikhail on 2/2/17.
 */

public class AddPostPresenter implements AddPostContract.UserActionListener {

    @Inject PostRepository mRepository;

    private final AddPostContract.View mView;
//    private final PostRepository mRepository;


    public AddPostPresenter(PostRepository postRepository, AddPostContract.View view) {
        mRepository = postRepository;
        mView = view;
    }

    @Override
    public void savePost(String title, String body) {

        Post post = new Post(title, body);
        if (post.isEmpty()) {
            mView.showEmptyPostError();
        } else {
            mRepository.sendPost(post);
        }
    }

    @Override
    public void getPost(String title) {
        mView.hideFragment();
//        mView.showPostsList();

    }

    @Override
    public void loadPostFailed() {
        mView.showEmptyPostError();
    }
}
