package com.example.dog;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PetNameAdapter extends ArrayAdapter<Pet> {

    private final Context context;
    private final ArrayList<Pet> petList;
    private ArrayList<Pet> filteredPetList;

    public PetNameAdapter(Context context, ArrayList<Pet> petList) {
        super(context, R.layout.pet_list_item, petList);
        this.context = context;
        this.petList = petList;
        this.filteredPetList = new ArrayList<>(petList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.pet_list_item, parent, false);

            holder = new ViewHolder();
            holder.petNameTextView = convertView.findViewById(R.id.petNameTextView);
            holder.petImageView = convertView.findViewById(R.id.petImageView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (position >= 0 && position < filteredPetList.size()) {
            Pet pet = filteredPetList.get(position);
            holder.petNameTextView.setText(pet.getName());

            Bitmap petImage = pet.getImage();
            if (petImage != null) {
                holder.petImageView.setImageBitmap(petImage);
            } else {
                // Set a placeholder image or handle empty image accordingly
            }
        }

        convertView.setOnClickListener(view -> {
            Pet selectedPet = filteredPetList.get(position);
            Intent intent = new Intent(context, view_detail_buyer.class);
            intent.putExtra("PET_ID", selectedPet.getId()); // Pass the ID or necessary data
            context.startActivity(intent);

        });

        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<Pet> filteredPets = new ArrayList<>();
                ArrayList<Pet> otherPets = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredPets.addAll(petList);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (Pet pet : petList) {
                        if (pet.getName().toLowerCase().contains(filterPattern)) {
                            filteredPets.add(pet);
                        } else {
                            otherPets.add(pet);
                        }
                    }
                }

                filteredPets.addAll(otherPets); // Add other pets after searched ones

                results.values = filteredPets;
                results.count = filteredPets.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredPetList.clear();
                filteredPetList.addAll((ArrayList<Pet>) results.values);
                notifyDataSetChanged();
            }
        };
    }


    static class ViewHolder {
        TextView petNameTextView;
        ImageView petImageView;
    }
}
