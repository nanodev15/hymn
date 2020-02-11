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
import android.widget.ListView;
import android.widget.SearchView;
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

import com.artifex.mupdfdemo.FilePicker;
import com.artifex.mupdfdemo.MuPDFCore;
import com.artifex.mupdfdemo.MuPDFPageAdapter;
import com.artifex.mupdfdemo.MuPDFReaderView;

public class Menu8 extends Fragment implements View.OnClickListener {
    private ListView lv;
    JSONObject jsonObject;
    String[] elements;
    String[]  thekeys={"C","Db","D", "Eb", "E", "F","Gb", "G", "Ab", "A", "Bb", "B"};
    ArrayList<String> keyhymns;
    ArrayList <Integer> mappings;

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

    int hymnmaps [];
    private int position;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View rootView=inflater.inflate(R.layout.fragment_onaliterech, container, false);


         position = this.getArguments().getInt("position");


        lv=(ListView) rootView.findViewById(R.id.list_cantik2);
      //  lv2= (LinearLayout) rootView.findViewById(R.id.side_index);


        getListofSongs(position);





        Log.d("tag", Arrays.toString(mapping));



        //adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,cantnamlist);
        //  adapter= new GmailAdapter(getActivity(), R.layout.item_listview, hymns);
        //  lv.setAdapter(adapter);

        lv.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, elements));

//
//        getIndexList(cantnamlist);
//        displayIndex();




        // item click
        lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // selected item
                //  String selected = ((TextView) view.findViewById(R.id.your_textView_item_id)).getText().toString();

                //Toast toast = Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT);
                //toast.show();
                Bundle bundle = new Bundle();
                int trueposition = hymnmaps[position];


                bundle.putInt("position", trueposition);

                try {
                    JSONObject obj2 = jsonObject.getJSONObject(String.valueOf(trueposition+1));
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

            }
        });
        // lv.setTextFilterEnabled(true);



        //SEARCH
//        sv.setQueryHint("Recherche..");
        //       sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

        //          @Override
        //           public boolean onQueryTextSubmit(String txt) {

        //               return false;
        //               // TODO Auto-generated method stub

        //           }

        //           @Override
        //           public boolean onQueryTextChange(String txt) {
        //              // TODO Auto-generated method stub

        //  adapter.getFilter().filter(txt);
        //  return true;


        // if (txt == null || TextUtils.isEmpty(txt)) {
        //      lv.clearTextFilter();
        //  } else {
        //               if(adapter ==null) System.out.println("adapter is null");
        //               if(adapter.getFilter() ==null) System.out.println("adapter filter is null");
        //               adapter.getFilter().filter(txt);
        //  }
        //               return true;
        //           }
        //      });

        //BUTTON



        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Menu 2");
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Chants en "+thekeys[position]);

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

    public static int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }


    private void getListofSongs(int position){
        // parsing json
        String tomatch=thekeys[position];
        String json = null;
        keyhymns=new ArrayList<String>();
        mappings=new ArrayList<Integer>();
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
                if (key.equalsIgnoreCase(tomatch)) {
                    keyhymns.add(obj.getString("name"));
                    mappings.add(j);
                }
            }

            elements  = new String[keyhymns.size()];
            elements = keyhymns.toArray(elements);



        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {

            e.printStackTrace();
        }

        hymnmaps = convertIntegers(mappings);

    }


}



