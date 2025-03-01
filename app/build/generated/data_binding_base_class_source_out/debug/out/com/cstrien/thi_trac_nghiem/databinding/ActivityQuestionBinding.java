// Generated by view binder compiler. Do not edit!
package com.cstrien.thi_trac_nghiem.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cstrien.thi_trac_nghiem.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityQuestionBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView btnBackMainUser;

  @NonNull
  public final TextView btnHome;

  @NonNull
  public final TextView buttonConfirmNext;

  @NonNull
  public final RadioButton radioButton1;

  @NonNull
  public final RadioButton radioButton2;

  @NonNull
  public final RadioButton radioButton3;

  @NonNull
  public final RadioButton radioButton4;

  @NonNull
  public final RadioGroup radioGroup;

  @NonNull
  public final TextView textViewCategory;

  @NonNull
  public final TextView textViewCountdown;

  @NonNull
  public final TextView textViewQuestion;

  @NonNull
  public final TextView textViewQuestionCount;

  @NonNull
  public final TextView textViewScore;

  @NonNull
  public final TextView txtClock;

  private ActivityQuestionBinding(@NonNull RelativeLayout rootView,
      @NonNull TextView btnBackMainUser, @NonNull TextView btnHome,
      @NonNull TextView buttonConfirmNext, @NonNull RadioButton radioButton1,
      @NonNull RadioButton radioButton2, @NonNull RadioButton radioButton3,
      @NonNull RadioButton radioButton4, @NonNull RadioGroup radioGroup,
      @NonNull TextView textViewCategory, @NonNull TextView textViewCountdown,
      @NonNull TextView textViewQuestion, @NonNull TextView textViewQuestionCount,
      @NonNull TextView textViewScore, @NonNull TextView txtClock) {
    this.rootView = rootView;
    this.btnBackMainUser = btnBackMainUser;
    this.btnHome = btnHome;
    this.buttonConfirmNext = buttonConfirmNext;
    this.radioButton1 = radioButton1;
    this.radioButton2 = radioButton2;
    this.radioButton3 = radioButton3;
    this.radioButton4 = radioButton4;
    this.radioGroup = radioGroup;
    this.textViewCategory = textViewCategory;
    this.textViewCountdown = textViewCountdown;
    this.textViewQuestion = textViewQuestion;
    this.textViewQuestionCount = textViewQuestionCount;
    this.textViewScore = textViewScore;
    this.txtClock = txtClock;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityQuestionBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityQuestionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_question, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityQuestionBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnBackMainUser;
      TextView btnBackMainUser = ViewBindings.findChildViewById(rootView, id);
      if (btnBackMainUser == null) {
        break missingId;
      }

      id = R.id.btnHome;
      TextView btnHome = ViewBindings.findChildViewById(rootView, id);
      if (btnHome == null) {
        break missingId;
      }

      id = R.id.button_confirm_next;
      TextView buttonConfirmNext = ViewBindings.findChildViewById(rootView, id);
      if (buttonConfirmNext == null) {
        break missingId;
      }

      id = R.id.radio_button1;
      RadioButton radioButton1 = ViewBindings.findChildViewById(rootView, id);
      if (radioButton1 == null) {
        break missingId;
      }

      id = R.id.radio_button2;
      RadioButton radioButton2 = ViewBindings.findChildViewById(rootView, id);
      if (radioButton2 == null) {
        break missingId;
      }

      id = R.id.radio_button3;
      RadioButton radioButton3 = ViewBindings.findChildViewById(rootView, id);
      if (radioButton3 == null) {
        break missingId;
      }

      id = R.id.radio_button4;
      RadioButton radioButton4 = ViewBindings.findChildViewById(rootView, id);
      if (radioButton4 == null) {
        break missingId;
      }

      id = R.id.radio_group;
      RadioGroup radioGroup = ViewBindings.findChildViewById(rootView, id);
      if (radioGroup == null) {
        break missingId;
      }

      id = R.id.text_view_category;
      TextView textViewCategory = ViewBindings.findChildViewById(rootView, id);
      if (textViewCategory == null) {
        break missingId;
      }

      id = R.id.text_view_countdown;
      TextView textViewCountdown = ViewBindings.findChildViewById(rootView, id);
      if (textViewCountdown == null) {
        break missingId;
      }

      id = R.id.text_view_question;
      TextView textViewQuestion = ViewBindings.findChildViewById(rootView, id);
      if (textViewQuestion == null) {
        break missingId;
      }

      id = R.id.text_view_question_count;
      TextView textViewQuestionCount = ViewBindings.findChildViewById(rootView, id);
      if (textViewQuestionCount == null) {
        break missingId;
      }

      id = R.id.text_view_score;
      TextView textViewScore = ViewBindings.findChildViewById(rootView, id);
      if (textViewScore == null) {
        break missingId;
      }

      id = R.id.txtClock;
      TextView txtClock = ViewBindings.findChildViewById(rootView, id);
      if (txtClock == null) {
        break missingId;
      }

      return new ActivityQuestionBinding((RelativeLayout) rootView, btnBackMainUser, btnHome,
          buttonConfirmNext, radioButton1, radioButton2, radioButton3, radioButton4, radioGroup,
          textViewCategory, textViewCountdown, textViewQuestion, textViewQuestionCount,
          textViewScore, txtClock);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
