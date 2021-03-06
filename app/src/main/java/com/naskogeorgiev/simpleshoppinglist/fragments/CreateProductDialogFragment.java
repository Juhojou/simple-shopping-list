package com.naskogeorgiev.simpleshoppinglist.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.naskogeorgiev.simpleshoppinglist.R;

/**
 * Created by nasko on 11.09.16.
 */

public class CreateProductDialogFragment extends DialogFragment {

    DialogListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setTitle(R.string.new_product)
                .setView(inflater.inflate(R.layout.fragment_new_product, null))
                .setPositiveButton("Добави", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onDialogPositiveClick(CreateProductDialogFragment.this, dialog);
                    }
                })
                .setNegativeButton("Отказ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onDialogNegativeClick(CreateProductDialogFragment.this, dialog);
                    }
                });


        return builder.create();
    }

    public interface DialogListener {
        void onDialogPositiveClick(DialogFragment dialog, DialogInterface dialogInterface);

        void onDialogNegativeClick(DialogFragment dialog, DialogInterface dialogInterface);
    }

}
