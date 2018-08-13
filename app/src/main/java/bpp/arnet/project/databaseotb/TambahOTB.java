package bpp.arnet.project.databaseotb;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ipaulpro.afilechooser.utils.FileUtils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import bpp.arnet.project.databaseotb.Lokasi.DownloaderLokasi;
import bpp.arnet.project.databaseotb.Model.BaseResponse;
import bpp.arnet.project.databaseotb.Network.TambahDataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahOTB extends AppCompatActivity implements View.OnClickListener{

    final String API_LOKASI = "http://192.168.1.17/otb/spinnerLokasi.php";

    private static final int STORAGE_PERMISSION_CODE = 123;
    private static final int REQUEST_CHOOSER = 1234;
    int serverResponseCode = 0;
    ProgressDialog dialog = null;

    private Spinner spinnerLokasi;
    private EditText editTextNamaOTB, editTextTipe, editTextArah,
    editTextRak, editTextKapasitas, editTextDataPort;
    private Button btnTambahData, btnPilihFile;
    private TextView textViewPathPhoto;

    private TambahDataService tambahDataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_tambah_otb);

        requestStoragePermission();
        init();
//        btnTambahData.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View v) {
//
//                tambahData ();
//            }
//        });
    }

    private void init(){

        editTextNamaOTB = (EditText)findViewById (R.id.editTextNamaOTB);
        editTextTipe = (EditText)findViewById (R.id.editTextTipe);
        editTextArah = (EditText)findViewById (R.id.editTextArah);
        editTextRak = (EditText)findViewById (R.id.editTextRak);
        editTextKapasitas = (EditText)findViewById (R.id.editTextKapasitas);
        editTextDataPort = (EditText)findViewById (R.id.editTextDataPort);
        textViewPathPhoto = (TextView)findViewById (R.id.textPathPhoto);

        spinnerLokasi = (Spinner)findViewById (R.id.spnTambahLokasi);
        btnTambahData = (Button)findViewById (R.id.btnTambahDataOTB);
        btnPilihFile = (Button)findViewById (R.id.buttonPilihFile) ;

        btnPilihFile.setOnClickListener (this);
        btnTambahData.setOnClickListener (this);
    }

    @Override
    protected void onStart() {
        super.onStart ();
        new DownloaderLokasi (TambahOTB.this, API_LOKASI, spinnerLokasi).execute ();
    }

