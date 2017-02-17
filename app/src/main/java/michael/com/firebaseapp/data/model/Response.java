package michael.com.firebaseapp.data.model;

import java.util.List;

/**
 * Created by Mikhail on 2/16/17.
 */

public class Response {

    private List<Post> postList;

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
