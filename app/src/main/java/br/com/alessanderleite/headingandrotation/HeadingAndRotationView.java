package br.com.alessanderleite.headingandrotation;

import android.content.Context;
import android.graphics.Canvas;
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
