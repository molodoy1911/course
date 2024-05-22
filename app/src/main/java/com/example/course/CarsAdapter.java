package com.example.course;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarViewHolder> {

    private List<Car> cars;

    public CarsAdapter(List<Car> cars) {
        this.cars = cars;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = cars.get(position);
        holder.tvName.setText(car.getName());
        holder.tvDescription.setText(car.getDescription());
        Bitmap bitmap = BitmapFactory.decodeByteArray(car.getImage(), 0, car.getImage().length);
        holder.ivImage.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    static class CarViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvDescription;
        ImageView ivImage;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
