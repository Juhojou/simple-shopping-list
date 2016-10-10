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

public class EditProductDialogFragment extends DialogFragment {

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
        builder.setTitle(R.string.edit_product)
                .setView(inflater.inflate(R.layout.fragment_edit_product, null))
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onEditDialogPositiveClick(EditProductDialogFragment.this);
                    }
                })
                .setNegativeButton("Отказ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onEditDialogNegativeClick(EditProductDialogFragment.this);
                    }
                });


        return builder.create();
    }

    public interface DialogListener {
        void onEditDialogPositiveClick(DialogFragment dialog);

        void onEditDialogNegativeClick(DialogFragment dialog);
    }

}
