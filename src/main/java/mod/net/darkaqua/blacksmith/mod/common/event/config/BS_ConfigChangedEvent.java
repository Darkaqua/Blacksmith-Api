package net.darkaqua.blacksmith.mod.common.event.config;

import net.darkaqua.blacksmith.api.common.event.config.IConfigChangedEvent;
import net.darkaqua.blacksmith.mod.common.event.BaseEvent;
import net.darkaqua.blacksmith.mod.common.exceptions.BlacksmithInternalException;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;

/**
 * Created by cout970 on 06/12/2015.
 */
public class BS_ConfigChangedEvent extends BaseEvent implements IConfigChangedEvent {

    private ConfigChangedEvent.OnConfigChangedEvent event;

    public BS_ConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event == null)
            throw new BlacksmithInternalException("Error trying to newCreativeTab a ConfigChangedEvent");
        this.event = event;
    }

    @Override
    public String getModID() {
        return event.modID;
    }

    @Override
    public boolean isWorldRunning() {
        return event.isWorldRunning;
    }

    @Override
    public boolean requiresMcRestart() {
        return event.isWorldRunning;
    }

    @Override
    public String getConfigID() {
        return event.configID;
    }

    @Override
    public Object getForgeEvent() {
        return event;
    }
}
