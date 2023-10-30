// Generated by view binder compiler. Do not edit!
package com.example.afinal.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.afinal.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogLayoutBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnApply;

  @NonNull
  public final Button btnClear;

  @NonNull
  public final EditText edEmail;

  @NonNull
  public final EditText edPassword;

  @NonNull
  public final EditText edPhonenum;

  @NonNull
  public final EditText edUsername;

  private DialogLayoutBinding(@NonNull LinearLayout rootView, @NonNull Button btnApply,
      @NonNull Button btnClear, @NonNull EditText edEmail, @NonNull EditText edPassword,
      @NonNull EditText edPhonenum, @NonNull EditText edUsername) {
    this.rootView = rootView;
    this.btnApply = btnApply;
    this.btnClear = btnClear;
    this.edEmail = edEmail;
    this.edPassword = edPassword;
    this.edPhonenum = edPhonenum;
    this.edUsername = edUsername;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_apply;
      Button btnApply = ViewBindings.findChildViewById(rootView, id);
      if (btnApply == null) {
        break missingId;
      }

      id = R.id.btn_clear;
      Button btnClear = ViewBindings.findChildViewById(rootView, id);
      if (btnClear == null) {
        break missingId;
      }

      id = R.id.ed_email;
      EditText edEmail = ViewBindings.findChildViewById(rootView, id);
      if (edEmail == null) {
        break missingId;
      }

      id = R.id.ed_password;
      EditText edPassword = ViewBindings.findChildViewById(rootView, id);
      if (edPassword == null) {
        break missingId;
      }

      id = R.id.ed_phonenum;
      EditText edPhonenum = ViewBindings.findChildViewById(rootView, id);
      if (edPhonenum == null) {
        break missingId;
      }

      id = R.id.ed_username;
      EditText edUsername = ViewBindings.findChildViewById(rootView, id);
      if (edUsername == null) {
        break missingId;
      }

      return new DialogLayoutBinding((LinearLayout) rootView, btnApply, btnClear, edEmail,
          edPassword, edPhonenum, edUsername);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}