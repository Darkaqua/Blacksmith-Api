package net.darkaqua.blacksmith.mod.common.entity;

import net.darkaqua.blacksmith.api.common.entity.IEntity;
import net.darkaqua.blacksmith.api.common.storage.IDataCompound;
import net.darkaqua.blacksmith.api.common.util.raytrace.Cube;
import net.darkaqua.blacksmith.api.common.util.EntityRotation;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect2d;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.api.common.world.IWorld;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
import net.minecraft.entity.Entity;

import java.util.UUID;

/**
 * Created by cout970 on 08/11/2015.
 */
public class EntityWrapper implements IEntity {

    private Entity entity;

    public EntityWrapper(Entity ent) {
        this.entity = ent;
    }

    public Entity getMcEntity() {
        return entity;
    }

    @Override
    public IWorld getWorld() {
        return MCInterface.fromWorld(entity.worldObj);
    }

    @Override
    public void setWorld(IWorld w) {
        entity.worldObj = MCInterface.toWorld(w);
    }

    @Override
    public void update() {
        entity.onUpdate();
    }

    @Override
    public Vect3d getLookVector() {
        return MCInterface.fromVec3(entity.getLookVec());
    }

    @Override
    public Vect3d getPosition() {
        return MCInterface.fromVec3(entity.getPositionVector());
    }

    @Override
    public void setPosition(Vect3d pos) {
        entity.setPosition(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public Vect3d getMotion() {
        return new Vect3d(entity.motionX, entity.motionY, entity.motionZ);
    }

    @Override
    public void setMotion(Vect3d mot) {
        entity.setVelocity(mot.getX(), mot.getY(), mot.getZ());
    }

    @Override
    public void moveEntity(Vect3d mot) {
        entity.moveEntity(mot.getX(), mot.getY(), mot.getZ());
    }

    @Override
    public EntityRotation getEntityRotation() {
        return new EntityRotation(entity.rotationPitch, entity.rotationYaw);
    }

    @Override
    public void setEntityRotation(EntityRotation rot) {
        entity.setAngles(rot.getYaw(), rot.getPitch());
    }

    @Override
    public Cube getEntityBoundingBox() {
        return MCInterface.fromAxisAlignedBB(entity.getEntityBoundingBox());
    }

    @Override
    public Cube getEntityCollisionBox() {
        return MCInterface.fromAxisAlignedBB(entity.getCollisionBoundingBox());
    }

    @Override
    public Vect2d getEntitySize() {
        return new Vect2d(entity.width, entity.height);
    }

    @Override
    public boolean isDead() {
        return !entity.isEntityAlive();
    }

    @Override
    public void setDead() {
        entity.setDead();
    }

    @Override
    public UUID getEntityUUID() {
        return entity.getPersistentID();
    }

    @Override
    public void load(IDataCompound data) {
        entity.readFromNBT(MCInterface.toNBTCompound(data));
    }

    @Override
    public void save(IDataCompound data) {
        entity.writeToNBT(MCInterface.toNBTCompound(data));
    }

    @Override
    public IEntity getRiddingEntity() {
        return MCInterface.fromEntity(entity.ridingEntity);
    }

    @Override
    public IEntity getRiddenByEntity() {
        return MCInterface.fromEntity(entity.riddenByEntity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityWrapper)) return false;

        EntityWrapper that = (EntityWrapper) o;

        return !(entity != null ? !entity.equals(that.entity) : that.entity != null);

    }

    @Override
    public int hashCode() {
        return entity != null ? entity.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "EntityWrapper{" +
                "entity=" + entity +
                '}';
    }
}
