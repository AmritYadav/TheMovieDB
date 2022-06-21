import com.amydvdev.domain.Result
import com.amydvdev.domain.respository.MovieRepository
import com.amydvdev.domain.usecase.PopularMovieUseCase
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.IOException

class PopularMovieUseCaseTest {

    private val movieRepository = mock<MovieRepository>()

    private val popularMovieUseCase = PopularMovieUseCase(movieRepository)

    @Test
    fun testGetPopularMovies_withSuccess() = runBlocking {
        // Given that the service responds with success
        whenever(movieRepository.getPopularMovies()).thenReturn(Result.Success(emptyList()))

        // When getting the list of popular movies
        val result = popularMovieUseCase.getPopularMovies()

        // Then there's one request to the popular movies use case
        verify(movieRepository).getPopularMovies()
        // Then the response is successful
        assert(result is Result.Success)
    }

    @Test
    fun getPopularMovies_whenRequestFailed() = runBlocking {
        // Given that the service responds with failure
        whenever(movieRepository.getPopularMovies()).thenReturn(Result.Error(IOException()))

        // When getting the list of popular movies
        val result = popularMovieUseCase.getPopularMovies()

        // Then the response is not successful
        assert(result is Result.Error)
    }
}