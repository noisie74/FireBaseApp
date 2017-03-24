package michael.com.firebaseapp.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mikhail on 1/31/17.
 */

public class Post {

    private String title;
    private String body;
    @SerializedName("$item_id")
    private String postID;

    public Post() {
    }

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public boolean isEmpty() {
        return (title == null || "".equals(title)) &&
                (body == null || "".equals(body));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;

        Post post = (Post) o;

        if (title != null ? !title.equals(post.title) : post.title != null) return false;
        return body != null ? body.equals(post.body) : post.body == null;

    }


    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }
}
