package techeart.blackout;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

@Mod(modid = MainClass.MOD_ID, name = MainClass.MOD_NAME, version = MainClass.MOD_VERSION)
public class MainClass
{
    public static final String MOD_ID = "blackout";
    public static final String MOD_NAME = "Blackout";
    public static final String MOD_VERSION = "1.0.0";
    public static final String MC_VERSION = "1.12.2";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        initConfig(event);
    }

    private void initConfig(FMLPreInitializationEvent event)
    {
        File configFile = new File(event.getModConfigurationDirectory().getPath());
        configFile.mkdirs();
        Config.init(new File(configFile.getPath(), MOD_ID + ".cfg"));
    }
}
