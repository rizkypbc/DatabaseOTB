package bpp.arnet.project.databaseotb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bpp.arnet.project.databaseotb.Model.Port;
import bpp.arnet.project.databaseotb.Port.PortHolder;
import bpp.arnet.project.databaseotb.R;

public class AdapterDataPort extends RecyclerView.Adapter<PortHolder> {

    Context context;
    ArrayList<Port> portArrayList;

    public AdapterDataPort(Context context, ArrayList<Port> portArrayList) {
        this.context = context;
        this.portArrayList = portArrayList;
    }

    @Override
    public PortHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from (context).inflate (R.layout.list_data_port, parent, false);
        return new PortHolder (view);
    }

    @Override
    public void onBindViewHolder(PortHolder holder, int position) {

        final Port port = portArrayList.get (position);

        holder.idPort.setText (port.getId_port ());
        holder.corePort.setText (port.getCore ());
        holder.userPort.setText (port.getUser ());
        holder.directionPort.setText (port.getDirection ());
        holder.keteranganPort.setText (port.getKeterangan ());
    }

    @Override
    public int getItemCount() {
        return portArrayList.size ();
    }
}
