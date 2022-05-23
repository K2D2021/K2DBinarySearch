package ru.k2d.k2dbinarysearch

import androidx.fragment.app.DialogFragment

class AcceptToClearHistory : DialogFragment() {
    /*override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(getString(R.string.full_clear))
                .setMessage(getString(R.string.approve_to_clear))
                .setCancelable(true)
                .setPositiveButton(
                    getString(R.string.yes_text)
                ) { dialog, id -> (getActivity() as FullHistory).clearData() }
                .setNegativeButton(
                    getString(R.string.no_text)
                ) { dialog, id ->
                }
            builder.create()
        } ?: throw IllegalStateException(getString(R.string.cannot_be_null))
    }
*/

}
