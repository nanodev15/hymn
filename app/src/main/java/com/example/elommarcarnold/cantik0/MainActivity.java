package com.example.elommarcarnold.cantik0;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.artifex.mupdfdemo.FilePicker;
import com.artifex.mupdfdemo.MuPDFCore;
import com.artifex.mupdfdemo.MuPDFPageAdapter;
import com.artifex.mupdfdemo.MuPDFReaderView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    MuPDFReaderView reader;
    private int hymnNumber;
    private Bundle bundle = new Bundle();
    String url2;
    Boolean test = false;
    private static long back_pressed;
    private MenuItem securedConnection;
    private MenuItem insecuredConnection;
    private boolean granted = false;
    private MenuItem pointeronitem;
    private int lastindex =-1;
    private static final int STORAGE_PERMISSION_CODE = 101;



    // int[] mappings = new int[] {1, 1, 2, 3, 4, 4, 5, 6, 6, 7, 8, 9, 10, 10, 11, 11, 12, 13, 13, 14, 14, 15, 15, 16, 16, 17, 18, 19, 19, 20, 21, 22, 22, 23, 24, 25, 25, 26, 26, 27, 28, 29, 29, 30, 30, 31, 32, 32, 33, 33, 34, 34, 35, 36, 37, 37, 38, 38, 39, 40, 40, 41, 41, 42, 42, 43, 44, 44, 45, 46, 46, 47, 47, 48, 48, 49, 50, 50, 51, 52, 52, 53, 54, 55, 56, 56, 57, 58, 58, 59, 59, 60, 60, 61, 62, 62, 63, 64, 64, 65, 66, 66, 67, 67, 68, 68, 69, 70, 71, 72, 72, 73, 74, 74, 75, 76, 77, 77, 78, 78, 79, 79, 80, 80, 81, 82, 83, 83, 84, 84, 85, 86, 86, 87, 88, 88, 89, 90, 90, 91, 92, 92, 93, 94, 95, 95, 96, 96, 97, 98, 98, 99, 100, 100, 101, 102, 102, 103, 104, 105, 105, 106, 106, 107, 108, 109, 110, 110, 111, 112, 112, 113, 114, 114, 115, 116, 116, 117, 118, 118, 119, 120, 121, 121, 122, 122, 123, 124, 124, 125, 126, 127, 127, 128, 128, 129, 129, 130, 130, 131, 132, 132, 133, 134, 134, 135, 136, 136, 137, 138, 138, 139, 140, 141, 141, 142, 142, 143, 144, 144, 145, 146, 146, 147, 148, 148, 149, 150, 150, 151, 152, 152, 153, 153, 154, 154, 155, 156, 156, 157, 158, 159, 159, 160, 160, 161, 162, 162, 163, 163, 164, 164, 165, 166, 166, 167, 168, 168, 169, 170, 170, 171, 171, 172, 172, 173, 174, 174, 175, 176, 176, 177, 177, 178, 178, 179, 179, 180, 180, 181, 182, 183, 184, 184, 185, 186, 186, 187, 188, 189, 190, 190, 191, 191, 192, 192, 193, 194, 194, 195, 196, 196, 197, 198, 198, 199, 200, 200, 201, 202, 202, 203, 204, 204, 205, 206, 206, 207, 208, 208, 209, 210, 210, 211, 211, 212, 212, 213, 213, 214, 214, 215, 216, 216, 217, 218, 218, 219, 220, 220, 221, 222, 222, 223, 224, 224, 225, 226, 226, 227, 228, 228, 229, 229, 230, 231, 231, 231, 232, 232, 232, 233, 233, 233, 234, 234, 234, 235, 235, 235, 235, 236, 236, 236, 237, 237, 237, 237, 238, 238, 238, 238, 239, 239, 239, 240, 240, 240, 241, 241, 241, 242, 242, 242, 243, 243, 243, 243, 243, 244, 244, 244, 245, 245, 245, 246, 246, 246, 247, 247, 247, 248, 248, 248, 248, 248, 249, 249, 249, 249, 250, 250, 250, 251, 251, 251, 252, 252, 252, 253, 253, 253, 253, 254, 254, 254, 254, 255, 255, 255, 256, 256, 256, 257, 257, 257, 257, 258, 258, 258, 258, 259, 259, 259, 260, 260, 261};
    int[] mappings = new int[] {1, 2, 3, 3, 4, 4, 5, 6, 6, 7, 8, 9, 10, 10, 11, 11, 12, 13, 13, 14, 14, 15, 15, 16, 16, 17, 18, 19, 19, 20, 21, 22, 22, 23, 24, 25, 25, 26, 26, 27, 28, 29, 29, 30, 30, 31, 32, 32, 33, 33, 34, 34, 35, 36, 37, 37, 38, 38, 39, 40, 40, 41, 41, 42, 42, 43, 44, 44, 45, 46, 46, 47, 47, 48, 48, 49, 50, 50, 51, 52, 52, 53, 54, 55, 56, 56, 57, 58, 58, 59, 59, 60, 60, 61, 62, 62, 63, 64, 64, 65, 66, 66, 67, 67, 68, 68, 69, 70, 71, 72, 72, 73, 74, 74, 75, 76, 77, 77, 78, 78, 79, 79, 80, 80, 81, 82, 83, 83, 84, 84, 85, 86, 86, 87, 88, 88, 89, 90, 90, 91, 92, 92, 93, 94, 95, 95, 96, 96, 97, 98, 98, 99, 100, 100, 101, 102, 102, 103, 104, 105, 105, 106, 106, 107, 108, 109, 110, 110, 111, 112, 112, 113, 114, 114, 115, 116, 116, 117, 118, 118, 119, 120, 121, 121, 122, 122, 123, 124, 124, 125, 126, 127, 127, 128, 128, 129, 129, 130, 130, 131, 132, 132, 133, 134, 134, 135, 136, 136, 137, 138, 138, 139, 140, 141, 141, 142, 142, 143, 144, 144, 145, 146, 146, 147, 148, 148, 149, 150, 150, 151, 152, 152, 153, 153, 154, 154, 155, 156, 156, 157, 158, 159, 159, 160, 160, 161, 162, 162, 163, 163, 164, 164, 165, 166, 166, 167, 168, 168, 169, 170, 170, 171, 171, 172, 172, 173, 174, 174, 175, 176, 176, 177, 177, 178, 178, 179, 179, 180, 180, 181, 182, 183, 184, 184, 185, 186, 186, 187, 188, 189, 190, 190, 191, 191, 192, 192, 193, 194, 194, 195, 196, 196, 197, 198, 198, 199, 200, 200, 201, 202, 202, 203, 204, 204, 205, 206, 206, 207, 208, 208, 209, 210, 210, 211, 211, 212, 212, 213, 213, 214, 214, 215, 216, 216, 217, 218, 218, 219, 220, 220, 221, 222, 222, 223, 224, 224, 225, 226, 226, 227, 228, 228, 229, 229, 230, 231, 231, 231, 232, 232, 232, 233, 233, 233, 234, 234, 234, 235, 235, 235, 235, 236, 236, 236, 237, 237, 237, 237, 238, 238, 238, 238, 239, 239, 239, 240, 240, 240, 241, 241, 241, 242, 242, 242, 243, 243, 243, 243, 243, 244, 244, 244, 245, 245, 245, 246, 246, 246, 247, 247, 247, 248, 248, 248, 248, 248, 249, 249, 249, 249, 250, 250, 250, 251, 251, 251, 252, 252, 252, 253, 253, 253, 253, 254, 254, 254, 254, 255, 255, 255, 256, 256, 256, 257, 257, 257, 257, 258, 258, 258, 258, 259, 259, 259, 260, 260, 261};
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instantiateobjects();


        checkPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                STORAGE_PERMISSION_CODE);

         if (granted == false) return;






        // =setContentView(R.layout.activity_main);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (reader !=null)  reader.setDisplayedViewIndex(6);
