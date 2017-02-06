package michael.com.firebaseapp.addpost;

import rx.Observable;

/**
 * Created by Mikhail on 2/2/17.
 */

public interface AddPostContract {

    interface View {

        void showEmptyPostError();

        void showPostsList();
    }

    interface UserActionListener {

//        Observable sendPost(String title, String body);
//
//        Observable getPost(String title);

        void savePost(String title, String body);

        void loadPostFailed();
    }
}
