package com.vandana.mvvmexample.contentprovider;


import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vandana.mvvmexample.R;

// sample application two passing the Sqlite data on this activity
public class Main2Activity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>
{
    TextView resultView = null;
    CursorLoader cursorLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        resultView= (TextView) findViewById(R.id.res);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1)
    {
        cursorLoader = new CursorLoader(this, Uri.parse("content://com.kathir.samplevolleyandretrofit.contentprovider2.MyProvider/cte"), null, null, null, null);
        return cursorLoader;
    }

    public void onClickDisplayNames(View view)
    {
        getSupportLoaderManager().initLoader(1, null, this);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor)
    {
        if (cursor != null)
        {
            cursor.moveToFirst();
            StringBuilder res = new StringBuilder();
            while (!cursor.isAfterLast()) {
                res.append("\n"+cursor.getString(cursor.getColumnIndex("id"))+ "-"+ cursor.getString(cursor.getColumnIndex("name")));
                cursor.moveToNext();
            }
            resultView.setText(res);
        }
        else
        {
            Toast.makeText(getBaseContext(), "No records", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0)
    {
    }
}
