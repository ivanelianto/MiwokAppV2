package com.example.android.miwok;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesFragment extends Fragment
{
    private MediaPlayerHandler mediaPlayer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.model_list, container, false);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Where are you going?", "minto wuksus", null, R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә", null, R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...", null, R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?", null, R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "kuchi achit", null, R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?", null, R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’әәnәm", null, R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm", null, R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis", null, R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem", null, R.raw.phrase_come_here));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_phrases);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Word word = words.get(position);
                mediaPlayer = new MediaPlayerHandler(getActivity(), word.getmAudioResourceId());
                mediaPlayer.play();
            }
        });

        return rootView;
    }

    @Override
    public void onStop()
    {
        super.onStop();
        try
        {
            mediaPlayer.releaseAudioResource();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
