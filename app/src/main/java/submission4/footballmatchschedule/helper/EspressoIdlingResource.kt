package submission4.footballmatchschedule

import android.support.test.espresso.IdlingResource

object EspressoIdlingResource {

    private val RESOURCE = "GLOBAL"

    private val mCountingIdlingResource = SimpleCountingIdlingResource(RESOURCE)

    fun increment() {
        mCountingIdlingResource.increment()
    }

    fun decrement() {
        mCountingIdlingResource.decrement()
    }

    fun gettingIdlingResource(): IdlingResource {
        return mCountingIdlingResource
    }

}