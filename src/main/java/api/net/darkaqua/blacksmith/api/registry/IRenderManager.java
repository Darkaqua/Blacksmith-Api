package net.darkaqua.blacksmith.api.registry;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.render.model.IPartIdentifier;
import net.darkaqua.blacksmith.api.render.model.RenderPlace;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.util.WorldRef;

import java.util.List;

/**
 * Created by cout970 on 20/12/2015.
 */
public interface IRenderManager {

    void renderItemStack(IItemStack stack, Vect3d pos, RenderPlace place);

    void bindTexture(ResourceReference resourceReference);

    void renderModelPartsDynamicLight(List<IPartIdentifier> parts);

    void renderModelPartsStaticLight(List<IPartIdentifier> parts, WorldRef ref, Vect3d offset);

    void bindBlocksTexture();

    void bindItemsTexture();
}
