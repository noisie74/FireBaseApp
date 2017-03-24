package michael.com.firebaseapp.addpost.adapter;

import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;

import java.util.List;

import michael.com.firebaseapp.R;
import michael.com.firebaseapp.data.model.Post;

public class AddPostAdapter extends RecyclerView.Adapter<AddPostAdapter.ViewHolder> {

    private List<Post> postObject;
    private final OnItemClickListener listener;

    public AddPostAdapter(List<Post> postObject, OnItemClickListener onItemClicked) {
        this.postObject = postObject;
        this.listener = onItemClicked;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.post_title) TextView cardTitle;
        @BindView(R.id.post_description) TextView cardDescription;


        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.post_row)
        public void onItemClick() {
            if (listener != null) {
                listener.onItemClick(itemView, getLayoutPosition());
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_card, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.cardTitle.setText(postObject.get(position).getTitle());
        holder.cardDescription.setText(postObject.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return postObject.size();
    }
}
