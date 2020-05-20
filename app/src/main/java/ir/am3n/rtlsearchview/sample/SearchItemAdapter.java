package ir.am3n.rtlsearchview.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.List;

public class SearchItemAdapter extends ArrayAdapter<SearchItem> {

    private int resourceLayout;
    private int clickViewId;
    private int txt1Id;
    private int txt2Id;
    private List<SearchItem> searchItemList;
    private LayoutInflater layoutInflater;
    private Listener listener;

    public SearchItemAdapter(@NonNull Context context, int resourceLayout, int clickViewId, int txt1Id, int txt2Id, List<SearchItem> searchItemList, Listener listener) {
        super(context, resourceLayout, searchItemList);
        this.resourceLayout = resourceLayout;
        this.clickViewId = clickViewId;
        this.txt1Id = txt1Id;
        this.txt2Id = txt2Id;
        this.searchItemList = searchItemList;
        this.listener = listener;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final SearchItem item = searchItemList.get(position);

        View view = layoutInflater.inflate(resourceLayout, null);

        View clickView = view.findViewById(clickViewId);
        clickView.setOnClickListener(v -> listener.onItemClick(item));

        AppCompatTextView txt1 = view.findViewById(txt1Id);
        txt1.setText(item.address);

        AppCompatTextView txt2 = view.findViewById(txt2Id);
        if (!item.address.equals("")) {
            txt2.setText(item.address);
            txt2.setVisibility(View.VISIBLE);
        } else
            txt2.setVisibility(View.GONE);

        return view;
    }

    public interface Listener {
        void onItemClick(SearchItem item);
    }
}