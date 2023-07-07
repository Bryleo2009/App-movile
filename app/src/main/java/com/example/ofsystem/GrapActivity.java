package com.example.ofsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class GrapActivity extends AppCompatActivity {

    private LineChart chart1;
    private BarChart chart2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grap);
        Toolbar toolbar = findViewById(R.id.toolbar);

        chart1 = findViewById(R.id.chart1);
        chart2 = findViewById(R.id.chart2);

        setupChart1();
        setupChart2();
        //activacion del toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupChart1() {
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 1000)); // Marzo
        entries.add(new Entry(1, 2000)); // Abril
        entries.add(new Entry(2, 1500)); // Mayo
        entries.add(new Entry(3, 3000)); // Junio

        LineDataSet dataSet = new LineDataSet(entries, "Ventas Mensuales");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextColor(android.R.color.black);

        LineData lineData = new LineData(dataSet);
        chart1.setData(lineData);
        chart1.invalidate();
    }

    private void setupChart2() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, new float[]{500, 800})); // Hombres: 500, Mujeres: 800
        entries.add(new BarEntry(1, new float[]{1200, 1500})); // Hombres: 1200, Mujeres: 1500
        entries.add(new BarEntry(2, new float[]{900, 1100})); // Hombres: 900, Mujeres: 1100
        entries.add(new BarEntry(3, new float[]{1800, 2200})); // Hombres: 1800, Mujeres: 2200

        BarDataSet dataSet = new BarDataSet(entries, "Cantidad de Prendas Vendidas");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setStackLabels(new String[]{"Hombres", "Mujeres"});

        BarData barData = new BarData(dataSet);
        chart2.setData(barData);
        chart2.invalidate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // Acción que deseas realizar al hacer clic en la flecha de navegación
            // Por ejemplo, puedes finalizar la actividad actual y regresar a la anterior
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}