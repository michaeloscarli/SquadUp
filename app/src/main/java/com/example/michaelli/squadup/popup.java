package com.example.michaelli.squadup;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.bailey.mobile.squadup.R;

    public class popup extends DialogFragment {

        private EditText opponent_name;

        public void EditNameDialog() {
            // Empty constructor required for DialogFragment
        }
        public static popup newInstance() {
            popup frag = new popup();
            return frag;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.new_game_popout, container);
            opponent_name = (EditText) view.findViewById(R.id.opponent_team_name);
            Button startGame = (Button) view.findViewById(R.id.start_game_button);
            Button cancel = (Button) view.findViewById(R.id.cancel_button);

            startGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(),CourtActivity.class);
                    i.putExtra("opponentName", opponent_name.getText().toString());
                    startActivity(i);
                    getActivity().getFragmentManager().popBackStack();
                }
            });

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getDialog().dismiss();
                }
            });

            return view;
        }

    }

