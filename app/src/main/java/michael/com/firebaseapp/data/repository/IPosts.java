package michael.com.firebaseapp.data.repository;

import michael.com.firebaseapp.data.model.Post;
import michael.com.firebaseapp.data.model.Response;
import michael.com.firebaseapp.data.model.Users;
import rx.Observable;

/**
 * Created by Mikhail on 2/5/17.
 */

public interface IPosts {

//    Observable sendPost(Post post);

    void sendPost(Post post);

    Observable getPost();
}
