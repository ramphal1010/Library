package com.example.ram.offline;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import com.example.ram.Book.PDF_Activity;
import com.example.ram.BuildConfig;
import com.example.ram.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

@SuppressWarnings("deprecation")
public class DownloadTask {
    private static final String TAG = "Download Task";
    private Context context;
    private Button buttonText,share;
    private String downloadUrl, downloadFileName;
    private  Dialog dialog;
    private TextView percent;
    public DownloadTask(Context context, Button button, Button share, String downloadFileName, String downloadUrl,TextView textView) {
        this.context = context;
        this.downloadFileName = downloadFileName;
        this.downloadUrl = downloadUrl;
        this.buttonText=button;
        this.share=share;
        this.percent=textView;
         AlertDialog.Builder  builder=new AlertDialog.Builder(context);
         builder.setView(R.layout.progress);
         dialog=builder.create();
         dialog.setCancelable(false);
        Log.e(TAG, downloadFileName);
        new DownloadingTask().execute();
    }


    @SuppressLint("StaticFieldLeak")
    private class DownloadingTask extends AsyncTask<String, String, String> {

        File apkStorage = null;
        File outputFile = null;


        protected void onPreExecute() {
            super.onPreExecute();
            buttonText.setEnabled(false);
            buttonText.setText(R.string.loading);
            dialog.show();
        }
        protected void onPostExecute(String result) {
            try {

                if (outputFile != null) {
                    buttonText.setEnabled(true);
                    dialog.dismiss();
                    percent.setVisibility(View.INVISIBLE);
                    share.setVisibility(View.VISIBLE);
                    buttonText.setText(R.string.open);//If Download completed then change button text
                    buttonText.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View view) {
                            String str = apkStorage.getAbsolutePath();
                            String path = str + "/" + downloadFileName + ".pdf";
                            openPDFView(path);

                        }
                    });
                    share.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            String str = apkStorage.getAbsolutePath();
                            String path = str + "/" + downloadFileName + ".pdf";
                            File file=new File(path);
                            Uri pdf_uri=Uri.fromFile(file);
                            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                                pdf_uri=FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID+".provider",file);
                            }
                            share_file(pdf_uri,view);
                        }
                    });
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                        }
                    }, 3000);

                    Log.e(TAG, "Download Failed");

                }
            } catch (Exception e) {
                e.printStackTrace();
                buttonText.setText(R.string.download_fail);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        buttonText.setEnabled(true);
                        buttonText.setText(R.string.downloadAgain);
                    }
                }, 3000);
                Log.e(TAG, "Download Failed with Exception - " + e.getLocalizedMessage());

            }
            super.onPostExecute(result);
        }

        @Override
        protected String doInBackground(String... arg0) {
            try {
                if (new CheckForSDCard().isSDCardPresent()) {
                    apkStorage=context.getExternalFilesDir("downloadDirectory");
                } else
                    Toast.makeText(context, "Oops!! There is no SD Card.", Toast.LENGTH_SHORT).show();

                //If File is not present create directory
                if (!Objects.requireNonNull(apkStorage).exists()) {
                    apkStorage.mkdir();
                    Log.e(TAG, "Directory Created.");
                }

                outputFile = new File(apkStorage, downloadFileName + ".pdf");//Create Output file in Main File

                //Create New File if not present
                if (!outputFile.exists()) {
                    outputFile.createNewFile();
                    Log.e(TAG, "File Created");


                    URL url = new URL(downloadUrl);//Create Download URl
                  //  URLConnection c=url.openConnection();
                  HttpURLConnection c = (HttpURLConnection) url.openConnection();//Open Url Connection
                   // c.setRequestMethod("GET");//Set Request Method to "GET" since we are grtting data
                    c.setRequestProperty("Accept-Encoding", "identity");
                    c.connect();//connect the URL Connection

                    int lengthoffile=c.getContentLength();
                    System.out.println("length of file="+lengthoffile);
                    System.out.println("type  of content="+c.getContentType());
                    //If Connection response is not OK then show Logs
                    if (c.getResponseCode() != HttpURLConnection.HTTP_OK) {
                        Log.e(TAG, "Server returned HTTP " + c.getResponseCode()
                                + " " + c.getResponseMessage());
                    }


                    FileOutputStream fos = new FileOutputStream(outputFile);//Get OutputStream for NewFile Location

                    InputStream is = c.getInputStream();//Get InputStream for connection

                    byte[] buffer = new byte[1024];//Set buffer type
                    int len1 ;//init length
                    long total=0;
                    while ((len1 = is.read(buffer)) != -1) {
                        total+=len1;
                        if(lengthoffile<0){
                            lengthoffile=1000000;
                        }

                        publishProgress(""+(int)((total*100)/lengthoffile));
                        fos.write(buffer, 0, len1);//Write new file
                    }

                    fos.close();
                    is.close();


                }
            } catch (Exception e) {

                //Read exception if something went wrong
                e.printStackTrace();
                outputFile = null;
                Log.e(TAG, "Download Error Exception " + e.getMessage());
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
           String msg= "Downloading..."+(values[0])+"/100";
           percent.setText(msg);
          if(Integer.parseInt(values[0])==100){
            Toast.makeText(context,"Download Completed",Toast.LENGTH_SHORT).show();}
        }
    }




    private void openPDFView(String path){
        Intent intent=new Intent(context, PDF_Activity.class);
        intent.putExtra("PATH",path);
        System.out.println(path);
        context.startActivity(intent);
    }

    private void share_file(Uri pdf_uri, View view)
    {
        Intent shareIntent=new Intent(Intent.ACTION_SEND);
        shareIntent.setType("application/pdf");
        shareIntent.putExtra(Intent.EXTRA_STREAM,pdf_uri);
        view.getContext().startActivity(Intent.createChooser(shareIntent,"share"));

    }

}
