package com.example.elommarcarnold.cantik0;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
//import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.AdapterView.OnItemClickListener;
import com.artifex.mupdfdemo.MuPDFReaderView;
import android.view.View.OnClickListener;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

// index thématique
public class Menu7 extends Fragment  {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ArrayList<String> hymns;
    JSONObject jsonObject;
    ListView lv;
    String key;
    String cam1;
    int[] maps = new int[17];  // header map
    //int[] mapping = new int[457];




    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        View rootView = inflater.inflate(R.layout.fragment_menu_7, container, false);
        super.onCreate(savedInstanceState);

        // get the listview
        expListView = (ExpandableListView) rootView.findViewById(R.id.lvExp);




        // parsing json
        String json = null;
        hymns=new ArrayList<String>();
        try {
            InputStream inputStream = getActivity().getAssets().open("Cantiques.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
            jsonObject = new JSONObject(json);
            int i= jsonObject.length();

            for (int j=0; j<jsonObject.length(); j++) {

                JSONObject obj = jsonObject.getJSONObject(String.valueOf(j+1));
                String cant= obj.getString("name");
                hymns.add(cant);
            }
            //  Collections.sort(hymns);


            // preparing list data
            prepareListData();

//            expListView.setOnChildClickListener(new OnItemClickListener() {
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    // selected item
                    //  String selected = ((TextView) view.findViewById(R.id.your_textView_item_id)).getText().toString();

                    //Toast toast = Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT);
                    //toast.show();
//                    Bundle bundle = new Bundle();
                  //  int trueposition = mapping[position];


//                    bundle.putInt("position", position);

//                    try {
//                        JSONObject obj2 = jsonObject.getJSONObject(String.valueOf(position+1));
//                        key= obj2.getString("key");
                        //   bundle.putString("key", key)
//                        bundle.putString("key", key);

//                    } catch (JSONException e) {
//                        cam1="segfault";
//                        e.printStackTrace();
//                    }


//                    Menu1 nextFrag= new Menu1();
//                    nextFrag.setArguments(bundle);
//                    getActivity().getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.content_frame, nextFrag, "findThisFragment")
//                            .addToBackStack(null)
//                            .commit();

//                    if((MuPDFReaderView) nextFrag.getReader() == null) System.out.println("It is null");
//                    else System.out.println("it is not null");

                    //              ((MuPDFReaderView) nextFrag.getReader()).setDisplayedViewIndex(position);
//

//                Menu1 myFragment = (Menu1)getFragmentManager().findFragmentByTag("findThisFragment");
//                ((MuPDFReaderView) nextFrag.getReader()).setDisplayedViewIndex(position);

//                }
//            });


            expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int group, int child, long childRowId) {
                    // selected item
                    //  String selected = ((TextView) view.findViewById(R.id.your_textView_item_id)).getText().toString();

                    //Toast toast = Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT);
                    //toast.show();
                    Bundle bundle = new Bundle();
                    int trueposition;

                     if(group==0) trueposition =child;
                     else trueposition = maps[group-1]+child;


                    bundle.putInt("position", trueposition);

                    try {
                        JSONObject obj2 = jsonObject.getJSONObject(String.valueOf(child+1));
                        key= obj2.getString("key");
                        //   bundle.putString("key", key)
                        bundle.putString("key", key);

                    } catch (JSONException e) {
                        cam1="segfault";
                        e.printStackTrace();
                    }


                    Menu1 nextFrag= new Menu1();
                    nextFrag.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, nextFrag, "findThisFragment")
                            .addToBackStack(null)
                            .commit();

                    if((MuPDFReaderView) nextFrag.getReader() == null) System.out.println("It is null");
                    else System.out.println("it is not null");

                    //              ((MuPDFReaderView) nextFrag.getReader()).setDisplayedViewIndex(position);
//

//                Menu1 myFragment = (Menu1)getFragmentManager().findFragmentByTag("findThisFragment");
//                ((MuPDFReaderView) nextFrag.getReader()).setDisplayedViewIndex(position);
                    return false;
                }


                });






        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {

            e.printStackTrace();
        }





        return rootView;
    }




    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        //   getActivity().setTitle("Menu 1");

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("");
        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        ActionMenuItemView it =(ActionMenuItemView) getActivity().findViewById(R.id.action_settings);

        if (it == null)      Log.i("Activity", "It is null");


        it.setText("");

        ActionMenuItemView ip =(ActionMenuItemView) getActivity().findViewById(R.id.action_hymnnum);
        ip.setEnabled(false);





    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data

        listDataHeader.add("Adoration et Louange à  Dieu");
        listDataHeader.add("Jésus Christ: Sa Naissance");
        listDataHeader.add("Jésus Christ: Ses Souffrances");
        listDataHeader.add("Jésus Christ: Sa Résurrection");
        listDataHeader.add("Jésus Christ: Son retour");
        listDataHeader.add("L’Evangile");
        listDataHeader.add("L’Evangile: Réponse à l’Appel");
        listDataHeader.add("L’Eglise");
        listDataHeader.add("La Vie Chrétienne: Aspiration de l’Ame");
        listDataHeader.add("La Vie Chrétienne: Combats et Victoire");
        listDataHeader.add("La Vie Chrétienne: Confiance en Dieu");
        listDataHeader.add("La Vie Chrétienne: Prière");
        listDataHeader.add("La Vie Chrétienne: Témoignage");
        listDataHeader.add("La Vie Chrétienne: Service");
        listDataHeader.add("La Vie Éternelle");
        listDataHeader.add("Les Écritures");
        listDataHeader.add("Chants");

        // Adding child data
        int i =0;
        List<String> top1 = new ArrayList<String>();
        for (i=0;i<54; i++) top1.add(hymns.get(i));
        List<String> top2 = new ArrayList<String>();
        for (i=54;i<82; i++) top2.add(hymns.get(i));
        List<String> top3 = new ArrayList<String>();
        for (i=82;i<101; i++) top3.add(hymns.get(i));
        List<String> top4 = new ArrayList<String>();
        for (i=101;i<109; i++) top4.add(hymns.get(i));
        List<String> top5 = new ArrayList<String>();
        for (i=109;i<115; i++) top5.add(hymns.get(i));
        List<String> top6 = new ArrayList<String>();
        for (i=115;i<172; i++) top6.add(hymns.get(i));
        List<String> top7 = new ArrayList<String>();
        for (i=172;i<191; i++) top7.add(hymns.get(i));
        List<String> top8 = new ArrayList<String>();
        for (i=191;i<193; i++) top8.add(hymns.get(i));
        List<String> top9 = new ArrayList<String>();
        for (i=193;i<206; i++) top9.add(hymns.get(i));
        List<String> top10 = new ArrayList<String>();
        for (i=206;i<213; i++) top10.add(hymns.get(i));
        List<String> top11 = new ArrayList<String>();
        for (i=213;i<258; i++) top11.add(hymns.get(i));
        List<String> top12 = new ArrayList<String>();
        for (i=258;i<273; i++) top12.add(hymns.get(i));
        List<String> top13 = new ArrayList<String>();
        for (i=274;i<321; i++) top13.add(hymns.get(i));
        List<String> top14 = new ArrayList<String>();
        for (i=321;i<332; i++) top14.add(hymns.get(i));
        List<String> top15 = new ArrayList<String>();
        for (i=332;i<354; i++) top15.add(hymns.get(i));
        List<String> top16= new ArrayList<String>();
        top16.add(hymns.get(354));
        List<String> top17 = new ArrayList<String>();
        for (i=355;i<457; i++) top17.add(hymns.get(i));

        maps[0] = 54;
        maps[1] = 82;  //28
        maps[2] = 101;   //19
        maps[3] = 109;    //8
        maps[4] = 115;       // 6
        maps[5] = 172;      //57
        maps[6] = 191;        //19
        maps[7] = 193;            //2
        maps[8] = 206;          // 13
        maps[9] = 213;               //7
        maps[10] = 258;        // 45
        maps[11] = 273;           //15
        maps[12] = 321;              //47
        maps[13] = 332;               //11
        maps[14] = 354;           //22
        maps[15] = 355;               //1
        maps[16] = 457;               //102





        listDataChild.put(listDataHeader.get(0), top1); // Header, Child data
        listDataChild.put(listDataHeader.get(1), top2); // Header, Child data
        listDataChild.put(listDataHeader.get(2), top3); // Header, Child data
        listDataChild.put(listDataHeader.get(3), top4); // Header, Child data
        listDataChild.put(listDataHeader.get(4), top5); // Header, Child data
        listDataChild.put(listDataHeader.get(5), top6); // Header, Child data
        listDataChild.put(listDataHeader.get(6), top7); // Header, Child data
        listDataChild.put(listDataHeader.get(7), top8); // Header, Child data
        listDataChild.put(listDataHeader.get(8), top9); // Header, Child data
        listDataChild.put(listDataHeader.get(9), top10); // Header, Child data
        listDataChild.put(listDataHeader.get(10), top11); // Header, Child data
        listDataChild.put(listDataHeader.get(11), top12); // Header, Child data
        listDataChild.put(listDataHeader.get(12), top13); // Header, Child data
        listDataChild.put(listDataHeader.get(13), top14); // Header, Child data
        listDataChild.put(listDataHeader.get(14), top15); // Header, Child data
        listDataChild.put(listDataHeader.get(15), top16); // Header, Child data
        listDataChild.put(listDataHeader.get(16), top17); // Header, Child data
    }





}
