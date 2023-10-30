// Generated by view binder compiler. Do not edit!
package com.example.afinal.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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

public final class FragmentHomeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnAddKhuTro;

  @NonNull
  public final ConstraintLayout constraintLayout2;

  @NonNull
  public final ConstraintLayout constraintLayout3;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final ImageView imageView4;

  @NonNull
  public final ImageView imageView5;

  @NonNull
  public final ImageView imageView8;

  @NonNull
  public final ImageView imageView9;

  @NonNull
  public final ListView lvKhuTro;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final TextView textView4;

  @NonNull
  public final TextView textView7;

  @NonNull
  public final TextView textView8;

  @NonNull
  public final TextView tvDate;

  @NonNull
  public final TextView welcomeUsername;

  private FragmentHomeBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnAddKhuTro,
      @NonNull ConstraintLayout constraintLayout2, @NonNull ConstraintLayout constraintLayout3,
      @NonNull ImageView imageView, @NonNull ImageView imageView2, @NonNull ImageView imageView4,
      @NonNull ImageView imageView5, @NonNull ImageView imageView8, @NonNull ImageView imageView9,
      @NonNull ListView lvKhuTro, @NonNull TextView textView, @NonNull TextView textView3,
      @NonNull TextView textView4, @NonNull TextView textView7, @NonNull TextView textView8,
      @NonNull TextView tvDate, @NonNull TextView welcomeUsername) {
    this.rootView = rootView;
    this.btnAddKhuTro = btnAddKhuTro;
    this.constraintLayout2 = constraintLayout2;
    this.constraintLayout3 = constraintLayout3;
    this.imageView = imageView;
    this.imageView2 = imageView2;
    this.imageView4 = imageView4;
    this.imageView5 = imageView5;
    this.imageView8 = imageView8;
    this.imageView9 = imageView9;
    this.lvKhuTro = lvKhuTro;
    this.textView = textView;
    this.textView3 = textView3;
    this.textView4 = textView4;
    this.textView7 = textView7;
    this.textView8 = textView8;
    this.tvDate = tvDate;
    this.welcomeUsername = welcomeUsername;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_add_khu_tro;
      Button btnAddKhuTro = ViewBindings.findChildViewById(rootView, id);
      if (btnAddKhuTro == null) {
        break missingId;
      }

      id = R.id.constraintLayout2;
      ConstraintLayout constraintLayout2 = ViewBindings.findChildViewById(rootView, id);
      if (constraintLayout2 == null) {
        break missingId;
      }

      id = R.id.constraintLayout3;
      ConstraintLayout constraintLayout3 = ViewBindings.findChildViewById(rootView, id);
      if (constraintLayout3 == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.imageView2;
      ImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      id = R.id.imageView4;
      ImageView imageView4 = ViewBindings.findChildViewById(rootView, id);
      if (imageView4 == null) {
        break missingId;
      }

      id = R.id.imageView5;
      ImageView imageView5 = ViewBindings.findChildViewById(rootView, id);
      if (imageView5 == null) {
        break missingId;
      }

      id = R.id.imageView8;
      ImageView imageView8 = ViewBindings.findChildViewById(rootView, id);
      if (imageView8 == null) {
        break missingId;
      }

      id = R.id.imageView9;
      ImageView imageView9 = ViewBindings.findChildViewById(rootView, id);
      if (imageView9 == null) {
        break missingId;
      }

      id = R.id.lv_khu_tro;
      ListView lvKhuTro = ViewBindings.findChildViewById(rootView, id);
      if (lvKhuTro == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      id = R.id.textView4;
      TextView textView4 = ViewBindings.findChildViewById(rootView, id);
      if (textView4 == null) {
        break missingId;
      }

      id = R.id.textView7;
      TextView textView7 = ViewBindings.findChildViewById(rootView, id);
      if (textView7 == null) {
        break missingId;
      }

      id = R.id.textView8;
      TextView textView8 = ViewBindings.findChildViewById(rootView, id);
      if (textView8 == null) {
        break missingId;
      }

      id = R.id.tv_date;
      TextView tvDate = ViewBindings.findChildViewById(rootView, id);
      if (tvDate == null) {
        break missingId;
      }

      id = R.id.welcome_username;
      TextView welcomeUsername = ViewBindings.findChildViewById(rootView, id);
      if (welcomeUsername == null) {
        break missingId;
      }

      return new FragmentHomeBinding((ConstraintLayout) rootView, btnAddKhuTro, constraintLayout2,
          constraintLayout3, imageView, imageView2, imageView4, imageView5, imageView8, imageView9,
          lvKhuTro, textView, textView3, textView4, textView7, textView8, tvDate, welcomeUsername);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}