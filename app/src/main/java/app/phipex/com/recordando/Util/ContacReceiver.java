package app.phipex.com.recordando.Util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by sony vaio on 21/12/2015.
 */
public class ContacReceiver extends BroadcastReceiver {

    public static final int CONTACTO_AGREGADO = 1;
    public static final int CONTACTO_ELIMINADO = 2;
    public static final int CONTACTO_ACTUALIZADO = 3;

    private final ArrayAdapter<Contacto> adapter;

    public ContacReceiver(ArrayAdapter<Contacto> adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int operacion = intent.getIntExtra("operacion",-1);
        switch (operacion){
            case CONTACTO_AGREGADO : agregarContacto(intent);
                break;
            case CONTACTO_ELIMINADO: eliminarContacto(intent);
                break;
            case CONTACTO_ACTUALIZADO: actualizarContacto(intent);
                break;

        }
    }

    private void actualizarContacto(Intent intent) {
        Contacto contacto = (Contacto) intent.getSerializableExtra("datos");
        int position = adapter.getPosition(contacto);
        adapter.insert(contacto,position);
    }

    private void eliminarContacto(Intent intent) {
        ArrayList<Contacto> lista = (ArrayList<Contacto>) intent.getSerializableExtra("datos");
        for (Contacto c: lista) {
            adapter.remove(c);
        }
    }

    private void agregarContacto(Intent intent) {
        Contacto contacto = (Contacto) intent.getSerializableExtra("datos");
        adapter.add(contacto);
    }
}
