package net.darkhax.solarvillage.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

import net.minecraftforge.common.capabilities.Capability;

import net.darkhax.tesla.capability.TeslaCapabilities;

public class TileEntityTeslaSolarPanel extends TileEntity implements ITickable {
    
    private final SolarTeslaContainer container;
    
    public TileEntityTeslaSolarPanel() {
        
        this.container = new SolarTeslaContainer();
    }
    
    @Override
    public void update () {
        
        if (this.hasWorldObj() && !this.worldObj.provider.getHasNoSky() && this.worldObj.canBlockSeeSky(this.pos.offset(EnumFacing.UP)) && !this.worldObj.isRaining() && this.worldObj.getSkylightSubtracted() == 0 && this.container.getStoredPower() != this.container.getCapacity())
            this.container.generatePower();
    }
    
    @Override
    public void readFromNBT (NBTTagCompound compound) {
        
        super.readFromNBT(compound);
        this.container.setPower(compound.getLong("StoredPower"));
    }
    
    @Override
    public void writeToNBT (NBTTagCompound compound) {
        
        super.writeToNBT(compound);
        compound.setLong("StoredPower", this.container.getStoredPower());
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