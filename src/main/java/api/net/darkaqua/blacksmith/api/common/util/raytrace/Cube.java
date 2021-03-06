package net.darkaqua.blacksmith.api.common.util.raytrace;

import net.darkaqua.blacksmith.api.common.util.Direction;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;

public class Cube {

    protected Vect3d min;
    protected Vect3d max;

    public Cube(double x0, double y0, double z0, double x1, double y1, double z1) {
        min = new Vect3d(Math.min(x0, x1), Math.min(y0, y1), Math.min(z0, z1));
        max = new Vect3d(Math.max(x0, x1), Math.max(y0, y1), Math.max(z0, z1));
    }

    public Cube(Vect3d start, Vect3d end) {
        this(start.getX(), start.getY(), start.getZ(), end.getX(), end.getY(), end.getZ());
    }

    public static Cube fullBlock() {
        return new Cube(0, 0, 0, 1, 1, 1);
    }

    public static Cube infinite() {
        return new Cube(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public Cube copy() {
        return new Cube(min, max);
    }

    public Vect3d min() {
        return min.copy();
    }

    public Vect3d max() {
        return max.copy();
    }

    public double minX() {
        return min.getX();
    }

    public double minY() {
        return min.getY();
    }

    public double minZ() {
        return min.getZ();
    }

    public double maxX() {
        return max.getX();
    }

    public double maxY() {
        return max.getY();
    }

    public double maxZ() {
        return max.getZ();
    }

    public Cube translate(Vect3d pos) {
        return new Cube(min.copy().add(pos), max.copy().add(pos));
    }

    public Cube expand(Vect3d pos) {
        return new Cube(min.copy().sub(pos), max.copy().add(pos));
    }

    public Cube union(Cube box) {
        return new Cube(Math.min(minX(), box.minX()),
                Math.min(minY(), box.minY()),
                Math.min(minZ(), box.minZ()),
                Math.max(maxX(), box.maxX()),
                Math.max(maxY(), box.maxY()),
                Math.max(maxZ(), box.maxZ()));
    }

    public boolean intersect(Cube box) {
        return box.maxX() > minX() && box.minX() < maxX()
                && box.maxY() > minY() && box.minY() < maxY()
                && box.maxZ() > minZ() && box.minZ() < maxZ();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cube)) return false;

        Cube cube = (Cube) o;

        if (min != null ? !min.equals(cube.min) : cube.min != null) return false;
        return !(max != null ? !max.equals(cube.max) : cube.max != null);

    }

    @Override
    public int hashCode() {
        int result = min != null ? min.hashCode() : 0;
        result = 31 * result + (max != null ? max.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Cube{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }

    public Cube extend(Direction dir, double amount) {
        Vect3d vec = dir.toVect3i().toVect3d().multiply(amount);
        return union(translate(vec));
    }
}
