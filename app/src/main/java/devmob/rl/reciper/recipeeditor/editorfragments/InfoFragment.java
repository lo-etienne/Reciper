package devmob.rl.reciper.recipeeditor.editorfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import devmob.rl.reciper.R;

public class InfoFragment extends Fragment {
    public static final String TITLE = "Information";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recipe_editor_info_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        NumberPicker np = (NumberPicker) view.findViewById(R.id.picker_nb_personne);
        np.setMinValue(1);
        np.setMaxValue(100);
        Bundle args = getArguments();
    }
}
