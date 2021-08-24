package com.example.ram.filelist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.content.FileProvider;
import com.example.ram.BuildConfig;
import com.example.ram.R;
import java.io.File;
import java.util.ArrayList;
@SuppressWarnings("deprecation")
public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<PDFDoc> pdfDocs;

    CustomAdapter(Context context, ArrayList<PDFDoc> pdfDocs) {
        this.context = context;
        this.pdfDocs = pdfDocs;
    }

    @Override
    public int getCount() {
        return pdfDocs.size();
    }

    @Override
    public Object getItem(int i) {
        return pdfDocs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.model_for_file_list,viewGroup,false);
        }
        final PDFDoc pdfDoc=(PDFDoc)this.getItem(i);
        TextView nameTxt=view.findViewById(R.id.name_txt);
        Button open_pdf=view.findViewById(R.id.openpdf);
        //Bind data
        nameTxt.setText(pdfDoc.getName());
        final Button share_pdf=view.findViewById(R.id.sharepdf);
        share_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path=pdfDoc.getPath();
                File file=new File(path);
                Uri pdf_uri=Uri.fromFile(file);
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                    pdf_uri= FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID+".provider",file);
                }
                share_file(pdf_uri,v);
            }
        });

        //click
        open_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPDFView(pdfDoc.getPath());
            }
        });

        return view;
    }

    private void openPDFView(String path){
        Intent intent=new Intent(context,PDF_Activity.class);
        intent.putExtra("PATH",path);
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