//    private void tambahData(){
//
//        String namaOTB = editTextNamaOTB.getText ().toString ();
//        if (TextUtils.isEmpty (namaOTB)){
//            editTextNamaOTB.setError ("Data Nama Tidak Boleh Kosong");
//            return;
//        }
//
//        String tipeOTB = editTextTipe.getText ().toString ();
//        if (TextUtils.isEmpty (tipeOTB)){
//
//            editTextTipe.setError ("Data Tipe Tidak Boleh Kosong");
//            return;
//        }
//
//        String arahOTB = editTextArah.getText ().toString ();
//        if (TextUtils.isEmpty (arahOTB)){
//
//            editTextArah.setError ("Data Arah Tidak Boleh Kosong");
//            return;
//        }
//
//        String rakOTB = editTextRak.getText ().toString ();
//        if (TextUtils.isEmpty (rakOTB)){
//
//            editTextRak.setError ("Data Rak Tidak Boleh Kosong");
//            return;
//        }
//
//        String kapasitasOTB = editTextKapasitas.getText ().toString ();
//        if (TextUtils.isEmpty (kapasitasOTB)){
//
//            editTextKapasitas.setError ("Data Tipe Tidak Boleh Kosong");
//            return;
//        }
//
//        String dataPortOTB = editTextDataPort.getText ().toString ();
//        if (TextUtils.isEmpty (dataPortOTB)){
//
//            editTextDataPort.setError ("Data Port Tidak Boleh Kosong");
//            return;
//        }
//
//        String lokasi = spinnerLokasi.getSelectedItem ().toString ();
//
//        tambahDataService = new TambahDataService (getApplicationContext ());
//        tambahDataService.doTambahData (namaOTB, tipeOTB, arahOTB, rakOTB, kapasitasOTB, dataPortOTB, lokasi, new Callback () {
//            @Override
//            public void onResponse(Call call, Response response) {
//
//                BaseResponse baseResponse = (BaseResponse)response.body ();
//                if (baseResponse != null){
//                    if (!baseResponse.isError ()){
//
//                        ListLokasi.start (TambahOTB.this);
//                        TambahOTB.this.finish ();
//                    }
//
//                    Toast.makeText (TambahOTB.this, baseResponse.getMessage (), Toast.LENGTH_SHORT).show ();
//                }
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//
//
//                Toast.makeText (TambahOTB.this, "An Error Occured", Toast.LENGTH_SHORT).show ();
//            }
//        });
//    }

    @Override
    public void onClick(View v) {

        switch (v.getId ()){

            case R.id.buttonPilihFile:

                Intent getContentIntent = FileUtils.createGetContentIntent();
                Intent intent = Intent
                        .createChooser(getContentIntent, "Pilih file");
                startActivityForResult(intent, REQUEST_CHOOSER);

                break;

            case R.id.btnTambahDataOTB:

                final String path = textViewPathPhoto.getText ().toString ();

                if (path != null && FileUtils.isLocal (path)){

                    File file = new File (path);
                }

                if ("".equals(editTextNamaOTB.getText().toString())) {
                    editTextNamaOTB.setError("Nama Tidak Boleh Kosong");
                    editTextNamaOTB.requestFocus();
                    return;
                }

                if ("".equals(editTextTipe.getText().toString())) {
                    editTextTipe.setError("Tipe Tidak Boleh Kosong");
                    editTextTipe.requestFocus();
                    return;
                }

                if ("".equals(editTextArah.getText().toString())) {
                    editTextArah.setError("Arah Tidak Boleh Kosong");
                    editTextArah.requestFocus();
                    return;
                }

                if ("".equals(editTextKapasitas.getText().toString())) {
                    editTextKapasitas.setError("Kapasitas Tidak Boleh Kosong");
                    editTextKapasitas.requestFocus();
                    return;
                }

                if ("".equals(editTextDataPort.getText().toString())) {
                    editTextDataPort.setError("Data Port Tidak Boleh Kosong");
                    editTextDataPort.requestFocus();
                    return;
                }


                if ("".equals(textViewPathPhoto.getText().toString())) {
                    textViewPathPhoto.setError("Pilih Photo");
                    textViewPathPhoto.requestFocus();
                    return;
                }

                dialog = ProgressDialog.show(TambahOTB.this, "",
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

                        String nama = editTextNamaOTB.getText ().toString ();
                        String tipe = editTextTipe.getText ().toString ();
                        String arah = editTextArah.getText ().toString ();
                        String rak = editTextRak.getText ().toString ();
                        String kapasitas = editTextKapasitas.getText ().toString ();
                        String data_port = editTextDataPort.getText ().toString ();
                        String lokasi = spinnerLokasi.getSelectedItem ().toString ();

                        response = uploadFile(path, nama, tipe, arah, rak, kapasitas, data_port, lokasi);
                        System.out.println("RES : " + response);
                    }
                }).start ();
                break;

                default:

                    break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case REQUEST_CHOOSER:
                if (resultCode == RESULT_OK) {

                    final Uri uri = data.getData();
                    final String path = FileUtils.getPath(this, uri);
                    textViewPathPhoto.setText(path);
                }
                break;
        }
    }

    public int uploadFile(String sourceFileUri, String nama, String tipe, String arah, String rak,
                          String kapasitas, String data_port, String nama_lokasi){

        // ip komputer server
        String upLoadServerUri = "http://192.168.1.17/otb/tambahDataWithImage.php";
        String fileName = sourceFileUri;
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
//        int maxBufferSize = 1 * 1024 * 1024;
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

            // untuk parameter keterangan
            dos.writeBytes("Content-Disposition: form-data; name=\"tipe\""
                    + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(tipe);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            // untuk parameter keterangan
            dos.writeBytes("Content-Disposition: form-data; name=\"arah\""
                    + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(arah);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            // untuk parameter keterangan
            dos.writeBytes("Content-Disposition: form-data; name=\"rak\""
                    + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(rak);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            // untuk parameter keterangan
            dos.writeBytes("Content-Disposition: form-data; name=\"kapasitas\""
                    + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(kapasitas);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            // untuk parameter keterangan
            dos.writeBytes("Content-Disposition: form-data; name=\"data_port\""
                    + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(data_port);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            // untuk parameter keterangan
            dos.writeBytes("Content-Disposition: form-data; name=\"nama_lokasi\""
                    + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(nama_lokasi);
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
                        View toastLayout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_layout));

                        Toast toast = new Toast(getApplicationContext());

                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(toastLayout);
                        toast.show();
                        toast.show();
                        toast.show();
                    }
                });
            } else {

                Toast.makeText(TambahOTB.this, "Gagal Mengirim Data", Toast.LENGTH_LONG).show();
            }

            // close the streams //

            fileInputStream.close();

            dos.flush();
            dos.close();
        } catch (MalformedURLException ex){

            dialog.dismiss();
            ex.printStackTrace();
            Toast.makeText(TambahOTB.this, "MalformedURLException",
                    Toast.LENGTH_SHORT).show();
            Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
        } catch (Exception e){

            dialog.dismiss();
            e.printStackTrace();
            Toast.makeText(TambahOTB.this, "Exception : " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            Log.e("Upload ", "Exception : " + e.getMessage(), e);
        }

        dialog.dismiss();
        finish();
        return serverResponseCode;
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
}
