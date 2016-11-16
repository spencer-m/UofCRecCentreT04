package cpsc481.fall2016.uofcreccentret04;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Spencer on 11/9/2016.
 */

public class menu_list_adapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;

    public menu_list_adapter(Activity context, String[] itemname, Integer[] imgid) {
        super(context, R.layout.menu_button, itemname);

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.menu_button, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.menu_text);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.menu_image);

        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);
        return rowView;

    }

}
