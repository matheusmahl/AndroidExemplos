package br.edu.pdm.cityharm;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_acelerometro)
public class AcelerometroActivity extends AppCompatActivity {

  Sensor accelerometer;
  SensorManager sensorManager;

  @ViewById
  TextView textView2;

  @ViewById
  Button btnBuscarCoordenadas;

  @AfterViews
  public void Atualizar() {
    sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    //sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
  }

}
