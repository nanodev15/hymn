package com.example.elommarcarnold.cantik0;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Menu5 extends Fragment implements AdapterView.OnItemClickListener {
    public static String [] prgmNameList={"C","Db","D", "Eb", "E", "F","Gb", "G", "Ab", "A", "Bb", "B"};
    int[] gridViewImageId = {R.drawable.ic_treble_clef,R.drawable.ic_treble_clef,R.drawable.ic_treble_clef,R.drawable.ic_treble_clef,R.drawable.ic_treble_clef,R.drawable.ic_treble_clef,R.drawable.ic_treble_clef,R.drawable.ic_treble_clef,R.drawable.ic_treble_clef,R.drawable.ic_treble_clef,R.drawable.ic_treble_clef,R.drawable.ic_treble_clef};
    private ArrayAdapter adColors;
    private GridView gvColors;
    private Context cnt;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        View rootView = inflater.inflate(R.layout.fragment_menu_5, container, false);
        ArrayList alColors = new ArrayList<>();
        alColors.addAll(Arrays.asList(prgmNameList));
       // CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(getActivity(), prgmNameList, gridViewImageId);


    //  //  ArrayAdapter<String> arrayAdapter
    ////            = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, prgmNameList);
        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(getActivity(), prgmNameList, gridViewImageId);
        gvColors=(GridView)rootView.findViewById(R.id.choice_grid);
   ////     gvColors.setAdapter(arrayAdapter);
        gvColors.setAdapter(adapterViewAndroid);
        gvColors.setOnItemClickListener(this);


        // adColors = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, alColors);

 ////     gvColors.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            // init ListView
//              @Override         gvColors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
 ////             public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                //    Object o = gvColors.getItemAtPosition(position);
 //                 Website website = (Website) o;             public void onItemClick(AdapterView parent, View v, int pos, long id) {
 ////                   Toast.makeText(cnt.getApplicationContext(), "Selected :" + " " + position +")", Toast.LENGTH_LONG).show();
//              }                // String item = (String) gvColors.getItemAtPosition(pos);
//                           System.out.println("item :" + pos);
 ////     }});


//
///*                   String selectedItem = parent.getItemAtPosition(pos).toString();
//
//                   // Display the selected/clicked item text and position on TextView
//                   System.out.println("GridView item clicked : " +selectedItem
//                           + "\nAt index position : " + pos);*/
//                  // Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
//                   Fragment tonal = tonalite.newInstance("Ab");
//                   FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                   transaction.replace(R.id.listView01, tonal); // give your fragment container id in first parameter
//                   transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                   transaction.commit();





 //              }
 //          });

        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        //   getActivity().setTitle("Menu 1");

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
       // toolbar.setTitle("hgvhgvhgvgh");
        cnt = this.getContext();

        ActionMenuItemView it =(ActionMenuItemView) getActivity().findViewById(R.id.action_settings);

        if (it == null)      Log.i("Activity", "It is null");


        it.setText("");

        ActionMenuItemView ip =(ActionMenuItemView) getActivity().findViewById(R.id.action_hymnnum);
        ip.setEnabled(false);





    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {




        Toast.makeText(cnt.getApplicationContext(), "gamme :" + " " + prgmNameList[position] +"", Toast.LENGTH_LONG).show();
 //                 Fragment tonal = tonalite.newInstance("Ab");
//                 FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                   transaction.replace(R.id.listView01, tonal); // give your fragment container id in first parameter
//                   transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                   transaction.commit();


//        Intent intent=new Intent(getActivity(), IntentListActivity.class);
//        intent.putExtra("List_item",position);
//        startActivity(intent);
        Bundle bundle = new Bundle();





        bundle.putInt("position", position);
        Menu8 nextFrag= new Menu8();

        nextFrag.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, nextFrag, "findThisFragment")
                .addToBackStack(null)
                .commit();

    }





}
