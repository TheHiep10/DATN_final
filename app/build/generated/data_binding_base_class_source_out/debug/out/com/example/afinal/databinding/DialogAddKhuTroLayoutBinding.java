// Generated by view binder compiler. Do not edit!
package com.example.afinal.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.afinal.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogAddKhuTroLayoutBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText editTextDiaChi;

  @NonNull
  public final EditText editTextMaThietBi;

  @NonNull
  public final EditText editTextTenKhuTro;

  @NonNull
  public final ImageView imageView3;

  @NonNull
  public final ImageButton imgBtnHuy;

  @NonNull
  public final ImageButton imgBtnXacNhan;

  @NonNull
  public final TextView textView12;

  @NonNull
  public final TextView textView14;

  @NonNull
  public final TextView textView15;

  @NonNull
  public final TextView textView5;

  @NonNull
  public final TextView textView9;

  private DialogAddKhuTroLayoutBinding(@NonNull ConstraintLayout rootView,
      @NonNull EditText editTextDiaChi, @NonNull EditText editTextMaThietBi,
      @NonNull EditText editTextTenKhuTro, @NonNull ImageView imageView3,
      @NonNull ImageButton imgBtnHuy, @NonNull ImageButton imgBtnXacNhan,
      @NonNull TextView textView12, @NonNull TextView textView14, @NonNull TextView textView15,
      @NonNull TextView textView5, @NonNull TextView textView9) {
    this.rootView = rootView;
    this.editTextDiaChi = editTextDiaChi;
    this.editTextMaThietBi = editTextMaThietBi;
    this.editTextTenKhuTro = editTextTenKhuTro;
    this.imageView3 = imageView3;
    this.imgBtnHuy = imgBtnHuy;
    this.imgBtnXacNhan = imgBtnXacNhan;
    this.textView12 = textView12;
    this.textView14 = textView14;
    this.textView15 = textView15;
    this.textView5 = textView5;
    this.textView9 = textView9;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogAddKhuTroLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogAddKhuTroLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_add_khu_tro_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogAddKhuTroLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.edit_text_dia_chi;
      EditText editTextDiaChi = ViewBindings.findChildViewById(rootView, id);
      if (editTextDiaChi == null) {
        break missingId;
      }

      id = R.id.edit_text_ma_thiet_bi;
      EditText editTextMaThietBi = ViewBindings.findChildViewById(rootView, id);
      if (editTextMaThietBi == null) {
        break missingId;
      }

      id = R.id.edit_text_ten_khu_tro;
      EditText editTextTenKhuTro = ViewBindings.findChildViewById(rootView, id);
      if (editTextTenKhuTro == null) {
        break missingId;
      }

      id = R.id.imageView3;
      ImageView imageView3 = ViewBindings.findChildViewById(rootView, id);
      if (imageView3 == null) {
        break missingId;
      }

      id = R.id.img_btn_huy;
      ImageButton imgBtnHuy = ViewBindings.findChildViewById(rootView, id);
      if (imgBtnHuy == null) {
        break missingId;
      }

      id = R.id.img_btn_xac_nhan;
      ImageButton imgBtnXacNhan = ViewBindings.findChildViewById(rootView, id);
      if (imgBtnXacNhan == null) {
        break missingId;
      }

      id = R.id.textView12;
      TextView textView12 = ViewBindings.findChildViewById(rootView, id);
      if (textView12 == null) {
        break missingId;
      }

      id = R.id.textView14;
      TextView textView14 = ViewBindings.findChildViewById(rootView, id);
      if (textView14 == null) {
        break missingId;
      }

      id = R.id.textView15;
      TextView textView15 = ViewBindings.findChildViewById(rootView, id);
      if (textView15 == null) {
        break missingId;
      }

      id = R.id.textView5;
      TextView textView5 = ViewBindings.findChildViewById(rootView, id);
      if (textView5 == null) {
        break missingId;
      }

      id = R.id.textView9;
      TextView textView9 = ViewBindings.findChildViewById(rootView, id);
      if (textView9 == null) {
        break missingId;
      }

      return new DialogAddKhuTroLayoutBinding((ConstraintLayout) rootView, editTextDiaChi,
          editTextMaThietBi, editTextTenKhuTro, imageView3, imgBtnHuy, imgBtnXacNhan, textView12,
          textView14, textView15, textView5, textView9);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
