package br.com.alessanderleite.headingandrotation;

import android.content.Context;
import android.graphics.PointF;

public class Ship {

    PointF a;
    PointF b;
    PointF c;
    PointF centre;

    /*
    * Which way is the ship facing
    * Straight up to start with
    */

    float facingAngle = 270;

    // How long will our spaceshi be
    private float length;
    private float width;

    // This will hold the pixels per second speed that the ship can move at
    private float speed = 100;

    /*
    * Thes next two variables control the actual movement rate per frame
    * their values are set each frame based on speed and heading
    */

    private float horizontalVelocity;
    private float verticalVelocity;

    /*
    * How fast does the ship rotate?
    * 100 degrees per second
    */

    private float rotationSpeed = 100;

    // Which ways can the ship move
    public final int STOPPED = 0;
    public final int LEFT = 1;
    public final int RIGHT = 2;
    public final int THRUSTING = 3;

    // Is the ship moving and in which direction
    private int shipMoving = STOPPED;

    /*
    * This the the contructor method
    * When we create an object from this class we will pass
    * in the screen width and height
    */

    public Ship(Context context, int screenX, int screenY) {

        length = screenX / 5;
        width = screenY / 5;

        a = new PointF();
        b = new PointF();
        c = new PointF();
        centre = new PointF();

        centre.x = screenX / 2;
        centre.y = screenY / 2;

        a.x = centre.x;
        a.y = centre.y - length /2;

        b.x = centre.x - width / 2;
        b.y = centre.y + length / 2;

        c.x = centre.x + width / 2;
        c.y = centre.y + length / 2;
    }
}
