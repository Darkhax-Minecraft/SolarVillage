package net.darkhax.solarvillage;

import net.darkhax.solarvillage.block.BlockTeslaSolarPanel;
import net.darkhax.solarvillage.common.ProxyCommon;
import net.darkhax.solarvillage.handler.SolarVillageConfig;
import net.darkhax.solarvillage.lib.Constants;
import net.darkhax.solarvillage.tileentity.TileEntityTeslaSolarPanel;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.VERSION_NUMBER, guiFactory = Constants.FACTORY)
public class SolarVillage {
    
    @SidedProxy(serverSide = Constants.PROXY_COMMON, clientSide = Constants.PROXY_CLIENT)
    public static ProxyCommon proxy;
    
    @Instance(Constants.MOD_ID)
    public static SolarVillage instance;
    
    public static Block blockSolarPanel;
    
    @EventHandler
    public void preInit (FMLPreInitializationEvent event) {
        
        blockSolarPanel = this.registerBlock(new BlockTeslaSolarPanel(), "panel");
        GameRegistry.registerTileEntity(TileEntityTeslaSolarPanel.class, "panel");
        SolarVillageConfig.initConfig(event.getSuggestedConfigurationFile());
        proxy.onPreInit();
    }
    
    private Block registerBlock (Block block, String ID) {
        
        block.setRegistryName(ID);
        GameRegistry.register(block);
        GameRegistry.register(new ItemBlock(block), block.getRegistryName());
        return block;
    }
}