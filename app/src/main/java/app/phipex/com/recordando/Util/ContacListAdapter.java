package app.phipex.com.recordando.Util;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.phipex.com.recordando.R;

/**
 * Created by sony vaio on 18/12/2015.
 */
public class ContacListAdapter extends ArrayAdapter<Contacto>{
    private Activity ctx;
    public ContacListAdapter(Activity context, List<Contacto> contactos){
        super(context, R.layout.listview_item, contactos);
        this.ctx = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        if (convertView == null){
            convertView = ctx.getLayoutInflater().inflate(R.layout.listview_item,parent,false);

        }
        Contacto actual = this.getItem(position);
        inicializarCamposDeTexto(convertView,actual);
        return convertView;
    }

    private void inicializarCamposDeTexto(View view, Contacto actual) {
        TextView viewText = (TextView)view.findViewById(R.id.viewNombre);
        viewText.setText(actual.getNombre());

        TextView viewTelefono = (TextView)view.findViewById(R.id.viewTelefono);
        viewTelefono.setText(actual.getTelefono());

        TextView viewEmail = (TextView)view.findViewById(R.id.viewEmail);
        viewEmail.setText(actual.getEmail());

        TextView viewDireccion = (TextView)view.findViewById(R.id.viewDireccion);
        viewDireccion.setText(actual.getDireccion());

        ImageView ivContactImage = (ImageView)view.findViewById(R.id.ivContactImage);
        ivContactImage.setImageURI(Uri.parse(actual.getImageUri()));

    }
}
