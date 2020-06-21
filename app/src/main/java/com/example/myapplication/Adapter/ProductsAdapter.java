package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.myapplication.Pag.Conctans;
import com.example.myapplication.Pag.Group;
import com.example.myapplication.GroupActivity;
import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {
    private static Context mCtx;
    private List<Group> productList;
    public static String NameGR;
    public static ImageView ImageGr;
    public ProductsAdapter(Context mCtx, List<Group> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }
    public ProductsAdapter(Context mCtx) {
        this.mCtx = mCtx;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.add_group, null);
        return new ProductViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Group product = productList.get(position);
        Glide.with(mCtx)
                .load(product.getImage())
                .into(holder.imageGroupBaz);

        holder.nameGroupBaz.setText(String.valueOf(product.getName()));
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView nameGroupBaz;
        public static ImageView imageGroupBaz;
        EditText Pass;
        public ProductViewHolder(View itemView) {
            super(itemView);
            nameGroupBaz = itemView.findViewById(R.id.nameGroupBaz);
            imageGroupBaz = itemView.findViewById(R.id.imageGroupBaz);
            Pass = itemView.findViewById(R.id.PasswordGR);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    NameGR = ((TextView) v.findViewById(R.id.nameGroupBaz)).getText().toString();
                    ImageGr = v.findViewById(R.id.imageGroupBaz);
                     group();
                }
            });
        }
        void group(){
            StringRequest stringRequest = new StringRequest(Request.Method.DEPRECATED_GET_OR_POST, Conctans.URL_GET_GROUP,
                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            Log.i("tagconvertstr", "[" + response + "]");
                            try {
                                JSONArray jsono = new JSONArray(response);
                                String js = jsono.getString(0).trim();
                                System.out.println("js: " + js);
                                if (js.equals("1")) {
                                    Intent intent = new Intent(mCtx.getApplicationContext(), GroupActivity.class);
                                    mCtx.startActivity(intent);
                                }else
                                {
                                    select();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    System.out.println("Id: " + LoginActivity.ID);
                    String Id = LoginActivity.ID.toString();
                    String name = NameGR.toString();
                    System.out.println(name);
                    params.put("Id", Id);
                    params.put("NameGroup", name);
                    return params;
                }
            };
            Volley.newRequestQueue(mCtx).add(stringRequest);
        }
        public void select(){
            LayoutInflater li = LayoutInflater.from(mCtx);
            final View promptsView = li.inflate(R.layout.user_password_in_group, null);
            AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(mCtx, R.style.MyAlertDialogStyle);
            mDialogBuilder.setView(promptsView);
            final EditText userInput = (EditText) promptsView.findViewById(R.id.PasswordGR);
            mDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Зайти",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {

                                    final String Pass = userInput.getText().toString().trim();
                                    final String[] Resolt = new String[1];
                                    StringRequest stringRequest = new StringRequest(Request.Method.DEPRECATED_GET_OR_POST, Conctans.URL_CONNECT_GROUP,
                                            new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    Log.i("tagconvertstr", "[" + response + "]");
                                                    try {
                                                        JSONArray jsono = new JSONArray(response);
                                                        Resolt[0] = jsono.getString(0).trim();
                                                        System.out.println(Resolt[0]);
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                    if(Resolt[0].equals("1"))
                                                    {
                                                        Intent intent = new Intent(mCtx.getApplicationContext(), GroupActivity.class);
                                                        mCtx.startActivity(intent);
                                                    }
                                                    else
                                                        Toast.makeText(mCtx, "Error", Toast.LENGTH_LONG).show();
                                                }
                                            },
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                }
                                            }){
                                        @Override
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            Map<String, String> params = new HashMap<>();
                                            String Id = LoginActivity.ID.toString();
                                            String name = ProductsAdapter.NameGR.toString();
                                            System.out.println(name);
                                            System.out.println(Pass);
                                            params.put("Id", Id);
                                            params.put("Password", Pass);
                                            params.put("NameGroup", name);
                                            return params;
                                        }
                                    };
                                    Volley.newRequestQueue(mCtx).add(stringRequest);
                                }
                            })
                    .setNegativeButton("Вихід",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alertDialog = mDialogBuilder.create();
            alertDialog.show();

        }
    }

}
