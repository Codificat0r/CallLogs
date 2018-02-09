package com.example.calllogs;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends ListActivity implements CallLogContract.Vista {
    private ListView lstvLlamadas;
    CallLogContract.Presenter presenter;
    CallLogAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new CallLogAdapter(this);
        lstvLlamadas = findViewById(android.R.id.list);
        lstvLlamadas.setAdapter(adapter);
        presenter = new CallLogPresenter(this);
        presenter.getCallLogs();
    }

    /*
       Método que inicializa el adapter con un cursor.
     */
    @Override
    public void swapCursor(Cursor cursor) {
        adapter.swapCursor(cursor);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
