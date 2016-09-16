package net.darkhax.solarvillage.client;

import net.darkhax.solarvillage.SolarVillage;
import net.darkhax.solarvillage.common.ProxyCommon;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ProxyClient extends ProxyCommon {
    
    @Override
    public void onInit () {
    
    }
    
    @Override
    public void onPostInit () {
    
    }
    
    @Override
    public void onPreInit () {
        
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SolarVillage.blockSolarPanel), 0, new ModelResourceLocation(SolarVillage.blockSolarPanel.getRegistryName(), "inventory"));
    }
}