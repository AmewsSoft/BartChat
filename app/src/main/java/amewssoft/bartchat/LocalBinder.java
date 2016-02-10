package amewssoft.bartchat;

import android.os.Binder;
import java.lang.ref.WeakReference;

public class LocalBinder extends Binder
{

    private final WeakReference mService;

    public LocalBinder(Object obj)
    {
        mService = new WeakReference(obj);
    }

    public Object getService()
    {
        return mService.get();
    }
}