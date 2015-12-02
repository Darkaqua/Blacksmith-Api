package net.darkaqua.blacksmith.mod.world;

import net.darkaqua.blacksmith.api.block.blockstate.IBlockVariant;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.util.Vector3i;
import net.darkaqua.blacksmith.api.world.IWorld;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.world.World;

/**
 * Created by cout970 on 08/11/2015.
 */
public class WorldWrapper implements IWorld{

    private World world;

    public WorldWrapper(World world){
        this.world = world;
    }

    public World getWorld(){
        return world;
    }

    @Override
    public IBlockVariant getBlockState(Vector3i pos) {
        return MCInterface.fromIBlockVariant(world.getBlockState(MCInterface.toBlockPos(pos)));
    }

    @Override
    public ITileEntity getTileEntity(Vector3i position) {
        return MCInterface.fromTileEntity(world.getTileEntity(MCInterface.toBlockPos(position)));
    }
}
