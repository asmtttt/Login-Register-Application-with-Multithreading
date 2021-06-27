package samet.ocsoy.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // logonun animasyon işlemi
        Thread logoAnimation = new Thread(){

            @Override
            public void run(){
                ImageView logo = findViewById(R.id.imageView);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_intro_logo);
                logo.startAnimation(animation);

            }
        };


        // baslik yazısının animasyon işlemi
        Thread tittleAnimation = new Thread(){

            @Override
            public void run(){
                TextView tittle = findViewById(R.id.LabelTittle);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_intro_author);
                tittle.startAnimation(animation);

            }
        };


        // yapımci adi animasyon işlemi

        Thread authorAnimation = new Thread(){

            @Override
            public void run(){
                TextView author = findViewById(R.id.authorTittle);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_intro_author);
                author.startAnimation(animation);
            }
        };

        // redirrect = yonlendir
        Thread redirrect = new Thread(){

            @Override
            public void run(){

                try {
                    // 3.5 saniye animasyonların çalışmasını bekledikten sonra aktif oluyor.
                    sleep(5500);
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                    super.run();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        // threadler çalıştırılıyor.
        logoAnimation.start();
        tittleAnimation.start();
        authorAnimation.start();
        redirrect.start();

    }
}