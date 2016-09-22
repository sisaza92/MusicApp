package udea.edu.co.musicapp.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by CristianCamilo on 21/09/2016.
 */
public class ContextProvider extends Application {
    private static ContextProvider instance;

    public ContextProvider() {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }
}
