# Sweet-Mother-of-SharedPreferences
The Sweetest way into saving and loading to sharedpreferences

[![](https://jitpack.io/v/jianastrero/Sweet-Mother-of-SharedPreferences.svg)](https://jitpack.io/#jianastrero/Sweet-Mother-of-SharedPreferences)


##Installation
Add the JitPack repository to your build file then add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Add the dependency
```
dependencies {
    compile 'com.github.jianastrero:Sweet-Mother-of-SharedPreferences:v1.0'
}
```


##Usage - Instruction


###Instructions
Extend SweetSP then call the super constructor supplying context and the indentifier for this class. and set the subclass instance always to this. then declare public fields. if wanted, you could add setters and getters.
```
public class StringThingClass extends SweetSP {

    public int ctr;
    public String string;

    public StringThingClass(Context context) {
        super(context, 1); //the identifier makes this flexible. if you supply it in your constructor, you can have multiple objects like this with different identifiers. its like the id in sql.
        setSubclassInstance(this);

        string="string";
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

```


####Saving: create an instance of your subclass then call save to save all data into the sqlite database
```
StringThingClass stringThingClass =new StringThingClass(this);
stringThingClass.setString("Count: "+ctr);
stringThingClass.setCtr(ctr);
stringThingClass.save();
```

####Loading: create an instance of your subclass then call load.
```
StringThingClass stringThingClass =new StringThingClass(this);
stringThingClass.load();
```

##Thats it! Now you can easily save and load to your sqlite database with a short and elegant code :)
