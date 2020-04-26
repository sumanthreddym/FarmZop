package com.farmzop.application.OrdersDisplayAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.farmzop.application.R;

import java.util.ArrayList;

/**
 * Created by Gaurav on 12-01-2016.
 */
public class OrdersListAdapter  extends ArrayAdapter<OrderDetails> {

    private Bitmap greenTick;
    private Bitmap grayTick;

    public OrdersListAdapter(Context context, ArrayList<OrderDetails> details) {

        super(context,0,details);

        //load the images greenTick and GrayTick
        grayTick= BitmapFactory.decodeResource(context.getResources(), R.drawable.graytickmark);
       greenTick= BitmapFactory.decodeResource(context.getResources(), R.drawable.greentickmark);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        OrderDetails tmp = getItem(position);

        if(tmp.getType()==0)
        {
            convertView=getOrderNormalView(tmp,convertView,parent);
        }
        else {
            convertView=getOrderCancelledView(tmp, convertView, parent);
        }


        // Return the completed view to render on screen
        return convertView;

    }

    private View getOrderNormalView(OrderDetails tmp, View convertView, final ViewGroup parent) {

        OrderDetailsNormal temp=(OrderDetailsNormal)tmp;
        ViewHolderNormal holder ;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.individual_order_layout, parent, false);

            holder=new ViewHolderNormal();
            // Lookup view for data population
            holder.orderId = (TextView) convertView.findViewById(R.id.order_id);
            holder.orderDate = (TextView) convertView.findViewById(R.id.order_date);
            holder.amount = (TextView) convertView.findViewById(R.id.order_amount);
            holder.paymentMode = (TextView) convertView.findViewById(R.id.payment_mode);
            holder.packed = (ImageView) convertView.findViewById(R.id.order_packed_image);
            holder.delivering = (ImageView) convertView.findViewById(R.id.order_delivering_image);
            holder.delivered = (ImageView) convertView.findViewById(R.id.order_delivered_image);
            holder.cancelBtn=(Button)convertView.findViewById(R.id.order_cancel_button);

            convertView.setTag(holder);
        }
        holder=(ViewHolderNormal)convertView.getTag();
        // Populate the data into the template view using the data object
        holder.orderId.setText(String.valueOf(temp.getOrderId()));
        holder.orderDate.setText( temp.getOrderDate());
        holder.amount.setText(String.valueOf(temp.getAmount()));
        holder.paymentMode.setText(String.valueOf(temp.getPaymentMode()));

        holder.cancelBtn.setVisibility(View.VISIBLE);

        //set onclick listener to cancel button
        holder.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(),"Orders Cancel Button Clicked",Toast.LENGTH_SHORT).show();
            }
        });


        if(temp.isPacked())
            holder.packed.setImageBitmap(greenTick);
        else
            holder.packed.setImageBitmap(grayTick);

        if(temp.isDelivering())
            holder.delivering.setImageBitmap(greenTick);
        else
            holder.delivering.setImageBitmap(grayTick);

        if(temp.isDelivered()) {
            holder.delivered.setImageBitmap(greenTick);

            //if product is delivered remove the cancel btn from display
            holder.cancelBtn.setVisibility(View.GONE);
        }
        else
            holder.delivered.setImageBitmap(grayTick);

        return convertView;
    }

    private View getOrderCancelledView(OrderDetails tmp, View convertView, ViewGroup parent) {
        OrderDetailsCancelled temp=(OrderDetailsCancelled)tmp;
        ViewHolderCancelled holder ;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.individual_order_layout_cancelled, parent, false);

            holder=new ViewHolderCancelled();
            // Lookup view for data population
            holder.orderId = (TextView) convertView.findViewById(R.id.order_id_cancelled);
            holder.orderDate = (TextView) convertView.findViewById(R.id.order_date_cancelled);
            holder.amount = (TextView) convertView.findViewById(R.id.order_amount_cancelled);
            holder.paymentMode = (TextView) convertView.findViewById(R.id.payment_mode_cancelled);

            convertView.setTag(holder);
        }
        holder=(ViewHolderCancelled)convertView.getTag();
        // Populate the data into the template view using the data object
        holder.orderId.setText(String.valueOf(temp.getOrderId()));
        holder.orderDate.setText( temp.getOrderDate());
        holder.amount.setText(String.valueOf(temp.getAmount()));
        holder.paymentMode.setText(String.valueOf(temp.getPaymentMode()));

        return convertView;
    }

    private static class ViewHolderNormal
    {
        TextView orderId;
        TextView orderDate;
        TextView amount;
        TextView paymentMode;
        ImageView packed;
        ImageView delivering;
        ImageView delivered;
        Button cancelBtn;

    }

    private static class ViewHolderCancelled
    {
        TextView orderId;
        TextView orderDate;
        TextView amount;
        TextView paymentMode;
    }

    public void setGrayTick(Bitmap grayTick) {
        this.grayTick = grayTick;
    }

    public void setGreenTick(Bitmap greenTick) {
        this.greenTick = greenTick;
    }

    @Override
    public int getViewTypeCount() {
        return 2;//since 2 view types
    }

    @Override
    public int getItemViewType(int position) {
        return this.getItem(position).getType();
    }
}