//
//
//                ///
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG);
//                Snackbar.make(view,    Environment.getExternalStorageDirectory().getPath()+"/Download/Cantiques.pdf"+"boolean"+test, Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//            }
//        });
        Log.i("dfdfdfd","just a test");

        /////    // RelativeLayout layout = (RelativeLayout) findViewById(R.id.main_layout);

        // important code



    }

    public void instantiateobjects() {
        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        Log.i("dfdfdfd","this part is done");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("EBBTHymn");
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        FrameLayout layout = (FrameLayout) findViewById(R.id.content_frame);
        try {//      //      MuPDFCore core = new MuPDFCore(this,getAssets().getLocales()[0])
//            //
//          //  System.out.println("path"+this.getAssets().openFd("Cantiques.pdf").toString());
//            //MuPDFCore core = new MuPDFCore(this,this.getAssets().openFd("Cantiques.pdf").toString());
            Log.i("dfdfdfd","opening 2");
            //    MuPDFCore core = new MuPDFCore(this, "/sdcard/Download/sbs50.pdf");
            //  MuPDFCore core = new MuPDFCore(this, Environment.getExternalStorageDirectory().getPath()+"/Download/mupdf_explored.pdf");
            //MuPDFCore core = new MuPDFCore(this, Environment.getExternalStorageDirectory().getPath()+"/Download/Cantiques.pdf");
            //     String url = Environment.getExternalStorageDirectory().getPath();
            //     if(Build.DEVICE.contains("Samsung") || Build.MANUFACTURER.contains("Samsung")){
            //          url=url+"/external_sd/";
            //     }

            //   Log.i("dfdfdfd","url done");
            //      url=url+"/Download/Cantiques.pdf";
//            String url =Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/com.your.app.media/Cantiques.pdf";
//
//            // Now test if the file is found
//            url2=url;
//            Log.i("dfdfdfd","before file");
//            // File file = getBaseContext().getFileStreamPath(url);
//            Log.i("dfdfdfd",getExternalFilesDir("Download").getAbsolutePath());
//            //  File file =new File(getExternalFilesDir(null),url);
//            File file =new File("/storage/emulated/0/Download/10gospel.pdf");
//            Log.i("dfdfdfd","after file");
//            if (file.exists()) { System.out.println("The file exists"); test = true;  Log.i("dfdfdfd","it is true");}
//            else {System.out.println("The file does not exist"); test =true; Log.i("dfdfdfd","it is false");}
//
//
//          MuPDFCore core = new MuPDFCore(this, url);
//      //    MuPDFCore core = new MuPDFCore(this, file.getAbsolutePath());
//
//            System.out.println("opened");
//            reader = new MuPDFReaderView(this);
//            System.out.println("after");
//
//            reader.setAdapter(new MuPDFPageAdapter(this, new FilePicker.FilePickerSupport() {
//                @Override
//                public void performPickFor(FilePicker filePicker) {
//
//                }
//            }, core));

            ////        //   layout.addView(reader);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            displaySelectedScreen(R.id.nav_num);      // did smth here

            //  setContentView(layout);
            //   setContentView(R.layout.activity_main);


        } catch (Exception e) {

        }



    }

    @Override
    public void onBackPressed()
    {

        getHymnMenuItem().setEnabled(false);
        if (lastindex==0)
        {
            Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar);
            toolbar2.setTitle("Index numérique");

        } else      if (lastindex==1)
    {
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar);
        toolbar2.setTitle("Index alphabétique");

    }  else     if (lastindex==2)
    {
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar);
        toolbar2.setTitle("Index thématique");

    } else     if (lastindex==3)
    {
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar);
        toolbar2.setTitle("Recherche par tonalité");

    }
        if ((mDrawerLayout.isDrawerOpen(GravityCompat.START))) {
        if (back_pressed + 2000 > System.currentTimeMillis()) super.onBackPressed();
        else
            Toast.makeText(getBaseContext(), "Appuyez encore une fois pour quitter!", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
       } else {
            super.onBackPressed();
        }
    }


    // added
    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {

            case R.id.nav_num0:    // Accueil cantiques
               // fragment = new Menu1();

                bundle.putInt("position", (int) 1);
                bundle.putString("key", "");



                Menu1 nextFrag= new Menu1();
                nextFrag.setArguments(bundle);
                fragment=nextFrag;

                break;

            case R.id.nav_num:   // Index numérique

                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                toolbar.setTitle("Index numérique");
                lastindex =0;

                fragment = new Menu2();



                break;

            case R.id.nav_camera:   // Index alphabétique
                Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar);
                toolbar2.setTitle("Index alphabétique");
                fragment = new Menu3();
                lastindex =1;
