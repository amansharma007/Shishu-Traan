package activity;

/**
 * Created by Aman Sharma on 24-03-2017.
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ncpcr.visionaries.sishutraan.R;

import static android.support.v7.widget.StaggeredGridLayoutManager.TAG;

public class LoginFragment extends Fragment {
    Button bLogin,bSignUp;
    Thread t;
    String string_email, string_password;
    EditText eEmail,ePassword;
    private FirebaseAuth mAuth;
    private ProgressDialog progress;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public LoginFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void login_progress(View view){
        progress=new ProgressDialog(LoginFragment.this.getActivity());
        progress.setMessage("Logging In....");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.show();

        final int totalProgressTime = 100;
        t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while (jumpTime < totalProgressTime) {
                    try {
                        sleep(200);
                        jumpTime += 5;
                        progress.setProgress(jumpTime);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        eEmail=(EditText)rootView.findViewById(R.id.eemail);
        ePassword=(EditText)rootView.findViewById(R.id.epassword);
        bLogin=(Button)rootView.findViewById(R.id.blogin);
        bSignUp=(Button)rootView.findViewById(R.id.bsignup);
        mAuth=FirebaseAuth.getInstance();
        mAuthListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.d(TAG, "onAuthStateChanged:signed_in:"+user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_progress(v);
                signIn();
            }
        });

        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SignUpFragment();
                FragmentManager fragmentManager = LoginFragment.this.getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_body, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }

    public void signIn(){
        Log.d(TAG, "signIn:" + string_email);
        if (!validateForm()) {
            return;
        }
                t.start();

                string_email=eEmail.getText().toString();
                string_password=ePassword.getText().toString();

                mAuth.signInWithEmailAndPassword(string_email, string_password)
                        .addOnCompleteListener(LoginFragment.this.getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    progress.hide();
                                    t.interrupt();
                                    Log.w(TAG, "signInWithEmail:failed", task.getException());
                                    Toast.makeText(LoginFragment.this.getActivity(), R.string.auth_failed,
                                            Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    progress.hide();
                                    t.interrupt();
                                    Toast.makeText(LoginFragment.this.getActivity(), "Logged In",
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = eEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            eEmail.setError("Required.");
            valid = false;
        } else {
            eEmail.setError(null);
        }

        String password = ePassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            ePassword.setError("Required.");
            valid = false;
        } else {
            ePassword.setError(null);
        }

        return valid;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity a;
        if (context instanceof Activity){
            a=(Activity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}