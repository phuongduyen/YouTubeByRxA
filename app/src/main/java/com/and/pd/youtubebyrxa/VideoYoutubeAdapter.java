package com.and.pd.youtubebyrxa;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoYoutubeAdapter extends RecyclerView.Adapter<VideoYoutubeAdapter.MyViewHolder> {
    private Context context;
    private List<VideoYoutube> videoYoutubeList;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView thumbnail;
        public TextView video_title, video_id, video_description;
        public RelativeLayout video_view;

        public MyViewHolder(View view) {

            super(view);

            thumbnail = (ImageView) view.findViewById(R.id.video_thumbnail);
            video_title = (TextView) view.findViewById(R.id.video_title);
            video_id = (TextView) view.findViewById(R.id.video_id);
            video_description = (TextView) view.findViewById(R.id.video_description);
            video_view = (RelativeLayout) view.findViewById(R.id.video_view);
        }
    }
    public VideoYoutubeAdapter(Context mContext, List<VideoYoutube> mVideoList) {
        this.context = mContext;
        this.videoYoutubeList = mVideoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_video_youtube, parent, false);

        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {

        final VideoYoutube singleVideo = videoYoutubeList.get(position);

        holder.video_id.setText("Video ID : "+singleVideo.getVideoId()+" ");
        holder.video_title.setText(singleVideo.getTitle());
        holder.video_description.setText(singleVideo.getDescription());

        Picasso.get()
                .load(singleVideo.getThumbnail())
                .resize(480,270)
                .centerCrop()
                .into(holder.thumbnail);

        holder.video_view.setOnClickListener(new View.OnClickListener() {

            //onClick method called when the view is clicked
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, PlayVideoActivity.class);

                intent.putExtra("VIDEO_ID", singleVideo.getVideoId());
                intent.putExtra("VIDEO_TITLE",singleVideo.getTitle());
                intent.putExtra("VIDEO_DESC",singleVideo.getDescription());

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoYoutubeList.size();
    }
}
