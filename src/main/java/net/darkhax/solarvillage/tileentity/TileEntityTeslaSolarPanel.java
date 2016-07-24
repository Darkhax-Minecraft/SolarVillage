package net.darkhax.solarvillage.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

import net.minecraftforge.common.capabilities.Capability;
import net.darkhax.solarvillage.handler.SolarVillageConfig;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.darkhax.tesla.api.ITeslaConsumer;

public class TileEntityTeslaSolarPanel extends TileEntity implements ITickable {
    
    private final SolarTeslaContainer container;
    
    public TileEntityTeslaSolarPanel() {
        
        this.container = new SolarTeslaContainer();
    }
    
    @Override
    public void update () {
        
        if (this.hasWorldObj()) {
            
            if (!this.worldObj.provider.getHasNoSky() && this.worldObj.canBlockSeeSky(this.pos.offset(EnumFacing.UP)) && !this.worldObj.isRaining() && this.worldObj.getSkylightSubtracted() == 0 && this.container.getStoredPower() != this.container.getCapacity())
                this.container.generatePower();
                
            final TileEntity tile = this.getWorld().getTileEntity(this.getPos().offset(EnumFacing.DOWN));
            if (tile != null && !tile.isInvalid() && tile.hasCapability(TeslaCapabilities.CAPABILITY_CONSUMER, EnumFacing.UP))
                this.container.takePower(((ITeslaConsumer) tile.getCapability(TeslaCapabilities.CAPABILITY_CONSUMER, EnumFacing.UP)).givePower(Math.min(this.container.getStoredPower(), SolarVillageConfig.panelTransferRate), false), false);
        }
    }
    
    @Override
    public void readFromNBT (NBTTagCompound compound) {
        
        super.readFromNBT(compound);
        this.container.setPower(compound.getLong("StoredPower"));
    }
    
    @Override
    public NBTTagCompound writeToNBT (NBTTagCompound compound) {
        
        compound.setLong("StoredPower", this.container.getStoredPower());
        return super.writeToNBT(compound);
    }
    
    @Override
    public SPacketUpdateTileEntity getUpdatePacket () {
        
        return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
    }
    
    @Override
    public NBTTagCompound getUpdateTag () {
        
        return this.writeToNBT(new NBTTagCompound());
    }
    
    @Override
    public void onDataPacket (NetworkManager net, SPacketUpdateTileEntity packet) {
        
        super.onDataPacket(net, packet);
        this.readFromNBT(packet.getNbtCompound());
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability (Capability<T> capability, EnumFacing facing) {
        
        if (facing == EnumFacing.DOWN && (capability == TeslaCapabilities.CAPABILITY_PRODUCER || capability == TeslaCapabilities.CAPABILITY_HOLDER))
            return (T) this.container;
            
        return super.getCapability(capability, facing);
    }
    
    @Override
    public boolean hasCapability (Capability<?> capability, EnumFacing facing) {
        
        if (facing == EnumFacing.DOWN && (capability == TeslaCapabilities.CAPABILITY_PRODUCER || capability == TeslaCapabilities.CAPABILITY_HOLDER))
            return true;
            
        return super.hasCapability(capability, facing);
    }
}