package activity;

/**
 * Created by Aman Sharma on 3/21/2017.
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ncpcr.visionaries.sishutraan.R;

import static android.support.v7.widget.StaggeredGridLayoutManager.TAG;

public class SignUpFragment extends Fragment {

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth_listener]

    EditText eEmail,ePassword;
    private ProgressDialog progress;
    Thread t;
    Button bSignUp;
    String string_email,string_password;



    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    public void signup_progress(View view){
        progress=new ProgressDialog(SignUpFragment.this.getActivity());
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
        View rootView = inflater.inflate(R.layout.fragment_signup, container, false);
        // Inflate the layout for this fragment

        eEmail=(EditText)rootView.findViewById(R.id.eemail2);
        ePassword=(EditText)rootView.findViewById(R.id.epassword2);

        bSignUp=(Button)rootView.findViewById(R.id.bsignup2);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        // [START auth_state_listener]
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // [START_EXCLUDE]
            }
        };


        bSignUp.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup_progress(v);
                createAccount();
            }
        });

        return rootView;
    }


    // [START on_start_add_listener]
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    // [END on_start_add_listener]

    // [START on_stop_remove_listener]
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    private void createAccount() {
        Log.d(TAG, "createAccount:" + string_email);
        if (!validateForm()) {
            return;
        }

        t.start();

        string_email=eEmail.getText().toString();
        string_password=ePassword.getText().toString();
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(string_email, string_password)
                .addOnCompleteListener(SignUpFragment.this.getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            progress.hide();
                            t.interrupt();
                            Toast.makeText(SignUpFragment.this.getActivity(), R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            progress.hide();
                            t.interrupt();
                            Toast.makeText(SignUpFragment.this.getActivity(), "Registered!",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
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
