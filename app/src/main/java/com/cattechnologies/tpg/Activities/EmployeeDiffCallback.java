package com.cattechnologies.tpg.Activities;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.cattechnologies.tpg.Model.ReportsFeePaidNew;

import java.util.List;

/**
 * Created by admin on 12/29/2017.
 */

public class EmployeeDiffCallback extends DiffUtil.Callback {

    private final List<ReportsFeePaidNew> mOldEmployeeList;
    private final List<ReportsFeePaidNew> mNewEmployeeList;

    public EmployeeDiffCallback(List<ReportsFeePaidNew> oldEmployeeList, List<ReportsFeePaidNew> newEmployeeList) {
        this.mOldEmployeeList = oldEmployeeList;
        this.mNewEmployeeList = newEmployeeList;
    }

    @Override
    public int getOldListSize() {
        return mOldEmployeeList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewEmployeeList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldEmployeeList.get(oldItemPosition).getId() == mNewEmployeeList.get(
                newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final ReportsFeePaidNew oldEmployee = mOldEmployeeList.get(oldItemPosition);
        final ReportsFeePaidNew newEmployee = mNewEmployeeList.get(newItemPosition);

        return oldEmployee.getId().equals(newEmployee.getId());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
