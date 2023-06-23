package com.example.ofsystem.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class CartItems implements Parcelable {
    private List<CartItem> items;

    public CartItems() {
        items = new ArrayList<>();
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public List<CartItem> getItems() {
        return items;
    }

    // Otros métodos y funcionalidades que puedas necesitar

    // Implementación Parcelable para poder pasar la instancia de CartItems entre fragments
    protected CartItems(Parcel in) {
        items = in.createTypedArrayList(CartItem.CREATOR);
    }

    public static final Creator<CartItems> CREATOR = new Creator<CartItems>() {
        @Override
        public CartItems createFromParcel(Parcel in) {
            return new CartItems(in);
        }

        @Override
        public CartItems[] newArray(int size) {
            return new CartItems[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(items);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CartItems: ");
        for (CartItem item : items) {
            sb.append(item.toString());
            sb.append(", ");
        }
        // Eliminar la última coma y espacio
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }
    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void removeItem(int position) {
        if (items != null && position >= 0 && position < items.size()) {
            items.remove(position);
        }
    }
}

