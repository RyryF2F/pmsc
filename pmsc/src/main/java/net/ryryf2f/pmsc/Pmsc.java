package net.ryryf2f.pmsc;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.ryryf2f.pmsc.config.ConfigMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Pmsc implements ModInitializer
{

    public static Logger LOGGER = LoggerFactory.getLogger("pmsc");
    public static final ConfigMain CONFIG = AutoConfig.register(ConfigMain.class, GsonConfigSerializer::new).getConfig();
    @Override
    public void onInitialize()
    {

        LOGGER.info("Here we go again...");
        VanillaMobSpawnController.loadVanillaMobSpawns();


    }
}
