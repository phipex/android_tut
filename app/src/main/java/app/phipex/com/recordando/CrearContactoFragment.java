package app.phipex.com.recordando;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import app.phipex.com.recordando.Util.Contacto;
import app.phipex.com.recordando.Util.TextChangeListener;
import app.phipex.com.recordando.Util.ContacReceiver;

/**
 * Created by sony vaio on 21/12/2015.
 */
public class CrearContactoFragment extends Fragment implements View.OnClickListener {



    private EditText txtNombre,txtTelefono, txtEmail,txtDireccion;
    private Button btnAgregar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View rootview = inflater.inflate(R.layout.fragment_crear_contactos,container,false);
        inicializarComponentes(rootview);
        return rootview;
    }

    private ImageView imgViewContacto;


    private int request_code = 1;

    @Override
    public void onClick(View view) {
        if(view == btnAgregar){
            onClickBtnAgregar(view);
        }else if(view == imgViewContacto) {
            onImgClick(view);
        }
    }

    private void inicializarComponentes(View view) {
        txtNombre = (EditText)view.findViewById(R.id.cmpNombre);
        txtTelefono = (EditText)view.findViewById(R.id.cmpTelefono);
        txtEmail = (EditText)view.findViewById(R.id.cmpEmail);
        txtDireccion = (EditText)view.findViewById(R.id.cmpDireccion);
        btnAgregar = (Button) view.findViewById(R.id.btnAgregar);
        imgViewContacto = (ImageView) view.findViewById(R.id.imgViewContacto);
        imgViewContacto.setOnClickListener(this);
        txtNombre.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence seq, int start, int before, int count) {
                //super.onTextChanged(s, start, before, count);

                btnAgregar.setEnabled(!seq.toString().trim().isEmpty());//lo que hace es desactivar el boton cuando la secuencia es vacia

            }
        });
        btnAgregar.setOnClickListener(this);
    }

    public void onClickBtnAgregar(View view) {
        agregagarContacto(
                txtNombre.getText().toString(),
                txtTelefono.getText().toString(),
                txtEmail.getText().toString(),
                txtDireccion.getText().toString(),
                String.valueOf(imgViewContacto.getTag())
        );

        String mesg = String.format("Ha sido agregado a la lista",txtNombre.getText());
        Toast.makeText(view.getContext(), mesg, Toast.LENGTH_SHORT).show();
        btnAgregar.setEnabled(false);
        limpiarCampos();
    }

    public void onImgClick(View view) {
        Intent intent = null;
        if (Build.VERSION.SDK_INT < 19)
        {//android jellybean 4.3 y anteriores
            intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);

        }else {
            //android kitkat
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);

        }
        intent.setType("image/*");
        startActivityForResult(intent, request_code);
    }

    private void limpiarCampos() {
        txtNombre.getText().clear();
        txtTelefono.getText().clear();
        txtEmail.getText().clear();
        txtDireccion.getText().clear();
        // restablesca la image predeterminada
        imgViewContacto.setImageResource(R.drawable.contacto);

        txtNombre.requestFocus();
    }

    private void agregagarContacto(String nombre, String telefono, String email, String direccion, String imageUri) {
        Contacto nuevo = new Contacto(nombre, telefono, email, direccion,imageUri);
        Intent intent = new Intent("listacontactos");
        intent.putExtra("operacion", ContacReceiver.CONTACTO_AGREGADO);
       intent.putExtra("datos", nuevo);
        getActivity().sendBroadcast(intent);
        //TODO corregir adapter.add(nuevo);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == request_code){
            imgViewContacto.setImageURI(data.getData());
            //guardamos en TAG el uri
            imgViewContacto.setTag(data.getData());
        }


    }
}
