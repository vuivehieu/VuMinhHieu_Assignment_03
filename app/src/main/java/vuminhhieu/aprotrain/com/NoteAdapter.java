package vuminhhieu.aprotrain.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NoteAdapter extends BaseAdapter {
    private List<Note> noteList;
    private Context context;
    private LayoutInflater layoutInflater;

    public NoteAdapter(List<Note> noteList, Context context) {
        this.noteList = noteList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int i) {
        return noteList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        NoteViewholder viewholder;
        if(view == null){
            view = layoutInflater.inflate(R.layout.item_view,null);
            viewholder = new NoteViewholder();
            viewholder.noteTitle = (TextView) view.findViewById(R.id.txt_title);
            viewholder.noteTime = (TextView) view.findViewById(R.id.txt_date);
            view.setTag(viewholder);
        }
        else {
            viewholder = (NoteViewholder) view.getTag();
        }
        Note note = this.noteList.get(i);
        viewholder.noteTime.setText(note.getTime());
        viewholder.noteTitle.setText(note.getNote());
        return view;
    }
    static class NoteViewholder {
        TextView noteTitle;
        TextView noteTime;
    }
}
