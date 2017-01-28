package com.jianastrero.sweetmotherofsharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import com.jianastrero.sweetmotherofsharedpreference.exception.SweetSPSuperNotCalledException;
import com.jianastrero.sweetmotherofsharedpreference.exception.UnknownSubclassInstanceException;

import org.json.JSONException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Jian Astrero on 1/28/2017.
 */
public abstract class SweetSP {

    private Context context;
    private SharedPreferences sp;
    private SharedPreferences.Editor spe;
    private HashMap<String, String> map;
    private boolean isSuperCalled=false;
    private Object object;
    private boolean sweeter=true;
    private long identifier;

    public SweetSP(Context context, long identifier) {
        this.context=context;
        map=new HashMap<>();
        isSuperCalled=true;
        this.identifier=identifier;
    }

    public boolean isSweeter() {
        return sweeter;
    }

    public void setSweeter(boolean sweeter) {
        this.sweeter = sweeter;
    }

    public void setSubclassInstance(SweetSP subclassInstance) {
        this.object = subclassInstance;

        sp=context.getSharedPreferences(subclassInstance.getClass().getName()+"#"+identifier, Context.MODE_PRIVATE);
        spe=sp.edit();
    }

    public void addData(String key, Object value) {
        if (!isSuperCalled) throw new SweetSPSuperNotCalledException();
        map.put(key, ""+value);
    }

    public void setIdentifier(long identifier) {
        this.identifier=identifier;
    }

    private void addAllData() {
        if (!isSuperCalled) throw new SweetSPSuperNotCalledException();
        if (object==null) throw new UnknownSubclassInstanceException();
        if (sweeter) {
            Field[] fields=object.getClass().getFields();

            for (Field f:fields) {
                try {
                    addData(f.getName(), f.get(object));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void save() {
        if (!isSuperCalled) throw new SweetSPSuperNotCalledException();
        if (object==null) throw new UnknownSubclassInstanceException();

        if (sweeter) addAllData();

        for (String key:map.keySet()) {
            String val=map.get(key);
            spe.putString(key, val);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            spe.apply();
        } else {
            spe.commit();
        }
    }

    public void load() {
        if (!isSuperCalled) throw new SweetSPSuperNotCalledException();
        if (object==null) throw new UnknownSubclassInstanceException();

        if (sweeter) addAllData();

        Field[] fields=object.getClass().getFields();
        Map<String, ?> map=sp.getAll();

        for (Field f:fields) {
            try {
                if (f.getType()==int.class || f.getType()==Integer.class)
                    f.set(object, Integer.parseInt(""+map.get(f.getName())));
                else if (f.getType()==boolean.class || f.getType()==Boolean.class)
                    f.set(object, Boolean.parseBoolean(""+map.get(f.getName())));
                else if (f.getType()==byte.class || f.getType()==Byte.class)
                    f.set(object, Byte.parseByte(""+map.get(f.getName())));
                else if (f.getType()==char.class || f.getType()==Character.class)
                    f.set(object, (""+map.get(f.getName())).charAt(0));
                else if (f.getType()==double.class || f.getType()==Double.class)
                    f.set(object, Double.parseDouble(""+map.get(f.getName())));
                else if (f.getType()==float.class || f.getType()==Float.class)
                    f.set(object, Float.parseFloat(""+map.get(f.getName())));
                else if (f.getType()==long.class || f.getType()==Long.class)
                    f.set(object, Long.parseLong(""+map.get(f.getName())));
                else if (f.getType()==short.class || f.getType()==Short.class)
                    f.set(object, Short.parseShort(""+map.get(f.getName())));
                else
                    f.set(object, (""+map.get(f.getName())));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
