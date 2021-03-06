package net.darkaqua.blacksmith.api.common.inventory;

import net.darkaqua.blacksmith.api.common.block.IBlock;
import net.darkaqua.blacksmith.api.common.item.IItem;
import net.darkaqua.blacksmith.api.common.storage.IDataCompound;

public abstract class ItemStackFactory {

    protected static ItemStackFactory INSTANCE;

    public static IItemStack createItemStack(IItemStack stack, int amount) {
        return createItemStack(stack.getItem(), amount, stack.getDamage());
    }

    public static IItemStack createItemStack(IItem item) {
        return INSTANCE.newItemStack(item, 1, 0);
    }

    public static IItemStack createItemStack(IItem item, int amount) {
        return INSTANCE.newItemStack(item, amount, 0);
    }

    public static IItemStack createItemStack(IItem item, int amount, int metadata) {
        return INSTANCE.newItemStack(item, amount, metadata);
    }

    public static IItemStack createItemStack(IBlock block) {
        return INSTANCE.newItemStack(block, 1, 0);
    }

    public static IItemStack createItemStack(IBlock block, int amount) {
        return INSTANCE.newItemStack(block, amount, 0);
    }

    public static IItemStack createItemStack(IBlock block, int amount, int metadata) {
        return INSTANCE.newItemStack(block, amount, metadata);
    }

    public static IItemStack loadItemStack(IDataCompound data) {
        return INSTANCE.load(data);
    }

    public static void saveItemStack(IDataCompound data, IItemStack stack) {
        INSTANCE.save(data, stack);
    }

    protected abstract IItemStack load(IDataCompound data);

    protected abstract void save(IDataCompound data, IItemStack stack);

    protected abstract IItemStack newItemStack(IItem item, int amount, int metadata);

    protected abstract IItemStack newItemStack(IBlock block, int amount, int metadata);
}
