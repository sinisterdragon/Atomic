package indrora.atomic;

import indrora.atomic.model.ColorScheme;
import indrora.atomic.model.Settings;
import indrora.atomic.utils.LatchingValue;
import android.app.Application;

public class App extends Application {

	Atomic atomic;
	public App()
	{
		super();
		
		autoconnectComplete = new LatchingValue<Boolean>(true, false);
	}
	
	private static LatchingValue<Boolean> autoconnectComplete;
	
	public static Boolean doAutoconnect()
	{
		return autoconnectComplete.getValue();
	}
	@Override
	public void onCreate() {
		// Context exists here.
		Atomic.getInstance().loadServers(getApplicationContext());
		
		indrora.atomic.model.Settings _settings = new Settings(this);
		// Release 16 changes things for colors.
		// Later, I plan on having a 
		if(_settings.getColorScheme().equals("monokai") || _settings.getColorScheme().equals("solarized"))
		{
			_settings.setColorScheme("default");
		}
		
		ColorScheme _c = new ColorScheme(getApplicationContext());
		
		super.onCreate();
	}

}
