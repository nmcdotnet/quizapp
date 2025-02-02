// Generated by view binder compiler. Do not edit!
package com.cstrien.thi_trac_nghiem.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
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

public final class ActivityMenuUserBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ImageButton btnAddUser;

  @NonNull
  public final TextView btnClear;

  @NonNull
  public final TextView btnHome;

  @NonNull
  public final EditText edtSearch;

  @NonNull
  public final ListView lvUser;

  @NonNull
  public final TextView textView14;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final TextView textView7;

  @NonNull
  public final TextView textView8;

  @NonNull
  public final TextView txtTieuDe;

  @NonNull
  public final TextView txtUserName;

  private ActivityMenuUserBinding(@NonNull RelativeLayout rootView, @NonNull ImageButton btnAddUser,
      @NonNull TextView btnClear, @NonNull TextView btnHome, @NonNull EditText edtSearch,
      @NonNull ListView lvUser, @NonNull TextView textView14, @NonNull TextView textView3,
      @NonNull TextView textView7, @NonNull TextView textView8, @NonNull TextView txtTieuDe,
      @NonNull TextView txtUserName) {
    this.rootView = rootView;
    this.btnAddUser = btnAddUser;
    this.btnClear = btnClear;
    this.btnHome = btnHome;
    this.edtSearch = edtSearch;
    this.lvUser = lvUser;
    this.textView14 = textView14;
    this.textView3 = textView3;
    this.textView7 = textView7;
    this.textView8 = textView8;
    this.txtTieuDe = txtTieuDe;
    this.txtUserName = txtUserName;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMenuUserBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMenuUserBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_menu_user, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMenuUserBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnAddUser;
      ImageButton btnAddUser = ViewBindings.findChildViewById(rootView, id);
      if (btnAddUser == null) {
        break missingId;
      }

      id = R.id.btnClear;
      TextView btnClear = ViewBindings.findChildViewById(rootView, id);
      if (btnClear == null) {
        break missingId;
      }

      id = R.id.btnHome;
      TextView btnHome = ViewBindings.findChildViewById(rootView, id);
      if (btnHome == null) {
        break missingId;
      }

      id = R.id.edtSearch;
      EditText edtSearch = ViewBindings.findChildViewById(rootView, id);
      if (edtSearch == null) {
        break missingId;
      }

      id = R.id.lvUser;
      ListView lvUser = ViewBindings.findChildViewById(rootView, id);
      if (lvUser == null) {
        break missingId;
      }

      id = R.id.textView14;
      TextView textView14 = ViewBindings.findChildViewById(rootView, id);
      if (textView14 == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
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

      return new ActivityMenuUserBinding((RelativeLayout) rootView, btnAddUser, btnClear, btnHome,
          edtSearch, lvUser, textView14, textView3, textView7, textView8, txtTieuDe, txtUserName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
