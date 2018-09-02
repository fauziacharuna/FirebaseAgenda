package fauziachmadharunadev.firebasecrud;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //inisialisasi Firebase Database
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mFirebaseReference;

    //Inisialisasi komponen

    EditText editAgenda;
    Button buttonSimpan;
    ListView listAgenda;
    List<Agenda> agendas;
    TextView cAgenda;

    //String key untuk mengakses perubahan
    public static final String AGENDA_ID = "fauziachmadharunadev.firebasecrud.agendaId";
    public static final String AGENDA_ITEM="fauziachmadharunadev.firebasecrud.agendaItem";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Referensi komponen dengan id
        editAgenda=(EditText)findViewById(R.id.editAgenda);
        buttonSimpan=(Button)findViewById(R.id.buttonAgenda);
        listAgenda=(ListView)findViewById(R.id.listAgenda);
        cAgenda=(TextView)findViewById(R.id.counterAgenda);


        //array untuk menyimpan nilai dari input menjadi objek
        agendas=new ArrayList<>();

        //untuk mereferensikan ke firebase
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mFirebaseReference=mFirebaseDatabase.getReference();

        //listener ketika tombol simpan di klik
        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mengambil nilai item
                String item = editAgenda.getText().toString();
                //mempublish ke firebase
                String id = mFirebaseReference.push().getKey();

                Agenda agenda = new Agenda(id, item);

                //membuat objek database baru
                mFirebaseReference.child(id).setValue(agenda);
                Toast.makeText(getApplicationContext(), "Berhasil Menambah ToDo List", Toast.LENGTH_LONG).show();
            }




        });
        listAgenda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Agenda agenda= agendas.get(position);
                showUpdateDeleteDialog(agenda.getId(), agenda.getItem());
                Intent intent = new Intent(getApplicationContext(), Agenda.class);
                intent.putExtra(AGENDA_ID, agenda.getId());
                intent.putExtra(AGENDA_ITEM, agenda.getItem());
                startActivity(intent);
            }
        });



    }

    private void showUpdateDeleteDialog(final String id, String item) {
        final EditText editText;
        final Button buttonUpdate;
        final Button buttonDelete;

        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(this);
        LayoutInflater inflater=getLayoutInflater();
        final View dialogView=inflater.inflate(R.layout.agenda_update,null);
        dialogBuilder.setView(dialogView);

        editText=(EditText)dialogView.findViewById(R.id.editAgenda);
        buttonUpdate=(Button)dialogView.findViewById(R.id.buttonUpdate);
        buttonDelete=(Button)dialogView.findViewById(R.id.buttonDelete);

        dialogBuilder.setTitle(item);
        final AlertDialog b=dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editText.getText().toString().trim();
                if (!TextUtils.isEmpty(item)) {
                    updateAgenda(id, item);
                    b.dismiss();
                }
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAgenda(id);
                b.dismiss();
            }
        });
    }

    private boolean deleteAgenda(String id) {
        mFirebaseReference.child(id).removeValue();
        Toast.makeText(getApplicationContext(),"Agenda telah dihapus", Toast.LENGTH_LONG).show();
        return true;

    }

    private boolean updateAgenda(String id, String item) {
        Agenda agenda = new Agenda(id, item);
        mFirebaseReference.child(id).setValue(agenda);
        Toast.makeText(getApplicationContext(), "Agenda terupdate", Toast.LENGTH_LONG).show();
        return true;

    }

    @Override
    protected void onStart() {
        super.onStart();

        mFirebaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                agendas.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Agenda agenda=postSnapshot.getValue(Agenda.class);
                    agendas.add(agenda);
                }

                AgendaList agendaAdapter = new AgendaList(MainActivity.this,agendas);
                listAgenda.setAdapter(agendaAdapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
