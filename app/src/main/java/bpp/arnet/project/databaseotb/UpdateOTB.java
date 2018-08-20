package bpp.arnet.project.databaseotb;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ipaulpro.afilechooser.utils.FileUtils;
import com.squareup.picasso.Picasso;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import bpp.arnet.project.databaseotb.Model.OTB;

public class UpdateOTB extends AppCompatActivity implements View.OnClickListener{

    private static final int STORAGE_PERMISSION_CODE = 123;

    private TextView textViewUpdatePathPhoto, textViewUpdateIdOTB;
    private static final int REQUEST_CHOOSER = 1234;
    private ImageView imageView;
    int serverResponseCode = 0;
    ProgressDialog dialog = null;
    private EditText editTextUpdateNamaOTB, editTextUpdateTipeOTB,
            editTextUpdateArahOTB, editTextUpdateRakOTB,
            editTextUpdateKapasitasOTB, editTextUpdateDataPortOTB,
            editTextUpdateLokasiOTB, editTextUpdatePathPhoto;
    private Button btnUpdate, btnUpdateGambar;
    private Uri uri;


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
        editTextUpdateLokasiOTB.setEnabled (false);

        textViewUpdateIdOTB = (TextView) findViewById (R.id.textUpdateIdOTB);
//        textViewUpdatePathPhoto = (TextView) findViewById (R.id.textUpdatePathPhoto);
        editTextUpdatePathPhoto = (EditText)findViewById (R.id.editTextUpdatePathPhoto);
        editTextUpdatePathPhoto.setEnabled (false);


        imageView = (ImageView) findViewById (R.id.update_img_thumb);
        btnUpdateGambar = (Button)findViewById (R.id.buttonUpdatePilihFile);
        btnUpdate = (Button) findViewById (R.id.btnUpdateDataOTB);

        btnUpdateGambar.setOnClickListener (this);
        btnUpdate.setOnClickListener (this);


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

        String valueLokasi = getIntent ().getStringExtra ("lokasi");
        editTextUpdateLokasiOTB.setText (valueLokasi);

