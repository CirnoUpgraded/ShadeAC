package top.n0rthmaster123.shadeac;

import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.settings.PacketEventsSettings;
import io.github.retrooper.packetevents.utils.server.ServerVersion;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import top.n0rthmaster123.shadeac.ban.BanUtil;
import top.n0rthmaster123.shadeac.check.AlertThemeUtil;
import top.n0rthmaster123.shadeac.check.CheckUtil;
import top.n0rthmaster123.shadeac.check.FailUtil;
import top.n0rthmaster123.shadeac.check.PacketListener;
import top.n0rthmaster123.shadeac.commands.Alerts;
import top.n0rthmaster123.shadeac.commands.Shade;
import top.n0rthmaster123.shadeac.commands.Violations;
import top.n0rthmaster123.shadeac.gui.GUIListener;

public final class ShadeAC extends JavaPlugin {

    public static String prefix = "§8Shade §7>";
    public FileConfiguration config;
    public static String author = "BaGuAr, Mimik";
    public static String version = "unknown";

    long tick;
    static ShadeAC ac;

    public static ShadeAC getPlugin(){
        return ac;
    }

    @Override
    public void onLoad() {
        //okie,,, save current tick!
        tick = System.currentTimeMillis();
        //Setup PacketEvents!
        PacketEvents.create(this);
        PacketEventsSettings settings = PacketEvents.get().getSettings();
        settings
                .fallbackServerVersion( ServerVersion.v_1_8_8 )
                .compatInjector(false)
                .checkForUpdates(false)
                .bStats(true);
        PacketEvents.get().loadAsyncNewThread();
    }

    @Override
    public void onEnable() {
        //Let's setup config!
        saveDefaultConfig();
        config = getConfig();
        //Next, set instance!
        ac = this;
        //Next, setup PacketEvents! ( register listener )
        PacketEvents.get().registerListener( new PacketListener() );
        PacketEvents.get().init();
        //Ok, set plugin version to the value.
        version = this.getDescription().getVersion();
        //OKIE!!!! LET'S SETUP BANUTIL , CHECKUTIL , ALERTTHEME
        BanUtil.setup( this );
        CheckUtil.setup( this );
        AlertThemeUtil.setup();
        //Okie Notify current theme..
        System.out.println( "ShadeAC> Current Theme is " + AlertThemeUtil.getCurrentTheme().name );
        //Okie,,, register Listener ( for GUI )
        getServer().getPluginManager().registerEvents( new GUIListener() , this );
        //Okie,,,!! setups commands!
        getCommand( "shade" ).setExecutor( new Shade() );
        getCommand( "alerts" ).setExecutor( new Alerts() );
        getCommand( "violations" ).setExecutor( new Violations() );
        //END!!! Notify message that mean loaded :3
        System.out.println( "ShadeAC> The plugin is loaded Successfully in " + ( System.currentTimeMillis() - tick ) + "ms. " );
    }

    @Override
    public void onDisable(){
        PacketEvents.get().terminate();
    }
}
