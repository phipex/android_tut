package app.phipex.com.recordando;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.phipex.com.recordando.Util.TextChangeListener;


public class MainActivity extends Activity {

    private EditText txtNombre,txtTelefono, txtEmail,txtDireccion;
    private Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentesUI();

    }

    private void inicializarComponentesUI() {
        txtNombre = (EditText)findViewById(R.id.cmpNombre);
        txtTelefono = (EditText)findViewById(R.id.cmpTelefono);
        txtEmail = (EditText)findViewById(R.id.cmpEmail);
        txtDireccion = (EditText)findViewById(R.id.cmpDireccion);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        txtNombre.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence seq, int start, int before, int count) {
                //super.onTextChanged(s, start, before, count);

                btnAgregar.setEnabled(!seq.toString().trim().isEmpty());//lo que hace es desactivar el boton cuando la secuencia es vacia

            }
        });
    }


    public void onClick(View view) {
        String mesg = String.format("No ha sido agregado a la lista",txtNombre.getText());
        Toast.makeText(this, mesg, Toast.LENGTH_SHORT).show();
        btnAgregar.setEnabled(false);
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtNombre.getText().clear();
        txtTelefono.getText().clear();
        txtEmail.getText().clear();
        txtDireccion.getText().clear();
        txtNombre.requestFocus();
    }
}
