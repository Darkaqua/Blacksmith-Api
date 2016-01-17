package net.darkaqua.blacksmith.mod.network;

import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.network.INetworkContext;
import net.darkaqua.blacksmith.api.world.IWorld;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.DimensionManager;

/**
 * Created by cout970 on 17/01/2016.
 */
public class NetworkClientContext implements INetworkContext.IClientContext {

    @Override
    public IPlayer getPlayer() {
        return MCInterface.toPlayer(Minecraft.getMinecraft().thePlayer);
    }

    @Override
    public IWorld getWorld() {
        return MCInterface.fromWorld(Minecraft.getMinecraft().theWorld);
    }

    @Override
    public IWorld getWorld(int dimension) {
        return MCInterface.fromWorld(DimensionManager.getWorld(dimension));
    }
}
