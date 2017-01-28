package com.jianastrero.sweetmotherofsharedpreferencesexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int ctr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ctr=0;
    }

    public void save(View v) {
        StringThingClass stringThingClass =new StringThingClass(this);
        stringThingClass.setString("Count: "+ctr);
        stringThingClass.setCtr(ctr);
        stringThingClass.save();
        ctr++;
    }

    public void load(View v) {
        StringThingClass stringThingClass =new StringThingClass(this);
        stringThingClass.load();
        ((TextView)findViewById(R.id.text)).setText(""+ stringThingClass.getString()+"\nctr: "+stringThingClass.getCtr());
    }
}
