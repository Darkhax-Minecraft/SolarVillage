package net.darkhax.solarvillage;

import net.darkhax.solarvillage.block.BlockTeslaSolarPanel;
import net.darkhax.solarvillage.handler.SolarVillageConfig;
import net.darkhax.solarvillage.lib.Constants;
import net.darkhax.solarvillage.tileentity.TileEntityTeslaSolarPanel;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.VERSION_NUMBER, guiFactory = Constants.FACTORY)
public class SolarVillage {
    
    private Block blockSolarPanel;
    private Item itemSolarPanel;
    
    @EventHandler
    public void preInit (FMLPreInitializationEvent event) {
        
    	blockSolarPanel  = new BlockTeslaSolarPanel();
    	itemSolarPanel = new ItemBlock(blockSolarPanel);
    	
    	MinecraftForge.EVENT_BUS.register(this);
        SolarVillageConfig.initConfig(event.getSuggestedConfigurationFile());
    }
    
    @SubscribeEvent
    public void onBlockRegister(RegistryEvent.Register<Block> register) {
    	
    	blockSolarPanel.setRegistryName("panel");
    	register.getRegistry().register(blockSolarPanel);
    	GameRegistry.registerTileEntity(TileEntityTeslaSolarPanel.class, "panel");
    }
    
    @SubscribeEvent
    public void onItemRegister(RegistryEvent.Register<Item> register) {
    	
    	itemSolarPanel.setRegistryName("panel");
    	register.getRegistry().register(itemSolarPanel);
    }
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void modelRegistryEvent (ModelRegistryEvent event) {
    	
    	ModelLoader.setCustomModelResourceLocation(itemSolarPanel, 0, new ModelResourceLocation("solarvillage:panel", "inventory"));
    }
}