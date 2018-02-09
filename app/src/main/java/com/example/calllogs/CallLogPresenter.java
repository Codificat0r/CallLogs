package com.example.calllogs;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.telecom.Call;

/**
 * Created by usuario on 9/02/18.
 */

public class CallLogPresenter implements LoaderManager.LoaderCallbacks<Cursor>, CallLogContract.Presenter {

    CallLogContract.Vista vista;
    private static final int CALLLOG = 0;

    public CallLogPresenter(CallLogContract.Vista vista) {
        this.vista = vista;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        CursorLoader loader = null;
        switch (i) {
            case CALLLOG:
                String[] projection={CallLog.Calls._ID, CallLog.Calls.NUMBER, CallLog.Calls.DATE, CallLog.Calls.DURATION, CallLog.Calls.TYPE};
                String order = CallLog.Calls.DATE + " DESC";
                loader = new CursorLoader(vista.getContext(), Uri.parse("content://call_log/calls"), projection, null,null,order);
                break;
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        vista.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        vista.swapCursor(null);
    }

    @Override
    public void getCallLogs() {
        //Necesitamos el contexto por lo que hacemos este truquito ya que el presenter
        //no debe tener el contexto de la vista
        ((Activity)vista.getContext()).getLoaderManager().restartLoader(CALLLOG, null, this);
    }
}
