package com.jianastrero.sweetmotherofsharedpreferencesexample;

import android.content.Context;

import com.jianastrero.sweetmotherofsharedpreference.SweetSP;

/**
 * Created by Jian Astrero on 1/28/2017.
 */
public class StringThingClass extends SweetSP {

    public int ctr;
    public String string;

    public StringThingClass(Context context) {
        super(context, 1);
        setSubclassInstance(this);

        string ="string";
        ctr=0;
    }

    public int getCtr() {
        return ctr;
    }

    public void setCtr(int ctr) {
        this.ctr = ctr;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
