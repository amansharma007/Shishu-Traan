package activity;

/**
 * Created by Aman Sharma on 3/21/2017.
 */
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ncpcr.visionaries.sishutraan.R;

import java.util.HashMap;


public class ComplaintFragment2 extends Fragment {

    String state,dist,cname,cmail,cmob;
    Button bSubmit;
    EditText eVn,eVFN,eVPO,eVPAMO,eVPB,eVES;
    DatabaseReference blankRecordReference;
    HashMap<String, String> hashMap_record;
    public ComplaintFragment2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_complaint2, container, false);

        FirebaseDatabase fb_db_instance = FirebaseDatabase.getInstance();
        DatabaseReference db_ref_Main = fb_db_instance.getReference("Child Labor");
                  //some random data
        bSubmit=(Button)rootView.findViewById(R.id.CLSubmit);
        eVn=(EditText) rootView.findViewById(R.id.CLVN);
        eVFN=(EditText) rootView.findViewById(R.id.CLVFN);
        eVPO=(EditText) rootView.findViewById(R.id.CLVPO);
        eVPAMO=(EditText) rootView.findViewById(R.id.CLVPAMO);
        eVPB=(EditText) rootView.findViewById(R.id.CLVPB);
        eVES=(EditText) rootView.findViewById(R.id.CLVES);
         hashMap_record = new HashMap<String, String>();


        String sVn = eVn.getText().toString();
        String sVFN = eVFN.getText().toString();
        String sVPO = eVPO.getText().toString();
        String sVPAMO = eVPAMO.getText().toString();
        String sVPB = eVPB.getText().toString();
        String sVES = eVES.getText().toString();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            state = bundle.getString("state");
            dist = bundle.getString("dist");
            cname = bundle.getString("comname");
            cmail = bundle.getString("commail");
            cmob = bundle.getString("commob");
        }

        hashMap_record.put("ComplDistrict", dist);
        hashMap_record.put("ComplEmail", cmail);
        hashMap_record.put("ComplMob", cmob);
        hashMap_record.put("ComplName", cname);
        hashMap_record.put("ComplState", state);
        hashMap_record.put("VictimName", sVn);
        hashMap_record.put("VictimFatherName", sVFN);
        hashMap_record.put("VictimParentOccupation", sVPO);
        hashMap_record.put("VictimParentAddressMobileNo", sVPAMO);
        hashMap_record.put("VictimParentBackground", sVPB);
        hashMap_record.put("VictimEducationSource", sVES);

        blankRecordReference = db_ref_Main ;



        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference db_ref = blankRecordReference.push();   //creates blank record in db
                String str_NEW_Records_Key = db_ref.getKey();             //the UniqueID/key you seek
                db_ref.setValue( hashMap_record);
            }
        });

        // Inflate the layout for this fragment
        return rootView;
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
