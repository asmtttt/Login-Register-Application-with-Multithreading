  package samet.ocsoy.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import samet.ocsoy.myapplication.veritabani;

public class LoginActivity extends AppCompatActivity {


    // sembollerin ve veri tabanının tanımlanması
    EditText email_Login, parola_Login;
    Button btn_Login, btn_Register;
    private veritabani veri_TABANİ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // bu kısımda action bardaki yazıyı değiştiriyoruz.
        getSupportActionBar().setTitle("MusicHouse");

        // veri tabanını bağladık
        veri_TABANİ = new veritabani(this);

        // kullanılan toolboxları bağlıyoruz.
        email_Login = (EditText)findViewById(R.id.email_login);
        parola_Login = (EditText)findViewById(R.id.parola_login);
        btn_Login = (Button)findViewById(R.id.btn_login);
        btn_Register = (Button)findViewById(R.id.btn_register);




        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // buraya login butonuna basılınca gerçekleşecek işlemler yazılacak

                // editttextteki verileri string değişkene taşıyoruz.
                String email = email_Login.getText().toString();
                String parola = parola_Login.getText().toString();

                // veri tabanı sınıfında yazdığımız metodu kullanarak sql sorgusunu yapıyoruz.
                Boolean kontrolet_giris = veri_TABANİ.Kontrol(email,parola);

                if (kontrolet_giris == true)
                {
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(intent);
                    finish();

                    Toast toast = Toast.makeText(LoginActivity.this, "MusicHouse'a Hoşgeldin "+ email, Toast.LENGTH_SHORT);

                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_drawable);
                    toast.show();


                }else
                    {
                        Toast.makeText(LoginActivity.this, "Tekrar Deneyiniz.", Toast.LENGTH_SHORT).show();

                    }





            }
        });


        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // kayit ol tuşuna basınca yapılacak işlem

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();

            }
        });










    }
}