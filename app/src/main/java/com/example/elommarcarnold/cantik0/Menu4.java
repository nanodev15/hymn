package com.example.elommarcarnold.cantik0;


import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.artifex.mupdfdemo.FilePicker;
import com.artifex.mupdfdemo.MuPDFCore;
import com.artifex.mupdfdemo.MuPDFPageAdapter;
import com.artifex.mupdfdemo.MuPDFReaderView;

import java.io.File;

/**
 * Created by Belal on 18/09/16.
 */


public class Menu4 extends Fragment {
    MuPDFReaderView mDocView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        View rootView = inflater.inflate(R.layout.fragment_menu_1, container, false);
        RelativeLayout layout = (RelativeLayout) rootView.findViewById(R.id.men4);

        try {
        String url = Environment.getExternalStorageDirectory().getPath();
        url=url+"/Download/Cantiques.pdf";
        File file =new File("/storage/emulated/0/Download/Cantiques.pdf");

        // MuPDFCore core = new MuPDFCore(this, url);
        RelativeLayout mupdfWrapper= (RelativeLayout) rootView.findViewById(R.id.men4);
     //   String path = "path/To/Your/PDF/File.pdf";
        MuPDFCore core = new MuPDFCore(getActivity(), file.getAbsolutePath());
        mDocView = new MuPDFReaderView(getActivity());
        mDocView.setAdapter( new MuPDFPageAdapter(getActivity(), new FilePicker.FilePickerSupport() {
            @Override
            public void performPickFor(FilePicker filePicker) {

            }
        }, core));

     //   mDocView.setAdapter(new MuPDFPageAdapter(getActivity(), getActivity(), core));
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
        toolbar.setTitle("hgvhgvhgvgh");




    }

    public MuPDFReaderView getReader() {
        return mDocView;
    }
}