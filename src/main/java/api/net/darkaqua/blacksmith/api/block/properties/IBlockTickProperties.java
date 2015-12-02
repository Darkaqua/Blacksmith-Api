package net.darkaqua.blacksmith.api.block.properties;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.blockstate.IBlockVariant;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.api.world.IWorld;

import java.util.Random;

public interface IBlockTickProperties {

	IBlock getBlock();
	
	boolean needsRandomTick();
	
	void randomTick(WorldRef ref, IBlockVariant state, Random rand);
	
	void updateTick(WorldRef ref, IBlockVariant state, Random rand);

	int tickRate(IWorld world);
}
