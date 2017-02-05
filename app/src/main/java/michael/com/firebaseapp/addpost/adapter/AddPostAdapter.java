package michael.com.firebaseapp.addpost.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class AddPostAdapter<T> extends RecyclerView.Adapter<AddPostAdapter.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder  {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public AddPostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(AddPostAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
