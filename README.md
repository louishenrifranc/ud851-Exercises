# ANDROID PROGRAMMING UDACITY COURSE

# Part 2
### 2.1: Create a layout
* Create a LinearLayout and add object.
* Get the object with the ```findViewbyId(R.id.name_defined_xml)```
### 2.2: Add a Menu (a button in the ActionBar)
* Create a menu folder and a xml file in that folder which will represent the Menu item
* Override ```onCreateOptionsMenu``` in the main Activity file. It will modify the ActionBar by adding the new button just created
```java
public boolean onCreateOptionsMenu(Menu menu) {
 MenuInflater inflater = getMenuInflater();
 inflater.inflate(R.menu.forecast, menu);
 return True;
}
```
* Set the action to do when someone click on the Menu button created:
```java
public boolean onOptionsItemSelected(MenuItem item){
	int id = item.getItemId();
	if(id = R.id.action_refresh){
# Create a new intent to open up the Settings Activity
}...
}
```
* Create a Toast with the lines:
```java
Toast.makeText(NameOfTheClass.context, message, TOAST.LENGTH_LONG).show()
```


### Build our URL
* Get the text of EditText widget: ```widget.getText().toString()```

## Connect to the Internet
* adding permission in manifests inside the manifest outside of everything else: 

### AsyncTask
* Create a class which extended from AsynTask<URL, Void, String>. The three parameters are the type of the params send to the task, the type of the progress units published during the background computation, and the type of the result.
* Override the doInBackground function
* Override, (not forced), the onPostExecute, which is the function called at the end on the computation
* Called the AsynTask inherited object background method with execute(params) and not doInBackground.

### addPolish
* Make a variable invisible in xml: ```android:visibility```, then in .java file it is possible to modify the visibility by calling setVisibility(View.INVISIBLE) on the Object.
* Create a ProgressBar

# Part 3. Recycler View
* 1. Add dependencies in build.gradle ```compile 'com.android.support:recyclerview-v7:25.0.1'```
* 2. Transform ScrollView by RecycleListView in the layout.xml file.
* 3. Add a new layout in the layout folder which will represent an item in the list
* 4. Create an AdapterViewHolder extending RecyclerView.ViewHolder. It will cache children's views for an item. 
* 5. Create an Adapter class extending a RecyclerView.Adapter<AdapterViewHolder>.
* 6. Override three functions:
	* onCreateViewHolder: This gets called when each new ViewHolder is created:
    ```java
    public AdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.recycler_view_id_in_xml;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new AdapterViewHolder(view);
    }
    ```
	* OnBindViewHolder: OnBindViewHolder is called by the RecyclerView to display the data at the specified position.
    ```java
    public void onBindViewHolder(AdapterViewHolder adapterViewHolder, int position) {
        String contentToDisplay = privateVariableData[position];
        adapterViewHolder.TextViewToModify.setText(contentToDisplay);
    }
    ```

	* getItemCount: This method simply returns the number of items to display. The items data could be a ```String[]```, saved as a private variable in the Adapter class.
* 7. Modify data to display by creating a set in the Adaptater class, and call notifyDatSetChanged() in it.

# Intents
### Definition
* Start an Activity from another Activity (in another app or not). It is done by passing messages called Intents.
### Create an Intent
* To pass to another Activity, you have to create a new intent:
```java
Intent intent = new Intent(contextFirstActivity, contextNewActivity)
// A context object is a reference to the activity class (this or .class)
startActivity(intent);
```
### Pass and receive data
* To also pass data, use the method ```i.putExtra("CODE_FOR_RETRIEVAL", dataVariable)```

* To receive the data in the new Activity:
```java
Bundle extras = getIntent().getExtras();
data = (Type) extras.get("CODE_FOR_RETRIEVAL");
```
### Activity for Result
You can launch new activity only to get back result
```java
startActivityForResult(
	new Intent(),
	"CODE_FOR_RETRIEVAL");
// and get back the data
onActivityResult("CODE_FOR_RETRIEVAL", intResultCode, intent);
```

