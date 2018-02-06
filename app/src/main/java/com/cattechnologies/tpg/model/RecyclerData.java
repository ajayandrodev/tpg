package com.cattechnologies.tpg.model;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

/**
 * Created by admin on 11/24/2017.
 */

public class RecyclerData {


    String title;
    String description;
    RecyclerView data;
    @Override
    public boolean equals(Object v) {
        boolean retVal = false;

        if (v instanceof RecyclerData) {
            RecyclerData ptr = (RecyclerData) v;
            retVal = ptr.title.equals(this.title);
        }

        return retVal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.title != null ? this.title.hashCode() : 0);
        return hash;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCrossImage(ImageView crossImage) {
        setCrossImage(crossImage);
    }
}
