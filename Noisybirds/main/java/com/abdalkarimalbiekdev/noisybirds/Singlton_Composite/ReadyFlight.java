package com.abdalkarimalbiekdev.noisybirds.Singlton_Composite;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.abdalkarimalbiekdev.noisybirds.GameView;
import com.abdalkarimalbiekdev.noisybirds.GameView2;
import com.abdalkarimalbiekdev.noisybirds.GameView3;
import com.abdalkarimalbiekdev.noisybirds.GameView4;
import com.abdalkarimalbiekdev.noisybirds.GameView5;
import com.abdalkarimalbiekdev.noisybirds.R;

import java.util.ArrayList;
import java.util.List;

import static com.abdalkarimalbiekdev.noisybirds.GameView.screenRatioX;
import static com.abdalkarimalbiekdev.noisybirds.GameView.screenRatioY;
import static com.abdalkarimalbiekdev.noisybirds.GameView2.screenRatioX2;
import static com.abdalkarimalbiekdev.noisybirds.GameView2.screenRatioY2;
import static com.abdalkarimalbiekdev.noisybirds.GameView3.screenRatioX3;
import static com.abdalkarimalbiekdev.noisybirds.GameView3.screenRatioY3;
import static com.abdalkarimalbiekdev.noisybirds.GameView4.screenRatioX4;
import static com.abdalkarimalbiekdev.noisybirds.GameView4.screenRatioY4;
import static com.abdalkarimalbiekdev.noisybirds.GameView5.screenRatioX5;
import static com.abdalkarimalbiekdev.noisybirds.GameView5.screenRatioY5;

public class ReadyFlight extends Component{


    public static ReadyFlight instanceLevel1 = null;
    public static ReadyFlight instanceLevel2 = null;
    public static ReadyFlight instanceLevel3 = null;
    public static ReadyFlight instanceLevel4 = null;
    public static ReadyFlight instanceLevel5 = null;


    public ReadyFlight(GameView gameView, int screenY, Resources res) {
        super(gameView, screenY, res);
    }
    public ReadyFlight(GameView2 gameView2, int screenY, Resources res) {
        super(gameView2, screenY, res);
    }
    public ReadyFlight(GameView3 gameView3, int screenY, Resources res) {
        super(gameView3, screenY, res);
    }
    public ReadyFlight(GameView4 gameView4, int screenY, Resources res) {
        super(gameView4, screenY, res);
    }
    public ReadyFlight(GameView5 gameView5, int screenY, Resources res) {
        super(gameView5, screenY, res);
    }



    @Override
    public void setupData(GameView gameView, int screenY, Resources res) {
        checkScreenRatio();

        this.gameView = gameView;

        y = screenY / 2;
        x = (int) (64 * selectedScreenRatioX);
    }
    @Override
    public void setupData(GameView2 gameView2, int screenY, Resources res) {

        checkScreenRatio();

        this.gameView2 = gameView2;

        y = screenY / 2;
        x = (int) (64 * selectedScreenRatioX);

    }
    @Override
    public void setupData(GameView3 gameView3, int screenY, Resources res) {

        checkScreenRatio();

        this.gameView3 = gameView3;

        y = (screenY / 2) + 100;
        x = (int) (64 * selectedScreenRatioX);

    }
    @Override
    public void setupData(GameView4 gameView4, int screenY, Resources res) {

        checkScreenRatio();

        this.gameView4 = gameView4;

        y = (screenY / 2) + 100;
        x = (int) (64 * selectedScreenRatioX);

    }
    @Override
    public void setupData(GameView5 gameView5, int screenY, Resources res) {

        checkScreenRatio();

        this.gameView5 = gameView5;

        y = (screenY / 2) + 100;
        x = (int) (64 * selectedScreenRatioX);
    }



