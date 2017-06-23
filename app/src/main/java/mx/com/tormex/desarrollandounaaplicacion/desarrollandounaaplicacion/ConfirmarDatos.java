package mx.com.tormex.desarrollandounaaplicacion.desarrollandounaaplicacion;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ConfirmarDatos extends AppCompatActivity {

    private TextView tvNombreCompleto;
    private TextView tvFechaNacimiento;
    private TextView tvTelefono;
    private TextView tveMail;
    private TextView tvDescripcionContacto;
    private Button btnEditarDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_datos);

        Bundle parametros = getIntent().getExtras();
        tvNombreCompleto = (TextView) findViewById(R.id.tvNombreCompleto);
        tvFechaNacimiento = (TextView) findViewById(R.id.tvFechaNacimiento);
        tvTelefono = (TextView) findViewById(R.id.tvTelefono);
        tveMail = (TextView) findViewById(R.id.tvEmail);
        tvDescripcionContacto = (TextView) findViewById(R.id.tvDescripcion);

        tvNombreCompleto.setText(parametros.getString(getResources().getString(R.string.varNombre).toString()));
        tvFechaNacimiento.setText(parametros.getString(getResources().getString(R.string.varFechaNacimiento)));
        tvTelefono.setText(parametros.getString(getResources().getString(R.string.varTelefono).toString()));
        tveMail.setText(parametros.getString(getResources().getString(R.string.vareMail).toString()));
        tvDescripcionContacto.setText(parametros.getString(getResources().getString(R.string.varDescripcion).toString()));

        btnEditarDatos = (Button) findViewById(R.id.btnEditarDatos);

        btnEditarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    SimpleDateFormat sdf = new SimpleDateFormat(getResources().getString(R.string.dateFormat));
                    Contacto contacto = new Contacto(tvNombreCompleto.getText().toString(), sdf.parse(tvFechaNacimiento.getText().toString()), tvTelefono.getText().toString(), tveMail.getText().toString(), tvDescripcionContacto.getText().toString());
                    Intent intent = new Intent(ConfirmarDatos.this, MainActivity.class);
                    intent.putExtra(getResources().getString(R.string.varNombre), contacto.getNombre());
                    DateFormat df = new SimpleDateFormat(getResources().getString(R.string.dateFormat));
                    intent.putExtra(getResources().getString(R.string.varFechaNacimiento), df.format(contacto.getFechaNacimiento()));
                    intent.putExtra(getResources().getString(R.string.varTelefono), contacto.getTelefono());
                    intent.putExtra(getResources().getString(R.string.vareMail), contacto.geteMail());
                    intent.putExtra(getResources().getString(R.string.varDescripcion), contacto.getDescripcion());
                    startActivity(intent);
                    finish();
                } catch (Exception e){
                    Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}
