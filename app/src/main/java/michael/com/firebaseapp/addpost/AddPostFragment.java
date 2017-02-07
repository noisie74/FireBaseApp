package michael.com.firebaseapp.addpost;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import michael.com.firebaseapp.R;

/**
 * Created by Mikhail on 2/2/17.
 */

public class AddPostFragment extends Fragment implements AddPostContract.View {

    @BindView(R.id.add_note_title) EditText mTitleEditText;
    @BindView(R.id.add_note_description) EditText mDescriptionEditText;
    @BindView(R.id.button_post) Button mPostButton;

    public static AddPostFragment newInstance() {
        return new AddPostFragment();
    }

    public AddPostFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_post_fragment, container, false);
        ButterKnife.bind(this, rootView);

        setHasOptionsMenu(true);
        setRetainInstance(true);

        return rootView;
    }


    @Override
    public void showEmptyPostError() {
        Snackbar.make(mTitleEditText, R.string.title_empty_error, Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void hideFragment() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
