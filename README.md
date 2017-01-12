# ANDROID PROGRAMMING UDACITY COURSE

# Part 2
### 2.1: Create a layout
* Create a LinearLayout and add object.
* Get the object with the ```findViewbyId(R.id.name_defined_xml)```
### 2.2: Add a Menu (a button in the ActionBar)
* Create a menu folder and a xml file in that folder which will represent the Menu item
* Override ```onCreateOptionsMenu``` in the main Activity file. It will modify the ActionBar by adding the new button just created (create the Menu)
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
	* OnBindViewHolder: OnBindViewHolder is called by the RecyclerView to display one element of the List.
    ```java
    public void onBindViewHolder(AdapterViewHolder adapterViewHolder, int position) {
        String contentToDisplay = privateVariableData[position];
        adapterViewHolder.TextViewToModify.setText(contentToDisplay);
    }
    ```

	* getItemCount: This method simply returns the number of items to display. The items data could be a ```String[]```, saved as a private variable in the Adapter class.
* 7. Modify data to display by creating a set in the Adaptater class, and call notifyDatSetChanged() in it.

##### Notes
* The recyclerView offers something called a tag object, it's meant to store any data that doesn't need to be displayed. When calling ```onCreateViewHolder```, the tag can be set to any type of value like that: ```adapterViewHolder.itemView.setTag(objectToNotDisplay);```.
* It is also possible to react when an element in the list is swipe right or left. Here is a simple code inside the onCreate of an Activity:
```java
new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
	// override when the item is moved	
	public boolean onMove(...){}
	// override when the item is swiped
	public void onSwiped(...){}
}
```

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

and the parent activity
```xml
<activity
	android:name=".MainActivity"
        android:label="@string/app_name"
        android:launchMode="singleTop">
        <intent-filter>
           <action android:name="android.intent.action.MAIN"/>
           <category android:name="android.intent.category.LAUNCHER"/>
        </intent-filter>
</activity>
```
```launchMode``` tells whether the MainActivity should be recreated when getting back to the MainActivity or only restore.
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
* A PreferenceFragment is an object to display properly a settings parameter.
* Create a Menu item (saw before).
* Create a new class in the java folder called SettingsFragment inherited from ```PreferenceFragmentCompat```.
* Create an _xml_ folder, and an xml file in it. It creates by default a PreferenceScreen in the xml. You can add CheckBoxPreference, ListPreference... Here is an example:
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
### Access the parameter value from the main activity
#### Easy way
In a method called by onCreate(), add the following code:
```java
SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(this);
# to get an object use the method get + type of the object
# Note that getString is used to access a string key from the string file
variable = sharedPreferences.getBoolean(getString(R.string.pref_parameter_key), getString(R.string.pref_parameter_value));
// pref_parameter_value are the default values
```
The problem with this method is to call the method in the onCreate method, that is only called when the app launches, rotates...
#### A better way
1. Make the the MainActivity implementing ```SharedPreferences.OnSharedPreferenceChangeListener```, override onSharedPreferenceChanged, with something like the code below:
```java
public void onSharedPrefernceChanged(SharedPreferences sharedPreferences, String key){
	if(key.equals(getString(R.string.pref_parameter_key)){
	// Then as up, 
	}
}
```
2. Register the listener by calling ```sharedPreferences.registerOnSharedPrefernceChangerListener(this)``` in the onCreate method.
3. Unregister the method by overriding the ```onDestroy``` method:
```PreferenceManager.getDefaultSharedPreferences(this).unregisterONSharedPreferenceChangeListener(this)```.

