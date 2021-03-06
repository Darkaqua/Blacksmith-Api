package net.darkaqua.blacksmith.api.common.tileentity;

import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.storage.IDataCompound;
import net.darkaqua.blacksmith.api.common.util.raytrace.Cube;
import net.darkaqua.blacksmith.api.common.util.WorldRef;
import net.darkaqua.blacksmith.api.common.util.annotations.Implementable;

/**
 * Created by cout970 on 13/11/2015.
 */
@Implementable
public interface ITileEntityDefinition {

    ITileEntity getParent();

    void bindParent(ITileEntity parent);

    default void onLoad(){}

    default void onDelete(){}

    default void update(){}

    default void loadData(IDataCompound tag){};

    default void saveData(IDataCompound tag){}

    default IDataCompound getUpdateData(){
        return null;
    }

    default void onUpdateDataArrives(IDataCompound data){}

    default void onChunkUnload(){}

    default boolean shouldRecreate(WorldRef ref, IBlockData oldState, IBlockData newSate){
        return true;
    }

    default void onBlockChange(){}

    default void onClientDataArrive(int id, int data){}

    default double getRenderDistance(){
        return 64;
    }

    default Cube getRenderBox(){
        return Cube.fullBlock().translate(getParent().getWorldRef().getPosition().toVect3d());
    }
}
