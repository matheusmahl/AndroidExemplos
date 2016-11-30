package br.edu.pdm.cityharm;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_acelerometro)
public class AcelerometroActivity extends AppCompatActivity {

  Sensor accelerometer;
  SensorManager sensorManager;
  float sensorX;
  float sensorZ;
  float sensorY;
  float sensorA;

  @ViewById
  TextView textView2;

  @ViewById
  Button btnBuscarCoordenadas;

  @AfterViews
  public void Atualizar() {
    sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    SensorEventListener sensorEventListener = new SensorEventListener() {
      @Override
      public void onSensorChanged(SensorEvent event) {
        sensorX = event.values[0];
        sensorY = event.values[1];
        sensorZ = event.values[2];
        sensorA = (event.values[0] + event.values[1] + event.values[2]);
      }

      @Override
      public void onAccuracyChanged(Sensor sensor, int accuracy) {

      }
    };

    sensorManager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
  }

  @Click({R.id.btnBuscarCoordenadas})
  public void onClickBuscarCoordenadas(){
    textView2.setText("");
    textView2.append("X: " + (sensorX));
    textView2.append("\nY: " + (sensorY));
    textView2.append("\nZ: " + (sensorZ));
    textView2.append("\nA: " + (sensorA));
  }

}
