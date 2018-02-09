package com.example.calllogs;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by usuario on 9/02/18.
 */

public class CallLogAdapter extends CursorAdapter {


    public CallLogAdapter(Context context) {
        //Es ese flag para que se notifiquen los cambios de la base de datos de manera automatica.
        super(context, null, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    }

    /**
     * Metodo que inicializa el objeto
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_llamada, null);
        CallHolder callHolder = new CallHolder();
        callHolder.txvNumero = view.findViewById(R.id.txvNumero);
        callHolder.txvFecha = view.findViewById(R.id.txvFecha);
        callHolder.txvDuracion = view.findViewById(R.id.txvDuracion);
        callHolder.txvTipo = view.findViewById(R.id.txvTipo);
        view.setTag(callHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        CallHolder callHolder = (CallHolder) view.getTag();
        //La gestión del cursor la hace el mismo adapter, lo mueve a la primera posición,
        //avanza, etc...
        callHolder.txvNumero.setText(cursor.getString(1));
        Date date = new Date(cursor.getLong(2));
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMMM, yyyy");
        callHolder.txvFecha.setText(format.format(date));
        callHolder.txvDuracion.setText(cursor.getString(3) + " segundos");
        int type = Integer.parseInt(cursor.getString(4));
        callHolder.txvTipo.setText(TipoLlamada(type));
    }

    private String TipoLlamada(int type) {
        String tipo = "";
        switch (type) {
            case CallLog.Calls.INCOMING_TYPE:
                tipo = "Llamada entrante";
                break;
            case CallLog.Calls.MISSED_TYPE:
                tipo = "Llamada perdida";
                break;
            case CallLog.Calls.OUTGOING_TYPE:
                tipo = "Llamada saliente";
                break;
        }
        return tipo;
    }

    private class CallHolder {
        private TextView txvNumero;
        private TextView txvFecha;
        private TextView txvDuracion;
        private TextView txvTipo;
    }
}
