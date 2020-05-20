package ir.am3n.rtlsearchview.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ir.am3n.rtlsearchview.RtlSearchview;

public class MainActivity extends AppCompatActivity {

    AppCompatImageButton imgbSearch;
    RtlSearchview srchv;
    SearchItemAdapter searchItemAdapter;
    List<SearchItem> searchItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgbSearch = findViewById(R.id.imgbSearch);
        srchv = findViewById(R.id.srchv);

        imgbSearch.setOnClickListener(v -> srchv.openSearch(imgbSearch));


        searchItemAdapter = new SearchItemAdapter(
            getBaseContext(),
            R.layout.itm_search,
            R.id.rlv,
            R.id.txt1,
            R.id.txt2,
            searchItemList,
            item -> {
                srchv.closeSearch();
            });
        srchv.setSelectionsListViewAdapter(searchItemAdapter);

        srchv.setOnQueryTextListener(new RtlSearchview.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchItemList.add(new SearchItem(query));
                searchItemAdapter.notifyDataSetChanged();
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        srchv.setSearchViewListener(new RtlSearchview.SearchViewListener() {
            @Override
            public void onSearchViewOpened() {
                searchItemList.clear();
                searchItemAdapter.notifyDataSetChanged();
            }
            @Override
            public void onSearchViewClosed() {
                searchItemList.clear();
                searchItemAdapter.notifyDataSetChanged();
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (srchv.isOpen()) {
            srchv.closeSearch();
        } else
            super.onBackPressed();
    }

}
