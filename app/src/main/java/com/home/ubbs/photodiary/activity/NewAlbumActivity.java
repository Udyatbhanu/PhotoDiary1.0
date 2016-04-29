package com.home.ubbs.photodiary.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.home.ubbs.photodiary.R;
import com.home.ubbs.photodiary.data.Album;
import com.home.ubbs.photodiary.lib.PhotoDiaryBaseActivity;
import com.home.ubbs.photodiary.sql.PhotoDiarySQLHelper;
import com.home.ubbs.photodiary.utils.CameraUtils;
import com.home.ubbs.photodiary.utils.Constants;

import java.io.File;

/**
 * Created by udyatbhanu-mac on 4/16/16.
 */
public class NewAlbumActivity  extends PhotoDiaryBaseActivity {

    private static final int REQUEST_WRITE_STORAGE = 112;
    EditText titleTextView, locationTextView, descriptionTextView;
    Button createAlbumButton;
    Album album;
    private PhotoDiarySQLHelper photoDiarySQLHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_album);
        setHeaderTitle(R.string.drawer_item_new_album_header);

        titleTextView = (EditText)findViewById(R.id.title_text_view);
        locationTextView = (EditText)findViewById(R.id.location_text_view);
        descriptionTextView = (EditText)findViewById(R.id.description_text_view);
        createAlbumButton = (Button)findViewById(R.id.create_album);
        photoDiarySQLHelper = new PhotoDiarySQLHelper(this);

        createAlbumButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                album = new Album();
                album.setTitle(titleTextView.getText().toString());
                album.setLocation(locationTextView.getText().toString());
                album.setDescription(descriptionTextView.getText().toString());
                boolean hasPermission = (ContextCompat.checkSelfPermission(NewAlbumActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
                if (!hasPermission) {
                    ActivityCompat.requestPermissions(NewAlbumActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_WRITE_STORAGE);
                }else{
                    new SQLLoaderTask(album).execute();
                }

            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_WRITE_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    new SQLLoaderTask(album).execute();
                } else
                {
                    Toast.makeText(this, "The app was not allowed to write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }
            }
        }

    }


    /**
     *
     */
    private class SQLLoaderTask extends AsyncTask<Void, Void, Uri> {
        private Album album;
        public SQLLoaderTask(Album travelAlbum){
            album = travelAlbum;

        }

        @Override
        protected void onPreExecute() {

        }

        // Decode image in background.
        @Override
        protected Uri doInBackground(Void... params) {

            String albumLocation = Constants.PHOTO_ROOT_FOLDER+ File.separator+album.getTitle();
            Uri uri = CameraUtils.getPhotoMediaUri(albumLocation);
            photoDiarySQLHelper.addAlbum(album);

            return uri;
        }

        @Override
        protected void onPostExecute(Uri uri) {

            // do something

            Toast.makeText(NewAlbumActivity.this, "Data inserted successfully for "+ uri.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
