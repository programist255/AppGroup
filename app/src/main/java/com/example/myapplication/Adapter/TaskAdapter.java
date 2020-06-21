package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Pag.Task;
import com.example.myapplication.R;
import com.example.myapplication.ShowTaskActivity;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ProductViewHolder> {

    private List<Task> productListTask;
    private static Context mCtx;
    public static String Title1;
    public static String Text1;
    public TaskAdapter(Context mCtx, List<Task> productList) {
        this.mCtx = mCtx;
        this.productListTask = productList;
    }
    public TaskAdapter(Context mCtx) {
        this.mCtx = mCtx;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.task, null);
        return new TaskAdapter.ProductViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Task product = productListTask.get(position);
        holder.Title.setText(String.valueOf(product.getTitle()));
        holder.Text.setText(String.valueOf(product.getText()));
    }
    @Override
    public int getItemCount() {
        return productListTask.size();
    }
    public class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView Title, Text;
        public ProductViewHolder(View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.TitleTask);
            Text = itemView.findViewById(R.id.TextTask);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Title1 = ((TextView) v.findViewById(R.id.TitleTask)).getText().toString();
                    Text1 = ((TextView) v.findViewById(R.id.TextTask)).getText().toString();
                    Intent integer = new Intent(mCtx.getApplicationContext(), ShowTaskActivity.class);
//                    integer.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
                    mCtx.startActivity(integer);
                }
            });
        }
    }
}
