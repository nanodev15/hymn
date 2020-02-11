package com.example.elommarcarnold.cantik0;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.artifex.mupdfdemo.MuPDFReaderView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Menu9 extends Fragment implements View.OnClickListener {
    private ListView lv;
    JSONObject jsonObject;
    String[] elements;
    String[]  thekeys={"C","Db","D", "Eb", "E", "F","Gb", "G", "Ab", "A", "Bb", "B"};
    ArrayList<String> keyhymns;

    LinearLayout lv2;
    SearchView sv;
    // ArrayAdapter<String> adapter;
    GmailAdapter adapter;
    private MenuItem mSearchItem;
    String[] cantnamlist={};
    String cam1="";
    String key;
    Map<String, Integer> mapIndex;
    int[] mapping = new int[457];
    ArrayList<String> hymns;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.credits, container, false);

        ListView myListView = rootView.findViewById(R.id.listViewId);

        List<String> dynamicText2Elements;
        dynamicText2Elements = new ArrayList<String>();
        dynamicText2Elements.add("Version beta");
        dynamicText2Elements.add("© ArnoDev 2018");
        dynamicText2Elements.add("Whatsapp +228 91 25 10 91");
        dynamicText2Elements.add("");
     //   dynamicText2Elements.add("element5 of text2");

        List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> element;

        int length = getResources().getStringArray(R.array.staticText1Elements).length;

        for (int i = 0; i < length; i++) {
            element = new HashMap<String, String>();
            element.put("text1",  getResources().getStringArray(R.array.staticText1Elements)[i]);
            element.put("text2", dynamicText2Elements.get(i));
            dataList.add(element);
        }
        ListAdapter myAdapter = new SimpleAdapter(getContext(), dataList, android.R.layout.simple_expandable_list_item_2, new String[]{"text1", "text2"}, new int[]{android.R.id.text1, android.R.id.text2});
        myListView.setAdapter(myAdapter);

        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Menu 2");
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Credits");

    }


    public Fragment getActiveFragment() {
        if (getActivity().getSupportFragmentManager().getBackStackEntryCount() == 0) {
            return null;
        }
        String tag = getActivity().getSupportFragmentManager().getBackStackEntryAt(getActivity().getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
        return (Fragment) getActivity().getSupportFragmentManager().findFragmentByTag(tag);
    }

    private void getIndexList(String[] cantlist) {
        mapIndex = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < cantlist.length; i++) {
            String cant= cantlist[i];
            String index = cant.substring(0, 1);

            if (mapIndex.get(index) == null)
                mapIndex.put(index, i);
        }
    }
    @Override
    public void onClick(View view) {
        TextView selectedIndex = (TextView) view;
        lv.setSelection(mapIndex.get(selectedIndex.getText()));
    }

    private void displayIndex() {
        //   LinearLayout indexLayout = (LinearLayout) getView().findViewById(R.id.side_index);

        TextView textView;
        List<String> indexList = new ArrayList<String>(mapIndex.keySet());
        for (String index : indexList) {
            textView = (TextView) getLayoutInflater().inflate(
                    R.layout.side_index_item, null);
            textView.setText(index);
            //      textView.setOnClickListener(rootview.getActivity());
            textView.setOnClickListener(this);

            lv2.addView(textView);
        }
    }

    private void buildprevioustablemap() {
        ArrayList<String> prevhymns = (ArrayList<String>) hymns.clone();
        Collections.sort(hymns);
        Iterator i = hymns.iterator();
        int positions=0;
        int j =0;
        while (i.hasNext()) {
            positions = prevhymns.indexOf((String)i.next());
            mapping[j] = positions;
            j++;
        }




    }


    private void getListofSongs(int position){
        // parsing json
        String tomatch=thekeys[position];
        String json = null;
        keyhymns=new ArrayList<String>();
        try {
            InputStream inputStream;
            inputStream = getActivity().getAssets().open("Cantiques.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
            jsonObject = new JSONObject(json);
            int i= jsonObject.length();

            for (int j=0; j<jsonObject.length(); j++) {

                JSONObject obj = jsonObject.getJSONObject(String.valueOf(j+1));
                String key= obj.getString("key");
                if (key.equalsIgnoreCase(tomatch))
                    keyhymns.add(obj.getString("name"));
            }

            elements  = new String[keyhymns.size()];
            elements = keyhymns.toArray(elements);



        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {

            e.printStackTrace();
        }
    }


}



