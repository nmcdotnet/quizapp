// Generated by view binder compiler. Do not edit!
package com.cstrien.thi_trac_nghiem.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
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

public final class ActivityAddCategoryBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ImageButton btnBackCategory;

  @NonNull
  public final ImageButton btnCreateCategory;

  @NonNull
  public final TextView btnHome;

  @NonNull
  public final EditText edtNameCategory;

  @NonNull
  public final TextView textView12;

  @NonNull
  public final TextView textView13;

  @NonNull
  public final TextView txtTieuDe;

  @NonNull
  public final TextView txtUserName;

  private ActivityAddCategoryBinding(@NonNull RelativeLayout rootView,
      @NonNull ImageButton btnBackCategory, @NonNull ImageButton btnCreateCategory,
      @NonNull TextView btnHome, @NonNull EditText edtNameCategory, @NonNull TextView textView12,
      @NonNull TextView textView13, @NonNull TextView txtTieuDe, @NonNull TextView txtUserName) {
    this.rootView = rootView;
    this.btnBackCategory = btnBackCategory;
    this.btnCreateCategory = btnCreateCategory;
    this.btnHome = btnHome;
    this.edtNameCategory = edtNameCategory;
    this.textView12 = textView12;
    this.textView13 = textView13;
    this.txtTieuDe = txtTieuDe;
    this.txtUserName = txtUserName;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAddCategoryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAddCategoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_add_category, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAddCategoryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnBackCategory;
      ImageButton btnBackCategory = ViewBindings.findChildViewById(rootView, id);
      if (btnBackCategory == null) {
        break missingId;
      }

      id = R.id.btnCreateCategory;
      ImageButton btnCreateCategory = ViewBindings.findChildViewById(rootView, id);
      if (btnCreateCategory == null) {
        break missingId;
      }

      id = R.id.btnHome;
      TextView btnHome = ViewBindings.findChildViewById(rootView, id);
      if (btnHome == null) {
        break missingId;
      }

      id = R.id.edtNameCategory;
      EditText edtNameCategory = ViewBindings.findChildViewById(rootView, id);
      if (edtNameCategory == null) {
        break missingId;
      }

      id = R.id.textView12;
      TextView textView12 = ViewBindings.findChildViewById(rootView, id);
      if (textView12 == null) {
        break missingId;
      }

      id = R.id.textView13;
      TextView textView13 = ViewBindings.findChildViewById(rootView, id);
      if (textView13 == null) {
        break missingId;
      }

      id = R.id.txtTieuDe;
      TextView txtTieuDe = ViewBindings.findChildViewById(rootView, id);
      if (txtTieuDe == null) {
        break missingId;
      }

      id = R.id.txtUserName;
      TextView txtUserName = ViewBindings.findChildViewById(rootView, id);
      if (txtUserName == null) {
        break missingId;
      }

      return new ActivityAddCategoryBinding((RelativeLayout) rootView, btnBackCategory,
          btnCreateCategory, btnHome, edtNameCategory, textView12, textView13, txtTieuDe,
          txtUserName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
