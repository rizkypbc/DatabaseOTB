package bpp.arnet.project.databaseotb.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import bpp.arnet.project.databaseotb.DetailOTB;
import bpp.arnet.project.databaseotb.Model.OTB;
import bpp.arnet.project.databaseotb.R;

public class AdapterOTB extends BaseAdapter {

//    private List<OTB> otbList;
//    private Context context;
//
//
//    public AdapterOTB(List<OTB> otbList, Context context) {
//        super(context, R.layout.list_otb, otbList);
//        this.otbList = otbList;
//        this.context = context;
//    }


    private Context context;
    private LayoutInflater inflater;
    private List<OTB> otbList;

    public AdapterOTB(Context context, List<OTB> otbList) {
        this.context = context;
        this.otbList = otbList;
    }

    @Override
    public int getCount() {
        return otbList.size ();
    }

    @Override
    public Object getItem(int position) {
        return otbList.get (position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        LayoutInflater inflater = LayoutInflater.from (context);
//        View listViewItem = inflater.inflate (R.layout.list_otb, null, false);
//        TextView textViewOTB = listViewItem.findViewById (R.id.textNamaOTB);
//        OTB otb = otbList.get (position);
//        textViewOTB.setText (otb.getNama ());
//        return listViewItem;

        if (inflater == null)

            inflater = (LayoutInflater) context.getSystemService (
                    Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null)
                convertView = inflater.inflate (R.layout.list_otb, null);

            TextView textViewNama = (TextView) convertView.findViewById (R.id.textNamaOTB);

            final OTB otb = otbList.get (position);
            textViewNama.setText (otb.getNama ());


            convertView.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    //Open Detail Activity
                    openDetailOTB (otb.getId (), otb.getNama (), otb.getTipe (), otb.getArah (), otb.getRak (), otb.getKapasitas (), otb.getData_port (), otb.getFoto (), otb.getNama_lokasi ());

                }
            });
            return convertView;
        }

        private void openDetailOTB(String id, String nama, String tipe, String arah, String rak, String kapasitas, String data_port, String foto, String nama_lokasi){

            Intent intent = new Intent (context, DetailOTB.class);
            //Data
            intent.putExtra ("ID_KEY", id);
            intent.putExtra ("NAMA_KEY", nama);
            intent.putExtra ("TIPE_KEY", tipe);
            intent.putExtra ("ARAH_KEY", arah);
            intent.putExtra ("RAK_KEY", rak);
            intent.putExtra ("KAPASITAS_KEY", kapasitas);
            intent.putExtra ("DATA_PORT_KEY", data_port);
            intent.putExtra ("FOTO_KEY", foto);
            intent.putExtra ("LOKASI_KEY", nama_lokasi);

            context.startActivity (intent);

        }
    }

