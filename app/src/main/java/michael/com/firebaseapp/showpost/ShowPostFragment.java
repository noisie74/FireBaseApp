package michael.com.firebaseapp.showpost;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import michael.com.firebaseapp.R;

/**
 * Created by Mikhail on 2/7/17.
 */

public class ShowPostFragment extends Fragment {

    private LinearLayoutManager linearLayoutManager;
    @BindView(R.id.swipeContainer) SwipeRefreshLayout swipeContainer;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    public static ShowPostFragment newInstance() {
        return new ShowPostFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_recycler_view, container, false);
        ButterKnife.bind(this, rootView);
        linearLayoutManager = new LinearLayoutManager(this.getActivity());

        setHasOptionsMenu(true);
        setRetainInstance(true);

        return rootView;
    }

}
