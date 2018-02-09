package com.example.calllogs;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by usuario on 9/02/18.
 */

public interface CallLogContract {
    interface Vista {
        void swapCursor(Cursor cursor);
        Context getContext();
    }

    interface Presenter {

        void getCallLogs();
    }
}
