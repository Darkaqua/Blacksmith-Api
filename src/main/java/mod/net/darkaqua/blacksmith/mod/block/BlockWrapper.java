package net.darkaqua.blacksmith.mod.block;

import com.google.common.base.Predicate;
import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.creativetab.ICreativeTab;
import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.entity.ILivingEntity;
import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.Block;

public class BlockWrapper implements IBlock {
	
    private Block block;

    public BlockWrapper(Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }

    @Override
    public String getUnlocalizedName() {
        return block.getUnlocalizedName();
    }

    @Override
    public String getLocalizedName() {
        return block.getLocalizedName();
    }

    @Override
    public Cube getBlockBounds() {
        return new Cube(block.getBlockBoundsMinX(), block.getBlockBoundsMinY(), block.getBlockBoundsMinZ(),
                block.getBlockBoundsMaxX(), block.getBlockBoundsMaxY(), block.getBlockBoundsMaxZ());
    }

    @Override
    public float getHardness() {
        //TODO
        return block.getBlockHardness(null, null);
    }

    @Override
    public float getLightEmitted() {
        return block.getLightValue();
    }

    @Override
    public float getLightOpacity() {
        return block.getLightOpacity();
    }

    @Override
    public float getResistance() {
        //TODO
        return block.getExplosionResistance(null);
    }


    @Override
    public IItem getItemBlock() {
        return MCInterface.fromItem(net.minecraft.item.Item.getItemFromBlock(block));
    }

    @Override
    public IBlockDefinition getBlockDefinition() {
        if (block instanceof BS_Block) {
            return ((BS_Block) block).getBlockDefinition();
        }
        return null;
    }

    @Override
    public boolean isOpaque() {
        return block.isOpaqueCube();
    }

	@Override
	public IBlockData getDefaultBlockData() {
		return MCInterface.fromIBlockState(block.getDefaultState());
	}

    @Override
    public IBlockData getVariantFromMeta(int meta) {
        return MCInterface.fromIBlockState(block.getDefaultState());
    }

    @Override
    public int getMetaFromVariant(IBlockData variant) {
        return block.getMetaFromState(MCInterface.toIBlockState(variant));
    }

    @Override
    public ICreativeTab getCreativeTab() {
        return MCInterface.fromCreativeTab(block.getCreativeTabToDisplayOn());
    }

    @Override
    public boolean canBeReplacedByOreGen(WorldRef ref, final Predicate<IBlockData> target) {
        return block.isReplaceableOreGen(MCInterface.toWorld(ref.getWorld()), MCInterface.toBlockPos(ref.getPosition()), input -> target.apply(MCInterface.fromIBlockState(input)));
    }

    @Override
    public Object getInternalBlock() {
        return block;
    }

    @Override
    public boolean onActivated(WorldRef ref, IBlockData state, IPlayer player, Direction side, Vect3d v) {
        return block.onBlockActivated(MCInterface.toWorld(ref.getWorld()), MCInterface.toBlockPos(ref.getPosition()),
                MCInterface.toIBlockState(state), MCInterface.fromPlayer(player), MCInterface.toEnumFacing(side), (float) v.getX(), (float) v.getY(), (float) v.getZ());
    }

    @Override
    public void onAdded(WorldRef ref, IBlockData state) {
        block.onBlockAdded(MCInterface.toWorld(ref.getWorld()), MCInterface.toBlockPos(ref.getPosition()), MCInterface.toIBlockState(state));
    }

    @Override
    public void onBreaks(WorldRef ref, IBlockData state) {
        block.breakBlock(MCInterface.toWorld(ref.getWorld()), MCInterface.toBlockPos(ref.getPosition()), MCInterface.toIBlockState(state));
    }

    @Override
    public void onClicked(WorldRef ref, IPlayer player) {
        block.onBlockClicked(MCInterface.toWorld(ref.getWorld()), MCInterface.toBlockPos(ref.getPosition()), MCInterface.fromPlayer(player));
    }

    @Override
    public void onEntityCollided(WorldRef ref, IEntity entity) {
        block.onEntityCollidedWithBlock(MCInterface.toWorld(ref.getWorld()), MCInterface.toBlockPos(ref.getPosition()), MCInterface.toEntity(entity));
    }

    @Override
    public void onHarvested(WorldRef ref, IBlockData variant, IPlayer player) {
        block.onBlockHarvested(MCInterface.toWorld(ref.getWorld()), MCInterface.toBlockPos(ref.getPosition()), MCInterface.toIBlockState(variant), MCInterface.fromPlayer(player));
    }

    @Override
    public void onNeighborBlockChange(WorldRef ref, IBlockData variant, IBlock neighbor) {
        block.onNeighborBlockChange(MCInterface.toWorld(ref.getWorld()), MCInterface.toBlockPos(ref.getPosition()), MCInterface.toIBlockState(variant), MCInterface.toBlock(neighbor));
    }

    @Override
    public IBlockData onPlaced(WorldRef ref, Direction side, ILivingEntity entity, Vect3d hit, int metadata) {
        return MCInterface.fromIBlockState(block.onBlockPlaced(MCInterface.toWorld(ref.getWorld()), MCInterface.toBlockPos(ref.getPosition()), MCInterface.toEnumFacing(side), (float) hit.getX(), (float) hit.getY(), (float) hit.getZ(), metadata, MCInterface.fromLivingEntity(entity)));
    }

    @Override
    public void onPlacedBy(WorldRef ref, IBlockData state, ILivingEntity placer, IItemStack stack) {
        block.onBlockPlacedBy(MCInterface.toWorld(ref.getWorld()), MCInterface.toBlockPos(ref.getPosition()), MCInterface.toIBlockState(state), MCInterface.fromLivingEntity(placer), MCInterface.toItemStack(stack));
    }

    @Override
    public boolean onRemovedByPlayer(WorldRef ref, IPlayer player, boolean willHarvest) {
        return block.removedByPlayer(MCInterface.toWorld(ref.getWorld()), MCInterface.toBlockPos(ref.getPosition()), MCInterface.fromPlayer(player), willHarvest);
    }

    //TODO add more methods


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockWrapper)) return false;

        BlockWrapper that = (BlockWrapper) o;

        return !(block != null ? !block.equals(that.block) : that.block != null);

    }

    @Override
    public int hashCode() {
        return block != null ? block.hashCode() : 0;
    }
}
