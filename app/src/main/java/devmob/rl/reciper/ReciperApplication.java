package devmob.rl.reciper;

import android.app.Application;
import android.util.Log;

import devmob.rl.reciper.database.ReciperDatabase;

public class ReciperApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("DAO", "Connexion");
        ReciperDatabase.initDatabase(getBaseContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ReciperDatabase.disconnectDatabase();
    }
}


