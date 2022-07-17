package br.com.howads.calculadora.historico;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import br.com.howads.calculadora.R;
import br.com.howads.calculadora.database.DatabaseHelper;


public class HistoricoFragment extends Fragment {



        static HistoricoFragment newInstance(){
        return new HistoricoFragment();
}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_historico, container, false);
        DatabaseHelper databaseHelper= new DatabaseHelper(getActivity());
        ListView lv = v.findViewById(R.id.historico_listView);
       databaseHelper.getAllValores(getActivity(),lv);
        return v;


    }
}