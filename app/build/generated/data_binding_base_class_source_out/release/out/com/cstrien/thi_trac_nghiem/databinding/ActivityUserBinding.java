// Generated by view binder compiler. Do not edit!
package com.cstrien.thi_trac_nghiem.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public final class ActivityUserBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView btnDangXuat;

  @NonNull
  public final TextView btnDoiMatKhau;

  @NonNull
  public final TextView btnGuide;

  @NonNull
  public final TextView btnReady;

  @NonNull
  public final TextView txtTieuDe;

  @NonNull
  public final TextView txtUserName;

  private ActivityUserBinding(@NonNull RelativeLayout rootView, @NonNull TextView btnDangXuat,
      @NonNull TextView btnDoiMatKhau, @NonNull TextView btnGuide, @NonNull TextView btnReady,
      @NonNull TextView txtTieuDe, @NonNull TextView txtUserName) {
    this.rootView = rootView;
    this.btnDangXuat = btnDangXuat;
    this.btnDoiMatKhau = btnDoiMatKhau;
    this.btnGuide = btnGuide;
    this.btnReady = btnReady;
    this.txtTieuDe = txtTieuDe;
    this.txtUserName = txtUserName;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityUserBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityUserBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_user, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityUserBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnDangXuat;
      TextView btnDangXuat = ViewBindings.findChildViewById(rootView, id);
      if (btnDangXuat == null) {
        break missingId;
      }

      id = R.id.btnDoiMatKhau;
      TextView btnDoiMatKhau = ViewBindings.findChildViewById(rootView, id);
      if (btnDoiMatKhau == null) {
        break missingId;
      }

      id = R.id.btnGuide;
      TextView btnGuide = ViewBindings.findChildViewById(rootView, id);
      if (btnGuide == null) {
        break missingId;
      }

      id = R.id.btnReady;
      TextView btnReady = ViewBindings.findChildViewById(rootView, id);
      if (btnReady == null) {
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

      return new ActivityUserBinding((RelativeLayout) rootView, btnDangXuat, btnDoiMatKhau,
          btnGuide, btnReady, txtTieuDe, txtUserName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
