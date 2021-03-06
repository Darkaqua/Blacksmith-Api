package net.darkaqua.blacksmith.mod.common.block;

import net.darkaqua.blacksmith.api.common.block.IBlockContainerDefinition;
import net.darkaqua.blacksmith.api.common.tileentity.ITileEntityDefinition;
import net.darkaqua.blacksmith.mod.common.registry.TileEntityRegistry;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * Created by cout970 on 08/11/2015.
 */
public class BS_BlockContainer extends BS_Block implements ITileEntityProvider {

    public BS_BlockContainer(IBlockContainerDefinition def) {
        super(def);
        this.isBlockContainer = true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        ITileEntityDefinition tile = ((IBlockContainerDefinition) definition).createTileEntity(MCInterface.fromWorld(worldIn), MCInterface.fromBlockState(getStateFromMeta(meta)));
        return MCInterface.toTileEntity(TileEntityRegistry.INSTANCE.createTileEntity(tile));
    }

    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        super.breakBlock(worldIn, pos, state);
        worldIn.removeTileEntity(pos);
    }

    public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {
        super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity != null && tileentity.receiveClientEvent(eventID, eventParam);
    }
}
