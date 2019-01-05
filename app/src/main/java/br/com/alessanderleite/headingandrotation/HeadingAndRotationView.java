package br.com.alessanderleite.headingandrotation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class HeadingAndRotationView extends SurfaceView implements Runnable {

    Context context;

    // This is our thread
    Thread gameThread = null;

    // Our SurfaceHolder to lock the surface before we draw our graphics
    SurfaceHolder ourHolder;

    // A boolean which we will set and unset
    // when the game is running- or not
    volatile boolean playing;

    // Game is paused at the start
    boolean paused = true;

    // A Canvas and a Paint object
    Canvas canvas;
    Paint paint;

    // This variable tracks the game frame rate
    long fps;

    // This is used to help calculate the fps
    private long timeThisFrame;

    // The size of the screen in pixels
    int screenX;
    int screenY;

    // The player's ship
    Ship ship;

    // When we initialize (call new()) on view
    // This special constructor method runs

    public HeadingAndRotationView(Context context, int x, int y) {
        super(context);
    }

    @Override
    public void run() {

    }

    private void update() {

        // Move the player's ship
        ship.update(fps);
    }

    private void draw() {
        // Make sure our drawing surface is valid or we crash
        if (ourHolder.getSurface().isValid()) {
            // Lock the canvas ready to draw
            canvas = ourHolder.lockCanvas();

            // Draw the background color
            canvas.drawColor(Color.argb(255,26,128,182));

            // Choose the brush color for drawing
            paint.setColor(Color.argb(255,255,255,255));

            // Now draw the player spaceship
            // Line from a to b
            canvas.drawLine(ship.getA().x, ship.getA().y,
                    ship.getB().x, ship.getB().y,
                    paint);

            // Line from b to c
            canvas.drawLine(ship.getB().x, ship.getB().y,
                    ship.getC().x, ship.getC().y,
                    paint);

            // Line from c to a
            canvas.drawLine(ship.getC().x, ship.getC().y,
                    ship.getA().x, ship.getA().y,
                    paint);

            canvas.drawPoint(ship.getCentre().x, ship.getCentre().y, paint);

            paint.setTextSize(60);
            canvas.drawText("facingAngle = " + (int)ship.getFacingAngle() + " degrees", 20, 70, paint);

            // Draw everything to the screen
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    // If the Activity is paused/stopped
    // shutdown our thread.
    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", " joining thread");
        }
    }

    // If the Activity is started then
    // start our thread.
    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
}
