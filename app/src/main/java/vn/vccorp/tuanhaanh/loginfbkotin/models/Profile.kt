package vn.vccorp.tuanhaanh.loginfbkotin.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Profile {
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

}