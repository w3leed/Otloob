package waleid.your.otloob.View;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import waleid.your.otloob.R;

public class MainActivity extends AppCompatActivity
{

    Button signInBtn,signUpBtn;
    TextView WlcMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signInBtn =findViewById(R.id.sign_in_mainBtn);
        signUpBtn=findViewById(R.id.sign_up_mainBtn);
        WlcMsg=findViewById(R.id.wlc_msg);
        ButterKnife.bind(this);

        signInBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent SignIn = new Intent(MainActivity.this,SignInActivity.class);
                startActivity(SignIn);


            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent SignUp = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(SignUp);


            }
        });

     }
}
