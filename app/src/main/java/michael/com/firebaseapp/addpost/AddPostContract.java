package michael.com.firebaseapp.addpost;

import android.support.design.widget.Snackbar;

import java.util.List;

import michael.com.firebaseapp.data.model.Post;
import michael.com.firebaseapp.data.model.Response;
import rx.Observable;

/**
 * Created by Mikhail on 2/2/17.
 */

public interface AddPostContract {

    interface View {

        void showEmptyPostError();

        void hideFragment();

    }

    interface MainView {

        void showPostList(List<Post> list);
    }

    interface UserActionListener {

        void savePost(String title, String body);

//        void getPosts(Response response);
    }
}
