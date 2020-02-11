package com.example.elommarcarnold.cantik0;


import android.os.Bundle;

import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.ActionMenuItemView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import com.artifex.mupdfdemo.FilePicker;
import com.artifex.mupdfdemo.MuPDFCore;
import com.artifex.mupdfdemo.MuPDFPageAdapter;
import com.artifex.mupdfdemo.MuPDFReaderView;
import android.widget.RelativeLayout.LayoutParams;


import java.io.File;

/**
 * Created by Belal on 18/09/16.
 */


public class Menu1 extends Fragment {
    MuPDFReaderView mDocView;
    int[] mappings = new int[] {1, 2, 3, 3, 4, 4, 5, 6, 6, 7, 8, 9, 10, 10, 11, 11, 12, 13, 13, 14, 14, 15, 15, 16, 16, 17, 18, 19, 19, 20, 21, 22, 22, 23, 24, 25, 25, 26, 26, 27, 28, 29, 29, 30, 30, 31, 32, 32, 33, 33, 34, 34, 35, 36, 37, 37, 38, 38, 39, 40, 40, 41, 41, 42, 42, 43, 44, 44, 45, 46, 46, 47, 47, 48, 48, 49, 50, 50, 51, 52, 52, 53, 54, 55, 56, 56, 57, 58, 58, 59, 59, 60, 60, 61, 62, 62, 63, 64, 64, 65, 66, 66, 67, 67, 68, 68, 69, 70, 71, 72, 72, 73, 74, 74, 75, 76, 77, 77, 78, 78, 79, 79, 80, 80, 81, 82, 83, 83, 84, 84, 85, 86, 86, 87, 88, 88, 89, 90, 90, 91, 92, 92, 93, 94, 95, 95, 96, 96, 97, 98, 98, 99, 100, 100, 101, 102, 102, 103, 104, 105, 105, 106, 106, 107, 108, 109, 110, 110, 111, 112, 112, 113, 114, 114, 115, 116, 116, 117, 118, 118, 119, 120, 121, 121, 122, 122, 123, 124, 124, 125, 126, 127, 127, 128, 128, 129, 129, 130, 130, 131, 132, 132, 133, 134, 134, 135, 136, 136, 137, 138, 138, 139, 140, 141, 141, 142, 142, 143, 144, 144, 145, 146, 146, 147, 148, 148, 149, 150, 150, 151, 152, 152, 153, 153, 154, 154, 155, 156, 156, 157, 158, 159, 159, 160, 160, 161, 162, 162, 163, 163, 164, 164, 165, 166, 166, 167, 168, 168, 169, 170, 170, 171, 171, 172, 172, 173, 174, 174, 175, 176, 176, 177, 177, 178, 178, 179, 179, 180, 180, 181, 182, 183, 184, 184, 185, 186, 186, 187, 188, 189, 190, 190, 191, 191, 192, 192, 193, 194, 194, 195, 196, 196, 197, 198, 198, 199, 200, 200, 201, 202, 202, 203, 204, 204, 205, 206, 206, 207, 208, 208, 209, 210, 210, 211, 211, 212, 212, 213, 213, 214, 214, 215, 216, 216, 217, 218, 218, 219, 220, 220, 221, 222, 222, 223, 224, 224, 225, 226, 226, 227, 228, 228, 229, 229, 230, 231, 231, 231, 232, 232, 232, 233, 233, 233, 234, 234, 234, 235, 235, 235, 235, 236, 236, 236, 237, 237, 237, 237, 238, 238, 238, 238, 239, 239, 239, 240, 240, 240, 241, 241, 241, 242, 242, 242, 243, 243, 243, 243, 243, 244, 244, 244, 245, 245, 245, 246, 246, 246, 247, 247, 247, 248, 248, 248, 248, 248, 249, 249, 249, 249, 250, 250, 250, 251, 251, 251, 252, 252, 252, 253, 253, 253, 253, 254, 254, 254, 254, 255, 255, 255, 256, 256, 256, 257, 257, 257, 257, 258, 258, 258, 258, 259, 259, 259, 260, 260, 261};
    private int pos;
    private String key="";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        View rootView = inflater.inflate(R.layout.fragment_menu_1, container, false);
        RelativeLayout layout = (RelativeLayout) rootView.findViewById(R.id.men1);

        try {
         /*   String url = Environment.getExternalStorageDirectory().getPath();
            url=url+"/Download/Cantiques.pdf";*/

            String url =Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/com.your.app.media/Cantiques.pdf";

        //    File file =new File("/storage/emulated/0/Download/Cantiques.pdf");
            File file = new File(url);

            // MuPDFCore core = new MuPDFCore(this, url);
            RelativeLayout mupdfWrapper= (RelativeLayout) rootView.findViewById(R.id.men1);
            //   String path = "path/To/Your/PDF/File.pdf";
            MuPDFCore core = new MuPDFCore(getActivity(), file.getAbsolutePath());
            mDocView = new MuPDFReaderView(getActivity());
            mDocView.setAdapter( new MuPDFPageAdapter(getActivity(), new FilePicker.FilePickerSupport() {
                @Override
                public void performPickFor(FilePicker filePicker) {

                }
            }, core));

            // Just some parameters
            //    mDocView.setKeepScreenOn(true);
            //    mDocView.setLinksHighlighted(false);
            //   mDocView.setScrollingDirectionHorizontal(true);


            //   mDocView.setAdapter(new MuPDFPageAdapter(getActivity(), getActivity(), core));
            int position = 0;
            position = this.getArguments().getInt("position");
            key = this.getArguments().getString("key");
            pos=position;
            if(position !=0)   mDocView.setDisplayedViewIndex(mappings[position]-1);

            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            mupdfWrapper.addView(mDocView, params);

        } catch (Exception e) {

        }


        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        //   getActivity().setTitle("Menu 1");



        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Cant. "+(pos+1));
        if (getActivity() == null)      Log.i("Activity", "getActivity is null");
        ActionMenuItemView  it =(ActionMenuItemView) getActivity().findViewById(R.id.action_settings);

        if (it == null)      Log.i("Activity", "It is null");
        MainActivity main = (MainActivity) getActivity();
        main.getHymnMenuItem().setEnabled(true);

        it.setText(equivalentString(key));

        ActionMenuItemView ip =(ActionMenuItemView) getActivity().findViewById(R.id.action_hymnnum);
        ip.setEnabled(true);







    }

    public MuPDFReaderView getReader() {
        return mDocView;
    }

    public String equivalentString(String key){
        if (key.equalsIgnoreCase("A")) return "La";
        else if (key.equalsIgnoreCase("Ab")) return "La"+"\u266D";
        else if (key.equalsIgnoreCase("B")) return "Si";
        else if (key.equalsIgnoreCase("Bb")) return "Si"+"\u266D";
        else if (key.equalsIgnoreCase("C")) return "Do";
        else if (key.equalsIgnoreCase("D")) return "Ré";
        else if (key.equalsIgnoreCase("Db")) return "Ré"+"\u266D";
        else if (key.equalsIgnoreCase("E")) return "Mi";
        else if (key.equalsIgnoreCase("Eb")) return "Mi"+"\u266D";
        else if (key.equalsIgnoreCase("F")) return "Fa";
        else if (key.equalsIgnoreCase("G")) return "Sol";
        else if (key.equalsIgnoreCase("Gb")) return "Sol"+"\u266D";
        else return "";
    }
}