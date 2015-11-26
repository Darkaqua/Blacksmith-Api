package net.darkaqua.blacksmith.mod.event.modloader;

import net.darkaqua.blacksmith.api.event.modloader.IPreInitEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

/**
 * Created by cout970 on 07/11/2015.
 */
public class PreInitEvent implements IPreInitEvent{

    private FMLPreInitializationEvent event;

    public PreInitEvent(FMLPreInitializationEvent event) {
        this.event = event;
    }

    @Override
    public boolean isEventCancelable() {
        return false;
    }

    @Override
    public void setEventCanceled(boolean value) {}

    @Override
    public boolean isEventCanceled() {
        return false;
    }

    @Override
    public boolean hasEventResult() {
        return false;
    }

    @Override
    public EventResult getEventResult() {
        return EventResult.DEFAULT;
    }

    @Override
    public Object getForgeEvent() {
        return event;
    }

    @Override
    public File getModConfigurationDirectory() {
        return event.getModConfigurationDirectory();
    }

    @Override
    public File getSuggestedConfigurationFile() {
        return event.getSuggestedConfigurationFile();
    }

    @Override
    public File getSourceFile() {
        return event.getSourceFile();
    }
}