// Generated by view binder compiler. Do not edit!
package com.example.afinal.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.afinal.R;
import com.example.afinal.custom_textView.RobotoBoldTextView;
import com.example.afinal.custom_textView.RobotoMediumTextView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ResetPasswordBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final FrameLayout ConfirmPassword;

  @NonNull
  public final FrameLayout NewPassword;

  @NonNull
  public final ImageButton btnBack;

  @NonNull
  public final RobotoBoldTextView btnSignIn;

  @NonNull
  public final ImageButton btnSubmit;

  @NonNull
  public final RelativeLayout frmTop;

  @NonNull
  public final ImageView newPassword;

  @NonNull
  public final Button openEyeConfirmNewPass;

  @NonNull
  public final Button openEyeNewPass;

  @NonNull
  public final EditText txtConfirmPassword;

  @NonNull
  public final EditText txtNewPassword;

  @NonNull
  public final RobotoMediumTextView txtSignIn;

  private ResetPasswordBinding(@NonNull RelativeLayout rootView,
      @NonNull FrameLayout ConfirmPassword, @NonNull FrameLayout NewPassword,
      @NonNull ImageButton btnBack, @NonNull RobotoBoldTextView btnSignIn,
      @NonNull ImageButton btnSubmit, @NonNull RelativeLayout frmTop,
      @NonNull ImageView newPassword, @NonNull Button openEyeConfirmNewPass,
      @NonNull Button openEyeNewPass, @NonNull EditText txtConfirmPassword,
      @NonNull EditText txtNewPassword, @NonNull RobotoMediumTextView txtSignIn) {
    this.rootView = rootView;
    this.ConfirmPassword = ConfirmPassword;
    this.NewPassword = NewPassword;
    this.btnBack = btnBack;
    this.btnSignIn = btnSignIn;
    this.btnSubmit = btnSubmit;
    this.frmTop = frmTop;
    this.newPassword = newPassword;
    this.openEyeConfirmNewPass = openEyeConfirmNewPass;
    this.openEyeNewPass = openEyeNewPass;
    this.txtConfirmPassword = txtConfirmPassword;
    this.txtNewPassword = txtNewPassword;
    this.txtSignIn = txtSignIn;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ResetPasswordBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ResetPasswordBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.reset_password, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ResetPasswordBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.ConfirmPassword;
      FrameLayout ConfirmPassword = ViewBindings.findChildViewById(rootView, id);
      if (ConfirmPassword == null) {
        break missingId;
      }

      id = R.id.NewPassword;
      FrameLayout NewPassword = ViewBindings.findChildViewById(rootView, id);
      if (NewPassword == null) {
        break missingId;
      }

      id = R.id.btnBack;
      ImageButton btnBack = ViewBindings.findChildViewById(rootView, id);
      if (btnBack == null) {
        break missingId;
      }

      id = R.id.btnSignIn;
      RobotoBoldTextView btnSignIn = ViewBindings.findChildViewById(rootView, id);
      if (btnSignIn == null) {
        break missingId;
      }

      id = R.id.btnSubmit;
      ImageButton btnSubmit = ViewBindings.findChildViewById(rootView, id);
      if (btnSubmit == null) {
        break missingId;
      }

      id = R.id.frmTop;
      RelativeLayout frmTop = ViewBindings.findChildViewById(rootView, id);
      if (frmTop == null) {
        break missingId;
      }

      id = R.id.newPassword;
      ImageView newPassword = ViewBindings.findChildViewById(rootView, id);
      if (newPassword == null) {
        break missingId;
      }

      id = R.id.openEyeConfirmNewPass;
      Button openEyeConfirmNewPass = ViewBindings.findChildViewById(rootView, id);
      if (openEyeConfirmNewPass == null) {
        break missingId;
      }

      id = R.id.openEyeNewPass;
      Button openEyeNewPass = ViewBindings.findChildViewById(rootView, id);
      if (openEyeNewPass == null) {
        break missingId;
      }

      id = R.id.txtConfirmPassword;
      EditText txtConfirmPassword = ViewBindings.findChildViewById(rootView, id);
      if (txtConfirmPassword == null) {
        break missingId;
      }

      id = R.id.txtNewPassword;
      EditText txtNewPassword = ViewBindings.findChildViewById(rootView, id);
      if (txtNewPassword == null) {
        break missingId;
      }

      id = R.id.txtSignIn;
      RobotoMediumTextView txtSignIn = ViewBindings.findChildViewById(rootView, id);
      if (txtSignIn == null) {
        break missingId;
      }

      return new ResetPasswordBinding((RelativeLayout) rootView, ConfirmPassword, NewPassword,
          btnBack, btnSignIn, btnSubmit, frmTop, newPassword, openEyeConfirmNewPass, openEyeNewPass,
          txtConfirmPassword, txtNewPassword, txtSignIn);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
