package com.cattechnologies.tpg.adapters.eroDepositsReportAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cattechnologies.tpg.model.RecyclerData;
import com.cattechnologies.tpg.interfaces.RemoveClickListner;
import com.cattechnologies.tpg.R;

import java.util.ArrayList;

/**
 * Created by Ajay on 11/24/2017.
 */

public class SbiEroListDataAdapter extends RecyclerView.Adapter<SbiEroListDataAdapter.RecyclerItemViewHolder> {
    private ArrayList<RecyclerData> myList;
    int mLastPosition = 0;
    private RemoveClickListner mListner;
    SbiEroListDataAdapter adapter;
    private Context mContext;

    public SbiEroListDataAdapter(ArrayList<RecyclerData> myList) {
        this.myList = myList;
    }

    public RecyclerItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ero_list_item_remove, parent, false);
        mContext = parent.getContext();
        RecyclerItemViewHolder holder = new RecyclerItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerItemViewHolder holder, int position) {
        Log.d("onBindViewHoler ", myList.size() + "");
        holder.etTitleTextView.setText(myList.get(position).getTitle());
        //  holder.etDescriptionTextView.setText(myList.get(position).getDescription());
        holder.crossImage.setBackgroundResource(R.drawable.remove_item);
//        mLastPosition = position;
    }

    @Override
    public int getItemCount() {
        if (myList != null) {
            return myList.size();
        } else {
            return 0;
        }
        // return (null != myList ? myList.size() : 0);
    }

    public void notifyData(ArrayList<RecyclerData> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.myList = myList;
        notifyDataSetChanged();
    }

    public void addItem(RecyclerData mLog) {
        myList.add(mLog);
        notifyItemInserted(0);
    }

    public SbiEroListDataAdapter getAdapter() {
        return adapter;
    }


    public void newAddedData(int mLastPosition, String title) {
        RecyclerData newValue = new RecyclerData();
        newValue.setTitle(title);
        if (!myList.contains(newValue)) {
            myList.add(myList.size(), newValue);
          //  notifyItemInserted(myList.size() - 1);
            notifyDataSetChanged();
        }
    }

    public class RecyclerItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView etTitleTextView;
        //    private final TextView etDescriptionTextView;
        private RelativeLayout mainLayout;
        public ImageButton crossImage;

        public RecyclerItemViewHolder(final View parent) {
            super(parent);
            etTitleTextView = (TextView) parent.findViewById(R.id.txtTitle);
            // etDescriptionTextView = (TextView) parent.findViewById(R.id.txtDescription);
            crossImage = (ImageButton) parent.findViewById(R.id.crossImage);
            mainLayout = (RelativeLayout) parent.findViewById(R.id.mainLayout);
            mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Position:" + Integer.toString(getPosition()), Toast.LENGTH_SHORT).show();
                }
            });
            crossImage.setOnClickListener(new AdapterView.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //mListner.OnRemoveClick(getAdapterPosition());
                    myList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), myList.size());
                    Toast.makeText(itemView.getContext(), "deleted", Toast.LENGTH_SHORT).show();


                }
            });
        }
    }
}
