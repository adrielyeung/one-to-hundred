package com.adriel.onetohundred.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.adriel.onetohundred.util.TransferObject;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<TransferObject> data = new MutableLiveData<>();

    public void setData(TransferObject transferObject) {
        data.setValue(transferObject);
    }

    public LiveData<TransferObject> getData() {
        return data;
    }
}
