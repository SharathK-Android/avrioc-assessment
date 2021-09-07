package com.avrioc.assessment

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.avrioc.assessment.ui.main.data.ArticlesResponse
import com.avrioc.assessment.ui.main.data.ResultsItem
import com.avrioc.assessment.ui.main.utils.DataResult
import com.avrioc.assessment.ui.main.view.ArticlesListFragment
import com.avrioc.assessment.ui.main.view.MainActivity
import com.squareup.picasso.Picasso
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockkClass
import io.mockk.mockkStatic
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowToast

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class ArticlesListFragmentTest {

    private lateinit var activity: MainActivity
    private lateinit var fragment: ArticlesListFragment

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        mockkStatic(Picasso::class)
        val picassoMock = mockkClass(Picasso::class, relaxed = true)
        coEvery {
            Picasso.get()
        } returns picassoMock

        activity = Robolectric.buildActivity(MainActivity::class.java).create().resume().get()
        fragment = ArticlesListFragment.newInstance()
        startFragment(fragment)
    }

    @Test
    fun testViewOnApiLoading() {
        with(fragment) {
            viewModel.liveData.value = DataResult.Loading(isLoading = true)
            assertEquals(binding.progressBar.visibility, View.VISIBLE)
        }
    }

    @Test
    fun testViewOnApiSuccess() {
        with(fragment) {
            viewModel.liveData.value = DataResult.Success(
                ArticlesResponse(
                    listOf(
                        ResultsItem(
                            title = "article",
                            byline = "by avrioc",
                            publishedDate = "2021-09-08"
                        )
                    )
                )
            )
            assertEquals(binding.articlesListView.adapter!!.itemCount, 1)
        }
    }

    @Test
    fun testViewOnApiError() {
        with(fragment) {
            viewModel.liveData.value = DataResult.Error()
            assertEquals(Toast.LENGTH_LONG, ShadowToast.getLatestToast().duration)
        }
    }

    private fun startFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = activity.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(fragment, null)
        fragmentTransaction.commit()
    }
}