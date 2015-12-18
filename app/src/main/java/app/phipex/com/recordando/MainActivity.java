package app.phipex.com.recordando;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.phipex.com.recordando.Util.Contacto;
import app.phipex.com.recordando.Util.TextChangeListener;


public class MainActivity extends Activity {

    private EditText txtNombre,txtTelefono, txtEmail,txtDireccion;
    private Button btnAgregar;

    private List<Contacto> contactos = new ArrayList<Contacto>();

    private ListView contactoslistView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentesUI();
        inicializarTabs();

    }

    private void inicializarTabs() {
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("tab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Crear");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("tab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Contactos");
        tabHost.addTab(spec);
    }

    private void inicializarComponentesUI() {
        txtNombre = (EditText)findViewById(R.id.cmpNombre);
        txtTelefono = (EditText)findViewById(R.id.cmpTelefono);
        txtEmail = (EditText)findViewById(R.id.cmpEmail);
        txtDireccion = (EditText)findViewById(R.id.cmpDireccion);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
         contactoslistView = (ListView) findViewById(R.id.listView);

        txtNombre.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence seq, int start, int before, int count) {
                //super.onTextChanged(s, start, before, count);

                btnAgregar.setEnabled(!seq.toString().trim().isEmpty());//lo que hace es desactivar el boton cuando la secuencia es vacia

            }
        });
    }


    public void onClick(View view) {
        agregagarContacto(
                txtNombre.getText().toString(),
                txtTelefono.getText().toString(),
                txtEmail.getText().toString(),
                txtDireccion.getText().toString()
        );

        String mesg = String.format("No ha sido agregado a la lista",txtNombre.getText());
        Toast.makeText(this, mesg, Toast.LENGTH_SHORT).show();
        btnAgregar.setEnabled(false);
        inicializarLista();
        limpiarCampos();
    }

    private void inicializarLista() {

    }

    private void agregagarContacto(String nombre, String telefono, String email, String direccion) {
        contactos.add(new Contacto(nombre, telefono, email, direccion));

    }

    private void limpiarCampos() {
        txtNombre.getText().clear();
        txtTelefono.getText().clear();
        txtEmail.getText().clear();
        txtDireccion.getText().clear();
        txtNombre.requestFocus();
    }
}
