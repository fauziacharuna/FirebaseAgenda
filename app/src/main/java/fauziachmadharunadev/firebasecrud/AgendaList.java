package fauziachmadharunadev.firebasecrud;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fauziachmadharuna on 02/09/18.
 */

//class adapter untuk jembatan antara database dengan view
    //mempassing data ke view

public class AgendaList extends ArrayAdapter<Agenda> {
    private Activity context;
    List<Agenda>agendas;
    public AgendaList(Activity context, List<Agenda> agendas){
        super(context, R.layout.agenda_list, agendas);
        this.context = context;
        this.agendas = agendas;
    }

    @NonNull
    @Override
    //untuk merubah tampilan
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //mengganti tamplian sesuai dengan context
        LayoutInflater inflater = context.getLayoutInflater();
        View listAgenda = inflater.inflate(R.layout.agenda_list, null, true);
        //merubah isi agenda
        TextView isiAgenda = (TextView) listAgenda.findViewById(R.id.teksItem);

        Agenda agenda = agendas.get(position);
        isiAgenda.setText(agenda.getItem());

        return listAgenda;
    }
}
