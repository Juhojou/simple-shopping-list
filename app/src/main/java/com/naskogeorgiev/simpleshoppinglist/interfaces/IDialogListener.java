package com.naskogeorgiev.simpleshoppinglist.interfaces;

import android.app.DialogFragment;

public interface IDialogListener {
    void onDialogPositiveClick(DialogFragment dialog);
    void onDialogNegativeClick(DialogFragment dialog);
}
