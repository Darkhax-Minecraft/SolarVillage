package net.darkhax.solarvillage.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.darkhax.solarvillage.handler.SolarVillageConfig;
import net.darkhax.solarvillage.lib.Constants;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class GuiConfigSolarVillage extends GuiConfig {
    
    private static Configuration cfg = SolarVillageConfig.config;
    
    public GuiConfigSolarVillage(GuiScreen parent) {
        
        super(parent, generateConfigList(), Constants.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(cfg.toString()));
    }
    
    /**
     * Generates a list of configuration options to be displayed in forge's configuration GUI.
     * 
     * @return List<IConfigElement> A list of IConfigElement which are used to populate forge's
     *         configuration GUI.
     */
    public static List<IConfigElement> generateConfigList () {
        
        final ArrayList<IConfigElement> elements = new ArrayList<IConfigElement>();
        
        for (final String name : cfg.getCategoryNames())
            elements.add(new ConfigElement(cfg.getCategory(name)));
            
        return elements;
    }
}
