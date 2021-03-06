package net.darkaqua.blacksmith.api.common.registry;

import net.darkaqua.blacksmith.api.common.item.IItem;
import net.darkaqua.blacksmith.api.common.item.IItemDefinition;

import java.util.List;

/**
 * Created by cout970 on 19/12/2015.
 */
public interface IItemRegistry {

    IItem registerItemDefinition(IItemDefinition definition, String identifier);

    List<IItem> getRegisteredItems();

    List<IItemDefinition> getRegisteredItemDefinitions();

    IItem findItem(String domain, String name);

    String getItemDomain(IItem item);

    String getItemName(IItem item);
}
