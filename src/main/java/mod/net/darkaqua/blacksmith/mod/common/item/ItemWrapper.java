package net.darkaqua.blacksmith.mod.common.item;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.api.common.block.IBlock;
import net.darkaqua.blacksmith.api.client.creativetab.ICreativeTab;
import net.darkaqua.blacksmith.api.common.entity.IEntity;
import net.darkaqua.blacksmith.api.common.entity.IPlayer;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.item.IItem;
import net.darkaqua.blacksmith.api.common.item.IItemDefinition;
import net.darkaqua.blacksmith.api.common.util.Direction;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.api.common.util.WorldRef;
import net.darkaqua.blacksmith.api.common.world.IWorld;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cout970 on 08/11/2015.
 */
public class ItemWrapper implements IItem {

    private Item item;

    public ItemWrapper(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public String getUnlocalizedName() {
        return item.getUnlocalizedName();
    }

    @Override
    public int getMaxStackSize(IItemStack stack) {
        return item.getItemStackLimit(MCInterface.toItemStack(stack));
    }

    @Override
    public void setMaxStackSize(IItemStack stack, int size) {
        item.setMaxStackSize(size);
    }

    @Override
    public int getMaxDamage(IItemStack stack) {
        return item.getMaxDamage(MCInterface.toItemStack(stack));
    }

    @Override
    public void setMaxDamage(IItemStack stack, int max) {
        item.setMaxDamage(max);
    }

    @Override
    public boolean hasSubtypes() {
        return item.getHasSubtypes();
    }

    @Override
    public void setSubtypes(boolean has) {
        item.setHasSubtypes(has);
    }

    @Override
    public IItemStack getContainerItemStack(IItemStack stack) {
        return MCInterface.fromItemStack(item.getContainerItem(MCInterface.toItemStack(stack)));
    }

    @Override
    public void setContainerItemStack(IItemStack stack) {
        item.setContainerItem(MCInterface.toItem(stack.getItem()));
    }

    @Override
    public String getUnlocalizedName(IItemStack stack) {
        return item.getUnlocalizedName(MCInterface.toItemStack(stack));
    }

    @Override
    public void setUnlocalizedName(String name) {
        item.setUnlocalizedName(name);
    }

    @Override
    public String getLocalizedName(IItemStack stack) {
        return item.getItemStackDisplayName(MCInterface.toItemStack(stack));
    }

    @Override
    public ICreativeTab getCreativeTab() {
        return MCInterface.fromCreativeTab(item.getCreativeTab());
    }

    @Override
    public void setCreativeTab(ICreativeTab tab) {
        item.setCreativeTab(MCInterface.fromCreativeTab(tab));
    }

    @Override
    public boolean is3DItem() {
        return item.isFull3D();
    }

    @Override
    public int getMetadata(IItemStack stack) {
        return item.getMetadata(MCInterface.toItemStack(stack));
    }

    @Override
    public int getDamage(IItemStack stack) {
        return item.getDamage(MCInterface.toItemStack(stack));
    }

    @Override
    public boolean isDamageable() {
        return item.isDamageable();
    }

    @Override
    public boolean shouldRotateWhenRenderingInHand() {
        return item.shouldRotateAroundWhenRendering();
    }

    @Override
    public Color getColorFromItemStack(IItemStack stack, int renderPass) {
        return new Color(item.getColorFromItemStack(MCInterface.toItemStack(stack), renderPass));
    }

    @Override
    public int getItemUseMaxDuration(IItemStack stack) {
        return item.getMaxItemUseDuration(MCInterface.toItemStack(stack));
    }

    @Override
    public boolean isTool(IItemStack stack) {
        return item.isItemTool(MCInterface.toItemStack(stack));
    }

    @Override
    public int getItemEnchantability(IItemStack stack) {
        return item.getItemEnchantability(MCInterface.toItemStack(stack));
    }

    @Override
    public void getSubItems(IItem item1, ICreativeTab tab, List<IItemStack> subItems) {
        List<ItemStack> list = Lists.newArrayList();
        item.getSubItems(MCInterface.toItem(item1), MCInterface.fromCreativeTab(tab), list);
        subItems.addAll(list.stream().map(MCInterface::fromItemStack).collect(Collectors.toList()));
    }

    @Override
    public boolean canItemModifyBlocks() {
        return item.canItemEditBlocks();
    }

    @Override
    public boolean isRepairable(IItemStack toRepair, IItemStack repair) {
        return item.getIsRepairable(MCInterface.toItemStack(toRepair), MCInterface.toItemStack(repair));
    }

    @Override
    public int getEntityLifespan(IItemStack itemStack, IWorld world) {
        return item.getEntityLifespan(MCInterface.toItemStack(itemStack), MCInterface.toWorld(world));
    }


    @Override
    public boolean onItemUse(IItemStack stack, IPlayer player, WorldRef ref, Direction side, Vect3d hit) {
        return item.onItemUse(MCInterface.toItemStack(stack), MCInterface.fromPlayer(player),
                MCInterface.toWorld(ref.getWorld()), MCInterface.toBlockPos(ref.getPosition()),
                MCInterface.toEnumFacing(side), (float) hit.getX(), (float) hit.getY(), (float) hit.getZ());
    }

    @Override
    public float getStrengthVsBlock(IItemStack stack, IBlock block) {
        return item.getStrVsBlock(MCInterface.toItemStack(stack), MCInterface.toBlock(block));
    }

    @Override
    public IItemStack onItemRightClick(IItemStack stack, IWorld world, IPlayer player) {
        return MCInterface.fromItemStack(item.onItemRightClick(MCInterface.toItemStack(stack), MCInterface.toWorld(world), MCInterface.fromPlayer(player)));
    }

    @Override
    public IItemStack onPlayerEndsUsing(IItemStack stack, IWorld world, IPlayer player) {
        return MCInterface.fromItemStack(item.onItemUseFinish(MCInterface.toItemStack(stack), MCInterface.toWorld(world), MCInterface.fromPlayer(player)));
    }

    @Override
    public void onPlayerStoppedUsing(IItemStack stack, IWorld world, IPlayer player, int timeLeft) {
        item.onPlayerStoppedUsing(MCInterface.toItemStack(stack), MCInterface.toWorld(world), MCInterface.fromPlayer(player), timeLeft);
    }

    @Override
    public boolean hitEntity(IItemStack stack, IEntity target, IEntity attacker) {
        return item.hitEntity(MCInterface.toItemStack(stack), (EntityLivingBase) MCInterface.toEntity(target), (EntityLivingBase) MCInterface.toEntity(attacker));
    }

    @Override
    public boolean onBlockDestroyed(IItemStack stack, WorldRef ref, IBlock block, IEntity player) {
        return item.onBlockDestroyed(MCInterface.toItemStack(stack), MCInterface.toWorld(ref.getWorld()), MCInterface.toBlock(block), MCInterface.toBlockPos(ref.getPosition()), (EntityLivingBase) MCInterface.toEntity(player));
    }

    @Override
    public boolean canHarvestBlock(IItemStack stack, IBlock block) {
        return item.canHarvestBlock(MCInterface.toBlock(block), MCInterface.toItemStack(stack));
    }

    @Override
    public boolean canInteractWithEntity(IItemStack stack, IPlayer player, IEntity target) {
        return item.itemInteractionForEntity(MCInterface.toItemStack(stack), MCInterface.fromPlayer(player), (EntityLivingBase) MCInterface.toEntity(target));
    }

    @Override
    public void onUpdate(IItemStack stack, IWorld world, IEntity entity, int itemSlot, boolean isSelected) {
        item.onUpdate(MCInterface.toItemStack(stack), MCInterface.toWorld(world), MCInterface.toEntity(entity), itemSlot, isSelected);
    }

    @Override
    public void onCreated(IItemStack stack, IWorld world, IPlayer player) {
        item.onCreated(MCInterface.toItemStack(stack), MCInterface.toWorld(world), MCInterface.fromPlayer(player));
    }

    @Override
    public void addInformation(IItemStack stack, IPlayer player, List tooltip, boolean advanced) {
        item.addInformation(MCInterface.toItemStack(stack), MCInterface.fromPlayer(player), tooltip, advanced);
    }

    @Override
    public IItemDefinition getItemDefinition() {
        if (item instanceof BS_Item) {
            return ((BS_Item) item).getItemDefinition();
        }
        return null;
    }

    @Override
    public Object getInternalItem() {
        return item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemWrapper)) return false;

        ItemWrapper that = (ItemWrapper) o;

        return !(item != null ? !item.equals(that.item) : that.item != null);

    }

    @Override
    public int hashCode() {
        return item != null ? item.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ItemWrapper{" +
                "item=" + item +
                '}';
    }
}
