package com.example.blackmask.cloudgallery;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blackmask.cloudgallery.Retrofit.ImageDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Blackma$k on 15/05/2018.
 */

public class ImageListAdapter  extends RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>{
    List<ImageDetails> imagelist;
    Context context;


    public ImageListAdapter(Context context, List<ImageDetails> imagelist)
    {
        this.context = context;
        this.imagelist=imagelist;

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ImageListAdapter.ImageViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.img_list_views,viewGroup,false);
        ImageViewHolder imghold=new ImageViewHolder(view);
        return imghold;
    }

    @Override
    public void onBindViewHolder(ImageListAdapter.ImageViewHolder holder, final int position) {

        holder.title.setText(imagelist.get(position).getTitle());
        String url=imagelist.get(position).getImgURI();
        Log.e("URL  : ",url);

        Picasso.get().load(url)
                .placeholder(R.drawable.loading_symbol)
                .into(holder.myimages);
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog myDialog;
                String url1=imagelist.get(position).getImgURI().toString();
                TextView titletxt;
                ImageView img;
                myDialog = new Dialog(context);
                myDialog.setContentView(R.layout.custom_popup);
                titletxt=(TextView)myDialog.findViewById(R.id.Pop_imgtitle);
                img=(ImageView)myDialog.findViewById(R.id.Pop_img);
                titletxt.setText(imagelist.get(position).getTitle().toString());
                Picasso.get().load(url1)
                        .fit()
//                        .centerCrop()
                        .placeholder(R.drawable.loading_symbol)
                        //.error(R.drawable.loading_symbol)
                        .into(img);
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return imagelist.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView title;
        CircleImageView myimages;

        ImageViewHolder(View view){
            super(view);


            cv=(CardView)view.findViewById(R.id.imageListCard);
            title=(TextView)view.findViewById(R.id.imgtitle);
//            myimages=(ImageView)view.findViewById(R.id.imgframe);
             myimages = (CircleImageView)view.findViewById(R.id.imgframe);
        }
    }
}
