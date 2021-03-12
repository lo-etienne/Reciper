package devmob.rl.reciper.recipeeditor.editorfragments.info;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.ImageView;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import devmob.rl.reciper.R;
import devmob.rl.reciper.recipeeditor.IFragmentPusher;
import devmob.rl.reciper.recipeeditor.IScreen.IScreenInfo;
import devmob.rl.reciper.recipeeditor.RecipeEditorPresenter;

public class InfoFragment extends Fragment implements IFragmentPusher, IScreenInfo {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final String[] CAMERA_PERMISSION = new String[]{Manifest.permission.CAMERA};
    private static final int CAMERA_REQUEST_CODE = 10;
    public static final String TITLE = "Information";
    private View view;
    private RecipeEditorPresenter presenter;
    private final boolean newRecipe;
    private String currentPhotoPath;
    private InfoLoader loader;

    public InfoFragment(final RecipeEditorPresenter presenter,final boolean newRecipe){
        this.presenter = presenter;
        this.newRecipe = newRecipe;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recipe_editor_info_fragment, container, false);
        if(!newRecipe){
            presenter.setScreenInfo(this);
            presenter.setDataInfo(presenter.getRecipeUUID());
        }

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
        this.loader = new InfoLoader(view.findViewById(R.id.nom_recette),view.findViewById(R.id.description_contenu),
                view.findViewById(R.id.commentaire_contenu),view.findViewById(R.id.layout_difficulte),
                view.findViewById(R.id.layout_prix),view.findViewById(R.id.picker_nb_personne),
                view.findViewById(R.id.picker_note),view.findViewById(R.id.container));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        loader.setMinMaxNumberPicker();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.recipe_editor_fragment,menu);
    }

    @Override
    public void push() {
        InfoSetter.setInfo(view, presenter, currentPhotoPath);
        Log.d("push", "passage de push dans infofragment");
    }

    /**
     * Verifie si on a la permission d'utiliser l'appareil photo
     * @return true si on peut, false si peut pas
     */
    private boolean hasCameraPermission() { return ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED; }

    /**
     * demande a l'utilisateur sa permition pour utiliser l'apareil photo
     */
    private void requestPermission() { ActivityCompat.requestPermissions(this.getActivity(), CAMERA_PERMISSION, CAMERA_REQUEST_CODE); }

    /**
     * Methode creant un file pour une photo
     * @return file
     * @throws IOException si une erreur est lancer durant la creation d'un file
     */
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

    /**
     * appel l'appareil photo si le file ou la photo ira est bon ensuite appel onActivityResult
     */
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = new File(Environment.getExternalStorageDirectory(), "photo.jpg");
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.d("IOException", "la methode createFile a genere une IOException");
                ex.printStackTrace();
            }
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

    @Override
    public void update() {
        loader.loadValue(presenter);
    }
}
