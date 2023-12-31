// Generated by view binder compiler. Do not edit!
package com.example.afinal.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.afinal.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class LayoutThemKhuTroBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final ImageView imgViewBackground;

  @NonNull
  public final ImageView imgViewIconMap;

  @NonNull
  public final ImageView imgViewIconSmartLock;

  @NonNull
  public final ImageView imgViewIconStatus;

  @NonNull
  public final TextView tvAddress;

  @NonNull
  public final TextView tvDevice;

  @NonNull
  public final TextView tvStatus;

  @NonNull
  public final TextView tvTenKhuTro;

  private LayoutThemKhuTroBinding(@NonNull CardView rootView, @NonNull ImageView imgViewBackground,
      @NonNull ImageView imgViewIconMap, @NonNull ImageView imgViewIconSmartLock,
      @NonNull ImageView imgViewIconStatus, @NonNull TextView tvAddress, @NonNull TextView tvDevice,
      @NonNull TextView tvStatus, @NonNull TextView tvTenKhuTro) {
    this.rootView = rootView;
    this.imgViewBackground = imgViewBackground;
    this.imgViewIconMap = imgViewIconMap;
    this.imgViewIconSmartLock = imgViewIconSmartLock;
    this.imgViewIconStatus = imgViewIconStatus;
    this.tvAddress = tvAddress;
    this.tvDevice = tvDevice;
    this.tvStatus = tvStatus;
    this.tvTenKhuTro = tvTenKhuTro;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static LayoutThemKhuTroBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LayoutThemKhuTroBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.layout_them_khu_tro, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LayoutThemKhuTroBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imgView_background;
      ImageView imgViewBackground = ViewBindings.findChildViewById(rootView, id);
      if (imgViewBackground == null) {
        break missingId;
      }

      id = R.id.imgView_icon_map;
      ImageView imgViewIconMap = ViewBindings.findChildViewById(rootView, id);
      if (imgViewIconMap == null) {
        break missingId;
      }

      id = R.id.imgView_icon_smart_lock;
      ImageView imgViewIconSmartLock = ViewBindings.findChildViewById(rootView, id);
      if (imgViewIconSmartLock == null) {
        break missingId;
      }

      id = R.id.imgView_icon_status;
      ImageView imgViewIconStatus = ViewBindings.findChildViewById(rootView, id);
      if (imgViewIconStatus == null) {
        break missingId;
      }

      id = R.id.tv_address;
      TextView tvAddress = ViewBindings.findChildViewById(rootView, id);
      if (tvAddress == null) {
        break missingId;
      }

      id = R.id.tv_device;
      TextView tvDevice = ViewBindings.findChildViewById(rootView, id);
      if (tvDevice == null) {
        break missingId;
      }

      id = R.id.tv_status;
      TextView tvStatus = ViewBindings.findChildViewById(rootView, id);
      if (tvStatus == null) {
        break missingId;
      }

      id = R.id.tv_ten_khu_tro;
      TextView tvTenKhuTro = ViewBindings.findChildViewById(rootView, id);
      if (tvTenKhuTro == null) {
        break missingId;
      }

      return new LayoutThemKhuTroBinding((CardView) rootView, imgViewBackground, imgViewIconMap,
          imgViewIconSmartLock, imgViewIconStatus, tvAddress, tvDevice, tvStatus, tvTenKhuTro);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
