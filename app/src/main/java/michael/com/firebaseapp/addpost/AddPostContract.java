package michael.com.firebaseapp.addpost;


/**
 * Created by Mikhail on 2/2/17.
 */

public interface AddPostContract {

    interface View {

        void showEmptyPostError();

        void hideFragment();

    }

    interface UserActionListener {

        void savePost(String title, String body);

    }
}
