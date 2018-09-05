package bpp.arnet.project.databaseotb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import bpp.arnet.project.databaseotb.Model.Port;
import bpp.arnet.project.databaseotb.R;

public class AdapterDataPort extends RecyclerView.Adapter<AdapterDataPort.ViewHolder> {

    Context context;
    ArrayList<Port> portArrayList;

    public AdapterDataPort(Context context, ArrayList<Port> portArrayList) {
        this.context = context;
        this.portArrayList = portArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.list_data_port, parent, false);
        return new ViewHolder (view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

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

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView idPort;
        public TextView corePort;
        public TextView userPort;
        public TextView directionPort;
        public TextView keteranganPort;
        public TextView nama_otb;

        public ViewHolder(View itemView) {
            super (itemView);
            idPort = (TextView)itemView.findViewById (R.id.textIdPort);
            corePort = (TextView)itemView.findViewById (R.id.textCore);
            userPort = (TextView)itemView.findViewById (R.id.textUser);
            directionPort = (TextView)itemView.findViewById (R.id.textDirection);
            keteranganPort = (TextView)itemView.findViewById (R.id.textKeterangan);
        }
    }
}
