package ml.ernestovector.menuopciones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Conectamos el TextView de la actividad al codigo
        TextView tvNombre = (TextView) findViewById(R.id.tvNombre);
        registerForContextMenu(tvNombre); //El view está disponible para correr el menu de contexto
    }

//Menu de opciones----------------------------------------------------------------------------------
    //Creamos el menu en la activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Obtenemos el menu de nuestros recursos y lo inflamos
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    //Le damos funcionalidad a los botones del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Al switch entra el id de la opcion del menu
        switch(item.getItemId()){

            //Si recibe click en About...
            case R.id.mAbout:
                //Inicia una nueva actividad(About)
                Intent i_abo = new Intent(this, About.class);
                startActivity(i_abo);
                break;

            //Si recibe click en Settings...
            case R.id.mSettings:
                //Inicia una nueva actividad(About)
                Intent i_set = new Intent(this, Settings.class);
                startActivity(i_set);
                break;

            case R.id.mRefresh:
                Toast.makeText(this, getResources().getString(R.string.menu_refresh), Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

//Menu de contexto----------------------------------------------------------------------------------
    //Le damos vida al menu de contexto
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //Obtenemos el menu de contexto de nuestros recursos y lo inflamos
        getMenuInflater().inflate(R.menu.menu_contexto, menu);
    }

    //Le damos funcionalidad a los botones del menu de contexto
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.mEdit:
                Toast.makeText(this, getResources().getString(R.string.toast_edit), Toast.LENGTH_SHORT).show();
                break;

            case R.id.mDelete:
                Toast.makeText(this, getResources().getString(R.string.toast_delete), Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

//Menu Pop Up---------------------------------------------------------------------------------------
    public void levantarMenuPopUp(View v){
        //Conectamos el Image View con el codigo
        ImageView imagen = (ImageView) findViewById(R.id.ivImagen);
        PopupMenu popupMenu = new PopupMenu(this, imagen);   //Creamos un popup

        //Inflamos el popup para poder visualizarlo
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());

        //Recibe el valor del menu seleccionado
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.mView:
                        Toast.makeText(getBaseContext(), getResources().getString(R.string.menu_view),Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mViewDetail:
                        Toast.makeText(getBaseContext(), getResources().getString(R.string.menu_view_detail),Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        popupMenu.show();   //Mostramos el menu
    }
}
