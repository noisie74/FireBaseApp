package michael.com.firebaseapp.showpost;

import javax.inject.Inject;

import michael.com.firebaseapp.FireBaseApp;
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

    @Override
    public void showPostsList() {

    }
}
