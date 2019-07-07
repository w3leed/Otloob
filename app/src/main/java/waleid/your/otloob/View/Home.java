package waleid.your.otloob.View;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import waleid.your.otloob.Interfaces.OnItemClickListner;
import waleid.your.otloob.Model.Category;
import waleid.your.otloob.R;
import waleid.your.otloob.ViewHolders.CategoriesViewHolder;

public class Home extends Activity implements NavigationView.OnNavigationItemSelectedListener
{
    FirebaseDatabase CategoriesDatabase;
    DatabaseReference CategoryTable;

    Toolbar toolbar;

    FloatingActionButton fab;

    DrawerLayout drawer;

    NavigationView navigationView;

    TextView UserFullName;

   private RecyclerView CategoriesRV;

    RecyclerView.LayoutManager RVCategoriesLayout;

    FirebaseRecyclerAdapter<Category, CategoriesViewHolder> CategoriesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        
        toolbar =findViewById(R.id.toolbar);

       // setSupportActionBar(toolbar);
        fab =findViewById(R.id.fab);
        drawer=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        UserFullName =findViewById(R.id.user_full_name);
        CategoriesRV =(RecyclerView)findViewById(R.id.rv_categories_menu);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        android.support.v7.app.ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        //basta7der esm elcurrent user 3la elnav bar
        View naveHeader = navigationView.getHeaderView(0);


        //2wl 7aga ha3ml gdwal llcategories belfirebase
        CategoriesDatabase = FirebaseDatabase.getInstance();
        CategoryTable =  CategoriesDatabase.getReference("Categories");

        //ba3d kida habtdy azabat elRV
        //ba3d kida habtdy azabat elRV
        //hageb elRV w a3mlha layoutmanager wba3den a7ot feha elCategories menu
        CategoriesRV.setHasFixedSize(true);
        RVCategoriesLayout = new LinearLayoutManager(this);
        CategoriesRV.setLayoutManager(RVCategoriesLayout);

        /*
        1-bs lazm f el2wl a3ml elmodel bta3 elcategory elwa7da
                34an a5od mnha list w a7otha f elRV(done)
        2- tany 7aga ha3ml interface llclick 3la elcategory elwa7da (done)
        3- view holder a3ml feh el4o8l llitem elwa7da (done)
        4- ne3ml fun esmha loadCategoris btgeb elcategories m eldatabase w te3redha f elRV
        5- nnady 3leha hna */
        LoadCategories();


    }

    void LoadCategories()
    {
        CategoriesAdapter = new FirebaseRecyclerAdapter<Category, CategoriesViewHolder>(Category.class,R.id.rv_categories_menu,CategoriesViewHolder.class,CategoryTable)

        {
            @Override
            protected void populateViewHolder(CategoriesViewHolder viewHolder, final Category model, int position)
            {
                viewHolder.categoryName.setText(model.getName());
                UserFullName.setText(model.getName()); //error
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.categoryImg);

//                final Category item =model;

                viewHolder.setOnItemClickListner(new OnItemClickListner() {
                    @Override
                    public void onClick(View item, int position, boolean isLongClick)
                    {
                        Intent intent = new Intent(Home.this,FoodList.class);
                        intent.putExtra("CategoryId",CategoriesAdapter.getRef(position).getKey());
                        startActivity(intent);
//                        Toast.makeText(Home.this,""+model.getName(),Toast.LENGTH_SHORT).show();

                    }
                });



            }
        };

        CategoriesRV.setAdapter(CategoriesAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu) {
            // Handle the camera action
        } else if (id == R.id.nav_cart) {

        } else if (id == R.id.nav_orders) {

        } else if (id == R.id.nav_signOut) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
