package michael.com.firebaseapp.showpost;

import java.util.List;

import michael.com.firebaseapp.data.model.Post;
import michael.com.firebaseapp.data.model.Response;

/**
 * Created by Mikhail on 2/7/17.
 */

public interface ShowPostContract {

    interface View {

        void showEmptyPostListError();

        void hideFragment();

//        void showPostList(List<Post> list);

    }

//    void showPostsList(Response response);

}
