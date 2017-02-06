package michael.com.firebaseapp.addpost;

import javax.inject.Inject;

import michael.com.firebaseapp.model.Post;
import michael.com.firebaseapp.post.PostRepository;

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
            mView.showPostsList();
        }
    }


    @Override
    public void loadPostFailed() {
        mView.showEmptyPostError();
    }
}
