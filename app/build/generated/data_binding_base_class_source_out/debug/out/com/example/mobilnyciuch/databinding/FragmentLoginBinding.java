// Generated by view binder compiler. Do not edit!
package com.example.mobilnyciuch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.mobilnyciuch.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentLoginBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final MaterialButton btnLogin;

  @NonNull
  public final MaterialButton btnRegister;

  @NonNull
  public final TextInputEditText loginEditText;

  @NonNull
  public final TextInputEditText passwordEditText;

  private FragmentLoginBinding(@NonNull ScrollView rootView, @NonNull MaterialButton btnLogin,
      @NonNull MaterialButton btnRegister, @NonNull TextInputEditText loginEditText,
      @NonNull TextInputEditText passwordEditText) {
    this.rootView = rootView;
    this.btnLogin = btnLogin;
    this.btnRegister = btnRegister;
    this.loginEditText = loginEditText;
    this.passwordEditText = passwordEditText;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_login;
      MaterialButton btnLogin = ViewBindings.findChildViewById(rootView, id);
      if (btnLogin == null) {
        break missingId;
      }

      id = R.id.btn_register;
      MaterialButton btnRegister = ViewBindings.findChildViewById(rootView, id);
      if (btnRegister == null) {
        break missingId;
      }

      id = R.id.loginEditText;
      TextInputEditText loginEditText = ViewBindings.findChildViewById(rootView, id);
      if (loginEditText == null) {
        break missingId;
      }

      id = R.id.passwordEditText;
      TextInputEditText passwordEditText = ViewBindings.findChildViewById(rootView, id);
      if (passwordEditText == null) {
        break missingId;
      }

      return new FragmentLoginBinding((ScrollView) rootView, btnLogin, btnRegister, loginEditText,
          passwordEditText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
