package yams.yams

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Player(val name: String, var score: Int = 0) : Parcelable {
}