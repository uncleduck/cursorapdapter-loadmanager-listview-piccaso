package com.xarv.flatchat;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

//使用ListView+CursorAdapter+LoaderManger。
// 这种方式可以把listview绑定一个CursorAdapter
// 然后就可以将listView的每个item当做是一个数据库返回的cursor的一条记录，
// 然后通过cursor的get方法获取这条数据的各个属性数据来填充Item中所需要的数据。
// 这种方式就不用吧cursor的数据一层一层封装来获取，而是直接通过cursor来更新item，这样就能实时更新视图的数据了。
// CursorAdapter的每个item的视图创建是在覆盖的newView(Context context, Cursor cursor, ViewGroup group)和bindView(View view, Context context, Cursor cursor)这两个方法上。
// 第一次创建视图是会先调用newView后调用bindView方法。
// 如果视图已经创建，那么每次刷新只会调用bindView。
// 所以用CussorAdapter时是在newView中初始item的视图并return 这个视图，在bindView中负责视图更新的操作。
public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final  int  URL_LOADER = 1;
    private String authorities= "com.xarv.flatchat";
    private String[] mProjection = {"_id","MSG_ID","MSG_TYPE" ,"MSG_DATA","MSG_TIME"};
    private Uri msgTable = Uri.parse("content://" + authorities+"/content"  + "/MSG_TABLE");

    private ListView msgListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        msgListView = (ListView) findViewById(R.id.msg_list);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            msgListView.setDivider(this.getResources().getDrawable(R.drawable.transparent_color,null));
        }
        msgListView.setDividerHeight(20);
        getLoaderManager().initLoader(URL_LOADER, null, this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    //when initloader to trigger the function
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case URL_LOADER:
                // Returns a new CursorLoader
                return new CursorLoader(
                        MainActivity.this,   // Parent activity context
                        msgTable,        // Table to query
                        mProjection,     // Projection to return
                        null,            // No selection clause
                        null,            // No selection arguments
                        null             // Default sort order
                );
            default:
                // An invalid id was passed in
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //Toast.makeText(this, data.toString(), Toast.LENGTH_LONG).show();
        // String _id = data.getString(0);
        MessageAdapter msgAdapter = new MessageAdapter(this,data,false);
        msgListView.setAdapter(msgAdapter);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
