package net.darkhax.solarvillage;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import net.darkhax.solarvillage.lib.Constants;

public class CreativeTabTeslaFlux extends CreativeTabs {
    
    public CreativeTabTeslaFlux() {
        
        super(Constants.MOD_ID);
    }
    
    @Override
    public Item getTabIconItem () {
        
        return null;
    }
}