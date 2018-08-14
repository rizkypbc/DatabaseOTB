package bpp.arnet.project.databaseotb;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import bpp.arnet.project.databaseotb.Model.OTB;

public class UpdateOTB extends AppCompatActivity {

    private EditText editTextUpdateNamaOTB, editTextUpdateTipeOTB,
            editTextUpdateArahOTB, editTextUpdateRakOTB,
            editTextUpdateKapasitasOTB, editTextUpdateDataPortOTB,
            editTextUpdateLokasiOTB;

    private TextView textViewUpdatePathPhoto, textViewUpdateIdOTB;
    private Button btnUpdate;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_update_otb);

        editTextUpdateNamaOTB = (EditText) findViewById (R.id.editTextUpdateNamaOTB);
        editTextUpdateTipeOTB = (EditText) findViewById (R.id.editTextUpdateTipeOTB);
        editTextUpdateArahOTB = (EditText) findViewById (R.id.editTextUpdateArahOTB);
        editTextUpdateRakOTB = (EditText) findViewById (R.id.editTextUpdateRakOTB);
        editTextUpdateKapasitasOTB = (EditText) findViewById (R.id.editTextUpdateKapasitasOTB);
        editTextUpdateDataPortOTB = (EditText) findViewById (R.id.editTextUpdateDataPort);
        editTextUpdateLokasiOTB = (EditText) findViewById (R.id.editTextUpdateLokasiOTB);

        textViewUpdateIdOTB = (TextView) findViewById (R.id.textUpdateIdOTB);
        textViewUpdatePathPhoto = (TextView) findViewById (R.id.textUpdatePathPhoto);


        imageView = (ImageView) findViewById (R.id.update_img_thumb);
        btnUpdate = (Button) findViewById (R.id.btnUpdateDataOTB);


        String valueNama = getIntent ().getStringExtra ("nama");
        editTextUpdateNamaOTB.setText (valueNama);

        String valueTipe = getIntent ().getStringExtra ("tipe");
        editTextUpdateTipeOTB.setText (valueTipe);

        String valueArah = getIntent ().getStringExtra ("arah");
        editTextUpdateArahOTB.setText (valueArah);

        String valueRak = getIntent ().getStringExtra ("rak");
        editTextUpdateRakOTB.setText (valueRak);

        String valueKapasitas = getIntent ().getStringExtra ("kapasitas");
        editTextUpdateKapasitasOTB.setText (valueKapasitas);

        String valueDataPort = getIntent ().getStringExtra ("data_port");
        editTextUpdateDataPortOTB.setText (valueDataPort);

        String valuePathPhoto = getIntent ().getStringExtra ("path");
        textViewUpdatePathPhoto.setText (valuePathPhoto);

        String valueLokasi = getIntent ().getStringExtra ("lokasi");
        editTextUpdateLokasiOTB.setText (valueLokasi);

        String valueId = getIntent ().getStringExtra ("id");
        textViewUpdateIdOTB.setText (valueId);

    }
}
