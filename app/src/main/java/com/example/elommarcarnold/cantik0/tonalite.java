package com.example.elommarcarnold.cantik0;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;

import com.artifex.mupdfdemo.MuPDFReaderView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

//import android.support.v7.widget.SearchView;


/**
 * Created by Belal on 18/09/16.
 */


public class tonalite extends Fragment {

    ListView lv;
 //   SearchView sv;
   // ArrayAdapter<String> adapter;
    GmailAdapter adapter;
    private MenuItem mSearchItem;
    String[] cantnamlist={};
    String cam1="";
    private String key="Ab";

    public static Fragment newInstance(String key) {
        Bundle bundle = new Bundle();
        bundle.putString("key", key);

        tonalite fragment = new tonalite();
        fragment.setArguments(bundle);

        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            key = bundle.getString("key");
        }
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View rootView=inflater.inflate(R.layout.fragment_menu_01, container, false);
        lv=(ListView) rootView.findViewById(R.id.listView1);
       // sv=(SearchView) rootView.findViewById(R.id.searchView1);

        // parsing json
        String json = null;
        ArrayList<String> hymns=new ArrayList<String>();
        try {
            InputStream inputStream = getActivity().getAssets().open("Cantiques.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(json);
           // JSONObject myResponse = jsonObject.getJSONObject("elements");



            int i= jsonObject.length();

            for (int j=0; j<jsonObject.length(); j++) {
                String cant="";
                JSONObject obj = jsonObject.getJSONObject(String.valueOf(j+1));
                String keyg= obj.getString("key");
  //            if (key == null) System.out.println("key is null");
  //              if (keyg == null) System.out.println("keyg is null");
                if(key.equalsIgnoreCase(keyg)) {
                    cant= obj.getString("name");
                }

     //           if(key.equalsIgnoreCase(keyg)) {
     //               cant= obj.getString("name");
     //           }

                hymns.add(cant);
            }

            cantnamlist = hymns.toArray(new String[hymns.size()]);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            cam1="segfault";
            e.printStackTrace();
        }


        //adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,cantnamlist);
        adapter= new GmailAdapter(getActivity(), R.layout.item_listview, hymns);
        lv.setAdapter(adapter);

        // item click
        lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // selected item
                //  String selected = ((TextView) view.findViewById(R.id.your_textView_item_id)).getText().toString();

                //Toast toast = Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT);
                //toast.show();
                Bundle bundle = new Bundle();

                bundle.putInt("position", position);

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



      /*  //SEARCH
        sv.setQueryHint("Recherche..");
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String txt) {

                return false;
                // TODO Auto-generated method stub

            }

            @Override
            public boolean onQueryTextChange(String txt) {
                // TODO Auto-generated method stub

              //  adapter.getFilter().filter(txt);
              //  return true;


               // if (txt == null || TextUtils.isEmpty(txt)) {
              //      lv.clearTextFilter();
              //  } else {
                    if(adapter ==null) System.out.println("adapter is null");
                    if(adapter.getFilter() ==null) System.out.println("adapter filter is null");
                    adapter.getFilter().filter(txt);
              //  }
                return true;
            }
        });*/

        //BUTTON



        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Menu 2");
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("cat"+cam1);

    }


    public Fragment getActiveFragment() {
        if (getActivity().getSupportFragmentManager().getBackStackEntryCount() == 0) {
            return null;
        }
        String tag = getActivity().getSupportFragmentManager().getBackStackEntryAt(getActivity().getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
        return (Fragment) getActivity().getSupportFragmentManager().findFragmentByTag(tag);
    }
}

