package app.phipex.com.recordando;

import android.app.Fragment;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import app.phipex.com.recordando.Util.ContacListAdapter;
import app.phipex.com.recordando.Util.ContacReceiver;
import app.phipex.com.recordando.Util.Contacto;

/**
 * Created by sony vaio on 21/12/2015.
 */
public class ListaContactosFragment extends Fragment {

    private ArrayAdapter<Contacto> adapter;
    private ListView contactsListView;
    private ContacReceiver receiver;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista_contactos, container, false);
        inicializaComponentes(rootView);
        setHasOptionsMenu(true);//habilita el actionbar en este fragment para tener botones
        return rootView;
    }

    private void inicializaComponentes(View view) {
        contactsListView = (ListView)view.findViewById(R.id.listView);
        adapter = new ContacListAdapter(getActivity(),new ArrayList<Contacto>());
        //se configura apara el adapter notifique en el dataser atutomatimente
        adapter.setNotifyOnChange(true);
        contactsListView.setAdapter(adapter);

    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        receiver = new ContacReceiver(adapter);
        getActivity().registerReceiver(receiver, new IntentFilter("listacontactos"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_eliminar_contacto:
                eliminarContacto(item);
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    private void eliminarContacto(MenuItem item) {
        SparseBooleanArray array= contactsListView.getCheckedItemPositions();
        ArrayList<Contacto> seleccion = new ArrayList<Contacto>();
        for (int i = 0; i < array.size(); i++) {
             int posicion = array.keyAt(i);
            if (array.valueAt(i)){
                seleccion.add(adapter.getItem(posicion));
            }
            Intent intent = new Intent("listacontactos");
            intent.putExtra("operacion",seleccion);
            getActivity().sendBroadcast(intent);
            contactsListView.clearChoices();

        }
        
    }
}
