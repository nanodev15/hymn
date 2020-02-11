package com.example.elommarcarnold.cantik0;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class SplashActivity extends AppCompatActivity {
    InputStream data;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


/*

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/

        new FetchStats().execute();
    }


    private class FetchStats extends AsyncTask<String, String, String> {
        JSONObject response;


        @Override
        protected String doInBackground(String... params) {

            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/com.your.app.media/Cantiques.pdf";
            if (new File(path).exists()) return  null;



            try {
                this.extractInstaller() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }



        protected void onPostExecute(String result) {
            runOnUiThread(new Runnable() {
                public void run() {
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);




                    // close this activity
                    finish();

                }
            });
        }

        private void copyFile(InputStream in, OutputStream out) throws IOException {
            byte[] buffer = new byte[1024];
            int read;
            while((read = in.read(buffer)) != -1){
                out.write(buffer, 0, read);
            }
        }

        private void extractInstaller() throws IOException {
// Path to write to

            String path = Environment.getExternalStorageDirectory()

                    .getAbsolutePath() + "/Android/data/com.your.app.media/";


// Current state of the external media

            String extState = Environment.getExternalStorageState();

// External media must be written onto

            if (extState.equals(Environment.MEDIA_MOUNTED)) {

                try { // Ensure path exists

                    boolean exists = (new File(path)).exists();

                    if (!exists) {

                        new File(path).mkdirs();

                    }

                    data = getResources().getAssets().open("Cantiques.pdf");
                    File outFile = new File(path, "Cantiques.pdf");
                    FileOutputStream out = new FileOutputStream(outFile);
                    copyFile(data, out);
                } catch(IOException e) {
                    Log.e("tag", "Failed to copy asset file: " + "Cantiques", e);
                } finally {
                    if (data != null) {
                        try {
                            data.close();
                        } catch (IOException e) {
                            // NOOP
                        }
                    }
                }
            } else {

                Log.e(getPackageName(), "not mounted?");

            }



        }






    }


}
