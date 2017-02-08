package michael.com.firebaseapp.showpost;

/**
 * Created by Mikhail on 2/7/17.
 */

public interface ShowPostContract {

    interface View {

        void showEmptyPostListError();

        void hideFragment();
    }

    void showPostsList();

}
