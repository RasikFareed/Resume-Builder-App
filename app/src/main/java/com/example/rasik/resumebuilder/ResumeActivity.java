package com.example.rasik.resumebuilder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rasik.resumebuilder.Util.TinyDB;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rasik on 3/10/17.
 */

public class ResumeActivity extends AppCompatActivity {
    String TAG = "ResumeActivity";
    TextView resumeName,resumeEmail,careerTextview,schoolTextview1,schoolTextview2,collegeTextview,skillTextview1,skillTextview2,skillTextview3,skillTextview4;
    RatingBar resumeRatingBar1,resumeRatingBar2,resumeRatingBar3,resumeRatingBar4;
    Button btn_generate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume);


        resumeName = (TextView) findViewById(R.id.resumeName);
        resumeEmail = (TextView) findViewById(R.id.resumeEmail);
        schoolTextview1 = (TextView) findViewById(R.id.schoolTextview1);
        schoolTextview2 = (TextView) findViewById(R.id.schoolTextview2);
        collegeTextview = (TextView) findViewById(R.id.collegeTextview);

        skillTextview1 = (TextView) findViewById(R.id.skillTextview1);
        skillTextview2 = (TextView) findViewById(R.id.skillTextview2);
        skillTextview3 = (TextView) findViewById(R.id.skillTextview3);
        skillTextview4 = (TextView) findViewById(R.id.skillTextview4);

        resumeRatingBar1 = (RatingBar) findViewById(R.id.resumeRatingBar1);
        resumeRatingBar2 = (RatingBar) findViewById(R.id.resumeRatingBar2);
        resumeRatingBar3 = (RatingBar) findViewById(R.id.resumeRatingBar3);
        resumeRatingBar4 = (RatingBar) findViewById(R.id.resumeRatingBar4);

        btn_generate = (Button) findViewById(R.id.btn_generate);

        TinyDB tinyDB = new TinyDB(getApplicationContext());
        resumeName.setText(tinyDB.getString("firstName")+" "+tinyDB.getString("lastName"));
        resumeName.setTextSize(35);
        resumeEmail.setText("Email: "+tinyDB.getString("email"));

        Spanned schoolText1 = Html.fromHtml("<b>"+tinyDB.getString("tenth")+"</b> -"+tinyDB.getString("tenthYear")+"<br />"+"CGPA: "+tinyDB.getString("tenthPercent"));
        schoolTextview1.setText(schoolText1);
        schoolTextview1.setTextSize(15);
        Spanned schoolText2 = Html.fromHtml("<b>"+tinyDB.getString("twelth")+"</b> -"+tinyDB.getString("twelthYear")+"<br />"+"CGPA: "+tinyDB.getString("twelthPercent"));
        schoolTextview2.setText(schoolText2);
        schoolTextview2.setTextSize(15);
        Spanned collegeText = Html.fromHtml("<b>"+tinyDB.getString("college")+"</b> -"+tinyDB.getString("collegeYear")+"<br />"+"CGPA: "+tinyDB.getString("collegePercent"));
        collegeTextview.setText(collegeText);
        collegeTextview.setTextSize(15);


        skillTextview1.setText(tinyDB.getString("skill1"));
        skillTextview2.setText(tinyDB.getString("skill2"));
        skillTextview3.setText(tinyDB.getString("skill3"));
        skillTextview4.setText(tinyDB.getString("skill4"));

        resumeRatingBar1.setRating(tinyDB.getFloat("rating1"));
        resumeRatingBar2.setRating(tinyDB.getFloat("rating2"));
        resumeRatingBar3.setRating(tinyDB.getFloat("rating3"));
        resumeRatingBar4.setRating(tinyDB.getFloat("rating4"));
        isStoragePermissionGranted();
        btn_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    createPdf();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void createPdf() throws FileNotFoundException, DocumentException {
        TinyDB tinyDB = new TinyDB(getApplicationContext());
        File pdfFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Resume");
        if (!pdfFolder.exists()) {
            pdfFolder.mkdir();
            Log.i("Tag", "Pdf Directory created");
        }

        Date date = new Date() ;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        RelativeLayout root = (RelativeLayout) inflater.inflate(R.layout.resume,null);
        root.setDrawingCacheEnabled(true);
        Bitmap screen= getBitmapByView((ScrollView) this.getWindow().findViewById(R.id.scrollViewResume));
        File myFile = new File(pdfFolder.getAbsolutePath(),"Resume Builder"+timeStamp + ".pdf");


        try {
            Rectangle pagesize = new Rectangle(screen.getWidth()+200, screen.getHeight()+200);
            Document  document = new Document(pagesize);
            PdfWriter.getInstance(document, new FileOutputStream(myFile));
            document.open();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            screen.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            addImage(document,byteArray);
            document.close();
            Toast.makeText(getApplicationContext(),"PDF Generated Sucessfully!, in Documents/Resume folder",Toast.LENGTH_LONG).show();
            tinyDB.clear();
            finish();
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    private static void addImage(Document document,byte[] byteArray)
    {
        Image image = null;
        try
        {
            image = Image.getInstance(byteArray);
        }
        catch (BadElementException e)
        {
            e.printStackTrace();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        // image.scaleAbsolute(150f, 150f);
        try
        {
            document.add(image);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    public static Bitmap getBitmapByView(ScrollView scrollView) {
        int h = 0;
        Bitmap bitmap = null;
        //get the actual height of scrollview
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
            scrollView.getChildAt(i).setBackgroundResource(R.color.white);
        }
        // create bitmap with target size
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
                Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("/sdcard/screen_test.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (null != out) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
            }
        } catch (IOException e) {
        }
        return bitmap;
    }


    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            try {
                createPdf();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
    }
}
