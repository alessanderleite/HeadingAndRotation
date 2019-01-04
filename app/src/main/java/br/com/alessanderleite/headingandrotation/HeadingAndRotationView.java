package br.com.alessanderleite.headingandrotation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
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
}
