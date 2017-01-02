# Toy App Exercise Repo

# Part 2
### 2.1: Create a layout
* Create a LinearLayout and add object.
* Get the object with the ```findViewbyId(R.id.name_defined_xml)```
### 2.2: Add a Menu
* Create a Menu in the xml file.
* Add it to the action bar.
* Save a string in the res/values/strings.xml and affect it to an element in the xml file via @string/name_of_the_string_.
* Overload onCreateOptionsMenu in .java file. It will modify the Menu (Action Bar)
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
	if(id = R.id.action_refresh){}...
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
* Start an Activity from another Activity. It is done by passing messages called Intents.
* To pass to another Activity, you have to create a new intent:
```java
Intent intent = new Intent(contextFirstActivity, contextNewActivity)
# A context object is a reference to the activity class (this or .class)
```
* To also pass data, use the method ```i.putExtra("NAME_FOR_RETRIEVAL", dataVariable)```

* To receive the data in the new Activity:
```java
Bundle extras = getIntent().getExtras();
data = (Type) extras.get("NAME_FOR_RETRIEVAL");
```
