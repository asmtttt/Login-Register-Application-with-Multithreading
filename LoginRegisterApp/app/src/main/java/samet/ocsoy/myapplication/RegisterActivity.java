package samet.ocsoy.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    // sembollerin ve veri tabanının tanımlanması
    EditText email_for_RegisterPage, parola_for_RegisterPage, ad_for_RegisterPage;
    Button btn_register_for_RegisterPage;
    private veritabani veri_TABANİ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // bu kısımda action bardaki yazıyı değiştiriyoruz.
        getSupportActionBar().setTitle("Geri Dön");

        // geri dön butonunu action bara koyuyoruz
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        // veri tabanını bağladık
        veri_TABANİ = new veritabani(this);

        // kullanılan toolboxları bağladık.
        email_for_RegisterPage = (EditText)findViewById(R.id.email_login_forRegister);
        parola_for_RegisterPage = (EditText)findViewById(R.id.parola_login_forRegister);
        ad_for_RegisterPage = (EditText)findViewById(R.id.name_login_forRegister);

        btn_register_for_RegisterPage = (Button)findViewById(R.id.btn_register_forRegister);


        // kayit ol butonuna tıklayınca edittext te yazan verilerin veritabanına eklenme işlemi.

        btn_register_for_RegisterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // buraya kayit ol butonuna basınca olması gereken işlemler yazılacak

                String email_text = email_for_RegisterPage.getText().toString();
                String parola_text = parola_for_RegisterPage.getText().toString();
                String ad_text = ad_for_RegisterPage.getText().toString();


                // eğer edittextlerden herhangi biri boş ise
                if (email_text.equals("") || parola_text.equals("") || ad_text.equals(""))
                {
                    Toast.makeText(RegisterActivity.this, "Hata : Eksik Bilgi.", Toast.LENGTH_SHORT).show();

                }

                // değilse yani hepsi dolu ise
                else {

                    // daha önceden yazdığımız metodu kullanıyoruz.
                    Boolean ekle_Veri = veri_TABANİ.veri_Ekle(email_text, parola_text, ad_text);

                    if (ekle_Veri == true) {
                        // hata yoksa
                        Toast.makeText(RegisterActivity.this, "Kayit Yapıldi.", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // hata varsa
                        Toast.makeText(RegisterActivity.this, "Hata : Kayit İşlemi Yapılamadı.", Toast.LENGTH_SHORT).show();
                    }

                    // edittextler temizleniyor.
                    email_for_RegisterPage.setText("");
                    parola_for_RegisterPage.setText("");
                    ad_for_RegisterPage.setText("");

                }



            }
        });

    }


    // geri dön butonuna basıldığında bir intent açılıyor ve bu bizi login activity ye geri döndürüyor.
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivityForResult(myIntent, 0 );
        return true;
    }



}