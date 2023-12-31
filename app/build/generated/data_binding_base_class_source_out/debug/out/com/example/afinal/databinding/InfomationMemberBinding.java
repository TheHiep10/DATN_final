// Generated by view binder compiler. Do not edit!
package com.example.afinal.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.afinal.R;
import com.example.afinal.custom_textView.RobotoBlackTextView;
import com.example.afinal.custom_textView.RobotoMediumTextView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class InfomationMemberBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final ImageView icon;

  @NonNull
  public final ImageView imagePhone;

  @NonNull
  public final RobotoBlackTextView nameMember;

  @NonNull
  public final CardView recCard;

  @NonNull
  public final RelativeLayout relative;

  @NonNull
  public final ImageView sex;

  @NonNull
  public final RobotoMediumTextView starttime;

  @NonNull
  public final RobotoMediumTextView textphone;

  @NonNull
  public final RobotoMediumTextView texttime;

  private InfomationMemberBinding(@NonNull CardView rootView, @NonNull ImageView icon,
      @NonNull ImageView imagePhone, @NonNull RobotoBlackTextView nameMember,
      @NonNull CardView recCard, @NonNull RelativeLayout relative, @NonNull ImageView sex,
      @NonNull RobotoMediumTextView starttime, @NonNull RobotoMediumTextView textphone,
      @NonNull RobotoMediumTextView texttime) {
    this.rootView = rootView;
    this.icon = icon;
    this.imagePhone = imagePhone;
    this.nameMember = nameMember;
    this.recCard = recCard;
    this.relative = relative;
    this.sex = sex;
    this.starttime = starttime;
    this.textphone = textphone;
    this.texttime = texttime;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static InfomationMemberBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static InfomationMemberBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.infomation_member, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static InfomationMemberBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.icon;
      ImageView icon = ViewBindings.findChildViewById(rootView, id);
      if (icon == null) {
        break missingId;
      }

      id = R.id.image_phone;
      ImageView imagePhone = ViewBindings.findChildViewById(rootView, id);
      if (imagePhone == null) {
        break missingId;
      }

      id = R.id.name_member;
      RobotoBlackTextView nameMember = ViewBindings.findChildViewById(rootView, id);
      if (nameMember == null) {
        break missingId;
      }

      CardView recCard = (CardView) rootView;

      id = R.id.relative;
      RelativeLayout relative = ViewBindings.findChildViewById(rootView, id);
      if (relative == null) {
        break missingId;
      }

      id = R.id.sex;
      ImageView sex = ViewBindings.findChildViewById(rootView, id);
      if (sex == null) {
        break missingId;
      }

      id = R.id.starttime;
      RobotoMediumTextView starttime = ViewBindings.findChildViewById(rootView, id);
      if (starttime == null) {
        break missingId;
      }

      id = R.id.textphone;
      RobotoMediumTextView textphone = ViewBindings.findChildViewById(rootView, id);
      if (textphone == null) {
        break missingId;
      }

      id = R.id.texttime;
      RobotoMediumTextView texttime = ViewBindings.findChildViewById(rootView, id);
      if (texttime == null) {
        break missingId;
      }

      return new InfomationMemberBinding((CardView) rootView, icon, imagePhone, nameMember, recCard,
          relative, sex, starttime, textphone, texttime);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