### Other Preferences
* ListPreference
An example:
```xml

```
* TextPreference
An example:
```xml
    <EditTextPreference
        android:defaultValue="Mountain View, CA 94843"
        android:inputType="text"
        android:key="location"
        android:singleLine="true"
        android:title="Location"
        >
    </EditTextPreference>
```
For TextPreference, the user can enter everything. Hence one should restrict and control what the user has inputed and act consequently.
To do so, you'll have to make the SettingsActivity inherited from ```Preference.OnPreferenceChangeListener```. Instead of ```OnSharedPreferenceChangeListener```, it is triggered BEFORE any avlue is saved in the SharedPreference file. The method to override is ```onPreferenceChange```. The listener should be attach to the  preference in onCreatePreferences:
```java 
public void onCreatePreferences(Bundle bundle, String s) {
         /* Other preference setup code code */
        //...
        Preference preference = findPreference(getString(R.string.pref_size_key));
        preference.setOnPreferenceChangeListener(this);
}
```
### Should I create a settings
![](https://d17h27t6h515a5.cloudfront.net/topher/2016/November/582a4eda_screen-shot-2016-11-14-at-3.55.51-pm/screen-shot-2016-11-14-at-3.55.51-pm.png)

# SQLite
### How to create a database
* Create a class that will represent a Table in the database:
```java
public final class TableClass{
	// constructor is private because it should not be used
	private TableClass() {}

	public static class TableClass implements BaseColumns{
		public static final String TABLE_NAME = "";
		public static final String COLUMN_NAME_WHATEVER = "whatever";
		...
	
	}
}
```
* Create the DataBase:
```java
public class DataBaseHelper extends SQLiteOpenHelper {
	// name of the database
	private static final String databaseName = "nameDB.db";
	// version of the database
	private static final int database_version = 1;

	// constructor
	public DataBaseHelper(Context context) {
		// just call parent constructor		
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// method that will actually create the database file
	public onCreate(SQLiteDatabase sqliteDatabase) {
		final String SQL_CREATE_DATABASE = "CREATE TABLE " + TableClass.TABLE_NAME + " (" + TableClass._ID + "INTEGER PRIMARY KEY AUTOINCREMENT," + TableClass.COLUMN_NAME_WHATEVER + "STRING NOT NULL" + ... + ");";
```	sqliteDatabase.execSQL(SQL_CREATE_DATABASE);
	}
}
```
### Insert and retrieve from the Database in an Activity
* Create a private parameter ```private SQLiteDatabase database;```, 
* Get the database with the command:
```java
WaitlistDbHelper wldbH = new WaitlistDbHelper(this);
database = wldbH.getWritableDatabase();
``` 
_Note that getReadableDatabase return a read-only database object._
* Query a database with the command
```sqliteDatabase.query()```. Here is the order of the arguments: location of the database/table name, filter of the columns, statement for how to filter rows, what to filter, sort order to return data)
* Insert in a database by creating a ContentValues:
```java
ContentValues contentValues = new ContentValues();
contentValues.put(KEY_NAME, value);
contentValues.put(KEY_NAME2, value2);
database.insert(TABLE_NAME, null, contentValues);
```
* Remove from the database (see the documentation).

# Content Provider
Every android developer should be aware of 4 key app components: 
* Activities
* Services
* Broadcast receivers
* Content provider
### Use Content provider from others
1. To have the access to read to a content provider, a permission must be added to the AndroidManifest:
```
<uses-permission android:name="com.example.udacity.application.TERMS_READ" />
```

2. To get acess to a content provider, one must create a ```ContentResolver``` from the ```getContentResolver```, which can perform four operations: update, query, insert, delete. To specify which contentProvider to get acess to, one must use an URI. An URI is of the form:
```ContentProviderPrefix://ContextAuthority/SpecificData```.
3. Here are some methods to use a Cursor and extract information from it:
* .getColumnIndex(COLUMN_NAME): return the index of the column
* .moveToNext() : move the pointer of the cursor to the next row, and return false when there are no more rows.
* .getString(Index): return the value at the cursor row pointer for the Index column 
* .close(): always close a cursor at the end

### Create a Content provider
* Create a class MyContentProvider that inherited from ContentProvider 
* Register the ContentProvider in the Manifest so as to be see by the system.
```xml
<provider
	android:authorities="package.name"
	android;name="package.name + class.name"
	android:exported="true if every app can access the CP"
/>
```
* MyContentProvider must override onCreate, delete, query... A URI is passed for query, delete, insert method, so the method inside must deal with different URI format. One way to deal with it is to use UriMatcher. A UriMatcher helps deals with different targeted path data. For example one may want to access a all table or only a single row refered by its ID (an Integer). So the URI must reflect this need. One example of an URI for this problem is contextauthority/tableName/#. '#' means that every integer could be pass, '*' means every sort of string.  
When the UriMatcher is build, it takes an URI, and return a different code for every different type handle for the URI. Here is how you can build an UriMatcher:
```java
URIMatcher buildUriMatcher(){
	// create a empty uriMatcher
	UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	// 1. AUTHORITIES is in ContentProvider class	
	// 2. '/#': match every URI with an integer at the end
	// 3. The code can be used as a switch in query/delete... functions where different treatment should be applied to different URI. See the example below
uriMatcher.addURI(MyContentProvider.AUTHORITIES, table.path + "/#',ID_to_return); 
}
```

Here is how you can use the UriMatcher in the insert function for example:
```
Uri insert(Uri uri, ContentValues values) {
	final SQLiteDataBase db = mTaskDbHelper.getWritableDatabase();
	int match = mUriMatcher.match(uri);
	switch(match){ ...
}

# Background task
### Services
No networking activity should be done in an Activity because we can leave the activity. This is what Services is for. It is meant for running background tasks that don't need a visual component
#### Loaders versus Services
* If you're loading or processing data that will be used in the UI, use a loader
* If the process is decoupled from the user interface, and exists even if there is no user interface, then a Service should be used. 
### Starting services
Services that are calling and started immediately.
```java
Intent intent = new Intent(this, MyIntentService.class);
startService(intent);
```
MyIntentService must override ```onHandleIntent(Intent intent)```.
Here is how one can implement a IntentService.
```java
public class MyIntentService extends IntentService {
	public MyIntentService() {super("MyIntentService"); }
	public void onHandleIntent(Intent intent) {
		// get the action that we should executed (set with ... setAction() :)
		String action = intent.getAction();
		...		
	}

}
* Every service should be defined in the Manifest.xml
* Notes that every intent services are run in another thread than the main one, but there is only one background thread for every intent. Hence every intent of the app are put in a queue, and done in order of call.
