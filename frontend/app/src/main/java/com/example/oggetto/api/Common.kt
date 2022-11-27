import com.example.oggetto.api.JSONPlaceHolderApi
import com.example.oggetto.api.networkservice

object Common {
    val retrofitService: JSONPlaceHolderApi
        get() = networkservice.getClient().create(JSONPlaceHolderApi::class.java)
}
