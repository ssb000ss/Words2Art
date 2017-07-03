package com.gmail.ssb000ss.words2part.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.ssb000ss.words2part.R;
import com.gmail.ssb000ss.words2part.db.DBWordsContract;

/**
 * Created by ssb000ss on 26.06.2017.
 */

public class WordAdapterCursor extends RecyclerView.Adapter<WordAdapterCursor.WordViewHolder> {

    Context context;
    Cursor cursor;

    public WordAdapterCursor(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.word_item, parent, false);
        return new WordAdapterCursor.WordViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {

        if (!cursor.moveToPosition(position))return;

        long id= cursor.getLong(cursor.getColumnIndex(DBWordsContract.DBWordEntry._ID));
        String word=cursor.getString(cursor.getColumnIndex(DBWordsContract.DBWordEntry.COLUMN_WORD));
        String translation=cursor.getString(cursor.getColumnIndex(DBWordsContract.DBWordEntry.COLUMN_TRANSLATION));

        holder.tv_word.setText(word);
        holder.tv_translation.setText(translation);
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (cursor != null) cursor.close();
        cursor = newCursor;
        if (newCursor != null) {
            this.notifyDataSetChanged();
        }
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {
        TextView tv_word;
        TextView tv_translation;

        public WordViewHolder(View itemView) {
            super(itemView);
            tv_translation = (TextView) itemView.findViewById(R.id.tv_item_translate);
            tv_word = (TextView) itemView.findViewById(R.id.tv_item_word);
        }
    }
}
