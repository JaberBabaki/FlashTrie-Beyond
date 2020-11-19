package com.ario.flashtriebeyond.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ario.flashtriebeyond.R;
import com.ario.flashtriebeyond.model.PrefixNextHob;

import java.util.List;

/**
 * Created by jaber babaki reskti on 7/22/2016.
 */
public class AdapterIP extends RecyclerView.Adapter<AdapterIP.NewsViewHolder> {


  private Context context;
  private List<PrefixNextHob> pres;

  public class NewsViewHolder extends RecyclerView.ViewHolder {
    private TextView prefix;
    private TextView nextHop;
    private TextView txtPrefix;
    private CardView cardView;

    public NewsViewHolder(View itemView) {
      super(itemView);
      prefix = (TextView) itemView.findViewById(R.id.enterPort);
      txtPrefix = (TextView) itemView.findViewById(R.id.txt_prefix);
      nextHop = (TextView) itemView.findViewById(R.id.next_hop);
      cardView = (CardView) itemView.findViewById(R.id.card_item);
    }
  }

  public AdapterIP(Context context, List<PrefixNextHob> posts) {
    this.context = context;
    this.pres = posts;
  }

  @Override
  public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_ip, parent, false);
    return new NewsViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final NewsViewHolder holder, int position) {
    final PrefixNextHob pre = pres.get(position);
    /*final Shohda shohda = new Shohda();
    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
    sharingIntent.setType("text/plain");
    sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
    sharingIntent.putExtra(Intent.EXTRA_TEXT, tiok);*/
    holder.prefix.setText(pre.getEnterPort());
    holder.nextHop.setText(pre.getNextHop());
    holder.txtPrefix.setText(pre.getPrefix());
  }

  @Override
  public int getItemCount() {
    return pres.size();
  }

}
