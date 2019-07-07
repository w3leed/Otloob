package waleid.your.otloob.View;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.ButterKnife;
import info.hoang8f.widget.FButton;
import waleid.your.otloob.Common.Common;
import waleid.your.otloob.Model.User;
import waleid.your.otloob.R;

public class SignInActivity extends AppCompatActivity
{

    MaterialEditText EtPhone,EtPass;
    FButton SignInBtn;
    FirebaseDatabase UsersDatabase ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        FirebaseApp.initializeApp(this);
        ButterKnife.bind(this);
        EtPhone  =findViewById(R.id.et_inPhone);
        EtPass=findViewById(R.id.et_inPass);
        SignInBtn=findViewById(R.id.sign_In_inBtn);

        //ha3ml object m el FirebaseDatabase 34an a3ml mnha instance a5zn gwah eldatabase bta3t eluser
         UsersDatabase = FirebaseDatabase.getInstance();
        //ha3ml gdwal lluser esmo tableuser
        final DatabaseReference tableUser = UsersDatabase.getReference("User");

        //what happened when press signInBtn
        SignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog dialog = new ProgressDialog(SignInActivity.this);
                dialog.setMessage("Please wait ....");
                dialog.show();

                //a elly hae7sal lma adef data llgdwal da w hae5znha ezay ??
                tableUser.addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        //lma aktb elphone ha4of hwa mawgod f eldatabase asln wla l2
                        if(dataSnapshot.child(EtPhone.getText().toString()).exists())
                        {
                            dialog.dismiss();
                            //lo mawgod habtdy a4ta8l 3leh l2no hwa kida account 3ndy asln w a5zeno f object m elusers
                            User user = dataSnapshot.child(EtPhone.getText().toString()).getValue(User.class);

                            //lazm a3ml check 3la elpass
                            if (user.getPassword().equals(EtPass.getText().toString()))
                            {
                                //lo elpass sa7 da5alny 3la elhome page
                                Toast.makeText(SignInActivity.this, "Sign in successfully", Toast.LENGTH_SHORT).show();

                                //bma n elpass s7 ezn hwa user 3ndy aro7 a5zeno f elcommon 34an a3rf elapp b men elly 48al 3leh
                                /*fe fr2 ben "user" w "currentUser" user da model ba5od mno model mn kol data leh ze (phone, name, pass)
                                * lkn elcurrent user da class b5zn gwah elusers elly 48alin 3la elapp m elmob elwa7d w mno object elcurrentUser
                                * elly howa wlly ana 48ala 3leh dlw2t*/
                                Common.CurrentUser = user;

                                Intent HomePage = new Intent(SignInActivity.this,Home.class);
                                startActivity(HomePage);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(SignInActivity.this, "Sign in faild !!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        else
                            {
                                Toast.makeText(SignInActivity.this, "user isn't exist", Toast.LENGTH_SHORT).show();

                            }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
