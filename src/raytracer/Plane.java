package raytracer;

import math.Matrix44D;
import math.Vec3D;

public class Plane extends Object {
    private Vec3D point;
    private Vec3D normal;

    public Plane(Matrix44D objectToWorld, Vec3D albedo) {
        this.objectToWorld = objectToWorld;
        worldToObject = objectToWorld.inverse();
        this.albedo = albedo;
        point = objectToWorld.multiplyPoint(new Vec3D());
        normal = objectToWorld.multiplyDirection(new Vec3D(0, -1, 0));
        //point = new Vec3D(0, -5, 0);
        //normal = new Vec3D(0, -1, 0);
    }

    @Override
    public Double intersect(Ray ray) {
        double denom = normal.dotProduct(ray.direction);
        if (denom > 1e-6) {
            Vec3D pointOrigin = point.subtract(ray.origin);
            double t = pointOrigin.dotProduct(normal) / denom;
            return t >= 0 ? t : null;
        }
        return null;
    }

    @Override
    public Vec3D getSurfaceProperties(Vec3D hitPoint) {
        return normal.multiply(-1);
    }
}