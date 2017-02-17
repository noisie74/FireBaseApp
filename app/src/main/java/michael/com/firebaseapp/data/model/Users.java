package michael.com.firebaseapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mikhail on 2/9/17.
 */

public class Users {

    @SerializedName("$uid")
    private String UID;
    @SerializedName("posts")
    private List<Post> mPostList;

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public List<Post> getPostList() {
        return mPostList;
    }

    public void setPostList(List<Post> postList) {
        mPostList = postList;
    }
}
