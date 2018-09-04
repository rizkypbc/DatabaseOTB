package bpp.arnet.project.databaseotb.Port;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import bpp.arnet.project.databaseotb.R;

public class PortHolder extends RecyclerView.ViewHolder {

    public TextView idPort;
    public TextView corePort;
    public TextView userPort;
    public TextView directionPort;
    public TextView keteranganPort;
    public TextView nama_otb;



    public PortHolder(View itemView) {
        super (itemView);

        idPort = (TextView)itemView.findViewById (R.id.textIdPort);
        corePort = (TextView)itemView.findViewById (R.id.textCore);
        userPort = (TextView)itemView.findViewById (R.id.textUser);
        directionPort = (TextView)itemView.findViewById (R.id.textDirection);
        keteranganPort = (TextView)itemView.findViewById (R.id.textKeterangan);
    }
}
