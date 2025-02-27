package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FacturasActivity extends AppCompatActivity {

    private EditText edtCliente, edtMonto, edtFecha;
    private Button btnGuardar;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facturas);

        // Vincular elementos de la interfaz
        edtCliente = findViewById(R.id.edtCliente);
        edtMonto = findViewById(R.id.edtMonto);
        edtFecha = findViewById(R.id.edtFecha);
        btnGuardar = findViewById(R.id.btnGuardar);
        txtResultado = findViewById(R.id.txtResultado);

        // Establecer acción del botón
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarFactura();
            }
        });
    }

    private void guardarFactura() {
        String cliente = edtCliente.getText().toString().trim();
        String montoStr = edtMonto.getText().toString().trim();
        String fecha = edtFecha.getText().toString().trim();

        // Validar que los campos no estén vacíos
        if (cliente.isEmpty() || montoStr.isEmpty() || fecha.isEmpty()) {
            mostrarMensaje("Todos los campos son obligatorios.");
            return;
        }

        try {
            double monto = Double.parseDouble(montoStr);

            // Validar que el monto sea positivo
            if (monto <= 0) {
                mostrarMensaje("El monto debe ser mayor a 0.");
                return;
            }

            // Mostrar la factura guardada con estilo
            txtResultado.setText("✅ Factura Guardada\n📌 Cliente: " + cliente + "\n💰 Monto: $" + monto + "\n📅 Vence: " + fecha);
            txtResultado.setTextColor(Color.parseColor("#00796B"));

            mostrarMensaje("Factura registrada con éxito.");

        } catch (NumberFormatException e) {
            mostrarMensaje("Ingrese un monto válido.");
        }
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}