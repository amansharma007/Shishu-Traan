package activity;

/**
 * Created by Aman Sharma on 3/21/2017.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ncpcr.visionaries.sishutraan.*;

public class ComplaintFragment1 extends Fragment  {

    Button bNext;
    Fragment fragment = null;
    int id=-1;
    Bundle bundle;
    TextView tFaq;
    String item,state,district;
    EditText eName,ePin,eMobile,eEmail;
    Spinner sState,sDistrict,sCase;
    public ComplaintFragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_complaint1, container, false);

        // Inflate the layout for this fragment
        bNext=(Button) rootView.findViewById(R.id.bNext_fc1);
        tFaq=(TextView)rootView.findViewById(R.id.faq_fc1);
        eName=(EditText)rootView.findViewById(R.id.name_fc1);
        eEmail=(EditText)rootView.findViewById(R.id.email_fc1);
        eMobile=(EditText)rootView.findViewById(R.id.mobile_fc1);
        ePin=(EditText)rootView.findViewById(R.id.pin_fc1);
        sState=(Spinner)rootView.findViewById(R.id.state_fc1);
        sCase=(Spinner)rootView.findViewById(R.id.case_fc1);
        sDistrict=(Spinner)rootView.findViewById(R.id.district_fc1);


        spinnerSetting();
        bundle = new Bundle();

        bNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (fragment != null) {
                    bundle.putString("state", state);
                    bundle.putString("dist",district);
                    bundle.putString("comname",eName.getText().toString());
                    bundle.putString("commail",eEmail.getText().toString());
                    bundle.putString("commob",eMobile.getText().toString());
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManager = ComplaintFragment1.this.getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container_body, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });
        return rootView;
    }

    private void spinnerSetting() {


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.states_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        sState.setAdapter(adapter);


        sState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id1) {

                switch (position) {

                    case 0:
                        id=R.array.Select;
                        break;
                    case 1:
                        id = R.array.Andaman;
                        break;
                    case 2:
                        id = R.array.Andhra;
                        break;
                    case 3:
                        id = R.array.Arunachal;
                        break;
                    case 4:
                        id = R.array.Assam;
                        break;
                    case 5:
                        id = R.array.Bihar;
                        break;
                    case 6:
                        id = R.array.Chandigarh;
                        break;
                    case 7:
                        id = R.array.Chhattisgarh;
                        break;
                    case 8:
                        id = R.array.Dadra;
                        break;
                    case 9:
                        id = R.array.Daman;
                        break;
                    case 10:
                        id = R.array.Delhi;
                        break;
                    case 11:
                        id = R.array.Goa;
                        break;
                    case 12:
                        id = R.array.Gujarat;
                        break;
                    case 13:
                        id = R.array.Haryana;
                        break;
                    case 14:
                        id = R.array.Himachal;
                        break;
                    case 15:
                        id = R.array.Jammu;
                        break;
                    case 16:
                        id = R.array.Jharkhand;
                        break;
                    case 17:
                        id = R.array.Karnataka;
                        break;
                    case 18:
                        id = R.array.Kerala;
                        break;
                    case 19:
                        id = R.array.Lakshadweep;
                        break;
                    case 20:
                        id = R.array.Madhya;
                        break;
                    case 21:
                        id = R.array.Maharashtra;
                        break;
                    case 22:
                        id = R.array.Manipur;
                        break;
                    case 23:
                        id = R.array.Meghalaya;
                        break;
                    case 24:
                        id = R.array.Mizoram;
                        break;
                    case 25:
                        id = R.array.Nagaland;
                        break;
                    case 26:
                        id = R.array.Odisha;
                        break;
                    case 27:
                        id = R.array.Pondicherry;
                        break;
                    case 28:
                        id = R.array.Punjab;
                        break;
                    case 29:
                        id = R.array.Rajasthan;
                        break;
                    case 30:
                        id = R.array.Sikkim;
                        break;
                    case 31:
                        id = R.array.Tamil;
                        break;
                    case 32:
                        id = R.array.Telangana;
                        break;
                    case 33:
                        id = R.array.Tripura;
                        break;
                    case 34:
                        id = R.array.Uttar;
                        break;
                    case 35:
                        id = R.array.Uttarakhand;
                        break;
                    case 36:
                        id = R.array.West;
                        break;
                    case 37:
                        id = R.array.Andaman;
                        break;
                }

                state=(String)parent.getItemAtPosition(position);
                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(ComplaintFragment1.this.getActivity(),
                        id, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
                sDistrict.setAdapter(adapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                district=(String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(ComplaintFragment1.this.getActivity(),
                R.array.cases, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        sCase.setAdapter(adapter3);

        sCase.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        fragment = new ComplaintFragment2();
                        item=(String)parent.getItemAtPosition(position);
                        break;
                    case 2:
                        fragment = new ComplaintFragment3();
                        item=(String)parent.getItemAtPosition(position);
                        break;
                    case 3:
                        fragment = new ComplaintFragment4();
                        item=(String)parent.getItemAtPosition(position);
                        break;
                    case 4:
                        fragment = new ComplaintFragment5();
                        item=(String)parent.getItemAtPosition(position);
                        break;
                    case 5:
                        fragment = new ComplaintFragment6();
                        item=(String)parent.getItemAtPosition(position);
                        break;
                    case 6:
                        fragment = new ComplaintFragment7();
                        item=(String)parent.getItemAtPosition(position);
                        break;
                    case 7:
                        fragment = new ComplaintFragment8();
                        item=(String)parent.getItemAtPosition(position);
                        break;
                    case 8:
                        fragment = new ComplaintFragment9();
                        item=(String)parent.getItemAtPosition(position);
                        break;
                    case 9:
                        fragment = new ComplaintFragment10();
                        item=(String)parent.getItemAtPosition(position);
                        break;
                    case 10:
                        fragment = new ComplaintFragment11();
                        item=(String)parent.getItemAtPosition(position);
                        break;
                    default:
                        break;
                }

              /*  if (fragment != null) {
                    FragmentManager fragmentManager = ComplaintFragment1.this.getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container_body, fragment);
                    fragmentTransaction.commit();
                }*/

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
