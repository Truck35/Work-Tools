package com.example.hello_world2.data;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hello_world2.R;
import com.example.hello_world2.model.Bays;

import java.util.ArrayList;


public class BayListAdapter extends RecyclerView.Adapter<BayListAdapter.ViewHolder> {

    private ArrayList<Bays> localDataSet;
    Context context;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private CardView card_view;

        private CheckBox checkBox;

        private View view;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            this.view = view;
            textView = (TextView) view.findViewById(R.id.BayLocation);
            card_view = (CardView) view.findViewById(R.id.cardView);
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);

        }








        public TextView getTextView() {
            return textView;
        }

        public CheckBox getCheckBox(){return checkBox;}

        public CardView getCardView(){
            return card_view;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public BayListAdapter(ArrayList<Bays> dataSet, Context context) {
        localDataSet = dataSet;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.bay_info, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder,  int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText(localDataSet.get(position).getBaylocation());
        localDataSet.get(position).setId(position);
        viewHolder.getCheckBox().setId(position);
        localDataSet.get(position).set_isChecked(viewHolder.getCheckBox().isChecked());

        //localDataSet.get(position).set_isChecked(viewHolder.checkBox.isChecked());




    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

}
