package br.edu.pdm.cityharm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import br.edu.pdm.cityharm.helper.DatabaseHelper;
import br.edu.pdm.cityharm.principal.PrincipalActivity_;


@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

  @ViewById
  protected ImageButton btnBluetooth;
  @ViewById
  protected ImageButton btnAcelerometro;
  

  @Click({R.id.btnBluetooth, R.id.btnAcelerometro})
  public void onClick(View view) {
    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    switch (view.getId()) {
      case R.id.btnBluetooth:

        Intent it = new Intent(this, PrincipalActivity_.class);
        startActivity(it);
        finish();

        break;

      case R.id.btnAcelerometro:
        Intent it2 = new Intent(this, PrincipalActivity_.class);
        startActivity(it2);
        finish();
        break;
    }
  }
}