        String valueId = getIntent ().getStringExtra ("id");
        textViewUpdateIdOTB.setText (valueId);

//        String valuePathPhoto = getIntent ().getStringExtra ("path");
//        textViewUpdatePathPhoto.setText (valuePathPhoto);

//        String urlPhoto = "http://192.168.1.17/otb/img/" + valuePathPhoto;
//        urlPhoto = urlPhoto.replaceAll (" ", "%20");
//        Picasso.with (this)
//                .load (urlPhoto)
//                .placeholder (R.drawable.ic_launcher_background)
//                .error (android.R.drawable.stat_notify_error)
//                .into (imageView);

    }


    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId ()){

            case R.id.buttonUpdatePilihFile:

                Intent getContentIntent = FileUtils.createGetContentIntent();
                Intent intent = Intent
                        .createChooser(getContentIntent, "Pilih file");
                startActivityForResult(intent, REQUEST_CHOOSER);
                break;

            case R.id.btnUpdateDataOTB:

                final String path = editTextUpdatePathPhoto.getText ().toString ();
                if (path != null && FileUtils.isLocal (path)){

                    File file = new File (path);
                }

                if ("".equals (editTextUpdateNamaOTB.getText ().toString ())){
                    editTextUpdateNamaOTB.setError ("Nama Tidak Boleh Kosong");
                    editTextUpdateNamaOTB.requestFocus ();
                    return;
                }

                if ("".equals (editTextUpdateTipeOTB.getText ().toString ())){
                    editTextUpdateTipeOTB.setError ("Tipe Tidak Boleh Kosong");
                    editTextUpdateTipeOTB.requestFocus ();
                    return;
                }

                if ("".equals (editTextUpdateArahOTB.getText ().toString ())){
                    editTextUpdateArahOTB.setError ("Arah Tidak Boleh Kosong");
                    editTextUpdateArahOTB.requestFocus ();
                    return;
                }

                if ("".equals (editTextUpdateRakOTB.getText ().toString ())){
                    editTextUpdateRakOTB.setError ("Rak Tidak Boleh Kosong");
                    editTextUpdateRakOTB.requestFocus ();
                    return;
                }
                if ("".equals (editTextUpdateKapasitasOTB.getText ().toString ())){
                    editTextUpdateKapasitasOTB.setError ("Kapasitas Tidak Boleh Kosong");
                    editTextUpdateKapasitasOTB.requestFocus ();
                    return;
                }

                if ("".equals (editTextUpdateDataPortOTB.getText ().toString ())){
                    editTextUpdateDataPortOTB.setError ("Data Port Tidak Boleh Kosong");
                    editTextUpdateDataPortOTB.requestFocus ();
                    return;
                }


                if ("".equals(editTextUpdatePathPhoto.getText().toString())) {
                    editTextUpdatePathPhoto.setError("Pilih Photo");
                    editTextUpdatePathPhoto.requestFocus();
                    return;
                }
//
//                if ("".equals(textViewUpdatePathPhoto.getText().toString())) {
//                    textViewUpdatePathPhoto.setError("Pilih Photo");
//                    textViewUpdatePathPhoto.requestFocus();
//                    return;
//                }


                dialog = ProgressDialog.show(UpdateOTB.this, "",
                        "Mohon Tunggu Sebentar, Sedang Proses Mengirim Data...", true);

                new Thread (new Runnable () {
                    @Override
                    public void run() {

                        runOnUiThread (new Runnable () {
                            @Override
                            public void run() {

                            }
                        });

                        int response = 0;

                        String id = textViewUpdateIdOTB.getText ().toString ();
                        String nama = editTextUpdateNamaOTB.getText ().toString ();
                        String tipe = editTextUpdateTipeOTB.getText ().toString ();
                        String arah = editTextUpdateArahOTB.getText ().toString ();
                        String rak = editTextUpdateRakOTB.getText ().toString ();
                        String kapasitas = editTextUpdateKapasitasOTB.getText ().toString ();
                        String data_port = editTextUpdateDataPortOTB.getText ().toString ();
                        String lokasi = editTextUpdateLokasiOTB.getText ().toString ();

                        response = uploadFile(path, nama, tipe, arah, rak,
                                              kapasitas, data_port, lokasi, id);
                        System.out.println("RES : " + response);
                    }
                }).start ();
//                ListLokasi.start (UpdateOTB.this);
//                UpdateOTB.this.finish ();
                break;

                default:

                    break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case REQUEST_CHOOSER:
                if (resultCode == RESULT_OK) {

                    uri = data.getData();
                    final String path = FileUtils.getPath(this, uri);
                    editTextUpdatePathPhoto.setText(path);
                    imageView.setImageURI (uri);
                }
                break;
        }
    }

    public int uploadFile(String sourceFileUri, String nama, String tipe,
                          String arah, String rak, String kapasitas, String data_port,
                          String nama_lokasi, String id){

        String upLoadServerUri = "http://aksesblk-samarinda.com/otb/updateDataWithImage.php";
        String fileName = sourceFileUri;
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;

        int maxBufferSize = 1 * 1500;
        File sourceFile = new File(sourceFileUri);
        if (!sourceFile.isFile()) {
            Log.e("uploadFile", "Source File Does not exist");
            return 0;
        }

        try {

            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            URL url = new URL(upLoadServerUri);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true); // Allow Inputs
            conn.setDoOutput(true); // Allow Outputs
            conn.setUseCaches(false); // Don't use a Cached Copy
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data;boundary=" + boundary);
            conn.setRequestProperty("uploaded_file", fileName);
            dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            // untuk parameter keterangan
            dos.writeBytes("Content-Disposition: form-data; name=\"nama\""
                    + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(nama);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"tipe\""
                    + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(tipe);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"arah\""
                    + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(arah);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"rak\""
                    + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(rak);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"kapasitas\""
                    + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(kapasitas);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"data_port\""
                    + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(data_port);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"nama_lokasi\""
                    + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(nama_lokasi);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"id\""
                    + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(id);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                    + fileName + "\"" + lineEnd);
            dos.writeBytes(lineEnd);
            // create a buffer of maximum size
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];
            // read file and write it into form...
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }

            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            serverResponseCode = conn.getResponseCode();
            String serverResponseMessage = conn.getResponseMessage();

            if (serverResponseCode == 200){

                runOnUiThread (new Runnable () {
                    @Override
                    public void run() {

                        LayoutInflater inflater = getLayoutInflater();
                        View toastLayout = inflater.inflate(R.layout.custom_toast_update, (ViewGroup) findViewById(R.id.custom_toast_update));

                        Toast toast = new Toast(getApplicationContext ());

                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(toastLayout);
                        toast.show();
                        toast.show();
                        toast.show();
                        ListLokasi.start (UpdateOTB.this);
                        UpdateOTB.this.finish ();
                    }
                });
            } else {

                Toast.makeText(UpdateOTB.this, "Gagal Mengirim Data", Toast.LENGTH_LONG).show();
            }

            // close the streams //

            fileInputStream.close();

            dos.flush();
            dos.close();
        } catch (MalformedURLException ex){

            dialog.dismiss();
            ex.printStackTrace();
            Toast.makeText(UpdateOTB.this, "MalformedURLException",
                    Toast.LENGTH_SHORT).show();
            Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
        } catch (Exception e){

            dialog.dismiss();
            e.printStackTrace();
            Toast.makeText(UpdateOTB.this, "Exception : " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            Log.e("Upload ", "Exception : " + e.getMessage(), e);
        }

        dialog.dismiss();
        finish();
        return serverResponseCode;
    }


}
