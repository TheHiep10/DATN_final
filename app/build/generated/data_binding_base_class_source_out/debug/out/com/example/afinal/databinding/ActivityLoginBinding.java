// Generated by view binder compiler. Do not edit!
package com.example.afinal.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
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

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final RelativeLayout Bot;

  @NonNull
  public final FrameLayout Password;

  @NonNull
  public final RobotoMediumTextView btnForgotpw;

  @NonNull
  public final ImageButton btnLogin;

  @NonNull
  public final RobotoBoldTextView btnSignUp;

  @NonNull
  public final RelativeLayout frmTop;

  @NonNull
  public final ImageView homeLogo;

  @NonNull
  public final RelativeLayout loginContainer;

  @NonNull
  public final Button openEye;

  @NonNull
  public final CheckBox remmberCheck;

  @NonNull
  public final EditText txtEmail;

  @NonNull
  public final EditText txtPassword;

  @NonNull
  public final RobotoMediumTextView txtSignup;

  private ActivityLoginBinding(@NonNull RelativeLayout rootView, @NonNull RelativeLayout Bot,
      @NonNull FrameLayout Password, @NonNull RobotoMediumTextView btnForgotpw,
      @NonNull ImageButton btnLogin, @NonNull RobotoBoldTextView btnSignUp,
      @NonNull RelativeLayout frmTop, @NonNull ImageView homeLogo,
      @NonNull RelativeLayout loginContainer, @NonNull Button openEye,
      @NonNull CheckBox remmberCheck, @NonNull EditText txtEmail, @NonNull EditText txtPassword,
      @NonNull RobotoMediumTextView txtSignup) {
    this.rootView = rootView;
    this.Bot = Bot;
    this.Password = Password;
    this.btnForgotpw = btnForgotpw;
    this.btnLogin = btnLogin;
    this.btnSignUp = btnSignUp;
    this.frmTop = frmTop;
    this.homeLogo = homeLogo;
    this.loginContainer = loginContainer;
    this.openEye = openEye;
    this.remmberCheck = remmberCheck;
    this.txtEmail = txtEmail;
    this.txtPassword = txtPassword;
    this.txtSignup = txtSignup;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Bot;
      RelativeLayout Bot = ViewBindings.findChildViewById(rootView, id);
      if (Bot == null) {
        break missingId;
      }

      id = R.id.Password;
      FrameLayout Password = ViewBindings.findChildViewById(rootView, id);
      if (Password == null) {
        break missingId;
      }

      id = R.id.btnForgotpw;
      RobotoMediumTextView btnForgotpw = ViewBindings.findChildViewById(rootView, id);
      if (btnForgotpw == null) {
        break missingId;
      }

      id = R.id.btnLogin;
      ImageButton btnLogin = ViewBindings.findChildViewById(rootView, id);
      if (btnLogin == null) {
        break missingId;
      }

      id = R.id.btnSignUp;
      RobotoBoldTextView btnSignUp = ViewBindings.findChildViewById(rootView, id);
      if (btnSignUp == null) {
        break missingId;
      }

      id = R.id.frmTop;
      RelativeLayout frmTop = ViewBindings.findChildViewById(rootView, id);
      if (frmTop == null) {
        break missingId;
      }

      id = R.id.homeLogo;
      ImageView homeLogo = ViewBindings.findChildViewById(rootView, id);
      if (homeLogo == null) {
        break missingId;
      }

      RelativeLayout loginContainer = (RelativeLayout) rootView;

      id = R.id.openEye;
      Button openEye = ViewBindings.findChildViewById(rootView, id);
      if (openEye == null) {
        break missingId;
      }

      id = R.id.remmberCheck;
      CheckBox remmberCheck = ViewBindings.findChildViewById(rootView, id);
      if (remmberCheck == null) {
        break missingId;
      }

      id = R.id.txtEmail;
      EditText txtEmail = ViewBindings.findChildViewById(rootView, id);
      if (txtEmail == null) {
        break missingId;
      }

      id = R.id.txtPassword;
      EditText txtPassword = ViewBindings.findChildViewById(rootView, id);
      if (txtPassword == null) {
        break missingId;
      }

      id = R.id.txtSignup;
      RobotoMediumTextView txtSignup = ViewBindings.findChildViewById(rootView, id);
      if (txtSignup == null) {
        break missingId;
      }

      return new ActivityLoginBinding((RelativeLayout) rootView, Bot, Password, btnForgotpw,
          btnLogin, btnSignUp, frmTop, homeLogo, loginContainer, openEye, remmberCheck, txtEmail,
          txtPassword, txtSignup);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
