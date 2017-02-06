package michael.com.firebaseapp.post;

import michael.com.firebaseapp.model.Post;
import rx.Observable;

/**
 * Created by Mikhail on 2/5/17.
 */

public interface IPosts {

    Observable sendPost(Post post);

    Observable getPost(String title);
}
