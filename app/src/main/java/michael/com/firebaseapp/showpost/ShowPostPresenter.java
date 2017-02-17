package michael.com.firebaseapp.showpost;

import java.util.List;

import javax.inject.Inject;

import michael.com.firebaseapp.FireBaseApp;
import michael.com.firebaseapp.data.model.Post;
import michael.com.firebaseapp.data.model.Response;
import michael.com.firebaseapp.data.repository.PostRepository;

/**
 * Created by Mikhail on 2/7/17.
 */

public class ShowPostPresenter implements ShowPostContract {

    PostRepository mPostRepository;
    ShowPostContract.View mView;
    FireBaseApp fireBaseApp;

    public ShowPostPresenter(PostRepository postRepository, ShowPostContract.View view) {
        this.mPostRepository = postRepository;
        mView = view;

//       ((FireBaseApp) fireBaseApp.getApplicationComponent().inject(this));
    }

//    @Override
//    public void showPostsList(Response response) {
//        mPostRepository.getPost();
//        List<Post> list = response.getPostList();
//        mView.showPostList(list);
//    }
}
