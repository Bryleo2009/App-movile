package com.example.ofsystem.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class CartItem implements Parcelable {
    private Producto producto;
    private int quantity;

    public CartItem(Producto producto, int quantity) {
        this.producto = producto;
        this.quantity = quantity;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getQuantity() {
        return quantity;
    }

    // Implementación Parcelable para poder pasar la instancia de CartItem entre fragments
    protected CartItem(Parcel in) {
        producto = in.readParcelable(Producto.class.getClassLoader());
        quantity = in.readInt();
    }

    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel in) {
            return new CartItem(in);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) producto, flags);
        dest.writeInt(quantity);
    }

    @Override
    public String toString() {
        return "CartItem{producto=" + producto + ", quantity=" + quantity + "}";
    }
}
