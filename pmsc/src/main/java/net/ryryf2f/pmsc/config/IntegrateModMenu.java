package net.ryryf2f.pmsc.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.gui.screen.Screen;

/**
 * This class calls the ModMenuAPI in order to register the Config in ModMenu
 */
public class IntegrateModMenu implements ModMenuApi
{
    @Override
    public ConfigScreenFactory<Screen> getModConfigScreenFactory()
    {
        return screen -> AutoConfig.getConfigScreen(ConfigMain.class, screen).get();
    }

}
