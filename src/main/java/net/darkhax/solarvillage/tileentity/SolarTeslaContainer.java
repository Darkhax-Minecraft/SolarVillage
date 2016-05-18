package net.darkhax.solarvillage.tileentity;

import net.darkhax.solarvillage.handler.SolarVillageConfig;
import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.api.ITeslaProducer;

public class SolarTeslaContainer implements ITeslaHolder, ITeslaProducer {
    
    /**
     * The power stored by the solar container.
     */
    private long storedPower = 0;
    
    @Override
    public long takePower (long tesla, boolean simulated) {
        
        final long removedPower = Math.min(this.storedPower, Math.min(SolarVillageConfig.panelTransferRate, tesla));
        
        if (!simulated)
            this.storedPower -= removedPower;
            
        return removedPower;
    }
    
    @Override
    public long getStoredPower () {
        
        return this.storedPower;
    }
    
    @Override
    public long getCapacity () {
        
        return SolarVillageConfig.panelCapacity;
    }
    
    public void generatePower () {
        
        this.storedPower += SolarVillageConfig.panelPowerGen;
        
        if (this.storedPower > this.getCapacity())
            this.storedPower = this.getCapacity();
    }
}
