package samet.ocsoy.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class veritabani extends SQLiteOpenHelper {

    // android studio da veri tabanı oluşturmak için ad ve sürüm adı bilgisi gereklidir.
    // bunun için ad ve sürüm bilgisi için değişken oluşturup rastgele değer atadık.

    public static String VERİ_TABANİ_ADİ = "Bilgiler";
    public static int SURUM = 1;


    // Constructor oluşturduk

    // Context:
    // uygulama için genel bilgileri tutan arayüz,
    // uygulamaya özgü kaynaklara erişimi sağlayan c nesnesi.

    public veritabani (Context c)
    {
        // super alt sınıftan üst sınıfa erişimi sağlıyor.
        super(c,VERİ_TABANİ_ADİ,null,SURUM);
    }


    // veri tabanı ile ilgili oluşturma işlemlerinin yapıldığı metot
    // genelde 1 defa kullanılır.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE bilgiler (email Text, parola Text, ad Text);");


    }


    // veri tabani ile ilgili guncelleme işlemlerinin yapılığı metot
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXİSTS bilgiler");
        onCreate(sqLiteDatabase);

    }

    public Boolean veri_Ekle (String email, String parola, String ad)
    {
        SQLiteDatabase veri_tabani = this.getWritableDatabase();
        ContentValues veriler = new ContentValues();
        veriler.put("email",email);
        veriler.put("parola",parola);
        veriler.put("ad",ad);

        // long veri tipinde tutuyoruz. insert komutu ile ekleme yapıyoruz.
        // insert metodu tablo adı, null, ve veriler olmak üzere 3 parametre alıyor.
        long sonuc = veri_tabani.insert("bilgiler", null, veriler);

        // bu kısım bir hata olma ihtimali için yapılıyor. sonuç negatif ise false yani hatalı
        if (sonuc == -1){
            return false;
        }

        // hatalı değil ise tru değerini döndürüyor.
        else {
            return true;
        }

    }


    // parametrelerin veri tabanında olup olmadığını kontrol eden metot
    public Boolean Kontrol (String email, String parola)
    {
        // veri tabanını okuma işlemi için kullanacağımızı bildiriyoruz.
        SQLiteDatabase veri_tabani = this.getReadableDatabase();

        // Cursor sınıfı ile rawQuery metodu ile sql sorgumuzu yazıyoruz
        Cursor cursor = veri_tabani.rawQuery("SELECT * FROM bilgiler WHERE email=? and parola=?", new String[]{email,parola});

        // eğer kayıt eşleşiyor ise böyle bir kayıt var ise true döndürüyoruz.
        if (cursor.getCount() > 0)
        {
            return true;
        } else
        {
            return false;
        }


    }


}
