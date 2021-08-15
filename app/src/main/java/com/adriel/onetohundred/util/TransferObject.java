package com.adriel.onetohundred.util;

import java.io.Serializable;
import java.util.List;

public class TransferObject implements Serializable {

    // Data storage
    private Integer integer;
    private boolean confirmed;
    private String thisPlayerTitle;
    private String nextPlayerTitle;
    private List<String> stringList;

    private static TransferObject transferObject = null;

    private TransferObject() {}

    public static TransferObject getInstance() {
        if (transferObject == null) {
            transferObject = new TransferObject();
        }
        return transferObject;
    }

    public Integer getInteger() {
        return integer;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getThisPlayerTitle() {
        return thisPlayerTitle;
    }

    public void setThisPlayerTitle(String thisPlayerTitle) {
        this.thisPlayerTitle = thisPlayerTitle;
    }

    public String getNextPlayerTitle() {
        return nextPlayerTitle;
    }

    public void setNextPlayerTitle(String nextPlayerTitle) {
        this.nextPlayerTitle = nextPlayerTitle;
    }
}
