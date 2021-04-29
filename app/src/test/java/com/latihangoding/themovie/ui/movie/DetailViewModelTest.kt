package com.latihangoding.themovie.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.latihangoding.themovie.repository.FavoriteRepository
import com.latihangoding.themovie.repository.MovieRepository
import com.latihangoding.themovie.repository.TvRepository
import com.latihangoding.themovie.ui.detail.DetailViewModel
import com.latihangoding.themovie.utils.MainCoroutineScopeRule
import com.latihangoding.themovie.utils.getOrAwaitValue
import com.latihangoding.themovie.vo.CommonDetail
import com.latihangoding.themovie.vo.Favorite
import com.latihangoding.themovie.vo.Genre
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailViewModel

    private val movieRepository = mock<MovieRepository>()
    private val tvRepository = mock<TvRepository>()
    private val favoriteRepository = mock<FavoriteRepository>()


    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepository, tvRepository, favoriteRepository)
    }

    @Test
    fun `setId id2statusTrue setMovieIdTo2`() {
        viewModel.setId(2, true)
        val movieId = viewModel.movieId.getOrAwaitValue()
        assertThat(movieId, `is`(2))
    }

    @Test
    fun `setId id100statusFalse setTvIdTo100`() {
        viewModel.setId(100, false)
        val tvId = viewModel.tvId.getOrAwaitValue()
        assertThat(tvId, `is`(100))
    }

    @Test
    fun `setInfo releaseNullGenresNullRuntimeNull setInfo`() {
        viewModel.setInfo(null, null, null)
        val info = viewModel.info.getOrAwaitValue()
        assertThat(info, `is`(""))
    }

    @Test
    fun `setInfo release21-04-07Genres(Fantasy, Action, Adventure)Runtime140 setInfo`() {
        viewModel.setInfo(
            "21-04-07",
            listOf(
                Genre(1, "Fantasy"),
                Genre(2, "Action"),
                Genre(3, "Adventure")
            ),
            140
        )
        val info = viewModel.info.getOrAwaitValue()
        assertThat(info, `is`("21-04-07 . Fantasy, Action, Adventure . 2h20m"))
    }

    @Test
    fun `setInfo releaseNullGenres(Fantasy, Action, Adventure)Runtime140 setInfo`() {
        viewModel.setInfo(
            null,
            listOf(
                Genre(1, "Fantasy"),
                Genre(2, "Action"),
                Genre(3, "Adventure")
            ),
            140
        )
        val info = viewModel.info.getOrAwaitValue()
        assertThat(info, `is`(". Fantasy, Action, Adventure . 2h20m"))
    }

    @Test
    fun `setInfo release21-04-07GenresNullRuntime140 setInfo`() {
        viewModel.setInfo(
            "21-04-07",
            null,
            140
        )
        val info = viewModel.info.getOrAwaitValue()
        assertThat(info, `is`("21-04-07 . 2h20m"))
    }

    @Test
    fun `setInfo release21-04-07Genres(Fantasy, Action, Adventure)RuntimeNull setInfo`() {
        viewModel.setInfo(
            "21-04-07",
            listOf(
                Genre(1, "Fantasy"),
                Genre(2, "Action"),
                Genre(3, "Adventure")
            ),
            null
        )
        val info = viewModel.info.getOrAwaitValue()
        assertThat(info, `is`("21-04-07 . Fantasy, Action, Adventure "))
    }

    @Test
    fun `setImageUrl myheart imageUrlTo`() {
        viewModel.setImageUrl("myheart")
        val imageUrl = viewModel.imageUrl.getOrAwaitValue()
        assertThat(imageUrl, `is`("myheart"))
    }

    @Test
    fun `setImageUrl null imageUrlToExistingValue`() {
        viewModel.setImageUrl("myheart")
        viewModel.setImageUrl(null)
        val imageUrl = viewModel.imageUrl.getOrAwaitValue()
        assertThat(imageUrl, `is`("myheart"))
    }

    @Test
    fun `setVote 20f voteTo`() {
        viewModel.setVote(20f)
        val vote = viewModel.vote.getOrAwaitValue()
        assertThat(vote, `is`(20f))
    }

    @Test
    fun `setVote null voteToExistingValue`() {
        viewModel.setVote(20f)
        viewModel.setVote(null)
        val vote = viewModel.vote.getOrAwaitValue()
        assertThat(vote, `is`(20f))
    }

    @Test
    fun `setTagLine pintar tagLineTo`() {
        viewModel.setTagLine("pintar")
        val tagLine = viewModel.tagline.getOrAwaitValue()
        assertThat(tagLine, `is`("pintar"))
    }

    @Test
    fun `setTagLine null tagLineToExistingValue`() {
        viewModel.setTagLine("pintar")
        viewModel.setTagLine(null)
        val tagLine = viewModel.tagline.getOrAwaitValue()
        assertThat(tagLine, `is`("pintar"))
    }

    @Test
    fun `setTitle Hebat titleTo`() {
        viewModel.setTitle("Hebat")
        val title = viewModel.title.getOrAwaitValue()
        assertThat(title, `is`("Hebat"))
    }

    @Test
    fun `setTitle null titleToExistingValue`() {
        viewModel.setTitle("Hebat")
        viewModel.setTitle(null)
        val title = viewModel.title.getOrAwaitValue()
        assertThat(title, `is`("Hebat"))
    }

    @Test
    fun `setOverview blablabla overviewTo`() {
        viewModel.setOverview("blablabla")
        val overview = viewModel.overview.getOrAwaitValue()
        assertThat(overview, `is`("blablabla"))
    }

    @Test
    fun `setOverview null overviewToExistingValue`() {
        viewModel.setOverview("blablabla")
        viewModel.setOverview(null)
        val overview = viewModel.overview.getOrAwaitValue()
        assertThat(overview, `is`("blablabla"))
    }

    @Test
    fun `setCreatedBy (Herlian, Zhang) createdByTo`() {
        val creator = listOf(
            CommonDetail(1, "Herlian", null, null, null, null),
            CommonDetail(2, "Zhang", null, null, null, null),
        )
        viewModel.setCreatedBy(creator)
        val createdBy = viewModel.createdBy.getOrAwaitValue()
        assertThat(createdBy, `is`(creator))
    }

    @Test
    fun `setCreatedBy null createdByToExistingValue`() {
        val creator = listOf(
            CommonDetail(1, "Herlian", null, null, null, null),
            CommonDetail(2, "Zhang", null, null, null, null),
        )
        viewModel.setCreatedBy(creator)
        viewModel.setCreatedBy(null)
        val createdBy = viewModel.createdBy.getOrAwaitValue()
        assertThat(createdBy, `is`(creator))
    }

    @Test
    fun `setProductionCompanies (Zara, Google) productionCompaniesTo`() {
        val companies = listOf(
            CommonDetail(1, "Zara", null, null, null, null),
            CommonDetail(2, "Google", null, null, null, null),
        )
        viewModel.setProductionCompanies(companies)
        val productionCompanies = viewModel.productionCompanies.getOrAwaitValue()
        assertThat(productionCompanies, `is`(companies))
    }

    @Test
    fun `setProductionCompanies null productionCompaniesToExistingValue`() {
        val companies = listOf(
            CommonDetail(1, "Zara", null, null, null, null),
            CommonDetail(2, "Google", null, null, null, null),
        )
        viewModel.setProductionCompanies(companies)
        viewModel.setProductionCompanies(null)
        val productionCompanies = viewModel.productionCompanies.getOrAwaitValue()
        assertThat(productionCompanies, `is`(companies))
    }

    @Test
    fun `setFavorite favorite thenUpdateFavoriteAndIsFavorite`() = runBlockingTest {
        given(favoriteRepository.checkIsFavorite(1)).willReturn(true)
        val mFavorite = Favorite(1, false, null, null, null)
        viewModel.setFavorite(mFavorite)
        verify(favoriteRepository, times(1)).checkIsFavorite(1)
        val favorite = viewModel.favorite.getOrAwaitValue()
        val isFavorite = viewModel.isFavorited.getOrAwaitValue()
        assertThat(favorite, `is`(mFavorite))
        assertThat(isFavorite, `is`(true))
    }

    @Test
    fun `checkFavorite favoritedInDatabase thenDeleteFromDatabase`() = runBlockingTest {
        given(favoriteRepository.checkIsFavorite(1)).willReturn(true)

        val mFavorite = Favorite(1, false, null, null, null)
        viewModel.setFavorite(mFavorite)

        viewModel.checkFavorite()

        verify(favoriteRepository, times(1)).deleteFavorite(1)
    }

    @Test
    fun `checkFavorite notFavoritedInDatabase thenInsertToDatabase`() = runBlockingTest {
        given(favoriteRepository.checkIsFavorite(1)).willReturn(false)

        val mFavorite = Favorite(1, false, null, null, null)
        viewModel.setFavorite(mFavorite)

        viewModel.checkFavorite()

        verify(favoriteRepository, times(1)).insertFavorite(mFavorite)
    }
}