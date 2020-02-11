package com.example.elommarcarnold.cantik0;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;
import java.util.List;

public class GmailAdapterNum extends ArrayAdapter<String> implements Filterable {

    private FragmentActivity activity;
    private List<String> friendList;
    private List<String> friendList2;
    Filter myFilter;

    public GmailAdapterNum(FragmentActivity context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.friendList = objects;
        this.friendList2=friendList;
         myFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                ArrayList<String> tempList=new ArrayList<String>();
                //constraint is the result from text you want to filter against.
                //objects is your data set you will filter from
                if (TextUtils.isDigitsOnly(constraint)) {
                    int length=friendList2.size();
                    int i=0;
                    while(i<length) {
                        String item=friendList2.get(i);

                        //do whatever you wanna do here
                        //adding result set output array
                        if((String.valueOf(i+1).startsWith(constraint.toString()))) { //lower case
                            tempList.add(item);
                        }
                        i++;
                    }
                    //following two lines is very important
                    //as publish result can only take FilterResults objects
                    filterResults.values = tempList;
                    filterResults.count = tempList.size();

                }
                 else if(constraint != null && friendList2!=null) {
                    int length=friendList2.size();
                    int i=0;
                    while(i<length){
                        String item=friendList2.get(i);

                        //do whatever you wanna do here
                        //adding result set output array
                        if(item.toLowerCase().contains(constraint.toString().toLowerCase()))  //lower case
                        tempList.add(item);

                        i++;
                    }
                    //following two lines is very important
                    //as publish result can only take FilterResults objects
                    filterResults.values = tempList;
                    filterResults.count = tempList.size();
                }

                return filterResults;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence contraint, FilterResults results) {
                friendList = (ArrayList<String>) results.values;
                if (results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
    }

    @Override
    public int getCount() {
        return friendList.size();
    }





    @Override
    public String getItem(int position) {
        return friendList.get(position);
    }

    @Override
    public long getItemId(int position) {
       // return position;
        return friendList2.indexOf((String) friendList.get(position));

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.item_listview, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        holder.friendName.setText(getItem(position));

        //get first letter of each String item
        //  String firstLetter = String.valueOf(position+1);
        String firstLetter = String.valueOf(getItemId(position)+1);
        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        // generate random color
        int color = generator.getColor(getItem(position));
        //int color = generator.getRandomColor();

        TextDrawable drawable = TextDrawable.builder().beginConfig()
                .width(40)  // width in px
                .height(40) // height in px
                .fontSize(20).endConfig()
                .buildRound(firstLetter, color); // radius in px

        holder.imageView.setImageDrawable(drawable);

        return convertView;
    }

    @Override
    public Filter getFilter() {
        // return a filter that filters data based on a constraint


        return myFilter;
    }


    private class ViewHolder {
        private ImageView imageView;
        private TextView friendName;

        public ViewHolder(View v) {
            imageView = (ImageView) v.findViewById(R.id.image_view);
            friendName = (TextView) v.findViewById(R.id.text);
        }
    }
}