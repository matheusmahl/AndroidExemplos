package br.edu.pdm.cityharm.principal;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.Trace;
import org.androidannotations.annotations.ViewById;

import br.edu.pdm.cityharm.R;
import br.edu.pdm.cityharm.dispositivos.DispositivosPareadosActivity_;
import br.edu.pdm.cityharm.dispositivos.LocalizarDispositivosActivity_;

@EActivity(R.layout.activity_principal)
public class PrincipalActivity extends AppCompatActivity {

  private static final int ENABLE_BLUETOOTH = 1;
  private static final int SELECT_PAIRED_DEVICE = 2;
  private static final int SELECT_DISCOVERED_DEVICE = 3;

  @ViewById
  protected Button btnAbreComunicacao;

  @ViewById
  protected Button btnLocalizarDispositivos;

  @ViewById
  protected Button btnDispositivosPareados;

  @ViewById
  protected TextView textView;

  @AfterViews
  void initBookmarkList() {

  }

  @Click({R.id.btnAbreComunicacao})
  public void onClick(View view) {
    textView.setText("");
    BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
    if (btAdapter == null) {
      textView.append("Que pena! Hardware Bluetooth não está funcionando :(\n");
    } else {
      textView.append("Ótimo! Hardware Bluetooth está funcionando :)\n");
    }

    if (!btAdapter.isEnabled()) {
      Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
      startActivityForResult(enableBtIntent, ENABLE_BLUETOOTH);
      textView.append("Solicitando ativação do Bluetooth...\n");
    } else {
      textView.append("Bluetooth já ativado :)\n");
    }
  }

  @Click({R.id.btnLocalizarDispositivos})
  public void onClickLocalizar(View view) {
    Intent searchPairedDevicesIntent = new Intent(this, LocalizarDispositivosActivity_.class);
    startActivityForResult(searchPairedDevicesIntent, SELECT_DISCOVERED_DEVICE);
  }

  @Click({R.id.btnDispositivosPareados})
  public void onClickDispositivosPareados(View view) {
    Intent searchPairedDevicesIntent = new Intent(this, DispositivosPareadosActivity_.class);
    startActivityForResult(searchPairedDevicesIntent, SELECT_PAIRED_DEVICE);
  }

  @OnActivityResult(SELECT_PAIRED_DEVICE)
  void onResultDispositivosPareados(int resultCode, Intent data) {
    if (resultCode == RESULT_OK) {
      textView.append("Você selecionou " + data.getStringExtra("btDevName") + "\n" + data.getStringExtra("btDevAddress") + "\n");
    } else {
      textView.append("Nenhum dispositivo selecionado :(\n");
    }
  }

  @OnActivityResult(SELECT_DISCOVERED_DEVICE)
  void onResultDispositivosDescobertos(int resultCode, Intent data) {
    if (resultCode == RESULT_OK) {
      textView.append("Você selecionou " + data.getStringExtra("btDevName") + "\n" + data.getStringExtra("btDevAddress") + "\n");
    } else {
      textView.append("Nenhum dispositivo selecionado :(\n");
    }
  }

}
