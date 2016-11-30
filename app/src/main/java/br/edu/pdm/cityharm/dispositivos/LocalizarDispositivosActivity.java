package br.edu.pdm.cityharm.dispositivos;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.Set;

import br.edu.pdm.cityharm.R;

@EActivity(R.layout.activity_localizar_dispositivos)
public class LocalizarDispositivosActivity extends AppCompatActivity {

  private ArrayAdapter<String> adapter;

  @ViewById
  ListView listView2;

  @AfterViews
  public void Atualizar() {

    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
    listView2.setAdapter(adapter);

    IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
    registerReceiver(receiver, filter);

    BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
    Log.d("Discovery - ","" + btAdapter.startDiscovery());
  }

  @ItemClick({R.id.listView2})
  public void onClickItem(String item) {
      /*  Extrai nome e endereço a partir do conteúdo do elemento selecionado.
            Nota: position-1 é utilizado pois adicionamos um título à lista e o
        valor de position recebido pelo método é deslocado em uma unidade.
         */
    String devName = item.substring(0, item.indexOf("\n"));
    String devAddress = item.substring(item.indexOf("\n") + 1, item.length());

        /*  Utiliza um Intent para encapsular as informações de nome e endereço.
            Informa à Activity principal que tudo foi um sucesso!
            Finaliza e retorna à Activity principal.
         */
    Intent returnIntent = new Intent();
    returnIntent.putExtra("btDevName", devName);
    returnIntent.putExtra("btDevAddress", devAddress);
    setResult(RESULT_OK, returnIntent);
    finish();

  }

  private final BroadcastReceiver receiver = new BroadcastReceiver() {

    /*  Este método é executado sempre que um novo dispositivo for descoberto.
     */
    @Override
    public void onReceive(Context context, Intent intent) {

            /*  Obtem o Intent que gerou a ação.
                Verifica se a ação corresponde à descoberta de um novo dispositivo.
                Obtem um objeto que representa o dispositivo Bluetooth descoberto.
                Exibe seu nome e endereço na lista.
             */
      String action = intent.getAction();
      if (BluetoothDevice.ACTION_FOUND.equals(action)) {
        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        adapter.add(device.getName() + "\n" + device.getAddress());
        adapter.notifyDataSetChanged();
      }
    }
  };


  @Override
  public void onDestroy(){
    super.onDestroy();
    unregisterReceiver(receiver);
  }

}
