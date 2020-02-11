package com.example.elommarcarnold.cantik0;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
//import android.support.v7.widget.SearchView;
import android.support.v7.view.menu.ActionMenuItemView;
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


/**
 * Created by Belal on 18/09/16.
 */


public class Menu3 extends Fragment implements OnClickListener {

    ListView lv;
    LinearLayout lv2;
    SearchView sv;
    // ArrayAdapter<String> adapter;
    GmailAdapter adapter;
    private MenuItem mSearchItem;
    String[] cantnamlist={};
    String cam1="";
    String key;
    JSONObject jsonObject;
    Map<String, Integer> mapIndex;
    int[] mapping = new int[457];
    ArrayList<String> hymns;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View rootView=inflater.inflate(R.layout.fragment_alphabetic, container, false);

        lv=(ListView) rootView.findViewById(R.id.list_cantik);
        lv2= (LinearLayout) rootView.findViewById(R.id.side_index);

        //  sv=(SearchView) rootView.findViewById(R.id.searchView1);

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
            buildprevioustablemap();
            cantnamlist = hymns.toArray(new String[hymns.size()]);
            Log.d("tag", Arrays.toString(mapping));







        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            cam1="segfault";
            e.printStackTrace();
        }


        //adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,cantnamlist);
      //  adapter= new GmailAdapter(getActivity(), R.layout.item_listview, hymns);
      //  lv.setAdapter(adapter);

        lv.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, cantnamlist));


        getIndexList(cantnamlist);
        displayIndex();




        // item click
        lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // selected item
                //  String selected = ((TextView) view.findViewById(R.id.your_textView_item_id)).getText().toString();

                //Toast toast = Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT);
                //toast.show();
                Bundle bundle = new Bundle();
                int trueposition = mapping[position];


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
      //  toolbar.setTitle("cat"+cam1);
        ActionMenuItemView it =(ActionMenuItemView) getActivity().findViewById(R.id.action_settings);

        it.setText("");

        ActionMenuItemView ip =(ActionMenuItemView) getActivity().findViewById(R.id.action_hymnnum);
        ip.setEnabled(false);


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


    }


