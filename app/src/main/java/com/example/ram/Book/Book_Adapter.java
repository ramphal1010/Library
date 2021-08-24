package com.example.ram.Book;




import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ram.R;
import com.example.ram.offline.DownloadTask;
import java.util.ArrayList;
@SuppressWarnings("deprecation")

public class Book_Adapter extends BaseAdapter {

    private Context context;
    private TextView percent;
    private ArrayList<Book_Down> downs_Modes;


    Book_Adapter(Context context, ArrayList<Book_Down> downs_Modes, TextView textView) {
        this.context = context;
        this.downs_Modes = downs_Modes;
        this.percent = textView;


    }
    @Override
    public int getCount() {
        return downs_Modes.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.layout,viewGroup,false);
        }
        final TextView filename=view.findViewById(R.id.name);
        final Button download=view.findViewById(R.id.download);
        final Button share=view.findViewById(R.id.share);
        filename.setText(downs_Modes.get(position).getName());
        download.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String link=downs_Modes.get(position).getLink();
               if(link!=null) {
                   new DownloadTask(context, download, share, downs_Modes.get(position).getName(), downs_Modes.get(position).getLink(), percent);
               }
               else{
                   Toast.makeText(context,"Pdf Not Available",Toast.LENGTH_SHORT).show();
               }

            }
        });
        return view;
    }
}

