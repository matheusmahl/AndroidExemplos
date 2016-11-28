package br.edu.pdm.cityharm.helper;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

import br.edu.pdm.cityharm.model.Usuario;


/**
 * DatabaseConfigUtl writes a configuration file to avoid using annotation processing in runtime which is very slow
 * under Android. This gains a noticeable performance improvement.
 * <p/>
 * The configuration file is written to /res/raw/ by default. More info at: http://ormlite.com/docs/table-config
 */
public class DatabaseConfigUtil extends OrmLiteConfigUtil {
  private static final Class<?>[] classes = new Class[]{
          Usuario.class,
  };

  public static void main(String[] args) throws SQLException, IOException {
    writeConfigFile("ormlite_config.txt", classes);
  }
}