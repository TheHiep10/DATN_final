// Generated by view binder compiler. Do not edit!
package com.example.afinal.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.afinal.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class CustomNotificationLayoutBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView info;

  @NonNull
  public final TextView time;

  @NonNull
  public final TextView title;

  private CustomNotificationLayoutBinding(@NonNull RelativeLayout rootView, @NonNull TextView info,
      @NonNull TextView time, @NonNull TextView title) {
    this.rootView = rootView;
    this.info = info;
    this.time = time;
    this.title = title;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static CustomNotificationLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static CustomNotificationLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.custom_notification_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static CustomNotificationLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.info;
      TextView info = ViewBindings.findChildViewById(rootView, id);
      if (info == null) {
        break missingId;
      }

      id = R.id.time;
      TextView time = ViewBindings.findChildViewById(rootView, id);
      if (time == null) {
        break missingId;
      }

      id = R.id.title;
      TextView title = ViewBindings.findChildViewById(rootView, id);
      if (title == null) {
        break missingId;
      }

      return new CustomNotificationLayoutBinding((RelativeLayout) rootView, info, time, title);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
