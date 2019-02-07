import greenfoot.*; 
import java.util.List;

/**
 * A 'Body' is any kind of object in space that has a mass. It could be 
 * a star, or a planet, or anything else that floats around in space.
 * 
 * @author Michael Kölling 
 * @version 0.2
 */
public class Body extends SmoothMover
{
    // constants
    private static final double GRAVITY = 5.8;
    private static final Color defaultColor = new Color(255, 216, 0);

    // fields
    private double mass;

    /**
     * Construct a Body with default size, mass, velocity and color.
     */
    public Body()
    {
        this (20, 300, new Vector(0, 1.0), defaultColor);
    }

    /**
     * Construct a Body with a specified size, mass, velocity and color.
     */
    public Body(int size, double mass, Vector velocity, Color color)
    {
        this.mass = mass;
        addToVelocity(velocity);
        GreenfootImage image = new GreenfootImage (size, size);
        image.setColor (color);
        image.fillOval (0, 0, size-1, size-1);
        setImage (image);
    }

    /**
     * Act. That is: apply  the gravitation forces from
     * all other bodies around, and then move.
     */
    public void act() 
    {
        applyForces();
        move();
        // To be done - not yet implemented
    }

    /**
     * Appy the forecs of gravity form all other bodies in this universe. 
     */
    private void applyForces(){
        List <Body> bodies = getWorld().getObjects(Body.class);
        for (Body b : bodies) {
            if(b != this) {
                applyGravity(b);
            }
        }
    }

    /**
     * Apply the force of gravity of a given body to this one.
     */
    private void applyGravity(Body other) {
        double dx = other.getExactX() - this.getExactX();
        double dy = other.getExactY() - this.getExactY();
        double dist = Math.sqrt(dx*dx+dy*dy);
        double force = GRAVITY*((this.mass*other.mass)/(dist*dist));
        double acceleration = force/this.mass;
        Vector v = new Vector(dx,dy);
        v.setLength(acceleration);
        this.addToVelocity(v);
    }

    /**
     * Return the mass of this body.
     */
    public double getMass()
    {
        return mass;
    }
}