    @Override
    public Bitmap getFlight() {

        if (toShoot != 0) {

            if (shootCounter == 1) {
                shootCounter++;
                return shoot1;
            }

            if (shootCounter == 2) {
                shootCounter++;
                return shoot2;
            }

            if (shootCounter == 3) {
                shootCounter++;
                return shoot3;
            }

            if (shootCounter == 4) {
                shootCounter++;
                return shoot4;
            }

            shootCounter = 1;
            toShoot--;

            if (gameView != null)
                gameView.newBullet();
            else if (gameView2 != null)
                gameView2.newBullet();
            else if (gameView3 != null)
                gameView3.newBullet();
            else if (gameView4 != null)
                gameView4.newBullet();
            else if (gameView5 != null)
                gameView5.newBullet();

            return shoot5;
        }

        if (wingCounter == 0) {
            wingCounter++;
            return flight1;
        }
        wingCounter--;

        return flight2;

    }
    @Override
    public Rect getCollisionShape() {
        return new Rect(x, y, x + width, y + height);
    }
    @Override
    public Bitmap getDead() {
        return dead;
    }
    @Override
    public void checkScreenRatio() {
        if (screenRatioX > 0 && screenRatioY > 0){
            selectedScreenRatioX = screenRatioX;
            selectedScreenRatioY = screenRatioY;
        }
        else if (screenRatioX2 > 0 && screenRatioY2 > 0){
            selectedScreenRatioX = screenRatioX2;
            selectedScreenRatioY = screenRatioY2;
        }
        else if (screenRatioX3 > 0 && screenRatioY3 > 0){
            selectedScreenRatioX = screenRatioX3;
            selectedScreenRatioY = screenRatioY3;
        }
        else if (screenRatioX4 > 0 && screenRatioY4 > 0){
            selectedScreenRatioX = screenRatioX4;
            selectedScreenRatioY = screenRatioY4;
        }
        else if (screenRatioX5 > 0 && screenRatioY5 > 0){
            selectedScreenRatioX = screenRatioX5;
            selectedScreenRatioY = screenRatioY5;
        }
    }



