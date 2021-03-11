package devmob.rl.reciper.recipeeditor.editorfragments;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;

import devmob.rl.reciper.R;
import devmob.rl.reciper.recipeeditor.IFragmentPusher;
import devmob.rl.reciper.recipeeditor.RecipeEditorPresenter;

public class InfoFragment extends Fragment implements IFragmentPusher {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final String[] CAMERA_PERMISSION = new String[]{Manifest.permission.CAMERA};
    private static final int CAMERA_REQUEST_CODE = 10;
    public static final String TITLE = "Information";
    private View view;
    private RecipeEditorPresenter presenter;
    private final boolean newRecipe;
    private String currentPhotoPath;

    public InfoFragment(RecipeEditorPresenter presenter, boolean newRecipe){
        this.presenter = presenter;
        this.newRecipe = newRecipe;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recipe_editor_info_fragment, container, false);

        Button enableCamera = view.findViewById(R.id.button_photo);
        enableCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasCameraPermission()) {
                    dispatchTakePictureIntent();
                } else {
                    requestPermission();
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        NumberPicker np = (NumberPicker) view.findViewById(R.id.picker_nb_personne);
        np.setMinValue(1);
        np.setMaxValue(100);
        np = (NumberPicker) view.findViewById(R.id.picker_note);
        np.setMinValue(1);
        np.setMaxValue(5);
        if(!(newRecipe)) loadValue();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.recipe_editor_fragment,menu);
    }

    private String getDifficulty(int id){
        if(id == R.id.facile) return "facile";
        if(id == R.id.moyen) return "moyen";
        if(id == R.id.difficile) return "difficile";
        else throw new IllegalArgumentException();
    }

    private String getPrice(int id){
        if(id == R.id.pas_cher) return "pas cher";
        if(id == R.id.cher) return "cher";
        if(id == R.id.tres_cher) return "tres cher";
        else throw new IllegalArgumentException();
    }


    @Override
    public void push() {
        EditText ed;

        ed = (EditText)view.findViewById(R.id.nom_recette);
        String nom = ed.getText().toString();
        ed = (EditText)view.findViewById(R.id.description_contenu);
        String description = ed.getText().toString();
        ed = (EditText)view.findViewById(R.id.commentaire_contenu);
        String commentaire = ed.getText().toString();

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.layout_difficulte);
        int id_dif = radioGroup.getCheckedRadioButtonId();
        radioGroup = (RadioGroup) view.findViewById(R.id.layout_prix);
        int id_price = radioGroup.getCheckedRadioButtonId();

        NumberPicker np = (NumberPicker) view.findViewById(R.id.picker_nb_personne);
        int numberPerson = np.getValue();
        np = (NumberPicker) view.findViewById(R.id.picker_note);
        int note = np.getValue();

        presenter.setInfoFragment(nom,getDifficulty(id_dif),getPrice(id_price),numberPerson,description,commentaire,note,currentPhotoPath);
        Log.d("1", "passage de push ds infofragment");
    }

    private void loadValue() {
        EditText ed;

        ed = (EditText)view.findViewById(R.id.nom_recette);
        ed.setText(presenter.getName_recipe());
        ed = (EditText)view.findViewById(R.id.description_contenu);
        ed.setText(presenter.getDescription_recipe());
        ed = (EditText)view.findViewById(R.id.commentaire_contenu);
        ed.setText(presenter.getCommentary_recipe());

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.layout_difficulte);
        radioGroup.check(getIdDifficulty(presenter.getDifficulty_recipe()));

        radioGroup = (RadioGroup) view.findViewById(R.id.layout_prix);
        radioGroup.check(getIdPrice(presenter.getPrice_recipe()));

        NumberPicker np = (NumberPicker) view.findViewById(R.id.picker_nb_personne);
        np.setValue(presenter.getNbPeople_recipe());
        np = (NumberPicker) view.findViewById(R.id.picker_note);
        np.setValue(presenter.getNote());
    }

    private int getIdPrice(String price_recipe) {
        if(price_recipe.equals("pas cher")) return R.id.pas_cher;
        if(price_recipe.equals("cher")) return R.id.cher;
        if(price_recipe.equals("tres cher")) return R.id.tres_cher;
        else throw new IllegalArgumentException();
    }

    private int getIdDifficulty(String difficulty_recipe) {
        if(difficulty_recipe.equals("facile")) return R.id.facile;
        if(difficulty_recipe.equals("moyen")) return R.id.moyen;
        if(difficulty_recipe.equals("difficile")) return R.id.difficile;
        else throw new IllegalArgumentException();
    }

    private boolean hasCameraPermission() {
        return ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this.getActivity(), CAMERA_PERMISSION, CAMERA_REQUEST_CODE);
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        }
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        Log.d("1",currentPhotoPath);
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = new File(Environment.getExternalStorageDirectory(), "photo.jpg");
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this.getContext(),
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                super.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                Log.d("1", "dispatchTakePictureIntent appeler");
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            ImageView image = view.findViewById(R.id.container);
            image.setImageURI(Uri.fromFile(new File(currentPhotoPath)));
            Log.d("1", "onActivityResult appeler condition");
        }
        Log.d("1", "onActivityResult appeler");
    }
}
