package com.example.course;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DeleteCarsAdapter extends RecyclerView.Adapter<DeleteCarsAdapter.CarViewHolder> {

    private List<Car> cars;
    private CarDAO carDAO;

    public DeleteCarsAdapter(List<Car> cars, CarDAO carDAO) {
        this.cars = cars;
        this.carDAO = carDAO;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car_delete, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = cars.get(position);
        holder.tvName.setText(car.getName());
        holder.tvDescription.setText(car.getDescription());
        Bitmap bitmap = BitmapFactory.decodeByteArray(car.getImage(), 0, car.getImage().length);
        holder.ivImage.setImageBitmap(bitmap);

        holder.btnDelete.setOnClickListener(v -> {
            carDAO.open();
            boolean deleted = carDAO.deleteCar(car.getId());
            carDAO.close();
            if (deleted) {
                cars.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, cars.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    static class CarViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvDescription;
        ImageView ivImage;
        Button btnDelete;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivImage = itemView.findViewById(R.id.ivImage);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
