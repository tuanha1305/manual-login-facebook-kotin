package vn.vccorp.tuanhaanh.loginfbkotin.activitys

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vn.vccorp.tuanhaanh.loginfbkotin.R
import vn.vccorp.tuanhaanh.loginfbkotin.constant.Constant
import vn.vccorp.tuanhaanh.loginfbkotin.models.Profile
import vn.vccorp.tuanhaanh.loginfbkotin.network.FacebookService

class ResultLoginActivity : AppCompatActivity() {
    private lateinit var textViewName: TextView;
    private lateinit var textViewId: TextView;
    private lateinit var editTextToken: EditText;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_login)

        // init view

        // init view
        editTextToken = findViewById(R.id.edit_text_token)

        textViewId = findViewById(R.id.tv_id)
        textViewName = findViewById(R.id.tv_name)

        val intent = intent

        if (intent != null) {
            val tokenString = intent.getStringExtra(Constant.ACCESS_TOKEN)

            //set token
            editTextToken.setText(tokenString)

            val gson = GsonBuilder().create()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://graph.facebook.com/v5.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service: FacebookService = retrofit.create(FacebookService::class.java)

            service.getInfoUser("id,name", tokenString)
                ?.enqueue(object : Callback<Profile?> {
                    override fun onResponse(
                        call: Call<Profile?>,
                        response: Response<Profile?>
                    ) {
                        if (response.isSuccessful() && response.code() == 200) {
                            val profile: Profile? = response.body()
                            if (profile != null) {
                                textViewId.setText(profile.id)
                                textViewName.setText(profile.name)
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "ko co dl !",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }

                    override fun onFailure(
                        call: Call<Profile?>,
                        t: Throwable
                    ) {
                        Toast.makeText(
                            applicationContext,
                            "error ! message: " + t.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }

    }
}
