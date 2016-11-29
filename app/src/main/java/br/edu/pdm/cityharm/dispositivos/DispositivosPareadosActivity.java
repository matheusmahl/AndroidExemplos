package br.edu.pdm.cityharm.dispositivos;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.Set;

import br.edu.pdm.cityharm.R;

@EActivity(R.layout.activity_dispositivos_pareados)
public class DispositivosPareadosActivity extends AppCompatActivity {

  @ViewById
  protected ListView listView;

  @AfterViews
  public void Atualizar() {
    BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
    Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();

    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
    listView.setAdapter(adapter);

    if (pairedDevices.size() > 0) {
      for (BluetoothDevice device : pairedDevices) {
        adapter.add(device.getName() + "\n" + device.getAddress());
      }
    }
  }

  @ItemClick({R.id.listView})
  public void onClickItem(String item){
      /*  Extrai nome e endereço a partir do conteúdo do elemento selecionado.
            Nota: position-1 é utilizado pois adicionamos um título à lista e o
        valor de position recebido pelo método é deslocado em uma unidade.
         */
    String devName = item.substring(0, item.indexOf("\n"));
    String devAddress = item.substring(item.indexOf("\n")+1, item.length());

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
}
