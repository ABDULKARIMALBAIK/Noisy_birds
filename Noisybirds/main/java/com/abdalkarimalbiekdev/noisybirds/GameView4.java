package com.abdalkarimalbiekdev.noisybirds;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.view.MotionEvent;

import com.abdalkarimalbiekdev.noisybirds.Model.Bullet;
import com.abdalkarimalbiekdev.noisybirds.Decorator.MusicInterface;
import com.abdalkarimalbiekdev.noisybirds.Observer.Data;
import com.abdalkarimalbiekdev.noisybirds.Observer.ScoreData;
import com.abdalkarimalbiekdev.noisybirds.Singlton_Composite.Component;
import com.abdalkarimalbiekdev.noisybirds.Strategy.GameBird;
import com.abdalkarimalbiekdev.noisybirds.Model.GameBlood;
import com.abdalkarimalbiekdev.noisybirds.Singlton_Composite.GameFlight;
import com.abdalkarimalbiekdev.noisybirds.Singlton_Composite.ReadyFlight;
import com.abdalkarimalbiekdev.noisybirds.Strategy.BirdFive;
import com.abdalkarimalbiekdev.noisybirds.Strategy.BirdFour;
import com.abdalkarimalbiekdev.noisybirds.Strategy.BirdOne;
import com.abdalkarimalbiekdev.noisybirds.Strategy.BirdSeven;
import com.abdalkarimalbiekdev.noisybirds.Strategy.BirdSix;
import com.abdalkarimalbiekdev.noisybirds.Strategy.BirdThree;
import com.abdalkarimalbiekdev.noisybirds.Strategy.BirdTwo;
import com.abdalkarimalbiekdev.noisybirds.Model.Background;
import com.abdalkarimalbiekdev.noisybirds.Template.Template;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView4 extends Template implements Runnable , MusicInterface {

    private Thread thread;
    private boolean isPlaying, isGameOver = false;

    private int screenX, screenY, score = 0;
    public static float screenRatioX4, screenRatioY4;

    private SharedPreferences prefs;
    private Random random;

    private List<Bullet> bullets;
    private GameBlood[] gameBlood;
    private GameBird[] birds;

    private SoundPool soundPool;
    private int soundBullet;
    private int soundBirdDamage;
    private int soundBirdDie;
    private int soundFlightDamage;
    private int soundEatBird;
    private int soundExplosion;

    private GameFlight gameFlight;
    private GameActivity4 activity;
    private Background background1, background2;

    private int bloodCounter = 5;
    private int chickenScore = 0;

    private Paint paintChicken;
    private Paint paint;

    Bitmap scoreBitmap;
    Bitmap chickenBitmap;

    ScoreData scoreData;
    Data data;

    public GameView4(GameActivity4 activity, int screenX, int screenY) {
        super(activity);

        this.activity = activity;

        prefs = activity.getSharedPreferences("game", Context.MODE_PRIVATE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .build();

        } else
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        soundBullet = soundPool.load(activity, R.raw.shoot, 1);
        soundBirdDie = soundPool.load(activity, R.raw.bird_die, 1);
        soundFlightDamage = soundPool.load(activity, R.raw.flight_damage, 1);
        soundEatBird = soundPool.load(activity, R.raw.eat_bird, 1);
        soundExplosion = soundPool.load(activity, R.raw.explosion, 1);
        soundBirdDamage = soundPool.load(activity, R.raw.damage_bird, 1);

        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX4 = 1920f / screenX;
        screenRatioY4 = 1080f / screenY;

        scoreBitmap = drawScoreBitmap();
        chickenBitmap = drawChickenBitmap();



        background1 = new Background(screenX, screenY, getResources() , 3);
        background2 = new Background(screenX, screenY, getResources() , 3);



        gameFlight = new GameFlight(GameView4.this, screenY, getResources());
        gameFlight.add(ReadyFlight.getInstanceLevel4(GameView4.this, screenY, getResources()));
        gameFlight.subFlights.get(0).setupData(GameView4.this, (screenY / 2), getResources());

        GameFlight subFlights = new GameFlight(GameView4.this, screenY, getResources());
        subFlights.add(new ReadyFlight(GameView4.this, screenY, getResources()));
        subFlights.add(new ReadyFlight(GameView4.this, screenY, getResources()));
        subFlights.subFlights.get(0).setupData(GameView4.this,  (screenY / 2) + 200, getResources());
        subFlights.subFlights.get(1).setupData(GameView4.this,  (screenY / 2) + 400, getResources());
        gameFlight.add(subFlights);



        scoreData = new ScoreData();
        data = new Data(scoreData);



        bullets = new ArrayList<>();
        background2.x = screenX;



        paint = new Paint();
        paint.setTextSize(90);
        paint.setColor(Color.WHITE);
        paintChicken = new Paint();
        paintChicken.setTextSize(90);
        paintChicken.setColor(Color.RED);



        birds = new GameBird[7];
        birds[0] = new BirdOne(getResources() , screenRatioX4 , screenRatioY4);
        birds[1] = new BirdTwo(getResources() , screenRatioX4 , screenRatioY4);
        birds[2] = new BirdThree(getResources() , screenRatioX4 , screenRatioY4);
        birds[3] = new BirdFour(getResources() , screenRatioX4 , screenRatioY4);
        birds[4] = new BirdFive(getResources() , screenRatioX4 , screenRatioY4);
        birds[5] = new BirdSix(getResources() , screenRatioX4 , screenRatioY4);
        birds[6] = new BirdSeven(getResources() , screenRatioX4 , screenRatioY4);
        for (int i = 0;i < 7;i++) {

            //GameBird bird = new GameBird(getResources());
            birds[i].bloodBird = 3;  //New
            //birds[i] = bird;

        }


        gameBlood = new GameBlood[5];
        for (int i = 0; i < bloodCounter; i++) {

            GameBlood blood = new GameBlood(getResources() , 1);
            gameBlood[i] = blood;

        }
        random = new Random();

    }

    @Override
    public void run() {

        while (isPlaying) {

            update ();
            draw ();
            sleep ();

        }

    }

    @Override
    public void update () {

        //Moving the background
        background1.x -= 10 * screenRatioX4;
        background2.x -= 10 * screenRatioX4;

        if (background1.x + background1.background.getWidth() < 0) {
            background1.x = screenX;
        }

        if (background2.x + background2.background.getWidth() < 0) {
            background2.x = screenX;
        }




        //Moving the bullets and check if bird is killed
        List<Bullet> trash = new ArrayList<>();

        for (Bullet bullet : bullets) {

            if (bullet.x > screenX)
                trash.add(bullet);

            bullet.x += 50 * screenRatioX4;

            for (GameBird bird : birds) {

                if (Rect.intersects(bird.getCollisionShape(),
                        bullet.getCollisionShape())) {

                    if (bird.bloodBird > 0){
                        bird.bloodBird--;
                        bullet.x = screenX + 500;

                        if (!prefs.getBoolean("isMute", false))
                            soundPool.play(soundBirdDamage, 1, 1, 0, 0, 1);
                    }
                    else if (!bird.wasShot){

                        score++;
                        scoreData.setData(score,chickenScore);
                        bullet.x = screenX + 500;
                        bird.wasShot = true;
                        bird.chickenLeg(getResources());

                        if (!prefs.getBoolean("isMute", false))
                            soundPool.play(soundBirdDie, 1, 1, 0, 0, 1);
                    }


                }

            }

        }

        for (Bullet bullet : trash)
            bullets.remove(bullet);




        //Moving the birds and check if bird arrived to the end
        for (GameBird bird : birds) {

            bird.x -= bird.speed;

            if (bird.x + bird.width < 0) {

                if (!bird.wasShot) {

                    if (bloodCounter > 1){
                        gameBlood[bloodCounter - 1].changeBlood(getResources() , 0);
                        bloodCounter--;

                        if (!prefs.getBoolean("isMute", false))
                            soundPool.play(soundFlightDamage, 1, 1, 0, 0, 1);
                    }
                    else {
                        gameBlood[bloodCounter - 1].changeBlood(getResources() , 0);
                        bloodCounter--;
                        isGameOver = true;

                        if (!prefs.getBoolean("isMute", false))
                            soundPool.play(soundExplosion, 1, 1, 0, 0, 1);

                        return;
                    }

                }

//                int bound = (int) (40 * screenRatioX4);
//                bird.speed = random.nextInt(bound);
//
//                if (bird.speed < 10 * screenRatioX4)
//                    bird.speed = (int) (10 * screenRatioX4);

                bird.x = screenX;
                bird.y = random.nextInt(screenY - bird.height);
                bird.bloodBird = 3;
                bird.changeBird(getResources());
                bird.wasShot = false;
            }

            if (Rect.intersects(bird.getCollisionShape(), gameFlight.subFlights.get(0).getCollisionShape())) {

                if (!bird.wasShot) {

                    if (bloodCounter > 1){
                        gameBlood[bloodCounter - 1].changeBlood(getResources() , 0);
                        bloodCounter--;

                        if (!prefs.getBoolean("isMute", false))
                            soundPool.play(soundFlightDamage, 1, 1, 0, 0, 1);


//                        int bound = (int) (40 * screenRatioX4);
//                        bird.speed = random.nextInt(bound);
//
//                        if (bird.speed < 10 * screenRatioX4)
//                            bird.speed = (int) (10 * screenRatioX4);

                        bird.x = screenX;
                        bird.y = random.nextInt(screenY - bird.height);
                        bird.bloodBird = 3;
                        bird.changeBird(getResources());
                        bird.wasShot = false;

                    }
                    else {
                        gameBlood[bloodCounter - 1].changeBlood(getResources() , 0);
                        bloodCounter--;
                        isGameOver = true;

                        if (!prefs.getBoolean("isMute", false))
                            soundPool.play(soundExplosion, 1, 1, 0, 0, 1);

                        return;
                    }

                }
                else {

                    chickenScore++;
                    scoreData.setData(score,chickenScore);

                    if (!prefs.getBoolean("isMute", false))
                        soundPool.play(soundEatBird, 1, 1, 0, 0, 1);

//                    int bound = (int) (30 * screenRatioX4);
//                    bird.speed = random.nextInt(bound);
//
//                    if (bird.speed < 40 * screenRatioX4)
//                        bird.speed = (int) (10 * screenRatioX4);

                    bird.x = screenX;
                    bird.y = random.nextInt(screenY - bird.height);
                    bird.bloodBird = 3;
                    bird.changeBird(getResources());
                    bird.wasShot = false;

                }

            }

            for (int i = 0; i < gameFlight.subFlights.get(1).subFlights.size(); i++ ){

                if (Rect.intersects(bird.getCollisionShape(), gameFlight.subFlights.get(1).subFlights.get(i).getCollisionShape())){

                    if (!bird.wasShot) {

                        if (bloodCounter > 1){
                            gameBlood[bloodCounter - 1].changeBlood(getResources() , 0);
                            bloodCounter--;

                            if (!prefs.getBoolean("isMute", false))
                                soundPool.play(soundFlightDamage, 1, 1, 0, 0, 1);


                            int bound = (int) (36 * screenRatioX4);
                            bird.speed = random.nextInt(bound);

                            if (bird.speed < 10 * screenRatioX4)
                                bird.speed = (int) (10 * screenRatioX4);

                            bird.x = screenX;
                            bird.y = random.nextInt(screenY - bird.height);
                            bird.bloodBird = 2;
                            bird.changeBird(getResources());
                            bird.wasShot = false;

                        }
                        else {
                            gameBlood[bloodCounter - 1].changeBlood(getResources() , 0);
                            bloodCounter--;
                            isGameOver = true;

                            if (!prefs.getBoolean("isMute", false))
                                soundPool.play(soundExplosion, 1, 1, 0, 0, 1);

                            return;
                        }

                    }
                    else {

                        chickenScore++;

                        if (!prefs.getBoolean("isMute", false))
                            soundPool.play(soundEatBird, 1, 1, 0, 0, 1);

                        int bound = (int) (36 * screenRatioX4);
                        bird.speed = random.nextInt(bound);

                        if (bird.speed < 10 * screenRatioX4)
                            bird.speed = (int) (10 * screenRatioX4);

                        bird.x = screenX;
                        bird.y = random.nextInt(screenY - bird.height);
                        bird.bloodBird = 2;
                        bird.changeBird(getResources());
                        bird.wasShot = false;

                    }
                }
            }

        }



    }

    @Override
    public void draw () {

        if (getHolder().getSurface().isValid()) {

            String[] spliteString = data.display().split("/");

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);

            for (GameBird bird : birds)
                canvas.drawBitmap(bird.getBird(), bird.x, bird.y, paint);

            int x = 0 , y = 0 , increment = 0;
            for (int i = 0; i < gameBlood.length; i++) {
                canvas.drawBitmap(gameBlood[i].getBlood() , x + increment , y , paint);
                increment += 150;
            }



            canvas.drawBitmap(scoreBitmap , (screenX / 2f) - 100 , 20 , paint);
            canvas.drawText(spliteString[0] + "", (screenX / 2f) - 100, 250, paint);
            canvas.drawBitmap(chickenBitmap , (screenX/ 2f) + 100 , 20 , paintChicken);
            canvas.drawText(spliteString[1] + "" , (screenX/ 2f) + 100 , 250 , paintChicken);



            if (isGameOver) {

                isPlaying = false;

                canvas.drawBitmap(gameFlight.subFlights.get(0).getDead(), gameFlight.subFlights.get(0).x, gameFlight.subFlights.get(0).y, paint);
                for (int i = 0; i < gameFlight.subFlights.get(1).subFlights.size(); i++) {
                    canvas.drawBitmap(gameFlight.subFlights.get(1).subFlights.get(i).getDead(), gameFlight.subFlights.get(1).subFlights.get(i).x, gameFlight.subFlights.get(1).subFlights.get(i).y, paint);
                }
                gameFlight.subFlights.clear();

                getHolder().unlockCanvasAndPost(canvas);
                saveIfHighScore();
                saveIfHighChicken();
                waitBeforeExiting();
                return;
            }

            canvas.drawBitmap(gameFlight.subFlights.get(0).getFlight(), gameFlight.subFlights.get(0).x, gameFlight.subFlights.get(0).y, paint);
            for (int i = 0; i < gameFlight.subFlights.get(1).subFlights.size(); i++) {
                canvas.drawBitmap(gameFlight.subFlights.get(1).subFlights.get(i).getFlight(), gameFlight.subFlights.get(1).subFlights.get(i).x, gameFlight.subFlights.get(1).subFlights.get(i).y, paint);
            }

            for (Bullet bullet : bullets)
                canvas.drawBitmap(bullet.photo, bullet.x, bullet.y, paint);

            getHolder().unlockCanvasAndPost(canvas);

        }

    }

    private Bitmap drawScoreBitmap() {

        Bitmap scoreBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_scores);

        int width = scoreBitmap.getWidth();
        int height = scoreBitmap.getHeight();

        width /= 12;
        height /= 12;
        width = (int) (width * screenRatioX4);
        height = (int) (height * screenRatioY4);

        scoreBitmap = Bitmap.createScaledBitmap(scoreBitmap, width, height, false);

        return scoreBitmap;

    }

    private Bitmap drawChickenBitmap() {

        Bitmap chicken = BitmapFactory.decodeResource(getResources(), R.drawable.chicken_leg);

        int width = chicken.getWidth();
        int height = chicken.getHeight();

        width /= 12;
        height /= 12;
        width = (int) (width * screenRatioX4);
        height = (int) (height * screenRatioY4);

        chicken = Bitmap.createScaledBitmap(chicken, width, height, false);

        return  chicken;

    }

    private void waitBeforeExiting() {

        try {
            Thread.sleep(3000);
            activity.finish();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void saveIfHighScore() {

        if (prefs.getInt("highscore", 0) < score) {

            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("highscore", score);
            editor.apply();
        }

    }

    public void saveIfHighChicken(){

        if (prefs.getInt("highChicken" , 0) < chickenScore){

            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("highChicken" , chickenScore);
            editor.apply();
        }
    }


    public void resume () {

        isPlaying = true;
        thread = new Thread(this);
        thread.start();

    }

    public void pause () {

        try {
            isPlaying = false;
            thread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:{
                if (event.getX() < screenX / 2) {

                    if (gameFlight.subFlights.size() > 0){

                        gameFlight.subFlights.get(0).x = (int) event.getX();
                        gameFlight.subFlights.get(0).y = (int)event.getY();

                        int increment = 100;
                        for (int i = 0; i < gameFlight.subFlights.get(1).subFlights.size(); i++) {
                            gameFlight.subFlights.get(1).subFlights.get(i).x = (int) event.getX();
                            gameFlight.subFlights.get(1).subFlights.get(i).y = (int)event.getY() + increment;
                            increment += 100;
                        }

                    }

                }
                break;
            }
            case MotionEvent.ACTION_DOWN:{

                if (event.getX() > screenX / 2){

                    if (gameFlight.subFlights.size() > 0)
                        gameFlight.subFlights.get(0).toShoot++;

                }

                break;
            }

        }

        return true;
    }

    public void newBullet() {

        if (!prefs.getBoolean("isMute", false))
            soundPool.play(soundBullet, 1, 1, 0, 0, 1);



        Bullet bullet = new Bullet(getResources());
        bullet.x = gameFlight.subFlights.get(0).x + gameFlight.subFlights.get(0).width;
        bullet.y = gameFlight.subFlights.get(0).y + (gameFlight.subFlights.get(0).height / 2);
        bullets.add(bullet);
        for (int i = 0; i < gameFlight.subFlights.get(1).subFlights.size(); i++) {

            Bullet subBulletBuilder = new Bullet(getResources());
            subBulletBuilder.x = gameFlight.subFlights.get(1).subFlights.get(i).x + gameFlight.subFlights.get(1).subFlights.get(i).width;
            subBulletBuilder.y = gameFlight.subFlights.get(1).subFlights.get(i).y + (gameFlight.subFlights.get(1).subFlights.get(i).height / 2);
            bullets.add(subBulletBuilder);
        }

    }

    @Override
    public void start() {

    }

    @Override
    public void pausee() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isPlaying() {
        return false;
    }
}
