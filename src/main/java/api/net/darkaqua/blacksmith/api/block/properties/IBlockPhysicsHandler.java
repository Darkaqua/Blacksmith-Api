package net.darkaqua.blacksmith.api.block.properties;

import java.util.List;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IIBlockState;
import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.Vector3i;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.api.world.IIBlockAccess;

public interface IBlockPhysicsHandler {

	IBlock getBlock();
	
	boolean isAir(IIBlockAccess world, Vector3i pos);
	
	boolean isSolidBlock();
	
	boolean isNormalBlock();
	
	boolean isFullBlock();
	
	boolean isCompleteBlock();
	
	boolean isSideSolid(IIBlockAccess world, Vector3i pos, Direction side);
	
	boolean isTraspasable(IIBlockAccess world, Vector3i pos);
	
	boolean canCollide(IIBlockState state, boolean hitIfIsLiquid);
	
	void setBlockBounds(Cube blockBounds);
	
	Cube getBlockBounds();
	
	Cube getColisionBox(WorldRef ref, IIBlockState state);

	Cube getSelectionBox(WorldRef ref);
	
	List<Cube> getCollidingCubes(WorldRef ref, IIBlockState state, Cube mask, IEntity entity);
}