//
//                bundle.putInt("position", (int) 1);
//
//                Menu1 nextFrag= new Menu1();
//                nextFrag.setArguments(bundle);
//                fragment=nextFrag;
//
                break;

            case R.id.nav_gallery:  // Index thématique
                Toolbar toolbar3 = (Toolbar) findViewById(R.id.toolbar);
                toolbar3.setTitle("Index thématique");
                lastindex =2;

                fragment = new Menu7();
                break;
//                fragment = new Menu5();
//                break;
//
//            case R.id.nav_manage:  // Index par tonalité
//                fragment = new Menu5();
//                break;
            case R.id.nav_search:   // Recherche par tonalité
                Toolbar toolbar4 = (Toolbar) findViewById(R.id.toolbar);
                toolbar4.setTitle("Recherche par tonalité");
                lastindex =3;

                fragment = new Menu5();
              break;
               //  fragment = new Menu5();
                //fragment = new Menu7();

            case R.id.nav_share:
                fragment = new Menu9();


                break;

            case R.id.nav_send:
//                Uri uri = Uri.parse("smsto:+22891251091");
//                Intent sendIntent = new Intent(Intent.ACTION_SENDTO, uri);
//
//                sendIntent.putExtra(Intent.EXTRA_TEXT, "Bonjour Arnodev, Je vous écris par rapport a l'appli EBBTHymn:");
//                sendIntent.setType("text/plain");
//                sendIntent.setPackage("com.whatsapp");
//                startActivity(Intent.createChooser(sendIntent, ""));
                PackageManager pm=getPackageManager();
                try {


                    String toNumber = "22891251091"; // Replace with mobile phone number without +Sign or leading zeros, but with country code.
                    String text="Bonjour Arnodev, Je vous écris par rapport à l'appli EBBTHymn";
                    //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.
                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    sendIntent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
                    sendIntent.setPackage("com.whatsapp");
                    startActivity(sendIntent);
                  }
                catch (Exception e){
                      e.printStackTrace();
                       Toast.makeText(MainActivity.this,"L'application whatsapp n'est sûrement pas installé",Toast.LENGTH_LONG).show();
                       }



                break;



        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
           ft.replace(R.id.content_frame, fragment);
         //  ft.replace(R.id.main_layout, fragment);
            ft.commit();
       }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);



    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());
        //make this method blank
        return true;
    }

    public MenuItem getHymnMenuItem () {
        return pointeronitem;
    }
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu){
//
//            menu.getItem(1).setEnabled(false);
//
//
//
//        // You can also use something like:
//            // menu.findItem(R.id.example_foobar).setEnabled(false);
//
//        return true;
//
//
//    }


    public void foo(){
        securedConnection.setEnabled(true);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem item = menu.findItem(R.id.action_hymnnum);

        MenuItem item2 = menu.findItem(R.id.action_settings);


        // disabled

                 item.setEnabled(false);
                 item.getIcon().setAlpha(130);
                 item2.setTitle("");
                 pointeronitem = item;


            return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.home){
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        } else if (id == R.id.action_hymnnum) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Numéro de cantique");

            //  File file = getBaseContext().getFileStreamPath(url2);
            // if (file.exists()) { System.out.println("The file exists"); test = true; }
            // else {System.out.println("The file does not exist"); test =true;}

            //     if (test)
            //         builder.setTitle(url2);
            //     else
            //        builder.setTitle("false");

            // Set up the input
            final EditText input = new EditText(this);
            // Specify the type of input expected
            // input.setInputType();
            input.setFilters(new InputFilter[]{new CustomRangeInputFilter(1, 457)});
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            input.setGravity(Gravity.CENTER);
            builder.setView(input);

            // Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    hymnNumber = Integer.valueOf(input.getText().toString());
                    Fragment currentFragment = getSupportFragmentManager()
                            .findFragmentById(R.id.content_frame);
                    ((Menu1) currentFragment).getReader().setDisplayedViewIndex(mappings[hymnNumber-1]-1);

                    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                    toolbar.setTitle("Cant. "+(hymnNumber));

                    ActionMenuItemView it =(ActionMenuItemView) findViewById(R.id.action_settings);




                    it.setText("");


                    //   if (reader !=null)  reader.setDisplayedViewIndex(mappings[hymnNumber-1]-1);

                }
            });

            builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });


            builder.show();
            return true;

        } else if (id ==R.id.action_settings) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            if (test)
                builder.setTitle("true");
            else builder.setTitle("false");

            final EditText input = new EditText(this);
            builder.setView(input);
            builder.show();

            return true;

        } else if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }




        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void copyAssets() {
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }

            InputStream in = null;
            OutputStream out = null;
            String filename = "Cantiques.json";
            try {
                in = assetManager.open(filename);

                String outDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/X/Y/Z/" ;

                File outFile = new File(outDir, filename);

                out = new FileOutputStream(outFile);
                copyFile(in, out);
                in.close();
                in = null;
                out.flush();
                out.close();
                out = null;
            } catch(IOException e) {
                Log.e("tag", "Failed to copy asset file: " + filename, e);
            }

    }
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
//        ActionMenuItemView  it =(ActionMenuItemView)findViewById(R.id.action_settings);
//
//        if (it == null)      Log.i("Activity", "It is null");
//
//
//        it.setText("");
//
//        ActionMenuItemView ip =(ActionMenuItemView) findViewById(R.id.action_hymnnum);
//        ip.setEnabled(false);
        securedConnection = menu.findItem(R.id.action_hymnnum);
        insecuredConnection =  menu.getItem(1);
        return true;
    }

    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission)
                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[] { permission },
                    requestCode);
        }
        else {

        }
    }

        @Override
        public void onRequestPermissionsResult(int requestCode,
         String[] permissions,
         int[] grantResults)
        {
            super
                    .onRequestPermissionsResult(requestCode,
                            permissions,
                            grantResults);

            if (requestCode == STORAGE_PERMISSION_CODE) {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this,
                            "Autorisation d'accès au stockage accordée",
                            Toast.LENGTH_SHORT)
                            .show();
                    granted = true;

                }
                else {
                    Toast.makeText(MainActivity.this,
                            "Autorisation d'accès au stockage refusée",
                            Toast.LENGTH_SHORT)
                            .show();
                    granted = false;
                }
            }
        }
    }


    //  public class App extends MultiDexApplication {
    //you can leave this empty or override methods if you like so the thing is that you need to extend from MultiDexApplication
    //  }