    @Override
    public void buildBitmaps(GameView gameView, int screenY, Resources res) {
        checkScreenRatio();

        this.gameView = gameView;

        flight1 = BitmapFactory.decodeResource(res, R.drawable.fly1);
        flight2 = BitmapFactory.decodeResource(res, R.drawable.fly2);

        width = flight1.getWidth();
        height = flight1.getHeight();
        width /= 4;
        height /= 4;
        width = (int) (width * selectedScreenRatioX);
        height = (int) (height * selectedScreenRatioY);

        flight1 = Bitmap.createScaledBitmap(flight1, width, height, false);
        flight2 = Bitmap.createScaledBitmap(flight2, width, height, false);

        shoot1 = BitmapFactory.decodeResource(res, R.drawable.shoot1);
        shoot2 = BitmapFactory.decodeResource(res, R.drawable.shoot2);
        shoot3 = BitmapFactory.decodeResource(res, R.drawable.shoot3);
        shoot4 = BitmapFactory.decodeResource(res, R.drawable.shoot4);
        shoot5 = BitmapFactory.decodeResource(res, R.drawable.shoot5);

        shoot1 = Bitmap.createScaledBitmap(shoot1, width, height, false);
        shoot2 = Bitmap.createScaledBitmap(shoot2, width, height, false);
        shoot3 = Bitmap.createScaledBitmap(shoot3, width, height, false);
        shoot4 = Bitmap.createScaledBitmap(shoot4, width, height, false);
        shoot5 = Bitmap.createScaledBitmap(shoot5, width, height, false);

        dead = BitmapFactory.decodeResource(res, R.drawable.dead);
        dead = Bitmap.createScaledBitmap(dead, width, height, false);

        y = screenY / 2;
        x = (int) (64 * selectedScreenRatioX);
    }
    @Override
    public void buildBitmaps(GameView2 gameView2, int screenY, Resources res) {

        checkScreenRatio();

        this.gameView2 = gameView2;

        flight1 = BitmapFactory.decodeResource(res, R.drawable.fly1);
        flight2 = BitmapFactory.decodeResource(res, R.drawable.fly2);

        width = flight1.getWidth();
        height = flight1.getHeight();
        width /= 4;
        height /= 4;
        width = (int) (width * selectedScreenRatioX);
        height = (int) (height * selectedScreenRatioY);

        flight1 = Bitmap.createScaledBitmap(flight1, width, height, false);
        flight2 = Bitmap.createScaledBitmap(flight2, width, height, false);

        shoot1 = BitmapFactory.decodeResource(res, R.drawable.shoot1);
        shoot2 = BitmapFactory.decodeResource(res, R.drawable.shoot2);
        shoot3 = BitmapFactory.decodeResource(res, R.drawable.shoot3);
        shoot4 = BitmapFactory.decodeResource(res, R.drawable.shoot4);
        shoot5 = BitmapFactory.decodeResource(res, R.drawable.shoot5);

        shoot1 = Bitmap.createScaledBitmap(shoot1, width, height, false);
        shoot2 = Bitmap.createScaledBitmap(shoot2, width, height, false);
        shoot3 = Bitmap.createScaledBitmap(shoot3, width, height, false);
        shoot4 = Bitmap.createScaledBitmap(shoot4, width, height, false);
        shoot5 = Bitmap.createScaledBitmap(shoot5, width, height, false);

        dead = BitmapFactory.decodeResource(res, R.drawable.dead);
        dead = Bitmap.createScaledBitmap(dead, width, height, false);

        y = screenY / 2;
        x = (int) (64 * selectedScreenRatioX);
    }
    @Override
    public void buildBitmaps(GameView3 gameView3, int screenY, Resources res) {

        checkScreenRatio();

        this.gameView3 = gameView3;

        flight1 = BitmapFactory.decodeResource(res, R.drawable.fly1);
        flight2 = BitmapFactory.decodeResource(res, R.drawable.fly2);

        width = flight1.getWidth();
        height = flight1.getHeight();
        width /= 4;
        height /= 4;
        width = (int) (width * selectedScreenRatioX);
        height = (int) (height * selectedScreenRatioY);

        flight1 = Bitmap.createScaledBitmap(flight1, width, height, false);
        flight2 = Bitmap.createScaledBitmap(flight2, width, height, false);

        shoot1 = BitmapFactory.decodeResource(res, R.drawable.shoot1);
        shoot2 = BitmapFactory.decodeResource(res, R.drawable.shoot2);
        shoot3 = BitmapFactory.decodeResource(res, R.drawable.shoot3);
        shoot4 = BitmapFactory.decodeResource(res, R.drawable.shoot4);
        shoot5 = BitmapFactory.decodeResource(res, R.drawable.shoot5);

        shoot1 = Bitmap.createScaledBitmap(shoot1, width, height, false);
        shoot2 = Bitmap.createScaledBitmap(shoot2, width, height, false);
        shoot3 = Bitmap.createScaledBitmap(shoot3, width, height, false);
        shoot4 = Bitmap.createScaledBitmap(shoot4, width, height, false);
        shoot5 = Bitmap.createScaledBitmap(shoot5, width, height, false);

        dead = BitmapFactory.decodeResource(res, R.drawable.dead);
        dead = Bitmap.createScaledBitmap(dead, width, height, false);

        y = screenY / 2;
        x = (int) (64 * selectedScreenRatioX);
    }
    @Override
    public void buildBitmaps(GameView4 gameView4, int screenY, Resources res) {

        checkScreenRatio();

        this.gameView4 = gameView4;

        flight1 = BitmapFactory.decodeResource(res, R.drawable.fly1);
        flight2 = BitmapFactory.decodeResource(res, R.drawable.fly2);

        width = flight1.getWidth();
        height = flight1.getHeight();
        width /= 4;
        height /= 4;
        width = (int) (width * selectedScreenRatioX);
        height = (int) (height * selectedScreenRatioX);

        flight1 = Bitmap.createScaledBitmap(flight1, width, height, false);
        flight2 = Bitmap.createScaledBitmap(flight2, width, height, false);

        shoot1 = BitmapFactory.decodeResource(res, R.drawable.shoot1);
        shoot2 = BitmapFactory.decodeResource(res, R.drawable.shoot2);
        shoot3 = BitmapFactory.decodeResource(res, R.drawable.shoot3);
        shoot4 = BitmapFactory.decodeResource(res, R.drawable.shoot4);
        shoot5 = BitmapFactory.decodeResource(res, R.drawable.shoot5);

        shoot1 = Bitmap.createScaledBitmap(shoot1, width, height, false);
        shoot2 = Bitmap.createScaledBitmap(shoot2, width, height, false);
        shoot3 = Bitmap.createScaledBitmap(shoot3, width, height, false);
        shoot4 = Bitmap.createScaledBitmap(shoot4, width, height, false);
        shoot5 = Bitmap.createScaledBitmap(shoot5, width, height, false);

        dead = BitmapFactory.decodeResource(res, R.drawable.dead);
        dead = Bitmap.createScaledBitmap(dead, width, height, false);

        y = screenY / 2;
        x = (int) (64 * selectedScreenRatioX);
    }
    @Override
    public void buildBitmaps(GameView5 gameView5, int screenY, Resources res) {

        checkScreenRatio();

        this.gameView5 = gameView5;

        flight1 = BitmapFactory.decodeResource(res, R.drawable.fly1);
        flight2 = BitmapFactory.decodeResource(res, R.drawable.fly2);

        width = flight1.getWidth();
        height = flight1.getHeight();
        width /= 4;
        height /= 4;
        width = (int) (width * selectedScreenRatioX);
        height = (int) (height * selectedScreenRatioY);

        flight1 = Bitmap.createScaledBitmap(flight1, width, height, false);
        flight2 = Bitmap.createScaledBitmap(flight2, width, height, false);

        shoot1 = BitmapFactory.decodeResource(res, R.drawable.shoot1);
        shoot2 = BitmapFactory.decodeResource(res, R.drawable.shoot2);
        shoot3 = BitmapFactory.decodeResource(res, R.drawable.shoot3);
        shoot4 = BitmapFactory.decodeResource(res, R.drawable.shoot4);
        shoot5 = BitmapFactory.decodeResource(res, R.drawable.shoot5);

        shoot1 = Bitmap.createScaledBitmap(shoot1, width, height, false);
        shoot2 = Bitmap.createScaledBitmap(shoot2, width, height, false);
        shoot3 = Bitmap.createScaledBitmap(shoot3, width, height, false);
        shoot4 = Bitmap.createScaledBitmap(shoot4, width, height, false);
        shoot5 = Bitmap.createScaledBitmap(shoot5, width, height, false);

        dead = BitmapFactory.decodeResource(res, R.drawable.dead);
        dead = Bitmap.createScaledBitmap(dead, width, height, false);

        y = screenY / 2;
        x = (int) (64 * selectedScreenRatioX);
    }

