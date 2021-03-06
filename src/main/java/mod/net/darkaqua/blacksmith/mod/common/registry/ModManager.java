package net.darkaqua.blacksmith.mod.common.registry;

import net.darkaqua.blacksmith.api.common.modloader.IModIdentifier;
import net.darkaqua.blacksmith.api.common.registry.IModManager;
import net.darkaqua.blacksmith.mod.common.modloader.ModLoaderManager;

/**
 * Created by cout970 on 03/02/2016.
 */
public class ModManager implements IModManager {

    public static final ModManager INSTANCE = new ModManager();

    private ModManager(){}

    @Override
    public IModIdentifier getIdentifier(Object instance) {
        return ModLoaderManager.getModIdentifier(instance);
    }
}
