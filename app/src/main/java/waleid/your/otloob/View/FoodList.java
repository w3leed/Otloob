package waleid.your.otloob.View;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import waleid.your.otloob.Interfaces.OnItemClickListner;
import waleid.your.otloob.Model.Food;
import waleid.your.otloob.R;
import waleid.your.otloob.ViewHolders.FoodViewHolder;

public class FoodList extends AppCompatActivity
{
    RecyclerView FoodsRV;

    RecyclerView.LayoutManager RVFoodsLayout;

    FirebaseDatabase FoodsDatabase;
    DatabaseReference FoodTable;

    String CategoryId="";

    FirebaseRecyclerAdapter<Food, FoodViewHolder> FoodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        FirebaseApp.initializeApp(this);
        ButterKnife.bind(this);
        FoodsRV =findViewById(R.id.rv_food_list);

        //2wl 7aga ha3ml gdwal llFoods belfirebase
        FoodsDatabase = FirebaseDatabase.getInstance();
        FoodTable = FoodsDatabase.getReference("Food");

        //ba3d kida habtdy azabat elRV
        //hageb elRV w a3mlha layoutmanager wba3den a7ot feha elCategories menu
        FoodsRV.setHasFixedSize(true);
        RVFoodsLayout = new LinearLayoutManager(this);
        FoodsRV.setLayoutManager(RVFoodsLayout);

        //hacheck n elfoodlist etalabat b categoryId mo3ayan m4 fady wla besawy null

        //hat elintent b mo7twaeatha
        if(getIntent() != null)
        {
            //ha7ot elid elmo3ayan f elvariable elly esmo categoryId
            CategoryId= getIntent().getStringExtra("CategoryId");

            //hatcheck no m4 fady w m4 b null
            if(!TextUtils.isEmpty(CategoryId))
            {
                LoadFoods(CategoryId);
            }

        }
       /* else
        {
            Toast.makeText(FoodList.this,"eroorerrrrrrrrr",Toast.LENGTH_LONG).show();

        }*/

    }

    void LoadFoods(String categoryId)
    {
        FoodAdapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,R.id.rv_food_list,
                FoodViewHolder.class,
                /*ha7der elgdwal bta3 elfood bs b4rt n elfoodid = elcategoryid elly gaealom m elclick */
                FoodTable.orderByChild("MenuId").equalTo(categoryId))
        {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, final Food model, int position)
            {
                viewHolder.foodName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.foodImg);

//                final Category item =model;

                viewHolder.setOnItemClickListner(new OnItemClickListner() {
                    @Override
                    public void onClick(View item, int position, boolean isLongClick) {
                        Toast.makeText(FoodList.this,""+model.getName(),Toast.LENGTH_SHORT).show();
                    }
                });



            }
        };

        FoodsRV.setAdapter(FoodAdapter);
    }
}

