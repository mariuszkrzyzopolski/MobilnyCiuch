import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.example.mobilnyciuch.LoginFragment
import com.example.mobilnyciuch.MainActivity
import com.example.mobilnyciuch.helpers.RetrofitHelper
import com.example.mobinyciuch.services.UserService
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CheckIfLoginFieldsAreAvailableMonkeyTest {
    @Test
    fun runMonkeyTest() {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val userService = RetrofitHelper.getInstance().create(UserService::class.java)
        // Launch the MainActivity
        val scenario = ActivityScenario.launch(MainActivity::class.java)

        // Wait for the LoginFragment to appear
        scenario.onActivity { activity ->
            activity.supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, LoginFragment(userService))
                .commitAllowingStateLoss()
        }

        // Wait for the login screen elements to appear
        val timeout = 500L
        val usernameField = device.wait(Until.findObject(By.res("com.example.mobilnyciuch:id/loginEditText")), timeout)
        val passwordField = device.wait(Until.findObject(By.res("com.example.mobilnyciuch:id/passwordEditText")), timeout)
        val loginButton = device.wait(Until.findObject(By.res("com.example.mobilnyciuch:id/btn_login")), timeout)

        // Check if login screen elements are found
        assertTrue("Login screen elements not found", usernameField != null && passwordField != null && loginButton != null)
        scenario.close()
    }
}
