package amewssoft.bartchat;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.IBinder;

import org.jivesoftware.smack.chat.Chat;

// Referenced classes of package amewssoft.bartchat:
//            LocalBinder, MyXMPP

public class MyService extends Service
{

    private static final String DOMAIN = "xmpp.jp";
    private static final String PASSWORD = "password";
    public static boolean ServerchatCreated = false;
    private static final String USERNAME = "khushi";
    public static ConnectivityManager cm;
    public static MyXMPP xmpp;
    public Chat chat;
    String text;

    public MyService()
    {
        text = "";
    }

    public static boolean isNetworkConnected()
    {
        return cm.getActiveNetworkInfo() != null;
    }

    public IBinder onBind(Intent intent)
    {
        return new LocalBinder(this);
    }

    public void onCreate()
    {
        super.onCreate();
        cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        xmpp = MyXMPP.getInstance(this, "xmpp.jp", "khushi", "password");
        xmpp.connect("onCreate");
    }

    public void onDestroy()
    {
        super.onDestroy();
        MyXMPP.connection.disconnect();
    }

    public int onStartCommand(Intent intent, int i, int j)
    {
        return 2;
    }

    public boolean onUnbind(Intent intent)
    {
        return super.onUnbind(intent);
    }

    static
    {
        ServerchatCreated = false;
    }
}