### Common intent 
* A common intent does not specify the app component to start, but instead specifies an action. Here is the example of how to start a browser. To check if an any app can resolve the activity, for example launch a browser, you can call the method ```intent.resolveActivity(getPacketManager())```. 
* When creating a new Activity it is possible to order them in the AndroidManifest.xml, so that a default Return button to go back from the previous Activity can be set. Here is the definition of a Child Activity:
```xml
<activity
            android:name=".DetailActivity"
            android:parentActivityName=".MainActivity">
            <meta-data
                android:name="android.support.PARENT_ACTIVITY"
                android:value=".MainActivity" />
</activity>
```

# Life cycle
### Logging message to the terminal
* To log something, you can use the method
```java
// TAG can be Activity.class.getSimpleName()
Log.d(TAG, "string to print");
```

### Lifecycle callback
![Activity Lifecyle](https://developer.android.com/guide/components/images/activity_lifecycle.png)

When the app started, stoped, or is even put in background, some functions fire and you can override them:
* onCreate() : always implement this callback. It is called when the system create this Activity
* onStart()

### Save Activity state
Activity state can be destroyed for multiple reasons (pressing back button, calling finish()). If an Activity is destroyed and recreated, all the layout/view are saved using the Bundle instante (Bundle is a mapping from String keys to various Parcelable values, Parcelable is an interface to write and read back class). But menbers variable of the Activity are destroyed so there is a way to saved them:
```java

public void onSaveInstanceState(Bundle savedInstanceState) {
	super.onSaveInstanceState(savedInstanceState);
	savedInstanceState.putInt("CODE_FOR_RETRIEVAL", variableToSave);
}
```

Each variable can be restore in the ```onCreate()``` method using the Bundle.

### Use AsyncTaskLoader instead of AsyncTask
It makes AsyncTask non dependant of the Activity life. Because it becomes a Loader, it will lives even if the Activity is destroyed. For example if you launch a network research, and the orientation of the screen is changed, then the thread handler is reset but the thread keep running.
One particular subclass of Loaders is of interest: the AsyncTaskLoader. This class performs the same function as the AsyncTask, but a bit better. It can handle Activity configuration changes more easily, and it behaves within the life cycles of Fragments and Activities. The nice thing is that the AsyncTaskLoader can be used in any situation that the AsyncTask is being used

# Preferences
### Data Persistence
* Bundle to save key-value pairs. Data is gone if app is shutdown, it is a temporary saved place
* SharedPreference: save key-value pairs in a File. Save forever until the app is uninstall or phone is crashed.	It is used to save small information about the user/app state such as string/numerical values.
* SQLite
* Internal/External storage: save into the memory card/hard drive music...
* Save in the cloud (Google Firebase)

### Create a Settings Activity and add it a PreferenceFragment
* A PreferenceFragment is an object to display properly an object representing a settings parameter.
* Create a Menu item (saw before)
* Create a new class in the java folder called SettingsFragment inherited from ```PreferenceFragmentCompat```.
* Create an _xml_ folder, and a xml file in it. It create by default a PreferenceScreen in the xml. Here is an example:
```xml
<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android">
    <CheckBoxPreference
        android:defaultValue="true"
        android:key="Name of the parameter"
        android:summaryOff="What to display when value is False"
        android:summaryOn="What to .. when value is True"
        android:title="Name to display"
        ></CheckBoxPreference>
</PreferenceScreen>
```
* Back in the SettingsFragment file, use the method ```addPreferencesFromRessource(R.xml.id_xml_file);
* Modify the settings activity if not done to display the new PreferenceScreen.
```xml
<fragment xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/activity_settings"
    android:name="android.example.com.visualizerpreferences.AudioVisuals.SettingsFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```
* Because we used the PreferenceFragmentCompat library, we need to add to the style xml:
```<item name="preferenceTheme">@style/PreferenceThemeOverlay</item>```.




