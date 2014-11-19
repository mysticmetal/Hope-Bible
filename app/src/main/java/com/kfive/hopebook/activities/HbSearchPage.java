package com.kfive.hopebook.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.kfive.hopebook.R;
import com.kfive.hopebook.fragments.HbBibleVersion;
import com.kfive.hopebook.helpers.SearchHelper;

import java.util.ArrayList;

public class HbSearchPage extends ActionBarActivity {

    //Extra intent message
    public static final String EXTRA_MESSAGE = "com.kfive.hopebookbete.MESSAGE";

    private static final String CUSTOM_FONT = "fonts/Dosis-Medium.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hb_search_page);
        setSearchFonts();
        setSelectCriteria();
        setTitle("Search Bible");
    }

    private void setSearchFonts() {
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), CUSTOM_FONT);

        TextView txtsearch = (TextView) findViewById(R.id.txtsearch);
        txtsearch.setTypeface(myTypeface);

        TextView txtsearchin = (TextView) findViewById(R.id.txtsearchin);
        txtsearchin.setTypeface(myTypeface);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hb_search_page, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //method shows spinner with filter values
    private void setSelectCriteria() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.searchfilter, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    //Events
    //events

    public void onHome(View v){
        Intent intent = new Intent(this, HbHome.class);
        startActivity(intent);
    }

    public void onBookSelect(View v){
        Intent intent = new Intent(this, HbBooksAll.class);
        startActivity(intent);
    }


    public void onSearchClick(View v) {
        SearchHelper searchHelper = new SearchHelper(this);
        //first we build another array list to be sent as intent
        ArrayList<String> itemlist = new ArrayList<String>();
        EditText searchtext = (EditText) findViewById(R.id.searchbox);
        itemlist.add(searchtext.getText().toString());

        CheckBox chkbx1 = (CheckBox) findViewById(R.id.chkbxexact);
        if(chkbx1.isChecked()){
            itemlist.add("true");
        }else{itemlist.add("false"); }

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        String searchcriteria = spinner.getSelectedItem().toString();
        itemlist.add(searchcriteria);


        Intent intent = new Intent(this, HbSearchResult.class);

        intent.putStringArrayListExtra(EXTRA_MESSAGE, itemlist);
        startActivity(intent);
    }
}
