// Generated by view binder compiler. Do not edit!
package com.example.afinal.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public final class ActivitySignUp2Binding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final RelativeLayout Bot;

  @NonNull
  public final ImageButton btnBack;

  @NonNull
  public final ImageButton btnNext;

  @NonNull
  public final RobotoBoldTextView btnSignIn;

  @NonNull
  public final DatePicker chooseDate;

  @NonNull
  public final RelativeLayout frmTop;

  @NonNull
  public final ImageView homeLogo;

  @NonNull
  public final View line;

  @NonNull
  public final RadioButton rbFemale;

  @NonNull
  public final RadioButton rbMale;

  @NonNull
  public final RadioButton rbOther;

  @NonNull
  public final RadioGroup rdGroup;

  @NonNull
  public final RobotoMediumTextView tvChooseDate;

  @NonNull
  public final RobotoMediumTextView tvGender;

  @NonNull
  public final RobotoMediumTextView txtSignup;

  private ActivitySignUp2Binding(@NonNull RelativeLayout rootView, @NonNull RelativeLayout Bot,
      @NonNull ImageButton btnBack, @NonNull ImageButton btnNext,
      @NonNull RobotoBoldTextView btnSignIn, @NonNull DatePicker chooseDate,
      @NonNull RelativeLayout frmTop, @NonNull ImageView homeLogo, @NonNull View line,
      @NonNull RadioButton rbFemale, @NonNull RadioButton rbMale, @NonNull RadioButton rbOther,
      @NonNull RadioGroup rdGroup, @NonNull RobotoMediumTextView tvChooseDate,
      @NonNull RobotoMediumTextView tvGender, @NonNull RobotoMediumTextView txtSignup) {
    this.rootView = rootView;
    this.Bot = Bot;
    this.btnBack = btnBack;
    this.btnNext = btnNext;
    this.btnSignIn = btnSignIn;
    this.chooseDate = chooseDate;
    this.frmTop = frmTop;
    this.homeLogo = homeLogo;
    this.line = line;
    this.rbFemale = rbFemale;
    this.rbMale = rbMale;
    this.rbOther = rbOther;
    this.rdGroup = rdGroup;
    this.tvChooseDate = tvChooseDate;
    this.tvGender = tvGender;
    this.txtSignup = txtSignup;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySignUp2Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySignUp2Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_sign_up2, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySignUp2Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Bot;
      RelativeLayout Bot = ViewBindings.findChildViewById(rootView, id);
      if (Bot == null) {
        break missingId;
      }

      id = R.id.btnBack;
      ImageButton btnBack = ViewBindings.findChildViewById(rootView, id);
      if (btnBack == null) {
        break missingId;
      }

      id = R.id.btnNext;
      ImageButton btnNext = ViewBindings.findChildViewById(rootView, id);
      if (btnNext == null) {
        break missingId;
      }

      id = R.id.btnSignIn;
      RobotoBoldTextView btnSignIn = ViewBindings.findChildViewById(rootView, id);
      if (btnSignIn == null) {
        break missingId;
      }

      id = R.id.chooseDate;
      DatePicker chooseDate = ViewBindings.findChildViewById(rootView, id);
      if (chooseDate == null) {
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

      id = R.id.line;
      View line = ViewBindings.findChildViewById(rootView, id);
      if (line == null) {
        break missingId;
      }

      id = R.id.rbFemale;
      RadioButton rbFemale = ViewBindings.findChildViewById(rootView, id);
      if (rbFemale == null) {
        break missingId;
      }

      id = R.id.rbMale;
      RadioButton rbMale = ViewBindings.findChildViewById(rootView, id);
      if (rbMale == null) {
        break missingId;
      }

      id = R.id.rbOther;
      RadioButton rbOther = ViewBindings.findChildViewById(rootView, id);
      if (rbOther == null) {
        break missingId;
      }

      id = R.id.rdGroup;
      RadioGroup rdGroup = ViewBindings.findChildViewById(rootView, id);
      if (rdGroup == null) {
        break missingId;
      }

      id = R.id.tvChooseDate;
      RobotoMediumTextView tvChooseDate = ViewBindings.findChildViewById(rootView, id);
      if (tvChooseDate == null) {
        break missingId;
      }

      id = R.id.tvGender;
      RobotoMediumTextView tvGender = ViewBindings.findChildViewById(rootView, id);
      if (tvGender == null) {
        break missingId;
      }

      id = R.id.txtSignup;
      RobotoMediumTextView txtSignup = ViewBindings.findChildViewById(rootView, id);
      if (txtSignup == null) {
        break missingId;
      }

      return new ActivitySignUp2Binding((RelativeLayout) rootView, Bot, btnBack, btnNext, btnSignIn,
          chooseDate, frmTop, homeLogo, line, rbFemale, rbMale, rbOther, rdGroup, tvChooseDate,
          tvGender, txtSignup);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}