    @Override
    public void add(Component component) {
        //Do nothing here
    }

    @Override
    public void remove(Component component) {
        //Do nothing here
    }


    public static  ReadyFlight getInstanceLevel1(GameView gameView , int screenY , Resources res){
        if (instanceLevel1 == null)
            instanceLevel1 = new ReadyFlight(gameView , screenY , res);

        return instanceLevel1;
    }
    public static ReadyFlight getInstanceLevel2(GameView2 gameView2 , int screenY , Resources res) {

        if (instanceLevel2 == null)
            instanceLevel2 = new ReadyFlight(gameView2 , screenY , res);

        return instanceLevel2;
    }
    public static ReadyFlight getInstanceLevel3(GameView3 gameView3 , int screenY , Resources res) {

        if (instanceLevel3 == null)
            instanceLevel3 = new ReadyFlight(gameView3 , screenY , res);

        return instanceLevel3;
    }
    public static ReadyFlight getInstanceLevel4(GameView4 gameView4 , int screenY , Resources res) {

        if (instanceLevel4 == null)
            instanceLevel4 = new ReadyFlight(gameView4 , screenY , res);

        return instanceLevel4;
    }
    public static ReadyFlight getInstanceLevel5(GameView5 gameView5 , int screenY , Resources res) {

        if (instanceLevel5 == null)
            instanceLevel5 = new ReadyFlight(gameView5 , screenY , res);

        return instanceLevel5;
    }


}
