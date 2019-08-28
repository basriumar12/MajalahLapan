package id.go.lapan.majalahlapan.ui.editorialteam;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import id.go.lapan.majalahlapan.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditorialTeamFragment extends Fragment {


    public EditorialTeamFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().setTitle(getString(R.string.menu_editorial_team));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_editorial_team, container, false);
    }

}
