package mx.com.tormex.desarrollandounaaplicacion.desarrollandounaaplicacion;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout etNombreCompleto;
    private DatePicker dpFechaNacimiento;
    private TextInputLayout etTelefono;
    private TextInputLayout etEmail;
    private TextInputLayout etDescripcionContacto;
    private Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombreCompleto = (TextInputLayout) findViewById(R.id.etNombreCompleto);
        dpFechaNacimiento = (DatePicker) findViewById(R.id.dpFechaNacimiento);
        etTelefono = (TextInputLayout) findViewById(R.id.etTelefono);
        etEmail = (TextInputLayout) findViewById(R.id.etEmail);
        etDescripcionContacto = (TextInputLayout) findViewById(R.id.etDescripcionContacto);
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);

        Bundle extras = getIntent().getExtras();
        if (extras!= null){
            etNombreCompleto.getEditText().setText(extras.containsKey(getResources().getString(R.string.varNombre)) ? extras.getString(getResources().getString(R.string.varNombre).toString()): "");
            try{
                SimpleDateFormat sdf = new SimpleDateFormat(getResources().getString(R.string.dateFormat));
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(sdf.parse(extras.getString(getResources().getString(R.string.varFechaNacimiento).toString())));

                dpFechaNacimiento.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            } catch (Exception e) {
                Toast.makeText(this, "A: Error al convertir la fecha", Toast.LENGTH_SHORT).show();
            }

            etTelefono.getEditText().setText(extras.containsKey(getResources().getString(R.string.varTelefono)) ? extras.getString(getResources().getString(R.string.varTelefono).toString()): "");
            etEmail.getEditText().setText(extras.containsKey(getResources().getString(R.string.vareMail)) ? extras.getString(getResources().getString(R.string.vareMail).toString()): "");
            etDescripcionContacto.getEditText().setText(extras.containsKey(getResources().getString(R.string.varDescripcion)) ? extras.getString(getResources().getString(R.string.varDescripcion).toString()): "");
        }

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Contacto contacto = new Contacto(etNombreCompleto.getEditText().getText().toString(), getDateFromDatePicker(dpFechaNacimiento), etTelefono.getEditText().getText().toString(), etEmail.getEditText().getText().toString(), etDescripcionContacto.getEditText().getText().toString());
                    Intent intent = new Intent(MainActivity.this, ConfirmarDatos.class);
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

    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        Calendar calendar = Calendar.getInstance();
        calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        return calendar.getTime();
    }
}

